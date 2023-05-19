package com.example.techprom_vol;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Window;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AdminAccount extends Constants {
    @FXML private Button btn_vol;
    @FXML private Button btn_event;
    @FXML private Button btn_activeEvent;
    @FXML private Button toCreateNewEvent;
    @FXML private Button end_forCreateNewEvent_btn;
    @FXML private Button btn_table_update;
    @FXML private Button btn_updateActiveEvent;
    @FXML private Button btn_exit;
    @FXML private Button changeFS_btn;
    @FXML private Button changeSS_btn;

    @FXML private Pane pane_vol;
    @FXML private Pane pane_event;
    @FXML private Pane pane_activeEvent;
    @FXML private Pane pane_createNewEvent;
    @FXML private Pane default_pane;
    @FXML private Pane changeFS_pane;
    @FXML private Pane changeSS_pane;
    
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

    @FXML private TableView<User> table_firstStaff;
    @FXML private TableColumn<User, String> column_fs_name;
    @FXML private TableColumn<User, String> column_fs_age;
    @FXML private TableColumn<User, String> column_fs_email;
    @FXML private TableColumn<User, String> column_fs_phone;

    @FXML private TableView<User> table_secondStaff;
    @FXML private TableColumn<User, String> column_ss_name;
    @FXML private TableColumn<User, String> column_ss_age;
    @FXML private TableColumn<User, String> column_ss_email;
    @FXML private TableColumn<User, String> column_ss_phone;

    @FXML private TableView<EventForm> table_eventActive;
    @FXML private TableColumn<EventForm, String> column_eventA;

    public static String currentEvent;
    public String eventName;
    public static String status;
    public int minAge;
    public String info;
    public int firstStaff;
    public int secondStaff;
    public static String currentVolName;


    private ObservableList<User> volDataAllVol = FXCollections.observableArrayList();
    private ObservableList<User> volDataFS = FXCollections.observableArrayList();
    private ObservableList<User> volDataSS = FXCollections.observableArrayList();
    private ObservableList<EventForm> activeEvents = FXCollections.observableArrayList();


    @FXML
    private void handleButtonAction(ActionEvent actionEvent) throws SQLException {
        if (actionEvent.getSource() == btn_vol) {
            pane_vol.toFront();
        }
        else if (actionEvent.getSource() == btn_event) {
            pane_event.toFront();
        }
        else if (actionEvent.getSource() == btn_exit) {
            ButtonController buttonController = new ButtonController();
            buttonController.toLogin(btn_exit);
        }
        else if (actionEvent.getSource() == btn_activeEvent) {
            pane_activeEvent.toFront();
        }
        else if (actionEvent.getSource() == btn_updateActiveEvent) {
            for ( int i = 0; i < table_eventActive.getItems().size(); i++) {
                table_eventActive.getItems().clear();
            }
            for ( int i = 0; i < table_firstStaff.getItems().size(); i++) {
                table_firstStaff.getItems().clear();
            }
            for ( int i = 0; i < table_secondStaff.getItems().size(); i++) {
                table_secondStaff.getItems().clear();
            }
            DatabaseHandler databaseHandler = new DatabaseHandler();
            makeTableActiveEvent();
            TableView.TableViewSelectionModel<EventForm> selectionModel = table_eventActive.getSelectionModel();
            selectionModel.selectedItemProperty().addListener(new ChangeListener<EventForm>() {
                @Override
                public void changed(ObservableValue<? extends EventForm> observable, EventForm oldValue, EventForm newValue) {
                    try {
                        currentEvent = newValue.getT_eventName();
                        getEventInform(databaseHandler, currentEvent);
                        makeTableStaff(currentEvent, firstStaff, secondStaff);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
        else if (actionEvent.getSource() == toCreateNewEvent) {
            pane_createNewEvent.toFront();
        }
        else if (actionEvent.getSource() == btn_table_update){
            for ( int i = 0; i < vol_table.getItems().size(); i++) {
                vol_table.getItems().clear();
            }
            makeTableAllVol();
        }
        else if (actionEvent.getSource() == end_forCreateNewEvent_btn) {
            AnchorPane alertGridPane = anchorPane_full;
            showAlert(Alert.AlertType.INFORMATION, alertGridPane.getScene().getWindow(), "Success!",
                    "Мероприятие успешно создано!");
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
        } else if (actionEvent.getSource() == changeFS_btn || actionEvent.getSource() == changeSS_btn) {
            btnReactionOnListener();
        }
    }

    public void makeTableAllVol() throws SQLException {
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getAllVolunteers();

        while (resultSet.next()) {
            String fullName = resultSet.getString(Constants.USERS_FULL_NAME);
            String age = resultSet.getString(Constants.USERS_AGE);
            String email = resultSet.getString(Constants.USERS_LOGIN_EMAIL);
            String phone = resultSet.getString(Constants.USERS_PHONE);

            volDataAllVol.add(new User(fullName, age, email, phone));
        }
        column_fullName.setCellValueFactory(cellData -> cellData.getValue().t_fullNameProperty());
        column_age.setCellValueFactory(cellData -> cellData.getValue().t_ageProperty());
        column_email.setCellValueFactory(cellData -> cellData.getValue().t_emailProperty());
        column_phone.setCellValueFactory(cellData -> cellData.getValue().t_phoneProperty());

        vol_table.setItems(volDataAllVol);
    }

    public void makeTableActiveEvent() throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getAllEvents();

        while (resultSet.next()) {
            String eventName = resultSet.getString(Constants.EVENT_NAME);
            int minAge = resultSet.getInt(Constants.EVENT_MINAGE);
            String info = resultSet.getString(Constants.EVENT_INFO);
            int firstStaff = resultSet.getInt(Constants.EVENT_FSTAFF);
            int secondStaff = resultSet.getInt(Constants.EVENT_SSTAFF);

            activeEvents.add(new EventForm(eventName, minAge, info, firstStaff, secondStaff));
        }
        column_eventA.setCellValueFactory(cellData -> cellData.getValue().t_eventNameProperty());

        table_eventActive.setItems(activeEvents);
    }

    public void makeTableStaff(String currentEvent, int firstStaff, int secondStaff) throws SQLException {
        clearTable(table_firstStaff);
        clearTable(table_secondStaff);
        DatabaseHandler dbHandler = new DatabaseHandler();
        ResultSet resultSet = dbHandler.getAllVolunteers();
        ResultSet resSet = dbHandler.getVolunteer(currentEvent);

        String strVolEmailForCurrentEvent = "";
        int cntRightVol = 0;

        while (resSet.next()) {
            String email = resSet.getString(Constants.APPLICATION_VOLUNTEER_EMAIL);
            strVolEmailForCurrentEvent += (email + " ");
        }
        List<String> emailForCurrentEvent = Arrays.stream(strVolEmailForCurrentEvent.split(" ")).toList();

        while (resultSet.next()) {
            String fullName = resultSet.getString(Constants.USERS_FULL_NAME);
            String age = resultSet.getString(Constants.USERS_AGE);
            String email = resultSet.getString(Constants.USERS_LOGIN_EMAIL);
            String phone = resultSet.getString(Constants.USERS_PHONE);

            String status = getStatus(currentEvent, email, dbHandler);
            if (Objects.equals(status, "first")) {
                volDataFS.add(new User(fullName, age, email, phone));
                dbHandler.setStatus(currentEvent, email, "first");
                cntRightVol++;
            } else if (Objects.equals(status, "second")) {
                volDataSS.add(new User(fullName, age, email, phone));
                cntRightVol++;
                dbHandler.setStatus(currentEvent, email, "second");
            }
        }
        while (resultSet.next()) {
            String fullName = resultSet.getString(Constants.USERS_FULL_NAME);
            String age = resultSet.getString(Constants.USERS_AGE);
            String email = resultSet.getString(Constants.USERS_LOGIN_EMAIL);
            String phone = resultSet.getString(Constants.USERS_PHONE);

            String status = getStatus(currentEvent, email, dbHandler);
            if (emailForCurrentEvent.contains(email) && cntRightVol <= firstStaff - 1
                    && Objects.equals(status, "commit")) {
                volDataFS.add(new User(fullName, age, email, phone));
                dbHandler.setStatus(currentEvent, email, "first");
                cntRightVol++;
            } else if (emailForCurrentEvent.contains(email) && cntRightVol <= (firstStaff  + secondStaff - 1)
                    && Objects.equals(status, "commit")) {
                volDataSS.add(new User(fullName, age, email, phone));
                cntRightVol++;
                dbHandler.setStatus(currentEvent, email, "second");
            }
        }
        column_fs_name.setCellValueFactory(cellData -> cellData.getValue().fs_fullNameProperty());
        column_fs_age.setCellValueFactory(cellData -> cellData.getValue().fs_ageProperty());
        column_fs_email.setCellValueFactory(cellData -> cellData.getValue().fs_emailProperty());
        column_fs_phone.setCellValueFactory(cellData -> cellData.getValue().fs_phoneProperty());

        column_ss_name.setCellValueFactory(cellData -> cellData.getValue().ss_fullNameProperty());
        column_ss_age.setCellValueFactory(cellData -> cellData.getValue().ss_ageProperty());
        column_ss_email.setCellValueFactory(cellData -> cellData.getValue().ss_emailProperty());
        column_ss_phone.setCellValueFactory(cellData -> cellData.getValue().ss_phoneProperty());

        table_firstStaff.setItems(volDataFS);
        table_secondStaff.setItems(volDataSS);
        listenerStaffTables(table_firstStaff);
        listenerStaffTables(table_secondStaff);
    }

    public void clearTable(TableView<User> table) {
        for ( int i = 0; i < table.getItems().size(); i++) {
            table.getItems().clear();
        }
    }

    public void listenerStaffTables(TableView<User> table) {
        TableView.TableViewSelectionModel<User> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                currentVolName = newValue.getT_fullName();
                if (table == table_firstStaff) {
                    changeFS_pane.toBack();
                    changeSS_pane.toFront();
                } else {
                    changeSS_pane.toBack();
                    changeFS_pane.toFront();
                }
            }
        });
    }

    public void btnReactionOnListener() throws SQLException {
        DatabaseHandler databaseHandler = new DatabaseHandler();
        ResultSet resultSet = databaseHandler.getEmail(currentVolName);
        String email = null;
        while (resultSet.next()) {
            email = resultSet.getString(Constants.USERS_LOGIN_EMAIL);
        }
        status = getStatus(currentEvent, email, databaseHandler);
        UpdateStaffTable updateStaffTable = new UpdateStaffTable();
        updateStaffTable.createTable();
    }

    public String getStatus(String currentEvent, String volEmail, DatabaseHandler databaseHandler) throws SQLException {
        ResultSet resultSet = databaseHandler.getStatus(currentEvent, volEmail);
        String status = null;
        while (resultSet.next()) {
            status = resultSet.getString(Constants.APPLICATION_STATUS);
        }
        return status;
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
        emails.add("1");

        return emails;
    }

    public String getCurrentEvent() {
        return currentEvent;
    }
}
