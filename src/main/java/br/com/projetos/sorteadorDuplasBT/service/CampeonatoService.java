package br.com.projetos.sorteadorDuplasBT.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.projetos.sorteadorDuplasBT.exception.CampeonatoNaoEncontradoException;
import br.com.projetos.sorteadorDuplasBT.model.Campeonato;
import br.com.projetos.sorteadorDuplasBT.repository.CampeonatoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CampeonatoService {
    
    @Autowired
    private CampeonatoRepository campeonatoRepository;

    public List<Campeonato> listarTodos() {
        return campeonatoRepository.findAll();
    }

    public Campeonato buscarPorId(Long id) {
        return campeonatoRepository.findById(id)
                .orElseThrow(() -> new CampeonatoNaoEncontradoException(id));
    }

    public Campeonato salvar(Campeonato campeonato) {
         // Desativa o campeonato ativo atual, se houver
         campeonatoRepository.findByAtivoTrue().ifPresent(campeonatoAnterior -> {
            campeonatoAnterior.setAtivo(false);
            campeonatoRepository.save(campeonatoAnterior);
        });
        campeonato.setAtivo(true);
        return campeonatoRepository.save(campeonato);
    }

    public Campeonato atualizar(Long id, Campeonato campeonatoAtualizado) {
        Campeonato campeonato = buscarPorId(id);
        campeonato.setNome(campeonatoAtualizado.getNome());
        campeonato.setData(campeonatoAtualizado.getData());
        campeonato.setLocal(campeonatoAtualizado.getLocal());
        campeonato.setStatus(campeonatoAtualizado.getStatus());
        campeonato.setAtivo(campeonatoAtualizado.isAtivo());
        return campeonatoRepository.save(campeonato);
    }

    public void deletar(Long id) {
        campeonatoRepository.deleteById(id);
    }

    public Campeonato buscarAtivo() {
        return campeonatoRepository.findByAtivoTrue()
                .orElseThrow(() -> new EntityNotFoundException("Campeonato não encontrado"));
    }
}
