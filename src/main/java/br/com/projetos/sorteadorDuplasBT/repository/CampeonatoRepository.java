package br.com.projetos.sorteadorDuplasBT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetos.sorteadorDuplasBT.model.Campeonato;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long> {
    
    Optional<Campeonato> findByAtivoTrue();

}
