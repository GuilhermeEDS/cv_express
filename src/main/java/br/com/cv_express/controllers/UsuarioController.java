package br.com.cv_express.controllers;
import br.com.cv_express.entities.Candidato;
import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.services.CandidatoService;
import br.com.cv_express.services.EmpresaService;
import br.com.cv_express.services.UsuarioService;
import br.com.cv_express.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private VagaService vagaService;
    @Autowired
    private CandidatoService candidatoService;
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/login")
    public String paginaLogin(Model model, @RequestParam(required = false) String error) {
        model.addAttribute("error", error != null);
        return "login";
    }

    @GetMapping("/login-success")
    public RedirectView loginSucesso() {
        return new RedirectView("/",true);
    }

    @GetMapping("/")
    public String paginaHome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        Usuario usuario = usuarioService.getByEmail(email);

        switch (usuario.getPapel()) {
            case CANDIDATO -> {
                Candidato candidato = candidatoService.getByUsuarioId(usuario.getId());
                model.addAttribute("vagas", vagaService.getAll());
                model.addAttribute("candidato", candidato);
                return "candidato/index";
            }
            case EMPRESA -> {
                Empresa empresa = empresaService.getByUsuarioId(usuario.getId());
                model.addAttribute("empresa", empresa);
                model.addAttribute("vagas", vagaService.getAllByEmpresa(empresa.getId()));
                return "empresa/index";
            }
        }
        return "index";
    }

}
