package com.example.techprom_vol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VolAccount {
    @FXML private Button btn_acc;
    @FXML private Button btn_event;

    @FXML private Pane pane_acc;
    @FXML private Pane pane_event;

    @FXML private Label label_name;
    @FXML private Label label_sex;
    @FXML private Label label_age;
    @FXML private Label label_email;
    @FXML private Label label_phone;


    public String name;
    public String age;
    public String sex;
    public String email;
    public String phone;


    @FXML
    private void handleButtonAction(ActionEvent actionEvent) throws SQLException {
        if (actionEvent.getSource() == btn_acc) {
            setLabelText();
            pane_acc.toFront();
        }
        else if (actionEvent.getSource() == btn_event) {
            pane_event.toFront();
        }
    }

    public void setLabelText() throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getVolData();

        while (resultSet.next()) {
            name = resultSet.getString(2);
            age = resultSet.getString(3);
            sex = resultSet.getString(4);
            email = resultSet.getString(5);
            phone = resultSet.getString(6);
        }

        databaseHandler.deleteVolData();

        label_name.setText(name);
        label_age.setText(age);
        label_sex.setText(sex);
        label_email.setText(email);
        label_phone.setText(phone);
    }
}
