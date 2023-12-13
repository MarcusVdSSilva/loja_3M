package com.example.projetoa3.Objetos;

import android.graphics.Bitmap;

import com.example.projetoa3.Biblioteca.Biblioteca;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;

public class Produto {

    @SerializedName("id")
    private int produtoId;
    @SerializedName("nome")
    private String nomeProduto;
    @SerializedName("marca")
    private String marca;
    @SerializedName("preco")
    private BigDecimal valor;
    @SerializedName("quantidade")
    private int quantidade = 1;
    @SerializedName("foto64")
    private String foto;

    public Produto(
        int produtoId,
        String nomeProduto,
        String marca,
        BigDecimal valor,
        String foto
    ) {
        this.produtoId = produtoId;
        this.nomeProduto = nomeProduto;
        this.marca = marca;
        this.valor = valor;
        this.foto = foto;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getMarca() {
        return marca;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Bitmap getFoto() {

        if(Biblioteca.isStringVazia(foto))
            return null;

        try{
            return Biblioteca.comprimirImagem(Biblioteca.base64ParaBitmap(foto),32,32);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    public int getQuantidade(){
        return quantidade == 0 ? 1 : quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void adicionarQuantidade(int quantidade){
        if(this.quantidade == 0)
            this.quantidade = 1;

        this.quantidade += quantidade;
    }

    public void removerQuantidade(int quantidade){
        if(this.quantidade == 1)
            return;
        this.quantidade -= quantidade;
    }

    public static ArrayList<Produto> buscarProdutoPorNome(ArrayList<Produto> produtos, String nome){
        ArrayList<Produto> retorno = new ArrayList<>();

        if(Biblioteca.isStringVazia(nome)){
            return produtos;
        }

        for(Produto produto : produtos) {
            if(produto.getNomeProduto().toUpperCase().contains(nome.toUpperCase()))
                retorno.add(produto);
        }

        return retorno;
    }


    public static BigDecimal calcularValorTotal(ArrayList<Produto> produtosCarrinho) {
        BigDecimal retorno = BigDecimal.ZERO;

        for(Produto produto : produtosCarrinho){
            retorno = retorno.add(produto.getValor().multiply(new BigDecimal(produto.getQuantidade())));
        }

        return retorno.setScale(2);
    }

    public static int calcularQuantidadeTotal(ArrayList<Produto> produtosCarrinho) {
        int retorno = 0;

        for(Produto produto : produtosCarrinho) {
            retorno += produto.getQuantidade();
        }

        return retorno;
    }

    public static Produto localizarProduto(ArrayList<Produto> produtosCarrinho, int produtoId) {
        for(Produto produto : produtosCarrinho){
            if(produto.getProdutoId() == produtoId)
                return produto;
        }

        return null;
    }


}
