package com.example.techprom_vol;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs {

    Connection successfulConnection;
    public Connection getDbConnection() {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        try{
            String username = "root";
            String password = "12345";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                successfulConnection = DriverManager.getConnection(connectionString, username, password);
                System.out.println("Connection to Store DB successful!");
            } catch (Exception ignored) {}
        } catch(Exception ex){
            System.out.println("Connection failed...");
        }

        return successfulConnection;
    }

    public void volRegistrationPanel(String fullName, String email, String password, String age, String sex, String phone) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String insert = "INSERT INTO " + Constants.USER_TABLE + " (" +
                Constants.USERS_FULL_NAME + "," + Constants.USERS_LOGIN_EMAIL + "," +
                Constants.USERS_PASSWORD + "," + Constants.USERS_AGE + "," +
                Constants.USERS_SEX + "," + Constants.USERS_PHONE + ")" + "VALUES(?,?,?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, fullName);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);
        preparedStatement.setString(4, sex);
        preparedStatement.setString(5, age);
        preparedStatement.setString(6, phone);

        preparedStatement.executeUpdate();
    }

    public ResultSet getUser(User user) throws SQLException {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Constants.USER_TABLE + " WHERE " + Constants.USERS_LOGIN_EMAIL
                + " =? AND " + Constants.USERS_PASSWORD + " =?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, user.getLoginEmail());
        preparedStatement.setString(2, user.getPassword());

        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getAdmin(User user) throws SQLException {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Constants.ADMINS_TABLE + " WHERE " + Constants.ADMIN_EMAIL
                + " =? AND " + Constants.ADMIN_PASSWORD + " =?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, user.getLoginEmail());
        preparedStatement.setString(2, user.getPassword());

        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public void createEvent(String name, int minAge, int firstStaff, int secondStaff, String info) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String insert = "INSERT INTO " + Constants.EVENTS_TABLE + " (" +
                Constants.EVENT_NAME + "," + Constants.EVENT_MINAGE + "," +
                Constants.EVENT_FSTAFF + "," + Constants.EVENT_SSTAFF + "," +
                Constants.EVENT_INFO + ")" + "VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, minAge);
        preparedStatement.setInt(3, firstStaff);
        preparedStatement.setInt(4, secondStaff);
        preparedStatement.setString(5, info);

        preparedStatement.executeUpdate();
    }

    public ResultSet getAllVolunteers() throws SQLException {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Constants.USER_TABLE;

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public void addVolData(String fullName, String age, String sex, String email, String phone) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String insert = "INSERT INTO " + Constants.DATA_TABLE + " (" +
                Constants.DATA_NAME + "," + Constants.DATA_AGE + "," +
                Constants.DATA_SEX + "," + Constants.DATA_EMAIL + "," +
                Constants.DATA_PHONE + ")" + "VALUES(?,?,?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, fullName);
        preparedStatement.setString(2, age);
        preparedStatement.setString(3, sex);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, phone);

        preparedStatement.executeUpdate();
    }

    public ResultSet getVolData() throws SQLException {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Constants.DATA_TABLE;

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public void deleteVolData() throws SQLException {

        String select = "DELETE FROM " + Constants.DATA_TABLE;

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.executeUpdate();

    }

    public ResultSet getAllEvents() throws SQLException {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Constants.EVENTS_TABLE;

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getEvent(String name) throws SQLException {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Constants.EVENTS_TABLE + " WHERE " + Constants.EVENT_NAME + " =?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, name);

        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public ResultSet getVolunteer(String currentEvent) throws SQLException {
        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Constants.APPLICATION_TABLE + " WHERE " + Constants.APPLICATION_EVENT_NAME + " =?";

        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        preparedStatement.setString(1, currentEvent);

        resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    public void sendApplication(String volEmail, String eventName) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String insert = "INSERT INTO " + Constants.APPLICATION_TABLE + " (" +
                Constants.APPLICATION_VOLUNTEER_EMAIL + "," + Constants.APPLICATION_EVENT_NAME
                + ")" + "VALUES(?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, volEmail);
        preparedStatement.setString(2, eventName);

        preparedStatement.executeUpdate();
    }

}
