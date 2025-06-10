package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Consulta{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados vai gerar o valor do ID automaticamente
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDateTime dataHora;
    private String especialidade;

    public Consulta(){}
    
    public Consulta(Cliente cliente, LocalDateTime dataHora, String especialidade) {
        this.cliente = cliente;
        this.dataHora = dataHora;
        this.especialidade = especialidade;
    }

    public Long getId(){
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", dataHora=" + dataHora +
                ", especialidade='" + especialidade + '\'' +
                '}';
    }

}
