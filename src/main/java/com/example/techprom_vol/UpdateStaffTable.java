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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class UpdateStaffTable {

    @FXML private Button btn_delete;
    @FXML private Button exitButton;
    @FXML private Label label_name;
    @FXML private Label label_status;
    @FXML private ComboBox<String> change_status;

    public static boolean checkDelete = false;
    public String volName;
    public String status;

    public static String changeStatusStr;

    private final ObservableList<String> statuses = FXCollections.observableArrayList();

    public UpdateStaffTable(String volName, String status) {
        this.volName = volName;
        this.status = status;
    }

    public void createTable() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/updateStaffTables.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 400, 250);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        label_name.setText(volName);
        label_status.setText(status);
    }
    @FXML
    private void handleButtonAction(ActionEvent actionEvent) throws SQLException {
        if (actionEvent.getSource() == btn_delete) {
            checkDelete = true;
        }
        else if (actionEvent.getSource() == exitButton) {
            //stage.close();
        }
        statuses.add("Основной");
        statuses.add("Резервный");
        change_status.setItems(statuses);//<3

        String setValueStatus;
        if (Objects.equals(status, "first")) setValueStatus = "Основной";
        else setValueStatus = "Резервный";
        change_status.setValue(setValueStatus);

        change_status.setOnAction(event -> changeStatusStr = change_status.getValue());
    }
}
