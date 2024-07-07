package br.com.cv_express.dtos;

import br.com.cv_express.entities.Candidato;
import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.enumerations.Escolaridade;
import br.com.cv_express.enumerations.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CandidatoDto {

    @NotEmpty(message = "Campo não pode ser vazio")
    private String nome;

    @NotEmpty(message = "Campo não pode ser vazio")
    private String telefone;

    @NotEmpty(message = "Campo não pode ser vazio")
    @Email(message = "Informe um e-mail válido")
    private String email;

    @NotEmpty(message = "Campo não pode ser vazio")
    private String senha;

    @Min(value = 0, message = "Selecione sua escolaridade.")
    private Integer escolaridade;

    private MultipartFile curriculo;

    public Candidato toCandidato() {
        Candidato candidato = new Candidato();

        candidato.setUsuario(new Usuario(getNome(), getEmail(), getSenha(), Role.CANDIDATO));
        candidato.setTelefone(getTelefone());
        candidato.setEscolaridade(Escolaridade.values()[escolaridade]);

        return candidato;
    }
}

