package com.example.techprom_vol;

import javafx.event.*;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Window;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class Registration{

    private String fullName;

    public String getFullName() {
        return fullName;
    }
    private String emailLogin;

    public String getEmailLogin() {
        return emailLogin;
    }
    private String password;

    public String getPassword() {
        return password;
    }

    void sceneUIControls(GridPane gridPane) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Label headerLabel = new Label("Регистрация");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Label nameLabel = new Label("ФИО: ");
        nameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gridPane.add(nameLabel, 0,1);

        TextField nameField = new TextField();
        nameField.setPrefHeight(40);
        gridPane.add(nameField, 1,1);

        Label emailLabel = new Label("Email: ");
        emailLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gridPane.add(emailLabel, 0, 2);

        TextField emailField = new TextField();
        emailField.setPrefHeight(40);
        gridPane.add(emailField, 1, 2);

        Label passwordLabel = new Label("Пароль: ");
        passwordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gridPane.add(passwordLabel, 0, 3);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        Button continueButton = new Button("Продолжить");
        continueButton.setPrefHeight(40);
        continueButton.setDefaultButton(true);
        continueButton.setPrefWidth(100);
        gridPane.add(continueButton, 0, 4, 2, 1);
        GridPane.setHalignment(continueButton, HPos.CENTER);
        GridPane.setMargin(continueButton, new Insets(20, 0,20,0));

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nameField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Пожалуйста, заполните поле 'ФИО'!");
                    return;
                }
                if (emailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Пожалуйста, заполните поле 'Email'!");
                    return;
                }
                if (passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Пожалуйста, заполните поле 'Пароль'!");
                    return;
                }

                fullName = nameField.getText();
                emailLogin = emailField.getText();
                password = passwordField.getText();

                ButtonController buttonController = new ButtonController();
                buttonController.continueReg(continueButton);

                DatabaseHandler databaseHandler = new DatabaseHandler();
                try {
                    databaseHandler.volRegistrationPanel1(fullName, emailLogin, password);
                } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                         InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    GridPane createRegistrationFormPane() {
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

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
