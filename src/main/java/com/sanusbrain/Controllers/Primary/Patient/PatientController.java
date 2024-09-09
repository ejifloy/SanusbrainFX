package com.sanusbrain.Controllers.Primary.Patient;

import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions;
import com.sanusbrain.Views.Enums.FXML.PatientViewOptions;
import io.github.palexdev.materialfx.controls.MFXRectangleToggleNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML
    private BorderPane patientBorderPane;

    @FXML
    private ToggleGroup patientMenu;

    @FXML
    private MFXRectangleToggleNode overviewToggle;

    @FXML
    private MFXRectangleToggleNode baseDataToggle;

    @FXML
    private MFXRectangleToggleNode historyToggle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        patientBorderPane.setTop(Model.getInstance().getViewFactory().getPatientOverview());

        /*
        * Adding Listener to SimpleStringProperty "patientSelectedViewItem" that handles Navigations-Events between
        * Sections within Patient-View
        * */
        Model.getInstance().getViewFactory().getPatientSelectedViewItem().addListener((observable, oldValue, newValue) -> {
            switch(newValue){
                case OVERVIEW -> {
                    patientBorderPane.setCenter(Model.getInstance().getViewFactory().getOverviewView());
                    patientMenu.selectToggle(overviewToggle);
                }
                case BASEDATA -> {
                    patientBorderPane.setCenter(Model.getInstance().getViewFactory().getBaseView());
                    Model.getInstance().getViewFactory().getBaseDataSelectedViewItem().set(BaseDataViewOptions.PERSONAL_DATA);
                    patientMenu.selectToggle(baseDataToggle);
                }
                case HISTORY -> {
                    patientBorderPane.setCenter(Model.getInstance().getViewFactory().getHistoryView());
                    patientMenu.selectToggle(historyToggle);
                }
            }
        });
    }

    @FXML
    private void onOverview(ActionEvent actionEvent) {
        Model.getInstance().getViewFactory().getPatientSelectedViewItem().set(PatientViewOptions.OVERVIEW);
    }

    @FXML
    private void onBaseData(ActionEvent actionEvent) {
        Model.getInstance().getViewFactory().getPatientSelectedViewItem().set(PatientViewOptions.BASEDATA);
    }

    @FXML
    private void onHistory(ActionEvent actionEvent) {
        Model.getInstance().getViewFactory().getPatientSelectedViewItem().set(PatientViewOptions.HISTORY);
    }
}
