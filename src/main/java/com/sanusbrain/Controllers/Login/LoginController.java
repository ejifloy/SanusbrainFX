package com.sanusbrain.Controllers.Login;

import com.sanusbrain.Models.Model;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;

import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * Controller for the login view.
 */
public class LoginController implements Initializable {
    private double x, y = 0;


    @FXML
    private AnchorPane parent;
    @FXML
    private HBox fxTopBar;
    @FXML
    private MFXTextField fxUsernameTextField;
    @FXML
    private MFXPasswordField fxPasswordField;
    @FXML
    private Label userValidationLabel, passwordValidationLabel;
    @FXML
    private MFXFontIcon mfxModeIcon;


    /**
     * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    // Checks whether the username and password are correct. If not, shows Validation-Warning.
    // If the Input is correct and exists, the Dashboard-FXML will be loaded.
    @FXML
    private void loginEvent(ActionEvent actionEvent) throws IOException {
        //Evaluate Admin Login Credentials
        Model.getInstance().evaluateAdminCredentials(fxUsernameTextField.getText(), fxPasswordField.getText());

        if(Model.getInstance().getAdminSuccessLoginFlag()){
            Model.getInstance().getViewFactory().closeWindow(((Stage) fxUsernameTextField.getScene().getWindow()));
            Model.getInstance().getViewFactory().showPrimaryWindow();
        }else{
            //TODO: Show Login Error...
            userValidationLabel.setVisible(true);
            passwordValidationLabel.setVisible(true);
        }

    }

    // Methode used to add Validation-Feature to JFXTextField,-PasswordField and -TextArea
    // Receives a List of nodes and iterates through it. Checks each node by "validate()".
    //private boolean checkValidation(List<Node> nodeArrayList) {
    //    boolean flag = true;
    //    boolean check = true;

    //    try{
    //        for(Node node:nodeArrayList){
    //            switch (node.getTypeSelector()) {
    //                case "MFXTextField" -> flag=((MFXTextField)node).validate();
    //                case "MFXPasswordField" -> flag=((MFXPasswordField) node).validate();
    //            }

    //            if(check&&!flag)
    //                check = false;
    //        }
    //    }catch (ClassCastException e){
    //        System.out.println("ClassCastException - addValidation:\n"+e);
    //    }

    //    return check;
    //}
    // Method used to change the skin by clicking on the Skin-Icon "light".
    // Changing the skin by removing current stylesheet and replacing with corresponding stylesheet.
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

    // Event to minimize the Login-Window through the minimize button in the custom top-bar
    @FXML
    private void minimizeWindow(ActionEvent actionEvent) {
        Stage stage = (Stage) fxPasswordField.getScene().getWindow();
        stage.setIconified(true);
    }

    // Event to close the Login-Window through the close button in the custom top-bar
    @FXML
    private void closeWindow(ActionEvent actionEvent) {
        //TODO: saveLoginSettings();
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

