package br.com.projetos.sorteadorDuplasBT.controller;

import br.com.projetos.sorteadorDuplasBT.model.Dupla;
import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.service.SorteioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sorteio")
public class SorteioController {
    
    private final SorteioService sorteioService;

    public SorteioController(SorteioService sorteioService) {
        this.sorteioService = sorteioService;
    }

    @PostMapping("/duplas")
    public ResponseEntity<List<Dupla>> realizarSorteio() {
        List<Dupla> duplas = sorteioService.sortearDuplas();
        return ResponseEntity.ok(duplas);
    }

    @GetMapping("/duplas")
    public ResponseEntity<List<Dupla>> findDuplas() {
        List<Dupla> duplas = sorteioService.findDuplas();
        return ResponseEntity.ok(duplas);
    }

    @GetMapping("/sortear-brinde")
    public Jogador sortearBrinde() {
        return sorteioService.sortearBrinde();
    }

    @GetMapping("/ganhador-brinde")
    public Jogador obterGanhadorBrinde() {
        return sorteioService.getJogadorGanhadorBrinde();
    }

    @DeleteMapping("/duplas")
    public ResponseEntity<Void> apagarTodasDuplas() {
        try {
            sorteioService.apagarTodasDuplas();
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace(); // Loga a exceção para verificar o motivo do erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
