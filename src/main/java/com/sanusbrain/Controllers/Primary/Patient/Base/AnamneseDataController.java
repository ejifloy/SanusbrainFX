package com.sanusbrain.Controllers.Primary.Patient.Base;

import com.sanusbrain.Utils.Clearable;
import com.sanusbrain.Utils.FieldMapProvider;
import com.sanusbrain.Models.Entities.Patient.PatientData;
import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.Enums.Database.BaseDataKeys;
import io.github.palexdev.materialfx.beans.NumberRange;
import io.github.palexdev.materialfx.controls.*;
import io.github.palexdev.materialfx.utils.ScrollUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

import static com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions.ANAMNESE_DATA;

public class AnamneseDataController implements Initializable, Clearable, FieldMapProvider {


    @FXML
    private MFXScrollPane mfxScrollPane;

    @FXML
    private MFXButton mfxAddMedical,mfxAddTreatment;

    @FXML
    private TextArea mfxDescription;

    @FXML
    private MFXDatePicker mfxBeginDate, mfxMedicalDate, mfxTreatmentFrom, mfxTreatmentTill;

    @FXML
    private ToggleGroup mfxCheckDoctor, mfxCheckTreatment, mfxCheckMedical;

    @FXML
    private MFXRadioButton mfxPsychiatristYes,  //ToggleGroup -> mfxCheckDoctor
                            mfxFamilyDoctorYes; //ToggleGroup -> mfxCheckDoctor

    @FXML
    private MFXRadioButton mfxMedicalNo,        //ToggleGroup -> mfxCheckMedical
                            mfxMedicalYes;      //ToggleGroup -> mfxCheckMedical

    @FXML
    private MFXRadioButton mfxTreatmentNo,      //ToggleGroup -> mfxCheckTreatment
                            mfxTreatmentYes;    //ToggleGroup -> mfxCheckTreatment

    @FXML
    private MFXComboBox<String> mfxComplaints, mfxTreatment;

    @FXML
    private MFXTextField mfxTreatmentDescription, mfxMedicalDescription,mfxMedicalDosage;

    @FXML
    private MFXTextField mfxOverallDescription, mfxPhysicalDescription, mfxProfessionDescription, mfxRelationDescription,
                            mfxFamilyDescription, mfxFinancialDescription, mfxLeisureDescription ,mfxSocialDescription;

    @FXML
    private MFXSlider mfxSliderFamily, mfxSliderFinancial, mfxSliderLeisure, mfxSliderOverall, mfxSliderPhysical,
                        mfxSliderProfession, mfxSliderRelation, mfxSliderSocial;


    private final Map<BaseDataKeys, Object> initialFieldValues = new EnumMap<>(BaseDataKeys.class);
    private final Map<BaseDataKeys, Object> fieldMap = new HashMap<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Initialize fxmlElementList
        List<Object> fxmlElementList = Arrays.asList(mfxDescription, mfxBeginDate, mfxMedicalDate, mfxTreatmentFrom,
                mfxTreatmentTill, mfxCheckDoctor, mfxCheckTreatment, mfxCheckMedical, mfxComplaints, mfxTreatment,
                mfxTreatmentDescription, mfxMedicalDescription, mfxMedicalDosage, mfxOverallDescription,
                mfxPhysicalDescription, mfxProfessionDescription, mfxRelationDescription, mfxFamilyDescription,
                mfxFinancialDescription, mfxLeisureDescription, mfxSocialDescription, mfxSliderFamily,
                mfxSliderFinancial, mfxSliderLeisure, mfxSliderOverall, mfxSliderPhysical, mfxSliderProfession,
                mfxSliderRelation, mfxSliderSocial);

        ScrollUtils.addSmoothScrolling(mfxScrollPane);

        for(MFXSlider slider: Arrays.asList(mfxSliderFamily, mfxSliderFinancial, mfxSliderLeisure, mfxSliderOverall,
                                        mfxSliderPhysical, mfxSliderProfession, mfxSliderRelation, mfxSliderSocial)){
            slider.getRanges1().add(NumberRange.of(slider.getMin(), 3.5));
            slider.getRanges2().add(NumberRange.of(3.6, 7.0));
            slider.getRanges3().add(NumberRange.of(7.1, slider.getMax()));
        }


