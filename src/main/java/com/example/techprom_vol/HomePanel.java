package com.example.techprom_vol;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomePanel {
    void sceneUIControls(GridPane gridPane) {
        Label headerLabel = new Label("Вход");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        gridPane.add(headerLabel, 0,0,  2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(-50, 0,0,0));

        Button toLogButton = new Button("Войти в аккаунт");
        toLogButton.setPrefHeight(80);
        toLogButton.setDefaultButton(true);
        toLogButton.setPrefWidth(400);
        gridPane.add(toLogButton, 0, 4, 2, 1);
        GridPane.setHalignment(toLogButton, HPos.CENTER);
        GridPane.setMargin(toLogButton, new Insets(-100, 0,20,0));

        Button toRegButton = new Button("Зарегистрироваться");
        toRegButton.setPrefHeight(80);
        toRegButton.setDefaultButton(true);
        toRegButton.setPrefWidth(400);
        gridPane.add(toRegButton, 0, 4, 2, 1);
        GridPane.setHalignment(toRegButton, HPos.CENTER);
        GridPane.setMargin(toRegButton, new Insets(100, 0,20,0));

        ButtonController buttonController = new ButtonController();
        buttonController.toRegistration(toRegButton);
        buttonController.toLogin(toLogButton);

    }

    GridPane homePanelPane() {
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
