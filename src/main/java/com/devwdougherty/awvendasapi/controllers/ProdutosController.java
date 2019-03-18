package com.devwdougherty.awvendasapi.controllers;

import com.devwdougherty.awvendasapi.entities.Produto;
import com.devwdougherty.awvendasapi.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

/**
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/produtos")
public class ProdutosController {

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     *
     * @return
     */
    @GetMapping
    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Produto> getProdutoById(@PathVariable Long id) {

        Produto persistedProduto = produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto not found"));

        return new ResponseEntity<>(persistedProduto, HttpStatus.OK);
    }

    /**
     *
     * @param produto
     * @return
     */
    @PostMapping
    public ResponseEntity<Produto> createProduto(@Valid @RequestBody Produto produto) {

        produtoRepository.save(produto);

        return new ResponseEntity(produto, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Produto> deleteProduto(@PathVariable Long id) {

        Produto persistedProduto = produtoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Produto not found"));

        produtoRepository.delete(persistedProduto);

        return new ResponseEntity<>(persistedProduto, HttpStatus.OK);
    }

}
