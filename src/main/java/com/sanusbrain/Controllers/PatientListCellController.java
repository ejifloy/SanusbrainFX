package com.sanusbrain.Controllers;

import com.sanusbrain.Models.Patient;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientListCellController implements Initializable {

    private final Patient patient;

    public PatientListCellController(Patient patient){
        this.patient = patient;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
