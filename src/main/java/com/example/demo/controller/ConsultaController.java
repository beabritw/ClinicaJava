package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.model.Consulta;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/consultas")
public class ConsultaController {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ClienteRepository clienteRepository; // Precisamos dele para associar a consulta a um cliente

   
    @GetMapping
    public List<Consulta> listarTodasAsConsultas() {
        return consultaRepository.findAll();
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Consulta> buscarConsultaPorId(@PathVariable Long id) {
        return consultaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @GetMapping("/cliente/{clienteId}")
    public List<Consulta> listarConsultasPorCliente(@PathVariable Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        return consultaRepository.findByCliente(cliente);
    }


    @PostMapping
    public ResponseEntity<Consulta> agendarConsulta(@RequestBody AgendamentoRequest request) {
        // 1. Busca o cliente no banco de dados usando o ID fornecido na requisição
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado para o agendamento"));

        // 2. Cria a nova entidade Consulta
        Consulta novaConsulta = new Consulta();
        novaConsulta.setCliente(cliente);
        novaConsulta.setDataHora(request.getDataHora());
        novaConsulta.setEspecialidade(request.getEspecialidade());

        // 3. Salva a nova consulta no banco
        Consulta consultaSalva = consultaRepository.save(novaConsulta);

        // 4. Retorna 201 Created com a consulta salva no corpo
        return ResponseEntity.status(HttpStatus.CREATED).body(consultaSalva);
    }
   

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long id) {
        return consultaRepository.findById(id)
                .map(consulta -> {
                    consultaRepository.delete(consulta);
                    return ResponseEntity.noContent().<Void>build(); // Retorna 204 No Content (sucesso, sem corpo)
                }).orElse(ResponseEntity.notFound().build());
    }
}


class AgendamentoRequest {
    private Long clienteId;
    private LocalDateTime dataHora;
    private String especialidade;

    // Getters e Setters
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public LocalDateTime getDataHora() { return dataHora; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}

