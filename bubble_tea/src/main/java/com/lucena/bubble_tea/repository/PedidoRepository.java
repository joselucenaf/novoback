package com.lucena.bubble_tea.repository;

import com.lucena.bubble_tea.model.Pedido;
import com.lucena.bubble_tea.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Optional<Pedido> findByIdCompra(String idCompra);

    List<Pedido> findByStatus(StatusPedido status);

    List<Pedido> findByClienteContainingIgnoreCase(String cliente);

    // Métodos para ordenação
    List<Pedido> findAllByOrderByDataCriacaoDesc();

    // Métodos para estatísticas
    Long countByDataCriacaoBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Pedido> findByDataCriacaoBetween(LocalDateTime inicio, LocalDateTime fim);

    boolean existsByIdCompra(String idCompra);
}