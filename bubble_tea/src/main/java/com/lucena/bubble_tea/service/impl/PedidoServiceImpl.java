package com.lucena.bubble_tea.service.impl;

import com.lucena.bubble_tea.dto.request.AtualizarPedidoRequest;
import com.lucena.bubble_tea.dto.request.CriarPedidoRequest;
import com.lucena.bubble_tea.dto.response.EstatisticasResponse;
import com.lucena.bubble_tea.dto.response.PedidoResponse;
import com.lucena.bubble_tea.exception.BusinessException;
import com.lucena.bubble_tea.exception.ResourceNotFoundException;
import com.lucena.bubble_tea.model.Pedido;
import com.lucena.bubble_tea.model.StatusPedido;
import com.lucena.bubble_tea.repository.PedidoRepository;
import com.lucena.bubble_tea.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public PedidoResponse criarPedido(CriarPedidoRequest request) { //Min. 2 caracteres para o nome do cliente
        if (request.getCliente().trim().length() < 2) {
            throw new BusinessException("Nome do cliente deve ter pelo menos 2 caracteres");
        }

        // Criar pedido (sem gerar idCompra)
        Pedido pedido = new Pedido();
        pedido.setCliente(request.getCliente().trim());
        pedido.setTipoCha(request.getTipoCha());
        pedido.setTamanho(request.getTamanho());

        // Calcular preço baseado no tamanho
        BigDecimal preco = calcularPreco(request.getTamanho());
        pedido.setPreco(preco);

        pedido.setObservacoes(request.getObservacoes());
        pedido.setStatus(StatusPedido.PENDENTE);
        pedido.setDataPedido(LocalDateTime.now());

        Pedido salvo = pedidoRepository.save(pedido);
        return converterParaResponse(salvo);
    }

    private BigDecimal calcularPreco(String tamanho) {
        return switch (tamanho.toUpperCase()) {
            case "PEQUENO" -> BigDecimal.valueOf(15.00);
            case "MEDIO" -> BigDecimal.valueOf(17.00);
            case "GRANDE" -> BigDecimal.valueOf(20.00);
            default -> BigDecimal.ZERO;
        };
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponse> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAllByOrderByDataPedidoDesc();
        return pedidos.stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponse> listarComFiltros(String cliente, StatusPedido status, LocalDate data) {
        if (cliente == null && status == null && data == null) {
            return listarTodos();
        }

        List<Pedido> todosPedidos = pedidoRepository.findAllByOrderByDataPedidoDesc();

        return todosPedidos.stream()
                .filter(p -> cliente == null || p.getCliente().toLowerCase().contains(cliente.toLowerCase()))
                .filter(p -> status == null || p.getStatus() == status)
                .filter(p -> data == null || p.getDataPedido().toLocalDate().equals(data))
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoResponse buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));
        return converterParaResponse(pedido);
    }

    @Override
    @Transactional
    public PedidoResponse atualizarPedido(Long id, AtualizarPedidoRequest request) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));

        if (request.getCliente() != null && !request.getCliente().trim().isEmpty()) {
            pedido.setCliente(request.getCliente().trim());
        }

        if (request.getTipoCha() != null && !request.getTipoCha().isEmpty()) {
            pedido.setTipoCha(request.getTipoCha());
        }

        if (request.getTamanho() != null && !request.getTamanho().isEmpty()) {
            pedido.setTamanho(request.getTamanho());
            pedido.setPreco(calcularPreco(request.getTamanho()));
        }

        if (request.getObservacoes() != null) {
            pedido.setObservacoes(request.getObservacoes());
        }

        Pedido atualizado = pedidoRepository.save(pedido);
        return converterParaResponse(atualizado);
    }

    @Override
    @Transactional
    public PedidoResponse atualizarStatus(Long id, StatusPedido novoStatus) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido", id));

        pedido.setStatus(novoStatus);
        Pedido atualizado = pedidoRepository.save(pedido);
        return converterParaResponse(atualizado);
    }

    @Override
    @Transactional
    public void excluirPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pedido", id);
        }
        pedidoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public EstatisticasResponse obterEstatisticas() {
        Long totalPedidos = pedidoRepository.count();       //pedidos com a data de hoje

        LocalDateTime inicioHoje = LocalDateTime.now().with(LocalTime.MIN);
        LocalDateTime fimHoje = LocalDateTime.now().with(LocalTime.MAX);
        Long pedidosHoje = pedidoRepository.countByDataPedidoBetween(inicioHoje, fimHoje);


        BigDecimal receitaTotal = BigDecimal.ZERO;  //receita total
        List<Pedido> todosPedidos = pedidoRepository.findAll();
        for (Pedido pedido : todosPedidos) {
            if (pedido.getPreco() != null) {
                receitaTotal = receitaTotal.add(pedido.getPreco());
            }
        }

        BigDecimal receitaHoje = BigDecimal.ZERO;
        List<Pedido> pedidosHojeList = pedidoRepository.findByDataPedidoBetween(inicioHoje, fimHoje);
        for (Pedido pedido : pedidosHojeList) {
            if (pedido.getPreco() != null) {
                receitaHoje = receitaHoje.add(pedido.getPreco());
            }
        }

        return new EstatisticasResponse(totalPedidos, pedidosHoje, receitaTotal, receitaHoje);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponse> listarPorStatus(StatusPedido status) {
        List<Pedido> pedidos = pedidoRepository.findByStatus(status);
        return pedidos.stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    private PedidoResponse converterParaResponse(Pedido pedido) {
        PedidoResponse response = new PedidoResponse();
        response.setId(pedido.getId());
        response.setCliente(pedido.getCliente());
        response.setTipoCha(pedido.getTipoCha());
        response.setTamanho(pedido.getTamanho());
        response.setPreco(pedido.getPreco());
        response.setObservacoes(pedido.getObservacoes());
        response.setStatus(pedido.getStatus());
        response.setDataPedido(pedido.getDataPedido());
        return response;
    }
}