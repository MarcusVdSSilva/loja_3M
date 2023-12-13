package com.example.projetoa3.Enums;

import java.util.ArrayList;

public enum DiasSemana {

    DOMINGO(1,"Domingo"),
    SEGUNDA(2,"Segunda"),
    TERCA(3,"Terça"),
    QUARTA(4,"Quarta"),
    QUINTA(5, "Quinta"),
    SEXTA(6, "Sexta"),
    SABADO(7, "Sábado");

    private int indice;
    private String descricao;

    DiasSemana(int indice, String descricao){
        this.indice = indice;
        this.descricao = descricao;
    }

    public static ArrayList<Integer> getListaInt(ArrayList<DiasSemana> frequencia) {
        ArrayList<Integer> lista = new ArrayList<>();

        for(DiasSemana freq : frequencia){
            lista.add(freq.getIndice());
        }

        return lista;
    }

    public static ArrayList<DiasSemana> getListaDias(ArrayList<Integer> frequencia) {
        ArrayList<DiasSemana> diasSemana = new ArrayList<>();

        for(int freq : frequencia)
            diasSemana.add(DiasSemana.getDiasSemanaPorIndice(freq));

        return diasSemana;
    }

    private static DiasSemana getDiasSemanaPorIndice(int freq) {
        for(DiasSemana dia : DiasSemana.values()){
            if(dia.getIndice() == freq)
                return dia;
        }

        return null;
    }

    public int getIndice() {
        return indice;
    }

    public String getDescricao() {
        return descricao;
    }
}
