package br.com.projetos.sorteadorDuplasBT.exception;

public class InscricaoExistenteException extends RuntimeException {
    public InscricaoExistenteException() {
        super("Já existe uma inscrição no campeonato para esse jogador.");
    }
}
