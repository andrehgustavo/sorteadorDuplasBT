package br.com.projetos.sorteadorDuplasBT.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "jogador")
public class Jogador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String nome;
   
    @Column(name = "foto_url") 
    private String fotoUrl;

}
