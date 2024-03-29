package com.sanusbrain.Controllers;

import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
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
    private HBox fxTopBar;

    @FXML
    private MFXFontIcon mfxModeIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void switchMode() {
        if (mfxModeIcon.getDescription().equals("fas-sun")) {
            // light-Mode
            parent.getStylesheets().remove(getClass().getResource("/css/dark.css").toString());
            parent.getStylesheets().add(getClass().getResource("/css/light.css").toString());
        } else {
            // dark-Mode
            parent.getStylesheets().remove(getClass().getResource("/css/light.css").toString());
            parent.getStylesheets().add(getClass().getResource("/css/dark.css").toString());
        }
    }

    // Event to minimize the Login-Window through the minimize button in the custom top-bar
    @FXML
    private void minimizeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) fxTopBar.getScene().getWindow();
        stage.setIconified(true);
    }

    // Event to close the Login-Window through the close button in the custom top-bar
    @FXML
    private void closeWindow(ActionEvent actionEvent) {
        //saveLoginSettings();
        System.exit(0);
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
}
