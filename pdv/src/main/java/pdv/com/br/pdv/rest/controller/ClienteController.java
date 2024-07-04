package pdv.com.br.pdv.rest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pdv.com.br.pdv.domain.entity.Cliente;
import pdv.com.br.pdv.domain.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {


    private ClienteRepository clientes;

    public ClienteController(ClienteRepository clientes) {
        this.clientes = clientes;
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Long id) {
        return clientes
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@Valid @RequestBody Cliente cliente) {
        return clientes.save(cliente);
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updade(@Valid @RequestBody Cliente cliente, @PathVariable Long id) {

        clientes
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }
}
