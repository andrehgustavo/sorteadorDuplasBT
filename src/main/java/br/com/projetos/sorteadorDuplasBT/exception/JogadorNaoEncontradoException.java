package br.com.projetos.sorteadorDuplasBT.exception;

public class JogadorNaoEncontradoException extends RuntimeException {
    public JogadorNaoEncontradoException(Long id) {
        super("Jogador com ID " + id + " não encontrado.");
    }
}

