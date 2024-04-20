package com.sanusbrain.Models;

import java.sql.*;
import java.time.LocalDate;

public class DatabaseDriver {
    private Connection connection;

    public DatabaseDriver(){
        try{
            this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sanusbrain","root","pw123456");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /*
    * Admin Section
    * */
    public ResultSet getAdminData(String username, String password){
        Statement statement;
        ResultSet resultSet = null;

        try {
            statement = this.connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM sanusbrain.users WHERE user_name='"+username+"' AND user_password='"+password+"';");
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

    public void createPatient(String firstname, String lastname, LocalDate birthdate, String gender){
        Statement statement;
        try {
            statement = this.connection.createStatement();
            statement.executeUpdate("INSERT INTO "+
                    "patient(firstname, lastname, birthdate, gender)"+
                    "VALUES ('"+firstname+"','"+lastname+"','"+birthdate+"','"+gender+"');");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    /*
    * Client Section
    * */


    /*
    * Utility Methods
    * */
}
