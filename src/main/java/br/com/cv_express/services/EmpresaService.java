package br.com.cv_express.services;

import br.com.cv_express.entities.Empresa;
import br.com.cv_express.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa create(Empresa empresa) {
        return empresaRepository.save(empresa);
    }
}
