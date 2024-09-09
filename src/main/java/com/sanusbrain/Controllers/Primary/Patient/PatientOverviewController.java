package com.sanusbrain.Controllers.Primary.Patient;

import com.sanusbrain.Models.Entities.Patient.PatientData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class PatientOverviewController implements Initializable {

    @FXML
    private Label fxInitials,fxBirthdate,fxFullName,fxGender,
            fxPhone,fxEmail,fxMobilePhone, fxStreet,fxCity,fxPostcode,
            fxBeginnDate;


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadPatientData(PatientData patientData) {
        // Clears all FXML-Fields
        clearFields();

        LocalDate birthday = patientData.getBirthdate();
        LocalDate beginn = patientData.getBeginDate();

        if(birthday != null){
            int age = Period.between(birthday, LocalDate.now()).getYears();
            fxBirthdate.setText(patientData.getBirthdate().format(formatter)+" - "+age+" Jahre");
        }else
            fxBirthdate.setText("");

        fxInitials.setStyle("-fx-background-color:"+patientData.getColor());
        fxInitials.setText(patientData.getInitials());
        fxFullName.setText(patientData.getFirstName()+" "+patientData.getLastName());
        fxGender.setText(patientData.getGender());
        fxPhone.setText(patientData.getPhone());
        fxEmail.setText(patientData.getEmail());
        fxMobilePhone.setText(patientData.getMobilePhone());
        fxStreet.setText(patientData.getStreet());
        fxCity.setText(patientData.getCity());
        fxPostcode.setText(patientData.getPostCode());
        fxBeginnDate.setText(beginn != null ? beginn.format(formatter) : "");
    }

    void clearFields(){
        fxInitials.setStyle("-fx-background-color: transparent");
        fxInitials.setText("");
        fxBirthdate.setText("");
        fxFullName.setText("");
        fxGender.setText("");
        fxPhone.setText("");
        fxEmail.setText("");
        fxMobilePhone.setText("");
        fxStreet.setText("");
        fxCity.setText("");
        fxPostcode.setText("");
        fxBeginnDate.setText("");
    }
}
