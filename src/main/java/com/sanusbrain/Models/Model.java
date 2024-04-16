package com.sanusbrain.Models;

import com.sanusbrain.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Model {
    /*
    * Using Singleton-Design-Pattern to use one instance of ViewFactory
    * @param model
    * */
    private static Model model;
    private final ViewFactory viewFactory;
    private final DatabaseDriver databaseDriver;
    private boolean adminSuccessLoginFlag;

    //Admin Data Section
    private Patient patient;

    private Model(){
        this.viewFactory = new ViewFactory();
        this.databaseDriver = new DatabaseDriver();
        this.adminSuccessLoginFlag = false;
    }

    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory(){
        return viewFactory;
    }
    public boolean getAdminSuccessLoginFlag() {
        return this.adminSuccessLoginFlag;
    }
    public void setAdminSuccessLoginFlag(boolean flag){
        this.adminSuccessLoginFlag = flag;
    }



    /*
    * Admin Section
    * */
    public void evaluateAdminCredentials(String username, String password){
        ResultSet resultSet = databaseDriver.getAdminData(username,password);
        try {
            if(resultSet.isBeforeFirst()){
                //TODO: Add username check individually
                this.adminSuccessLoginFlag = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
