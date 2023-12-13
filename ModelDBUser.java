package com.laundry.laundry.databases;

import java.sql.Connection;
import java.sql.SQLException;

public class ModelDBUser {
    public final Connection connection;

    public ModelDBUser() throws SQLException {
        this.connection = DbConncection.getConnection();
    }

    public boolean login(String username, String password) {
        String query = "SELECT * FROM user WHERE username = ? AND password = ?";
        try {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            preparedStatement.close();
        } catch (Exception e) {
           return false;
        }
        return false;
    }
}
