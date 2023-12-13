package com.example.projetoa3.Enums;

public enum Rastreadores {
    NAO_TEM(1,"Não tem"),
    TEM(2, "Tem, mas não sei a marca"),
    AUTOTRAC(3, "Autotrac"),
    OMNILINK(4,"Omnilink"),
    ONIX_SAT(5,"Onix Sat"),
    POSITRON(6,"Positron"),
    SASCAR(7,"Sascar"),
    PORTO_SEGURO(8,"Porto Seguro");

    private int indice;
    private String descricao;

    Rastreadores(int indice, String descricao){
        this.indice = indice;
        this.descricao = descricao;
    }

    public static Rastreadores getTipoPorIndice(int indice) {
        for(Rastreadores rastreador : Rastreadores.values()){
            if(rastreador.getIndice() == indice)
                return rastreador;
        }
        return null;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getIndice() {
        return indice;
    }
}
