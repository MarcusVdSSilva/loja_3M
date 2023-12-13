package com.example.projetoa3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.R;
import com.example.projetoa3.ViewHolders.VwProdutos;
import java.util.ArrayList;


public class AdpProdutos extends RecyclerView.Adapter{

    private final Context context;
    private ArrayList<Produto> produtos = new ArrayList<>();

    public AdpProdutos(Context context) {
        this.context = context;
    }


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adp_produtos, viewGroup, false);

        return new VwProdutos(view);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VwProdutos vwProdutos = (VwProdutos) holder;
        vwProdutos.preencherComponentes(produtos.get(position));
        vwProdutos.configurarAcoes(produtos.get(position), context);
    }


    @Override
    public int getItemCount() {
        return produtos.size();
    }


    public void atualizar(ArrayList<Produto> produtosAtualizados){
        produtos.clear();
        produtos.addAll(produtosAtualizados);
        notifyDataSetChanged();
    }



}
