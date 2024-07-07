package br.com.cv_express.enumerations;

import lombok.Getter;

@Getter
public enum Role {
    CANDIDATO("Candidato"),
    EMPRESA("Empresa");

    private final String descricao;

    Role(String descricao) {
        this.descricao = descricao;
    }
}