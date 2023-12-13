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
import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Biblioteca.Dialogo;
import com.example.projetoa3.Controladores.CtrProdutos;
import com.example.projetoa3.Interfaces.CbProdutosCarrinho;
import com.example.projetoa3.Objetos.Produto;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.R;

public class ActCarrinho extends ActApp implements CbProdutosCarrinho {

    private RecyclerView rvProdutos;
    private AdpProdutosCarrinho adpProdutosCarrinho;
    private TextView tvValor;
    private TextView tvQuantidadeTotal;
    private Button btFinalizarCompra;
    private String quantidadeTotal;
    private String finalizarCompra;
    private String carrinhoSemItens;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_carrinho);

        configurarComponentes();
        preencherComponentes();
        configurarAcoes();

    }

        @Override
    public void configurarComponentes() {
        btFinalizarCompra = findViewById(R.id.act_carrinho_bt_finalizar_compra);
        rvProdutos = findViewById(R.id.act_carrinho_rv_produtos);
        adpProdutosCarrinho = new AdpProdutosCarrinho(this);
        tvValor = findViewById(R.id.act_carrinho_tv_valor);
        tvQuantidadeTotal = findViewById(R.id.act_carrinho_tv_quantidade_total);
        quantidadeTotal = getResources().getString(R.string.total_quantity);
        finalizarCompra = getResources().getString(R.string.purchase_completed);
        carrinhoSemItens = getResources().getString(R.string.purchase_completed);
    }

    @Override
    public void preencherComponentes() {
        tvValor.setText(Biblioteca.formatarValorReais(Produto.calcularValorTotal(CtrProdutos.getProdutosCarrinho())));
        tvQuantidadeTotal.setText(quantidadeTotal + Produto.calcularQuantidadeTotal(CtrProdutos.getProdutosCarrinho()));
        adpProdutosCarrinho.atualizar(CtrProdutos.getProdutosCarrinho());
        rvProdutos.setAdapter(adpProdutosCarrinho);
        rvProdutos.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void configurarAcoes() {


        btFinalizarCompra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(CtrProdutos.getProdutosCarrinho().size() == 0){
                    Dialogo.informar(ActCarrinho.this, carrinhoSemItens);
                    return;
                }
                Retorno retorno = CtrProdutos.finalizarCompra();

                if(retorno.isHouveErro()){
                    Dialogo.informar(ActCarrinho.this, retorno.getMensagem());
                    return;
                }

                CtrProdutos.limparCarrinho();

                Dialogo.toastInformar(ActCarrinho.this, finalizarCompra);
                finish();
            }
        });
    }

    public static void iniciarActivity(Context contexto){
        Intent intent = new Intent(contexto, ActCarrinho.class);
        contexto.startActivity(intent);
    }

    @Override
    public void atualizarValor() {
        tvValor.setText(Biblioteca.formatarValorReais(Produto.calcularValorTotal(CtrProdutos.getProdutosCarrinho())));
        tvQuantidadeTotal.setText(quantidadeTotal + Produto.calcularQuantidadeTotal(CtrProdutos.getProdutosCarrinho()));
    }
}
