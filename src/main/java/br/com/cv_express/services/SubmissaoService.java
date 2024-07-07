package br.com.cv_express.services;

import br.com.cv_express.arq.EmailSender;
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
    private EmailSender emailSender;
    @Autowired
    private SubmissaoRepository submissaoRepository;

    public List<Submissao> findAllByVaga(Long id) {
        return submissaoRepository.findAllByVagaId(id);
    }

    public Submissao create(Submissao submissao, Candidato candidato, Vaga vaga){
        submissao.setVaga(vaga);
        submissao.setCandidato(candidato);
        String curriculo = "Nome: "+ submissao.getCandidato().getUsuario().getNome() + "<br>" +
                "E-mail: "+ submissao.getCandidato().getUsuario().getEmail() + "<br>" +
                "Telefone: "+ submissao.getCandidato().getTelefone() + "<br>" +
                "Cargo Desejado: "+ submissao.getVaga().getNome() + "<br>" +
                "Escolaridade: "+ submissao.getCandidato().getEscolaridade().getDescricao() + "<br>" +
                "Observações: "+ submissao.getObservacao() + "<br>";
        emailSender.enviarEmail(submissao.getCandidato().getUsuario().getEmail(), "Submissão na vaga: "+ vaga.getNome(), curriculo, candidato.getCurriculo());
        emailSender.enviarEmail(submissao.getVaga().getEmpresa().getUsuario().getEmail(), "Novo candidato na vaga: "+ vaga.getNome(), curriculo, candidato.getCurriculo());
        return submissaoRepository.save(submissao);
    }
}
