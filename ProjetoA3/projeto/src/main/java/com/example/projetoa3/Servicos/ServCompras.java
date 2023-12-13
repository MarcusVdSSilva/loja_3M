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

public class ServCompras {

    private static final String LISTAR_COMPRAS = "venda/listarCompraCliente.php";

    public static ArrayList<Venda> buscarCompras(int usuarioId) {
        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor() + LISTAR_COMPRAS,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        requisicao.addParametros("usuarioId", usuarioId);

        Retorno retorno = requisicao.executarRequisicao();

        return Json.toArrayList(retorno.getDados(), Venda.class);
    }
}
