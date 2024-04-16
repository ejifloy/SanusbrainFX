package com.sanusbrain.Views;

import com.sanusbrain.App;
import com.sanusbrain.Utils.ResizeHelper;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;


public class ViewFactory {

    /*
    * Admin Views
    * */

    //Variable for switching between different views using Enum-Class AdminViewOptions
    private final ObjectProperty<AdminViewOptions> adminSelectedViewItem;
    private BorderPane dashboardView;
    private AnchorPane settingsView;
    private AnchorPane patientsView;


    /*
     * TODO: Manager Views...
     * */


    /*
    * Admin-View Section
    * */
    public ViewFactory(){
        this.adminSelectedViewItem = new SimpleObjectProperty<>();
    }

    public ObjectProperty<AdminViewOptions> getAdminSelectedViewItem(){
        return adminSelectedViewItem;
    }


    public BorderPane getDashboardView(){
        if(dashboardView == null) {
            try {
                dashboardView = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return dashboardView;
    }

    public AnchorPane getSettingsView(){
        if(settingsView == null) {
            try {
                settingsView = new FXMLLoader(getClass().getResource("/fxml/settings.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return settingsView;
    }

    public AnchorPane getPatientsView(){
        if(patientsView == null){
            try {
                patientsView = new FXMLLoader(getClass().getResource("/fxml/patients.fxml")).load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return patientsView;
    }

    public void showLoginWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        createStage(loader);
    }

    public void showPrimaryWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary.fxml"));
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
        stage.getIcons().add(new Image(Objects.requireNonNull(App.class.getResourceAsStream("/images/SANUSBRAIN-ICON.png"))));
        stage.setTitle("SANUSBRAIN");
        stage.setResizable(true);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        ResizeHelper.addResizeListener(stage);
        //stage.setOnCloseRequest(event -> MongoDBServerHandler.closeServer());
        stage.show();
    }

    public void closeWindow(Stage stage){
        stage.close();
    }
    public void minimizeWindow(Stage stage) {
        stage.setIconified(true);
    }
    public void maximizeWindow(Stage stage) {
        stage.setMaximized(!stage.isMaximized());
    }
}
