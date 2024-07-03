package br.com.cv_express.repositories;

import br.com.cv_express.entities.Submissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissaoRepository extends JpaRepository<Submissao, Long> {
}
