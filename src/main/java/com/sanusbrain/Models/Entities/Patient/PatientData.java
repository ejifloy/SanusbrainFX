package com.sanusbrain.Models.Entities.Patient;

import java.time.LocalDate;
import java.util.Random;

public class PatientData {

    private int id;

    // Personal Data
    private String initials;
    private String title;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private String gender;
    private String customerID;
    private boolean status;
    private String maritalStatus;
    private String nationality;
    private String profession;
    private String professionStatus;
    private String training;
    private String color;

    // Contact Data
    private String street;
    private String postCode;
    private String city;
    private String email;
    private String mobilePhone;
    private String phone;

    // Anamnese Data
    private String description;
    private String familyDescription;
    private String financialDescription;
    private String leisureDescription;
    private String medicalDescription;
    private String overallDescription;
    private String medicalDosage;
    private String physicalDescription;
    private String professionDescription;
    private String relationDescription;
    private String socialDescription;
    private String treatment;
    private String treatmentDescription;
    private String complaints;
    private double sliderSocial;
    private double sliderRelation;
    private double sliderPhysical;
    private double sliderOverall;
    private double sliderLeisure;
    private double sliderFinancial;
    private double sliderFamily;
    private double sliderProfession;
    private LocalDate treatmentTill;
    private LocalDate treatmentFrom;
    private LocalDate medicalDate;
    private LocalDate beginDate;
    private String checkDoctor;
    private String checkMedical;
    private String checkTreatment;
    //TODO: adjust checks output im FXML...

    // Insurance Data
    private String familyDoctor;
    private String insurance;
    private String insuranceContact;
    private String doctorCity;
    private String doctorEmail;
    private String doctorMobilePhone;
    private String doctorPhone;
    private String doctorPostCode;
    private String doctorStreet;
    private String insuranceCity;
    private String insuranceEmail;
    private String insuranceMobilePhone;
    private String insurancePhone;
    private String insurancePostCode;
    private String insuranceStreet;
    //TODO: Add additional fields


    // Getter
    public int getId() {return id;}

    // -> Personal
    public String getInitials() {return initials;}
    public String getTitle() {return title;}
    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public LocalDate getBirthdate() {return birthdate;}
    public String getGender() {return gender;}
    public String getCustomerID() {return customerID;}
    public boolean getStatus() {return status;}
    public String getMaritalStatus() {return maritalStatus;}
    public String getNationality() {return nationality;}
    public String getProfession() {return profession;}
    public String getProfessionStatus() {return professionStatus;}
    public String getTraining() {return training;}
    public String getColor() {
        //TODO Adjust color picking feature...
        if(color == null) {
            // create random object - reuse this as often as possible
            Random random = new Random();

            // create a big random number - maximum is ffffff (hex) = 16777215 (dez)
            int nextInt = random.nextInt(0xffffff + 1);

            // format it as hexadecimal string (with hashtag and leading zeros)
            color = String.format("#%06x", nextInt);
        }

        return color;
    }

    // -> Contact
    public String getStreet() {return street;}
    public String getCity() {return city;}
    public String getEmail() {return email;}
    public String getMobilePhone() {return mobilePhone;}
    public String getPhone() {return phone;}
    public String getPostCode() {return postCode;}

