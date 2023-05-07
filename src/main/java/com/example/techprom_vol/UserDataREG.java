package com.example.techprom_vol;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserDataREG {
    private String age;

    public String getAge() {
        return age;
    }
    public String sex;
    private String phone;

    private String fullName = Registration.fullName;
    private String emailLogin = Registration.emailLogin;
    private String password = Registration.password;
    public String getPhone() {
        return phone;
    }

    void sceneUIControls(GridPane gridPane) throws SQLException, ClassNotFoundException {
        Label headerLabel = new Label("Личные данные");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        Label ageLabel = new Label("Дата рождения \n(ДД.ММ.ГГГГ): ");
        ageLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gridPane.add(ageLabel, 0, 1);

        TextField ageField = new TextField();
        ageField.setPrefHeight(40);
        ageField.setPrefWidth(20);
        gridPane.add(ageField, 1, 1);

        Label sexLabel = new Label("Пол: ");
        sexLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gridPane.add(sexLabel, 0, 2);

        RadioButton maleButton = new RadioButton("Male");
        maleButton.setPrefHeight(10);
        gridPane.add(maleButton, 1, 2);

        RadioButton femaleButton = new RadioButton("Female");
        femaleButton.setPrefHeight(10);
        gridPane.add(femaleButton, 1, 3);

        Label phoneLabel = new Label("Телефон: ");
        phoneLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        gridPane.add(phoneLabel, 0, 5);

        TextField phoneField = new TextField();
        phoneField.setPrefHeight(40);
        phoneField.setPrefWidth(20);
        gridPane.add(phoneField, 1, 5);

        Button continueButton = new Button("Продолжить");
        continueButton.setPrefHeight(40);
        continueButton.setDefaultButton(true);
        continueButton.setPrefWidth(100);
        gridPane.add(continueButton, 0, 6, 2, 1);
        GridPane.setHalignment(continueButton, HPos.CENTER);
        GridPane.setMargin(continueButton, new Insets(20, 0, 20, 0));

        ToggleGroup group = new ToggleGroup();
        maleButton.setToggleGroup(group);
        femaleButton.setToggleGroup(group);

        DatabaseHandler databaseHandler = new DatabaseHandler();

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){

            public void changed(ObservableValue<? extends Toggle> changed, Toggle oldValue, Toggle newValue){
                RadioButton selectedBtn = (RadioButton) newValue;
                sex = selectedBtn.getText();
                System.out.println(sex);
            }
        });

        continueButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ageField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Пожалуйста, заполните поле 'Дата рождения'!");
                    return;
                }

                if (phoneField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Пожалуйста, заполните поле 'Телефон'!");
                    return;
                }

                age = ageField.getText();
                phone = phoneField.getText();

                System.out.println(fullName);
                System.out.println(emailLogin);
                System.out.println(password);
                System.out.println(age);
                System.out.println(sex);
                System.out.println(phone);

                try {
                    databaseHandler.volRegistrationPanel(fullName, emailLogin, password, sex, age, phone);
                } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                         InstantiationException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }

                ButtonController buttonController = new ButtonController();
                buttonController.toAccount(continueButton);


            }
        });

        ButtonController buttonController = new ButtonController();
        buttonController.toAccount(continueButton);
    }

    GridPane createUserDataREGPane() {
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
