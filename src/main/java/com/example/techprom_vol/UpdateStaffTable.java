package com.example.techprom_vol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UpdateStaffTable {

    @FXML private Button btn_delete;
    @FXML private Button save_btn;
    @FXML private Button toChanging_btn;

    @FXML private Label label_name;
    @FXML private Label label_status;
    @FXML private ComboBox<String> change_status;

    @FXML private Pane default_pane;

    public boolean checkDelete = false;
    public String volName = AdminAccount.currentVolName;
    public String status = AdminAccount.status;
    public String currentEvent = AdminAccount.currentEvent;

    public static String changeStatusStr;

    private final ObservableList<String> statuses = FXCollections.observableArrayList();

    public void createTable() {
        try {
            URL fxmlLocation = getClass().getResource("UpdateStaffTable.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 400, 250);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @FXML
    private void handleButtonAction(ActionEvent actionEvent) throws SQLException {
        if (actionEvent.getSource() == btn_delete) {
            checkDelete = true;
        }
        else if (actionEvent.getSource() == toChanging_btn) {
            default_pane.toBack();
            label_name.setText(volName);

            if (statuses.size() == 0) {
                statuses.add("Основной");
                statuses.add("Резервный");
            }
            change_status.setItems(statuses);//<3

            String setValueStatus;
            if (Objects.equals(status, "first")) setValueStatus = "Основной";
            else setValueStatus = "Резервный";
            label_status.setText(setValueStatus);
            change_status.setValue(setValueStatus);

            change_status.setOnAction(event -> {
                changeStatusStr = change_status.getValue();
                label_status.setText(changeStatusStr);
            });
        }
        else if (actionEvent.getSource() == save_btn) {
            if (checkDelete) {
                change_status.setValue("Исключен");
                changeStatusStr = "commit";
            }
            if (changeStatusStr.equals("Основной")) changeStatusStr = "first";
            else if (changeStatusStr.equals("Резервный")) changeStatusStr = "second";

            DatabaseHandler databaseHandler = new DatabaseHandler();
            ResultSet resultSet = databaseHandler.getEmail(volName);
            String email = null;
            while (resultSet.next()) {
                email = resultSet.getString(Constants.USERS_LOGIN_EMAIL);
            }
            databaseHandler.setStatus(currentEvent, email, changeStatusStr);
        }
    }
}
