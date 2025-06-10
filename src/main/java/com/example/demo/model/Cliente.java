package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // Diz que esta classe representa uma tabela no banco de dados
public class Cliente {

    @Id // Marca o campo 'id' como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // O banco de dados vai gerar o valor do ID automaticamente
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;

    // Construtor vazio é necessário para o JPA
    public Cliente() {}

    public Cliente(String nome, String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        //return "Cliente (" + "Nome:'" + nome + '\'' + ", CPF:'" + cpf + '\'' + ", Telefone:'" + telefone + '\'' + ", Endereco:'" + endereco + '\'' + ')';
      return "\nCliente"+"    Nome: " +nome+ "';\n    CPF: '" +cpf+ "';\n    Telefone: '" +telefone+ "';\n    Endereço: '" +endereco+ "'.";
    }

}
  