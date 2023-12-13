package com.example.projetoa3.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoa3.Adapters.AdpProdutosCarrinho;
import com.example.projetoa3.Adapters.AdpVendas;
import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Biblioteca.Dialogo;
import com.example.projetoa3.Controladores.CtrProdutos;
import com.example.projetoa3.Controladores.CtrVendas;
import com.example.projetoa3.Interfaces.CbProdutosCarrinho;
import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.R;

public class ActCompras extends ActApp{

    private RecyclerView rvVendas;
    private AdpVendas adpVendas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_compras);

        configurarComponentes();
        preencherComponentes();
        configurarAcoes();

    }

        @Override
    public void configurarComponentes() {

        rvVendas = findViewById(R.id.act_compras_rv_vendas);
        adpVendas = new AdpVendas(this);

    }

    @Override
    public void preencherComponentes() {
        adpVendas.atualizar(CtrVendas.getCompras());
        rvVendas.setAdapter(adpVendas);
        rvVendas.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void configurarAcoes() {

    }

    public static void iniciarActivity(Context contexto){
        Intent intent = new Intent(contexto, ActCompras.class);
        contexto.startActivity(intent);
    }

}
