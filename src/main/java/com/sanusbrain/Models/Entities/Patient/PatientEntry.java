package com.sanusbrain.Models.Entities.Patient;


import javafx.beans.property.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Patient class represents a patient entity with various properties.
 */
public class PatientEntry {

    // Properties-Variables
    private final int id;
    private final StringProperty initials;
    private final StringProperty first_name;
    private final StringProperty last_name;
    private final StringProperty customer_id;
    private final ObjectProperty<LocalDate> birthdate;
    private final StringProperty gender;
    private final BooleanProperty status;
    private final StringProperty color;


    /**
     * Constructor for creating a Patient-Entry object.
     *
     * @param first_name    first name of the patient.
     * @param last_name     last name of the patient.
     * @param customer_id   customer ID of the patient.
     * @param birthdate     birthdate of the patient.
     * @param gender        gender of the patient.
     * @param status        status of the patient.
     * @param color         color for the patient.
     */
    public PatientEntry(int id, String first_name, String last_name, String customer_id, LocalDate birthdate, String gender, boolean status, String color){
        this.id = id;
        this.first_name = new SimpleStringProperty(this,"first_name", first_name);
        this.last_name = new SimpleStringProperty(this,"last_name", last_name);
        this.customer_id = new SimpleStringProperty(this,"customer_id", customer_id);
        this.birthdate = new SimpleObjectProperty<>(this, "birthdate", birthdate);
        this.gender = new SimpleStringProperty(this, "gender", gender);
        this.status = new SimpleBooleanProperty(this, "status", status);
        this.color = new SimpleStringProperty(this,"color", color);
        this.initials = new SimpleStringProperty(this, "initials", generateInitials(first_name,last_name));

        // Update initials when first or last name has been changed
        this.first_name.addListener((observableValue, oldVal, newVal) -> updateInitials() );
        this.last_name.addListener((observableValue, oldVal, newVal) -> updateInitials() );
    }


    // Getter for Properties
    public int getId(){
        return id;
    }
    public StringProperty initialsProperty() {
        return initials;
    }
    public StringProperty first_nameProperty() {
        return first_name;
    }
    public StringProperty last_nameProperty() {
        return last_name;
    }
    public StringProperty customer_idProperty() {
        return customer_id;
    }
    public ObjectProperty<LocalDate> birthdateProperty() {
        return birthdate;
    }
    public StringProperty genderProperty() {
        return gender;
    }
    public BooleanProperty statusProperty() {
        return status;
    }
    public StringProperty colorProperty(){return color;}



    /**
     * Updates the initials based on the current first and last name.
     */
    private void updateInitials() {
        this.initials.set(generateInitials(first_name.get(), last_name.get()));
    }

    /**
     * Generates the initials from the first and last name.
     *
     * @param firstName The first name of the patient.
     * @param lastName  The last name of the patient.
     * @return The initials.
     */
    private String generateInitials(String firstName, String lastName) {
        return (firstName.charAt(0)+""+lastName.charAt(0)).toUpperCase();
    }

    /**
     * Returns the birthdate as a formatted string or an empty string if null.
     *
     * @return The formatted birthdate string ("dd.MM.yyy").
     */
    public String getFormattedBirthdate(){
        LocalDate date = birthdate.get();
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "";
    }
}