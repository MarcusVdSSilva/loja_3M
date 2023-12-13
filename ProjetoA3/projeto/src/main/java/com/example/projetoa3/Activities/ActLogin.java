package com.example.projetoa3.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.example.projetoa3.Activities.registrar.ActInfoUsuario;
import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Biblioteca.Dialogo;
import com.example.projetoa3.Controladores.CtrGeral;
import com.example.projetoa3.Controladores.CtrLogin;
import com.example.projetoa3.R;

public class ActLogin extends ActApp {

    private Button btEntrar;
    private TextView tvRegistrar;
    private EditText edEmail;
    private EditText edSenha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        CtrGeral.setActAtual(0);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_login);

        configurarComponentes();
        preencherComponentes();
        configurarAcoes();

    }

    @Override
    public void configurarComponentes(){
        btEntrar = findViewById(R.id.act_login_bt_entrar);
        tvRegistrar = findViewById(R.id.act_login_tv_registrar);
        edEmail = findViewById(R.id.act_login_ed_email);
        edSenha = findViewById(R.id.act_login_ed_senha);
    }

    @Override
    public void preencherComponentes() {
    }

    @Override
    public void configurarAcoes(){
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Biblioteca.esconderTeclado(ActLogin.this, edEmail, false);
                Biblioteca.esconderTeclado(ActLogin.this, edSenha, false);

                if(!CtrGeral.ping()){
                    Dialogo.informar(ActLogin.this, "Sem conexão com a internet!");
                    return;
                }

                CtrGeral.setActAtual(1);

                CtrLogin.logarUsuario(edEmail.getText().toString(), edSenha.getText().toString());

                if(CtrLogin.getUsuario() == null){
                    Dialogo.informar(ActLogin.this, "Usuario ou senha inválidos");
                    return;
                }

                ActMenu.iniciarActivity(ActLogin.this);
            }
        });


        tvRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!CtrGeral.ping()){
                    Dialogo.informar(ActLogin.this, "Sem conexão com a internet!");
                    return;
                }

                CtrGeral.setActAtual(1);

                ActInfoUsuario.iniciarActivity(ActLogin.this);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public static void iniciarActivity(Context contexto) {
        Intent intent = new Intent(contexto, ActLogin.class);
        contexto.startActivity(intent);
    }

}
