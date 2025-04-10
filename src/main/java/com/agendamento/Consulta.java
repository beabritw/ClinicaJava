package com.agendamento;

import java.io.*;
import java.time.LocalDateTime;

class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;
    private Cliente cliente;
    private LocalDateTime dataHora;
    private String especialidade;

    public Consulta(Cliente cliente, LocalDateTime dataHora, String especialidade) {
        this.cliente = cliente;
        this.dataHora = dataHora;
        this.especialidade = especialidade;
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
        return "Consulta{" + "cliente=" + cliente + ", dataHora=" + dataHora + ", especialidade='" + especialidade + '\'' + '}';
    }

}
