package com.example.projetoa3.Biblioteca;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Base64;
import com.example.projetoa3.Enums.MetodoRequisicao;
import com.example.projetoa3.Enums.TiposConteudosRequisicoes;
import com.example.projetoa3.Objetos.Retorno;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;


public class Requisicao {

    private HashMap<String, String> requestProperty;
    private HashMap<String, Object> parametros = new HashMap<>();
    private MetodoRequisicao metodo;
    private TiposConteudosRequisicoes tipoConteudo;
    private String endereco;
    private SSLSocketFactory sockFact;
    private String chaveArquivo;
    private File arquivo;
    private String corpoRaw;
    private String boundary = UUID.randomUUID().toString();
    private int [] respostasServidor = {200,201,403,406,415};

    public Requisicao(
            String enderecoRequisicao,
            MetodoRequisicao metodoRequisicao,
            TiposConteudosRequisicoes tipoConteudo
    ) {

        this.endereco = enderecoRequisicao;
        this.metodo = metodoRequisicao;
        this.tipoConteudo = tipoConteudo;
        this.requestProperty = new HashMap<>();
    }

    public void addParametroUrl(String nome, String valor) {

        if(this.metodo == MetodoRequisicao.GET) {
            if(this.endereco.contains("?")){
                this.endereco += "&" + nome + "=" + valor;
            }
            else {
                this.endereco += "?" + nome + "=" + valor;
            }
        }
    }

    public void addRequestProperty(String parametro, String valor){
        this.requestProperty.put(parametro, valor);
    }

    public void addParametros(String chave, Object valores){
        addParametros(chave, valores, false);
    }

    public void addParametros(String chave, Object valores, boolean codificar){
        try {
            if (codificar)
                valores = URLEncoder.encode((String) valores, "UTF-8");
        }
        catch (Exception e) {
        }

        parametros.put(chave, valores);
    }

