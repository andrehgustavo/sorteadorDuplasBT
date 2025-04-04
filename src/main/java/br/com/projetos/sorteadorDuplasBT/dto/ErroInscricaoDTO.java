package br.com.projetos.sorteadorDuplasBT.dto;

import lombok.Data;

@Data
public class ErroInscricaoDTO {
    private Long jogadorId;
    private String nomeJogador;
    private String mensagem;

    public ErroInscricaoDTO(Long jogadorId, String nomeJogador, String mensagem) {
        this.jogadorId = jogadorId;
        this.nomeJogador = nomeJogador;
        this.mensagem = mensagem;
    }
}
