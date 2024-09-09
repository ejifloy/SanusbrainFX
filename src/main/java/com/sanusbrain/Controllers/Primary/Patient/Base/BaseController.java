package com.sanusbrain.Controllers.Primary.Patient.Base;

import com.sanusbrain.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseController implements Initializable {


    @FXML
    private BorderPane baseBorderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Adding Listener to SimpleStringProperty that handled Navigations-Events within Patient-View "Base-Data Section"
        Model.getInstance().getViewFactory().getBaseDataSelectedViewItem().addListener((observable, oldValue, newValue) -> {
            baseBorderPane.setTop(Model.getInstance().getViewFactory().getMenuBarView());
            baseBorderPane.setLeft(Model.getInstance().getViewFactory().getBaseDataNavigationView());

            switch(newValue){
                case PERSONAL_DATA -> baseBorderPane.setCenter(Model.getInstance().getViewFactory().getPersonalDataView());
                case CONTACT_DATA -> baseBorderPane.setCenter(Model.getInstance().getViewFactory().getContactDataView());
                case ANAMNESE_DATA -> baseBorderPane.setCenter(Model.getInstance().getViewFactory().getAnamneseDataView());
                case INSURANCE_DATA -> baseBorderPane.setCenter(Model.getInstance().getViewFactory().getInsuranceDataView());
            }
        });
    }
}
