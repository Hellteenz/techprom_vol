package com.example.techprom_vol;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Window;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminAccount extends Constants {
    @FXML private Button btn_vol;
    @FXML private Button btn_event;
    @FXML private Button btn_activeEvent;
    @FXML private Button toCreateNewEvent;
    @FXML private Button end_forCreateNewEvent_btn;
    @FXML private Button btn_table_update;

    @FXML private Pane pane_vol;
    @FXML private Pane pane_event;
    @FXML private Pane pane_activeEvent;
    @FXML private Pane pane_createNewEvent;
    @FXML private Pane default_pane;
    @FXML private AnchorPane anchorPane_full;

    @FXML private TextField event_name_field;
    @FXML private TextField event_minAge_field;
    @FXML private TextField event_firstStaff_field;
    @FXML private TextField event_secondStaff_field;
    @FXML private TextArea event_information_area;

    @FXML private TableView<User> vol_table;
    @FXML private TableColumn<User, String> column_fullName;
    @FXML private TableColumn<User, String> column_age;
    @FXML private TableColumn<User, String> column_email;
    @FXML private TableColumn<User, String> column_phone;


    private ObservableList<User> volData = FXCollections.observableArrayList();


    @FXML
    private void handleButtonAction(ActionEvent actionEvent) throws SQLException {
        if (actionEvent.getSource() == btn_vol) {
            pane_vol.toFront();
        }
        else if (actionEvent.getSource() == btn_event) {
            pane_event.toFront();
        }
        else if (actionEvent.getSource() == btn_activeEvent) {
            pane_activeEvent.toFront();
        }
        else if (actionEvent.getSource() == toCreateNewEvent) {
            pane_createNewEvent.toFront();
        }
        else if (actionEvent.getSource() == btn_table_update){
            DatabaseHandler dbHandler = new DatabaseHandler();
            ResultSet resultSet = dbHandler.getAllVolunteers();

            while (resultSet.next()) {
                String fullName = resultSet.getString(Constants.USERS_FULL_NAME);
                String age = resultSet.getString(Constants.USERS_AGE);
                String email = resultSet.getString(Constants.USERS_LOGIN_EMAIL);
                String phone = resultSet.getString(Constants.USERS_PHONE);

                volData.add(new User(fullName, age, email, phone));
            }
            column_fullName.setCellValueFactory(cellData -> cellData.getValue().t_fullNameProperty());
            column_age.setCellValueFactory(cellData -> cellData.getValue().t_ageProperty());
            column_email.setCellValueFactory(cellData -> cellData.getValue().t_emailProperty());
            column_phone.setCellValueFactory(cellData -> cellData.getValue().t_phoneProperty());

            vol_table.setItems(volData);
        }
        else if (actionEvent.getSource() == end_forCreateNewEvent_btn) {
            AnchorPane alertGridPane = anchorPane_full;
            showAlert(Alert.AlertType.INFORMATION, alertGridPane.getScene().getWindow(), "Success!",
                    "Мероприятие усешно создано!");
            DatabaseHandler databaseHandler = new DatabaseHandler();

            EventForm newEvent = new EventForm();
            newEvent.setEventName(event_name_field.getText());
            newEvent.setMinAge(Integer.parseInt(event_minAge_field.getText()));
            newEvent.setFirstStaff(Integer.parseInt(event_firstStaff_field.getText()));
            newEvent.setSecondStaff(Integer.parseInt(event_secondStaff_field.getText()));
            newEvent.setInformation(event_information_area.getText());

            try {
                databaseHandler.createEvent(newEvent.getEventName(), newEvent.getMinAge(),
                        newEvent.getFirstStaff(), newEvent.getSecondStaff(), newEvent.getInformation());
            } catch (SQLException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
            default_pane.toFront();
        }
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public List<String> getAdminEmail() {
        List<String> emails = new ArrayList<>();
        emails.add("test@admin");

        return emails;
    }
}
