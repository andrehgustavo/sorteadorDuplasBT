package br.com.projetos.sorteadorDuplasBT.repository;

import br.com.projetos.sorteadorDuplasBT.model.Dupla;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DuplaRepository extends JpaRepository<Dupla, Long> {
    void deleteAll();
}