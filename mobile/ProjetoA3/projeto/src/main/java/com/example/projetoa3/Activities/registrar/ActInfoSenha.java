package com.example.projetoa3.Activities.registrar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import com.example.projetoa3.Activities.ActApp;
import com.example.projetoa3.Activities.ActLogin;
import com.example.projetoa3.Activities.ActMenu;
import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Biblioteca.Dialogo;
import com.example.projetoa3.Controladores.CtrInfoSenha;
import com.example.projetoa3.Controladores.CtrLogin;
import com.example.projetoa3.Controladores.CtrRegistrar;
import com.example.projetoa3.Interfaces.CbDialogos;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.R;

public class ActInfoSenha extends ActApp implements CbDialogos {

    private EditText edSenha;
    private EditText edRepitaSenha;
    private Button btContinuar;
    private static boolean update;
    private static boolean esqueciSenha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cadastro_senha);
        configurarComponentes();
        preencherComponentes();
        configurarAcoes();
    }

    @Override
    public void configurarComponentes() {
        edSenha = findViewById(R.id.act_cadastro_senha_ed_senha);
        edRepitaSenha = findViewById(R.id.act_cadastro_senha_ed_repita_senha);
        btContinuar = findViewById(R.id.act_cadastro_senha_bt_continuar);
    }

    @Override
    public void preencherComponentes() {
        if(ActInfoSenha.update){
            btContinuar.setText(getResources().getString(R.string.save));
        }
    }

    @Override
    public void configurarAcoes() {
        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(existeCamposVazios())
                    return;

                CtrInfoSenha.setSenha(edSenha.getText().toString());

                if(ActInfoSenha.update){

                    ActInfoSenha.update = false;

                    if(ActInfoSenha.esqueciSenha){
                        CtrInfoSenha.updateSenha();
                        ActInfoSenha.esqueciSenha = false;
                        ActLogin.iniciarActivity(ActInfoSenha.this);
                    }else {
                        CtrInfoSenha.setUsuarioId(CtrLogin.getUsuario().getUsuarioId());
                        CtrInfoSenha.updateSenha();
                        ActMenu.iniciarActivity(ActInfoSenha.this);
                    }

                    Dialogo.toastInformar(ActInfoSenha.this, "Senha alterada com sucesso");
                    return;
                }

                Handler handler = Dialogo.exibirProcessando(ActInfoSenha.this, "Carregando", "Aguarde", 0);

                new Thread(() -> {
                    Retorno retorno = CtrRegistrar.cadastrarUsuario();
                    Message msg = new Message();
                    msg.obj = retorno;
                    handler.sendMessage(msg);

                }).start();

            }
        });
    }

    private boolean existeCamposVazios() {
        String senha = edSenha.getText().toString();
        String repitaSenha = edRepitaSenha.getText().toString();

        if(Biblioteca.isStringVazia(senha) || Biblioteca.isStringVazia(repitaSenha)){
            Dialogo.informar(this,"Os campos de senha devem ser preenchidos!");
            return true;
        }

        if(!senha.contentEquals(repitaSenha)){
            Dialogo.informar(this,"As senhas digitadas não são iguais!");
            return true;
        }

        if(!Biblioteca.isSenhaValida(senha)){
            Dialogo.informar(this, "A senha deve conter ao menos, 1 letra máisucula, 1 letra mínuscula, 1 número e 1 caractér especial!");
            return true;
        }

        return false;
    }

    @Override
    public void onBackPressed() {
        ActInfoSenha.update = false;
        ActInfoSenha.esqueciSenha = false;
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        ActInfoSenha.update = false;
        ActInfoSenha.esqueciSenha = false;
    }

    public static void iniciarActivity(Context contexto) {
        iniciarActivity(contexto, false, false);
    }

    public static void iniciarActivity(Context contexto, boolean update, boolean esqueciSenha) {
        ActInfoSenha.update = update;
        ActInfoSenha.esqueciSenha = esqueciSenha;
        Intent intent = new Intent(contexto, ActInfoSenha.class);
        contexto.startActivity(intent);
    }

    @Override
    public void callbackDialogoProcessar(Retorno retorno, int dialogoId) {

        if(retorno.isHouveErro()){
            Dialogo.informar(ActInfoSenha.this, retorno.getMensagem());
            return;
        }

        ActLogin.iniciarActivity(ActInfoSenha.this);
        CtrRegistrar.limparTelas();

        Dialogo.toastInformar(ActInfoSenha.this, "Usuario cadastrado com sucesso!");

    }
}