    // -> Anamnese
    public LocalDate getBeginDate() {return beginDate;}
    public LocalDate getMedicalDate() {return medicalDate;}
    public LocalDate getTreatmentFrom() {return treatmentFrom;}
    public LocalDate getTreatmentTill() {return treatmentTill;}
    public double getSliderProfession() {return sliderProfession;}
    public double getSliderFamily() {return sliderFamily;}
    public double getSliderFinancial() {return sliderFinancial;}
    public double getSliderLeisure() {return sliderLeisure;}
    public double getSliderOverall() {return sliderOverall;}
    public double getSliderPhysical() {return sliderPhysical;}
    public double getSliderRelation() {return sliderRelation;}
    public double getSliderSocial() {return sliderSocial;}
    public String getComplaints() {return complaints;}
    public String getTreatment() {return treatment;}
    public String getTreatmentDescription() {return treatmentDescription;}
    public String getSocialDescription() {return socialDescription;}
    public String getRelationDescription() {return relationDescription;}
    public String getProfessionDescription() {return professionDescription;}
    public String getPhysicalDescription() {return physicalDescription;}
    public String getMedicalDosage() {return medicalDosage;}
    public String getOverallDescription() {return overallDescription;}
    public String getMedicalDescription() {return medicalDescription;}
    public String getLeisureDescription() {return leisureDescription;}
    public String getFinancialDescription() {return financialDescription;}
    public String getFamilyDescription() {return familyDescription;}
    public String getDescription() {return description;}
    public String getCheckDoctor() {return checkDoctor;}
    public String getCheckMedical() {return checkMedical;}
    public String getCheckTreatment() {return checkTreatment;}

    // -> Insurance
    public String getInsuranceContact() {return insuranceContact;}
    public String getInsurance() {return insurance;}
    public String getFamilyDoctor() {return familyDoctor;}
    public String getDoctorCity() {return doctorCity;}
    public String getDoctorEmail() {return doctorEmail;}
    public String getDoctorMobilePhone() {return doctorMobilePhone;}
    public String getDoctorPhone() {return doctorPhone;}
    public String getDoctorPostCode() {return doctorPostCode;}
    public String getDoctorStreet() {return doctorStreet;}
    public String getInsuranceCity() {return insuranceCity;}
    public String getInsuranceEmail() {return insuranceEmail;}
    public String getInsuranceMobilePhone() {return insuranceMobilePhone;}
    public String getInsurancePhone() {return insurancePhone;}
    public String getInsurancePostCode() {return insurancePostCode;}
    public String getInsuranceStreet() {return insuranceStreet;}


    // Setter
    public void setId(int id) {this.id = id;}

