package com.example.projetoa3.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projetoa3.Activities.ActComprasProdutos;
import com.example.projetoa3.Controladores.CtrVendas;
import com.example.projetoa3.Objetos.Venda;
import com.example.projetoa3.R;
import com.example.projetoa3.ViewHolders.VwVendas;

import java.util.ArrayList;


public class AdpVendas extends RecyclerView.Adapter{

    private final Context context;
    private ArrayList<Venda> vendas = new ArrayList<>();

    public AdpVendas(Context context) {
        this.context = context;
    }


    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adp_vendas, viewGroup, false);

        return new VwVendas(view);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VwVendas vwVendas = (VwVendas) holder;
        vwVendas.preencherComponentes(vendas.get(position));
        vwVendas.configurarAcoes(vendas.get(position), context);
        configurarAcoes(holder);
    }


    private void configurarAcoes(RecyclerView.ViewHolder holder) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int posicao = holder.getAdapterPosition();
                CtrVendas.setVenda(vendas.get(posicao));
                ActComprasProdutos.iniciarActivity(context);
            }
        });
    }


    @Override
    public int getItemCount() {
        return vendas.size();
    }


    public void atualizar(ArrayList<Venda> vendasAtualizado){
        vendas.clear();
        vendas.addAll(vendasAtualizado);
        notifyDataSetChanged();
    }



}
