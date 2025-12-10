package com.lucena.bubble_tea.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String cliente;

    @Column(name = "tipo_cha", nullable = false, length = 50)
    private String tipoCha;

    @Column(nullable = false, length = 20)
    private String tamanho;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal preco;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPedido status = StatusPedido.PENDENTE;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido = LocalDateTime.now();

    // Construtor simplificado
    public Pedido() {
        this.dataPedido = LocalDateTime.now();
    }
}