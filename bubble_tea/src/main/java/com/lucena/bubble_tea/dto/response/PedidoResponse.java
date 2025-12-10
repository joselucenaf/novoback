package com.lucena.bubble_tea.dto.response;

import com.lucena.bubble_tea.model.StatusPedido;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PedidoResponse {
    private Long id;
    private String cliente;
    private String tipoCha;
    private String tamanho;
    private BigDecimal preco;
    private String observacoes;
    private StatusPedido status;
    private LocalDateTime dataPedido;
}