package com.example.projetoa3.Objetos;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Venda {

    @SerializedName("id")
    private int vendaId;
    @SerializedName("valor")
    private BigDecimal valor;
    @SerializedName("produtos")
    private ArrayList<Produto> produtos;
    @SerializedName("data")
    private Timestamp data;

    public Venda(
        int vendaId,
        BigDecimal valor,
        ArrayList<Produto> produtos
    ) {
        this.vendaId = vendaId;
        this.valor = valor;
        this.produtos = produtos;
    }

    public int getVendaId() {
        return vendaId;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public Timestamp getData() {
        return data;
    }
}
