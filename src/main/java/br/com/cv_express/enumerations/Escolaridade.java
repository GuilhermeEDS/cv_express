package br.com.cv_express.enumerations;

import lombok.Getter;

@Getter
public enum Escolaridade {
    FUNDAMENTAL_INCOMPLETO("Fundamental Incompleto"),
    FUNDAMENTAL_COMPLETO("Fundamental Completo"),
    MEDIO_INCOMPLETO("Médio Incompleto"),
    MEDIO_COMPLETO("Médio Completo"),
    TECNICO_INCOMPLETO("Técnico Incompleto"),
    TECNICO_COMPLETO("Técnico Completo"),
    SUPERIOR_INCOMPLETO("Superior Incompleto"),
    SUPERIOR_COMPLETO("Superior Completo"),
    POS_GRADUACAO_INCOMPLETO("Pós-Graduação Incompleto"),
    POS_GRADUACAO_COMPLETO("Pós-Graduação Completo"),
    MESTRADO_INCOMPLETO("Mestrado Incompleto"),
    MESTRADO_COMPLETO("Mestrado Completo"),
    DOUTORADO_INCOMPLETO("Doutorado Incompleto"),
    DOUTORADO_COMPLETO("Doutorado Completo"),
    POS_DOUTORADO_INCOMPLETO("Pós-Doutorado Incompleto"),
    POS_DOUTORADO_COMPLETO("Pós-Doutorado Completo");

    private final String descricao;

    Escolaridade(String descricao) {
        this.descricao = descricao;
    }
}
