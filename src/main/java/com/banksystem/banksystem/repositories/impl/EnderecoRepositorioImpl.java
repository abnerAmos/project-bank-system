package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.domains.Endereco;
import com.banksystem.banksystem.repositories.EnderecoRepositorio;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public class EnderecoRepositorioImpl implements EnderecoRepositorio {

    @SneakyThrows
    @Override
    public Endereco criarEndereco(Endereco endereco) {

        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(INSERT_ADDRESS_SQL);

        stmt.setString(1, endereco.getCidade());
        stmt.setString(2, endereco.getEstado());
        stmt.setString(3, endereco.getRua());
        stmt.setString(4, endereco.getBairro());
        stmt.setString(5, endereco.getNumeroCasa());
        stmt.setInt(6, endereco.getCep());
        stmt.setString(7, endereco.getComplemento());
        stmt.executeUpdate();

        int id = getIdEndereco();
        endereco.setId(id);
        return endereco;
    }

    @SneakyThrows
    public int getIdEndereco() {

        int addressId = 0;
        Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(NEXT_ADDRESS_ID_SQL);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int nextId = resultSet.getInt("AUTO_INCREMENT");
            addressId = nextId - 1;
        }
        return addressId;
    }
}