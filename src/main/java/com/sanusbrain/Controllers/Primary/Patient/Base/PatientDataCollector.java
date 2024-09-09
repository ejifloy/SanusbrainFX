package com.sanusbrain.Controllers.Primary.Patient.Base;

import com.sanusbrain.Models.Entities.Patient.PatientData;
import com.sanusbrain.Models.Model;

/**
 * This Class is used to gather all data from the base section of Patient-View.
 * It is used to provide a central access point of patient data to create a new patient or save changes to the database.
 * <p>
 * Controllers which are accessed:
 * <p>{@link PersonalDataController}   - personal data<br>{@link ContactDataController}    - contact data
 * <br>{@link AnamneseDataController}   - anamnese data<br>{@link InsuranceDataController}  - insurance data
 */
public class PatientDataCollector {

    private final PersonalDataController personalDataController;
    private final ContactDataController contactDataController;
    private final AnamneseDataController anamneseDataController;
    private final InsuranceDataController insuranceDataController;

    public PatientDataCollector() {
        this.personalDataController = Model.getInstance().getController(PersonalDataController.class);
        this.contactDataController = Model.getInstance().getController(ContactDataController.class);
        this.anamneseDataController = Model.getInstance().getController(AnamneseDataController.class);
        this.insuranceDataController = Model.getInstance().getController(InsuranceDataController.class);
    }

    public PatientData collectData() {
        // Create Patient Data instance
        PatientData patientData = new PatientData();

        // Personal Data Section
        patientData.setTitle(personalDataController.getMfxTitle());
        patientData.setFirstName(personalDataController.getMfxFirstName());
        patientData.setLastName(personalDataController.getMfxLastName());
        patientData.setInitials(patientData.generateInitials());
        patientData.setBirthdate(personalDataController.getMfxBirthdate());
        patientData.setGender(personalDataController.getMfxGender());
        patientData.setCustomerID(personalDataController.getMfxCustomerID());
        patientData.setStatus(personalDataController.getMfxStatus());
        patientData.setMaritalStatus(personalDataController.getMfxMaritalStatus());
        patientData.setNationality(personalDataController.getMfxNationality());
        patientData.setProfession(personalDataController.getMfxProfession());
        patientData.setProfessionStatus(personalDataController.getMfxProfessionStatus());
        patientData.setTraining(personalDataController.getMfxTraining());

        // Contact Data Section
        patientData.setStreet(contactDataController.getMfxStreet());
        patientData.setPostCode(contactDataController.getMfxPostCode());
        patientData.setCity(contactDataController.getMfxCity());
        patientData.setEmail(contactDataController.getMfxEmail());
        patientData.setMobilePhone(contactDataController.getMfxMobilPhone());
        patientData.setPhone(contactDataController.getMfxPhone());


        // Anamnese Data Section
        patientData.setDescription(anamneseDataController.getMfxDescription());
        patientData.setFamilyDescription(anamneseDataController.getMfxFamilyDescription());
        patientData.setFinancialDescription(anamneseDataController.getMfxFinancialDescription());
        patientData.setLeisureDescription(anamneseDataController.getMfxLeisureDescription());
        patientData.setMedicalDescription(anamneseDataController.getMfxMedicalDescription());
        patientData.setOverallDescription(anamneseDataController.getMfxOverallDescription());
        patientData.setMedicalDosage(anamneseDataController.getMfxMedicalDosage());
        patientData.setPhysicalDescription(anamneseDataController.getMfxPhysicalDescription());
        patientData.setProfessionDescription(anamneseDataController.getMfxProfessionDescription());
        patientData.setRelationDescription(anamneseDataController.getMfxRelationDescription());
        patientData.setSocialDescription(anamneseDataController.getMfxSocialDescription());
        patientData.setTreatmentDescription(anamneseDataController.getMfxTreatmentDescription());
        patientData.setComplaints(anamneseDataController.getMfxComplaints());
        patientData.setSliderSocial(anamneseDataController.getMfxSliderSocial());
        patientData.setSliderRelation(anamneseDataController.getMfxSliderRelation());
        patientData.setSliderPhysical(anamneseDataController.getMfxSliderPhysical());
        patientData.setSliderOverall(anamneseDataController.getMfxSliderOverall());
        patientData.setSliderLeisure(anamneseDataController.getMfxSliderLeisure());
        patientData.setSliderFinancial(anamneseDataController.getMfxSliderFinancial());
        patientData.setSliderFamily(anamneseDataController.getMfxSliderFamily());
        patientData.setSliderProfession(anamneseDataController.getMfxSliderProfession());
        patientData.setTreatment(anamneseDataController.getMfxTreatment());
        patientData.setTreatmentTill(anamneseDataController.getMfxTreatmentTill());
        patientData.setTreatmentFrom(anamneseDataController.getMfxTreatmentFrom());
        patientData.setMedicalDate(anamneseDataController.getMfxMedicalDate());
        patientData.setBeginDate(anamneseDataController.getMfxBeginDate());
        patientData.setCheckDoctor(anamneseDataController.getMfxCheckDoctor());
        patientData.setCheckMedical(anamneseDataController.getMfxCheckMedical());
        patientData.setCheckTreatment(anamneseDataController.getMfxCheckTreatment());

        // Insurance Data Section
        patientData.setFamilyDoctor(insuranceDataController.getMfxFamilyDoctor());
        patientData.setInsurance(insuranceDataController.getMfxInsurance());
        patientData.setInsuranceContact(insuranceDataController.getMfxInsuranceContact());
        patientData.setDoctorCity(insuranceDataController.getMfxDoctorCity());
        patientData.setDoctorEmail(insuranceDataController.getMfxDoctorEmail());
        patientData.setDoctorMobilePhone(insuranceDataController.getMfxDoctorMobilPhone());
        patientData.setDoctorPhone(insuranceDataController.getMfxDoctorPhone());
        patientData.setDoctorPostCode(insuranceDataController.getMfxDoctorPostCode());
        patientData.setDoctorStreet(insuranceDataController.getMfxDoctorStreet());
        patientData.setInsuranceCity(insuranceDataController.getMfxInsuranceCity());
        patientData.setInsuranceEmail(insuranceDataController.getMfxInsuranceEmail());
        patientData.setInsuranceMobilePhone(insuranceDataController.getMfxInsuranceMobilPhone());
        patientData.setInsurancePhone(insuranceDataController.getMfxInsurancePhone());
        patientData.setInsurancePostCode(insuranceDataController.getMfxInsurancePostCode());
        patientData.setInsuranceStreet(insuranceDataController.getMfxInsuranceStreet());

        return patientData;
    }
}
