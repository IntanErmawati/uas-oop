package com.laundry.laundry.controllers;

import com.laundry.laundry.Main;
import com.laundry.laundry.databases.ModelDBUser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    public TextField tf_username;

    @FXML
    public TextField tf_password;
    private ModelDBUser modelDBUser;

    @FXML
    AnchorPane loginScene;

    @FXML
    protected void loginAction(ActionEvent event) throws IOException {
        checkField();
        if (modelDBUser.login(tf_username.getText(), tf_password.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Success");
            alert.setContentText("Login Success");
            alert.showAndWait();
            changeToHome(event);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Login Failed");
            alert.showAndWait();
        }
    }

    public void changeToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("home.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    private void checkField() {
        if (tf_username.getText().isEmpty() || tf_password.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            modelDBUser = new ModelDBUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
