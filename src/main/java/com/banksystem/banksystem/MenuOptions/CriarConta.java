package com.banksystem.banksystem.MenuOptions;

import com.banksystem.banksystem.domains.Cliente;
import com.banksystem.banksystem.domains.Conta;
import com.banksystem.banksystem.domains.Endereco;
import com.banksystem.banksystem.enums.TipoConta;
import com.banksystem.banksystem.services.ServicoAcesso;
import com.banksystem.banksystem.services.ServicoCliente;
import com.banksystem.banksystem.services.ServicoConta;
import com.banksystem.banksystem.services.ServicoEndereco;
import com.banksystem.banksystem.services.implementacoes.ImplAcesso;
import com.banksystem.banksystem.services.implementacoes.ImplCliente;
import com.banksystem.banksystem.services.implementacoes.ImplConta;
import com.banksystem.banksystem.services.implementacoes.ImplEndereco;

import java.util.Optional;
import java.util.Scanner;

public class CriarConta {

    public static void criarConta() {

        ServicoAcesso servicoAcesso = new ImplAcesso();
        ServicoConta servicoConta = new ImplConta();
        ServicoCliente servicoCliente = new ImplCliente();
        ServicoEndereco servicoEnd = new ImplEndereco();			// Chamando a classe onde será construido o endereço

        System.out.println("###################################");
        System.out.println("***** CRIANDO CONTA BANCÁRIA: *****");
        System.out.println("###################################\n");

        System.out.println("Informe Dados Pessoais para Cadastro:");
        System.out.println("EXEMPLO: Nome Completo, Data Nascimento: dd/MM/yyyy (com barras), Telefone: 11922224444, email, PF ou PJ, CPF ou CNPJ, Numero de Doc. (CPF ou CNPJ)");
        String clienteInput = new Scanner(System.in).nextLine();	// Capturando dados do usuário
        Optional<Cliente> clienteOpt = servicoCliente.construtorCliente(clienteInput);

        while (clienteOpt.isEmpty()) {
            System.out.println("**********************************************");
            System.out.println("##### DADOS INVÁLIDOS, INSIRA NOVAMENTE ######");
            System.out.println("**********************************************");
            clienteInput = new Scanner(System.in).nextLine();
            clienteOpt = servicoCliente.construtorCliente(clienteInput);
        }

        Cliente cliente = clienteOpt.get();
        Optional<Conta> contaOpt = servicoConta.contaJaExistente(cliente);

        if (contaOpt.isPresent()) {
            Conta conta = contaOpt.get();
            System.out.println("\n*******************************");
            System.out.println("##### CONTA JÁ EXISTENTE ######");
            System.out.println("*******************************");
            return;
        }

        System.out.println("\nInforme o seu Endereço Completo:");
        System.out.println("EXEMPLO: Rua Fulano de Tal, 1234, Bairro, Cidade, SP, 02552012, Torre B Apto 01");
        String enderecoInput = new Scanner(System.in).nextLine();	// Capturando o endereço do usuário
        Optional<Endereco> enderecoOpt = servicoEnd.construtorEndereco(enderecoInput);		// Enviando os dados de end. p/ classe Impl., tratando e validando infos.

        while (enderecoOpt.isEmpty()) {
            System.out.println("\n************************************************");
            System.out.println("##### ENDEREÇO INVALIDO, INSIRA NOVAMENTE ######");
            System.out.println("************************************************\n");
            enderecoInput = new Scanner(System.in).nextLine();
            enderecoOpt = servicoEnd.construtorEndereco(enderecoInput);
        }

        Endereco endereco = enderecoOpt.get();		// Após tratar e validar, armazena o end. em uma variável
        Endereco salvarEnd = servicoEnd.criarEndereco(endereco);			// Inserindo o end. tratado e validado dentro de outra classe para que não seja necessário refazer o processo.

        System.out.println("\nInforme a senha de Acesso:");
        System.out.println("** Senha deve ter 6 digitos numerais **");
        Integer senhaInput = new Scanner(System.in).nextInt();

        cliente.setEnderecoId(salvarEnd.getId());
        Cliente salvarCliente = servicoCliente.criarCliente(cliente);
        Conta conta = new Conta(salvarCliente.getId(), TipoConta.CORRENTE);
        Conta salvarConta = servicoConta.criarConta(conta);
        servicoAcesso.criarAcesso(senhaInput, salvarConta.getClienteId(), salvarConta.getId());

        System.out.println("\n************************************************");
        System.out.println("##### CONTA CRIADA COM SUCESSO ######");
        System.out.printf("Conta: %s", salvarConta.getNumeroConta());
        System.out.printf("\nAgencia: %s", salvarConta.getAgencia());
        System.out.println("\n************************************************");
    }
}
