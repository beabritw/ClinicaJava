package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cliente;
import com.example.demo.model.Consulta;


@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long>{
        List<Consulta> findByCliente(Cliente cliente);
        List<Consulta> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
        List<Consulta> findByEspecialidade(String especialidade);
}
