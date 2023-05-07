package com.example.techprom_vol;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class AdminAccount {
    @FXML
    private Button btn_vol;

    @FXML
    private Button btn_event;

    @FXML
    private Button btn_activeEvent;

    @FXML
    private Pane pane_vol;

    @FXML
    private Pane pane_event;

    @FXML
    private Pane pane_activeEvent;

    @FXML
    private void handleButtonAction(ActionEvent actionEvent) {
        if (actionEvent.getSource() == btn_vol) {
            pane_vol.toFront();
        }
        else if (actionEvent.getSource() == btn_event) {
            pane_event.toFront();
        }
        else if (actionEvent.getSource() == btn_activeEvent) {
            pane_activeEvent.toFront();
        }
    }


    GridPane createVAPane() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(40, 40, 40, 40));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }
}
