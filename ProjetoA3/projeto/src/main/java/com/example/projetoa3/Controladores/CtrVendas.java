package com.example.projetoa3.Controladores;

import com.example.projetoa3.Objetos.Venda;
import com.example.projetoa3.Servicos.ServCompras;

import java.util.ArrayList;

public class CtrVendas {
    private static ArrayList<Venda> compras;
    private static Venda venda;

    public static void setVenda(Venda venda){
        CtrVendas.venda = venda;
    }

    public static Venda getVenda(){
        return venda;
    }

    public static ArrayList<Venda> getCompras() {
        return compras;
    }

    public static void buscarCompras() {
        compras = ServCompras.buscarCompras(CtrLogin.getUsuario().getUsuarioId());
    }
}
