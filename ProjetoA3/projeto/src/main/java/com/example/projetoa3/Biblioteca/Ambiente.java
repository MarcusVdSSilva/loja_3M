package com.example.projetoa3.Biblioteca;

import com.example.projetoa3.Controladores.CtrLogin;
import com.example.projetoa3.Servicos.ServGeral;

public class Ambiente {

    private static String ENDERECO_SERVIDOR = "http://memsolucoestecnologicas.com.br/App/Controller/";

    public static String getEnderecoServidor(){
        return ENDERECO_SERVIDOR;
    }

}
