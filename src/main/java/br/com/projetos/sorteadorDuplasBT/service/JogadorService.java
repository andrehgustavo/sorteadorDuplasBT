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
import jakarta.transaction.Transactional;

@Service
public class JogadorService {
    @Autowired
    private JogadorRepository jogadorRepository;
    
    @Autowired
    private ClassificacaoRepository classificacaoRepository;

    public List<Jogador> listarTodos() {
        return jogadorRepository.findAllByOrderByNomeAsc();
    }

    public Jogador salvarJogador(String nome, Long classificacaoId, MultipartFile foto) throws IOException {
        Jogador jogador = new Jogador();
        jogador.setNome(nome);

        Classificacao classificacao = new Classificacao();
        classificacao.setId(classificacaoId);
        jogador.setClassificacao(classificacao);

        if (foto != null && !foto.isEmpty()) {
            jogador.setFoto(foto.getBytes()); // CONVERTE MultipartFile para byte[]
        }

        return jogadorRepository.save(jogador);
    }

    @Transactional
    public void excluir(Long id) {
        jogadorRepository.deleteById(id);
    }



    @Transactional
    public Jogador atualizarJogador(Long id, String nome, Long classificacaoId, MultipartFile foto) {
        Jogador jogadorExistente = jogadorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Jogador n�o encontrado"));

        jogadorExistente.setNome(nome);
        
        Classificacao classificacao = classificacaoRepository.findById(classificacaoId)
            .orElseThrow(() -> new RuntimeException("Classifica��o n�o encontrada"));
        jogadorExistente.setClassificacao(classificacao);

        if (foto != null && !foto.isEmpty()) {
            try {
                jogadorExistente.setFoto(foto.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Erro ao processar a foto", e);
            }
        }

        return jogadorRepository.save(jogadorExistente);
    }
}
