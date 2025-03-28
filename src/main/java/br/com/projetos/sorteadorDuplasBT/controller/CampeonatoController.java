package br.com.projetos.sorteadorDuplasBT.controller;

import br.com.projetos.sorteadorDuplasBT.model.Campeonato;
import br.com.projetos.sorteadorDuplasBT.service.CampeonatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/campeonatos")
public class CampeonatoController {
    
    @Autowired
    private CampeonatoService campeonatoService;

    @GetMapping
    public List<Campeonato> listarTodos() {
        return campeonatoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campeonato> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(campeonatoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Campeonato> criar(@RequestBody Campeonato campeonato) {
        Campeonato novoCampeonato = campeonatoService.salvar(campeonato);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCampeonato);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campeonato> atualizar(@PathVariable Long id, @RequestBody Campeonato campeonato) {
        return ResponseEntity.ok(campeonatoService.atualizar(id, campeonato));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        campeonatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
