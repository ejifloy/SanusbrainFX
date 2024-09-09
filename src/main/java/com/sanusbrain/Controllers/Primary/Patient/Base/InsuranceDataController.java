package com.sanusbrain.Controllers.Primary.Patient.Base;

import com.sanusbrain.Utils.Clearable;
import com.sanusbrain.Utils.FieldMapProvider;
import com.sanusbrain.Models.Entities.Patient.PatientData;
import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.Enums.Database.BaseDataKeys;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;

import static com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions.INSURANCE_DATA;

public class InsuranceDataController implements Initializable, Clearable, FieldMapProvider {


    @FXML
    private MFXButton mfxAdd;

    @FXML
    private MFXTextField mfxDoctorCity, mfxDoctorEmail, mfxDoctorMobilPhone, mfxDoctorPhone, mfxDoctorPostCode,
            mfxDoctorStreet, mfxFamilyDoctor;

    @FXML
    private MFXFilterComboBox<String> mfxInsurance;

    @FXML
    private MFXTextField mfxInsuranceCity, mfxInsuranceContact, mfxInsuranceEmail, mfxInsuranceMobilPhone,
            mfxInsurancePhone, mfxInsurancePostCode, mfxInsuranceStreet;


    private final Map<BaseDataKeys, Object> initialFieldValues = new EnumMap<>(BaseDataKeys.class);
    private final Map<BaseDataKeys, Object> fieldMap = new HashMap<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Object> fxmlElementList = Arrays.asList(mfxDoctorCity, mfxDoctorEmail, mfxDoctorMobilPhone, mfxDoctorPhone,
                mfxDoctorPostCode, mfxDoctorStreet, mfxFamilyDoctor, mfxInsurance, mfxInsuranceCity, mfxInsuranceContact,
                mfxInsuranceEmail, mfxInsuranceMobilPhone, mfxInsurancePhone, mfxInsurancePostCode, mfxInsuranceStreet);

        // Create field-Map
        Model.getInstance().getFieldMonitorUtil().initializeFieldMap(InsuranceDataController.class, fieldMap, fxmlElementList);

        // Initializing field values into map for comparing and adding listeners to FXML-Field to observe changes
        Model.getInstance().getFieldMonitorUtil().initializeInitialFieldValues(fieldMap, initialFieldValues, INSURANCE_DATA);
    }


    @FXML
    private void onAddInsuranceSection(ActionEvent event) {

    }

    public void loadPatientData(PatientData selectedPatient){
        // Clearing FXML fields
        clearFields();

        // Loading data into FXML fields
        mfxDoctorCity.setText(selectedPatient.getDoctorCity());
        mfxDoctorEmail.setText(selectedPatient.getDoctorEmail());
        mfxDoctorMobilPhone.setText(selectedPatient.getDoctorMobilePhone());
        mfxDoctorPhone.setText(selectedPatient.getDoctorPhone());
        mfxDoctorPostCode.setText(selectedPatient.getDoctorPostCode());
        mfxDoctorStreet.setText(selectedPatient.getDoctorStreet());
        mfxFamilyDoctor.setText(selectedPatient.getFamilyDoctor());

        mfxInsurance.setText(selectedPatient.getInsurance());
        mfxInsuranceCity.setText(selectedPatient.getInsuranceCity());
        mfxInsuranceContact.setText(selectedPatient.getInsuranceContact());
        mfxInsuranceEmail.setText(selectedPatient.getInsuranceEmail());
        mfxInsuranceMobilPhone.setText(selectedPatient.getInsuranceMobilePhone());
        mfxInsurancePhone.setText(selectedPatient.getInsurancePhone());
        mfxInsurancePostCode.setText(selectedPatient.getInsurancePostCode());
        mfxInsuranceStreet.setText(selectedPatient.getInsuranceStreet());

        Model.getInstance().getFieldMonitorUtil().updateInitialFieldValues(fieldMap, initialFieldValues);
    }

    //Getter Section
    public String getMfxDoctorCity() {return mfxDoctorCity.getText();}
    public String getMfxDoctorEmail() {return mfxDoctorEmail.getText();}
    public String getMfxDoctorMobilPhone() {return mfxDoctorMobilPhone.getText();}
    public String getMfxDoctorPhone() {return mfxDoctorPhone.getText();}
    public String getMfxDoctorPostCode() {return mfxDoctorPostCode.getText();}
    public String getMfxDoctorStreet() {return mfxDoctorStreet.getText();}
    public String getMfxFamilyDoctor() {return mfxFamilyDoctor.getText();}
    public String getMfxInsurance() {return mfxInsurance.getText();}
    public String getMfxInsuranceCity() {return mfxInsuranceCity.getText();}
    public String getMfxInsuranceContact() {return mfxInsuranceContact.getText();}
    public String getMfxInsuranceEmail() {return mfxInsuranceEmail.getText();}
    public String getMfxInsuranceMobilPhone() {return mfxInsuranceMobilPhone.getText();}
    public String getMfxInsurancePhone() {return mfxInsurancePhone.getText();}
    public String getMfxInsurancePostCode() {return mfxInsurancePostCode.getText();}
    public String getMfxInsuranceStreet() {return mfxInsuranceStreet.getText();}


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
    public void clearFields() {
        mfxDoctorCity.clear();
        mfxDoctorEmail.clear();
        mfxDoctorMobilPhone.clear();
        mfxDoctorPhone.clear();
        mfxDoctorPostCode.clear();
        mfxDoctorStreet.clear();
        mfxFamilyDoctor.clear();
        mfxInsurance.clear();
        mfxInsuranceCity.clear();
        mfxInsuranceContact.clear();
        mfxInsuranceEmail.clear();
        mfxInsuranceMobilPhone.clear();
        mfxInsurancePhone.clear();
        mfxInsurancePostCode.clear();
        mfxInsuranceStreet.clear();
    }
}
