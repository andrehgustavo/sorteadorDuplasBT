package br.com.projetos.sorteadorDuplasBT.dto;

import lombok.Data;

@Data
public class InscricaoRequestDTO  {
    private Long jogadorId;
    private Long classificacaoId;

    public InscricaoRequestDTO (Long jogadorId, Long classificacaoId) {
        this.jogadorId = jogadorId;
        this.classificacaoId = classificacaoId;
    }
}
