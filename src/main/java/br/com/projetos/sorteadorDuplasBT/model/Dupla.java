package br.com.projetos.sorteadorDuplasBT.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dupla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_campeonato", nullable = false)
    private Campeonato campeonato;

    @ManyToOne
    @JoinColumn(name = "id_inscricao_1", nullable = false)
    private Inscricao inscricao1;

    @ManyToOne
    @JoinColumn(name = "id_inscricao_2", nullable = false)
    private Inscricao inscricao2;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Dupla(Inscricao inscricao1, Inscricao inscricao2) {
        this.inscricao1 = inscricao1;
        this.inscricao2 = inscricao2;
        this.timestamp = new Date();
    }

    public Dupla(Campeonato campeonato, Inscricao inscricao1, Inscricao inscricao2) {
        this.campeonato = campeonato;
        this.inscricao1 = inscricao1;
        this.inscricao2 = inscricao2;
        this.timestamp = new Date();
    }
}

