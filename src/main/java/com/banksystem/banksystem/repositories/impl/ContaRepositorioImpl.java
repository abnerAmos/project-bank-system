package com.banksystem.banksystem.repositories.impl;

import com.banksystem.banksystem.domains.Conta;
import com.banksystem.banksystem.enums.TipoConta;
import com.banksystem.banksystem.repositories.ContaRepositorio;
import com.banksystem.banksystem.utils.ValidandoData;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public class ContaRepositorioImpl implements ContaRepositorio {

    @Override
    @SneakyThrows
    public Conta criarConta(Conta conta) {

        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(INSERT_ACCOUNT_SQL);

        stmt.setInt(1, conta.getNumeroConta());
        stmt.setInt(2, conta.getClienteId());
        stmt.setTimestamp(3, ValidandoData.localDateTimetoTimestamp(conta.getDataRegistro()));
        stmt.setString(4, String.valueOf(conta.getTipoConta()));
        stmt.executeUpdate();

        int id = getId("bank_account");
        conta.setId(id);
        return conta;
    }

    @SneakyThrows
    @Override
    public Conta procurarContaPorDoc(String documento) {

        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(GET_ACCOUNT_BY_DOC_SQL);
        stmt.setString(1, documento);
        ResultSet resultSet = stmt.executeQuery();

        if (resultSet.next()) {
            int nextId = resultSet.getInt("id");
            int numeroConta = resultSet.getInt("account_number");
            int clienteId = resultSet.getInt("client_id");
            int agencia = resultSet.getInt("agency");
            double saldo = resultSet.getDouble("balance");
            String tipoConta = resultSet.getString("account_tp");
            LocalDateTime dtRegistro = null;
            LocalDateTime dtDesativacao = null;
            Timestamp registro = resultSet.getTimestamp("registration_dt");
            Timestamp desativacao = resultSet.getTimestamp("deactivation_dt");

            if (Objects.nonNull(registro))
                dtRegistro = registro.toLocalDateTime();

            if (Objects.nonNull(desativacao))
                dtDesativacao = desativacao.toLocalDateTime();

            return Conta
                    .builder()
                    .id(nextId)
                    .numeroConta(numeroConta)
                    .clienteId(clienteId)
                    .agencia(agencia)
                    .saldo(saldo)
                    .dataRegistro(dtRegistro)
                    .dataDesativacao(dtDesativacao)
                    .tipoConta(TipoConta.valueOf(tipoConta))
                    .build();

        }

        return null;
    }
}
