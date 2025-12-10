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
                                            //endpoints para o teste no postman
@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoResponse> criarPedido(
            @Valid @RequestBody CriarPedidoRequest request) {
        PedidoResponse response = pedidoService.criarPedido(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarPedidos(
            @RequestParam(required = false) String cliente,
            @RequestParam(required = false) StatusPedido status,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        List<PedidoResponse> pedidos = pedidoService.listarComFiltros(cliente, status, data);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponse> buscarPorId(@PathVariable Long id) {
        PedidoResponse response = pedidoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponse> atualizarPedido(
            @PathVariable Long id,
            @Valid @RequestBody AtualizarPedidoRequest request) {
        PedidoResponse response = pedidoService.atualizarPedido(id, request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PedidoResponse> atualizarStatus(
            @PathVariable Long id,
            @RequestParam StatusPedido novoStatus) {
        PedidoResponse response = pedidoService.atualizarStatus(id, novoStatus);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPedido(@PathVariable Long id) {
        pedidoService.excluirPedido(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/estatisticas")
    public ResponseEntity<EstatisticasResponse> obterEstatisticas() {
        EstatisticasResponse estatisticas = pedidoService.obterEstatisticas();
        return ResponseEntity.ok(estatisticas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<PedidoResponse>> listarPorStatus(
            @PathVariable StatusPedido status) {
        List<PedidoResponse> pedidos = pedidoService.listarPorStatus(status);
        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<PedidoResponse>> listarTodos() {
        List<PedidoResponse> pedidos = pedidoService.listarTodos();
        return ResponseEntity.ok(pedidos);
    }
}