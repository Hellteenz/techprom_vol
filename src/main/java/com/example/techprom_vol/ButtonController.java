package com.example.techprom_vol;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class ButtonController {
    @FXML
    public void toRegistration(Button button){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Registration registration = new Registration();
                GridPane gridPane = registration.createRegistrationFormPane();
                Main.stage.setScene(new Scene(gridPane,800, 500));
                registration.sceneUIControls(gridPane);
            }
        });
    }
    public void toLogin(Button button){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Login login = new Login();
                GridPane gridPane = login.createStartPane();
                Main.stage.setScene(new Scene(gridPane,800, 500));
                login.sceneUIControls(gridPane);
            }
        });
    }
}
