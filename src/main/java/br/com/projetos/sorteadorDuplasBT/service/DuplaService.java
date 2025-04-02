package br.com.projetos.sorteadorDuplasBT.service;

import br.com.projetos.sorteadorDuplasBT.model.Dupla;
import br.com.projetos.sorteadorDuplasBT.model.Inscricao;
import br.com.projetos.sorteadorDuplasBT.model.Jogador;
import br.com.projetos.sorteadorDuplasBT.repository.DuplaRepository;
import br.com.projetos.sorteadorDuplasBT.repository.InscricaoRepository;
import br.com.projetos.sorteadorDuplasBT.repository.JogadorRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DuplaService {

    @Autowired
    private DuplaRepository duplaRepository;
    @Autowired
    private InscricaoRepository inscricaoRepository;

    public Dupla criarDupla(Long inscricao1Id, Long inscricao2Id) {
        Inscricao inscricao1 = inscricaoRepository.findById(inscricao1Id)
                .orElseThrow(() -> new EntityNotFoundException("Jogador 1 não encontrado"));
        Inscricao inscricao2 = inscricaoRepository.findById(inscricao2Id)
                .orElseThrow(() -> new EntityNotFoundException("Jogador 2 não encontrado"));

        Dupla dupla = new Dupla(inscricao1, inscricao2);
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
