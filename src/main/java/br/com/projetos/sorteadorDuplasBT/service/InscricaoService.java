package br.com.projetos.sorteadorDuplasBT.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.sorteadorDuplasBT.dto.ErroInscricaoDTO;
import br.com.projetos.sorteadorDuplasBT.dto.InscricaoBateladaResultadoDTO;
import br.com.projetos.sorteadorDuplasBT.dto.InscricaoDTO;
import br.com.projetos.sorteadorDuplasBT.dto.InscricaoRequestDTO;
import br.com.projetos.sorteadorDuplasBT.exception.CampeonatoNaoEncontradoException;
import br.com.projetos.sorteadorDuplasBT.exception.ClassificacaoNaoEncontradaException;
import br.com.projetos.sorteadorDuplasBT.exception.InscricaoExistenteException;
import br.com.projetos.sorteadorDuplasBT.exception.JogadorNaoEncontradoException;
import br.com.projetos.sorteadorDuplasBT.model.Campeonato;
import br.com.projetos.sorteadorDuplasBT.model.Classificacao;
import br.com.projetos.sorteadorDuplasBT.model.Inscricao;
import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.model.StatusInscricao;
import br.com.projetos.sorteadorDuplasBT.repository.CampeonatoRepository;
import br.com.projetos.sorteadorDuplasBT.repository.ClassificacaoRepository;
import br.com.projetos.sorteadorDuplasBT.repository.InscricaoRepository;
import br.com.projetos.sorteadorDuplasBT.repository.JogadorRepository;
import jakarta.transaction.Transactional;


@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private CampeonatoRepository campeonatoRepository;
    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    private ClassificacaoRepository classificacaoRepository;

    public List<Inscricao> listarPorCampeonato(Long campeonatoId) {
        return inscricaoRepository.findByCampeonatoId(campeonatoId);
    }

    @Transactional
    public Inscricao inscreverJogador(Long campeonatoId, Long jogadorId, Long classificacaoId) {
        if (inscricaoRepository.existsByCampeonatoIdAndJogadorId(campeonatoId, jogadorId)) {
            throw new InscricaoExistenteException();
        }

        Campeonato campeonato = campeonatoRepository.findById(campeonatoId)
            .orElseThrow(() -> new CampeonatoNaoEncontradoException(campeonatoId));

        Jogador jogador = jogadorRepository.findById(jogadorId)
            .orElseThrow(() -> new JogadorNaoEncontradoException(jogadorId));

        Classificacao classificacao = classificacaoRepository.findById(classificacaoId)
            .orElseThrow(() -> new ClassificacaoNaoEncontradaException(classificacaoId));

        Inscricao inscricao = new Inscricao(campeonato, jogador, LocalDate.now(), StatusInscricao.CONFIRMADA, classificacao);
        return inscricaoRepository.save(inscricao);
    }

    public void removerInscricao(Long inscricaoId) {
        inscricaoRepository.deleteById(inscricaoId);
    }

    public void atualizarParticipacaoBrindeEmMassa(List<Inscricao> inscricoes) {
        inscricaoRepository.saveAll(inscricoes);
    }

    @Transactional
    public Inscricao atualizarInscricao(Long id, Long classificacaoId) throws IOException {
        Inscricao inscricaoExistente = inscricaoRepository.findById(id)
            .orElseThrow(() -> new InscricaoExistenteException());
        
        Classificacao classificacao = classificacaoRepository.findById(classificacaoId)
            .orElseThrow(() -> new ClassificacaoNaoEncontradaException(classificacaoId));
        inscricaoExistente.setClassificacao(classificacao);
       
        return inscricaoRepository.save(inscricaoExistente);
    }
   
    public InscricaoBateladaResultadoDTO inscreverJogadoresEmBatelada(Long campeonatoId, List<InscricaoRequestDTO> inscricoesRequestDTOs) {
        InscricaoBateladaResultadoDTO resultado = new InscricaoBateladaResultadoDTO();

        for (InscricaoRequestDTO inscricaoRequestDTO : inscricoesRequestDTOs) {
            try {
                Inscricao nova = inscreverJogador(campeonatoId, inscricaoRequestDTO.getJogadorId(), inscricaoRequestDTO.getClassificacaoId());
                InscricaoDTO novaInscricaoDTO = new InscricaoDTO(nova.getJogador().getId(), nova.getJogador().getNome(),
                 campeonatoId, nova.getClassificacao().getId(), nova.getStatus().name()) ;
                resultado.getInscricoesSucesso().add(novaInscricaoDTO);
            } catch (RuntimeException e) {
                String nomeJogador = jogadorRepository.findById(inscricaoRequestDTO.getJogadorId())
                .map(Jogador::getNome)
                .orElse("Jogador desconhecido");

                resultado.getErros().add(
                    new ErroInscricaoDTO(inscricaoRequestDTO.getJogadorId(), nomeJogador, e.getMessage())
                );
            }
        }

        return resultado;
    }
}

