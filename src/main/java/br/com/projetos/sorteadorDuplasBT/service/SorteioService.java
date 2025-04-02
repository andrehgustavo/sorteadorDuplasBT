package br.com.projetos.sorteadorDuplasBT.service;

import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.model.Campeonato;
import br.com.projetos.sorteadorDuplasBT.model.Classificacao;
import br.com.projetos.sorteadorDuplasBT.model.Dupla;
import br.com.projetos.sorteadorDuplasBT.model.Inscricao;
import br.com.projetos.sorteadorDuplasBT.repository.CampeonatoRepository;
import br.com.projetos.sorteadorDuplasBT.repository.DuplaRepository;
import br.com.projetos.sorteadorDuplasBT.repository.InscricaoRepository;
import br.com.projetos.sorteadorDuplasBT.repository.JogadorRepository;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SorteioService {

    private Inscricao inscricaoGanhadorBrinde;

    @Autowired
    private DuplaRepository duplaRepository;

    @Autowired
    private InscricaoRepository inscricaoRepository;

    @Transactional
    public List<Dupla> sortearDuplas(Long idCampeonato) {
        List<Inscricao> inscricoes = inscricaoRepository.findByCampeonatoId(idCampeonato);
    
        // Agrupar inscricoes por classificação
        Map<Integer, List<Inscricao>> inscricoesPorClassificacao = inscricoes.stream()
                .collect(Collectors.groupingBy(i -> i.getJogador().getClassificacao().getOrdem()));
    
        LinkedList<Integer> ordens = new LinkedList<>(inscricoesPorClassificacao.keySet());
        Collections.sort(ordens);
    
        List<Dupla> duplas = new ArrayList<>();
        List<Inscricao> inscricoesSemPar = new ArrayList<>();
        Random random = new Random();
    
        while (!ordens.isEmpty()) {
            int menorOrdem = ordens.getFirst();
    
            List<Inscricao> grupoMenorOrdem = inscricoesPorClassificacao.get(menorOrdem);
            if (grupoMenorOrdem == null || grupoMenorOrdem.isEmpty()) {
                inscricoesPorClassificacao.remove(menorOrdem);
                ordens.removeFirst();
                continue;
            }
    
            Inscricao j1 = grupoMenorOrdem.remove(random.nextInt(grupoMenorOrdem.size()));
    
            // Remover a ordem se estiver vazia
            if (grupoMenorOrdem.isEmpty()) {
                inscricoesPorClassificacao.remove(menorOrdem);
                ordens.removeFirst();
            }
    
            // Encontrar jogador da menor classificação disponível
            Inscricao j2 = null;
            for (int i = ordens.size() - 1; i >= 0; i--) {
                int ordemAtual = ordens.get(i);
                List<Inscricao> grupoAtual = inscricoesPorClassificacao.get(ordemAtual);
    
                if (grupoAtual != null && !grupoAtual.isEmpty()) {
                    j2 = grupoAtual.remove(random.nextInt(grupoAtual.size()));
                    
                    // Remover a ordem se estiver vazia
                    if (grupoAtual.isEmpty()) {
                        inscricoesPorClassificacao.remove(ordemAtual);
                        ordens.remove(i);
                    }
                    break;
                }
            }
    
            if (j2 != null) {
                Campeonato c = new Campeonato();
                c.setId(idCampeonato);
                duplas.add(new Dupla(c, j1, j2));
            } else {
                inscricoesSemPar.add(j1);
            }
        }
    
        // Tratamento para jogadores sem par
        if (!inscricoesSemPar.isEmpty()) {
            System.out.println("Número de inscrições ímpar. Algumas inscrições não têm par.");
        }
    
        // Limpa duplas antigas e salva as novas
        duplaRepository.deleteByCampeonatoId(idCampeonato);
        duplaRepository.saveAll(duplas);
    
        return duplas;
    }
    
    
    
    

    public List<Dupla> findDuplas(Long idCampeonato) {
        return duplaRepository.findByCampeonatoId(idCampeonato);
    }

    public Inscricao sortearBrinde(Long idCampeonato) {
        List<Inscricao> inscricoes = inscricaoRepository.findByCampeonatoIdAndParticipaBrindeTrue(idCampeonato);
        Random random = new Random();
        if (inscricoes.isEmpty()) {
            return null; 
        }
        int indiceSorteado = random.nextInt(inscricoes.size());
        // Atualiza a variável com as duplas sorteadas
        this.inscricaoGanhadorBrinde = inscricoes.get(indiceSorteado);
        return inscricaoGanhadorBrinde;
    }

    public Inscricao getInscricaoGanhadorBrinde(){
        return inscricaoGanhadorBrinde == null ? null : inscricaoGanhadorBrinde;
    }

    @Transactional
    public void apagarTodasDuplas(Long idCampeonato) {
        duplaRepository.deleteByCampeonatoId(idCampeonato);
    }
}
