package br.com.cv_express.entities;

import br.com.cv_express.enumerations.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date dataCadastro;

    @Column(nullable = false)
    private boolean ativo;

    private String nome;

    private String email;

    private String telefone;

    private String senha;

    private Role papel;

    public Usuario() {
        this.dataCadastro = new Date();
        this.ativo = true;
    }

    public Usuario(String nome, String email, String telefone, String senha) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.senha = senha;
        this.dataCadastro = new Date();
        this.ativo = true;
    }
}