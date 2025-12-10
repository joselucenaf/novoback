package com.lucena.bubble_tea.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EstatisticasResponse {
    private Long totalPedidos;
    private Long pedidosHoje;
    private BigDecimal receitaTotal;
    private BigDecimal receitaHoje;




}