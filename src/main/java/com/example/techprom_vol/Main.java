package com.example.techprom_vol;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class Main extends Application {
    static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }
//    HomePanel homePanel = new HomePanel();
//    @Override
//    public void start(Stage stage) {
//        this.stage = stage;
//        GridPane gridPane = homePanel.homePanelPane();
//        homePanel.sceneUIControls(gridPane);
//        Scene scene = new Scene(gridPane, 800, 500);
//        stage.setScene(scene);
//
//        stage.show();
//    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        VolAccount volAccount = new VolAccount();
        GridPane gridPane = volAccount.createVAPane();
        volAccount.volUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}