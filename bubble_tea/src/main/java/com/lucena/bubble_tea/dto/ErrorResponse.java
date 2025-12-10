package com.lucena.bubble_tea.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private String message;
    private String path;

    // Construtor para uso comum
    public ErrorResponse(int status, String error, String message, String path) {
        this(); // Chama o construtor sem argumentos que inicializa o timestamp
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }
}