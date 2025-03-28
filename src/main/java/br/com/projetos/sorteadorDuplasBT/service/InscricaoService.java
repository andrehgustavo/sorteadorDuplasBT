package br.com.projetos.sorteadorDuplasBT.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.sorteadorDuplasBT.model.Campeonato;
import br.com.projetos.sorteadorDuplasBT.model.Dupla;
import br.com.projetos.sorteadorDuplasBT.model.Inscricao;
import br.com.projetos.sorteadorDuplasBT.model.StatusInscricao;
import br.com.projetos.sorteadorDuplasBT.repository.InscricaoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class InscricaoService {

    @Autowired
    private InscricaoRepository inscricaoRepository;
    @Autowired
    private CampeonatoService campeonatoService;
    @Autowired
    private DuplaService duplaService;

    public List<Inscricao> listarTodos() {
        return inscricaoRepository.findAll();
    }

    public Inscricao buscarPorId(Long id) {
        return inscricaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Inscrição não encontrada"));
    }

    public Inscricao inscrever(Long campeonatoId, Long duplaId) {
        Campeonato campeonato = campeonatoService.buscarPorId(campeonatoId);
        Dupla dupla = duplaService.buscarPorId(duplaId);

        if (campeonato.getMaxDuplas() != null &&
            inscricaoRepository.countByCampeonatoId(campeonatoId) >= campeonato.getMaxDuplas()) {
            throw new IllegalStateException("Limite de duplas atingido para este campeonato");
        }

        Inscricao inscricao = new Inscricao(campeonato, dupla, LocalDate.now(), StatusInscricao.PENDENTE);

        return inscricaoRepository.save(inscricao);
    }

    public void deletar(Long id) {
        inscricaoRepository.deleteById(id);
    }
}

