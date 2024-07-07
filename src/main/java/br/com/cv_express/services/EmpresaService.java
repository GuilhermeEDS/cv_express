package br.com.cv_express.services;

import br.com.cv_express.entities.Candidato;
import br.com.cv_express.entities.Empresa;
import br.com.cv_express.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private UsuarioService usuarioService;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa create(Empresa empresa) {
        usuarioService.create(empresa.getUsuario());
        return empresaRepository.save(empresa);
    }

    public Empresa getByUsuarioId(Long id) {
        return empresaRepository.findByUsuarioId(id).isPresent() ? empresaRepository.findByUsuarioId(id).get() : null;
    }

    public Empresa getEmpresa(Long id) {
        return empresaRepository.findById(id).isPresent() ? empresaRepository.findById(id).get() : null;
    }
}
