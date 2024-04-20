package com.sanusbrain.Controllers;

import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.AdminViewOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientsController implements Initializable {
    @FXML
    private AnchorPane patientsView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void onCreatePatient(ActionEvent actionEvent) {
        Model.getInstance().getViewFactory().getAdminSelectedViewItem().set(AdminViewOptions.PATIENT);
    }
}
