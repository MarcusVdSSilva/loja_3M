package com.example.projetoa3.Fragmentos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import com.example.projetoa3.Activities.registrar.ActInfoSenha;
import com.example.projetoa3.R;

public class FraConfiguracoes extends FraApp {

    private Button btAlterarSenha;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fra_configuracoes, container, false);

        configurarComponentes(view);
        preencherComponentes();
        configurarAcoes();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void configurarComponentes(View view) {
        btAlterarSenha = view.findViewById(R.id.fra_configuracoes_bt_alterar_senha);
    }

    @Override
    public void preencherComponentes() {

    }

    @Override
    public void configurarAcoes() {
        btAlterarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActInfoSenha.iniciarActivity(getContext(), true, false);
            }
        });
    }
}