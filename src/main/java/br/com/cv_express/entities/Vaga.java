package br.com.cv_express.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date dataCadastro;

    @Column(nullable = false)
    private boolean ativo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Empresa empresa;

    private String nome;

    private String descricao;

    public Vaga() {
        this.dataCadastro = new Date();
        this.ativo = true;
    }

    public Vaga(String nome, String descricao, Empresa empresa) {
        this.nome = nome;
        this.descricao = descricao;
        this.empresa = empresa;
        this.dataCadastro = new Date();
        this.ativo = true;
    }
}
