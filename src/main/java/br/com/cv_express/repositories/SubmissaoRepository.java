package br.com.cv_express.repositories;

import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Submissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissaoRepository extends JpaRepository<Submissao, Long> {
    @Query("SELECT s FROM Submissao s WHERE s.vaga.id = ?1")
    List<Submissao> findAllByVagaId(Long id);
}
