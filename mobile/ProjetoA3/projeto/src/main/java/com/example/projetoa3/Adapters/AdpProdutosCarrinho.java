package com.example.projetoa3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoa3.Controladores.CtrProdutos;
import com.example.projetoa3.Interfaces.CbProdutosCarrinho;
import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.R;
import com.example.projetoa3.ViewHolders.VwProdutos;
import com.example.projetoa3.ViewHolders.VwProdutosCarrinho;

import java.util.ArrayList;


public class AdpProdutosCarrinho extends RecyclerView.Adapter{

    private final Context context;
    private ArrayList<Produto> produtos = new ArrayList<>();
    private CbProdutosCarrinho callback;

    public AdpProdutosCarrinho(Context context) {
        this.context = context;
    }


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adp_produtos_carrinho, viewGroup, false);
        callback = (CbProdutosCarrinho) context;
        return new VwProdutosCarrinho(view);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VwProdutosCarrinho vwProdutos = (VwProdutosCarrinho) holder;
        vwProdutos.preencherComponentes(produtos.get(position), context);
        vwProdutos.configurarAcoes(produtos.get(position));
        configurarAcoes(holder);
    }

    private void configurarAcoes(RecyclerView.ViewHolder holder) {
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                int posicao = holder.getAdapterPosition();
                PopupMenu popupMenu = new PopupMenu(context, view);
                popupMenu.getMenuInflater().inflate(R.menu.mi_excluir, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Produto produto = CtrProdutos.getProdutosCarrinho().get(posicao);
                        switch (item.getItemId()) {
                            case R.id.mi_excluir_excluir:
                                CtrProdutos.removerProdutoCarrinho(produto);
                                produtos.remove(produto);
                                notifyDataSetChanged();
                                callback.atualizarValor();
                                break;

                        }
                        return false;
                    }
                });

                popupMenu.show();
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {
        return produtos.size();
    }


    public void atualizar(ArrayList<Produto> rotasAtualizadas){
        produtos.clear();
        produtos.addAll(rotasAtualizadas);
        notifyDataSetChanged();
    }



}
