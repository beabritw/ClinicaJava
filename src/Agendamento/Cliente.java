package Agendamento;

import java.io.*;

class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;


    public Cliente(String nome, String cpf, String telefone, String endereco) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
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