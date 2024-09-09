package com.sanusbrain.Models;

import com.sanusbrain.Utils.FieldMonitorUtil;
import com.sanusbrain.Models.Entities.Patient.PatientData;
import com.sanusbrain.Views.Enums.Database.BaseDataKeys;
import com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

public class DatabaseDriver {
    private Connection connection;
    private static final String URL = "jdbc:mysql://localhost:3306/test";
    private static final String USER = "root";
    private static final String PASSWORD = "pw123456";

    // Constructor
    public DatabaseDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    // Get a new connection for each operation
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/sanusbrain", "root", "pw123456");
    }

    public List<Map<String, Object>> getLoginData(String username, String password) {
        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection connection = getConnection()){
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM sanusbrain.users WHERE user_name='" + username + "' AND user_password='" + password + "';");
            extractResultSetData(resultSet, results);
        } catch (SQLException e) {
            // Displays error information
            Model.getInstance().getDialogController().showErrorDialog("Login (DatabaseDriver 32)","Es ist ein fehler aufgetreten:\n\n"+e);
            throw new RuntimeException(e);
        }
        return results;
    }

    public List<Map<String, Object>> getAllDataFromTable(String table) {
        List<Map<String, Object>> results = new ArrayList<>();

        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM "+table);
            extractResultSetData(resultSet,results);
        } catch (SQLException e) {
            Model.getInstance().getDialogController().showErrorDialog(table+"-Daten laden(DatabaseDriver 46)","Es ist ein fehler aufgetreten:\n\n"+e);
            throw new RuntimeException(e);
        }
        return results;
    }

    public List<Map<String,Object>> getPatientSectionDataById(String section, int patientId){
        List<Map<String,Object>> result = new ArrayList<>();

        // Preparing SQL query
        String query = "SELECT * FROM " + section + " WHERE patient_id = ?";

        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,patientId);
            ResultSet resultSet = statement.executeQuery();
            extractResultSetData(resultSet, result);
        } catch (SQLException e) {
            Model.getInstance().getDialogController().showErrorDialog("Patienten-Daten laden ("+section+", DatabaseDriver 64)", "Es ist ein fehler aufgetreten:\n\n"+e);
            throw new RuntimeException(e);
        }
        return result;
    }


    /**
     * This method creates a new patient in the database.
     *
     * @param patientData An instance of PatientData containing all the patient's information.
     */
    public void createPatient(PatientData patientData) {
        // Counter for checking correct query execution
        int affectedRows;

        try (Connection connection = getConnection()){
            // Start a transaction
            connection.setAutoCommit(false);

            // Insert into the patient table
            String patientSQL = "INSERT INTO patient (initials, title, firstname, lastname, birthdate, gender, customer_id, status, marital_status, nationality, profession, profession_status, training, color) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement patientStatement = connection.prepareStatement(patientSQL, Statement.RETURN_GENERATED_KEYS)) {
                patientStatement.setString(1, patientData.getInitials());
                patientStatement.setString(2, patientData.getTitle());
                patientStatement.setString(3, patientData.getFirstName());
                patientStatement.setString(4, patientData.getLastName());
                patientStatement.setDate(5, Optional.ofNullable(patientData.getBirthdate()).map(Date::valueOf).orElse(null));
                patientStatement.setString(6, patientData.getGender());
                patientStatement.setString(7, patientData.getCustomerID());
                patientStatement.setInt(8, patientData.getStatus() ? 1 : 0);
                patientStatement.setString(9, patientData.getMaritalStatus());
                patientStatement.setString(10, patientData.getNationality());
                patientStatement.setString(11, patientData.getProfession());
                patientStatement.setString(12, patientData.getProfessionStatus());
                patientStatement.setString(13, patientData.getTraining());
                patientStatement.setString(14, patientData.getColor());

                affectedRows = patientStatement.executeUpdate();
                affectedRowsCheck(affectedRows);

                // Get the generated patient ID
                ResultSet generatedKeys = patientStatement.getGeneratedKeys();
                int patientID;
                if (generatedKeys.next()) {
                    patientID = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating patient failed, no ID obtained.");
                }

                // Insert into address table
                String addressSQL = "INSERT INTO contact (patient_id, street, postcode, city, mobile_phone, phone, email) VALUES (?,?,?,?,?,?,?)";
                try (PreparedStatement addressStatement = connection.prepareStatement(addressSQL)) {
                    addressStatement.setInt(1, patientID);
                    addressStatement.setString(2, patientData.getStreet());
                    addressStatement.setString(3, patientData.getPostCode());
                    addressStatement.setString(4, patientData.getCity());
                    addressStatement.setString(5, patientData.getMobilePhone());
                    addressStatement.setString(6, patientData.getPhone());
                    addressStatement.setString(7, patientData.getEmail());

                    affectedRows = addressStatement.executeUpdate();
                    affectedRowsCheck(affectedRows);
                }

                // Insert into anamnese table
                String anamneseSQL = "INSERT INTO anamnese (patient_id, description, family_description, financial_description, leisure_description, medical_description, overall_description, medical_dosage, physical_description, profession_description, relation_description, social_description, treatment, treatment_description, complaints, slider_social, slider_relation, slider_physical, slider_overall, slider_leisure, slider_financial, slider_family, slider_profession, treatment_till, treatment_from, medical_date, begin_date, check_doctor, check_medical, check_treatment) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement anamneseStatement = connection.prepareStatement(anamneseSQL)) {
                    anamneseStatement.setInt(1, patientID);
                    anamneseStatement.setString(2, patientData.getDescription());
                    anamneseStatement.setString(3, patientData.getFamilyDescription());
                    anamneseStatement.setString(4, patientData.getFinancialDescription());
                    anamneseStatement.setString(5, patientData.getLeisureDescription());
                    anamneseStatement.setString(6, patientData.getMedicalDescription());
                    anamneseStatement.setString(7, patientData.getOverallDescription());
                    anamneseStatement.setString(8, patientData.getMedicalDosage());
                    anamneseStatement.setString(9, patientData.getPhysicalDescription());
                    anamneseStatement.setString(10, patientData.getProfessionDescription());
                    anamneseStatement.setString(11, patientData.getRelationDescription());
                    anamneseStatement.setString(12, patientData.getSocialDescription());
                    anamneseStatement.setString(13, patientData.getTreatment());
                    anamneseStatement.setString(14, patientData.getTreatmentDescription());
                    anamneseStatement.setString(15, patientData.getComplaints());
                    anamneseStatement.setDouble(16, patientData.getSliderSocial());
                    anamneseStatement.setDouble(17, patientData.getSliderRelation());
                    anamneseStatement.setDouble(18, patientData.getSliderPhysical());
                    anamneseStatement.setDouble(19, patientData.getSliderOverall());
                    anamneseStatement.setDouble(20, patientData.getSliderLeisure());
                    anamneseStatement.setDouble(21, patientData.getSliderFinancial());
                    anamneseStatement.setDouble(22, patientData.getSliderFamily());
                    anamneseStatement.setDouble(23, patientData.getSliderProfession());
                    anamneseStatement.setDate(24, patientData.getTreatmentTill() != null ? Date.valueOf(patientData.getTreatmentTill()) : null);
                    anamneseStatement.setDate(25, patientData.getTreatmentFrom() != null ? Date.valueOf(patientData.getTreatmentFrom()) : null);
                    anamneseStatement.setDate(26, patientData.getMedicalDate() != null ? Date.valueOf(patientData.getMedicalDate()) : null);
                    anamneseStatement.setDate(27, patientData.getBeginDate() != null ? Date.valueOf(patientData.getBeginDate()) : null);
                    anamneseStatement.setString(28, patientData.getCheckDoctor());
                    anamneseStatement.setString(29, patientData.getCheckMedical());
                    anamneseStatement.setString(30, patientData.getCheckTreatment());

                    affectedRows = anamneseStatement.executeUpdate();
                    affectedRowsCheck(affectedRows);
                }

                // Insert into the insurance table
                String insuranceSQL = "INSERT INTO insurance (patient_id, family_doctor, insurance, insurance_contact, doctor_city, doctor_email, doctor_mobile_phone, doctor_phone, doctor_post_code, doctor_street, insurance_city, insurance_email, insurance_mobile_phone, insurance_phone, insurance_post_code, insurance_street) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement insuranceStatement = connection.prepareStatement(insuranceSQL)) {
                    insuranceStatement.setInt(1, patientID);
                    insuranceStatement.setString(2, patientData.getFamilyDoctor());
                    insuranceStatement.setString(3, patientData.getInsurance());
                    insuranceStatement.setString(4, patientData.getInsuranceContact());
                    insuranceStatement.setString(5, patientData.getDoctorCity());
                    insuranceStatement.setString(6, patientData.getDoctorEmail());
                    insuranceStatement.setString(7, patientData.getDoctorMobilePhone());
                    insuranceStatement.setString(8, patientData.getDoctorPhone());
                    insuranceStatement.setString(9, patientData.getDoctorPostCode());
                    insuranceStatement.setString(10, patientData.getDoctorStreet());
                    insuranceStatement.setString(11, patientData.getInsuranceCity());
                    insuranceStatement.setString(12, patientData.getInsuranceEmail());
                    insuranceStatement.setString(13, patientData.getInsuranceMobilePhone());
                    insuranceStatement.setString(14, patientData.getInsurancePhone());
                    insuranceStatement.setString(15, patientData.getInsurancePostCode());
                    insuranceStatement.setString(16, patientData.getInsuranceStreet());

                    affectedRows = insuranceStatement.executeUpdate();
                    affectedRowsCheck(affectedRows);

                }

                // Commit the transaction
                connection.commit();

            } catch (SQLException e) {
                // Rollback the transaction on error
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    Model.getInstance().getDialogController().showErrorDialog("Patient hinzufügen", "Rollback failed:\n\n" + rollbackEx);
                }

                // Shows dialog window with error of failure while adding patient to database
                Model.getInstance().getDialogController().showErrorDialog("Patient hinzufügen", "Es ist ein fehler aufgetreten:\n\n" + e);
                throw new RuntimeException(e);

            } finally {

                try {
                    // Reset auto-commit to true
                    connection.setAutoCommit(true);
                } catch (SQLException ex) {
                    Model.getInstance().getDialogController().showErrorDialog("Patient hinzufügen", "Resetting auto-commit failed:\n\n" + ex);
                }
            }
        } catch (SQLException e) {
            Model.getInstance().getDialogController().showErrorDialog("Patient hinzufügen", "Connection failed:\n\n" + e);
            throw new RuntimeException(e);
        }

        // Shows dialog window with confirmation of successfully adding patient to database
        Model.getInstance().getDialogController().showSuccessDialog("Patient hinzufügen", "Patient wurde erfolgreich hinzugefügt.");
    }


    /**
     * Save Patient Methode
     * <p>
     * This Methode saves changes of an existing patient in the database and
     * sets the flag "newPatientFlag" within the Model Class to "false", for
     * adjusting the access of the MenuBars Save-Button.
     *
     */
    public void savePatientChanges() {
        Iterator<String> sectionsIterator = Arrays.asList("patient","contact","anamnese","insurance").iterator();
        FieldMonitorUtil fieldMonitorUtil = Model.getInstance().getFieldMonitorUtil();
        List<String> updateStatementsList = new ArrayList<>();
        Map<BaseDataKeys, Object> initialValues = fieldMonitorUtil.getInitialFieldValues();
        List<Set<Map.Entry<BaseDataKeys, Object>>> changedFieldsList= new ArrayList<>();

        List<Map<BaseDataKeys, Object>> currentValuesList = Arrays.asList(
                fieldMonitorUtil.collectCurrentValues(BaseDataViewOptions.PERSONAL_DATA),
                fieldMonitorUtil.collectCurrentValues(BaseDataViewOptions.CONTACT_DATA),
                fieldMonitorUtil.collectCurrentValues(BaseDataViewOptions.ANAMNESE_DATA),
                fieldMonitorUtil.collectCurrentValues(BaseDataViewOptions.INSURANCE_DATA));

        System.out.println("current size:"+currentValuesList.size());

        for(Map<BaseDataKeys,Object> currentValues: currentValuesList){
            String section = sectionsIterator.next();

            // Filter the changed fields
            Set<Map.Entry<BaseDataKeys, Object>> changedFields = currentValues.entrySet().stream()
                    .filter(entry -> !Objects.equals(initialValues.get(entry.getKey()), entry.getValue()))
                    .collect(Collectors.toSet());

            System.out.println("-----------------------------------------------\nDatabase Save:\n");
            System.out.println("List: "+currentValues+"\n");
            if(currentValues.isEmpty()) System.out.println("empty...");
            for(Map.Entry<BaseDataKeys,Object> entry : changedFields){
                System.out.printf("%-15s : %-15s\n", entry.getKey().getLabel(), entry.getValue());
            }

            if (!changedFields.isEmpty()){
                updateStatementsList.add(buildSQLUpdateStatement(section, changedFields));
                changedFieldsList.add(new HashSet<>(changedFields));
            }

            //Clear comparing list for next section
            changedFields.clear();
        }

        try (Connection connection = Model.getInstance().getDatabaseDriver().getConnection()) {
            // Start a transaction
            connection.setAutoCommit(false);

            try{
                // Prepare update-statement for each section (personal,contact,anamnese,insurance)
                for(int i=0; i < updateStatementsList.size(); i++) {
                    String sql = updateStatementsList.get(i);
                    System.out.println("check cf-list:"+changedFieldsList.size()+"\ncontent:"+changedFieldsList);
                    Set<Map.Entry<BaseDataKeys, Object>> changedFields = changedFieldsList.get(i);

                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        int index = 1;
                        for (Map.Entry<BaseDataKeys, Object> entry : changedFields) {
                            System.out.printf("index:"+index+" - %-15s : %-15s\n", entry.getKey().getLabel(), entry.getValue());
                            preparedStatement.setObject(index++, entry.getValue());
                        }
                        preparedStatement.setInt(index, Model.getInstance().getSelectedPatientEntry().getId());
                        System.out.println("\nExecute:"+preparedStatement);
                        preparedStatement.executeUpdate();
                    }
                }
                // Commit the transaction
                connection.commit();
            } catch (SQLException e){
                // Rollback the transaction on error
                try{
                    connection.rollback();
                }catch (SQLException rollbackEx){
                    Model.getInstance().getDialogController().showErrorDialog("Änderung speichern (302,DatabaseDriver)","Rollback fehlgeschlagen:\n\n"+rollbackEx);
                }

                // Show error-dialog with error while saving patient changes to the database
                Model.getInstance().getDialogController().showErrorDialog("Änderung speichern (306,DatabaseDriver)","Es ist ein Fehler aufgetreten:\n\n"+e);
            }finally {
                try{
                    // Reset auto-commit to true
                    connection.setAutoCommit(true);
                }catch (SQLException ex){
                    Model.getInstance().getDialogController().showErrorDialog("Änderung speichern (312,DatabaseDriver)","Es ist ein fehler aufgetreten:\n\n"+ex);
                }
            }

        } catch (SQLException e) {
            Model.getInstance().getDialogController().showErrorDialog("Änderung speichern (317,DatabaseDriver)", "Es ist ein Fehler aufgetreten:\n\n" + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Builds an SQL update statement for a given section and set of changed fields.
     *
     * @param section      The section to update (e.g., "patient", "contact").
     * @param changedFields The set of changed fields.
     * @return The SQL update statement as a String.
     */
    public String buildSQLUpdateStatement(String section, Set<Map.Entry<BaseDataKeys, Object>> changedFields){
        // Build the SQL update statement
        String sql = "UPDATE "+section+ " SET ";
        String setClauses = changedFields.stream()
                .map(entry -> entry.getKey().getLabel() + " = ?")
                .collect(Collectors.joining(", "));
        sql += setClauses + " WHERE patient_id = ?";

        return sql;
    }

    /**
     * This Method check if the query execution has affected the rows correctly by checking the return value.
     *
     * @param affectedRows Counter that holds the return Value of {@link java.sql.PreparedStatement#executeUpdate() executeUpdate()}.
     * @throws SQLException Mentions an error, if the return value is 0.
     */
    private static void affectedRowsCheck(int affectedRows) throws SQLException {
        if (affectedRows == 0) {
            Model.getInstance().getDialogController().showErrorDialog("Patient hinzufügen","Erstellung des Patienten fehlgeschlagen, keine Reihen betroffen.");
            throw new SQLException("Erstellung des Patienten fehlgeschlagen, keine Reihen betroffen.");
        }
    }

    /**
     * Deletes an existing patient from the database.
     *
     * @param id The ID of The patient.
     */
    public void deletePatient(int id){
        String patientSQL = "DELETE FROM patient WHERE patient_id = ?;";
        String contactSQL = "DELETE FROM contact WHERE patient_id = ? ;";
        String anamneseSQL = "DELETE FROM anamnese WHERE patient_id = ? ;";
        String insuranceSQL = "DELETE FROM insurance WHERE patient_id = ? ;";

        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);

            try(PreparedStatement deletePatient = connection.prepareStatement(patientSQL);
                    PreparedStatement deleteContact = connection.prepareStatement(contactSQL);
                    PreparedStatement deleteAnamnese = connection.prepareStatement(anamneseSQL);
                    PreparedStatement deleteInsurance = connection.prepareStatement(insuranceSQL)){

                // Set the patient ID for all delete statements
                deletePatient.setInt(1,id);
                deleteContact.setInt(1,id);
                deleteAnamnese.setInt(1,id);
                deleteInsurance.setInt(1,id);

                // Execute delete statement
                deleteContact.executeUpdate();
                deleteAnamnese.executeUpdate();
                deleteInsurance.executeUpdate();
                deletePatient.executeUpdate();

                // Commit the transaction
                connection.commit();
            }catch (SQLException e){
                // Rollback the transaction in case of error
                connection.rollback();
                // Show error dialog
                Model.getInstance().getDialogController().showErrorDialog("Patient entfernen","Ein Fehler ist aufgetreten:\n\n"+e);
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            // Shows dialog window with error of failure while deleting patient from database
            Model.getInstance().getDialogController().showErrorDialog("Patient entfernen","Es ist ein Fehler aufgetreten:\n\n"+ e);
            throw new RuntimeException(e);
        }
    }



    // ------------------------------- Database utility methods -------------------------------

    /**
     * This Method extracts all data from a given resultSet into a list. It contains a Map with the column name as Key
     * and the object as key.
     *
     * @param resultSet resultSet with fetched data from the database.
     * @param results list to store the extracted data from the resultSet.
     * @throws SQLException Mentions an exception during the extraction process.
     */
    private static void extractResultSetData(ResultSet resultSet, List<Map<String, Object>> results) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while(resultSet.next()){
            Map<String, Object> row = new HashMap<>();
            for(int i= 1;i<= columnCount; i++){
                row.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            results.add(row);
        }
    }
}
