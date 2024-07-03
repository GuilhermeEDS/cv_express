package br.com.cv_express.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date dataCadastro;

    @Column(nullable = false)
    private boolean ativo;

    private String nome;

    private String cnpj;

    @OneToMany
    private List<Vaga> vagas;

    public Empresa() {
        this.dataCadastro = new Date();
        this.ativo = true;
    }

    public Empresa(boolean ativo, String nome, String cnpj) {
        this.dataCadastro = new Date();
        this.ativo = true;
        this.ativo = ativo;
        this.nome = nome;
        this.cnpj = cnpj;
    }
}
