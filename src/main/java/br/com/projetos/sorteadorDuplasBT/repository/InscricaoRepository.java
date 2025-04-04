package br.com.projetos.sorteadorDuplasBT.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetos.sorteadorDuplasBT.model.Inscricao;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    List<Inscricao> findByCampeonatoId(Long campeonatoId);

    List<Inscricao> findByCampeonatoIdAndParticipaBrindeTrue(Long campeonatoId);

    boolean existsByCampeonatoIdAndJogadorId(Long campeonatoId, Long jogadorId);
}
