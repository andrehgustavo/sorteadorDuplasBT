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
    
    @ManyToOne
    @JoinColumn(name = "id_classificacao", nullable = false)
    private Classificacao classificacao;
   

    @Column(name = "foto", columnDefinition = "bytea")
    private byte[] foto;

    @Column(name = "participa_brinde")
    private boolean participaBrinde = false;

}
