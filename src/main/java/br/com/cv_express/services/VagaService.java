package br.com.cv_express.services;

import br.com.cv_express.entities.Vaga;
import br.com.cv_express.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {
    @Autowired
    private VagaRepository vagaRepository;

    public List<Vaga> getAll(){
        return vagaRepository.findAll();
    }

    public Vaga create(Vaga vaga){
        return vagaRepository.save(vaga);
    }
}
