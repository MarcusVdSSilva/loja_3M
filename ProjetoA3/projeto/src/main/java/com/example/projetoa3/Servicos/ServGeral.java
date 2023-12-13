package com.example.projetoa3.Servicos;


import com.example.projetoa3.Biblioteca.Ambiente;
import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Biblioteca.Json;
import com.example.projetoa3.Biblioteca.Requisicao;
import com.example.projetoa3.Enums.MetodoRequisicao;
import com.example.projetoa3.Enums.TiposConteudosRequisicoes;
import com.example.projetoa3.Objetos.Retorno;

public class ServGeral {

    public static boolean ping(){
        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor()+"ping.php",
                MetodoRequisicao.GET,
                TiposConteudosRequisicoes.RAW
        );

        Retorno retorno = requisicao.executarRequisicao();

        if(retorno.isHouveErro())
            return false;

        return true;
    }

}
