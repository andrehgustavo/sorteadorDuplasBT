package br.com.projetos.sorteadorDuplasBT.controller;

import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.service.JogadorService;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/jogadores")
public class JogadorController {
    
    @Autowired
    private JogadorService jogadorService;

    @GetMapping
    public ResponseEntity<List<Jogador>> listarJogadores() {
        return ResponseEntity.ok(jogadorService.listarTodos());
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Jogador> criarJogador(
        @RequestParam("nome") String nome,
        @RequestParam("classificacaoId") Long classificacaoId,
        @RequestParam(value = "foto", required = false) MultipartFile foto
    ) {
        try {
            Jogador jogador = jogadorService.salvarJogador(nome, classificacaoId, foto);
            return ResponseEntity.status(HttpStatus.CREATED).body(jogador);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogador> atualizarJogador(
        @PathVariable Long id,
        @RequestParam("nome") String nome,
        @RequestParam("classificacaoId") Long classificacaoId,
        @RequestPart(value = "foto", required = false) MultipartFile foto) {
        
        Jogador jogadorAtualizado = jogadorService.atualizarJogador(id, nome, classificacaoId, foto);
        return ResponseEntity.ok(jogadorAtualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirJogador(@PathVariable Long id) {
        jogadorService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/atualizarParticipacaoBrinde")
    public ResponseEntity<Void> atualizarParticipacaoBrindeEmMassa(@RequestBody List<Jogador> jogadores) {
        try {
            jogadorService.atualizarParticipacaoBrindeEmMassa(jogadores);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace(); // Loga a exceção para verificar o motivo do erro
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }   


}