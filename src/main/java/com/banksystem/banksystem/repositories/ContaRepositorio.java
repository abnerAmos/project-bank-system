package com.banksystem.banksystem.repositories;

import com.banksystem.banksystem.domains.Conta;

public interface ContaRepositorio {

    String INSERT_ACCOUNT_SQL = "INSERT INTO bank_account (account_number, client_id, registration_dt, account_tp) VALUES (?, ?, ?, ?)";
    String NEXT_ACCOUNT_ID_SQL = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'DB_BANK_SYSTEM' AND TABLE_NAME = 'bank_account'";
    String GET_ACCOUNT_BY_DOC_SQL = "SELECT * FROM bank_account ba INNER JOIN client ac ON (ba.client_id  = ac.id) WHERE ac.document = ?";

    Conta criarConta(Conta conta);

    Conta procurarContaPorDoc(String documento);
}