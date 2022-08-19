package com.banksystem.banksystem;

public class teste {

    public static void main(String[] args) {

        String endereço = "Rua Mere Marie Anais de Sion";
        Boolean end = endereço.matches("(?=^.{2,60}$)^[A-Z][a-z]+(?:[ ](?:das?|dos?|de|e|[A-Z][a-z]+))*$");
        System.out.println(end);
    }
}
