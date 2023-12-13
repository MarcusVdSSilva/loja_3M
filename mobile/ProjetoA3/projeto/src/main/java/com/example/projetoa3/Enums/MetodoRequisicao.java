package com.example.projetoa3.Enums;

public enum MetodoRequisicao {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    PATCH("PATCH");

    private final String descricao;

    MetodoRequisicao(final String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}