package br.com.cv_express.repositories;

import br.com.cv_express.entities.Candidato;
import br.com.cv_express.entities.Empresa;
import br.com.cv_express.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    @Query("SELECT c FROM Candidato c WHERE c.usuario.id = ?1")
    Optional<Candidato> findByUsuarioId(Long id);
}