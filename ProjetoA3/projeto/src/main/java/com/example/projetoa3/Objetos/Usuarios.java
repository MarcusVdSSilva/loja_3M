package com.example.projetoa3.Objetos;

import android.graphics.Bitmap;

import com.example.projetoa3.Biblioteca.Biblioteca;
import com.example.projetoa3.Enums.TiposUsuarios;
import com.google.gson.annotations.SerializedName;

public class Usuarios {

    @SerializedName("id")
    private int usuarioId;

    @SerializedName("perfil")
    private int perfilId;

    @SerializedName("nome")
    private String nome;

    @SerializedName("email")
    private String email;

    @SerializedName("cpf_cnpj")
    private String cpfCnpj;

    @SerializedName("telefone")
    private String telefone;

    @SerializedName("fotoPerfil")
    private String fotoPerfil;

    public Usuarios(
        int perfilId,
        String nome,
        String email,
        String cpfCnpj,
        String telefone
    ) {
        this.perfilId = perfilId;
        this.nome = nome;
        this.email = email;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public TiposUsuarios getPerfil() {
        return TiposUsuarios.getTipoPorIndice(perfilId);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    public Bitmap getFotoPerfil() {
        if(Biblioteca.isStringVazia(fotoPerfil))
            return null;

        return Biblioteca.comprimirImagem(Biblioteca.base64ParaBitmap(fotoPerfil), 64,64);
    }

    public void setInfoBasicaUsuario(
        String nome,
        String cpfCnpj,
        String email,
        String telefone
    ) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.telefone = telefone;
    }

    public void setFotoPerfil(String fotoPerfil){
        this.fotoPerfil = fotoPerfil;
    }

}
