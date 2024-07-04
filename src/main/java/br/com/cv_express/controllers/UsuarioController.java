package br.com.cv_express.controllers;

import br.com.cv_express.dtos.UsuarioDto;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAllUser() {
        return ResponseEntity.ok(usuarioService.getAll());
    }

    @GetMapping("/")
    public String paginaCadastro(Model model) {
        UsuarioDto usuarioDto = new UsuarioDto();
        model.addAttribute("usuarioDto", usuarioDto);
        return "cadastroUsuario";
    }

    @PostMapping("/")
    public ResponseEntity<Usuario> createUser(UsuarioDto usuarioDto) {
        return ResponseEntity.ok(usuarioService.create(usuarioDto.toUsuario()));
    }

}
