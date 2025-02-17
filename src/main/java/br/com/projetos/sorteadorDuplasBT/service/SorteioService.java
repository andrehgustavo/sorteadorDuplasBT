package br.com.projetos.sorteadorDuplasBT.service;

import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.model.Classificacao;
import br.com.projetos.sorteadorDuplasBT.model.Dupla;
import br.com.projetos.sorteadorDuplasBT.repository.DuplaRepository;
import br.com.projetos.sorteadorDuplasBT.repository.JogadorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SorteioService {

    private Jogador jogadorGanhadorBrinde;

    @Autowired
    private JogadorRepository jogadorRepository;
    @Autowired
    private DuplaRepository duplaRepository;

    public SorteioService() {

    }

    public List<Dupla> sortearDuplas() {
        List<Jogador> jogadores = jogadorRepository.findAll();
    
        // Agrupar jogadores por classificação
        Map<Integer, List<Jogador>> jogadoresPorClassificacao = jogadores.stream()
                .collect(Collectors.groupingBy(j -> j.getClassificacao().getOrdem()));
    
        LinkedList<Integer> ordens = new LinkedList<>(jogadoresPorClassificacao.keySet());
        Collections.sort(ordens);
    
        List<Dupla> duplas = new ArrayList<>();
        List<Jogador> jogadoresSemPar = new ArrayList<>();
        Random random = new Random();
    
        while (!ordens.isEmpty()) {
            int menorOrdem = ordens.getFirst();
    
            List<Jogador> grupoMenorOrdem = jogadoresPorClassificacao.get(menorOrdem);
            if (grupoMenorOrdem == null || grupoMenorOrdem.isEmpty()) {
                jogadoresPorClassificacao.remove(menorOrdem);
                ordens.removeFirst();
                continue;
            }
    
            Jogador j1 = grupoMenorOrdem.remove(random.nextInt(grupoMenorOrdem.size()));
    
            // Remover a ordem se estiver vazia
            if (grupoMenorOrdem.isEmpty()) {
                jogadoresPorClassificacao.remove(menorOrdem);
                ordens.removeFirst();
            }
    
            // Encontrar jogador da menor classificação disponível
            Jogador j2 = null;
            for (int i = ordens.size() - 1; i >= 0; i--) {
                int ordemAtual = ordens.get(i);
                List<Jogador> grupoAtual = jogadoresPorClassificacao.get(ordemAtual);
    
                if (grupoAtual != null && !grupoAtual.isEmpty()) {
                    j2 = grupoAtual.remove(random.nextInt(grupoAtual.size()));
                    
                    // Remover a ordem se estiver vazia
                    if (grupoAtual.isEmpty()) {
                        jogadoresPorClassificacao.remove(ordemAtual);
                        ordens.remove(i);
                    }
                    break;
                }
            }
    
            if (j2 != null) {
                duplas.add(new Dupla(j1, j2));
            } else {
                jogadoresSemPar.add(j1);
            }
        }
    
        // Tratamento para jogadores sem par
        if (!jogadoresSemPar.isEmpty()) {
            System.out.println("Número de jogadores ímpar. Alguns jogadores não têm par.");
        }
    
        // Limpa duplas antigas e salva as novas
        duplaRepository.deleteAll();
        duplaRepository.saveAll(duplas);
    
        return duplas;
    }
    
    
    
    

    public List<Dupla> findDuplas() {
        return duplaRepository.findAll();
    }

    public Jogador sortearBrinde() {
        List<Jogador> jogadores = jogadorRepository.findByParticipaBrindeTrue();
        Random random = new Random();
        if (jogadores.isEmpty()) {
            return null; 
        }
        int indiceSorteado = random.nextInt(jogadores.size());
        // Atualiza a variável com as duplas sorteadas
        this.jogadorGanhadorBrinde = jogadores.get(indiceSorteado);
        return jogadorGanhadorBrinde;
    }

    public Jogador getJogadorGanhadorBrinde(){
        return jogadorGanhadorBrinde == null ? null : jogadorGanhadorBrinde;
    }

    public void apagarTodasDuplas() {
        duplaRepository.deleteAll();
    }
}
