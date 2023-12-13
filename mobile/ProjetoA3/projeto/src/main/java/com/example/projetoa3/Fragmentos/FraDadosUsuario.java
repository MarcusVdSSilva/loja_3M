package com.example.projetoa3.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.projetoa3.Activities.ActCompras;
import com.example.projetoa3.Activities.registrar.ActInfoUsuario;
import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Controladores.CtrLogin;
import com.example.projetoa3.Controladores.CtrVendas;
import com.example.projetoa3.Objetos.Usuarios;
import com.example.projetoa3.R;

public class FraDadosUsuario extends FraApp {

    private Button btInfoBasica;
    private Button btCompras;
    private ImageView ivFotoPerfil;
    private TextView tvNomeUsuario;
    private final int FOTO_PERFIL = 0;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fra_dados_usuario, container, false);
        configurarComponentes(view);
        preencherComponentes();
        configurarAcoes();
        return view;
    }

    @Override
    public void configurarComponentes(View view) {
        btInfoBasica = view.findViewById(R.id.fra_dados_usuario_bt_info_basica);
        btCompras = view.findViewById(R.id.fra_dados_usuario_bt_listar_vendas);
        ivFotoPerfil = view.findViewById(R.id.fra_dados_usuario_iv_foto_perfil);
        tvNomeUsuario = view.findViewById(R.id.fra_dados_usuario_tv_nome_usuario);

    }

    @Override
    public void onResume(){
        super.onResume();
        preencherComponentes();
    }

    @Override
    public void preencherComponentes() {
        if(CtrLogin.getUsuario().getFotoPerfil() != null)
            ivFotoPerfil.setImageBitmap(CtrLogin.getUsuario().getFotoPerfil());
        tvNomeUsuario.setText(CtrLogin.getUsuario().getNome());
    }

    @Override
    public void configurarAcoes() {

        btInfoBasica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActInfoUsuario.iniciarActivity(getContext(),true);
            }
        });

        ivFotoPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Biblioteca.abrirArquivoFoto(getActivity(), FOTO_PERFIL);
            }
        });

        btCompras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CtrVendas.buscarCompras();
                ActCompras.iniciarActivity(getContext());
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}