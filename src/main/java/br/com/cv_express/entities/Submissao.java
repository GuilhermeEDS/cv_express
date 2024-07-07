package br.com.cv_express.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Submissao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date dataCadastro;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Candidato candidato;

    @ManyToOne(fetch = FetchType.LAZY)
    private Vaga vaga;

    private String observacao;

    public Submissao() {
        this.dataCadastro = new Date();
        this.ativo = true;
    }

    public Submissao(Candidato candidato, Vaga vaga, String observacao) {
        this.dataCadastro = new Date();
        this.ativo = true;
        this.candidato = candidato;
        this.vaga = vaga;
        this.observacao = observacao;
    }
}
