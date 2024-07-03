package br.com.cv_express.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Submissao {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date dataCadastro;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne
    private Usuario candidato;

    @ManyToOne
    private Vaga vaga;

    public Submissao() {
        this.dataCadastro = new Date();
        this.ativo = true;
    }

    public Submissao(Usuario candidato, Vaga vaga) {
        this.candidato = candidato;
        this.vaga = vaga;
        this.dataCadastro = new Date();
        this.ativo = true;
    }
}
