package br.com.projetos.sorteadorDuplasBT.controller;

import br.com.projetos.sorteadorDuplasBT.model.Dupla;
import br.com.projetos.sorteadorDuplasBT.model.Inscricao;
import br.com.projetos.sorteadorDuplasBT.service.SorteioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sorteio")
public class SorteioController {
    
    @Autowired
    private SorteioService sorteioService;

    @PostMapping("{idCampeonato}/duplas")
    public ResponseEntity<List<Dupla>> realizarSorteio(@PathVariable Long idCampeonato) {
        List<Dupla> duplas = sorteioService.sortearDuplas(idCampeonato);
        return ResponseEntity.ok(duplas);
    }

    @GetMapping("{idCampeonato}/duplas")
    public ResponseEntity<List<Dupla>> findDuplas(@PathVariable Long idCampeonato) {
        List<Dupla> duplas = sorteioService.findDuplas(idCampeonato);
        return ResponseEntity.ok(duplas);
    }

    @GetMapping("{idCampeonato}/sortear-brinde")
    public Inscricao sortearBrinde(@PathVariable Long idCampeonato) {
        return sorteioService.sortearBrinde(idCampeonato);
    }

    @GetMapping("/ganhador-brinde")
    public Inscricao obterGanhadorBrinde() {
        return sorteioService.getInscricaoGanhadorBrinde();
    }

    @DeleteMapping("{idCampeonato}/duplas")
    public ResponseEntity<Void> apagarTodasDuplas(@PathVariable Long idCampeonato) {
        try {
            sorteioService.apagarTodasDuplas(idCampeonato);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace(); // Loga a exceção para verificar o motivo do erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
}
