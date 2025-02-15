package br.com.projetos.sorteadorDuplasBT.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.projetos.sorteadorDuplasBT.model.Classificacao;

@Repository
public interface ClassificacaoRepository extends JpaRepository<Classificacao, Long> {}