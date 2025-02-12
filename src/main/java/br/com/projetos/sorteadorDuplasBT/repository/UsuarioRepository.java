package br.com.projetos.sorteadorDuplasBT.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.projetos.sorteadorDuplasBT.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByUsername(String username);
}