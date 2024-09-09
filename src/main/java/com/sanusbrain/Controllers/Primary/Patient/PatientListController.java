package com.sanusbrain.Controllers.Primary.Patient;

import com.sanusbrain.Utils.AsyncTaskRunner;
import com.sanusbrain.Controllers.Primary.Patient.Base.AnamneseDataController;
import com.sanusbrain.Controllers.Primary.Patient.Base.ContactDataController;
import com.sanusbrain.Controllers.Primary.Patient.Base.InsuranceDataController;
import com.sanusbrain.Controllers.Primary.Patient.Base.PersonalDataController;
import com.sanusbrain.Controllers.Primary.Patient.History.HistoryController;
import com.sanusbrain.Models.Entities.Patient.PatientData;
import com.sanusbrain.Models.Model;
import com.sanusbrain.Models.Entities.Patient.PatientEntry;
import com.sanusbrain.Views.Enums.FXML.AdminViewOptions;
import com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions;
import com.sanusbrain.Views.PatientCellFactory;
import com.sanusbrain.Views.Enums.FXML.PatientViewOptions;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientListController implements Initializable {

    @FXML
    private MFXTextField mfxSearch;
    @FXML
    private ListView<PatientEntry> patientListView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        MFXFontIcon searchIcon = new MFXFontIcon("fas-magnifying-glass");
        mfxSearch.setLeadingIcon(searchIcon);

        //Loads the Patient-Data
        initPatientEntries();

        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<PatientEntry> filteredData = new FilteredList<>(Model.getInstance().getPatientEntries(), p -> true);
        //Set the filter Predicate whenever the filter changes.
        mfxSearch.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(patient ->{
            // If filter text is empty, display all persons.
            if(newValue == null || newValue.isEmpty())
                return true;

            // Compare first name and last name of every client with filter text.
            String lowerCaseFilter = newValue.toLowerCase();

            //filter matches first/last-name or birthdate
            return patient.first_nameProperty().toString().toLowerCase().contains(lowerCaseFilter) ||
                    patient.last_nameProperty().toString().toLowerCase().contains(lowerCaseFilter) ||
                    patient.birthdateProperty().toString().toLowerCase().contains(lowerCaseFilter);
        }));
        //Wrap the FilteredList in a SortedList.
        SortedList<PatientEntry> sortedData = new SortedList<>(filteredData);
        //put the sorted list into the listview
        patientListView.setItems(sortedData);

        //loads PatientCellFactory for each patient entry
        patientListView.setCellFactory(e -> new PatientCellFactory());

    }


    @FXML
    void onLoadPatient(MouseEvent event) {
        if(event.getClickCount() > 1){

            PatientEntry patientEntry = patientListView.getSelectionModel().getSelectedItem();

            // Set the selected patient within the model class for access
            Model.getInstance().setSelectedPatientEntry(patientEntry);
            // TODO: Might be needed later...
            //Model.getInstance().setSelectedPatientData(patientData);

            if(patientEntry != null){
                // Execute the loading process asynchronously.
                AsyncTaskRunner.runAsync(()->{
                    // Fetch patient-data by starting the loading process asynchronously.
                    int patient_id = patientEntry.getId();
                    PatientData patientData = Model.getInstance().getPatientService().getPatientById(patient_id);

                    // Update the GUI with the loaded data on the JavaFX Application Thread.
                    Platform.runLater(()->{
                        // Set patient flag to indicate an existing patient.
                        Model.getInstance().setNewPatientFlag(true);

                        // Fill GUI elements with patient data.
                        Model.getInstance().getController(PatientOverviewController.class).loadPatientData(patientData);
                        Model.getInstance().getController(PersonalDataController.class).loadPatientData(patientData);
                        Model.getInstance().getController(ContactDataController.class).loadPatientData(patientData);
                        Model.getInstance().getController(AnamneseDataController.class).loadPatientData(patientData);
                        Model.getInstance().getController(InsuranceDataController.class).loadPatientData(patientData);
                        Model.getInstance().getController(HistoryController.class).loadHistoryEntries(patient_id);


                        // Load Patient View
                        Model.getInstance().getViewFactory().getAdminSelectedViewItem().set(AdminViewOptions.PATIENT);
                        Model.getInstance().getViewFactory().getPatientSelectedViewItem().set(PatientViewOptions.OVERVIEW);

                        // Clear search field
                        mfxSearch.clear();
                    });
                    return null;
                });
            }
        }
    }

    /**
     * Handles the event when the "Patient Hinzufügen" button has been pressed.
     * Sets the {@link com.sanusbrain.Models.Model#setNewPatientFlag(boolean) newPatientFlag} flag to true,
     * clears the form fields, and navigates to the personal data view for patient creation.
     */
    @FXML
    private void onCreatePatient() {
        // Set patient flag to indicate a new patient
        Model.getInstance().setNewPatientFlag(false);
        // Initialize PatientDataController for data collection
        Model.getInstance().setPatientDataCollector();

        // Clearing field within the Controllers of Patient-Base section
        Model.getInstance().getController(PatientOverviewController.class).clearFields();
        Model.getInstance().getController(PersonalDataController.class).clearFields();
        Model.getInstance().getController(ContactDataController.class).clearFields();
        Model.getInstance().getController(AnamneseDataController.class).clearFields();
        Model.getInstance().getController(InsuranceDataController.class).clearFields();

        Model.getInstance().getViewFactory().getAdminSelectedViewItem().set(AdminViewOptions.PATIENT);
        Model.getInstance().getViewFactory().getPatientSelectedViewItem().set(PatientViewOptions.BASEDATA);
        Model.getInstance().getViewFactory().getBaseDataSelectedViewItem().set(BaseDataViewOptions.PERSONAL_DATA);
    }

    /**
     * Loads patients data from database.
     */
    private void initPatientEntries(){
        Model.getInstance().loadPatients();
    }
}


