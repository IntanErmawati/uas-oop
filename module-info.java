module com.laundry.laundry {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.laundry.laundry to javafx.fxml;
    opens com.laundry.laundry.models to javafx.fxml;
    exports com.laundry.laundry;
    exports com.laundry.laundry.models;
    exports com.laundry.laundry.controllers;
    opens com.laundry.laundry.controllers to javafx.fxml;
}