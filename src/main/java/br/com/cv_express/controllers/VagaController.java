package br.com.cv_express.controllers;

import br.com.cv_express.dtos.UsuarioDto;
import br.com.cv_express.dtos.VagaDto;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.entities.Vaga;
import br.com.cv_express.services.UsuarioService;
import br.com.cv_express.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/vaga")
public class VagaController {
    @Autowired
    VagaService vagaService;

    @GetMapping("/all")
    public ResponseEntity<List<Vaga>> getAll() {
        return ResponseEntity.ok(vagaService.getAll());
    }

    @GetMapping("/")
    public String paginaCadastro(Model model) {
        VagaDto vagaDto = new VagaDto();
        model.addAttribute("vagaDto", vagaDto);
        return "cadastroVaga";
    }

    @PostMapping("/")
    public ResponseEntity<Vaga> create(VagaDto vagaDto) {
        return ResponseEntity.ok(vagaService.create(vagaDto.toVaga()));
    }
}
