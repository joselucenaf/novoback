package com.lucena.bubble_tea.dto.response;

import java.math.BigDecimal;

public class EstatisticasResponse {
    private Long totalPedidos;
    private Long pedidosHoje;
    private BigDecimal receitaTotal;
    private BigDecimal receitaHoje;

    // Construtor
    public EstatisticasResponse(Long totalPedidos, Long pedidosHoje,
                                BigDecimal receitaTotal, BigDecimal receitaHoje) {
        this.totalPedidos = totalPedidos;
        this.pedidosHoje = pedidosHoje;
        this.receitaTotal = receitaTotal;
        this.receitaHoje = receitaHoje;
    }

    // Getters
    public Long getTotalPedidos() { return totalPedidos; }
    public Long getPedidosHoje() { return pedidosHoje; }
    public BigDecimal getReceitaTotal() { return receitaTotal; }
    public BigDecimal getReceitaHoje() { return receitaHoje; }
}