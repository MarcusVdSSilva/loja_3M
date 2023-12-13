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
import com.example.projetoa3.Objetos.CidadesEstados;
import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.R;

public class VwProdutos extends RecyclerView.ViewHolder {


    private TextView tvNomeProduto;
    private TextView tvMarca;
    private TextView tvValor;
    private ImageView ivFoto;
    private ImageButton ibAdicionar;

    public VwProdutos(@NonNull View itemView) {
        super(itemView);
        configurarComponentes(itemView);

    }

    private void configurarComponentes(View view) {
        tvNomeProduto = view.findViewById(R.id.adp_produtos_tv_nome_produto);
        tvMarca = view.findViewById(R.id.adp_produtos_tv_marca);
        tvValor = view.findViewById(R.id.adp_produtos_tv_valor);
        ivFoto = view.findViewById(R.id.apd_produtos_iv_foto_produto);
        ibAdicionar =  view.findViewById(R.id.adp_produtos_ib_adicionar);

    }

    public void preencherComponentes(Produto produto) {
        tvNomeProduto.setText(produto.getNomeProduto());
        tvMarca.setText(produto.getMarca());
        tvValor.setText(Biblioteca.formatarValorReais(produto.getValor()));
        if(produto.getFoto() != null)
            ivFoto.setImageBitmap(produto.getFoto());
    }

    public void configurarAcoes(Produto produto, Context context) {

        ibAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CtrProdutos.adicionarProdutoCarrinho(produto);
                ActCarrinho.iniciarActivity(context);
            }
        });

    }

}
