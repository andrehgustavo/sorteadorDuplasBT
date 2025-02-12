package br.com.projetos.sorteadorDuplasBT.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "classificacao")
@Data
public class Classificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true)
    private String descricao;
    
    @Column(nullable = false)
    private int ordem;
}
