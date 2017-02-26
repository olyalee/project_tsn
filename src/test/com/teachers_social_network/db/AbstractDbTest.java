package com.teachers_social_network.db;

/**
 * Created by Olya Lee on 17.11.2016.
 */
import lombok.Builder;
import lombok.Data;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;

public abstract class AbstractDbTest {

    @Data
    @Builder
    protected static class DbConfiguration{
        private final String url;
        private final String username;
        private final String password;
    }

    protected abstract DbConfiguration getDbConfiguration() throws ClassNotFoundException;

//    @Test
//    public void test() throws SQLException, ClassNotFoundException {
//        final DbConfiguration config = getDbConfiguration();
//
//        try (Connection connection = DriverManager.getConnection(config.getUrl(), config.getUsername(), config.getPassword())) {
//            final Statement statement = connection.createStatement();
//
//            statement.execute("CREATE SCHEMA teachers_social_network;");
//
//            statement.execute(
//                    "CREATE TABLE teachers_social_network.users ( login VARCHAR(25) PRIMARY KEY, psw VARCHAR(255) NOT NULL);"
//            );
//
//            final int cnt = statement.executeUpdate(
//                    "INSERT INTO teachers_social_network.users(login, psw)" +
//                            " SELECT 'ivan', 'ivanpsw'" +
//                            "UNION ALL SELECT 'fedor', 'fedorpsw';"
//            );
//
//            System.out.println("Inserted: " + cnt);
//
//            final ResultSet resultSet = statement.executeQuery("SELECT login, psw FROM teachers_social_network.users");
//
//            while (resultSet.next()) {
//                System.out.printf("User[login: %s, psw: %s]\n", resultSet.getString("login"), resultSet.getString("psw"));
//            }
//        }
//    }

}