package br.com.cv_express.controllers;

import br.com.cv_express.dtos.UsuarioDto;
import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @GetMapping("/all")
    public ResponseEntity<List<Empresa>> getAll() {
        return ResponseEntity.ok(empresaService.findAll());
    }

    @GetMapping("/")
    public String paginaCadastro(Model model) {
        UsuarioDto usuarioDto = new UsuarioDto();
        model.addAttribute("usuarioDto", usuarioDto);
        return "cadastroEmpresa";
    }

    @PostMapping("/")
    public ResponseEntity<Empresa> create(UsuarioDto usuarioDto) {
        return ResponseEntity.ok(empresaService.create(usuarioDto.toEmpresa()));
    }

}
