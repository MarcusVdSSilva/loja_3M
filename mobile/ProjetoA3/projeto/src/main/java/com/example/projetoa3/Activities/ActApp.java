package com.example.projetoa3.Activities;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projetoa3.Biblioteca.Dialogo;
import com.example.projetoa3.Controladores.CtrGeral;
import com.example.projetoa3.Interfaces.CbDialogos;
import com.example.projetoa3.Interfaces.CbInternet;
import com.example.projetoa3.Objetos.Retorno;

import java.util.Calendar;

public abstract class ActApp extends AppCompatActivity implements CbInternet, CbDialogos {

    private static final int PING_GERAL = 1;
    private static final int TENT_CONEX = 2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(!CtrGeral.isActLogin() && !CtrGeral.ping())
            Dialogo.informar(this,"Sem conexão com a internet", PING_GERAL);

    }

    @Override
    public void internetException() {
        Dialogo.informar(this,"Sem conexão com a internet", TENT_CONEX);
    }

    @Override
    public void callbackDialogoInformar(int dialogoId) {
        switch (dialogoId) {
            case PING_GERAL:
                finish();
                ActLogin.iniciarActivity(this);
                break;
            case TENT_CONEX:
                break;
        }

    }

    @Override
    public void callbackDialogoProcessar(Retorno retorno, int dialogoId) {}

    @Override
    public void callbackColetaData(int dialogoId, String data) {}

    @Override
    public void callbackColetaDataRetorno(int dialogoId, Calendar data) {}

    public abstract void configurarComponentes();

    public abstract void preencherComponentes();

    public abstract void configurarAcoes();

}
