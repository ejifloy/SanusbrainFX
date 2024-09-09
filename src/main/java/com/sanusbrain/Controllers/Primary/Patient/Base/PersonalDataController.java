package com.sanusbrain.Controllers.Primary.Patient.Base;

import com.sanusbrain.Utils.Clearable;
import com.sanusbrain.Utils.FieldMapProvider;
import com.sanusbrain.Models.Entities.Patient.PatientData;
import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.Enums.Database.BaseDataKeys;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXDatePicker;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.MFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions.PERSONAL_DATA;

/**
 * Controller class for handling the personal data section of the patient view.
 */
public class PersonalDataController implements Initializable, Clearable, FieldMapProvider {

    @FXML
    private MFXTextField mfxTitle;
    @FXML
    private MFXTextField mfxFirstName;
    @FXML
    private MFXTextField mfxLastName;
    @FXML
    private MFXDatePicker mfxBirthdate;
    @FXML
    private MFXComboBox<String> mfxGender;
    @FXML
    private MFXTextField mfxCustomerID;
    @FXML
    private MFXToggleButton mfxStatus;
    @FXML
    private MFXComboBox<String> mfxMaritalStatus;
    @FXML
    private MFXTextField mfxNationality;
    @FXML
    private MFXTextField mfxProfession;
    @FXML
    private MFXComboBox<String> mfxProfessionStatus;
    @FXML
    private MFXComboBox<String> mfxTraining;


    private final Map<BaseDataKeys, Object> initialFieldValues = new EnumMap<>(BaseDataKeys.class);
    private final Map<BaseDataKeys, Object> fieldMap = new HashMap<>();


    /**
     * Initializes the controller class.
     *
     * @param url  the url used to resolve relative paths for the root object, or null if the url is not known
     * @param resourceBundle the resourceBundle used to localize the root object, or null if the root object was not localized
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize fxmlElementList
        List<Object> fxmlElementList = Arrays.asList(mfxTitle, mfxFirstName, mfxLastName, mfxBirthdate, mfxGender, mfxCustomerID,
                mfxStatus, mfxMaritalStatus, mfxNationality, mfxProfession, mfxProfessionStatus, mfxTraining);

        // Create field-Map
        Model.getInstance().getFieldMonitorUtil().initializeFieldMap(PersonalDataController.class, fieldMap, fxmlElementList);

        // Initializing field values into map for comparing and adding listeners to FXML-Field to observe changes
        Model.getInstance().getFieldMonitorUtil().initializeInitialFieldValues(fieldMap, initialFieldValues, PERSONAL_DATA);

    }

    public void loadPatientData(PatientData selectedPatient) {
        // Clear all fields before loading.
        clearFields();

        // Load patient data into FXML fields
        setPatientDataToFields(selectedPatient);

        // Update loaded field into initialFieldValues map
        Model.getInstance().getFieldMonitorUtil().updateInitialFieldValues(fieldMap,initialFieldValues);
    }

    private void setPatientDataToFields(PatientData selectedPatient) {
        mfxTitle.setText(selectedPatient.getTitle());
        mfxFirstName.setText(selectedPatient.getFirstName());
        mfxLastName.setText(selectedPatient.getLastName());
        mfxBirthdate.setValue(selectedPatient.getBirthdate());
        mfxGender.setText(selectedPatient.getGender());
        mfxCustomerID.setText(selectedPatient.getCustomerID());
        mfxStatus.setSelected(selectedPatient.getStatus());
        mfxMaritalStatus.setText(selectedPatient.getMaritalStatus());
        mfxNationality.setText(selectedPatient.getNationality());
        mfxProfession.setText(selectedPatient.getProfession());
        mfxProfessionStatus.setText(selectedPatient.getProfessionStatus());
        mfxTraining.setText(selectedPatient.getTraining());
    }

    //Getter
    public String getMfxTitle() {return mfxTitle.getText();}
    public String getMfxFirstName() {return mfxFirstName.getText();}
    public String getMfxLastName() {return mfxLastName.getText();}
    public LocalDate getMfxBirthdate() {return mfxBirthdate.getValue();}
    public String getMfxGender() {return (mfxGender.getText());}
    public String getMfxCustomerID() {return mfxCustomerID.getText();}
    public boolean getMfxStatus(){return mfxStatus.isSelected();}
    public String getMfxMaritalStatus() {return mfxMaritalStatus.getText();}
    public String getMfxNationality() {return mfxNationality.getText();}
    public String getMfxProfession() {return mfxProfession.getText();}
    public String getMfxProfessionStatus() {return mfxProfessionStatus.getText();}
    public String getMfxTraining() {return mfxTraining.getText();}


    /**
     * Checks for necessary fields input value. If any input is missing, an info dialog-window is shown.
     */
    public boolean checkForNecessaryFields(){
        if(mfxFirstName.getText().isBlank()||mfxLastName.getText().isBlank()||mfxCustomerID.getText().isBlank()){
            Model.getInstance().getDialogController().showInfoDialog("Patient hinzufügen", "Die Eingabe ist unvollständig. Bitte füllen Sie mindestens folgende Felder aus:\n\n\tVorname, Nachname, Kundennumer");
            return false;
        }
        return true;
    }

    /**
     * Returns the fieldMap of the section, containing all (changed) values within the FXML-Elements.
     *
     * @return Map containing the current values of each field;
     */
    @Override
    public Map<BaseDataKeys, Object> getFieldMap() {
        return fieldMap;
    }

    @Override
    public Map<BaseDataKeys, Object> getInitialFieldValues(){
        return initialFieldValues;
    }

    /**
     * Clears all the input fields in the form.
     */
    @Override
    public void clearFields(){
        mfxTitle.clear();
        mfxFirstName.clear();
        mfxLastName.clear();
        mfxBirthdate.setValue(null);
        mfxGender.clear();
        mfxCustomerID.clear();
        mfxStatus.setSelected(false);
        mfxMaritalStatus.clear();
        mfxNationality.clear();
        mfxProfession.clear();
        mfxProfessionStatus.clear();
        mfxTraining.clear();
    }
}
