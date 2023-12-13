package com.example.projetoa3.ViewHolders;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoa3.Interfaces.CbProdutosCarrinho;
import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.R;

public class VwProdutosCarrinho extends RecyclerView.ViewHolder {


    private TextView tvNomeProduto;
    private TextView tvQuantidade;

    private ImageButton ibAdicionar;
    private ImageButton ibRemover;

    private CbProdutosCarrinho callback;

    public VwProdutosCarrinho(@NonNull View itemView) {
        super(itemView);
        configurarComponentes(itemView);

    }

    private void configurarComponentes(View view) {
        tvNomeProduto = view.findViewById(R.id.adp_produtos_carrinho_tv_nome_produto);
        tvQuantidade = view.findViewById(R.id.adp_produtos_carrinho_tv_quantidade);
        ibAdicionar =  view.findViewById(R.id.adp_produtos_carrinho_ib_adicionar);
        ibRemover =  view.findViewById(R.id.adp_produtos_carrinho_ib_remover);
    }

    public void preencherComponentes(Produto produto, Context context) {
        tvNomeProduto.setText(produto.getNomeProduto());
        tvQuantidade.setText(Integer.toString(produto.getQuantidade()));
        callback = (CbProdutosCarrinho) context;
    }

    public void configurarAcoes(Produto produto){

        ibAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produto.adicionarQuantidade(1);
                tvQuantidade.setText(Integer.toString(produto.getQuantidade()));
                callback.atualizarValor();
            }
        });

        ibRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produto.removerQuantidade(1);
                tvQuantidade.setText(Integer.toString(produto.getQuantidade()));
                callback.atualizarValor();
            }
        });
    }
}
