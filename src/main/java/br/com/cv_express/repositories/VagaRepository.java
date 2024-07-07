package br.com.cv_express.repositories;

import br.com.cv_express.entities.Candidato;
import br.com.cv_express.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    @Query("SELECT v FROM Vaga v WHERE v.empresa.id = ?1")
    List<Vaga> findByEmpresaId(Long id);
}
