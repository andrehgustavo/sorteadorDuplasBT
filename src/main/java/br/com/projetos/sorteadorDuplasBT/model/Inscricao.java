package br.com.projetos.sorteadorDuplasBT.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "inscricao")
@Data
@AllArgsConstructor
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_campeonato", nullable = false)
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn(name = "id_dupla", nullable = false)
    private Dupla dupla;

    @Column(nullable = false)
    private LocalDate dataInscricao;

    @Enumerated(EnumType.STRING)
    private StatusInscricao status;
    
    
    public Inscricao(Campeonato campeonato2, Dupla dupla2, LocalDate now, StatusInscricao status) {
        this.campeonato = campeonato2;
        this.dupla = dupla2;
        this.dataInscricao = now;
        this.status = status;
    }
}
