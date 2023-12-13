package com.laundry.laundry.databases;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ModelDBDashboard {
    public final Connection connection;

    public ModelDBDashboard() throws SQLException {
        this.connection = DbConncection.getConnection();
    }

    public double countPendapatan(){
        String sql = "SELECT SUM(total_harga) FROM pesanan WHERE status = 'Selesai'";
        try {
            var preparedStatement = connection.prepareStatement(sql);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getDouble("SUM(total_harga)");
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Gagal get pendapatan : " + e.getMessage());
        }
        return 0;
    }
    public int countPesananSelesai(){
        String sql = "SELECT COUNT(*) FROM pesanan WHERE status = 'Selesai'";
        try {
            var preparedStatement = connection.prepareStatement(sql);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Gagal get pendapatan : " + e.getMessage());
        }
        return 0;
    }
    public int countPesananBelumSelesai(){
        String sql = "SELECT COUNT(*) FROM pesanan WHERE status = 'Belum Selesai'";
        try {
            var preparedStatement = connection.prepareStatement(sql);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("COUNT(*)");
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Gagal get pendapatan : " + e.getMessage());
        }
        return 0;
    }

}
