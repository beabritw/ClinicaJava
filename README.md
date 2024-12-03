# Sistema de Agendamento de Consultas

Este é um sistema simples em Java de agendamento de consultas, onde é possível cadastrar clientes, agendar, alterar, excluir consultas e gerenciar dados de clientes. O sistema também permite salvar e carregar os dados a partir de um arquivo, garantindo a persistência dos dados entre as execuções.

## Funcionalidades

- **Cadastro de Cliente**: Permite cadastrar um novo cliente com informações como nome, CPF, telefone e endereço.
- **Agendamento de Consulta**: Permite agendar uma nova consulta para um cliente, informando a data e hora, além da especialidade.
- **Alteração de Agendamento**: Permite alterar a data, hora e especialidade de uma consulta agendada.
- **Exclusão de Agendamento**: Permite excluir uma consulta agendada.
- **Exclusão de Cliente**: Exclui um cliente e todas as suas consultas associadas.
- **Salvar e Carregar Dados**: Salva os dados dos clientes e consultas em um arquivo e carrega os dados de um arquivo para restaurar as informações.
- **Impressão de Dados**: Exibe todos os dados cadastrados, tanto de clientes quanto de consultas.

## Tecnologias Utilizadas

- Java 8 ou superior
- Objetos serializados para salvar dados (utilizando `ObjectOutputStream` e `ObjectInputStream`)

## Como Usar

1. **Clonar o Repositório**

   Primeiro, clone este repositório para o seu computador local utilizando o comando:

   ```bash
   git clone https://github.com/beabritw/ClinicaJava.git

   
2. **Interação com o Sistema**

  O sistema irá apresentar um menu onde você poderá escolher uma das opções disponíveis:
  
  **Cadastrar Cliente:** Cadastra um novo cliente no sistema.
  **Agendar Consulta:** Agenda uma nova consulta para um cliente.
  **Alterar Agendamento:** Modifica a data, hora ou especialidade de uma consulta existente.
  **Excluir Agendamento:** Remove uma consulta agendada.
  **Excluir Cadastro de Cliente:** Exclui um cliente e todas as consultas associadas a ele.
  **Salvar Dados:** Salva os dados de clientes e consultas em um arquivo.
  **Carregar Dados:** Carrega os dados a partir de um arquivo previamente salvo.
  **Imprimir Dados:** Exibe todos os dados de clientes e consultas cadastrados.
  **Sair:** Encerra o sistema, salvando os dados antes de sair.
  
  *Ao agendar ou alterar consultas, você deve fornecer a data e hora no formato dd/MM/yyyy HH:mm (ex: 03/12/2024 12:00).*
  *Os dados dos clientes e consultas são salvos em um arquivo binário chamado dados_clinica.ser. Este arquivo será criado no diretório onde o programa foi executado*


## Estrutura do Projeto

**SistemaDeAgendamento.java:** Contém a implementação do sistema de agendamento.
**Cliente.java:** Contém a classe que representa um cliente.
**Consulta.java:** Contém a classe que representa uma consulta agendada.


## Licença
Este projeto é licenciado sob a Licença MIT - veja o arquivo LICENSE para mais detalhes.
