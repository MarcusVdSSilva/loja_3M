package com.example.projetoa3.Controladores;

import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Objetos.Usuarios;
import com.example.projetoa3.Servicos.ServLogin;

public class CtrLogin {

    private static Usuarios usuario;

    public static void logarUsuario(String email, String senha){

        CtrLogin.usuario = ServLogin.logarUsuario(email,Biblioteca.codificarSenha(senha));

    }

    public static void logoutUsuario(){
        CtrLogin.usuario = null;
    }

    public static Usuarios getUsuario() {
        return usuario;

    }

    public static void updateUsuario(
        String nome,
        String cpfCnpj,
        String email,
        String telefone
    ) {
        usuario.setInfoBasicaUsuario(
            nome,
            cpfCnpj,
            email,
            telefone
        );
    }

    public static void updateFotoPerfil(String fotoPerfil){
        usuario.setFotoPerfil(fotoPerfil);
    }

    public static int getUsuarioIdEmail(String email) {
        return ServLogin.getUsuarioIdEmail(email);
    }
}
