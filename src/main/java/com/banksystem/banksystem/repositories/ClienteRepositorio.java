package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.domains.Cliente;

public interface ClienteRepositorio {

    String INSERT_CLIENT_SQL = "INSERT INTO client (name, birthdate, phone, email, person_tp, document_tp, document, address_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String NEXT_CLIENT_ID_SQL = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'DB_BANK_SYSTEM' AND TABLE_NAME = 'client'";

    Cliente criarCliente(Cliente cliente);
}
