package com.laundry.laundry.controllers;

import com.laundry.laundry.Main;
import com.laundry.laundry.databases.ModelDBDashboard;
import com.laundry.laundry.databases.ModelDBHarga;
import com.laundry.laundry.databases.ModelDBPesanan;
import com.laundry.laundry.models.Pesanan;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    public TextField tf_nama_pemesan_selesai;
    @FXML
    public TextField tf_nohp_selesai;
    @FXML
    public TextField tf_berat_selesai;
    @FXML
    public DatePicker tf_tgl_order_selesai;
    @FXML
    public ComboBox<String> cb_status_selesai;
    @FXML
    public Button btn_edit_selesai;
    @FXML
    public Button btn_delete_selesai;

    @FXML
    public Button btn_clear_selesai;
    @FXML
    public Label id_pesanan_selesai;
    @FXML
    public AnchorPane homeScene;

    @FXML
    public Label lbl_jml_antrian;

    @FXML
    public Label lbl_antrian_selesai;

    @FXML
    public Label lbl_pendapatan;

    @FXML
    public TableView<Pesanan> tbl_laundry;

    @FXML
    public TableColumn<Pesanan, Integer> col_id;

    @FXML
    public TableColumn<Pesanan, String> col_pemesan;

    @FXML
    public TableColumn<Pesanan, String> col_hp;

    @FXML
    public TableColumn<Pesanan, Double> col_berat;

    @FXML
    public TableColumn<Pesanan, Double> col_harga;

    @FXML
    public TableColumn<Pesanan, String> col_tgl_order;

    @FXML
    public TableView<Pesanan> tbl_antrian_selesai;

    @FXML
    public TableColumn<Pesanan, Integer> col_id_selesai;

    @FXML
    public TableColumn<Pesanan, String> col_nama_selesai;

    @FXML
    public TableColumn<Pesanan, String> col_hp_selesai;

    @FXML
    public TableColumn<Pesanan, Double> col_berat_selesai;

    @FXML
    public TableColumn<Pesanan, Double> col_harga_selesai;

    @FXML
    public TableColumn<Pesanan, String> col_tgl_order_selesai;
    @FXML
    public Label lbl_harga_laundry;
    @FXML
    public TextField tf_harga;
    @FXML
    public TextField tf_nama_pemesan;
    @FXML
    public TextField tf_nohp;
    @FXML
    public TextField tf_berat;
    @FXML
    public DatePicker tf_tgl_order;
    @FXML
    public ComboBox<String> cb_status;
    @FXML
    public Button btn_edit;
    @FXML
    public Button btn_delete;
    @FXML
    public Button btn_save;
    @FXML
    public Button btn_clear;
    @FXML
    public Label id_antrian;

    private ModelDBPesanan modelDBPesanan;
    private ModelDBHarga modelDBHarga;
    private ModelDBDashboard modelDBDashboard;


    @FXML
    void logOutAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("login.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void refreshDashboard() {
        fetchDashboard();
    }

    @FXML
    void simpanHarga() {
        if (tf_harga.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Harga tidak boleh kosong");
            alert.showAndWait();
        }else{
            try {
                modelDBHarga.updateHarga(Double.parseDouble(tf_harga.getText()));
                setHarga();
                tf_harga.clear();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
    @FXML
    void clearAction() {
        clearFromAntrian();
    }

    @FXML
    void deleteAction() {
        try {
            modelDBPesanan.deletePesanan(Integer.parseInt(id_antrian.getText()));
            clearFromAntrian();
            fetchPesananBelumSelesai();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void editAction() throws SQLException {
        if (saveValidation()){
            double total_harga = Double.parseDouble(tf_berat.getText()) * Double.parseDouble(lbl_harga_laundry.getText());
            modelDBPesanan.updatePesanan(new Pesanan(
                    Integer.parseInt(id_antrian.getText()),
                    tf_nama_pemesan.getText(),
                    tf_nohp.getText(),
                    Double.parseDouble(tf_berat.getText()),
                    total_harga,
                    tf_tgl_order.getValue().toString(),
                    cb_status.getValue()
            ));
            clearFromAntrian();
            fetchPesananSelesai();
            fetchPesananBelumSelesai();
        }
    }

    @FXML
    void saveAction() throws SQLException {
        if (saveValidation()){
            double total_harga = Double.parseDouble(tf_berat.getText()) * Double.parseDouble(lbl_harga_laundry.getText());
            modelDBPesanan.insertPesanan(new Pesanan(
                    tf_nama_pemesan.getText(),
                    tf_nohp.getText(),
                    Double.parseDouble(tf_berat.getText()),
                    total_harga,
                    tf_tgl_order.getValue().toString(),
                    cb_status.getValue()
            ));
            clearFromAntrian();
            fetchPesananSelesai();
            fetchPesananBelumSelesai();
        }

    }

    @FXML
    void clearActionSelesai() {
        clearFromAntrianSelesai();
    }


    @FXML
    void deleteActionsSelesai() throws SQLException {
        modelDBPesanan.deletePesanan(Integer.parseInt(id_pesanan_selesai.getText()));
        clearFromAntrianSelesai();
        fetchPesananSelesai();
        fetchPesananBelumSelesai();
    }



    @FXML
    void editActionSelesai() throws SQLException {
        double total_harga = Double.parseDouble(tf_berat_selesai.getText()) * Double.parseDouble(lbl_harga_laundry.getText());
        if (saveValidationSelesai()){
            modelDBPesanan.updatePesanan(new Pesanan(
                    Integer.parseInt(id_pesanan_selesai.getText()),
                    tf_nama_pemesan_selesai.getText(),
                    tf_nohp_selesai.getText(),
                    Double.parseDouble(tf_berat_selesai.getText()),
                    total_harga,
                    tf_tgl_order_selesai.getValue().toString(),
                    cb_status_selesai.getValue()
            ));
        }
        clearFromAntrianSelesai();
        fetchPesananSelesai();
        fetchPesananBelumSelesai();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            modelDBPesanan = new ModelDBPesanan();
            modelDBHarga = new ModelDBHarga();
            modelDBDashboard = new ModelDBDashboard();
            cb_status_selesai.getItems().addAll("Selesai", "Belum Selesai");
            cb_status_selesai.getSelectionModel().selectFirst();
            cb_status.getItems().addAll("Selesai", "Belum Selesai");
            fetchPesananBelumSelesai();
            fetchPesananSelesai();
            setHarga();
            btnCRUDAntrian(true);
            fetchDashboard();

            tbl_laundry.getSelectionModel().selectedItemProperty().addListener((observableValue, pesanan1, t1) -> {
                if (t1 != null) {
                    LocalDate localDate = LocalDate.parse(t1.getTanggal_order());
                    id_antrian.setText(String.valueOf(t1.getId()));
                    tf_nama_pemesan.setText(t1.getNama_pemesan());
                    tf_nohp.setText(t1.getNo_tlp());
                    tf_berat.setText(String.valueOf(t1.getBerat()));
                    tf_tgl_order.setValue(localDate);
                    cb_status.getSelectionModel().select(t1.getStatus());
                    btnCRUDAntrian(false);
                }
            });
            tbl_antrian_selesai.getSelectionModel().selectedItemProperty().addListener((observableValue, pesanan1, t1) -> {
                if (t1 != null) {
                    LocalDate localDate = LocalDate.parse(t1.getTanggal_order());
                    id_pesanan_selesai.setText(String.valueOf(t1.getId()));
                    tf_nama_pemesan_selesai.setText(t1.getNama_pemesan());
                    tf_nohp_selesai.setText(t1.getNo_tlp());
                    tf_berat_selesai.setText(String.valueOf(t1.getBerat()));
                    tf_tgl_order_selesai.setValue(localDate);
                    cb_status_selesai.getSelectionModel().select(t1.getStatus());
                    btnCRUDAntrianSelesai(false);
                }
            });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean saveValidation(){
        if (tf_nama_pemesan.getText().isEmpty() || tf_nohp.getText().isEmpty() || tf_berat.getText().isEmpty() || tf_tgl_order.getValue() == null || cb_status.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Semua field harus diisi");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    private boolean saveValidationSelesai(){
        if (tf_nama_pemesan_selesai.getText().isEmpty() || tf_nohp_selesai.getText().isEmpty() || tf_berat_selesai.getText().isEmpty() || tf_tgl_order_selesai.getValue() == null || cb_status_selesai.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Semua field harus diisi");
            alert.showAndWait();
            return false;
        }
        return true;
    }
    private void clearFromAntrian(){
        id_antrian.setText("");
        tf_nama_pemesan.clear();
        tf_nohp.clear();
        tf_berat.clear();
        tf_tgl_order.setValue(null);
        cb_status.getSelectionModel().selectFirst();
        tbl_laundry.getSelectionModel().clearSelection();
        btnCRUDAntrian(true);
    }
    private void fetchPesananBelumSelesai() throws SQLException {
        ObservableList<Pesanan> pesanan = modelDBPesanan.getPesananBelumSelesai();
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_pemesan.setCellValueFactory(new PropertyValueFactory<>("nama_pemesan"));
        col_hp.setCellValueFactory(new PropertyValueFactory<>("no_tlp"));
        col_berat.setCellValueFactory(new PropertyValueFactory<>("berat"));
        col_harga.setCellValueFactory(new PropertyValueFactory<>("total_harga"));
        col_tgl_order.setCellValueFactory(new PropertyValueFactory<>("tanggal_order"));
        tbl_laundry.setItems(null);
        tbl_laundry.setItems(pesanan);
    }
    private void fetchPesananSelesai() throws SQLException {
        ObservableList<Pesanan> pesanan = modelDBPesanan.getPesananSelesai();
        col_id_selesai.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_nama_selesai.setCellValueFactory(new PropertyValueFactory<>("nama_pemesan"));
        col_hp_selesai.setCellValueFactory(new PropertyValueFactory<>("no_tlp"));
        col_berat_selesai.setCellValueFactory(new PropertyValueFactory<>("berat"));
        col_harga_selesai.setCellValueFactory(new PropertyValueFactory<>("total_harga"));
        col_tgl_order_selesai.setCellValueFactory(new PropertyValueFactory<>("tanggal_order"));
        tbl_antrian_selesai.setItems(null);
        tbl_antrian_selesai.setItems(pesanan);
    }
    private void btnCRUDAntrian(boolean isDisable){
        btn_edit.setDisable(isDisable);
        btn_delete.setDisable(isDisable);
        btn_save.setDisable(!isDisable);
    }
    private void btnCRUDAntrianSelesai(boolean isDisable){
        btn_edit_selesai.setDisable(isDisable);
        btn_delete_selesai.setDisable(isDisable);
    }
    private void clearFromAntrianSelesai(){
        id_pesanan_selesai.setText("");
        tf_nama_pemesan_selesai.clear();
        tf_nohp_selesai.clear();
        tf_berat_selesai.clear();
        tf_tgl_order_selesai.setValue(null);
        cb_status_selesai.getSelectionModel().selectFirst();
        tbl_antrian_selesai.getSelectionModel().clearSelection();
        btnCRUDAntrianSelesai(true);
    }
    private void setHarga(){
        try {
            double harga = modelDBHarga.getHarga();
            lbl_harga_laundry.setText(String.valueOf(modelDBHarga.getHarga()));
            System.out.println(harga);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void fetchDashboard(){
        double harga = modelDBDashboard.countPendapatan();
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        lbl_pendapatan.setText(String.valueOf(nf.format(harga)));
        lbl_antrian_selesai.setText(String.valueOf(modelDBDashboard.countPesananSelesai()));
        lbl_jml_antrian.setText(String.valueOf(modelDBDashboard.countPesananBelumSelesai()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Information");
        alert.setContentText("Data berhasil di refresh");
        alert.showAndWait();
    }

}