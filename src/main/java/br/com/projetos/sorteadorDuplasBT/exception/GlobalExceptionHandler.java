package br.com.projetos.sorteadorDuplasBT.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CampeonatoNaoEncontradoException.class)
    public ResponseEntity<String> handleCampeonatoNaoEncontrado(CampeonatoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(JogadorNaoEncontradoException.class)
    public ResponseEntity<String> handleJogadorNaoEncontrado(JogadorNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
    
    @ExceptionHandler(ClassificacaoNaoEncontradaException.class)
    public ResponseEntity<String> handleClassificacaoNaoEncontrada(ClassificacaoNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(InscricaoExistenteException.class)
    public ResponseEntity<String> handleInscricaoExistente(InscricaoExistenteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + ex.getMessage());
    }
}
