package br.com.projetos.sorteadorDuplasBT.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.projetos.sorteadorDuplasBT.model.Classificacao;
import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.repository.ClassificacaoRepository;
import br.com.projetos.sorteadorDuplasBT.repository.JogadorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class JogadorService {
    @Autowired
    private JogadorRepository jogadorRepository;
    
    @Autowired
    private ClassificacaoRepository classificacaoRepository;

    @Autowired
    private FotoService fotoService;

    public List<Jogador> listarTodos() {
        return jogadorRepository.findAllByOrderByNomeAsc();
    }

    public Jogador salvarJogador(String nome, Long classificacaoId, MultipartFile foto) throws IOException {
        Jogador jogador = new Jogador();
        jogador.setNome(nome);

        Classificacao classificacao = classificacaoRepository.findById(classificacaoId)
            .orElseThrow(() -> new RuntimeException("Classificação não encontrada"));
        jogador.setClassificacao(classificacao);

        // Primeiro salva o jogador para gerar o ID
        jogador = jogadorRepository.save(jogador);

        // Depois salva a foto com o ID correto
        if (foto != null && !foto.isEmpty()) {
            String fotoUrl = fotoService.salvarFoto(foto, jogador.getId()); // Usa o ID correto
            jogador.setFotoUrl(fotoUrl);
            jogadorRepository.save(jogador); // Atualiza com a URL da foto
        }

        return jogador;
    }

    @Transactional
    public void excluir(Long id) {
        jogadorRepository.deleteById(id);
    }

    @Transactional
    public Jogador atualizarJogador(Long id, String nome, Long classificacaoId, MultipartFile foto) throws IOException {
        Jogador jogadorExistente = jogadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        
        jogadorExistente.setNome(nome);
        
        Classificacao classificacao = classificacaoRepository.findById(classificacaoId)
            .orElseThrow(() -> new RuntimeException("Classificação não encontrada"));
        jogadorExistente.setClassificacao(classificacao);

        if (foto != null && !foto.isEmpty()) {
            String fotoUrl = fotoService.salvarFoto(foto, id);
            jogadorExistente.setFotoUrl(fotoUrl);
        }
        
        return jogadorRepository.save(jogadorExistente);
    }

    public Jogador atualizarParticipacaoBrinde(Long id, Boolean participaBrinde) {
        Jogador jogador = jogadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        jogador.setParticipaBrinde(participaBrinde);
        return jogadorRepository.save(jogador);
    }

    public void atualizarParticipacaoBrindeEmMassa(List<Jogador> jogadores) {
        jogadorRepository.saveAll(jogadores);
    }

    public Jogador buscarPorId(Long id) {
        return jogadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Jogador não encontrado"));
    }

    public String uploadFoto(Long jogadorId, MultipartFile file) throws IOException {
        Jogador jogador = buscarPorId(jogadorId);
        String fotoUrl = fotoService.salvarFoto(file, jogadorId);
        jogador.setFotoUrl(fotoUrl);
        jogadorRepository.save(jogador);
        return fotoUrl;
    }
}