package br.com.projetos.sorteadorDuplasBT.model;

import java.time.LocalDate;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "campeonato")
@Data
public class Campeonato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private LocalDate data;

    private String local;

    @Enumerated(EnumType.STRING)
    private StatusCampeonato status;

    private Integer maxDuplas;
}
