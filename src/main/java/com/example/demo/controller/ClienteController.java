package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Define que esta classe é um controller REST
@RequestMapping("/api/clientes") // Todas as requisições para /api/clientes virão para esta classe
public class ClienteController {

    @Autowired // O Spring vai injetar automaticamente uma instância do ClienteRepository aqui
    private ClienteRepository clienteRepository;

    // READ - Listar todos os clientes
    // Requisição: GET /api/clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // READ - Buscar um cliente por ID
    // Requisição: GET /api/clientes/1
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com o cliente
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }

    // CREATE - Cadastrar um novo cliente
    // Requisição: POST /api/clientes com os dados do cliente no corpo da requisição
    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // UPDATE - Atualizar um cliente existente
    // Requisição: PUT /api/clientes/1 com os novos dados no corpo
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteDetalhes) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {
                    clienteExistente.setNome(clienteDetalhes.getNome());
                    clienteExistente.setCpf(clienteDetalhes.getCpf());
                    clienteExistente.setTelefone(clienteDetalhes.getTelefone());
                    clienteExistente.setEndereco(clienteDetalhes.getEndereco());
                    Cliente atualizado = clienteRepository.save(clienteExistente);
                    return ResponseEntity.ok(atualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Excluir um cliente
    // Requisição: DELETE /api/clientes/1
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    clienteRepository.delete(cliente);
                    return ResponseEntity.ok().build(); // Retorna 200 OK sem corpo
                }).orElse(ResponseEntity.notFound().build());
    }
}
 