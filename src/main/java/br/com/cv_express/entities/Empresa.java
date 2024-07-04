package br.com.cv_express.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Empresa extends Usuario{

    private String cnpj;

    @OneToMany
    private List<Vaga> vagas;

    public Empresa() {
        super();
    }

    public Empresa(String nome, String telefone, String email,String cnpj, String senha) {
        super(nome, telefone, email, senha);
        this.cnpj = cnpj;
    }
}
