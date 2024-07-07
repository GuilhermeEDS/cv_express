package br.com.cv_express.services;

import br.com.cv_express.entities.Candidato;
import br.com.cv_express.entities.Submissao;
import br.com.cv_express.entities.Vaga;
import br.com.cv_express.repositories.SubmissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissaoService {

    @Autowired
    private SubmissaoRepository submissaoRepository;

    public List<Submissao> findAllByVaga(Long id) {
        return submissaoRepository.findAllByVagaId(id);
    }

    public Submissao create(Submissao submissao, Candidato candidato, Vaga vaga){
        submissao.setVaga(vaga);
        submissao.setCandidato(candidato);
        return submissaoRepository.save(submissao);
    }
}
