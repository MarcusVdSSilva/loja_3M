package com.example.projetoa3.Servicos;

import com.example.projetoa3.Biblioteca.Ambiente;
import com.example.projetoa3.Biblioteca.Json;
import com.example.projetoa3.Biblioteca.Requisicao;
import com.example.projetoa3.Controladores.CtrLogin;
import com.example.projetoa3.Enums.MetodoRequisicao;
import com.example.projetoa3.Enums.TiposConteudosRequisicoes;
import com.example.projetoa3.Objetos.Credenciais;
import com.example.projetoa3.Objetos.Retorno;
import com.example.projetoa3.Objetos.Usuarios;

import java.util.HashMap;


public class ServLogin {
    private static final String CADASTRAR_USUARIO = "usuario/cadastrar.php";
    private static final String EDITAR_USUARIO = "usuario/editar.php";
    private static final String LOGAR_USUARIO = "usuario/logar.php";

    public static Retorno cadastrarUsuario(Usuarios usuario, String senha){

        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor()+CADASTRAR_USUARIO,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        requisicao.addParametros("cadastrarCliente", Json.toJson(usuario));
        requisicao.addParametros("senha", senha);

        Retorno retorno = requisicao.executarRequisicao();

        if(retorno.isHouveErro())
            return retorno;

        return Json.toRetorno(retorno.getDados());
    }

    public static boolean existeCpfCadastrado(String cpf) {
        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor()+CADASTRAR_USUARIO,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        requisicao.addParametros("verificarCpf", cpf);

        Retorno retorno = requisicao.executarRequisicao();

        return Json.toBoolean(retorno.getDados());
    }

    public static Usuarios logarUsuario(String email, String senha) {
        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor()+LOGAR_USUARIO,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        Credenciais credenciais = new Credenciais();
        credenciais.email = email;
        credenciais.senha = senha;

        requisicao.addParametros("credenciais",Json.toJson(credenciais));

        Retorno retorno = requisicao.executarRequisicao();

        return Json.toObject(retorno.getDados(), Usuarios.class);

    }

    public static Retorno updateUsuario(
        String nome,
        String cpfCnpj,
        String email,
        String telefone
    ) {
        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor()+EDITAR_USUARIO,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        requisicao.addParametros("usuarioId", CtrLogin.getUsuario().getUsuarioId());
        requisicao.addParametros("nome", nome);
        requisicao.addParametros("cpfCnpj", cpfCnpj);
        requisicao.addParametros("email", email);
        requisicao.addParametros("telefone", telefone);

        Retorno retorno = requisicao.executarRequisicao();

        if(retorno.isHouveErro())
            return retorno;

        return Json.toRetorno(retorno.getDados());
    }


    public static Retorno updateSenha(String senha, int usuarioId) {

        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor()+CADASTRAR_USUARIO,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        requisicao.addParametros("usuarioId", usuarioId);
        requisicao.addParametros("senha", senha);

        Retorno retorno = requisicao.executarRequisicao();

        if(retorno.isHouveErro())
            return retorno;

        return Json.toRetorno(retorno.getDados());
    }

    public static Retorno updateFotoPerfil(String fotoPerfil) {

        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor()+CADASTRAR_USUARIO,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        requisicao.addParametros("usuarioId", CtrLogin.getUsuario().getUsuarioId());
        requisicao.addParametros("fotoPerfil", fotoPerfil, true);

        Retorno retorno = requisicao.executarRequisicao();

        if(retorno.isHouveErro())
            return retorno;

        return Json.toRetorno(retorno.getDados());
    }

    public static int getUsuarioIdEmail(String email) {
        Requisicao requisicao = new Requisicao(
                Ambiente.getEnderecoServidor()+CADASTRAR_USUARIO,
                MetodoRequisicao.POST,
                TiposConteudosRequisicoes.URLENCODED
        );

        requisicao.addParametros("email", email);
        Retorno retorno = requisicao.executarRequisicao();

        if(retorno.isHouveErro())
            return 0;

        return Json.toInteger(retorno.getDados());
    }
}
