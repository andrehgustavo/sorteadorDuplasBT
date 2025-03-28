package br.com.projetos.sorteadorDuplasBT.service;

import br.com.projetos.sorteadorDuplasBT.model.Dupla;
import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.repository.DuplaRepository;
import br.com.projetos.sorteadorDuplasBT.repository.JogadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DuplaService {

    private final DuplaRepository duplaRepository;
    private final JogadorRepository jogadorRepository;

    public Dupla criarDupla(Long jogador1Id, Long jogador2Id) {
        Jogador jogador1 = jogadorRepository.findById(jogador1Id)
                .orElseThrow(() -> new EntityNotFoundException("Jogador 1 não encontrado"));
        Jogador jogador2 = jogadorRepository.findById(jogador2Id)
                .orElseThrow(() -> new EntityNotFoundException("Jogador 2 não encontrado"));

        Dupla dupla = new Dupla(jogador1, jogador2);
        return duplaRepository.save(dupla);
    }

    public List<Dupla> listarTodas() {
        return duplaRepository.findAll();
    }

    public Dupla buscarPorId(Long id) {
        return duplaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Dupla não encontrada"));
    }

    public void excluirDupla(Long id) {
        if (!duplaRepository.existsById(id)) {
            throw new EntityNotFoundException("Dupla não encontrada");
        }
        duplaRepository.deleteById(id);
    }
}
