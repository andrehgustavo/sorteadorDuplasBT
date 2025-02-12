package br.com.projetos.sorteadorDuplasBT.controller;

import br.com.projetos.sorteadorDuplasBT.model.Classificacao;
import br.com.projetos.sorteadorDuplasBT.service.ClassificacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classificacoes")
public class ClassificacaoController {
    private final ClassificacaoService classificacaoService;

    public ClassificacaoController(ClassificacaoService classificacaoService) {
        this.classificacaoService = classificacaoService;
    }

    @GetMapping
    public List<Classificacao> listarTodas() {
        return classificacaoService.listarTodas();
    }

    @PostMapping
    public Classificacao salvar(@RequestBody Classificacao classificacao) {
        return classificacaoService.salvar(classificacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        classificacaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}