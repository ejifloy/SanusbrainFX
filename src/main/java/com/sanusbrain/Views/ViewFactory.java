package com.sanusbrain.Views;

import com.sanusbrain.App;
import com.sanusbrain.Utils.ResizeHelper;
import com.sanusbrain.Controllers.Primary.Home.HomeController;
import com.sanusbrain.Controllers.Primary.Patient.Base.*;
import com.sanusbrain.Controllers.Primary.Patient.History.HistoryController;
import com.sanusbrain.Controllers.Primary.Patient.Overview.OverViewController;
import com.sanusbrain.Controllers.Primary.Patient.PatientController;
import com.sanusbrain.Controllers.Primary.Patient.PatientListController;
import com.sanusbrain.Controllers.Primary.Patient.PatientOverviewController;
import com.sanusbrain.Controllers.Primary.Settings.SettingsController;
import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.Enums.FXML.AdminViewOptions;
import com.sanusbrain.Views.Enums.FXML.BaseDataViewOptions;
import com.sanusbrain.Views.Enums.FXML.PatientViewOptions;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


/**
 * Factory class for loading and managing views and their controllers.
 * This class preloads all views and controllers during initialization.
 */
public class ViewFactory {
    
    // Variables for switching between different views using Enum-Class AdminViewOptions
    private final ObjectProperty<AdminViewOptions> adminSelectedViewItem;
    private final ObjectProperty<BaseDataViewOptions> baseDataSelectedViewItem;
    private final ObjectProperty<PatientViewOptions> patientSelectedViewItem;

    // View variables for setting content within FXML-files (fx:include)
    private AnchorPane homeView, patientListView, settingsView, overviewView,
            patientView, historyView, baseView, patientOverview, menubarView, personalDataView,
            baseDataNavigationView, contactDataView, anamneseDataView, insuranceDataView;


    // Constructor
    public ViewFactory(){
        this.patientSelectedViewItem = new SimpleObjectProperty<>();
        this.baseDataSelectedViewItem = new SimpleObjectProperty<>();
        this.adminSelectedViewItem = new SimpleObjectProperty<>();
    }

    // Enum-Getter for switching views through navigation
    public ObjectProperty<AdminViewOptions> getAdminSelectedViewItem(){
        return adminSelectedViewItem;
    }
    public ObjectProperty<BaseDataViewOptions> getBaseDataSelectedViewItem(){
        return baseDataSelectedViewItem;
    }
    public ObjectProperty<PatientViewOptions> getPatientSelectedViewItem(){
        return patientSelectedViewItem;
    }

    /**
     * Loads all views and their respective controllers.
     */
    public void preloadAllViews(){
        /*
        * Dashboard-Section
        * */
        homeView = loadView("/fxml/primary/home/home.fxml", HomeController.class);
        patientListView = loadView("/fxml/primary/patient/patientList.fxml", PatientListController.class);
        settingsView = loadView("/fxml/primary/settings/settings.fxml", SettingsController.class);

        /*
        * -> Patient-Section
        * */
        patientOverview = loadView("/fxml/primary/patient/patientOverview.fxml", PatientOverviewController.class);
        patientView = loadView("/fxml/primary/patient/patient.fxml", PatientController.class);

        //  -> Overview
        overviewView = loadView("/fxml/primary/patient/overview/overview.fxml", OverViewController.class);

        //  -> Base
        baseView = loadView("/fxml/primary/patient/base/base.fxml", BaseController.class);
        menubarView = loadView("/fxml/primary/patient/base/menu.fxml",MenuBarController.class);
        baseDataNavigationView = loadView("/fxml/primary/patient/base/navigation.fxml", BaseDataNavigationController.class);
        personalDataView = loadView("/fxml/primary/patient/base/personal.fxml", PersonalDataController.class);
        contactDataView = loadView("/fxml/primary/patient/base/contact.fxml", ContactDataController.class);
        anamneseDataView = loadView("/fxml/primary/patient/base/anamnese.fxml", AnamneseDataController.class);
        insuranceDataView = loadView("/fxml/primary/patient/base/insurance.fxml", InsuranceDataController.class);

        //  -> History
        historyView = loadView("/fxml/primary/patient/history/history.fxml", HistoryController.class);
    }

    /**
     * Loads a specific FXML view and sets its controller in the model.
     * <p>
     * @param <T>  The type of the controller.
     * @param fxmlPath The path to the FXML file.
     * @param controllerClass The class of the controller.
     * @return The loaded AnchorPane.
     */
    private <T> AnchorPane loadView(String fxmlPath, Class<T> controllerClass){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            AnchorPane view = loader.load();
            T controller = loader.getController();
            System.out.println("loadView: "+view+" - "+controller);
            Model.getInstance().setControllers(controllerClass, controller);
            return view;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Getter methods
    public AnchorPane getHomeView(){return homeView;}
    public AnchorPane getPatientListView(){return patientListView;}
    public AnchorPane getSettingsView(){return settingsView;}
    public AnchorPane getPatientOverview(){return patientOverview;}
    public AnchorPane getPatientView(){return patientView;}
    public AnchorPane getOverviewView(){return overviewView;}
    public AnchorPane getBaseView() {return baseView;}
    public AnchorPane getMenuBarView() {return menubarView;}
    public AnchorPane getBaseDataNavigationView(){return  baseDataNavigationView;}
    public AnchorPane getPersonalDataView(){return  personalDataView;}
    public AnchorPane getContactDataView(){return  contactDataView;}
    public AnchorPane getAnamneseDataView(){return  anamneseDataView;}
    public AnchorPane getInsuranceDataView(){return  insuranceDataView;}
    public AnchorPane getHistoryView(){return historyView;}


    // Changes Scene Views
    public void showLoginWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login/login.fxml"));
        createStage(loader);
    }

    public void showPrimaryWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/primary.fxml"));

        /*TODO: Keep in mind for later...
        * PrimaryController primaryController = new PrimaryController();
        * loader.setController(primaryController);
        * */
        createStage(loader);
    }

    private static void createStage(FXMLLoader loader) throws IOException {
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();

        scene.setFill(Color.TRANSPARENT);
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/css/images/SANUSBRAIN_LOGO_light.png"))));
        stage.setTitle("SANUSBRAIN");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        ResizeHelper.addResizeListener(stage);
        //stage.setOnCloseRequest(event -> MongoDBServerHandler.closeServer());
        stage.show();
    }

    // Top-Bar methods
    //TODO: Add to WindowUtil
    public void closeWindow(Stage stage){
        stage.close();
    }
    public void minimizeWindow(Stage stage) {
        stage.setIconified(true);
    }
}