    // -> Personal
    public void setInitials(String initials) {this.initials = initials;}
    public void setTitle(String title) {this.title = title;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setBirthdate(LocalDate birthdate) {this.birthdate = birthdate;}
    public void setGender(String gender) {this.gender = gender;}
    public void setCustomerID(String customerID) {this.customerID = customerID;}
    public void setStatus(boolean status) {this.status = status;}
    public void setMaritalStatus(String maritalStatus) {this.maritalStatus = maritalStatus;}
    public void setNationality(String nationality) {this.nationality = nationality;}
    public void setProfession(String profession) {this.profession = profession;}
    public void setProfessionStatus(String professionStatus) {this.professionStatus = professionStatus;}
    public void setTraining(String training) {this.training = training;}
    public void setColor(String color) {this.color = color;}

    // -> Contact
    public void setStreet(String street) {this.street = street;}
    public void setCity(String city) {this.city = city;}
    public void setEmail(String email) {this.email = email;}
    public void setMobilePhone(String mobilePhone) {this.mobilePhone = mobilePhone;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setPostCode(String postCode) {this.postCode = postCode;}

    // -> Anamnese
    public void setDescription(String description) {this.description = description;}
    public void setFamilyDescription(String familyDescription) {this.familyDescription = familyDescription;}
    public void setFinancialDescription(String financialDescription) {this.financialDescription = financialDescription;}
    public void setLeisureDescription(String leisureDescription) {this.leisureDescription = leisureDescription;}
    public void setMedicalDescription(String medicalDescription) {this.medicalDescription = medicalDescription;}
    public void setOverallDescription(String overallDescription) {this.overallDescription = overallDescription;}
    public void setMedicalDosage(String medicalDosage) {this.medicalDosage = medicalDosage;}
    public void setPhysicalDescription(String physicalDescription) {this.physicalDescription = physicalDescription;}
    public void setProfessionDescription(String professionDescription) {this.professionDescription = professionDescription;}
    public void setRelationDescription(String relationDescription) {this.relationDescription = relationDescription;}
    public void setSocialDescription(String socialDescription) {this.socialDescription = socialDescription;}
    public void setTreatment(String treatment) {this.treatment = treatment;}
    public void setTreatmentDescription(String treatmentDescription) {this.treatmentDescription = treatmentDescription;}
    public void setComplaints(String complaints) {this.complaints = complaints;}
    public void setSliderSocial(double sliderSocial) {this.sliderSocial = sliderSocial;}
    public void setSliderRelation(double sliderRelation) {this.sliderRelation = sliderRelation;}
    public void setSliderPhysical(double sliderPhysical) {this.sliderPhysical = sliderPhysical;}
    public void setSliderOverall(double sliderOverall) {this.sliderOverall = sliderOverall;}
    public void setSliderLeisure(double sliderLeisure) {this.sliderLeisure = sliderLeisure;}
    public void setSliderFinancial(double sliderFinancial) {this.sliderFinancial = sliderFinancial;}
    public void setSliderFamily(double sliderFamily) {this.sliderFamily = sliderFamily;}
    public void setSliderProfession(double sliderProfession) {this.sliderProfession = sliderProfession;}
    public void setTreatmentTill(LocalDate treatmentTill) {this.treatmentTill = treatmentTill;}
    public void setTreatmentFrom(LocalDate treatmentFrom) {this.treatmentFrom = treatmentFrom;}
    public void setMedicalDate(LocalDate medicalDate) {this.medicalDate = medicalDate;}
    public void setBeginDate(LocalDate beginDate) {this.beginDate = beginDate;}
    public void setCheckDoctor(String checkDoctor) {this.checkDoctor = checkDoctor;}
    public void setCheckMedical(String checkMedical) {this.checkMedical = checkMedical;}
    public void setCheckTreatment(String checkTreatment) {this.checkTreatment = checkTreatment;}

    // -> Insurance
    public void setFamilyDoctor(String familyDoctor) {this.familyDoctor = familyDoctor;}
    public void setInsurance(String insurance) {this.insurance = insurance;}
    public void setInsuranceContact(String insuranceContact) {this.insuranceContact = insuranceContact;}
    public void setDoctorCity(String doctorCity) {this.doctorCity = doctorCity;}
    public void setDoctorEmail(String doctorEmail) {this.doctorEmail = doctorEmail;}
    public void setDoctorMobilePhone(String doctorMobilePhone) {this.doctorMobilePhone = doctorMobilePhone;}
    public void setDoctorPhone(String doctorPhone) {this.doctorPhone = doctorPhone;}
    public void setDoctorPostCode(String doctorPostCode) {this.doctorPostCode = doctorPostCode;}
    public void setDoctorStreet(String doctorStreet) {this.doctorStreet = doctorStreet;}
    public void setInsuranceCity(String insuranceCity) {this.insuranceCity = insuranceCity;}
    public void setInsuranceEmail(String insuranceEmail) {this.insuranceEmail = insuranceEmail;}
    public void setInsuranceMobilePhone(String insuranceMobilePhone) {this.insuranceMobilePhone = insuranceMobilePhone;}
    public void setInsurancePhone(String insurancePhone) {this.insurancePhone = insurancePhone;}
    public void setInsurancePostCode(String insurancePostCode) {this.insurancePostCode = insurancePostCode;}
    public void setInsuranceStreet(String insuranceStreet) {this.insuranceStreet = insuranceStreet;}

    /**
     * Generates initials value for the patient.
     *
     * @return provides the initials as string value.
     */
    public String generateInitials(){
        initials = (firstName.charAt(0)+""+lastName.charAt(0)).toUpperCase();
        System.out.println("Check initials: "+initials);
        return initials;
    }
}
