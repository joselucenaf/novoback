package com.lucena.bubble_tea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Produto {
                            //Definição das entidade - produto
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 50)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "preco_pequeno", precision = 10, scale = 2)
    private BigDecimal precoPequeno = BigDecimal.valueOf(15.00);

    @Column(name = "preco_medio", precision = 10, scale = 2)
    private BigDecimal precoMedio = BigDecimal.valueOf(17.00);

    @Column(name = "preco_grande", precision = 10, scale = 2)
    private BigDecimal precoGrande = BigDecimal.valueOf(20.00);

    @Column(name = "imagem_url", length = 255)
    private String imagemUrl;
}