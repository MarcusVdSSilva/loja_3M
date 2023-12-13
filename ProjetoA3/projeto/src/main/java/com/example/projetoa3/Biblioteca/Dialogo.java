package com.example.projetoa3.Biblioteca;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.projetoa3.Interfaces.CbDialogos;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.R;

import java.sql.Timestamp;
import java.util.Calendar;

public class Dialogo {

    public static void informar(Context context, String mensagem){
        informar(context, "Atenção", mensagem, 0);
    }

    public static void informar(Context context, String mensagem, int dialogoId){
        informar(context, "Atenção", mensagem, dialogoId);
    }

    public static void informar(Context context, String titulo, String mensagem){
        informar(context, titulo, mensagem, 0);
    }

    public static void informar(Context context, String titulo, String mensagem, int dialogoId){
       AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle(titulo)
            .setMessage( mensagem )
            .setCancelable(false)
            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    CbDialogos callbackDialogo = (CbDialogos) context;
                    callbackDialogo.callbackDialogoInformar(dialogoId);
                }
            })
            .show();

    }

    public static void toastInformar(Context context, String mensagem){
        Toast.makeText(context, mensagem, Toast.LENGTH_SHORT).show();
    }


    public static Handler exibirProcessando(Context context, String titulo, String mensagemProcessando, int dialogoId) {
        ProgressDialog dialogoProcessando = new ProgressDialog(context);
        dialogoProcessando.setTitle(titulo);
        dialogoProcessando.setMessage(mensagemProcessando);
        dialogoProcessando.show();

        Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if ((dialogoProcessando.isShowing() && dialogoProcessando != null)) {
                    dialogoProcessando.dismiss();
                    CbDialogos callback = (CbDialogos) context;
                    callback.callbackDialogoProcessar((Retorno) msg.obj, dialogoId);
                }
            }
        };
        return handler;
    }

    public static void coletaDataHora(Context context, String titulo, Timestamp valor, int codigoRequisicao, DialogInterface.OnClickListener negativo) {

        Calendar horaAtual = Calendar.getInstance();
        horaAtual.setTime(valor);
        CbDialogos callback = (CbDialogos) context;
        int hora = horaAtual.get(horaAtual.HOUR_OF_DAY);
        int minuto = horaAtual.get(horaAtual.MINUTE);
        TimePickerDialog dialog;
        dialog = new TimePickerDialog(context, R.style.TimePickerDialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int horaSelecinada, int minutoSelecionado) {
                callback.callbackColetaData(codigoRequisicao, horaSelecinada + ":" + minutoSelecionado);
            }
        }, hora, minuto, true);

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                callback.callbackColetaData(1,null);
            }
        });

        dialog.setTitle(titulo);
        dialog.show();
    }

    public static void coletaData(Context context, String titulo, int codigoRequisicao, boolean bloqueiaDatasAnteriores, Long dataMinima, DialogInterface.OnCancelListener negativo) {
        coletaData(context, titulo, codigoRequisicao, bloqueiaDatasAnteriores, dataMinima, false, null, negativo);
    }

    public static void coletaData(
            Context context,
            String titulo,
            int codigoRequisicao,
            boolean bloqueiaDatasAnteriores,
            Long dataMinima,
            boolean bloquearDataPosteriores,
            Long dataMaxima,
            DialogInterface.OnCancelListener negativo
    ) {
        Calendar calendario = Calendar.getInstance();
        DatePickerDialog dialog;

        CbDialogos callback = (CbDialogos) context;
        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendario.set(year,monthOfYear,dayOfMonth);
                callback.callbackColetaDataRetorno(codigoRequisicao, calendario);
            }

        };

        dialog = new DatePickerDialog(context, R.style.TimePickerDialog, date , ano, mes, dia);
        dialog.setTitle(titulo);
        if(bloqueiaDatasAnteriores) {
            if(dataMinima != null) {
                dialog.getDatePicker().setMinDate(dataMinima);
            }else{
                dialog.getDatePicker().setMinDate(calendario.getTimeInMillis());
            }
        }

        if(bloquearDataPosteriores){
            if(dataMaxima != null)
                dialog.getDatePicker().setMaxDate(dataMaxima);
            else
                dialog.getDatePicker().setMaxDate(calendario.getTimeInMillis());
        }

        dialog.setOnCancelListener(negativo);
        dialog.show();
    }

}
