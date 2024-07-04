package br.com.cv_express.dtos;

import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.entities.Vaga;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class VagaDto {

    @NotEmpty(message = "Campo não pode ser vazio")
    private String nome;

    @NotEmpty(message = "Campo não pode ser vazio")
    private String descricao;

    public Vaga toVaga() {
        Vaga vaga = new Vaga();

        vaga.setNome(getNome());
        vaga.setDescricao(getDescricao());

        return vaga;
    }
}

