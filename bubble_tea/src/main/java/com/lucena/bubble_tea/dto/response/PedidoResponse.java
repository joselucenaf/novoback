package com.lucena.bubble_tea.dto.response;

import com.lucena.bubble_tea.model.StatusPedido;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PedidoResponse {
    private Long id;
    private String idCompra;
    private String cliente;
    private String tipoCha;
    private String tamanho;
    private BigDecimal preco;
    private String observacoes;
    private StatusPedido status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    // Construtor
    public PedidoResponse() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdCompra() { return idCompra; }
    public void setIdCompra(String idCompra) { this.idCompra = idCompra; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getTipoCha() { return tipoCha; }
    public void setTipoCha(String tipoCha) { this.tipoCha = tipoCha; }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public StatusPedido getStatus() { return status; }
    public void setStatus(StatusPedido status) { this.status = status; }

    public LocalDateTime getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(LocalDateTime dataCriacao) { this.dataCriacao = dataCriacao; }

    public LocalDateTime getDataAtualizacao() { return dataAtualizacao; }
    public void setDataAtualizacao(LocalDateTime dataAtualizacao) { this.dataAtualizacao = dataAtualizacao; }
}