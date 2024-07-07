package br.com.cv_express.controllers;

import br.com.cv_express.dtos.EmpresaDto;
import br.com.cv_express.dtos.VagaDto;
import br.com.cv_express.entities.Candidato;
import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Submissao;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.services.*;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.util.List;

@Controller
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private VagaService vagaService;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SubmissaoService submissaoService;
    @Autowired
    private CandidatoService candidatoService;

    @GetMapping("/cadastro")
    public String paginaCadastro(Model model) {
        EmpresaDto empresaDto = new EmpresaDto();
        model.addAttribute("empresaDto", empresaDto);
        return "empresa/cadastroEmpresa";
    }

    @PostMapping("/cadastro")
    public RedirectView create(final @Valid EmpresaDto empresaDto, @NonNull BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("empresaDto", empresaDto);
            return new RedirectView("/empresa/cadastro", true);
        }

        empresaService.create(empresaDto.toEmpresa());
        return new RedirectView("/login", true);
    }

    @GetMapping("/vaga")
    public String paginaVaga(Model model) {
        VagaDto vagaDto = new VagaDto();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = usuarioService.getByEmail(email);
        Empresa empresa = empresaService.getByUsuarioId(usuario.getId());
        model.addAttribute("empresaId", empresa.getId());
        model.addAttribute("vagaDto", vagaDto);
        return "empresa/cadastroVaga";
    }

    @PostMapping("/vaga")
    public RedirectView createVaga(final @Valid VagaDto vagaDto, @NonNull BindingResult result, Model model) {
        if (result.hasErrors()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            Usuario usuario = usuarioService.getByEmail(email);
            Empresa empresa = empresaService.getByUsuarioId(usuario.getId());
            model.addAttribute("empresa", empresa);
            model.addAttribute("vagaDto", vagaDto);
            return new RedirectView("/empresa/vaga", true);
        }
        System.out.println(vagaDto.getEmpresaId());
        Empresa empresa = empresaService.getEmpresa(vagaDto.getEmpresaId());
        vagaService.create(vagaDto.toVaga(), empresa);
        return new RedirectView("/", true);
    }

    @GetMapping("/vaga/{id}")
    public String paginaHome(Model model, @PathVariable("id") Long vagaId) {

        List<Submissao> submissoess = submissaoService.findAllByVaga(vagaId);
        model.addAttribute("submissoes",submissoess);

        return "empresa/listaCandidatos";
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<FileSystemResource> downloadFile(@PathVariable Long id) {
        Candidato candidato = candidatoService.getCandidato(id);
        if (candidato.getCurriculo() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File file = new File(candidato.getCurriculo());
        if (!file.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        FileSystemResource resource = new FileSystemResource(file);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + file.getName());
        return ResponseEntity.ok()
                .headers(headers)
                .body(resource);
    }
}
