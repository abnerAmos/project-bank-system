package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.domains.Acesso;
import com.banksystem.banksystem.repositories.AcessoRepositorio;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public class AcessoRepositorioImpl implements AcessoRepositorio {

    @Override
    @SneakyThrows
    public void criarSenha(Integer senha, Integer idCliente, Integer idConta) {

        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(INSERT_ACESS_SQL);

        stmt.setInt(1, senha);
        stmt.setInt(2,idCliente);
        stmt.setInt(3, idConta);
        stmt.executeUpdate();
    }

    @Override
    @SneakyThrows
    public Optional<Integer> getSenha(Integer idCliente, Integer idConta) {

        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(GET_PASSWORD_SQL);
        stmt.setInt(1, idCliente);
        stmt.setInt(2, idConta);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            return Optional.of(resultSet.getInt("password_acess"));
        }

        return Optional.empty();
    }
}
