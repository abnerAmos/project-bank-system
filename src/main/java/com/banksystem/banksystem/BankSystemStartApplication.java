package com.banksystem.banksystem;

import java.util.InputMismatchException;
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
				System.out.println("Criando Conta Bancária - EM CONSTRUÇÃO");
			case 2:
				System.out.println("Depositando - EM CONSTRUÇÃO");
			case 3:
				System.out.println("Sacando - EM CONSTRUÇÃO");
			case 4:
				System.out.println("Ativando Conta - EM CONSTRUÇÃO");
			case 5:
				System.out.println("Desativando Conta - EM CONSTRUÇÃO");
			case 6:
				System.out.println("Emitindo Extrato Bancário - EM CONSTRUÇÃO");
			case 7:
				System.out.println("Transferindo - EM CONSTRUÇÃO");
			case 8:
				System.out.println("Consultando Saldo - EM CONSTRUÇÃO");
			case 9:
				System.out.println("Encerrando Aplicação\n");
				System.out.println("****** OBRIGADO POR ACESSAR NOSSO SISTEMA ;) ******");
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