package com.lucena.bubble_tea.dto.request;

import jakarta.validation.constraints.Size;

public class AtualizarPedidoRequest {

    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String cliente;

    @Size(max = 500, message = "Observações não pode exceder 500 caracteres")
    private String observacoes;

    // Getters e Setters
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }
}