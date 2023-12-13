package com.example.projetoa3.Controladores;

import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Enums.TiposUsuarios;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.Servicos.ServLogin;

public class CtrInfoUsuario {
    private static TiposUsuarios tipoUsuario = TiposUsuarios.CLIENTE;
    private static String nome;
    private static String cpfCnpj;
    private static String email;
    private static String telefone;

    //MÉTODOS INFO USUARIO
    public static void setInfoUsuario(String nome, String cpfCnpj, String email, String telefone){
        CtrInfoUsuario.nome = nome;
        CtrInfoUsuario.cpfCnpj = cpfCnpj;
        CtrInfoUsuario.email = email;
        CtrInfoUsuario.telefone = telefone;
    }

    public static void limparInfoUsuario(){
        CtrInfoUsuario.nome = "";
        CtrInfoUsuario.cpfCnpj = "";
        CtrInfoUsuario.email = "";
        CtrInfoUsuario.telefone = "";
    }

    public static boolean infoUsuPreenchido(){

        return !(Biblioteca.isStringVazia(nome) ||
                Biblioteca.isStringVazia(cpfCnpj) ||
                Biblioteca.isStringVazia(email) ||
                Biblioteca.isStringVazia(telefone)
        );
    }


    public static String getNome() {
        return nome;
    }

    public static String getCpfCnpj() {
        return cpfCnpj;
    }

    public static String getEmail() {
        return email;
    }

    public static String getTelefone() {
        return telefone;
    }

    public static TiposUsuarios getTipoUsuario() {
        return tipoUsuario;
    }

    public static Retorno salvarDadosEdicao() {
        return ServLogin.updateUsuario(
            nome,
            Biblioteca.numeros(cpfCnpj),
            email,
            telefone
        );
    }

    public static Retorno salvarDadosEdicaoFotoPerfil(String fotoPerfil) {
        return ServLogin.updateFotoPerfil(fotoPerfil);
    }
}
