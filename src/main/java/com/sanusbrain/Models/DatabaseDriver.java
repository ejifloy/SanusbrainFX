package com.sanusbrain.Models;

import java.sql.*;

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


    /*
    * Client Section
    * */


    /*
    * Utility Methods
    * */
}
