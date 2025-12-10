package com.lucena.bubble_tea.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class ProdutoResponse {
    private Integer id;
    private String nome;
    private String descricao;
    private BigDecimal precoPequeno;
    private BigDecimal precoMedio;
    private BigDecimal precoGrande;
    private String imagemUrl;


}