        // Create field-Map
        Model.getInstance().getFieldMonitorUtil().initializeFieldMap(AnamneseDataController.class, fieldMap, fxmlElementList);

        // Initializing field values into map for comparing and adding listeners to FXML-Field to observe changes
        Model.getInstance().getFieldMonitorUtil().initializeInitialFieldValues(fieldMap, initialFieldValues, ANAMNESE_DATA);
    }

    @FXML
    void onAddMedicalSection(ActionEvent event) {

    }

    @FXML
    void onAddTreatmentSection(ActionEvent event) {

    }

    public void loadPatientData(PatientData selectedPatient){
        // Clearing FXML fields
        clearFields();

        // Loading data into FXML fields
        setAnamneseDataIntoFields(selectedPatient);

        Model.getInstance().getFieldMonitorUtil().updateInitialFieldValues(fieldMap,initialFieldValues);
    }

    // Getter Section
    public String getMfxDescription() {return mfxDescription.getText();}
    public String getMfxFamilyDescription() {return mfxFamilyDescription.getText();}
    public String getMfxFinancialDescription() {return mfxFinancialDescription.getText();}
    public String getMfxLeisureDescription() {return mfxLeisureDescription.getText();}
    public String getMfxMedicalDescription() {return mfxMedicalDescription.getText();}
    public String getMfxOverallDescription() {return mfxOverallDescription.getText();}
    public String getMfxMedicalDosage() {return mfxMedicalDosage.getText();}
    public String getMfxPhysicalDescription() {return mfxPhysicalDescription.getText();}
    public String getMfxProfessionDescription() {return mfxProfessionDescription.getText();}
    public String getMfxRelationDescription() {return mfxRelationDescription.getText();}
    public String getMfxSocialDescription() {return mfxSocialDescription.getText();}
    public String getMfxTreatmentDescription() {return mfxTreatmentDescription.getText();}
    public String getMfxTreatment() {return mfxTreatment.getText();}
    public String getMfxComplaints() {return mfxComplaints.getText();}
    public double getMfxSliderSocial() {return mfxSliderSocial.getValue();}
    public double getMfxSliderRelation() {return mfxSliderRelation.getValue();}
    public double getMfxSliderPhysical() {return mfxSliderPhysical.getValue();}
    public double getMfxSliderOverall() {return mfxSliderOverall.getValue();}
    public double getMfxSliderLeisure() {return mfxSliderLeisure.getValue();}
    public double getMfxSliderFinancial() {return mfxSliderFinancial.getValue();}
    public double getMfxSliderFamily() {return mfxSliderFamily.getValue();}
    public double getMfxSliderProfession() {return mfxSliderProfession.getValue();}
    public LocalDate getMfxTreatmentTill() {return mfxTreatmentTill.getValue();}
    public LocalDate getMfxTreatmentFrom() {return mfxTreatmentFrom.getValue();}
    public LocalDate getMfxMedicalDate() {return mfxMedicalDate.getValue();}
    public LocalDate getMfxBeginDate() {return mfxBeginDate.getValue();}
    public String getMfxCheckDoctor() {
        System.out.println("Toggle:"+mfxCheckDoctor+", "+mfxCheckDoctor.getSelectedToggle());
        return Optional.ofNullable(((MFXRadioButton) mfxCheckDoctor.getSelectedToggle()))
                        .map(Labeled::getText)
                        .orElse("");
    }
    public String getMfxCheckMedical() {
        return Optional.ofNullable(((MFXRadioButton) mfxCheckMedical.getSelectedToggle()))
                .map(Labeled::getText)
                .orElse("");
    }
    public String getMfxCheckTreatment() {
        return Optional.ofNullable(((MFXRadioButton) mfxCheckTreatment.getSelectedToggle()))
                .map(Labeled::getText)
                .orElse("");
    }

    // Loads Anamnese Data into FXML-Fields
    private void setAnamneseDataIntoFields(PatientData selectedPatient) {
        mfxDescription.setText(selectedPatient.getDescription());
        mfxFamilyDescription.setText(selectedPatient.getFamilyDescription());
        mfxFinancialDescription.setText(selectedPatient.getFamilyDescription());
        mfxLeisureDescription.setText(selectedPatient.getLeisureDescription());
        mfxMedicalDescription.setText(selectedPatient.getMedicalDescription());
        mfxOverallDescription.setText(selectedPatient.getOverallDescription());
        mfxMedicalDosage.setText(selectedPatient.getMedicalDosage());
        mfxPhysicalDescription.setText(selectedPatient.getPhysicalDescription());
        mfxProfessionDescription.setText(selectedPatient.getProfessionDescription());
        mfxRelationDescription.setText(selectedPatient.getRelationDescription());
        mfxSocialDescription.setText(selectedPatient.getSocialDescription());
        mfxTreatmentDescription.setText(selectedPatient.getTreatmentDescription());
        mfxTreatment.setText(selectedPatient.getTreatment());
        mfxComplaints.setText(selectedPatient.getComplaints());

        mfxSliderSocial.setValue(selectedPatient.getSliderSocial());
        mfxSliderRelation.setValue(selectedPatient.getSliderRelation());
        mfxSliderPhysical.setValue(selectedPatient.getSliderPhysical());
        mfxSliderOverall.setValue(selectedPatient.getSliderOverall());
        mfxSliderLeisure.setValue(selectedPatient.getSliderLeisure());
        mfxSliderFinancial.setValue(selectedPatient.getSliderFinancial());
        mfxSliderFamily.setValue(selectedPatient.getSliderFamily());
        mfxSliderProfession.setValue(selectedPatient.getSliderProfession());

        mfxTreatmentTill.setValue(selectedPatient.getTreatmentTill());
        mfxTreatmentFrom.setValue(selectedPatient.getTreatmentFrom());
        mfxMedicalDate.setValue(selectedPatient.getMedicalDate());
        mfxBeginDate.setValue(selectedPatient.getBeginDate());

        setToggleByString(mfxCheckDoctor , selectedPatient.getCheckDoctor());
        setToggleByString(mfxCheckMedical , selectedPatient.getCheckMedical());
        setToggleByString(mfxCheckTreatment , selectedPatient.getCheckTreatment());
    }

    /**
     * This methode sets the active toggle in the specified ToggleGroup based on the provided string value.
     * It searches for a toggle whose label matches the given value and selects it.
     *
     * @param toggleGroup   ToggleGroup containing all toggle nodes.
     * @param value         The string value of the toggle's label to be selected.
     */
    private void setToggleByString(ToggleGroup toggleGroup, String value) {
        toggleGroup.getToggles().stream()
                .filter(toggle -> ((MFXRadioButton) toggle).getText().equals(value))
                .findFirst()
                .ifPresent(toggle -> toggleGroup.selectToggle(toggle));
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

    @Override
    public void clearFields() {
        mfxDescription.clear();
        mfxBeginDate.clear();
        mfxComplaints.clear();
        mfxFamilyDescription.clear();
        mfxFinancialDescription.clear();
        mfxLeisureDescription.clear();
        mfxMedicalDate.clear();
        mfxMedicalDescription.clear();
        mfxMedicalDosage.clear();
        mfxOverallDescription.clear();
        mfxPhysicalDescription.clear();
        mfxProfessionDescription.clear();
        mfxRelationDescription.clear();

        mfxSliderFamily.setValue(0.0);
        mfxSliderFinancial.setValue(0.0);
        mfxSliderLeisure.setValue(0.0);
        mfxSliderOverall.setValue(0.0);
        mfxSliderPhysical.setValue(0.0);
        mfxSliderProfession.setValue(0.0);
        mfxSliderRelation.setValue(0.0);
        mfxSliderSocial.setValue(0.0);

        mfxSocialDescription.clear();
        mfxTreatment.clear();
        mfxTreatmentDescription.clear();
        mfxTreatmentFrom.clear();
        mfxTreatmentTill.clear();

        mfxCheckDoctor.selectToggle(null);
        mfxCheckMedical.selectToggle(null);
        mfxCheckTreatment.selectToggle(null);

       // mfxPsychiatristYes.setSelected(false);
       // mfxFamilyDoctorYes.setSelected(false);
       // mfxMedicalYes.setSelected(false);
       // mfxMedicalNo.setSelected(false);
       // mfxTreatmentYes.setSelected(false);
       // mfxTreatmentNo.setSelected(false);
    }
}
