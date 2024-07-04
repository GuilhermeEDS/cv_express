package br.com.cv_express.enumerations;

import lombok.Getter;

public enum Role {
    USUARIO("Usuario"),
    EMPRESA("Empresa");

    @Getter
    private final String descricao;

    Role(String descricao) {
        this.descricao = descricao;
    }
}