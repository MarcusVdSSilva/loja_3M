package com.example.projetoa3.Controladores;

import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.Objetos.Usuarios;
import com.example.projetoa3.Servicos.ServLogin;

public class CtrRegistrar {

    public static void limparTelas() {
        CtrInfoUsuario.limparInfoUsuario();
        CtrInfoSenha.limparInfoSenha();
    }

    public static Retorno cadastrarUsuario() {

        Usuarios usuario = new Usuarios(
            CtrInfoUsuario.getTipoUsuario().getIndice(),
            CtrInfoUsuario.getNome(),
            CtrInfoUsuario.getEmail(),
            Biblioteca.numeros(CtrInfoUsuario.getCpfCnpj()),
            Biblioteca.numeros(CtrInfoUsuario.getTelefone())
        );

        return ServLogin.cadastrarUsuario(usuario, CtrInfoSenha.getSenha());

    }

    public static boolean existeCpfCadastrado(String cpf) {
        return ServLogin.existeCpfCadastrado(cpf);
    }
}
