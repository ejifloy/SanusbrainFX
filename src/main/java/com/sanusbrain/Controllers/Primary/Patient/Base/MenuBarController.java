package com.sanusbrain.Controllers.Primary.Patient.Base;

import com.sanusbrain.Utils.AsyncTaskRunner;
import com.sanusbrain.Utils.FieldMapProvider;
import com.sanusbrain.Utils.FieldMonitorUtil;
import com.sanusbrain.Models.Model;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MenuBarController implements Initializable {
    @FXML
    private MFXButton mfxSavePatient;
    @FXML
    private MFXFontIcon mfxSaveIcon;

    /**
     * Initializes the controller class.
     *
     * @param url  the location used to resolve relative paths for the root object, or null if the location is not known
     * @param resourceBundle the resources used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Bind the icon- and text-adjustment to the newPatientFlag property
        Model.getInstance().getNewPatientFlag().addListener((obs, oldVal, newVal) -> {
            mfxSaveIcon.setDescription(newVal ? "fas-floppy-disk":"fas-user-plus");
            mfxSavePatient.setText(newVal ? "Speichern":"Hinzufügen");
            mfxSavePatient.setDisable(newVal);
        });

        // Initialize MFX-Save-Button appearance
        boolean newPatientFlag = Model.getInstance().getNewPatientFlag().get();
        mfxSaveIcon.setDescription(newPatientFlag ? "fas-floppy-disk":"fas-user-plus");
        mfxSavePatient.setText(newPatientFlag ? "Speichern":"Hinzufügen");
    }

    /**
     * Save Patient Methode
     * <p>
     * This Methode creates a new patient or saves changes of an existing patient in the database and
     * sets the flag "newPatientFlag" within the Model Class to "false", for adjusting the MenuBar Save-Button Icon.
     **/
    @FXML
    private void onSavePatient(ActionEvent actionEvent) {

        // Checks if current Patient is a new or existing Patient
        if(!Model.getInstance().getNewPatientFlag().get()){
            //Check for necessary fields input
            if(Model.getInstance().getController(PersonalDataController.class).checkForNecessaryFields()){
                //Creating new Patient
                Model.getInstance().getDatabaseDriver().createPatient(
                        Model.getInstance().getPatientDataCollector().collectData()
                );

                // Reload patients from database to update the observable list
                Model.getInstance().loadPatients();

                // Adjusting the "newPatientFlag" withing Model Class
                Model.getInstance().setNewPatientFlag(true);
            }
        }else{
            //TODO: Save Patient Changes...
            Model.getInstance().getDialogController().showWarningDialog(
                    "Änderung speichern",
                    "Soll die Änderung gespeichert werden?",
                    ()-> {
                        Task<Void> saveTask = new Task<>() {
                            @Override
                            protected Void call(){
                                try {
                                    // Save patient changes into database
                                    Model.getInstance().getDatabaseDriver().savePatientChanges();
                                }catch (Exception e){
                                    Model.getInstance().getDialogController().showErrorDialog("Änderung speichern (MenuBarController, 79)","Es ist ein Fehler aufgetreten:\n\n"+e);
                                    throw new RuntimeException(e);
                                }
                                return null;
                            }

                            @Override
                            protected void succeeded(){
                                // Update initial values for each section
                                updateAllInitialFieldValues();
                            }

                            @Override
                            protected void failed(){
                                System.out.println("Failed to save the changes. (MenuBarController, 92");
                            }
                        };
                        AsyncTaskRunner.runAsync(saveTask);
                    });

        }
    }

    private void updateAllInitialFieldValues() {
        // Reloading initialValues within FieldMonitorUtil
        FieldMonitorUtil fieldMonitorUtil = Model.getInstance().getFieldMonitorUtil();

        // List of controller classes
        List<Class<? extends FieldMapProvider>> controllerClasses = Arrays.asList(
                PersonalDataController.class,
                ContactDataController.class,
                AnamneseDataController.class,
                InsuranceDataController.class
        );

        // Loop through each controller class and update initial field values
        for (Class<? extends FieldMapProvider> controllerClass : controllerClasses) {
            fieldMonitorUtil.updateInitialFieldValues(
                    Model.getInstance().getController(controllerClass).getFieldMap(),
                    Model.getInstance().getController(controllerClass).getInitialFieldValues()
            );
        }
        //TODO: Check Initial-Values updated + Save-Button disabled, test by change
    }

    /**
     * Enables or disables the save button based on the provided flag.
     *
     * @param enable true to enable the save button, false to disable it.
     */
    public void enableSaveButton(boolean enable){
        mfxSavePatient.setDisable(!enable);
    }

    @FXML
    private void checkData(ActionEvent actionEvent){
        // Access to personalDataController to fetch data from the FXML Nodes
        PersonalDataController personalDataController = Model.getInstance().getController(PersonalDataController.class);
        AnamneseDataController anamneseDataController = Model.getInstance().getController(AnamneseDataController.class);

        System.out.println("CHECK:\n"+personalDataController.getMfxFirstName()+
                "\nfname     :"+ personalDataController.getMfxFirstName()+
                "\nbirthdate :"+ personalDataController.getMfxBirthdate()+
                "\ngender    :"+ personalDataController.getMfxGender()+
                "\nmartial s :"+ personalDataController.getMfxMaritalStatus()+
                "\nprof stat :"+ personalDataController.getMfxProfessionStatus()+
                "\nslider-ov :"+ anamneseDataController.getMfxSliderOverall()+
                "\nov-descrp :"+ anamneseDataController.getMfxOverallDescription()+
                "\nc-doctor  :"+ anamneseDataController.getMfxCheckDoctor()+
                "\nc-treatmt :"+ anamneseDataController.getMfxCheckTreatment()
        );
    }
}
