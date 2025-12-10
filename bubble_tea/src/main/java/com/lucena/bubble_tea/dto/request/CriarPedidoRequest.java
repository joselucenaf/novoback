package com.lucena.bubble_tea.dto.request;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
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

}