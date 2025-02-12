package br.com.projetos.sorteadorDuplasBT.service;

import br.com.projetos.sorteadorDuplasBT.model.Classificacao;
import br.com.projetos.sorteadorDuplasBT.repository.ClassificacaoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClassificacaoService {
    private final ClassificacaoRepository classificacaoRepository;

    public ClassificacaoService(ClassificacaoRepository classificacaoRepository) {
        this.classificacaoRepository = classificacaoRepository;
    }

    public List<Classificacao> listarTodas() {
        return classificacaoRepository.findAll();
    }

    public Classificacao salvar(Classificacao classificacao) {
        return classificacaoRepository.save(classificacao);
    }

    public void deletar(Long id) {
        classificacaoRepository.deleteById(id);
    }
}
