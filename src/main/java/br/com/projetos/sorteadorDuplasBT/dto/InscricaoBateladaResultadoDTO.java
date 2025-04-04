package br.com.projetos.sorteadorDuplasBT.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class InscricaoBateladaResultadoDTO {
    private List<InscricaoDTO> inscricoesSucesso;
    private List<ErroInscricaoDTO> erros;

    public InscricaoBateladaResultadoDTO() {
        this.inscricoesSucesso = new ArrayList<>();
        this.erros = new ArrayList<>();
    }
}
