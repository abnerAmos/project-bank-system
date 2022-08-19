package com.banksystem.banksystem;

import java.util.Scanner;

public class BankSystemStartApplication {
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		menuOpcoes();

	}

	private static void menuOpcoes() {

		System.out.println("----------- BEM VINDO AO BANK SYSTEM -------------");
		System.out.println("##################################################");
		System.out.println("---------------------- MENU ----------------------");
		System.out.println("--------------------------------------------------");
		System.out.println("01 - Criar Conta Bancária");
		System.out.println("02 - Depositar");
		System.out.println("03 - Sacar");
		System.out.println("04 - Ativar Conta");
		System.out.println("05 - Desativar Conta");
		System.out.println("06 - Extrato Bancário");
		System.out.println("07 - Transferência");
		System.out.println("08 - Consultar Saldo");
		System.out.println("09 - Consultar Saldo");
		System.out.println("##################################################");
		System.out.println("--------------------------------------------------");
		System.out.println("Seleciona uma Opção:");

		if (!input.hasNext("[0-9]")) {
			System.out.println("Opção Inválida");
		}
		else {
			System.out.println(input.next());
		}
		return;
	}

}
