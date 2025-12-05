package com.lucena.bubble_tea.repository;

import com.lucena.bubble_tea.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByNome(String nome);

    boolean existsByNome(String nome);
}