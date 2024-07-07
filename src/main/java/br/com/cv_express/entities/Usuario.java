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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Role papel;

    public Usuario() {
        this.dataCadastro = new Date();
        this.ativo = true;
    }

    public Usuario(String nome, String email, String senha, Role papel) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = new Date();
        this.ativo = true;
        this.papel = papel;
    }
}