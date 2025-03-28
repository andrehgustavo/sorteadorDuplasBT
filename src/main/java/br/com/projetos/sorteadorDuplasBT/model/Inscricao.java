package br.com.projetos.sorteadorDuplasBT.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inscricao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inscricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "campeonato_id", nullable = false)
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn(name = "dupla_id", nullable = false)
    private Dupla dupla;

    @Column(nullable = false)
    private LocalDate dataInscricao;

    @Enumerated(EnumType.STRING)
    private StatusInscricao status;
}
