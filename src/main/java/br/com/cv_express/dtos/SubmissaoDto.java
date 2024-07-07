package br.com.cv_express.dtos;

import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Submissao;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.entities.Vaga;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SubmissaoDto {

    private String Observacao;

    private Long candidatoId;

    private Long vagaId;

    public Submissao toSubmissao() {
        Submissao submissao = new Submissao();

        submissao.setObservacao(getObservacao());

        return submissao;
    }
}

