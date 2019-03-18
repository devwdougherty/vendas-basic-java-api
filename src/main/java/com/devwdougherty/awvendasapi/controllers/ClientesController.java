package com.devwdougherty.awvendasapi.controllers;

import com.devwdougherty.awvendasapi.entities.Cliente;
import com.devwdougherty.awvendasapi.entities.Produto;
import com.devwdougherty.awvendasapi.repositories.ClienteRepository;
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
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> getClients() {
        return new ResponseEntity(clienteRepository.findAll(), HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(value = {"/{id}"})
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {

        Cliente persistedCliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente not found"));

        return new ResponseEntity<>(persistedCliente, HttpStatus.OK);
    }

    /**
     *
     * @param cliente
     * @return
     */
    @PostMapping
    public ResponseEntity<Cliente> createClient(@Valid @RequestBody Cliente cliente) {

       clienteRepository.save(cliente);

       return new ResponseEntity(cliente, HttpStatus.OK);
    }

    /**
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id) {

        Cliente persistedCliente = clienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cliente not found"));

        clienteRepository.delete(persistedCliente);

        return new ResponseEntity<>(persistedCliente, HttpStatus.OK);
    }
}
