package br.com.projetos.sorteadorDuplasBT.service;

import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.model.Classificacao;
import br.com.projetos.sorteadorDuplasBT.repository.JogadorRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SorteioService {
    private List<List<Jogador>> duplasSorteadas = new ArrayList<>();
    private Jogador jogadorGanhadorBrinde;

    private final JogadorRepository jogadorRepository;

    public SorteioService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    public List<List<Jogador>> sortearDuplas() {
        List<Jogador> jogadores = jogadorRepository.findAll();

        // Agrupa jogadores por nível de classificação (ordem)
        Map<Integer, List<Jogador>> jogadoresPorClassificacao = jogadores.stream()
            .collect(Collectors.groupingBy(j -> j.getClassificacao().getOrdem()));
        
            // Lista de ordens disponíveis, ordenadas do menor para o maior
        List<Integer> ordens = new ArrayList<>(jogadoresPorClassificacao.keySet());
        Collections.sort(ordens);
        
        List<List<Jogador>> duplas = new ArrayList<>();
        Random random = new Random();
        
        while (!jogadoresPorClassificacao.isEmpty()) {

            int menorOrdem = ordens.get(0);
            int maiorOrdem = ordens.get(ordens.size() - 1);
            
            List<Jogador> grupoMenor = jogadoresPorClassificacao.get(menorOrdem);
            List<Jogador> grupoMaior = jogadoresPorClassificacao.get(maiorOrdem);
            
            // Se houver jogadores disponíveis nos dois grupos, sorteia um par
            if (grupoMenor != null && !grupoMenor.isEmpty() && grupoMaior != null && !grupoMaior.isEmpty()) {
                Jogador j1 = grupoMenor.remove(random.nextInt(grupoMenor.size()));
                Jogador j2 = grupoMaior.remove(random.nextInt(grupoMaior.size()));
                duplas.add(Arrays.asList(j1, j2));
            }
            
            // Remove a classificação se não houver mais jogadores nesse grupo
            if (grupoMenor.isEmpty()) {
                jogadoresPorClassificacao.remove(menorOrdem);
                ordens.remove(0);
            }
            
            if (grupoMaior.isEmpty()) {
                jogadoresPorClassificacao.remove(maiorOrdem);
                ordens.remove(ordens.size() - 1);
            }
        }

        // Atualiza a variável com as duplas sorteadas
        this.duplasSorteadas = duplas;
        
        return duplas;
    }

    public List<List<Jogador>> obterDuplasAtuais() {
        return duplasSorteadas.isEmpty() ? Collections.emptyList() : duplasSorteadas;
    }

    public void atualizarDuplas(List<List<Jogador>> novasDuplas) {
        this.duplasSorteadas = novasDuplas;
    }

    public Jogador sortearBrinde() {
        List<Jogador> jogadores = jogadorRepository.findAll();
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
}
