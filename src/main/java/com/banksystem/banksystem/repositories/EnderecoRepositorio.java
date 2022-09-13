package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.domains.Endereco;

public interface EnderecoRepositorio extends Repositorio{

    String INSERT_ADDRESS_SQL = "INSERT INTO address (city, state, address, district, house_number, cep, address_2) VALUES (?, ?, ?, ?, ?, ?, ?)";

    /**
     * @param endereco dados do endereço que serão salvos no banco de dados
     * @return retorna o ID que foi atribuido ao endereco criado
     */

    Endereco criarEndereco(Endereco endereco);



}
