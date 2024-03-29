package com.sanusbrain.Models;

import com.sanusbrain.Views.ViewFactory;

public class Model {
    /*
    * Using Singleton-Pattern to use one Instance of ViewFactory-Object
    * */
    private static Model model;
    private final ViewFactory viewFactory;

    private Model(){
        this.viewFactory = new ViewFactory();
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
}
