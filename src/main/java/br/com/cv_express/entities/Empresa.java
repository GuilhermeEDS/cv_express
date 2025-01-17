package br.com.cv_express.entities;

import br.com.cv_express.enumerations.Role;
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

    @Column(nullable = false)
    private String cnpj;

    @OneToOne
    private Usuario usuario;

    public Empresa() {
        this.dataCadastro = new Date();
        this.ativo = true;
    }

    public Empresa(String nome, String email,String cnpj, String senha) {
        this.usuario = new Usuario(nome, email, senha, Role.EMPRESA);
        this.cnpj = cnpj;
        this.dataCadastro = new Date();
        this.ativo = true;
    }
}
