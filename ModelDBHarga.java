package com.laundry.laundry.databases;

import java.sql.Connection;
import java.sql.SQLException;

public class ModelDBHarga {
    public final Connection connection;

    public ModelDBHarga() throws SQLException {
        this.connection = DbConncection.getConnection();
    }

    public double getHarga() throws SQLException {
        String query = "SELECT harga FROM harga";
        try {
            var preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("harga");
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Gagal get harga : " + e.getMessage());
        }
        return 0;
    }
    public void updateHarga(double harga) throws SQLException {
        String query = "UPDATE harga SET harga = ?";
        try {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDouble(1, harga);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Gagal update harga : " + e.getMessage());
        }
    }
}
