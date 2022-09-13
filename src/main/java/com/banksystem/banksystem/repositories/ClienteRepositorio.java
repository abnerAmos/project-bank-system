package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.domains.Cliente;

public interface ClienteRepositorio extends Repositorio{

    String INSERT_CLIENT_SQL = "INSERT INTO client (name, birthdate, phone, email, person_tp, document_tp, document, address_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    Cliente criarCliente(Cliente cliente);
}
