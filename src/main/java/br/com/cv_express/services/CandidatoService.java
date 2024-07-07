package br.com.cv_express.services;

import br.com.cv_express.entities.Candidato;
import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Usuario;
import br.com.cv_express.repositories.CandidatoRepository;
import br.com.cv_express.repositories.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CandidatoService {

    @Autowired
    private CandidatoRepository candidatoRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired //pede pra injetar
    private HttpServletRequest request;

    public Candidato getCandidato( Long id){
        return candidatoRepository.findById(id).isPresent() ? candidatoRepository.findById(id).get() : null;
    }

    public List<Candidato> getAll() {
        return candidatoRepository.findAll();
    }

    public Candidato create(Candidato candidato, MultipartFile curriculo) throws IOException {

        usuarioService.create(candidato.getUsuario());

        if (!curriculo.isEmpty()) {
            File dir = new File(request.getServletContext().getRealPath("/uploads/"));
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new IOException("Não foi possível criar o diretório de uploads.");
                }
            }
            File uploadFile = new File(request.getServletContext().getRealPath("/uploads/") + curriculo.getOriginalFilename());
            curriculo.transferTo(uploadFile);
            candidato.setCurriculo(uploadFile.getAbsolutePath());
        }

        return candidatoRepository.save(candidato);
    }

    public Candidato getByUsuarioId(Long id) {
        return candidatoRepository.findByUsuarioId(id).isPresent() ? candidatoRepository.findByUsuarioId(id).get() : null;
    }
}
