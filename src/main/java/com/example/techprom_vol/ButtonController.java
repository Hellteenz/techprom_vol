package com.example.techprom_vol;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class ButtonController {
    @FXML
    public void toRegistration(Button button){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Registration registration = new Registration();
                GridPane gridPane = registration.createRegistrationFormPane();
                Main.stage.setScene(new Scene(gridPane,800, 500));
                try {
                    registration.sceneUIControls(gridPane);
                } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                         InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
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

    public void continueReg(Button button){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                UserDataREG userDataREG = new UserDataREG();
                GridPane gridPane = userDataREG.createUserDataREGPane();
                Main.stage.setScene(new Scene(gridPane,800, 500));
                try {
                    userDataREG.sceneUIControls(gridPane);
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void toVolAccount(Button button){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("VolAccount.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 800, 500);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Main.stage.setScene(scene);
                Main.stage.show();
            }
        });
    }

    public void toAdmAccount(Button button){
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminAccount.fxml"));
                Scene scene = null;
                try {
                    scene = new Scene(fxmlLoader.load(), 800, 500);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Main.stage.setScene(scene);
                Main.stage.show();
            }
        });
    }
}
