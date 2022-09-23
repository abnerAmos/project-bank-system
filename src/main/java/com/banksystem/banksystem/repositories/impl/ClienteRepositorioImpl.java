package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.domains.Cliente;
import com.banksystem.banksystem.repositories.ClienteRepositorio;
import com.banksystem.banksystem.utils.ValidandoData;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public class ClienteRepositorioImpl implements ClienteRepositorio {

    @SneakyThrows
    @Override
    public Cliente criarCliente(Cliente cliente) {

        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(INSERT_CLIENT_SQL);

        stmt.setString(1, cliente.getNome());
        stmt.setDate(2, ValidandoData.localDate(cliente.getNascimento()));
        stmt.setString(3, cliente.getTelefone());
        stmt.setString(4, cliente.getEmail());
        stmt.setString(5, String.valueOf(cliente.getTipoPessoa()));
        stmt.setString(6, String.valueOf(cliente.getTipoDocumento()));
        stmt.setString(7, cliente.getDocumento());
        stmt.setInt(8, cliente.getEnderecoId());
        stmt.executeUpdate();

        int id = getId("client");
        cliente.setId(id);

        return cliente;
    }
}

