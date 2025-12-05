package com.lucena.bubble_tea.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
public class Produto {

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

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public BigDecimal getPrecoPequeno() { return precoPequeno; }
    public void setPrecoPequeno(BigDecimal precoPequeno) { this.precoPequeno = precoPequeno; }

    public BigDecimal getPrecoMedio() { return precoMedio; }
    public void setPrecoMedio(BigDecimal precoMedio) { this.precoMedio = precoMedio; }

    public BigDecimal getPrecoGrande() { return precoGrande; }
    public void setPrecoGrande(BigDecimal precoGrande) { this.precoGrande = precoGrande; }

    public String getImagemUrl() { return imagemUrl; }
    public void setImagemUrl(String imagemUrl) { this.imagemUrl = imagemUrl; }
}