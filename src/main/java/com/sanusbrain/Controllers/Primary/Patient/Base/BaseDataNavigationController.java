package com.sanusbrain.Controllers.Primary.Patient.Base;

import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

public class BaseDataNavigationController implements Initializable {

    @FXML
    private ToggleGroup baseData;
    @FXML
    private MFXRectangleToggleNode baseDataToggle;
    @FXML
    private MFXRectangleToggleNode contactToggle;
    @FXML
    private MFXRectangleToggleNode anamneseToggle;
    @FXML
    private MFXRectangleToggleNode insuranceToggle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model.getInstance().getViewFactory().getBaseDataSelectedViewItem().addListener((observableValue, oldValue, newValue) -> {
            switch (newValue){
                case PERSONAL_DATA -> baseData.selectToggle(baseDataToggle);
                case CONTACT_DATA -> baseData.selectToggle(contactToggle);
                case ANAMNESE_DATA -> baseData.selectToggle(anamneseToggle);
                case INSURANCE_DATA -> baseData.selectToggle(insuranceToggle);
            }
        });
    }

    @FXML
    void onAnamneseData(ActionEvent event) {
        Model.getInstance().getViewFactory().getBaseDataSelectedViewItem().set(BaseDataViewOptions.ANAMNESE_DATA);
    }

    @FXML
    void onContactData(ActionEvent event) {
        Model.getInstance().getViewFactory().getBaseDataSelectedViewItem().set(BaseDataViewOptions.CONTACT_DATA);
    }

    @FXML
    void onInsuranceData(ActionEvent event) {
        Model.getInstance().getViewFactory().getBaseDataSelectedViewItem().set(BaseDataViewOptions.INSURANCE_DATA);
    }

    @FXML
    void onPersonalData(ActionEvent event) {
        Model.getInstance().getViewFactory().getBaseDataSelectedViewItem().set(BaseDataViewOptions.PERSONAL_DATA);
    }
}
