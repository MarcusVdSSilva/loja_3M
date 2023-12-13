package com.example.projetoa3.Controladores;

import com.example.projetoa3.Objetos.CidadesEstados;
import com.example.projetoa3.Servicos.ServGeral;

import java.util.ArrayList;
import java.util.HashMap;

public class CtrGeral {
    private static final int ACT_LOGIN = 0;

    private static int actAtual = -1;

    public static boolean ping(){
        return ServGeral.ping();
    }

    public static void setActAtual(int actAtual) {
        CtrGeral.actAtual = actAtual;
    }

    public static boolean isActLogin() {
        if (actAtual == ACT_LOGIN)
            return true;
        return false;
    }

}
