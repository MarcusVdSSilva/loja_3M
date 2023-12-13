package com.example.projetoa3.Enums;

public enum TiposConteudosRequisicoes {
    JSON("application/json"),
    URLENCODED("application/x-www-form-urlencoded"),
    MULTFORMDATA("multipart/form-data"),
    RAW("application/raw"),
    XML("application/xml");

    private final String valor;

    TiposConteudosRequisicoes(final String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return valor;
    }
}