package br.com.projetos.sorteadorDuplasBT.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.projetos.sorteadorDuplasBT.dto.InscricaoBateladaResultadoDTO;
import br.com.projetos.sorteadorDuplasBT.dto.InscricaoRequestDTO;
import br.com.projetos.sorteadorDuplasBT.model.Inscricao;
import br.com.projetos.sorteadorDuplasBT.service.InscricaoService;

@RestController
@RequestMapping("/inscricoes")
public class InscricaoController {
    
    @Autowired
    private InscricaoService inscricaoService;

    @GetMapping("/campeonato/{campeonatoId}")
    public ResponseEntity<List<Inscricao>> listarPorCampeonato(@PathVariable Long campeonatoId) {
        return ResponseEntity.ok(inscricaoService.listarPorCampeonato(campeonatoId));
    }

    @PostMapping("/campeonato/{campeonatoId}")
    public ResponseEntity<Inscricao> inscreverJogador(@PathVariable Long campeonatoId, 
    @RequestParam("jogadorId") Long jogadorId,
    @RequestParam("classificacaoId") Long classificacaoId){
        return ResponseEntity.ok(inscricaoService.inscreverJogador(campeonatoId, jogadorId, classificacaoId));
    }

    @PatchMapping("/campeonato/{campeonatoId}")
    public ResponseEntity<InscricaoBateladaResultadoDTO> inscreverJogadoresEmBatelada(@PathVariable Long campeonatoId, @RequestBody List<InscricaoRequestDTO> inscricoesRequestDTOs) {
        InscricaoBateladaResultadoDTO resultado = inscricaoService.inscreverJogadoresEmBatelada(campeonatoId, inscricoesRequestDTOs);
        return ResponseEntity.ok(resultado);
    }

    @DeleteMapping("/{inscricaoId}")
    public ResponseEntity<Void> removerInscricao(@PathVariable Long inscricaoId) {
        inscricaoService.removerInscricao(inscricaoId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/atualizarParticipacaoBrinde")
    public ResponseEntity<Void> atualizarParticipacaoBrindeEmMassa(@RequestBody List<Inscricao> inscricoes) {
        try {
            inscricaoService.atualizarParticipacaoBrindeEmMassa(inscricoes);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    } 
}

