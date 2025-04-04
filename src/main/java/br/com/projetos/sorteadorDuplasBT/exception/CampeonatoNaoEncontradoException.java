package br.com.projetos.sorteadorDuplasBT.exception;

public class CampeonatoNaoEncontradoException extends RuntimeException {
    public CampeonatoNaoEncontradoException(Long id) {
        super("Campeonato com ID " + id + " não encontrado.");
    }
}
