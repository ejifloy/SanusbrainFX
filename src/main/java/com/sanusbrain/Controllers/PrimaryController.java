package com.sanusbrain.Controllers;

import com.sanusbrain.Utils.DialogControllerUtil;
import com.sanusbrain.Utils.WindowUtil;
import com.sanusbrain.Models.Model;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    //Press and Drag-Coordinate Variables
    private double x,y=0;

    @FXML
    private AnchorPane parent;

    @FXML
    private BorderPane primaryBorderPane;

    @FXML
    private HBox fxTopBar;

    @FXML
    private Button fxMaximizeButton;

    @FXML
    private MFXFontIcon mfxModeIcon;

    @FXML
    private MFXFontIcon mfxWindowIcon;


    private WindowUtil windowUtil;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Sets up the dialog controller for the parent component
        DialogControllerUtil.setupDialogController(parent);

        //Adding a Listener to SimpleStringProperty that handles Navigation-Events to switch Views
        Model.getInstance().getViewFactory().getAdminSelectedViewItem().addListener((observable, oldValue, newValue) -> {
            switch (newValue){
                case DASHBOARD-> primaryBorderPane.setCenter(Model.getInstance().getViewFactory().getHomeView());
                case PATIENTS -> primaryBorderPane.setCenter(Model.getInstance().getViewFactory().getPatientListView());
                case SETTINGS -> primaryBorderPane.setCenter(Model.getInstance().getViewFactory().getSettingsView());
                case PATIENT -> primaryBorderPane.setCenter(Model.getInstance().getViewFactory().getPatientView());
                default -> primaryBorderPane.setCenter(Model.getInstance().getViewFactory().getHomeView());
            }
        });
    }


    /*******************************************************************************
     * Event to switch theme, min-/maximize,close and drag Window...               *
     *******************************************************************************/
    @FXML
    private void switchMode() {
        if (mfxModeIcon.getDescription().equals("fas-sun")) {
            // light-Mode
            parent.getStylesheets().remove(getClass().getResource("/css/theme/dark.css").toString());
            parent.getStylesheets().add(getClass().getResource("/css/theme/light.css").toString());
        } else {
            // dark-Mode
            parent.getStylesheets().remove(getClass().getResource("/css/theme/light.css").toString());
            parent.getStylesheets().add(getClass().getResource("/css/theme/dark.css").toString());
        }
    }

    // Events to max-/minimize or close the current Window through custom top-bar
    @FXML
    private void minimizeWindow(ActionEvent actionEvent) {
        Model.getInstance().getViewFactory().minimizeWindow(((Stage) fxTopBar.getScene().getWindow()));
    }
    @FXML
    private void maximizeWindow(ActionEvent actionEvent) {
        if(windowUtil==null)
            setWindowUtilUp();

        windowUtil.toggleMaximize();
        mfxWindowIcon.setDescription((mfxWindowIcon.getDescription().equals("fas-window-maximize")) ? "fas-window-restore":"fas-window-maximize");
    }
    @FXML
    private void closeWindow(ActionEvent actionEvent) {
        Model.getInstance().getViewFactory().closeWindow(((Stage) fxTopBar.getScene().getWindow()));
    }

    //Press and Drag-Event for Custom Top-Bar
    @FXML
    public void onDragTopBar(MouseEvent mouseEvent) {
        Stage stage = ((Stage) fxTopBar.getScene().getWindow());
        stage.setX(mouseEvent.getScreenX() - x);
        stage.setY(mouseEvent.getScreenY() - y);

    }
    @FXML
    public void onPressedTopBar(MouseEvent mouseEvent) {
        x = mouseEvent.getX();
        y = mouseEvent.getY();
    }

    /**
     * Initializes resizeUtil to enable maximize feature.
     */
    public void setWindowUtilUp(){
        this.windowUtil = new WindowUtil(((Stage) parent.getScene().getWindow()));
    }
}
