package br.com.cv_express.services;

import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Vaga;
import br.com.cv_express.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {
    @Autowired
    private VagaRepository vagaRepository;

    public Vaga getVaga(Long id) {
        return vagaRepository.findById(id).isPresent() ? vagaRepository.findById(id).get() : null;
    }

    public List<Vaga> getAll(){
        return vagaRepository.findAll();
    }

    public List<Vaga> getAllByEmpresa(Long empresaId){
        return vagaRepository.findByEmpresaId(empresaId);
    }

    public Vaga create(Vaga vaga, Empresa empresa){
        vaga.setEmpresa(empresa);
        return vagaRepository.save(vaga);
    }
}
