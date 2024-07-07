package br.com.cv_express.arq;

import br.com.cv_express.entities.Usuario;
import br.com.cv_express.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class AuthProvider implements AuthenticationProvider {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String senha = authentication.getCredentials().toString();
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isEmpty() || !usuario.get().getSenha().equals(senha)) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(email, senha, new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}