package com.devwdougherty.awvendasapi.controllers;

import com.devwdougherty.awvendasapi.entities.Venda;
import com.devwdougherty.awvendasapi.repositories.VendaRepository;
import com.devwdougherty.awvendasapi.services.VendasService;
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
@RequestMapping("/vendas")
public class VendasController {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendasService vendasService;

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Venda>> getVendas() {
        return new ResponseEntity(vendaRepository.findAll(), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Venda> getVendaById(@PathVariable Long id) {

        Venda persistedVenda = vendaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Venda not found"));

        return new ResponseEntity<>(persistedVenda, HttpStatus.OK);
    }

    /**
     *
     * @param venda
     * @return
     */
    @PostMapping
    public ResponseEntity<Venda> createVenda(@Valid @RequestBody Venda venda) {

        vendasService.addVenda(venda);

        return new ResponseEntity(venda, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Venda> deleteVenda(@PathVariable Long id) {

        Venda persistedVenda = vendaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Venda not found"));

        vendaRepository.delete(persistedVenda);

        return new ResponseEntity<>(persistedVenda, HttpStatus.OK);
    }
    
}