    public void addParametroBase64(String chave, String valor){
        try {
            parametros.put(chave, Base64.encodeToString(valor.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addParametroJson(String chave, Object valores) {

        HashMap<String, Object> jsonChaveParametro = new HashMap<>();

        jsonChaveParametro.put(chave, valores);

        this.corpoRaw = Json.toJson(jsonChaveParametro);

    }

    public void addCertificado(String caminhoCertificado, String senhaCertificado) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException, UnrecoverableKeyException, KeyManagementException {

        InputStream stream = null;
        KeyStore clientStore = null;
        clientStore = KeyStore.getInstance("PKCS12");
        stream = new FileInputStream(caminhoCertificado);

        clientStore.load(stream, senhaCertificado.toCharArray());

        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(clientStore, senhaCertificado.toCharArray());
        KeyManager[] kms = kmf.getKeyManagers();

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(kms, null, new SecureRandom());

        this.sockFact = sslContext.getSocketFactory();


    }

    public void addFile(String chaveArquivo, File arquivo) {
        this.chaveArquivo = chaveArquivo;
        this.arquivo = arquivo;
    }

    public void addCorpoRaw(String corpoRaw) {
        this.corpoRaw = corpoRaw;
    }

    private String montarCorpoRequisicao() {
        StringBuilder corpoRequisicao = new StringBuilder();

        switch(tipoConteudo) {

            case JSON:

            case RAW:
                corpoRequisicao.append(this.corpoRaw);
                break;

            case URLENCODED:
                for (Map.Entry<String, Object> parametro : parametros.entrySet()) {
                        String append = (corpoRequisicao.toString().isEmpty() ? "" : "&") + parametro.getKey() + "=" + parametro.getValue();
                    corpoRequisicao.append(append);
                }
                break;

            default:
                break;
        }

        return corpoRequisicao.toString();
    }

    public Retorno executarRequisicao() {

        Retorno retorno = new Retorno();

        @SuppressLint("StaticFieldLeak") AsyncTask servico = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Retorno retorno = new Retorno();

                try {

                    if (metodo == MetodoRequisicao.PATCH)
                        allowMethods(metodo.toString());

                    if (tipoConteudo == TiposConteudosRequisicoes.MULTFORMDATA) {
                        retorno = executarRequisicaoMultFormData();
                        return retorno;
                    }

                    URL url = new URL(endereco);
                    HttpURLConnection con;
                    if (url.getProtocol().equalsIgnoreCase("https")) {
                        con = (HttpsURLConnection) url.openConnection();
                        setSSLSocket((HttpsURLConnection) con);
                    } else {
                        con = (HttpURLConnection) url.openConnection();
                    }

                    con.setRequestMethod(metodo.toString());
                    if (metodo != MetodoRequisicao.GET)
                        con.setRequestProperty("Content-Type", tipoConteudo.toString());

                    if (tipoConteudo == TiposConteudosRequisicoes.JSON)
                        addRequestProperty("Accept", tipoConteudo.toString());

                    requestProperty.forEach((chave, valor) -> {
                        con.setRequestProperty(chave, valor);
                    });

                    con.setConnectTimeout(5000);
                    con.setReadTimeout(5000);

                    if(metodo != MetodoRequisicao.GET){
                        con.setDoOutput(true);
                        DataOutputStream out = new DataOutputStream(con.getOutputStream());
                        out.writeBytes(montarCorpoRequisicao());
                        out.flush();
                        out.close();
                    }

                    InputStream in;
                    int status = con.getResponseCode();

                    if (isErro(status))
                        in = con.getErrorStream();
                    else
                        in = con.getInputStream();

                    String contentEncoding = con.getContentEncoding();

                    if (!Biblioteca.isStringVazia(contentEncoding)) {
                        if (contentEncoding.equalsIgnoreCase("gzip"))
                            in = new GZIPInputStream(in);
                        else if (contentEncoding.equalsIgnoreCase("deflate"))
                            in = new InflaterInputStream(in, new Inflater(true));
                    }

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }

                    if (isErro(status))
                        retorno.definirErro(response.toString());
                    else
                        retorno.setDados(response.toString());
                } catch (Exception e) {
                    retorno.definirErro(e.getMessage());
                }

                return retorno;
            }
        };

        try{

            retorno = (Retorno) servico.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    private boolean isErro(int status) {
        for(int i: respostasServidor) {
            if(status == i)
                return false;
        }

        return true;
    }

    private void setSSLSocket(HttpsURLConnection con){
        if (sockFact != null)
            con.setSSLSocketFactory(sockFact);
    }
    private Retorno executarRequisicaoMultFormData() throws IOException {
        Retorno retorno = new Retorno();

        boundary = UUID.randomUUID().toString();
        String LINE = "\r\n";
        URL url = new URL(this.endereco);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        httpConn.setRequestProperty("User-Agent", "*");
        this.requestProperty.forEach((chave, valor) -> {
            httpConn.setRequestProperty(chave, valor);
        });

        OutputStream outputStream = httpConn.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, "utf-8"), true);

        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            String chave = entry.getKey();
            Object valor = entry.getValue();
            writer.append("--" + boundary).append(LINE);
            writer.append("Content-Disposition: form-data; name=\"" + chave + "\"").append(LINE);
            writer.append("Content-Type: text/plain; charset=" + "utf-8").append(LINE);
            writer.append(LINE);
            writer.append(valor.toString()).append(LINE);
        }

        if(this.arquivo != null) {
            String fileName = this.arquivo.getName();
            writer.append("--" + boundary).append(LINE);
            writer.append("Content-Disposition: form-data; name=\"" + this.chaveArquivo + "\"; filename=\"" + fileName + "\"").append(LINE);
            writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(fileName)).append(LINE);
            writer.append("Content-Transfer-Encoding: binary").append(LINE);
            writer.append(LINE);
            writer.flush();

            FileInputStream inputStream = new FileInputStream(this.arquivo);
            byte[] buffer = new byte[4096];
            int bytesRead = -1;

            while ((bytesRead = inputStream.read(buffer)) != -1)
                outputStream.write(buffer, 0, bytesRead);

            outputStream.flush();
            inputStream.close();
            writer.append(LINE);
            writer.flush();
        }

        writer.append("--" + boundary + "--").append(LINE);
        writer.close();


        int status = httpConn.getResponseCode();
        boolean statusOk = (status == HttpURLConnection.HTTP_OK || status == 201);
        InputStream in = statusOk ? httpConn.getInputStream() : httpConn.getErrorStream();

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] newBuffer = new byte[1024];
        int length;
        while ((length = in.read(newBuffer)) != -1)
            result.write(newBuffer, 0, length);

        httpConn.disconnect();

        if(statusOk)
            retorno.setDados(result.toString());
        else
            retorno.definirErro(result.toString());

        return retorno;
    }

    private static void allowMethods(String... methods) {
        try {
            @SuppressLint("SoonBlockedPrivateApi")
            Field methodsField = HttpURLConnection.class.getDeclaredField("methods");  // Remove final modifier

            Field modifiersField = Field.class.getDeclaredField("modifiers"); //Pego os modificadores
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL); // torno a class acessível por ser statica
            methodsField.setAccessible(true);

            String[] oldMethods = (String[]) methodsField.get(null);
            Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null, newMethods);
        }

        catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    private static byte[] compress(String data) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
            gzip.write(data.getBytes("UTF-8"));
        }
        return bos.toByteArray();
    }

}


