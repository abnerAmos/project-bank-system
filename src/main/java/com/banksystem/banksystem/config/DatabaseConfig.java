package com.banksystem.banksystem.config;

import com.banksystem.banksystem.utils.ParametrosSistema;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

// Singleton (design pattern)
public class DatabaseConfig {

    private static Connection connection;

    @SneakyThrows
    public static Connection getConnection() {
        if (Objects.nonNull(connection) && !connection.isClosed()) {
            return connection;
        }

        String url = ParametrosSistema.DB_CONNECTION;
        String user = ParametrosSistema.DB_USERNAME;
        String pass = ParametrosSistema.DB_PASSWORD;
        Connection connection = DriverManager.getConnection(url, user, pass);

        return connection;
    }
}
