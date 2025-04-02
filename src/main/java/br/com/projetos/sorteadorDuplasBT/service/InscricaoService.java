package br.com.projetos.sorteadorDuplasBT.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.sorteadorDuplasBT.model.Campeonato;
import br.com.projetos.sorteadorDuplasBT.model.Inscricao;
import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.model.StatusInscricao;
import br.com.projetos.sorteadorDuplasBT.repository.CampeonatoRepository;
import br.com.projetos.sorteadorDuplasBT.repository.InscricaoRepository;
import br.com.projetos.sorteadorDuplasBT.repository.JogadorRepository;


@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private CampeonatoRepository campeonatoRepository;
    @Autowired
    private JogadorRepository jogadorRepository;

    public List<Inscricao> listarPorCampeonato(Long campeonatoId) {
        return inscricaoRepository.findByCampeonatoId(campeonatoId);
    }

    public Inscricao inscreverJogador(Long campeonatoId, Long jogadorId) {
        Campeonato campeonato = campeonatoRepository.findById(campeonatoId)
            .orElseThrow(() -> new RuntimeException("Campeonato não encontrado"));

        Jogador jogador = jogadorRepository.findById(jogadorId)
            .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));

        Inscricao inscricao = new Inscricao(campeonato, jogador, LocalDate.now(), StatusInscricao.CONFIRMADA);
        return inscricaoRepository.save(inscricao);
    }

    public void removerInscricao(Long inscricaoId) {
        inscricaoRepository.deleteById(inscricaoId);
    }

    public void atualizarParticipacaoBrindeEmMassa(List<Inscricao> inscricoes) {
        inscricaoRepository.saveAll(inscricoes);
    }
}

