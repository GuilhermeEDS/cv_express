package br.com.cv_express.dtos;

import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.enumerations.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmpresaDto {

    @NotEmpty(message = "Campo não pode ser vazio")
    private String nome;

    @NotEmpty(message = "Campo não pode ser vazio")
    @Email(message = "Informe um e-mail válido")
    private String email;

    @NotEmpty(message = "Campo não pode ser vazio")
    private String senha;

    @NotEmpty(message = "Campo não pode ser vazio")
    private String cnpj;

    public Empresa toEmpresa() {
        Empresa empresa = new Empresa();

        empresa.setUsuario(new Usuario(getNome(), getEmail(), getSenha(), Role.EMPRESA));
        empresa.setCnpj(getCnpj());

        return empresa;
    }
}

