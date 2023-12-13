package com.example.projetoa3.Objetos;

public class Retorno {
    private boolean houveErro;
    private String mensagem;
    private String dados;

    public Retorno(){
        houveErro = false;
        mensagem = "Sucesso";
        dados = "";
    }

    public void setDados(String dados){
        this.dados = dados;
    }

    public void definirErro(String erro){
        houveErro = true;
        mensagem = erro;
        dados = "";
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public boolean isHouveErro() {
        return houveErro;
    }

    public String getDados() {
        return dados;
    }
}
