package br.com.projetos.sorteadorDuplasBT.model;

import java.time.LocalDate;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "campeonato")
@Data
@NoArgsConstructor
@AllArgsConstructor
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
