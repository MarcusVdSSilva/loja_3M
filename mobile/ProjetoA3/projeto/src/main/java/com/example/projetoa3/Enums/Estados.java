package com.example.projetoa3.Enums;

import java.util.ArrayList;

public enum Estados {

    AC(1, "AC", "Acre"),
    AL(2, "AL", "Alagoas"),
    AM(3, "AM", "Amazonas"),
    AP(4, "AP", "Amapá"),
    BA(5, "BA", "Bahia"),
    CE(6, "CE", "Ceará"),
    DF(7, "DF", "Distrito Federal"),
    ES(8, "ES", "Espirito Santos"),
    GO(9, "GO", "Goiás"),
    MA(10, "MA", "Maranhão"),
    MG(11, "MG", "Minas Gerais"),
    MS(12, "MS", "Mato Grosso do Sul"),
    MT(13, "MT", "Mato Grosso"),
    PA(14, "PA", "Pará"),
    PB(15, "PB", "Paraíba"),
    PE(16, "PE", "Pernanbuco"),
    PI(17, "PI", "Piauí"),
    PR(18, "PR", "Paraná"),
    RJ(19, "RJ", "Rio de Janeiro"),
    RN(20, "RN", "Rio Grande do Norte"),
    RO(21, "RO", "Rondônia"),
    RR(22, "RR", "Roraima"),
    RS(23, "RS","Rio Grande do Sul"),
    SC(24, "SC", "Santa Catarina"),
    SE(25, "SE", "Sergipe"),
    SP(26, "SP", "São Paulo"),
    TO(27, "TO", "Tocantins");

    public static boolean existeEstado(String uf) {
        try{
            obterPorDescricao(uf);
        }
        catch(Exception e){
            return false;
        }

        return true;
    }

    private final int indice;
    private final String uf;
    private final String descricao;

    Estados(int indice, String uf, String descricao) {
        this.indice = indice;
        this.uf = uf;
        this.descricao = descricao;
    }

    public int getIndice() {
        return indice;
    }

    public String getUF() {
        return uf;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Estados obterPorIndice(int indice) {
        Estados[] estados = Estados.values();

        for (Estados estado : estados) {
            if (estado.indice == indice)
                return estado;
        }
        throw new IllegalArgumentException("Não foi encontrado nenhum estado com o índice fornecido.");
    }

    public static Estados obterPorDescricao(String uf) {
        Estados[] estados = Estados.values();

        for (Estados estado : estados) {
            if (estado.uf.contentEquals(uf))
                return estado;
        }
        throw new IllegalArgumentException("Não foi encontrado nenhum estado com o índice fornecido.");
    }

    public static ArrayList<String> getDescricoes(){
        ArrayList<String> descricoes = new ArrayList<>();

        for(Estados estado : Estados.values()){
            descricoes.add(estado.getDescricao().toUpperCase());
        }

        return descricoes;
    }


}