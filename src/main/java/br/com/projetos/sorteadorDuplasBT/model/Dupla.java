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
    @JoinColumn(name = "id_jogador_1", nullable = false)
    private Jogador jogador1;

    @ManyToOne
    @JoinColumn(name = "id_jogador_2", nullable = false)
    private Jogador jogador2;

    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Dupla(Jogador jogador1, Jogador jogador2) {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.timestamp = new Date();
    }
}

