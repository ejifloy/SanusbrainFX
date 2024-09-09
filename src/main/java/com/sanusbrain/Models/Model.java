package com.sanusbrain.Models;

import com.sanusbrain.Utils.FieldMonitorUtil;
import com.sanusbrain.Controllers.DialogController;
import com.sanusbrain.Controllers.Primary.Patient.Base.PatientDataCollector;
import com.sanusbrain.Models.Entities.History.HistoryEntry;
import com.sanusbrain.Models.Entities.Patient.PatientEntry;
import com.sanusbrain.Models.Entities.Patient.PatientData;
import com.sanusbrain.Models.Service.HistoryService;
import com.sanusbrain.Models.Service.PatientService;
import com.sanusbrain.Views.ViewFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Using Singleton-Design-Pattern to create one instance of ViewFactory
 **/
public class Model {

    private static Model model;

    //Controllers
    private final ViewFactory viewFactory;
    private final Map<Class<?>, Object> controllers;
    private final DatabaseDriver databaseDriver;
    private DialogController dialogController;

    //Flags
    private boolean adminSuccessLoginFlag;
    private final BooleanProperty newPatientFlag;

    //Data
    private PatientEntry patientEntry;
    private PatientData patientData;
    private final PatientService patientService;
    private final HistoryService historyService;
    private final ObservableList<PatientEntry> patientEntries;
    private final ObservableList<HistoryEntry> historyEntries;
    private PatientDataCollector patientDataCollector;
    private FieldMonitorUtil fieldMonitorUtil;

    // Constructor
    private Model(){
        // Controller initialization...
        this.viewFactory = new ViewFactory();
        this.controllers = new HashMap<>();
        this.databaseDriver = new DatabaseDriver();

        // Flag initialization...
        this.adminSuccessLoginFlag = true; //TODO:Change here...
        this.newPatientFlag = new SimpleBooleanProperty(false);

        // Data & Lists/Collections
        this.patientService = new PatientService();
        this.historyService = new HistoryService();
        this.patientEntries = FXCollections.observableArrayList();
        this.historyEntries = FXCollections.observableArrayList();
        this.patientDataCollector = null;
        this.fieldMonitorUtil = new FieldMonitorUtil();
    }


    //Getter
    public static synchronized Model getInstance(){
        if(model == null){
            model = new Model();
        }
        return model;
    }

    @SuppressWarnings("unchecked")
    public <T> T getController(Class<T> controllerClass) {return (T) controllers.get(controllerClass);}

    public ViewFactory getViewFactory(){return viewFactory;}
    public DatabaseDriver getDatabaseDriver(){return  databaseDriver;}
    public DialogController getDialogController(){return dialogController;}

    public boolean getAdminSuccessLoginFlag() {return this.adminSuccessLoginFlag;}
    public BooleanProperty getNewPatientFlag() {return this.newPatientFlag;}

    public PatientService getPatientService() {return this. patientService;}
    public HistoryService getHistoryService() {return this. historyService;}
    public PatientEntry getSelectedPatientEntry() {return this.patientEntry;}
    public PatientData getSelectedPatientData() {return this.patientData;}
    public ObservableList<PatientEntry> getPatientEntries(){return patientEntries;}
    public ObservableList<HistoryEntry> getHistoryEntries(){return historyEntries;}
    public PatientDataCollector getPatientDataCollector() {return patientDataCollector;}
    public FieldMonitorUtil getFieldMonitorUtil() { return  fieldMonitorUtil;}

    //Setter
    public <T> void setControllers(Class<T> controllerClass, T controller) {controllers.put(controllerClass, controller);}
    public void setDialogController(Stage stage, Pane ownerNode) {this.dialogController = new DialogController(stage, ownerNode);}
    public void setAdminSuccessLoginFlag(boolean flag){this.adminSuccessLoginFlag = flag;}
    public void setNewPatientFlag(boolean flag){this.newPatientFlag.set(flag);}
    public void setPatientDataCollector() {this.patientDataCollector = new PatientDataCollector();}
    public void setSelectedPatientEntry(PatientEntry patientEntry) {this.patientEntry = patientEntry;}
    public void setSelectedPatientData(PatientData patientData) {this.patientData = patientData;}


    /**
     *This methode evaluates the login data to grant access to the dashboard.
     *
     * @param username Entered username.
     * @param password Entered password.
     */
    public void evaluateAdminCredentials(String username, String password){
        List<Map<String, Object>> results = databaseDriver.getLoginData(username,password);
        if(!results.isEmpty())
            this.adminSuccessLoginFlag = true; //TODO: Add username/password check individually
    }


    /**
     * This method loads all patients from the database and adds them to the patients list.
     */
    public void loadPatients(){
        patientEntries.clear();
        patientEntries.addAll(patientService.getAllPatients());
    }

    /**
     * This method loads all histories from the database and adds them to the history list.
     */
    public void loadHistoryEntries(int patient_id){
        historyEntries.clear();
        historyEntries.addAll(historyService.getAllHistoryEntries(patient_id));
    }

}