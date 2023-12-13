package com.laundry.laundry.models;

import javafx.beans.property.*;

public class Pesanan {
    IntegerProperty id;
    StringProperty nama_pemesan;
    StringProperty no_tlp;
    DoubleProperty berat;
    DoubleProperty total_harga;
    StringProperty tanggal_order;
    StringProperty status;

    public Pesanan(String nama_pemesan, String no_tlp, Double berat, Double total_harga, String tanggal_order, String status) {
        this.nama_pemesan = new SimpleStringProperty(nama_pemesan);
        this.no_tlp = new SimpleStringProperty(no_tlp);
        this.berat = new SimpleDoubleProperty(berat);
        this.total_harga = new SimpleDoubleProperty(total_harga);
        this.tanggal_order = new SimpleStringProperty(tanggal_order);
        this.status = new SimpleStringProperty(status);
    }
    public Pesanan(Integer id, String nama_pemesan, String no_tlp, Double berat, Double total_harga, String tanggal_order, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.nama_pemesan = new SimpleStringProperty(nama_pemesan);
        this.no_tlp = new SimpleStringProperty(no_tlp);
        this.berat = new SimpleDoubleProperty(berat);
        this.total_harga = new SimpleDoubleProperty(total_harga);
        this.tanggal_order = new SimpleStringProperty(tanggal_order);
        this.status = new SimpleStringProperty(status);
    }

    public String getNama_pemesan() {
        return nama_pemesan.get();
    }

    public StringProperty nama_pemesanProperty() {
        return nama_pemesan;
    }

    public void setNama_pemesan(String nama_pemesan) {
        this.nama_pemesan.set(nama_pemesan);
    }

    public String getNo_tlp() {
        return no_tlp.get();
    }

    public StringProperty no_tlpProperty() {
        return no_tlp;
    }

    public void setNo_tlp(String no_tlp) {
        this.no_tlp.set(no_tlp);
    }

    public double getBerat() {
        return berat.get();
    }

    public DoubleProperty beratProperty() {
        return berat;
    }

    public void setBerat(double berat) {
        this.berat.set(berat);
    }

    public double getTotal_harga() {
        return total_harga.get();
    }

    public DoubleProperty total_hargaProperty() {
        return total_harga;
    }

    public void setTotal_harga(double total_harga) {
        this.total_harga.set(total_harga);
    }

    public String getTanggal_order() {
        return tanggal_order.get();
    }

    public StringProperty tanggal_orderProperty() {
        return tanggal_order;
    }

    public void setTanggal_order(String tanggal_order) {
        this.tanggal_order.set(tanggal_order);
    }

    public String getStatus() {
        return status.get();
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public StringProperty statusProperty() {
        return status;
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
