package com.example.projetoa3.Biblioteca;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.ParcelFileDescriptor;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.projetoa3.Interfaces.CbPesquisaProdutos;
import com.example.projetoa3.Objetos.CidadesEstados;
import com.example.projetoa3.Objetos.Produto;

import org.mindrot.jbcrypt.BCrypt;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Biblioteca {

    private static final Pattern FORMATADOR_EMAIL = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern FORMATADOR_PALCA = Pattern.compile("^[A-Z]{3}\\d{4}$");
    private static final Pattern FORMATADOR_PALCA_MERCO = Pattern.compile("^[A-Z]{3}\\d{1}[A-Z]{1}\\d{2}$");
    private static final Pattern FORMATADOR_SENHA = Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=]).{8,}$");
    private static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat("dd/MM/yyyy");

    public static void esconderTeclado(Context context, View view, boolean hideImplicityOnly) {
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (hideImplicityOnly)
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        else
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isStringVazia(String... valores) {
        for(String valor : valores){
            if(isStringVazia(valor))
                return true;
        }

        return false;
    }

    public static boolean isStringVazia(String json) {
        if (json == null)
            return true;
        else if (json.isEmpty())
            return true;
        else if (json.contentEquals(""))
            return true;

        return false;
    }

    public static boolean validarNome(String nome) {
        if(isStringVazia(nome) || nome.length() < 3)
            return false;
        return true;
    }

    public static String numeros(String valor){
        String numeros = "";

        for(char c : valor.toCharArray()){
            if(Character.isDigit(c))
                numeros += c;
        }

        return numeros;
    }

    public static boolean isTelefoneValido(String telefone) {

        if(isStringVazia(telefone) || numeros(telefone).length() < 10)
            return false;
        else
            return true;
    }

    public static boolean validarCPFCnpj(String cpfCnpj){
        //Removendo mascara e  espaços em branco
        cpfCnpj = numeros(cpfCnpj);

        if(cpfCnpj.length() == 11)
            return isCPFValido(cpfCnpj);
        else if(cpfCnpj.length() == 14)
            return isCNPJValido(cpfCnpj);

        return false;
    }

    public static boolean isCPFValido(String cpf) {
        int[] numeros1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] numeros2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int digito1 = 0;
        int digito2 = 0;

        for (int i = 0; i < numeros1.length; i++)
            digito1 += Character.getNumericValue(cpf.charAt(i)) * numeros1[i];

        for (int i = 0; i < numeros2.length; i++)
            digito2 += Character.getNumericValue(cpf.charAt(i)) * numeros2[i];

        int mod1 = digito1 % 11;
        int mod2 = digito2 % 11;

        digito1 = mod1 < 2 ? 0 : 11 - mod1;
        digito2 = mod2 < 2 ? 0 : 11 - mod2;

        return cpf.charAt(9) == digito1 + '0' && cpf.charAt(10) == digito2 + '0';
    }

    public static boolean isCNPJValido(String cnpj) {
        int[] numeros1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] numeros2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum1 = 0;
        int sum2 = 0;

        for (int i = 0; i < numeros1.length; i++)
            sum1 += Character.getNumericValue(cnpj.charAt(i)) * numeros1[i];

        for (int i = 0; i < numeros2.length; i++)
            sum2 += Character.getNumericValue(cnpj.charAt(i)) * numeros2[i];

        int mod1 = sum1 % 11;
        int mod2 = sum2 % 11;
        int firstDigit = mod1 < 2 ? 0 : 11 - mod1;
        int secondDigit = mod2 < 2 ? 0 : 11 - mod2;

        return cnpj.charAt(12) == firstDigit + '0' && cnpj.charAt(13) == secondDigit + '0';
    }

    public static boolean isEmailValido(String email) {
        Matcher matcher = FORMATADOR_EMAIL.matcher(email);
        return matcher.matches();
    }

    public static void formatarCampoTelefone(EditText edTelefone) {

        edTelefone.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(15)});

        edTelefone.addTextChangedListener(new TextWatcher() {

            boolean usuarioDigitou = true;
            boolean apagando = false;
            boolean ajustado = false;
            boolean ajustar = false;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                apagando = i1 > 0;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if((i1+1 < i2 && usuarioDigitou) || ajustar){
                    ajustar = false;
                    String texto;
                    String textoCopiado = Biblioteca.numeros(charSequence.toString());

                    int pos = textoCopiado.length() > 10 ? 7 : 6;

                    if(textoCopiado.length() >= 7)
                        texto = "(" + textoCopiado.subSequence(0,2) + ") " + textoCopiado.subSequence(2,pos) + "-" + textoCopiado.subSequence(pos,textoCopiado.length());
                    else
                        texto = "(" + textoCopiado.subSequence(0,2) + ") " + textoCopiado.subSequence(2,textoCopiado.length());

                    usuarioDigitou = false;
                    edTelefone.setText(texto);
                    edTelefone.setSelection(edTelefone.getText().length());

                }

                usuarioDigitou = true;

                if(!apagando) {
                    int posicao = edTelefone.getText().length() - 1;
                    switch (posicao) {
                        case 0:
                            edTelefone.setText("(" + charSequence);
                            edTelefone.setSelection(edTelefone.getText().length());
                            return;

                        case 2:
                            edTelefone.setText(charSequence + ")");
                            edTelefone.setSelection(edTelefone.getText().length());
                            return;
                        case 4:
                            edTelefone.setText(
                                    charSequence.subSequence(0,charSequence.length()-1)
                                    + " " +
                                    charSequence.subSequence(charSequence.length()-1, charSequence.length()));
                            edTelefone.setSelection(edTelefone.getText().length());
                            return;
                        case 8:
                            edTelefone.setText(charSequence + "-");
                            edTelefone.setSelection(edTelefone.getText().length());
                            return;
                    }

                    if(charSequence.length() == 15) {
                        ajustar = true;
                        edTelefone.setText(charSequence);
                        edTelefone.setSelection(edTelefone.getText().length());
                    }
                }
                else{
                    if(edTelefone.getText().length() != 14)
                        ajustado = false;

                    if(edTelefone.getText().length() == 14 && !ajustado){
                        ajustado = true;
                        String texto = Biblioteca.numeros(charSequence.toString());
                        String textoFormatado = "(" + texto.subSequence(0,2) + ") " + texto.subSequence(2,6) + "-" + texto.subSequence(6,texto.length());
                        edTelefone.setText(textoFormatado);
                        edTelefone.setSelection(edTelefone.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Este método é chamado depois que o texto foi alterado.

            }
        });
    }

    public static void formatarCampoCpfCnpj(EditText edCpfCnpj) {

        edCpfCnpj.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(18)});

        edCpfCnpj.addTextChangedListener(new TextWatcher() {

            boolean usuarioDigitou = true;
            boolean apagando = false;
            boolean ajustado = false;
            boolean ajustar = false;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                apagando = i1 > 0;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if((i1+1 < i2 && usuarioDigitou) || ajustar){
                    ajustar = false;
                    String texto;
                    String textoCopiado = Biblioteca.numeros(charSequence.toString());

                    if(textoCopiado.length() >= 12)
                        texto = textoCopiado.subSequence(0,2) + "." +
                            textoCopiado.subSequence(2,5) + "." +
                            textoCopiado.subSequence(5,8) + "/" +
                            textoCopiado.subSequence(8,12) + "-" +
                            textoCopiado.subSequence(12,textoCopiado.length());
                    else {
                        if(textoCopiado.length() <= 3) {
                            texto = textoCopiado.subSequence(0, 3) + ".";
                        }
                        else if(textoCopiado.length() <= 6){
                            texto = textoCopiado.subSequence(0, 3) + "." + textoCopiado.subSequence(3, textoCopiado.length());
                        }
                        else if (textoCopiado.length() <= 9){
                            texto = textoCopiado.subSequence(0, 3) + "." + textoCopiado.subSequence(3, 6) + "." + textoCopiado.subSequence(6, textoCopiado.length());
                        }
                        else
                            texto = textoCopiado.subSequence(0, 3) + "." + textoCopiado.subSequence(3, 6) + "." + textoCopiado.subSequence(6, 9) + "-" + textoCopiado.subSequence(9, textoCopiado.length());
                    }

                    usuarioDigitou = false;
                    edCpfCnpj.setText(texto);
                    edCpfCnpj.setSelection(edCpfCnpj.getText().length());

                }

                usuarioDigitou = true;

                if(!apagando) {
                    int posicao = edCpfCnpj.getText().length() - 1;
                    switch (posicao) {
                        case 2:

                        case 6:
                            edCpfCnpj.setText(charSequence + ".");
                            edCpfCnpj.setSelection(edCpfCnpj.getText().length());
                            return;
                        case 10:
                            edCpfCnpj.setText(charSequence + "-");
                            edCpfCnpj.setSelection(edCpfCnpj.getText().length());
                            return;

                    }

                    if(charSequence.length() == 17) {
                        ajustar = true;
                        edCpfCnpj.setText(charSequence);
                        edCpfCnpj.setSelection(edCpfCnpj.getText().length());
                    }
                }
                else{
                    if(edCpfCnpj.getText().length() != 14)
                        ajustado = false;

                    if(edCpfCnpj.getText().length() == 14 && !ajustado){
                        ajustado = true;
                        String texto = Biblioteca.numeros(charSequence.toString());
                        String textoFormatado =  texto.subSequence(0,3) + "." + texto.subSequence(3,6) + "." + texto.subSequence(6,9) + "-" + texto.subSequence(9, texto.length());
                        edCpfCnpj.setText(textoFormatado);
                        edCpfCnpj.setSelection(edCpfCnpj.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Este método é chamado depois que o texto foi alterado.

            }
        });
    }

    public static void formatarCampoPlaca(EditText edPlaca) {

        edPlaca.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(8)});
        edPlaca.setInputType(InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

        edPlaca.addTextChangedListener(new TextWatcher() {

            boolean usuarioDigitou = true;
            boolean apagando = false;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                apagando = i1 > 0;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if((i1+1 < i2 && usuarioDigitou)){
                    String texto;
                    String textoCopiado = charSequence.toString();

                    if(textoCopiado.length() >= 3)
                        texto = textoCopiado.subSequence(0,3) + "-" +
                                textoCopiado.subSequence(3,textoCopiado.length());
                    else {
                        texto = textoCopiado;
                    }

                    usuarioDigitou = false;
                    edPlaca.setText(texto);
                    edPlaca.setSelection(edPlaca.getText().length());

                }

                usuarioDigitou = true;

                if(!apagando) {
                    int posicao = edPlaca.getText().length() - 1;
                    switch (posicao) {

                        case 2:
                            edPlaca.setText(charSequence + "-");
                            edPlaca.setSelection(edPlaca.getText().length());
                            return;

                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Este método é chamado depois que o texto foi alterado.

            }
        });
    }

    public static void formatarCampoCep(EditText edCep) {

        edCep.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(9)});

        edCep.addTextChangedListener(new TextWatcher() {

            boolean usuarioDigitou = true;
            boolean apagando = false;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                apagando = i1 > 0;
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if((i1+1 < i2 && usuarioDigitou)){
                    String texto;
                    String textoCopiado = Biblioteca.numeros(charSequence.toString());

                    if(textoCopiado.length() >= 6)
                        texto = textoCopiado.subSequence(0,5) + "-" +
                                textoCopiado.subSequence(5,textoCopiado.length());
                    else {
                        texto = textoCopiado;
                    }

                    usuarioDigitou = false;
                    edCep.setText(texto);
                    edCep.setSelection(edCep.getText().length());

                }

                usuarioDigitou = true;

                if(!apagando) {
                    int posicao = edCep.getText().length() - 1;
                    switch (posicao) {

                        case 4:
                            edCep.setText(charSequence + "-");
                            edCep.setSelection(edCep.getText().length());
                            return;

                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public static void formatarCampoPesquisaProdutos(
            CbPesquisaProdutos callback,
            EditText edPesquisa,
            ArrayList<Produto> produtosCompletos
    ) {
        final ArrayList<Produto>[] lista = new ArrayList[]{(ArrayList<Produto>) produtosCompletos.clone()};

        edPesquisa.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        lista[0] = Produto.buscarProdutoPorNome(produtosCompletos, edPesquisa.getText().toString());
                        callback.callbackPesquisaProdutos(lista[0]);
                    }
                }).start();

            }
        });
    }


    public static ArrayList<CidadesEstados> getCidadesPorEstado(int estadoId, ArrayList<CidadesEstados> cidadesEstadosCompletos){
        ArrayList<CidadesEstados> lista = new ArrayList<>();

        for(CidadesEstados cidadesEstado : cidadesEstadosCompletos){
            if(cidadesEstado.getEstado().getIndice() == estadoId)
                lista.add(cidadesEstado);
        }

        return lista;
    }

    public static void formatarCampoRntrc(EditText edRntrc){
        edRntrc.setInputType(InputType.TYPE_CLASS_NUMBER);
        edRntrc.setFilters(new InputFilter.LengthFilter[]{new InputFilter.LengthFilter(8)});
    }

    public static void configurarSpinner(Context contexto, Spinner spinner, ArrayList<String> descricoes) {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(
                contexto,
                android.R.layout.simple_spinner_item,
                descricoes
        );

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }

    public static boolean isCepValido(String cep) {
        String diditos = numeros(cep);

        if(diditos.length() < 8)
            return false;
        return true;

    }

    public static boolean isCamposVazios(String ... campos) {
        for(String campo : campos){
            if(isStringVazia(campo))
                return true;
        }

        return false;
    }

    public static boolean isPlacaValida(String placa) {
        placa = somenteLetrasNumeros(placa);
        Matcher matcher = FORMATADOR_PALCA.matcher(placa);
        if(!matcher.matches())
            matcher = FORMATADOR_PALCA_MERCO.matcher(placa);
        return matcher.matches();
    }

    public static boolean isRntrcValido(String rntrc){
        String numRntrc = numeros(rntrc);

        return numRntrc.length() == 8;
    }

    public static String somenteLetrasNumeros(String texto){
        String retorno = "";

        for(char c: texto.toCharArray()){
            if(Character.isDigit(c) || Character.isAlphabetic(c))
                retorno += c;
        }

        return retorno;
    }

    public static void abrirArquivo(Activity act, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        act.startActivityForResult(intent, requestCode);
    }

    public static void abrirArquivoFoto(Activity act, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        act.startActivityForResult(intent, requestCode);
    }

    public static String getDocumentoPdf(Activity act, Intent data) {
        String retorno = null;
        try {
            InputStream inputStream = act.getContentResolver().openInputStream(data.getData());
            byte[] pdfBytes = new byte[inputStream.available()];
            inputStream.read(pdfBytes);
            inputStream.close();

            retorno =  new String(pdfBytes, StandardCharsets.UTF_8);


        }catch (IOException e){

        }

        return pdfParaBase64(retorno);
    }

    public static Bitmap getImagemBitMap(Activity act, Intent data) {
        Bitmap image = null;
        try {
            ParcelFileDescriptor parcelFileDescriptor =
                act.getContentResolver().openFileDescriptor(data.getData(), "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
            image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
            parcelFileDescriptor.close();
        }
        catch (IOException e){

        }

        return image;
    }

    public static Bitmap comprimirImagem(Bitmap bmOriginal, int altura, int largura){

        Bitmap bmNovo = Bitmap.createScaledBitmap(bmOriginal, largura, altura, false);
        bmNovo.setDensity(DisplayMetrics.DENSITY_LOW);

        return bmNovo;
    }

    public static String bitmapParaBase64(Bitmap imagem) {
        return bitmapParaBase64(imagem, false);
    }

    public static String bitmapParaBase64(Bitmap imagem, Boolean fundoTransparente) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (fundoTransparente)
            imagem.compress(Bitmap.CompressFormat.PNG, 100, stream);
        else
            imagem.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        return Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP);
    }

    public static Bitmap base64ParaBitmap(String imagem) {
        byte[] imagemDedocada = Base64.decode(imagem, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imagemDedocada, 0, imagemDedocada.length);
    }

    public static String codificarSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());

    }


    public static boolean isSenhaValida(String senha){
        Matcher matcher = FORMATADOR_SENHA.matcher(senha);
        return matcher.matches();
    }

    public static String pdfParaBase64(String valor){
        return Base64.encodeToString(valor.getBytes(StandardCharsets.UTF_8), Base64.DEFAULT);
    }

    public static String formatarData(Timestamp data){
        return FORMATADOR_DATA.format(data);
    }

    public static boolean intToBoolean(int valor) {
        return valor == 1;
    }

    public static String formatarValorReais(BigDecimal valor) {
        return "R$ " + valor.setScale(2).toString();
    }
}
