package com.example.projetoa3.Activities.registrar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import com.example.projetoa3.Activities.ActApp;
import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Biblioteca.Dialogo;
import com.example.projetoa3.Controladores.CtrInfoUsuario;
import com.example.projetoa3.Controladores.CtrLogin;
import com.example.projetoa3.Controladores.CtrRegistrar;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.Objetos.Usuarios;
import com.example.projetoa3.R;

public class ActInfoUsuario extends ActApp {

    private EditText edNome;
    private EditText edCpfCnpj;
    private EditText edEmail;
    private EditText edTelefone;
    private Button btContinuar;

    private static boolean editar = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_cadastro_info_usu);

        configurarComponentes();
        preencherComponentes();
        configurarAcoes();
    }

    @Override
    public void configurarComponentes() {
        edNome = findViewById(R.id.act_cadastro_info_usu_ed_nome);
        edCpfCnpj = findViewById(R.id.act_cadastro_info_usu_ed_cpf_cnpj);
        edEmail = findViewById(R.id.act_cadastro_info_usu_ed_email);
        edTelefone  = findViewById(R.id.act_cadastro_info_usu_ed_telefone);
        btContinuar  = findViewById(R.id.act_cadastro_info_usu_bt_continuar);

        Biblioteca.formatarCampoTelefone(edTelefone);
        Biblioteca.formatarCampoCpfCnpj(edCpfCnpj);

        if(ActInfoUsuario.editar)
            btContinuar.setText("Salvar");

    }

    @Override
    public void preencherComponentes() {

        if(ActInfoUsuario.editar) {
            Usuarios usuario = CtrLogin.getUsuario();
            CtrInfoUsuario.setInfoUsuario(
                usuario.getNome(),
                usuario.getCpfCnpj(),
                usuario.getEmail(),
                usuario.getTelefone()
            );
        }

        limparCampos();

        if(CtrInfoUsuario.infoUsuPreenchido()){
            edNome.setText(CtrInfoUsuario.getNome());
            edCpfCnpj.setText(CtrInfoUsuario.getCpfCnpj());
            edEmail.setText(CtrInfoUsuario.getEmail());
            edTelefone.setText(CtrInfoUsuario.getTelefone());
        }
    }

    @Override
    public void configurarAcoes() {

        btContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(existemCamposVazios())
                    return;

                CtrInfoUsuario.setInfoUsuario(
                    edNome.getText().toString(),
                    edCpfCnpj.getText().toString(),
                    edEmail.getText().toString(),
                    edTelefone.getText().toString()
                );

                if(ActInfoUsuario.editar){
                    Retorno retorno = CtrInfoUsuario.salvarDadosEdicao();

                    if(retorno.isHouveErro()){
                        Dialogo.informar(ActInfoUsuario.this, retorno.getMensagem());
                        return;
                    }

                    CtrLogin.updateUsuario(
                            edNome.getText().toString(),
                            Biblioteca.numeros(edCpfCnpj.getText().toString()),
                            edEmail.getText().toString(),
                            edTelefone.getText().toString()
                    );

                    CtrInfoUsuario.limparInfoUsuario();
                    Dialogo.toastInformar(ActInfoUsuario.this,"Dados salvos com sucesso");
                    ActInfoUsuario.editar = false;
                    finish();
                    return;
                }

                ActInfoSenha.iniciarActivity(ActInfoUsuario.this);

            }
        });
    }

    private void limparCampos() {
        edNome.setText("");
        edCpfCnpj.setText("");
        edEmail.setText("");
        edTelefone.setText("");
    }

    @Override
    public void onBackPressed() {
        ActInfoUsuario.editar = false;
        CtrRegistrar.limparTelas();
        finish();
    }

    private boolean existemCamposVazios() {

        if(!Biblioteca.validarNome(edNome.getText().toString())){
            Dialogo.informar(this,"Campo nome inválido","O nome precisa ter ao menos três caracteres!");
            return true;
        }
        else if(!Biblioteca.validarCPFCnpj(edCpfCnpj.getText().toString())){
            Dialogo.informar(this,"CPF ou CNPJ inválido","Digite um CPF ou CNPJ válido!");
            return true;
        }
        else if(!Biblioteca.isTelefoneValido(edTelefone.getText().toString())){
            Dialogo.informar(this,"Telefone inválido","Digite um telefone válido!");
            return true;
        }
        else if(!Biblioteca.isEmailValido(edEmail.getText().toString())){
            Dialogo.informar(this,"Email inválido","Digite um email válido!");
            return true;
        }
        else if(CtrRegistrar.existeCpfCadastrado(Biblioteca.numeros(edCpfCnpj.getText().toString())) && !ActInfoUsuario.editar){
            Dialogo.informar(this,"Cpf já cadastrado no sistema!");
            return true;
        }

        return false;
    }
    public static void iniciarActivity(Context contexto) {
        iniciarActivity(contexto, false);
    }

    public static void iniciarActivity(Context contexto, boolean editar){
        CtrInfoUsuario.limparInfoUsuario();
        Intent intent = new Intent(contexto, ActInfoUsuario.class);
        contexto.startActivity(intent);
        ActInfoUsuario.editar = editar;
    }

}
