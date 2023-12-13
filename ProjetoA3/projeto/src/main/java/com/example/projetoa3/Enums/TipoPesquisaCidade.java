package com.example.projetoa3.Enums;

public enum TipoPesquisaCidade {

    ORIGEM(0, "orgiem"),
    DESTINO(1, "destino"),
    PARADA(2, "parada"),
    PARADA_CIDADE(3, "lista de cidades dialogo");

    private final int indice;
    private final String descricao;

    TipoPesquisaCidade(int indice, String descricao){
        this.indice = indice;
        this.descricao = descricao;
    }
}
