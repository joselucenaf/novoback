package com.lucena.bubble_tea.controller;

import com.lucena.bubble_tea.dto.response.ProdutoResponse;
import com.lucena.bubble_tea.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        List<ProdutoResponse> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Integer id) {
        ProdutoResponse produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProdutoResponse> buscarPorNome(@PathVariable String nome) {
        ProdutoResponse produto = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(produto);
    }
}