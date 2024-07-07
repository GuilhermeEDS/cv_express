package br.com.cv_express.entities;

import br.com.cv_express.enumerations.Escolaridade;
import br.com.cv_express.enumerations.Role;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Candidato{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Date dataCadastro;

    @Column(nullable = false)
    private boolean ativo;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private Escolaridade escolaridade;

    private String curriculo;

    @OneToOne
    private Usuario usuario;

    public Candidato() {
        this.dataCadastro = new Date();
        this.ativo = true;
    }

    public Candidato(String nome, String telefone, String email, String senha, String curriculo, Escolaridade escolaridade) {
        this.usuario = new Usuario(nome, email, senha, Role.CANDIDATO);
        this.telefone = telefone;
        this.escolaridade = escolaridade;
        this.curriculo = curriculo;
        this.dataCadastro = new Date();
        this.ativo = true;
    }
}
