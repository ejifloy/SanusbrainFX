package com.sanusbrain.Models.DAO;

import com.sanusbrain.Models.Entities.Patient.PatientEntry;
import com.sanusbrain.Models.Entities.Patient.PatientData;
import com.sanusbrain.Models.Model;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PatientDAO {

    //Fetch basic patient details to fill the ListView
    public List<PatientEntry> getAllPatients(){
        List<PatientEntry> patientEntries = new ArrayList<>();
        List<Map<String, Object>> results = Model.getInstance().getDatabaseDriver().getAllDataFromTable("patient");

        for(Map<String, Object> row:results){
            int id = (int) row.get("patient_id");
            String firstName = (String) row.get("firstname");
            String lastName = (String) row.get("lastname");
            String gender = (String) row.get("gender");
            String customerID = (String) row.get("customer_id");
            boolean status = ((Number) row.get("status")).intValue() == 1;
            LocalDate birthdate = Optional.ofNullable((Date) row.get("birthdate"))
                    .map(Date::toLocalDate)
                    .orElse(null);
            String color = (String) row.get("color");

            patientEntries.add(new PatientEntry(id,firstName,lastName,customerID,birthdate,gender,status,color));
        }

        return patientEntries;
    }

    //Fetch detailed patient data for a selected patient
    public PatientData getPatientDataById(int patientId){
        PatientData patientData = new PatientData();
        patientData.setId(patientId);

        loadPersonalData(patientData);
        loadContactData(patientData);
        loadAnamneseData(patientData);
        loadInsuranceData(patientData);

        return patientData;
    }

    private void loadPersonalData(PatientData patientData) {
        List<Map<String,Object>> result = Model.getInstance().getDatabaseDriver().getPatientSectionDataById("patient", patientData.getId());

        for(Map<String, Object> row:result){
            patientData.setInitials((String) row.get("initials"));
            patientData.setTitle((String) row.get("title"));
            patientData.setFirstName((String) row.get("firstname"));
            patientData.setLastName((String) row.get("lastname"));
            patientData.setGender((String) row.get("gender"));
            patientData.setCustomerID((String) row.get("customer_id"));
            patientData.setStatus(((Number) row.get("status")).intValue() == 1);
            patientData.setBirthdate(Optional.ofNullable((Date) row.get("birthdate")).map(Date::toLocalDate).orElse(null));
            patientData.setColor((String) row.get("color"));
            patientData.setMaritalStatus((String) row.get("marital_status"));
            patientData.setNationality((String) row.get("nationality"));
            patientData.setProfession((String) row.get("profession"));
            patientData.setProfessionStatus((String) row.get("profession_status"));
            patientData.setTraining((String) row.get("training"));
        }
    }

    private void loadContactData(PatientData patientData) {
        List<Map<String,Object>> result = Model.getInstance().getDatabaseDriver().getPatientSectionDataById("contact", patientData.getId());

        for(Map<String, Object> row:result){
            patientData.setStreet((String) row.get("street"));
            patientData.setPostCode((String) row.get("postcode"));
            patientData.setCity((String) row.get("city"));
            patientData.setMobilePhone((String) row.get("mobile_phone"));
            patientData.setPhone((String) row.get("phone"));
            patientData.setEmail((String) row.get("email"));
        }
    }

    private void loadAnamneseData(PatientData patientData) {
        List<Map<String,Object>> result = Model.getInstance().getDatabaseDriver().getPatientSectionDataById("anamnese", patientData.getId());

        for(Map<String, Object> row:result){
            patientData.setComplaints(((String) row.get("complaints")));
            patientData.setDescription(((String) row.get("description")));
            patientData.setFamilyDescription(((String) row.get("family_description")));
            patientData.setFinancialDescription(((String) row.get("financial_description")));
            patientData.setLeisureDescription(((String) row.get("leisure_description")));
            patientData.setMedicalDescription(((String) row.get("medical_description")));
            patientData.setOverallDescription(((String) row.get("overall_description")));
            patientData.setMedicalDosage(((String) row.get("medical_dosage")));
            patientData.setPhysicalDescription(((String) row.get("physical_description")));
            patientData.setProfessionDescription(((String) row.get("profession_description")));
            patientData.setRelationDescription(((String) row.get("relation_description")));
            patientData.setSocialDescription(((String) row.get("social_description")));
            patientData.setTreatment(((String) row.get("treatment")));
            patientData.setTreatmentDescription(((String) row.get("treatment_description")));
            patientData.setComplaints(((String) row.get("complaints")));

            patientData.setSliderSocial((Double) row.get(("slider_social")));
            patientData.setSliderRelation((Double) row.get(("slider_relation")));
            patientData.setSliderPhysical((Double) row.get(("slider_physical")));
            patientData.setSliderOverall((Double) row.get(("slider_overall")));
            patientData.setSliderLeisure((Double) row.get(("slider_leisure")));
            patientData.setSliderFinancial((Double) row.get(("slider_financial")));
            patientData.setSliderFamily((Double) row.get(("slider_family")));
            patientData.setSliderProfession((Double) row.get(("slider_profession")));

            patientData.setTreatmentTill(Optional.ofNullable((Date) row.get("treatment_till")).map(Date::toLocalDate).orElse(null));
            patientData.setTreatmentFrom(Optional.ofNullable((Date) row.get("treatment_from")).map(Date::toLocalDate).orElse(null));
            patientData.setMedicalDate(Optional.ofNullable((Date) row.get("medical_date")).map(Date::toLocalDate).orElse(null));
            patientData.setBeginDate(Optional.ofNullable((Date) row.get("begin_date")).map(Date::toLocalDate).orElse(null));

            patientData.setCheckDoctor((String) row.get("check_doctor"));
            patientData.setCheckMedical((String) row.get("check_medical"));
            patientData.setCheckTreatment((String) row.get("check_treatment"));
        }
    }

    private void loadInsuranceData(PatientData patientData) {
        List<Map<String,Object>> result = Model.getInstance().getDatabaseDriver().getPatientSectionDataById("insurance", patientData.getId());

        for(Map<String, Object> row:result){
            patientData.setFamilyDoctor((String) row.get("family_doctor"));
            patientData.setInsurance((String) row.get("insurance"));
            patientData.setInsuranceContact((String) row.get("insurance_contact"));
            patientData.setDoctorCity((String) row.get("doctor_city"));
            patientData.setDoctorEmail((String) row.get("doctor_email"));
            patientData.setDoctorMobilePhone((String) row.get("doctor_mobile_phone"));
            patientData.setDoctorPhone((String) row.get("doctor_phone"));
            patientData.setDoctorPostCode((String) row.get("doctor_post_code"));
            patientData.setDoctorStreet((String) row.get("doctor_street"));
            patientData.setInsuranceCity((String) row.get("insurance_city"));
            patientData.setInsuranceEmail((String) row.get("insurance_email"));
            patientData.setInsuranceMobilePhone((String) row.get("insurance_mobile_phone"));
            patientData.setInsurancePhone((String) row.get("insurance_phone"));
            patientData.setInsurancePostCode((String) row.get("insurance_post_code"));
            patientData.setInsuranceStreet((String) row.get("insurance_street"));
        }
    }
}
