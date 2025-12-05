package com.lucena.bubble_tea.controller;

import com.lucena.bubble_tea.dto.response.ProdutoResponse;
import com.lucena.bubble_tea.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller para gerenciamento de produtos
 *
 * Endpoints disponíveis:
 * - GET /api/produtos       - Listar todos os produtos
 * - GET /api/produtos/{id}  - Buscar produto por ID
 * - GET /api/produtos/nome/{nome} - Buscar produto por nome
 */
@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "${cors.allowed-origins}")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    /**
     * Lista todos os produtos disponíveis
     *
     * @return Lista de produtos
     */
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        List<ProdutoResponse> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    /**
     * Busca um produto pelo ID
     *
     * @param id ID do produto
     * @return Produto encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Integer id) {
        ProdutoResponse produto = produtoService.buscarPorId(id);
        return ResponseEntity.ok(produto);
    }

    /**
     * Busca um produto pelo nome
     *
     * @param nome Nome do produto
     * @return Produto encontrado
     */
    @GetMapping("/nome/{nome}")
    public ResponseEntity<ProdutoResponse> buscarPorNome(@PathVariable String nome) {
        ProdutoResponse produto = produtoService.buscarPorNome(nome);
        return ResponseEntity.ok(produto);
    }
}