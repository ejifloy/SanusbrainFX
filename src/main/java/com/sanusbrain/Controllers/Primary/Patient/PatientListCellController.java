package com.sanusbrain.Controllers.Primary.Patient;

import com.sanusbrain.Utils.AsyncTaskRunner;
import com.sanusbrain.Models.Entities.Patient.PatientEntry;
import com.sanusbrain.Models.Model;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientListCellController implements Initializable {

    private final PatientEntry patientEntry;

    @FXML
    private Label fxInitials;

    @FXML
    private Label fxFirstName;

    @FXML
    private Label fxLastName;

    @FXML
    private Label fxCustomerID;

    @FXML
    private Label fxBirthdate;

    @FXML
    private Label fxGender;

    @FXML
    private Label fxStatus;

    @FXML
    private MFXButton mfxDelete;


    /**
     * Constructor for creating a PatientListCellController with a specific patient.
     *
     * @param patientEntry The patient associated with this cell.
     */
    public PatientListCellController(PatientEntry patientEntry){
        this.patientEntry = patientEntry;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is unknown.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Delete Patient Event
        mfxDelete.setOnAction(actionEvent -> onDeletePatient());

        // Custom binding for status to handle boolean values and representation
        StringBinding statusBinding = Bindings.createStringBinding(
                () -> patientEntry.statusProperty().get() ? "Aktiv" : "Inaktiv", patientEntry.statusProperty()
        );
        StringBinding colorBinding = Bindings.createStringBinding(
                () -> patientEntry.statusProperty().get() ? "-fx-text-fill: -fx-green" : "-fx-text-fill: -fx-red",
                patientEntry.statusProperty()
        );
        StringBinding backgroundColorBinding = Bindings.createStringBinding(
                () -> "-fx-background-color: " + patientEntry.colorProperty().get(), patientEntry.colorProperty()
        );

        fxInitials.styleProperty().bind(backgroundColorBinding);
        fxInitials.textProperty().bind(patientEntry.initialsProperty());
        fxFirstName.textProperty().bind(patientEntry.first_nameProperty());
        fxLastName.textProperty().bind(patientEntry.last_nameProperty());
        fxCustomerID.textProperty().bind(patientEntry.customer_idProperty());
        fxBirthdate.textProperty().bind(Bindings.createStringBinding(patientEntry::getFormattedBirthdate, patientEntry.birthdateProperty()));
        fxGender.textProperty().bind(patientEntry.genderProperty());
        fxStatus.textProperty().bind(statusBinding);
        fxStatus.styleProperty().bind(colorBinding);
    }

    @FXML
    public void onDeletePatient(){
        Model.getInstance().getDialogController().showWarningDialog(
            "Patient entfernen",
            "Soll der Patient:\n\n "+fxFirstName.getText()+" "+fxLastName.getText()+"\n\n entfernt werden?",
            ()-> { // Confirmation callback
                Task<Void> deleteTask = new Task<>() {
                    @Override
                    protected Void call(){
                        try {
                            // Delete patient from database
                            Model.getInstance().getDatabaseDriver().deletePatient(patientEntry.getId());
                        }catch (Exception e){
                            Model.getInstance().getDialogController().showErrorDialog("Patient entfernen", "Es ist ein fehler aufgetreten:\n\n"+e);
                            throw new RuntimeException(e);
                        }
                        return null;
                    }

                    @Override
                    protected void succeeded() {
                        // Update the patient list view on the JavaFX Application Thread
                        Platform.runLater(() -> {
                            System.out.println("Patient successfully deleted!");
                            Model.getInstance().getPatientEntries().remove(patientEntry);
                        });
                    }

                    @Override
                    protected void failed() {
                        // Handle failure
                        Platform.runLater(() -> {
                            System.out.println("Failed to delete patient.");
                        });
                    }
                };

                AsyncTaskRunner.runAsync(deleteTask);
            }
        );
    }
}
