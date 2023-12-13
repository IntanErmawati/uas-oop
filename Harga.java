package com.laundry.laundry.models;

import javafx.beans.property.*;

public class Harga {

    IntegerProperty id;
    DoubleProperty harga;
    StringProperty created_at;
    StringProperty updated_at;

    public Harga(int id, double harga, String created_at, String updated_at) {
        this.id = new SimpleIntegerProperty(id);
        this.harga = new SimpleDoubleProperty(harga);
        this.created_at = new SimpleStringProperty(created_at);
        this.updated_at = new SimpleStringProperty(updated_at);
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

    public double getHarga() {
        return harga.get();
    }

    public DoubleProperty hargaProperty() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga.set(harga);
    }

    public String getCreated_at() {
        return created_at.get();
    }

    public StringProperty created_atProperty() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at.set(created_at);
    }

    public String getUpdated_at() {
        return updated_at.get();
    }

    public StringProperty updated_atProperty() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at.set(updated_at);
    }
}
