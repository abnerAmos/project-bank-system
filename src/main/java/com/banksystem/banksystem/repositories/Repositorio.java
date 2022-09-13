package com.banksystem.banksystem.repositories;

import java.sql.*;

import static com.banksystem.banksystem.config.DatabaseConfig.getConnection;

public interface Repositorio {

    String GET_NEXT_ID_SQL = "SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'DB_BANK_SYSTEM' AND TABLE_NAME = ?";

    default int getId(String nomeTabela) {

        int id = 0;

        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_NEXT_ID_SQL);
            statement.setString(1, nomeTabela);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int nextId = resultSet.getInt("AUTO_INCREMENT");
                id = nextId - 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
}
