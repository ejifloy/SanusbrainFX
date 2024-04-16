package com.sanusbrain.Views;

import com.sanusbrain.Controllers.PatientListCellController;
import com.sanusbrain.Models.Patient;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;
/*
* PatientCellFactory handles each individual Patient-Object (item). Loading the
* ListCell-FXML and passing each Patient-Object to the PatientListCellController,
* to set the values of the Patient within the Controller FXML-Elements.
* */
public class PatientCellFactory extends ListCell<Patient> {

    @Override
    protected void updateItem(Patient patient, boolean empty){
        super.updateItem(patient, empty);

        if(empty || patient == null){
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/patientListCell.fxml"));
            PatientListCellController controller = new PatientListCellController(patient);
            loader.setController(controller);
            setText(null);

            try{
                setGraphic(loader.load());
            }catch(IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
