package com.sanusbrain.Controllers.Primary.Patient.Base;

import com.sanusbrain.Utils.Clearable;
import com.sanusbrain.Utils.FieldMapProvider;
import com.sanusbrain.Models.Entities.Patient.PatientData;
import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.Enums.Database.BaseDataKeys;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;

import static com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions.CONTACT_DATA;

public class ContactDataController implements Initializable, Clearable, FieldMapProvider {

    @FXML
    private MFXButton Add;

    @FXML
    private MFXTextField mfxCity;

    @FXML
    private MFXTextField mfxEmail;

    @FXML
    private MFXTextField mfxMobilPhone;

    @FXML
    private MFXTextField mfxPhone;

    @FXML
    private MFXTextField mfxPostCode;

    @FXML
    private MFXTextField mfxStreet;



    private final Map<BaseDataKeys, Object> initialFieldValues = new EnumMap<>(BaseDataKeys.class);
    private final Map<BaseDataKeys, Object> fieldMap = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize fxmlElementList
        List<Object> fxmlElementList = Arrays.asList(mfxCity, mfxEmail, mfxMobilPhone, mfxPhone, mfxPostCode, mfxStreet);

        // Create field-Map
        Model.getInstance().getFieldMonitorUtil().initializeFieldMap(ContactDataController.class, fieldMap, fxmlElementList);

        // Initializing field values into map for comparing and adding listeners to FXML-Field to observe changes
        Model.getInstance().getFieldMonitorUtil().initializeInitialFieldValues(fieldMap, initialFieldValues, CONTACT_DATA);
    }

    @FXML
    private void onAddAddressSection(ActionEvent actionEvent) {

    }

    public void loadPatientData(PatientData selectedPatient) {
        clearFields();
        //System.out.println("-> load \n\tfieldM:"+fieldMap+"\n\tinitialFV:"+initialFieldValues);

        setPatientDataToFields(selectedPatient);

        Model.getInstance().getFieldMonitorUtil().updateInitialFieldValues(fieldMap,initialFieldValues);
    }


    // Getter
    public String getMfxStreet() {return mfxStreet.getText();}
    public String getMfxPostCode() {return mfxPostCode.getText();}
    public String getMfxCity() {return mfxCity.getText();}
    public String getMfxPhone() {return mfxPhone.getText();}
    public String getMfxMobilPhone() {return mfxMobilPhone.getText();}
    public String getMfxEmail() {return mfxEmail.getText();}


    private void setPatientDataToFields(PatientData selectedPatient) {
        mfxStreet.setText(selectedPatient.getStreet());
        mfxPostCode.setText(selectedPatient.getPostCode());
        mfxCity.setText(selectedPatient.getCity());
        mfxPhone.setText(selectedPatient.getPhone());
        mfxMobilPhone.setText(selectedPatient.getMobilePhone());
        mfxEmail.setText(selectedPatient.getEmail());
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
    public Map<BaseDataKeys, Object> getInitialFieldValues() {
        return initialFieldValues;
    }

    /**
     * Clears all the input fields in the form.
     */
    @Override
    public void clearFields(){
        mfxCity.clear();
        mfxEmail.clear();
        mfxMobilPhone.clear();
        mfxPhone.clear();
        mfxPostCode.clear();
        mfxStreet.clear();
    }
}
