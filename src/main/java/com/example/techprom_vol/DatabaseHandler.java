package com.example.techprom_vol;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler extends Configs {

    Connection successfulConnection;
    public Connection getDbConnection() {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        try{
            String username = "root";
            String password = "12345";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection dbConnection = DriverManager.getConnection(connectionString, username, password)){
                successfulConnection = dbConnection;
                System.out.println("Connection to Store DB successful!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }

        return successfulConnection;
    }

    public void volRegistrationPanel1(String fullName, String email, String password) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String insert = "INSERT INTO " + Constants.USER_TABLE + " (" +
                Constants.USERS_FULL_NAME + "," + Constants.USERS_LOGIN_EMAIL + "," +
                Constants.USERS_PASSWORD + ")" + "VALUES(?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, fullName);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password);

        preparedStatement.executeUpdate();
    }
    public void volRegistrationPanel2(String age, String sex, String phone) throws SQLException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String insert = "INSERT INTO " + Constants.USER_TABLE + " (" +
                Constants.USERS_AGE + "," +
                Constants.USERS_SEX + "," + Constants.USERS_PHONE + ")" + "VALUES(?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, age);
        preparedStatement.setString(2, sex);
        preparedStatement.setString(3, phone);

        preparedStatement.executeUpdate();
    }
}
