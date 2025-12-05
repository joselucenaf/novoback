package com.lucena.bubble_tea.dto.response;

import java.math.BigDecimal;

public class ProdutoResponse {
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal precoPequeno;
    private BigDecimal precoMedio;
    private BigDecimal precoGrande;
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