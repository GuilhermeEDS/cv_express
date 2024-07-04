package br.com.cv_express.dtos;

import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioDto {

    @NotEmpty(message = "Campo não pode ser vazio")
    private String nome;

    @NotEmpty(message = "Campo não pode ser vazio")
    private String telefone;

    @NotEmpty(message = "Campo não pode ser vazio")
    @Email(message = "Informe um e-mail válido")
    private String email;

    @NotEmpty(message = "Campo não pode ser vazio")
    private String senha;

    private String cnpj;

    public Usuario toUsuario() {
        Usuario usuario = new Usuario();

        usuario.setNome(getNome());
        usuario.setTelefone(getTelefone());
        usuario.setEmail(getEmail());
        usuario.setSenha(senha);

        return usuario;
    }

    public Empresa toEmpresa() {
        Empresa empresa = new Empresa();

        empresa.setNome(getNome());
        empresa.setTelefone(getTelefone());
        empresa.setEmail(getEmail());
        empresa.setSenha(getSenha());
        empresa.setCnpj(getCnpj());

        return empresa;
    }
}

