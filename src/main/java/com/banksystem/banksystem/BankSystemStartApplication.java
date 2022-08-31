package com.banksystem.banksystem;

import com.banksystem.banksystem.domains.Cliente;
import com.banksystem.banksystem.domains.Conta;
import com.banksystem.banksystem.domains.Endereco;
import com.banksystem.banksystem.enums.TipoConta;
import com.banksystem.banksystem.repositories.impl.ClienteRepositorioImpl;
import com.banksystem.banksystem.repositories.impl.ContaRepositorioImpl;
import com.banksystem.banksystem.repositories.impl.EnderecoRepositorioImpl;
import com.banksystem.banksystem.services.ServicoCliente;
import com.banksystem.banksystem.services.ServicoConta;
import com.banksystem.banksystem.services.ServicoEndereco;
import com.banksystem.banksystem.services.implementacoes.ImplCliente;
import com.banksystem.banksystem.services.implementacoes.ImplConta;
import com.banksystem.banksystem.services.implementacoes.ImplEndereco;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class BankSystemStartApplication {

	public static void main(String[] args) {

		int opcao = 0;

		while (opcao == 0) {
			opcao = menuOpcoes();
			if (opcao == 0) {
				System.out.println();
				System.out.println("**************************************************");
				System.out.println("----------------- OPÇÃO INVÁLIDA -----------------");
				System.out.println("------------- INSIRA UMA OPÇÃO VÁLIDA ------------");
				System.out.println("**************************************************\n");
			}
		}

		switch (opcao) {
			case 1:

				ServicoConta servicoConta = new ImplConta(new ContaRepositorioImpl());
				ServicoCliente servicoCliente = new ImplCliente(new ClienteRepositorioImpl());
				ServicoEndereco servicoEnd = new ImplEndereco(new EnderecoRepositorioImpl());			// Chamando a classe onde será construido o endereço

				System.out.println("###################################");
				System.out.println("***** CRIANDO CONTA BANCÁRIA: *****");
				System.out.println("###################################\n");

				System.out.println("Informe Dados Pessoais para Cadastro:");
				System.out.println("EXEMPLO: Nome Completo, Data Nascimento: dd/MM/yyyy (com barras), Telefone: 11922224444, email, PF ou PJ, CPF ou CNPJ, Numero de Doc. (CPF ou CNPJ)");
				String clienteInput = new Scanner(System.in).nextLine();	// Capturando dados do usuário
				Optional<Cliente> clienteOpt = servicoCliente.construtorCliente(clienteInput);

				while (clienteOpt.isEmpty()) {
					System.out.println("***** DADOS INVÁLIDOS ******");
					System.out.println("***** INSIRA NOVAMENTE *****");
					clienteInput = new Scanner(System.in).nextLine();
					clienteOpt = servicoCliente.construtorCliente(clienteInput);
				}

				Cliente cliente = clienteOpt.get();
				boolean contaExistente = servicoConta.contaJaExistente(cliente);

				if (contaExistente) {
					System.out.println("CONTA JÁ EXISTENTE");
					return;
				}

				System.out.println("\nInforme o seu Endereço Completo:");
				System.out.println("EXEMPLO: Rua Fulano de Tal, 1234, Bairro, Cidade, SP, 02552012, Torre B Apto 01");
				String enderecoInput = new Scanner(System.in).nextLine();	// Capturando o endereço do usuário
				Optional<Endereco> enderecoOpt = servicoEnd.construtorEndereco(enderecoInput);		// Enviando os dados de end. p/ classe Impl., tratando e validando infos.

				while (enderecoOpt.isEmpty()) {
					System.out.println("\n***** ENDERECO INVÁLIDOS *****");
					System.out.println("****** INSIRA NOVAMENTE ******");
					enderecoInput = new Scanner(System.in).nextLine();
					enderecoOpt = servicoEnd.construtorEndereco(enderecoInput);
				}

				Endereco endereco = enderecoOpt.get();		// Após tratar e validar, armazena o end. em uma variável
				Endereco salvarEnd = servicoEnd.criarEndereco(endereco);			// Inserindo o end. tratado e validado dentro de outra classe para que não seja necessário refazer o processo.

				cliente.setEnderecoId(salvarEnd.getId());
				Cliente salvarCliente = servicoCliente.criarCliente(cliente);
				Conta conta = new Conta(salvarCliente.getId(), TipoConta.CORRENTE);
				Conta salvarConta = servicoConta.criarConta(conta);

				break;
			case 2:
				System.out.println("Depositando - EM CONSTRUÇÃO");
				break;
			case 3:
				System.out.println("Sacando - EM CONSTRUÇÃO");
				break;
			case 4:
				System.out.println("Ativando Conta - EM CONSTRUÇÃO");
				break;
			case 5:
				System.out.println("Desativando Conta - EM CONSTRUÇÃO");
				break;
			case 6:
				System.out.println("Emitindo Extrato Bancário - EM CONSTRUÇÃO");
				break;
			case 7:
				System.out.println("Transferindo - EM CONSTRUÇÃO");
				break;
			case 8:
				System.out.println("Consultando Saldo - EM CONSTRUÇÃO");
				break;
			case 9:
				System.out.println("Encerrando Aplicação\n");
				System.out.println("****** OBRIGADO POR ACESSAR NOSSO SISTEMA ;) ******");
				break;
		}
	}

	private static int menuOpcoes() {
		System.out.println("##################################################");
		System.out.println("---------------- MENU BANK SYSTEM ----------------");
		System.out.println("--------------------------------------------------");
		System.out.println("01 - Criar Conta Bancária");
		System.out.println("02 - Depositar");
		System.out.println("03 - Sacar");
		System.out.println("04 - Ativar Conta");
		System.out.println("05 - Desativar Conta");
		System.out.println("06 - Extrato Bancário");
		System.out.println("07 - Transferência");
		System.out.println("08 - Consultar Saldo");
		System.out.println("09 - Encerrar");
		System.out.println("--------------------------------------------------");
		System.out.println("##################################################");
		System.out.println("               SELECIONE UMA OPÇÃO:");

		try {
			Scanner input = new Scanner(System.in);
			if (input.hasNext("[1-9]")) {
				return input.nextInt();
			} else {
				return 0;
			}

		} catch (InputMismatchException e) {
			return 0;
		}
	}
}