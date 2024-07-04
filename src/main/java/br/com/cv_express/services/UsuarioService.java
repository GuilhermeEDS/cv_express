package br.com.cv_express.services;

import br.com.cv_express.entities.Usuario;
import br.com.cv_express.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario getByEmail(String email) {
        return usuarioRepository.findByEmail(email).isPresent() ? usuarioRepository.findByEmail(email).get() : null;
    }
}
