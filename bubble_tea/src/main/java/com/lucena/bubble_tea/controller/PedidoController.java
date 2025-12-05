package com.lucena.bubble_tea.controller;

import com.lucena.bubble_tea.dto.request.AtualizarPedidoRequest;
import com.lucena.bubble_tea.dto.request.CriarPedidoRequest;
import com.lucena.bubble_tea.dto.response.EstatisticasResponse;
import com.lucena.bubble_tea.dto.response.PedidoResponse;
import com.lucena.bubble_tea.model.StatusPedido;
import com.lucena.bubble_tea.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controller para gerenciamento de pedidos
 *
 * Endpoints disponíveis:
 * - POST   /api/pedidos          - Criar novo pedido
 * - GET    /api/pedidos          - Listar pedidos (com filtros opcionais)
 * - GET    /api/pedidos/{id}     - Buscar pedido por ID
 * - GET    /api/pedidos/codigo/{idCompra} - Buscar por código
 * - PUT    /api/pedidos/{id}     - Atualizar pedido
 * - PATCH  /api/pedidos/{id}/status - Atualizar status
 * - DELETE /api/pedidos/{id}     - Excluir pedido
 * - GET    /api/pedidos/estatisticas - Obter estatísticas
 * - GET    /api/pedidos/status/{status} - Listar por status
 */
@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    /**
     * Cria um novo pedido
     *
     * @param request Dados do pedido a ser criado
     * @return Pedido criado com status 201
     */
    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(
            @Valid @RequestBody CriarPedidoRequest request) {
        PedidoResponse response = pedidoService.criarPedido(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Lista todos os pedidos com filtros opcionais
     *
     * @param cliente Filtro por nome do cliente (opcional)
     * @param status Filtro por status do pedido (opcional)
     * @param data Filtro por data de criação (opcional, formato: yyyy-MM-dd)
     * @return Lista de pedidos filtrados
     */
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarPedidos(
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) StatusPedido status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        List<PedidoResponse> pedidos = pedidoService.listarComFiltros(cliente, status, data);
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Busca um pedido pelo ID
     *
     * @param id ID do pedido
     * @return Pedido encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id) {
        PedidoResponse response = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    /**
     * Busca um pedido pelo código de compra (ID Compra)
     *
     * @param idCompra Código do pedido (ex: BTS000124)
     * @return Pedido encontrado
     */
    @GetMapping("/codigo/{idCompra}")
    public ResponseEntity<PedidoResponse> buscarPorIdCompra(@PathVariable String idCompra) {
        PedidoResponse response = pedidoService.buscarPorIdCompra(idCompra);
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza os dados de um pedido existente
     *
     * @param id ID do pedido a ser atualizado
     * @param request Dados para atualização
     * @return Pedido atualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> atualizarPedido(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarPedidoRequest request) {
        PedidoResponse response = pedidoService.atualizarPedido(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * Atualiza apenas o status de um pedido
     *
     * @param id ID do pedido
     * @param novoStatus Novo status a ser definido
     * @return Pedido atualizado
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponse> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido novoStatus) {
        PedidoResponse response = pedidoService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(response);
    }

    /**
     * Exclui um pedido
     *
     * @param id ID do pedido a ser excluído
     * @return Status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Obtém estatísticas do sistema
     *
     * @return Estatísticas com total de pedidos, pedidos hoje, receitas
     */
    @GetMapping("/estatisticas")
    public ResponseEntity<EstatisticasResponse> obterEstatisticas() {
        EstatisticasResponse estatisticas = pedidoService.obterEstatisticas();
        return ResponseEntity.ok(estatisticas);
    }

    /**
     * Lista pedidos por status específico
     *
     * @param status Status para filtrar
     * @return Lista de pedidos com o status especificado
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<PedidoResponse>> listarPorStatus(
            @PathVariable StatusPedido status) {
        List<PedidoResponse> pedidos = pedidoService.listarPorStatus(status);
        return ResponseEntity.ok(pedidos);
    }

    /**
     * Lista todos os pedidos (sem filtros)
     *
     * @return Todos os pedidos cadastrados
     */
    @GetMapping("/todos")
    public ResponseEntity<List<PedidoResponse>> listarTodos() {
        List<PedidoResponse> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }

}