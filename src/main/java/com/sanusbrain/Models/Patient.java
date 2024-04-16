package com.sanusbrain.Models;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Patient {
    //Properties-Variables
    private final StringProperty first_name;
    private final StringProperty last_name;
    private final StringProperty patient_id;
    private final ObjectProperty<LocalDate> birthdate;
    private final ObjectProperty<LocalDate> recent_meeting;


    //Constructor for Patient-Objects
    public Patient(String first_name, String last_name, String patient_id, LocalDate birthdate, LocalDate recent_meeting){
        this.first_name = new SimpleStringProperty(this,"first_name", first_name);
        this.last_name = new SimpleStringProperty(this,"last_name", last_name);
        this.patient_id = new SimpleStringProperty(this,"patient_id", patient_id);
        this.birthdate = new SimpleObjectProperty<>(this, "birthdate", birthdate);
        this.recent_meeting = new SimpleObjectProperty<>(this, "recent_meeting", recent_meeting);

    }

    //Getter for Properties
    public StringProperty first_nameProperty() {
        return first_name;
    }
    public StringProperty last_nameProperty() {
        return last_name;
    }
    public StringProperty patient_idProperty() {
        return patient_id;
    }
    public ObjectProperty<LocalDate> birthdateProperty() {
        return birthdate;
    }
    public ObjectProperty<LocalDate> recent_meetingProperty() {
        return recent_meeting;
    }
}
