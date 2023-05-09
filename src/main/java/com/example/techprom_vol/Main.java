package com.example.techprom_vol;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.stage.*;

public class Main extends Application {
    static Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    HomePanel homePanel = new HomePanel();

    @Override
    public void start(Stage stage) {
        Main.stage = stage;
        GridPane gridPane = homePanel.homePanelPane();
        homePanel.sceneUIControls(gridPane);
        Scene scene = new Scene(gridPane, 800, 500);
        stage.setScene(scene);

        stage.show();
    }
}
