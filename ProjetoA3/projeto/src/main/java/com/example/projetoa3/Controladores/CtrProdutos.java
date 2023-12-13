package com.example.projetoa3.Controladores;

import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.Objetos.Venda;
import com.example.projetoa3.Servicos.ServProdutos;

import java.util.ArrayList;

public class CtrProdutos {

    private static ArrayList<Produto> produtosCarrinho = new ArrayList<>();

    public static ArrayList<Produto> buscarProdutos(){
        return ServProdutos.buscarProdutos();
    }

    public static void adicionarProdutoCarrinho(Produto produto){
        Produto produtoLista = Produto.localizarProduto(produtosCarrinho, produto.getProdutoId());

        if(produtoLista != null){
            produtoLista.adicionarQuantidade(1);
            return;
        }

        produtosCarrinho.add(produto);
    }

    public static void limparCarrinho(){
        produtosCarrinho.clear();
    }

    public static ArrayList<Produto> getProdutosCarrinho(){
        return produtosCarrinho;
    }

    public static void removerProdutoCarrinho(Produto produto){
        produtosCarrinho.remove(produto);
    }

    public static Retorno finalizarCompra() {

        Venda venda = new Venda(0, Produto.calcularValorTotal(produtosCarrinho), produtosCarrinho);

        return ServProdutos.finalizarCompra(venda, CtrLogin.getUsuario().getUsuarioId());
    }

}
