package br.com.projetos.sorteadorDuplasBT.controller;

import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.service.SorteioService;

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

    @GetMapping("/duplas")
    public List<List<Jogador>> sortearDuplas() {
        return sorteioService.sortearDuplas();
    }

    @GetMapping("/duplas-ativas")
    public ResponseEntity<List<List<Jogador>>> obterDuplasSorteadas() {
        List<List<Jogador>> duplas = sorteioService.obterDuplasAtuais();
        if (duplas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
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
}
