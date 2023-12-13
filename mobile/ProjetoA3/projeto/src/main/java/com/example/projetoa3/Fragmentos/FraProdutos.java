package com.example.projetoa3.Fragmentos;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetoa3.Activities.ActCarrinho;
import com.example.projetoa3.Adapters.AdpProdutos;
import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Controladores.CtrProdutos;
import com.example.projetoa3.Interfaces.CbPesquisaProdutos;
import com.example.projetoa3.Interfaces.CbProdutosCarrinho;
import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.R;

import java.util.ArrayList;

public class FraProdutos extends FraApp implements CbPesquisaProdutos, CbProdutosCarrinho {

    private LinearLayout llCarrinho;
    private TextView tvValor;
    private TextView tvQuantidade;
    private EditText edPesquisa;
    private ImageButton ibPesquisa;
    private RecyclerView rvProdutos;
    private AdpProdutos adpProdutos;
    private Handler uiHandler;
    private ArrayList<Produto> produtos = CtrProdutos.buscarProdutos();
    private String quantidadeTotal;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fra_produtos, container, false);
        uiHandler = new Handler(Looper.getMainLooper());
        configurarComponentes(view);
        preencherComponentes();
        configurarAcoes();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tvValor.setText(Biblioteca.formatarValorReais(Produto.calcularValorTotal(CtrProdutos.getProdutosCarrinho())));
        tvQuantidade.setText(quantidadeTotal + Produto.calcularQuantidadeTotal(CtrProdutos.getProdutosCarrinho()));
    }

    @Override
    public void configurarComponentes(View view) {
        llCarrinho = view.findViewById(R.id.fra_produtos_ll_carrinho);
        tvValor = view.findViewById(R.id.fra_produtos_tv_valor_compra);
        tvQuantidade = view.findViewById(R.id.fra_produtos_tv_quantidade_produtos);
        edPesquisa = view.findViewById(R.id.fra_produtos_ed_pesquisa);
        ibPesquisa = view.findViewById(R.id.fra_produtos_ib_pesquisa);
        rvProdutos = view.findViewById(R.id.fra_produtos_rv_produtos);
        adpProdutos = new AdpProdutos(getContext());
        quantidadeTotal = getResources().getString(R.string.total_quantity);
    }

    @Override
    public void preencherComponentes() {

        tvValor.setText(Biblioteca.formatarValorReais(Produto.calcularValorTotal(CtrProdutos.getProdutosCarrinho())));
        tvQuantidade.setText(quantidadeTotal + Produto.calcularQuantidadeTotal(CtrProdutos.getProdutosCarrinho()));

        Biblioteca.formatarCampoPesquisaProdutos(this, edPesquisa,produtos);
        adpProdutos.atualizar(produtos);

        rvProdutos.setAdapter(adpProdutos);
        rvProdutos.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void configurarAcoes() {
        ibPesquisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adpProdutos.atualizar(Produto.buscarProdutoPorNome(produtos, edPesquisa.getText().toString()));
            }
        });

        llCarrinho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActCarrinho.iniciarActivity(getContext());
            }
        });
    }

    @Override
    public void callbackPesquisaProdutos(ArrayList<Produto> produtos) {
        uiHandler.post(new Runnable() {
            @Override
            public void run() {
                adpProdutos.atualizar(produtos);
            }
        });

    }

    @Override
    public void atualizarValor() {
        tvValor.setText(Biblioteca.formatarValorReais(Produto.calcularValorTotal(CtrProdutos.getProdutosCarrinho())));
        tvQuantidade.setText(quantidadeTotal + Produto.calcularQuantidadeTotal(CtrProdutos.getProdutosCarrinho()));
    }
}