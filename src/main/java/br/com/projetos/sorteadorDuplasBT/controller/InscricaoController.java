package br.com.projetos.sorteadorDuplasBT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetos.sorteadorDuplasBT.model.Inscricao;
import br.com.projetos.sorteadorDuplasBT.service.InscricaoService;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {
    
    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping
    public List<Inscricao> listarTodos() {
        return inscricaoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(inscricaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Inscricao> inscrever(@RequestParam Long campeonatoId, @RequestParam Long duplaId) {
        Inscricao novaInscricao = inscricaoService.inscrever(campeonatoId, duplaId);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaInscricao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        inscricaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

