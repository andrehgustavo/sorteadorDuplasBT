package br.com.projetos.sorteadorDuplasBT.repository;

import br.com.projetos.sorteadorDuplasBT.model.Campeonato;
import br.com.projetos.sorteadorDuplasBT.model.Dupla;
import br.com.projetos.sorteadorDuplasBT.model.Inscricao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuplaRepository extends JpaRepository<Dupla, Long> {
    List<Dupla> findByCampeonatoId(Long campeonatoId);

    void deleteByCampeonatoId(Long campeonatoId);

    void deleteByCampeonato(Campeonato campeonato);
    
}