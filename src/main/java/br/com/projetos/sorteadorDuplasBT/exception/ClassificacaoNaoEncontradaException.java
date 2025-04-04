package br.com.projetos.sorteadorDuplasBT.exception;

public class ClassificacaoNaoEncontradaException extends RuntimeException {
    public ClassificacaoNaoEncontradaException(Long id) {
        super("Classificação com ID " + id + " não encontrado.");
    }
}
