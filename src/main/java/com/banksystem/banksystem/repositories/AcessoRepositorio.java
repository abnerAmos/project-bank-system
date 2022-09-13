package com.banksystem.banksystem.repositories;

import java.util.Optional;

public interface AcessoRepositorio extends Repositorio{

    String INSERT_ACESS_SQL = "INSERT INTO access (password_acess, client_id, account_id) VALUES (?, ?, ?)";
    String GET_PASSWORD_SQL = "SELECT a.password_acess FROM access a WHERE a.client_id = ? AND a.account_id = ?";

    void criarSenha(Integer senha, Integer idCliente, Integer idConta);

    Optional<Integer> getSenha(Integer idCliente, Integer idConta);
}
