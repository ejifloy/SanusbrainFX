package com.sanusbrain.Views;

import com.sanusbrain.Controllers.Primary.Patient.PatientListCellController;
import com.sanusbrain.Models.Entities.Patient.PatientEntry;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;
/*
* PatientCellFactory handles each individual Patient-Object (item). Loading the
* ListCell-FXML and passing each Patient-Object to the PatientListCellController,
* to set the values of the Patient within the Controllers FXML-Elements.
* */
public class PatientCellFactory extends ListCell<PatientEntry> {

    @Override
    protected void updateItem(PatientEntry patientEntry, boolean empty){
        super.updateItem(patientEntry, empty);

        if(empty || patientEntry == null){
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/patient/patientListCell.fxml"));
            PatientListCellController controller = new PatientListCellController(patientEntry);
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
