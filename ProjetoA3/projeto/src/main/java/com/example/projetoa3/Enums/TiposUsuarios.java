package com.example.projetoa3.Enums;

public enum TiposUsuarios {
    CLIENTE (0,"Cliente"),
    VENDEDOR (1, "Vendedor"),
    ADMIN (2, "Admin");

    private int indice;
    private String descricao;

    TiposUsuarios(int indice, String descricao){
        this.indice = indice;
        this.descricao = descricao;
    }

    public int getIndice() {
        return indice;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TiposUsuarios getTipoPorIndice(int indice){
        TiposUsuarios [] tiposUsuarios = TiposUsuarios.values();

        for(TiposUsuarios usuario : tiposUsuarios){
            if(usuario.getIndice() == indice)
                return usuario;
        }

        return null;
    }
}
