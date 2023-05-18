package com.example.techprom_vol;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Window;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class VolAccount {
    @FXML private Button btn_acc;
    @FXML private Button btn_event;
    @FXML private Button btn_update_eventTable;
    @FXML private Button btn_sendApplication;
    @FXML private Button btn_exit;

    @FXML private Pane pane_acc;
    @FXML private Pane pane_event;
    @FXML private Pane pane_eventInfo;
    @FXML private Pane pane_HIDEeventInfo;

    @FXML private Label label_name;
    @FXML private Label label_sex;
    @FXML private Label label_age;
    @FXML private Label label_email;
    @FXML private Label label_phone;
    @FXML private Label label_eventName;
    @FXML private Label label_eventMinAge;
    @FXML private Label label_eventAnotherInfo;

    @FXML private TableView<EventForm> eventTable;
    @FXML private TableColumn<EventForm, String> column_name;

    @FXML private AnchorPane anchor_full;

    public String name;
    public String age;
    public String sex;
    public String email;
    public String phone;
    public String eventName;
    public int minAge;
    public String info;
    public int firstStaff;
    public int secondStaff;
    public String currentEvent;

    private final ObservableList<EventForm> allEvents = FXCollections.observableArrayList();
    @FXML
    private void handleButtonAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (actionEvent.getSource() == btn_acc) {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            setLabelText(databaseHandler);
            pane_acc.toFront();
        }
        else if (actionEvent.getSource() == btn_exit) {
            ButtonController buttonController = new ButtonController();
            buttonController.toLogin(btn_exit);
        }
        else if (actionEvent.getSource() == btn_event) {
            pane_event.toFront();
        }
        else if (actionEvent.getSource() == btn_update_eventTable) {
            DatabaseHandler dbHandler = new DatabaseHandler();
            makeTable(dbHandler);

            TableView.TableViewSelectionModel<EventForm> selectionModel = eventTable.getSelectionModel();
            selectionModel.selectedItemProperty().addListener(new ChangeListener<EventForm>() {
                @Override
                public void changed(ObservableValue<? extends EventForm> observable, EventForm oldValue, EventForm newValue) {
                    try {
                        currentEvent = newValue.getT_eventName();
                        getEventInform(dbHandler, currentEvent);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    pane_HIDEeventInfo.toBack();
                    label_eventName.setText(eventName);
                    label_eventMinAge.setText(String.valueOf(minAge));
                    label_eventAnotherInfo.setText(infoRegex(info));
                }
            });
        }
        else if (actionEvent.getSource() == btn_sendApplication) {
            DatabaseHandler dbHandler = new DatabaseHandler();
            setLabelText(dbHandler);
            getEventInform(dbHandler, currentEvent);
            String[] ageSplit = age.trim().split("\\.");
            if (checkingApplication(eventName, email)) {
                AnchorPane alertGridPane = anchor_full;
                showAlert(Alert.AlertType.ERROR, alertGridPane.getScene().getWindow(), "Error!",
                        "Вы уже подавали заявку на это мероприятие!");
            }
            else if (ageFromBirth(Integer.parseInt(ageSplit[2]), Integer.parseInt(ageSplit[1]),
                    Integer.parseInt(ageSplit[0])) >= minAge) {
                String status = "commit";
                dbHandler.sendApplication(email, eventName, status);
                AnchorPane alertGridPane = anchor_full;
                showAlert(Alert.AlertType.INFORMATION, alertGridPane.getScene().getWindow(), "Success!",
                        "Заявка успешно подана!");
                pane_HIDEeventInfo.toFront();
            }
            else {
                AnchorPane alertGridPane = anchor_full;
                showAlert(Alert.AlertType.ERROR, alertGridPane.getScene().getWindow(), "Error!",
                        "Вы не можете подать завяку. Проверьте соответствие Вашей заявки и условий набора.");
            }
        }
    }


    public void setLabelText(DatabaseHandler databaseHandler) throws SQLException {
        ResultSet resultSet = databaseHandler.getVolData();

        while (resultSet.next()) {
            name = resultSet.getString(2);
            age = resultSet.getString(3);
            sex = resultSet.getString(4);
            email = resultSet.getString(5);
            phone = resultSet.getString(6);
        }

        label_name.setText(name);
        label_age.setText(age);
        label_sex.setText(sex);
        label_email.setText(email);
        label_phone.setText(phone);
    }

    public int ageFromBirth(int year, int month, int day) {
        LocalDateTime birthday = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime now = LocalDateTime.now();
        return (int) ChronoUnit.YEARS.between(birthday, now);
    }

    public void makeTable(DatabaseHandler databaseHandler) throws SQLException {
        for ( int i = 0; i < eventTable.getItems().size(); i++) {
            eventTable.getItems().clear();
        }
        ResultSet resultSet = databaseHandler.getAllEvents();

        while (resultSet.next()) {
            eventName = resultSet.getString(Constants.EVENT_NAME);
            minAge = resultSet.getInt(Constants.EVENT_MINAGE);
            info = resultSet.getString(Constants.EVENT_INFO);
            firstStaff = resultSet.getInt(Constants.EVENT_FSTAFF);
            secondStaff = resultSet.getInt(Constants.EVENT_SSTAFF);

            allEvents.add(new EventForm(eventName, minAge, info, firstStaff, secondStaff));
        }
        column_name.setCellValueFactory(cellData -> cellData.getValue().t_eventNameProperty());

        eventTable.setItems(allEvents);
    }

    public String infoRegex(String str) {
        String[] splitStr = str.split(" ");
        String strToReturn = "";
        int len = 0;
        for (String element: splitStr) {
            if (len + element.length() < 60) {
                len += (element.length() + 1);
                strToReturn += (element + " ");
            }
            else {
                strToReturn.trim();
                strToReturn += "\n";
                strToReturn += (element + " ");
                len = 0;
            }
        }
        return strToReturn;
    }

    public void getEventInform(DatabaseHandler databaseHandler, String nameEvent) throws SQLException {
        ResultSet resultSet = databaseHandler.getEvent(nameEvent);

        while (resultSet.next()) {
            eventName = resultSet.getString(Constants.EVENT_NAME);
            minAge = resultSet.getInt(Constants.EVENT_MINAGE);
            info = resultSet.getString(Constants.EVENT_INFO);
            firstStaff = resultSet.getInt(Constants.EVENT_FSTAFF);
            secondStaff = resultSet.getInt(Constants.EVENT_SSTAFF);
        }
    }

    public boolean checkingApplication(String currentEvent, String volEmail) throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getVolunteer(currentEvent);

        while (resultSet.next()) {
            String volEmailDB = resultSet.getString(Constants.APPLICATION_VOLUNTEER_EMAIL);
            if (volEmail.equals(volEmailDB)) {
                return true;
            }
        }
        return false;
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
