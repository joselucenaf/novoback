package com.lucena.bubble_tea.service;

import com.lucena.bubble_tea.dto.response.ProdutoResponse;
import java.util.List;

public interface ProdutoService {

    List<ProdutoResponse> listarTodos();

    ProdutoResponse buscarPorId(Integer id);

    ProdutoResponse buscarPorNome(String nome);

    void inicializarProdutosPadrao();
}