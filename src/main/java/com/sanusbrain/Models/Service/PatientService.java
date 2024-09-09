package com.sanusbrain.Models.Service;

import com.sanusbrain.Models.DAO.PatientDAO;
import com.sanusbrain.Models.Entities.Patient.PatientEntry;
import com.sanusbrain.Models.Entities.Patient.PatientData;

import java.util.List;

public class PatientService {

    private final PatientDAO patientDAO = new PatientDAO();

    public List<PatientEntry> getAllPatients(){
        return patientDAO.getAllPatients();
    }

    public PatientData getPatientById(int patientId){
        return  patientDAO.getPatientDataById(patientId);
    }
}
