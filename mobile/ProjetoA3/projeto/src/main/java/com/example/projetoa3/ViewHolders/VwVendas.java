package com.example.projetoa3.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoa3.Activities.ActCarrinho;
import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Controladores.CtrProdutos;
import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.Objetos.Venda;
import com.example.projetoa3.R;

public class VwVendas extends RecyclerView.ViewHolder {


    private TextView tvData;
    private TextView tvValor;

    public VwVendas(@NonNull View itemView) {
        super(itemView);
        configurarComponentes(itemView);

    }

    private void configurarComponentes(View view) {
        tvData = view.findViewById(R.id.adp_vendas_tv_data);
        tvValor = view.findViewById(R.id.adp_vendas_tv_valor);

    }

    public void preencherComponentes(Venda venda) {
        tvData.setText(Biblioteca.formatarData(venda.getData()));
        tvValor.setText(Biblioteca.formatarValorReais(venda.getValor()));

    }

    public void configurarAcoes(Venda venda, Context context) {

    }

}
