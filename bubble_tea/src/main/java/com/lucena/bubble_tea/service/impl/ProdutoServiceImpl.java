package com.lucena.bubble_tea.service.impl;

import com.lucena.bubble_tea.dto.response.ProdutoResponse;
import com.lucena.bubble_tea.exception.ResourceNotFoundException;
import com.lucena.bubble_tea.model.Produto;
import com.lucena.bubble_tea.repository.ProdutoRepository;
import com.lucena.bubble_tea.service.ProdutoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponse> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProdutoResponse buscarPorId(Integer id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", Long.valueOf(id)));
        return converterParaResponse(produto);
    }

    @Override
    @Transactional(readOnly = true)
    public ProdutoResponse buscarPorNome(String nome) {
        Produto produto = produtoRepository.findByNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Produto", nome));
        return converterParaResponse(produto);
    }

    @PostConstruct
    @Transactional
    public void inicializarProdutosPadrao() {
        // Verificar se já existem produtos
        if (produtoRepository.count() == 0) {
            criarProdutoPadrao("Chá Verde",
                    "Chá verde tradicional com pearls de tapioca.",
                    "chaVerde.png");

            criarProdutoPadrao("Manga Morango",
                    "Combinação tropical de manga e morango com pearls de fruta.",
                    "mangaMorango.png");

            criarProdutoPadrao("Milk Tea",
                    "O clássico bubble tea com chá preto, leite e pearls de tapioca.",
                    "milkTea.png");

            System.out.println("Produtos padrão inicializados com sucesso!");
        }
    }

    private void criarProdutoPadrao(String nome, String descricao, String imagem) {
        if (!produtoRepository.existsByNome(nome)) {
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setPrecoPequeno(BigDecimal.valueOf(15.00));
            produto.setPrecoMedio(BigDecimal.valueOf(17.00));
            produto.setPrecoGrande(BigDecimal.valueOf(20.00));
            produto.setImagemUrl("/assets/" + imagem);
            produtoRepository.save(produto);
        }
    }

    private ProdutoResponse converterParaResponse(Produto produto) {
        ProdutoResponse response = new ProdutoResponse();
        response.setId(produto.getId());
        response.setNome(produto.getNome());
        response.setDescricao(produto.getDescricao());
        response.setPrecoPequeno(produto.getPrecoPequeno());
        response.setPrecoMedio(produto.getPrecoMedio());
        response.setPrecoGrande(produto.getPrecoGrande());
        response.setImagemUrl(produto.getImagemUrl());
        return response;
    }
}