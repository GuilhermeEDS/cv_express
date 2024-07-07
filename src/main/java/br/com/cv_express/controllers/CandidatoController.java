package br.com.cv_express.controllers;

import br.com.cv_express.dtos.CandidatoDto;
import br.com.cv_express.dtos.SubmissaoDto;
import br.com.cv_express.dtos.VagaDto;
import br.com.cv_express.entities.Candidato;
import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.entities.Vaga;
import br.com.cv_express.enumerations.Escolaridade;
import br.com.cv_express.services.CandidatoService;
import br.com.cv_express.services.SubmissaoService;
import br.com.cv_express.services.UsuarioService;
import br.com.cv_express.services.VagaService;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.io.IOException;

@Controller
@RequestMapping("/candidato")
public class CandidatoController {

    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VagaService vagaService;
    @Autowired
    private SubmissaoService submissaoService;

    @GetMapping("/cadastro")
    public String paginaCadastro(Model model) {
        CandidatoDto candidatoDto = new CandidatoDto();
        model.addAttribute("candidatoDto", candidatoDto);
        model.addAttribute("escolaridades", Escolaridade.values());
        return "candidato/cadastroCandidato";
    }

    @PostMapping("/cadastro")
    public RedirectView create(final @Valid CandidatoDto candidatoDto, @NonNull BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            model.addAttribute("usuarioDto", candidatoDto);
            model.addAttribute("escolaridade", Escolaridade.values());
            return new RedirectView("/candidato/cadastro", true);
        }

        candidatoService.create(candidatoDto.toCandidato(), candidatoDto.getCurriculo());
        return new RedirectView("/login", true);
    }

    @GetMapping("/submissao/{id}")
    public String paginaSubmissao(Model model, @PathVariable("id") Long vagaId) {
        SubmissaoDto submissaoDto = new SubmissaoDto();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = usuarioService.getByEmail(email);
        Candidato candidato = candidatoService.getByUsuarioId(usuario.getId());
        Vaga vaga = vagaService.getVaga(vagaId);
        model.addAttribute("candidatoId", candidato.getId());
        model.addAttribute("vaga", vaga);
        model.addAttribute("submissaoDto", submissaoDto);
        return "candidato/submissaoVaga";
    }

    @PostMapping("/submissao/")
    public RedirectView createSubmissao(final @Valid SubmissaoDto submissaoDto, @NonNull BindingResult result, Model model) throws IOException {
        if (result.hasErrors()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String email = auth.getName();
            Usuario usuario = usuarioService.getByEmail(email);
            Candidato candidato = candidatoService.getByUsuarioId(usuario.getId());
            Vaga vaga = vagaService.getVaga(submissaoDto.getVagaId());
            model.addAttribute("candidatoId", candidato.getId());
            model.addAttribute("vaga", vaga);
            model.addAttribute("submissaoDto", submissaoDto);
            return new RedirectView("/candidato/submissaoVaga", true);
        }

        Vaga vaga = vagaService.getVaga(submissaoDto.getVagaId());
        Candidato candidato = candidatoService.getCandidato(submissaoDto.getCandidatoId());
        submissaoService.create(submissaoDto.toSubmissao(), candidato, vaga);
        return new RedirectView("/", true);
    }


}
