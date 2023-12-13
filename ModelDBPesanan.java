package com.laundry.laundry.databases;

import com.laundry.laundry.models.Pesanan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;

public class ModelDBPesanan {

    public final Connection connection;

    public ModelDBPesanan() throws SQLException {
        this.connection = DbConncection.getConnection();
    }

    public void insertPesanan(Pesanan pesanan) throws SQLException {
        String query = "INSERT INTO pesanan (nama_pemesan, no_tlp, berat, total_harga, tanggal_order, status) VALUES " +
                "(?, ?, ?, ?, ?, ?)";
        try {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pesanan.getNama_pemesan());
            preparedStatement.setString(2, pesanan.getNo_tlp());
            preparedStatement.setDouble(3, pesanan.getBerat());
            preparedStatement.setDouble(4, pesanan.getTotal_harga());
            preparedStatement.setString(5, pesanan.getTanggal_order());
            preparedStatement.setString(6, pesanan.getStatus());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Gagal insert pesanan : " + e.getMessage());
        }
    }
    public ObservableList<Pesanan> getPesananBelumSelesai() throws SQLException {
        ObservableList<Pesanan> pesanan = FXCollections.observableArrayList();
        String query = "SELECT * FROM pesanan WHERE status = 'Belum Selesai'";
        try {
            var preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pesanan.add(new Pesanan(
                        resultSet.getInt("id"),
                        resultSet.getString("nama_pemesan"),
                        resultSet.getString("no_tlp"),
                        resultSet.getDouble("berat"),
                        resultSet.getDouble("total_harga"),
                        resultSet.getString("tanggal_order"),
                        resultSet.getString("status")
                ));
            }
            preparedStatement.close();
            return pesanan;
        } catch (Exception e) {
            System.out.println("Gagal get pesanan : " + e.getMessage());
        }
        return pesanan;
    }
    public ObservableList<Pesanan> getPesananSelesai() throws SQLException {
        ObservableList<Pesanan> pesanan = FXCollections.observableArrayList();
        String query = "SELECT * FROM pesanan WHERE status = 'Selesai'";
        try {
            var preparedStatement = connection.prepareStatement(query);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                pesanan.add(new Pesanan(
                        resultSet.getInt("id"),
                        resultSet.getString("nama_pemesan"),
                        resultSet.getString("no_tlp"),
                        resultSet.getDouble("berat"),
                        resultSet.getDouble("total_harga"),
                        resultSet.getString("tanggal_order"),
                        resultSet.getString("status")
                ));
            }
            preparedStatement.close();
            return pesanan;
        } catch (Exception e) {
            System.out.println("Gagal get pesanan : " + e.getMessage());
        }
        return pesanan;
    }
    public void updatePesanan(Pesanan pesanan) throws SQLException {
        String query = "UPDATE pesanan SET nama_pemesan = ?, no_tlp = ?, berat = ?, total_harga = ?, tanggal_order = ?, status = ? WHERE id = ?";
        try {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pesanan.getNama_pemesan());
            preparedStatement.setString(2, pesanan.getNo_tlp());
            preparedStatement.setDouble(3, pesanan.getBerat());
            preparedStatement.setDouble(4, pesanan.getTotal_harga());
            preparedStatement.setString(5, pesanan.getTanggal_order());
            preparedStatement.setString(6, pesanan.getStatus());
            preparedStatement.setInt(7, pesanan.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Gagal update pesanan : " + e.getMessage());
        }
    }
    public void deletePesanan(int id) throws SQLException {
        String query = "DELETE FROM pesanan WHERE id = ?";
        try {
            var preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (Exception e) {
            System.out.println("Gagal delete pesanan : " + e.getMessage());
        }
    }
}
