package com.example.projetoa3.Objetos;

import com.example.projetoa3.Enums.Estados;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CidadesEstados {
    @SerializedName("cidadeId")
    private int cidadeId;
    @SerializedName("cidade")
    private String cidade;
    @SerializedName("codIbge")
    private String codIbge;
    @SerializedName("estadoId")
    private int estadoId;

    public CidadesEstados(
            int cidadeId,
            String cidade,
            String codIbge,
            int estadoId
    ){
        this.cidadeId = cidadeId;
        this.cidade = cidade;
        this.codIbge = codIbge;
        this.estadoId = estadoId;
    }

    public static CidadesEstados getCidadeEstadoPorNome(ArrayList<CidadesEstados> cidadesEstados, String nomeCidade) {
        for(CidadesEstados cidadeEstado : cidadesEstados){
            if(cidadeEstado.getCidade().contentEquals(nomeCidade))
                return cidadeEstado;
        }

        return null;
    }

    public static ArrayList<Integer> getListaInt(ArrayList<CidadesEstados> cidadesParada) {
        ArrayList<Integer> retorno = new ArrayList<>();

        for(CidadesEstados cidade : cidadesParada){
            retorno.add(cidade.getCidadeId());
        }

        return retorno;
    }

    public static CidadesEstados getCidadePorId(ArrayList<CidadesEstados> cidadesEstadosCompletos, int cidadeOrigemId) {
        for(CidadesEstados cidade : cidadesEstadosCompletos){
            if(cidade.getCidadeId() == cidadeOrigemId)
                return cidade;
        }

        return null;
    }

    public static ArrayList<CidadesEstados> getCidadesPorListaId(ArrayList<CidadesEstados> cidadesEstadosCompletos, ArrayList<Integer> cidadesParada) {
        ArrayList<CidadesEstados> retorno = new ArrayList<>();

        for(int cidadeId : cidadesParada){
           retorno.add(getCidadePorId(cidadesEstadosCompletos,cidadeId));
        }

        return retorno;
    }

    public int getCidadeId() {
        return cidadeId;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCodIbge() {
        return codIbge;
    }

    public Estados getEstado() {
        return Estados.obterPorIndice(estadoId);
    }

    public static ArrayList<String> getNomesCidades(ArrayList<CidadesEstados> cidadesEstados){
        ArrayList<String> nomesCidades = new ArrayList<>();

        for(CidadesEstados cidadeEstado : cidadesEstados){
            nomesCidades.add(cidadeEstado.getCidade());
        }

        return nomesCidades;
    }

    public static int getPosicaoCidade(ArrayList<CidadesEstados> cidadesEstado, int cidadeId) {
        for(int i = 0; i < cidadesEstado.size(); i++){
            if(cidadesEstado.get(i).getCidadeId() == cidadeId)
                return i;
        }

        return 0;
    }

    public static ArrayList<CidadesEstados> getCidadesEstadosPesquisaNome(String nome, ArrayList<CidadesEstados> cidadesEstados){

        nome = nome.toUpperCase();
        ArrayList<CidadesEstados> retorno = new ArrayList<>();

        for(CidadesEstados cidadeEstado : cidadesEstados){
            if(cidadeEstado.getCidade().toUpperCase().contains(nome))
                retorno.add(cidadeEstado);
        }

        return retorno;
    }

}