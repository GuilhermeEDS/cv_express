package br.com.cv_express.services;

import br.com.cv_express.repositories.SubmissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubmissaoService {

    @Autowired
    private SubmissaoRepository submissaoRepository;
}
