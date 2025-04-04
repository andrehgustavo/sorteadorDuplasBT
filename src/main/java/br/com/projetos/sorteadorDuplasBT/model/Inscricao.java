package br.com.projetos.sorteadorDuplasBT.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inscricao")
@Data
@NoArgsConstructor
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_campeonato", nullable = false)
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn(name = "id_jogador", nullable = false)
    private Jogador jogador;
    
    @ManyToOne
    @JoinColumn(name = "id_classificacao", nullable = false)
    private Classificacao classificacao;

    @Column(nullable = false)
    private LocalDate dataInscricao;

    @Enumerated(EnumType.STRING)
    private StatusInscricao status;

    @Column(name = "participa_brinde")
    private boolean participaBrinde = false;
    
    
    public Inscricao(Campeonato campeonato2, Jogador jogador, LocalDate now, StatusInscricao status, Classificacao classificacao) {
        this.campeonato = campeonato2;
        this.jogador = jogador;
        this.dataInscricao = now;
        this.status = status;
        this.participaBrinde = true;
        this.classificacao = classificacao;
    }
}
