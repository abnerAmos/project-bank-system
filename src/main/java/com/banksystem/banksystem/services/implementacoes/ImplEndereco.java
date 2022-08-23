package com.banksystem.banksystem.services.implementacoes;

import com.banksystem.banksystem.services.ServicoEndereco;

public class ImplEndereco implements ServicoEndereco {


    @Override
    public void CriarEndereco(String endereco) {

    }

    @Override
    public Boolean ValidarEndereco(String endereco) {

        String[] token = endereco.split(",");
        boolean rua = token[0].trim().matches("(?=^.{2,60}$)^[A-Z][a-z]+(?:[ ](?:das?|dos?|de|e|[A-Z][a-z]+))*$");
        boolean numeroCasa = token[1].trim().matches("\\d{1,5}[A-Z]?");
        boolean bairro = token[2].trim().matches("(?=^.{2,30}$)^[A-Z][a-z]+(?:[ ](?:das?|dos?|de|e|[A-Z][a-z]+))*$");
        boolean cidade = token[3].trim().matches("(\\s?[A-Z][a-z]{2,10}([ ]?[do|de]?|[A-Z][a-z]{3,8})*)");
        boolean estado = token[4].trim().matches("[A-Z]{2}");
        boolean cep = token[5].trim().matches("\\d{5}-?\\d{3}");
        boolean complemento = token[6].trim().matches("((\\s?[A-Z][a-z]{2,12}([ ]\\d{1,4}[A-Z]?|[A-Z])?)*)?");

        return true;
    }
}
