package com.banksystem.banksystem;

import java.util.Scanner;

public class RegexEndereco {

    public static void main(String[] args) {

        System.out.println("Informe um endereço:");
        Scanner input = new Scanner(System.in);
        String[] endereco = "Rua Abura, 226, Imirim, Rio de Janeiro, SP, 02552-012, Apartamento 01 Torre 01".split(",");

        boolean token1 = endereco[0].trim().matches("(?=^.{2,60}$)^[A-Z][a-z]+(?:[ ](?:das?|dos?|de|e|[A-Z][a-z]+))*$");
        boolean token2 = endereco[1].trim().matches("\\d{1,5}[A-Z]?");
        boolean token3 = endereco[2].trim().matches("(?=^.{2,30}$)^[A-Z][a-z]+(?:[ ](?:das?|dos?|de|e|[A-Z][a-z]+))*$");
        boolean token4 = endereco[3].trim().matches("(\\s?[A-Z][a-z]{2,10}([ ]?[do|de]?|[A-Z][a-z]{3,8})*)");
        boolean token5 = endereco[4].trim().matches("[A-Z]{2}");
        boolean token6 = endereco[5].trim().matches("\\d{5}-?\\d{3}");
        boolean token7 = endereco[6].trim().matches("((\\s?[A-Z][a-z]{2,12}([ ]\\d{1,4}[A-Z]?|[A-Z])?)*)?");

        System.out.println(token1);
        System.out.println(token2);
        System.out.println(token3);
        System.out.println(token4);
        System.out.println(token5);
        System.out.println(token6);
        System.out.println(token7);
    }
}
