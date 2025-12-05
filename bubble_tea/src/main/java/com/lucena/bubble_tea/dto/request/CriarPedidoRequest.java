package com.lucena.bubble_tea.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public class CriarPedidoRequest {

    @NotBlank(message = "Nome do cliente é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String cliente;

    @NotBlank(message = "Tipo de chá é obrigatório")
    @Pattern(regexp = "^(CHA_VERDE|MANGA_MORANGO|MILK_TEA)$",
            message = "Tipo de chá inválido. Use: CHA_VERDE, MANGA_MORANGO ou MILK_TEA")
    private String tipoCha;

    @NotBlank(message = "Tamanho é obrigatório")
    @Pattern(regexp = "^(PEQUENO|MEDIO|GRANDE)$",
            message = "Tamanho inválido. Use: PEQUENO, MEDIO ou GRANDE")
    private String tamanho;

    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @Size(max = 500, message = "Observações não pode exceder 500 caracteres")
    private String observacoes;

    // Getters e Setters
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
}