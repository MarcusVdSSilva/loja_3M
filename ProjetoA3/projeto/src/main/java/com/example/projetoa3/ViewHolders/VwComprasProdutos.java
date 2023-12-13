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
import com.example.projetoa3.R;

public class VwComprasProdutos extends RecyclerView.ViewHolder {


    private TextView tvNomeProduto;
    private TextView tvMarca;
    private TextView tvQuantidade;
    private TextView tvValor;
    private ImageView ivFoto;
    private String quantidade;

    public VwComprasProdutos(@NonNull View itemView) {
        super(itemView);
        configurarComponentes(itemView);

    }

    private void configurarComponentes(View view) {
        tvNomeProduto = view.findViewById(R.id.adp_compras_produtos_tv_nome_produto);
        tvMarca = view.findViewById(R.id.adp_compras_produtos_tv_marca);
        tvQuantidade = view.findViewById(R.id.adp_compras_produtos_tv_quantidade);
        tvValor = view.findViewById(R.id.adp_compras_produtos_tv_valor);
        ivFoto = view.findViewById(R.id.apd_produtos_iv_foto_produto);


    }

    public void preencherComponentes(Produto produto, Context context) {
        quantidade = context.getResources().getString(R.string.total_quantity);
        tvNomeProduto.setText(produto.getNomeProduto());
        tvMarca.setText(produto.getMarca());
        tvQuantidade.setText(quantidade + produto.getQuantidade());
        tvValor.setText(Biblioteca.formatarValorReais(produto.getValor()));
        if(produto.getFoto() != null)
            ivFoto.setImageBitmap(produto.getFoto());
    }

    public void configurarAcoes(Produto produto, Context context) {

    }

}
