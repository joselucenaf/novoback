package com.lucena.bubble_tea.service;

import com.lucena.bubble_tea.dto.request.AtualizarPedidoRequest;
import com.lucena.bubble_tea.dto.request.CriarPedidoRequest;
import com.lucena.bubble_tea.dto.response.EstatisticasResponse;
import com.lucena.bubble_tea.dto.response.PedidoResponse;
import com.lucena.bubble_tea.model.StatusPedido;

import java.time.LocalDate;
import java.util.List;

public interface PedidoService {

    PedidoResponse criarPedido(CriarPedidoRequest request);

    List<PedidoResponse> listarTodos();

    List<PedidoResponse> listarComFiltros(String cliente, StatusPedido status, LocalDate data);

    PedidoResponse buscarPorId(Long id);

    PedidoResponse buscarPorIdCompra(String idCompra);

    PedidoResponse atualizarPedido(Long id, AtualizarPedidoRequest request);

    PedidoResponse atualizarStatus(Long id, StatusPedido novoStatus);

    void excluirPedido(Long id);

    EstatisticasResponse obterEstatisticas();

    List<PedidoResponse> listarPorStatus(StatusPedido status);
}