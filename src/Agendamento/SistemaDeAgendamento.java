package Agendamento;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaDeAgendamento {
    private List<Cliente> clientes;
    private List<Consulta> consultas;
    private transient Scanner scanner;

  // construtor
    public SistemaDeAgendamento() {
        clientes = new ArrayList<Cliente>();
        consultas = new ArrayList<Consulta>();
        scanner = new Scanner(System.in);
    }
    
    
    //===============================================================================================


    // cadastro de cliente
    public void cadastrarCliente() {
        System.out.println("Digite o nome do cliente:");
        String nome = scanner.nextLine();
        String cpf;
        while(true) {
          System.out.println("Digite o CPF do cliente:");
          cpf = scanner.nextLine();
          if (validarCPF(cpf)) break;
          else System.out.println("CPF inválido. Tente novamente.");
        }
        String telefone;
        while(true) {
        	System.out.println("Digite o telefone do cliente:");
        	telefone = scanner.nextLine();
        	if(validarTelefone(telefone)) break;
        }
        System.out.println("Digite o endereço do cliente:");
        String endereco = scanner.nextLine();

        Cliente cliente = new Cliente(nome, cpf, telefone, endereco);
        clientes.add(cliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }
    
    //===============================================================================================

    private boolean validarTelefone(String telefone) {
    	if(!telefone.trim().matches("\\d{11}")){
    		System.out.println("Telefone inserido de forma incorreta, tente novamente!");
    		if(telefone.trim().matches("\\d{9}")){
        		System.out.println("Verifique se o DDD esteja inserido de forma correta");
        	}
    	}
   		return telefone.trim().matches("\\d{11}");
    }

    // agendamento de consulta
    public void agendarConsulta() {
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.nextLine();
        Cliente cliente = buscarClientePorCpf(cpf); // busca cliente armazenados numa lista de clientes
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return; // volta para o ponto que o metodo foi chamado (retorna pro menu
        }
        // solicitar a data e hora da consulta
        System.out.println("Digite a data da consulta (dd/MM/yyyy HH:mm):");
        String dataHoraStr = scanner.nextLine(); // recebe em formato de string
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")); // retrona em forma de objeto LocalDateTime que realiza operacoes com a data e hora 

        System.out.println("Digite a especialidade da consulta:");
        String especialidade = especialidade();
        

        Consulta consulta = new Consulta(cliente, dataHora, especialidade); // instancia o objeto
        consultas.add(consulta); // adiciona a consulta inserida na lista de consultas
        System.out.println("Consulta agendada com sucesso!");
    }
    
    //===============================================================================================

    
    public void alterarConsulta() {
	        System.out.println("Digite o CPF do cliente:");
	        String cpf = scanner.nextLine();
	        
	        Cliente cliente = buscarClientePorCpf(cpf);
	        
	        if (cliente == null) {
	            System.out.println("Cliente não encontrado!"); 
	            return;
	        }
	
        	// converte para LocalDateTime para o java entender como data e hora
	        LocalDateTime dataHora = null;
	        while (dataHora == null) {
	            System.out.println("Digite a data da consulta a ser alterada (dd/MM/yyyy HH:mm):");
	            String dataHoraStr = scanner.nextLine();
	
	            try {
	                dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	            } catch (DateTimeParseException e) { // excecao caso o formato inserido esteja errado
	                System.out.println("Formato de data e hora inválido. Tente novamente.");
	                // Continua no loop até o usuário inserir uma data válida
	            }
	        }
	
	        Consulta consulta = buscarConsultaPorClienteEData(cliente, dataHora);
	        if (consulta == null) {
	            System.out.println("Consulta não encontrada!");
	            return;
	        }
	
	        
        	// definindo novo horario
	        System.out.println("Consulta encontrada! Digite a nova data (dd/MM/yyyy HH:mm):");
	        LocalDateTime novaData = null;
	        while (novaData == null) {
	            String novaDataStr = scanner.nextLine();
	            try {
	                novaData = LocalDateTime.parse(novaDataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
	            } catch (DateTimeParseException e) {
	                System.out.println("Formato de data e hora inválido. Tente novamente.");
	            }
	        }
	
	        consulta.setDataHora(novaData);
	        System.out.println("Consulta alterada com sucesso para: " + novaData.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
	    	
	        System.out.println("Digite a nova especialidade da consulta:");
	        String novaEspecialidade = especialidade();
	        consulta.setEspecialidade(novaEspecialidade);
	        System.out.println("Consulta alterada com sucesso!");
    }
    
    //===============================================================================================
  
    // excluir agendamento
    public void excluirConsulta() {
        System.out.println("Digite o CPF do cliente:");
        String cpf = scanner.nextLine();
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.println("Digite a data da consulta a ser excluída (dd/MM/yyyy HH:mm):");
        String dataHoraStr = scanner.nextLine();
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));

        Consulta consulta = buscarConsultaPorClienteEData(cliente, dataHora);
        if (consulta == null) {
            System.out.println("Consulta não encontrada!");
            return;
        }

        consultas.remove(consulta);
        System.out.println("Consulta excluída com sucesso!");
    }  
    
    //===============================================================================================
    
    // excluir cadastro de cliente
    public void excluirCliente() {
        System.out.println("Digite o CPF do cliente a ser excluído:");
        String cpf = scanner.nextLine();
        Cliente cliente = buscarClientePorCpf(cpf);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        clientes.remove(cliente);
        List<Consulta> consultasAtualizadas = new ArrayList<Consulta>();
        for (Consulta consulta : consultas) {
            if (!consulta.getCliente().equals(cliente)) {
                consultasAtualizadas.add(consulta);
            }
        }
       consultas = consultasAtualizadas;

        System.out.println("Cliente e suas consultas foram excluídos com sucesso!");
    }

    //===============================================================================================
    
    // buscando o cliente
    private Cliente buscarClientePorCpf(String cpf) {
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) return cliente;
        }
        return null;
    }
    
    private Consulta buscarConsultaPorClienteEData(Cliente cliente, LocalDateTime dataHora) {
        for (Consulta consulta : consultas) {
            if (consulta.getCliente().equals(cliente) && consulta.getDataHora().equals(dataHora)) return consulta;
        }
        return null;
    }

    //===============================================================================================

    // salvar dados em arquivo
     public void salvarDados(String arquivo) throws IOException { // pode lançar uma excecao de entrada e saida
       ObjectOutputStream oos = null; // serializa (converte em fluxo de bytes) objetos em formato que pode ser armazenado em um arquivo
       try {
           oos = new ObjectOutputStream(new FileOutputStream(arquivo)); // FileOutputStream vai abrir/criar o arquivo que vai armazenar os dados
           oos.writeObject(clientes); // escreve a lista de clientes cadastrados no arquivo 
           oos.writeObject(consultas); // escreve a lista de consultas no arquivo 
       } finally { // garante que seja fechado
           if (oos != null) {
               try {
                   oos.close(); // fecha o stream pra garantir que os dados sejam gravados e liberar recursos
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
     }
     
     //===============================================================================================

   // carregar dados de arquivo
   @SuppressWarnings("unchecked")
   public void carregarDados(String arquivo) throws IOException, ClassNotFoundException {
     ObjectInputStream ois = null;
     try {
         ois = new ObjectInputStream(new FileInputStream(arquivo));
         clientes = (List<Cliente>) ois.readObject();
         consultas = (List<Consulta>) ois.readObject();
     } finally {
         if (ois != null) {
             try {
                 ois.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
   }
   
   //===============================================================================================

    // imprime todos os dados
    public void imprimirDados() {
        System.out.println("\nClientes:");
        for (Cliente cliente : clientes) {
        	
            System.out.println(cliente);
        }

        System.out.println("\nConsultas:");
        for (Consulta consulta : consultas) {
            System.out.println(consulta);
        }
    }
    
    //===============================================================================================

    // menu do sistema
    public void menu() {
        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Agendar Consulta");
            System.out.println("3. Alterar Agendamento");
            System.out.println("4. Excluir Agendamento");
            System.out.println("5. Excluir Cadastro");
            System.out.println("6. Salvar Dados");
            System.out.println("7. Carregar Dados");
            System.out.println("8. Imprimir Dados");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    agendarConsulta();
                    break;
                case 3:
                    alterarConsulta();
                    break;
                case 4:
                    excluirConsulta();
                    break;
                case 5:
                    excluirCliente();
                    break;
                case 6:
                    try {
                        salvarDados("dados_clinica.ser");
                        System.out.println("Dados salvos com sucesso!");
                    } catch (IOException e) {
                        System.out.println("Erro ao salvar dados: " + e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        carregarDados("dados_clinica.ser");
                        System.out.println("Dados carregados com sucesso!");
                    } catch (IOException e) {
                        System.out.println("Erro ao carregar dados: " + e.getMessage());
                    } catch (ClassNotFoundException e){
                      System.out.println("Erro ao carregar dados: " + e.getMessage());
                    }
                      break;
                case 8:
                    imprimirDados();
                    break;
                case 9:
                  try { salvarDados("dados_clinica.ser");
                    System.out.println("Os dados foram salvos!");
                  } catch (IOException e) {
                    System.out.println("Os dados não foram salvos" + e.getMessage());
                  }

                  
                    System.out.println("Saindo...");
                    return;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    //===============================================================================================
    
    // validacao do cpf e da data
    private boolean validarCPF(String CPF) {
      return CPF.matches("\\d{11}"); // expressao regular que verifica se o cpf tem 11 digitos consecutivos !precisa de dois traços pro java nao identificar como um caractere especial e sim como uma ER!
    }

    //===============================================================================================    
    
    // escolha da especialidade
    private String especialidade() {

        String especialidade = null;
        String opcao;
        
        while(especialidade == null) {
        	System.out.println("Qual especialidade escolhida?");
        	System.out.println("1. Cardiologia");
        	System.out.println("2. Clínica Médica");
        	System.out.println("3. Endocrinologia");
        	System.out.println("4. Ginecologia");
        	System.out.println("5. Ortopedia");
        	System.out.println("6. Psicologia");
        	opcao = scanner.nextLine().trim().toUpperCase();

        	switch(opcao) {
        	case "1": case "CARDIOLOGIA":
        		especialidade = "Cardiologia";
        		break;
        	case "2": case "CLINICA MEDICA":
        		especialidade = "Clínica Médica";
        		break;
        	case "3": case "ENDOCRINOLOGIA":
        		especialidade = "Endocrinologia";
        		break;
        	case "4": case "GINECOLOGIA":
        		especialidade = "Ginecologia";
        		break;
        	case "5": case "ORTOPEDIA":
        		especialidade = "Ortopedia";
        		break;
        	case "6": case "PSICOLOGIA":
        		especialidade = "Psicologia";
        		break;
        	default:
        		System.out.println("Especialidade inválida, confira a escrita e tente novamente.");
        	}
        }
        return especialidade;
     }
        
    //===============================================================================================
    
    /*
     * 
 	//metodo de validacao da data evitando redundancia no codigo, necessario acrescentar e alterar os metodos originais
 	 {	LocalDateTime dataHora = obterDataValida("Digite a data da consulta a ser alterada (dd/MM/yyyy HH:mm):");
		LocalDateTime novaData = obterDataValida("Consulta encontrada! Digite a nova data (dd/MM/yyyy HH:mm):");
		}
		
    private LocalDateTime obterDataValida(String mensagem) {
    	LocalDateTime data = null;
    	while (data == null) {
    		System.out.println(mensagem);
    		String dataStr = scanner.nextLine();
    		try {
    			data = LocalDateTime.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    		} catch (DateTimeParseException e) {
    			System.out.println("Formato de data e hora inválido. Tente novamente.");
    		}
    	}
    	return data;
    }
    
    */
    
    //===============================================================================================
    
}


    
    






  
 
