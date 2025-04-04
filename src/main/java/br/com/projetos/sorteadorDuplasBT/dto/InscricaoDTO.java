package br.com.projetos.sorteadorDuplasBT.dto;

import lombok.Data;

@Data
public class InscricaoDTO {
    private Long jogadorId;
    private String nomeJogador;
    private Long campeonatoId;
    private Long classificacaoId;
    private String status;

    public InscricaoDTO(Long jogadorId, String nomeJogador, Long campeonatoId, Long classificacaoId, String status) {
        this.jogadorId = jogadorId;
        this.nomeJogador = nomeJogador;
        this.campeonatoId = campeonatoId;
        this.classificacaoId = classificacaoId;
        this.status = status;
    }
}
