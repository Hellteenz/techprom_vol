package com.example.techprom_vol;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Window;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.lang.reflect.InvocationTargetException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Login {
    public GridPane alertGridPane;
    public String emailLogin;
    public String password;
    void sceneUIControls(GridPane gridPane) {
        Label headerLabel = new Label("Вход");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        Label nameLabel = new Label("Логин: ");
        nameLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gridPane.add(nameLabel, 0,1);

        TextField loginEmailField = new TextField();
        loginEmailField.setPrefHeight(40);
        gridPane.add(loginEmailField, 1,1);

        Label passwordLabel = new Label("Пароль: ");
        passwordLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gridPane.add(passwordLabel, 0, 3);

        PasswordField passwordField = new PasswordField();
        passwordField.setPrefHeight(40);
        gridPane.add(passwordField, 1, 3);

        Button loginButton = new Button("Войти");
        loginButton.setPrefHeight(40);
        loginButton.setDefaultButton(true);
        loginButton.setPrefWidth(100);
        gridPane.add(loginButton, 0, 4, 2, 1);
        GridPane.setHalignment(loginButton, HPos.CENTER);
        GridPane.setMargin(loginButton, new Insets(20, 0,20,0));

        Button toRegButton = new Button("Зарегистрироваться");
        loginButton.setPrefHeight(40);
        loginButton.setDefaultButton(true);
        loginButton.setPrefWidth(100);
        gridPane.add(toRegButton, 0, 5, 2, 1);
        GridPane.setHalignment(toRegButton, HPos.CENTER);
        GridPane.setMargin(toRegButton, new Insets(20, 0,20,0));

        alertGridPane = gridPane;

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (loginEmailField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Пожалуйста, заполните поле 'Логин'!");
                    return;
                }
                if (passwordField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Пожалуйста, заполните поле 'Пароль'!");
                    return;
                }

                emailLogin = loginEmailField.getText();
                password = passwordField.getText();

                AdminAccount admAcc = new AdminAccount();
                List<String> adminEmails = admAcc.getAdminEmail();
                boolean check = Boolean.FALSE;
                for (String login: adminEmails) {
                    if (login.equals(emailLogin)) {
                        check = Boolean.TRUE;
                        break;
                    }
                }
                if (check) {
                    try {
                        loginAdmin(emailLogin, password, loginButton);
                    } catch (SQLException | NoSuchPaddingException | IllegalBlockSizeException |
                             NoSuchAlgorithmException |
                             BadPaddingException | InvalidKeyException e) {
                        throw new RuntimeException(e);
                    }
                } else {

                    try {
                        loginUser(emailLogin, password, loginButton);
                    } catch (SQLException | NoSuchPaddingException | IllegalBlockSizeException |
                             NoSuchAlgorithmException | BadPaddingException | InvalidKeyException |
                             ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                             InstantiationException | IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        toRegButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ButtonController buttonController = new ButtonController();
                buttonController.toRegistration(toRegButton);
            }
        }

        );
    }

    public void loginUser(String loginEmail, String password, Button loginButton) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        AES aes = new AES();
        user.setLoginEmail(loginEmail);
        user.setPassword(aes.cipher(password));
        ResultSet resultSet = dbHandler.getUser(user);

        int cnt = 0;
        while (resultSet.next()) {
            cnt++;
            user.setFullname(resultSet.getString(2));
            user.setAge(resultSet.getString(6));
            user.setSex(resultSet.getString(5));
            user.setPhone(resultSet.getString(7));
        }

        if (cnt > 0) {
            dbHandler.addVolData(user.getFullname(), user.getAge(), user.getSex(), user.getLoginEmail(),
                    user.getPhone());
            ButtonController buttonController = new ButtonController();
            buttonController.toVolAccount(loginButton);
            dbHandler.deleteVolData();
        }
        else {
            showAlert(Alert.AlertType.ERROR, alertGridPane.getScene().getWindow(), "Login Error!",
                    "Пожалуйста, проверьте правильность данных. Если Вы не были зарегистрированны" +
                            ", вернитесь и пройдите eё.");
        }
    }

    public void loginAdmin(String loginEmail, String password, Button loginButton) throws SQLException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        User user = new User();
        user.setLoginEmail(loginEmail);
        user.setPassword(password);
        ResultSet resultSet = dbHandler.getAdmin(user);

        int cnt = 0;
        while (resultSet.next()) {
            cnt++;
        }

        if (cnt > 0) {
            ButtonController buttonController = new ButtonController();
            buttonController.toAdmAccount(loginButton);

        }
        else {
            showAlert(Alert.AlertType.ERROR, alertGridPane.getScene().getWindow(), "Login Error!",
                    "Пожалуйста, проверьте правильность данных. Если Вы не были зарегистрированны" +
                            ", вернитесь и пройдите eё.");
        }
    }


    GridPane createStartPane() {
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
