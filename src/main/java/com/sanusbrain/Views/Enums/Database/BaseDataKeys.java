package com.sanusbrain.Views.Enums.Database;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum BaseDataKeys {
    // Personal Data Keys
    MFXTITLE("title"),
    MFXFIRSTNAME("firstname"),
    MFXLASTNAME("lastname"),
    MFXBIRTHDATE("birthdate"),
    MFXGENDER("gender"),
    MFXCUSTOMERID("customer_id"),
    MFXSTATUS("status"),
    MFXMARITALSTATUS("marital_status"),
    MFXNATIONALITY("nationality"),
    MFXPROFESSION("profession"),
    MFXPROFESSIONSTATUS("profession_status"),
    MFXTRAINING("training"),
    

    // Contact Data Keys
    MFXCITY("city"),
    MFXEMAIL("email"),
    MFXMOBILPHONE("mobile_phone"),
    MFXPHONE("phone"),
    MFXPOSTCODE("postcode"),
    MFXSTREET("street"),


    // Anamnese Data Keys
    MFXDESCRIPTION("description"),
    MFXBEGINDATE("begin_date"),
    MFXMEDICALDATE("medical_date"),
    MFXTREATMENTFROM("treatment_from"),
    MFXTREATMENTTILL("treatment_till"),
    MFXCHECKDOCTOR("check_doctor"),
    MFXCHECKTREATMENT("check_treatment"),
    MFXCHECKMEDICAL("check_medical"),
    MFXCOMPLAINTS("complaints"),
    MFXTREATMENT("treatment"),
    MFXTREATMENTDESCRIPTION("treatment_description"),
    MFXMEDICALDESCRIPTION("medical_description"),
    MFXMEDICALDOSAGE("medical_dosage"),
    MFXOVERALLDESCRIPTION("overall_description"),
    MFXPHYSICALDESCRIPTION("physical_description"),
    MFXPROFESSIONDESCRIPTION("profession_description"),
    MFXRELATIONDESCRIPTION("relation_description"),
    MFXFAMILYDESCRIPTION("family_description"),
    MFXFINANCIALDESCRIPTION("financial_description"),
    MFXLEISUREDESCRIPTION("leisure_description"),
    MFXSOCIALDESCRIPTION("social_description"),
    MFXSLIDERFAMILY("slider_family"),
    MFXSLIDERFINANCIAL("slider_financial"),
    MFXSLIDERLEISURE("slider_leisure"),
    MFXSLIDEROVERALL("slider_overall"),
    MFXSLIDERPHYSICAL("slider_physical"),
    MFXSLIDERPROFESSION("slider_profession"),
    MFXSLIDERRELATION("slider_relation"),
    MFXSLIDERSOCIAL("slider_social"),


    // Insurance Data Keys
    MFXDOCTORCITY("doctor_city"),
    MFXDOCTOREMAIL("doctor_email"),
    MFXDOCTORMOBILPHONE("doctor_mobile_phone"),
    MFXDOCTORPHONE("doctor_phone"),
    MFXDOCTORPOSTCODE("doctor_post_code"),
    MFXDOCTORSTREET("doctor_street"),
    MFXFAMILYDOCTOR("family_doctor"),
    MFXINSURANCE("insurance"),
    MFXINSURANCECITY("insurance_city"),
    MFXINSURANCECONTACT("insurance_contact"),
    MFXINSURANCEEMAIL("insurance_email "),
    MFXINSURANCEMOBILPHONE("insurance_mobile_phone"),
    MFXINSURANCEPHONE("insurance_phone"),
    MFXINSURANCEPOSTCODE("insurance_post_code"),
    MFXINSURANCESTREET("insurance_street");


    public final String label;
    // Set to hold all enum names for quick lookup
    private static final Set<String> KEYS = new HashSet<>();
    private static final Map<BaseDataKeys,String> LABELS = new HashMap<>();

    // Static block to initialize the keys set
    static {
        for (BaseDataKeys key : values()){
            KEYS.add(key.name().toLowerCase());
        }
    }

    // Static block to initialize the labels set
    static {
        for (BaseDataKeys key : values()){
            LABELS.put(key, key.label);
        }
    }

    BaseDataKeys(String label) {
        this.label = label;
    }

    /**
     * Checks if a given string exists in the enum.
     *
     * @param value String to be checked.
     * @return Returns true, if the String exists in the enum, otherwise false;
     */
    public static boolean contains(String value) {
        return KEYS.contains(value.toLowerCase());
    }

    /**
     * Returns the database-table label of the key.
     */
    public String getLabel() {
        return label;
    }
}
