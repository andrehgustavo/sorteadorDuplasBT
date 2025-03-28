package br.com.projetos.sorteadorDuplasBT.service;

import br.com.projetos.sorteadorDuplasBT.controller.CampeonatoController;
import br.com.projetos.sorteadorDuplasBT.model.Campeonato;
import br.com.projetos.sorteadorDuplasBT.repository.CampeonatoRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CampeonatoService {
    
    @Autowired
    private CampeonatoRepository campeonatoRepository;

    public List<Campeonato> listarTodos() {
        return campeonatoRepository.findAll();
    }

    public Campeonato buscarPorId(Long id) {
        return campeonatoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Campeonato não encontrado"));
    }

    public Campeonato salvar(Campeonato campeonato) {
        return campeonatoRepository.save(campeonato);
    }

    public Campeonato atualizar(Long id, Campeonato campeonatoAtualizado) {
        Campeonato campeonato = buscarPorId(id);
        campeonato.setNome(campeonatoAtualizado.getNome());
        campeonato.setData(campeonatoAtualizado.getData());
        campeonato.setLocal(campeonatoAtualizado.getLocal());
        campeonato.setStatus(campeonatoAtualizado.getStatus());
        campeonato.setMaxDuplas(campeonatoAtualizado.getMaxDuplas());
        return campeonatoRepository.save(campeonato);
    }

    public void deletar(Long id) {
        campeonatoRepository.deleteById(id);
    }
}
