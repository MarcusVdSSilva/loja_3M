package com.example.projetoa3.Controladores;

import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Servicos.ServLogin;

public class CtrInfoSenha {

    private static String senha;
    private static int usuarioId;

    public static void setSenha(String senha) {
        CtrInfoSenha.senha = Biblioteca.codificarSenha(senha);
    }

    public static void setUsuarioId(int usuarioId){
        CtrInfoSenha.usuarioId = usuarioId;
    }

    public static void limparInfoSenha() {
        CtrInfoSenha.senha = null;
    }

    public static String getSenha() {
        return senha;
    }

    public static void updateSenha() {
        ServLogin.updateSenha(senha, usuarioId);
    }
}
