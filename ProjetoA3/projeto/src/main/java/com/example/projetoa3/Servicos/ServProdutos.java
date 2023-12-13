package com.example.projetoa3.Servicos;

import com.example.projetoa3.Biblioteca.Ambiente;
import com.example.projetoa3.Biblioteca.Json;
import com.example.projetoa3.Biblioteca.Requisicao;
import com.example.projetoa3.Enums.MetodoRequisicao;
import com.example.projetoa3.Enums.TiposConteudosRequisicoes;
import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.Objetos.Venda;

import java.util.ArrayList;

public class ServProdutos {

    private static final String BUSCAR_PRODUTOS = "produto/listarPCliente.php";
    private static final String FINALIZAR_COMPRA = "venda/cadastrar.php";
    private static final String LISTAR_COMPRAS = "venda/listarCompraCliente.php";

    public static ArrayList<Produto> buscarProdutos(){

        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor()+BUSCAR_PRODUTOS,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        Retorno retorno = requisicao.executarRequisicao();

        return Json.toArrayList(retorno.getDados(), Produto.class);
    }

    public static Retorno finalizarCompra(Venda venda, int usuarioId) {
        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor() + FINALIZAR_COMPRA,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        requisicao.addParametros("usuarioId", usuarioId);
        requisicao.addParametros("venda", Json.toJson(venda));

        Retorno retorno = requisicao.executarRequisicao();

        return retorno;
    }
}
