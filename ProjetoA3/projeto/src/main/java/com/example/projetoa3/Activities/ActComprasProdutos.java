package com.example.projetoa3.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoa3.Adapters.AdpComprasProdutos;
import com.example.projetoa3.Controladores.CtrVendas;
import com.example.projetoa3.R;

public class ActComprasProdutos extends ActApp{

    private RecyclerView rvprodutos;
    private AdpComprasProdutos adpProdutos;

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

        rvprodutos = findViewById(R.id.act_compras_rv_vendas);
        adpProdutos = new AdpComprasProdutos(this);

    }

    @Override
    public void preencherComponentes() {
        adpProdutos.atualizar(CtrVendas.getVenda().getProdutos());
        rvprodutos.setAdapter(adpProdutos);
        rvprodutos.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void configurarAcoes() {

    }

    public static void iniciarActivity(Context contexto){
        Intent intent = new Intent(contexto, ActComprasProdutos.class);
        contexto.startActivity(intent);
    }

}
