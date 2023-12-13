package com.example.projetoa3.Interfaces;

import com.example.projetoa3.Objetos.Retorno;

import java.util.Calendar;

public interface CbDialogos {
    void callbackDialogoInformar(int dialogoId);
    void callbackDialogoProcessar(Retorno retorno, int dialogoId);
    void callbackColetaData(int dialogoId, String dataSelecionada);
    void callbackColetaDataRetorno(int dialogoId, Calendar calendario);
}
