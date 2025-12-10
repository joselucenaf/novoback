package com.lucena.bubble_tea.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarPedidoRequest {

    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String cliente;

    @Pattern(regexp = "^(CHA_VERDE|MANGA_MORANGO|MILK_TEA)?$",
            message = "Tipo de chá inválido. Use: CHA_VERDE, MANGA_MORANGO ou MILK_TEA")
    private String tipoCha;

    @Pattern(regexp = "^(PEQUENO|MEDIO|GRANDE)?$",
            message = "Tamanho inválido. Use: PEQUENO, MEDIO ou GRANDE")
    private String tamanho;

    @Size(max = 500, message = "Observações não pode exceder 500 caracteres")
    private String observacoes;

}