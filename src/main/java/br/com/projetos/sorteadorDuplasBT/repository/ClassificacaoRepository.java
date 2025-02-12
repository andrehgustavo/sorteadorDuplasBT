package br.com.projetos.sorteadorDuplasBT.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.projetos.sorteadorDuplasBT.model.Classificacao;

public interface ClassificacaoRepository extends JpaRepository<Classificacao, Long> {}