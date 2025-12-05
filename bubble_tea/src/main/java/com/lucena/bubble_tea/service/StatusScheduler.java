package com.lucena.bubble_tea.service;

import com.lucena.bubble_tea.model.Pedido;
import com.lucena.bubble_tea.model.StatusPedido;
import com.lucena.bubble_tea.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@EnableAsync
public class StatusScheduler {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoService pedidoService;

    /**
     * A cada minuto, verifica e atualiza status dos pedidos automaticamente
     * Fluxo: PENDENTE → PREPARANDO → PRONTO → ENTREGUE
     * Cada etapa dura 2 minutos
     */
    @Async
    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
    public void atualizarStatusAutomaticamente() {
        List<Pedido> pedidosAtivos = pedidoRepository.findAll();

        for (Pedido pedido : pedidosAtivos) {
            if (pedido.getStatus() != StatusPedido.ENTREGUE &&
                    pedido.getStatus() != StatusPedido.CANCELADO) {

                long minutosDesdeCriacao = calcularMinutosDesde(pedido.getDataCriacao());

                // Regras de transição automática
                if (pedido.getStatus() == StatusPedido.PENDENTE && minutosDesdeCriacao >= 2) {
                    pedidoService.atualizarStatus(pedido.getId(), StatusPedido.PREPARANDO);
                    System.out.println("Pedido " + pedido.getIdCompra() + " mudou para PREPARANDO automaticamente");
                }
                else if (pedido.getStatus() == StatusPedido.PREPARANDO && minutosDesdeCriacao >= 4) {
                    pedidoService.atualizarStatus(pedido.getId(), StatusPedido.PRONTO);
                    System.out.println("Pedido " + pedido.getIdCompra() + " mudou para PRONTO automaticamente");
                }
                else if (pedido.getStatus() == StatusPedido.PRONTO && minutosDesdeCriacao >= 6) {
                    pedidoService.atualizarStatus(pedido.getId(), StatusPedido.ENTREGUE);
                    System.out.println("Pedido " + pedido.getIdCompra() + " mudou para ENTREGUE automaticamente");
                }
            }
        }
    }

    private long calcularMinutosDesde(LocalDateTime data) {
        return java.time.Duration.between(data, LocalDateTime.now()).toMinutes();
    }

    /**
     * Método para iniciar temporizador para um pedido específico
     * Útil quando o usuário quer iniciar o fluxo manualmente
     */
    @Async
    public void iniciarFluxoAutomatico(Long pedidoId) {
        try {
            System.out.println("Iniciando fluxo automático para pedido ID: " + pedidoId);

            // Etapa 1: PENDENTE → PREPARANDO (2 minutos)
            TimeUnit.MINUTES.sleep(2);
            pedidoService.atualizarStatus(pedidoId, StatusPedido.PREPARANDO);
            System.out.println("→ Preparando...");

            // Etapa 2: PREPARANDO → PRONTO (2 minutos)
            TimeUnit.MINUTES.sleep(2);
            pedidoService.atualizarStatus(pedidoId, StatusPedido.PRONTO);
            System.out.println("→ Pronto!");

            // Etapa 3: PRONTO → ENTREGUE (2 minutos)
            TimeUnit.MINUTES.sleep(2);
            pedidoService.atualizarStatus(pedidoId, StatusPedido.ENTREGUE);
            System.out.println("✓ Entregue!");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Fluxo automático interrompido para pedido: " + pedidoId);
        }
    }
}