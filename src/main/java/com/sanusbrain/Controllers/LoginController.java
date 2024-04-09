package com.sanusbrain.Controllers;

import com.sanusbrain.Models.Model;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.validation.Constraint;
import io.github.palexdev.materialfx.validation.Severity;

import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanExpression;
import javafx.css.PseudoClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private double x, y = 0;
    private static final PseudoClass INVALID_PSEUDO_CLASS = PseudoClass.getPseudoClass("invalid");


    @FXML
    private HBox fxTopBar;
    @FXML
    private VBox parent;
    @FXML
    private MFXTextField fxUsernameTextField;
    @FXML
    private MFXPasswordField fxPasswordField;
    @FXML
    private Label userValidationLabel, passwordValidationLabel;
    @FXML
    private MFXFontIcon mfxModeIcon;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Apply Validator Feature to User-/PasswordTextField
        // applyValidation(fxUsernameTextField,"Benutzername ungültig...                  ", "admin", userValidationLabel);
        // applyValidation(fxPasswordField,"Passwort ist ungültig...                  ", "pw123456", passwordValidationLabel);
    }


    // Checks whether the username and password are correct. If not, shows Validation-Warning.
    // If the Input is correct and exists, the Dashboard-FXML will be loaded.
    @FXML
    private void loginEvent(ActionEvent actionEvent) throws IOException {
        //TODO: Check Login Data
        Model.getInstance().getViewFactory().closeWindow(((Stage) fxUsernameTextField.getScene().getWindow()));
        Model.getInstance().getViewFactory().showDashboardWindow();
    }


    private void applyValidation(Object field, String errorMessage, String compareString, Label validationLabel) {
        // Create a Constraint
        buildCondition(field, compareString, errorMessage);
        // Add a Listener
        addListener(field, errorMessage, validationLabel);
    }

    private void buildCondition(Object field, String compareString, String errorMessage) {
        Constraint constraint;

        if (field instanceof MFXPasswordField) {
            MFXPasswordField passwordField = (MFXPasswordField) field;

            // Add Constraint to Validator & apply on PasswordField
            constraint = createConstraint(errorMessage, (Bindings.createBooleanBinding(
                    () -> passwordField.getText().equals(compareString),
                    passwordField.textProperty()
            )));
            passwordField.getValidator().constraint(constraint);

        } else if (field instanceof MFXTextField) {
            MFXTextField textField = ((MFXTextField) field);

            // Add Constraint to Validator & apply on TextField
            constraint = createConstraint(errorMessage, textField.textProperty().isEqualTo(compareString));
            textField.getValidator().constraint(constraint);
        }
    }

    //Methode used to create a constraint with specific errorMessage and condition
    private static Constraint createConstraint(String errorMessage, BooleanExpression condition) {
        return Constraint.Builder.build()
                .setSeverity(Severity.ERROR)
                .setMessage(errorMessage)
                .setCondition(condition)
                .get();
    }

    //Methode used to apply Listener on field-Object with specific errorMessage on corresponding validationLabel
    private void addListener(Object field, String errorMessage, Label validationLabel) {
        if (field instanceof MFXPasswordField) {
            MFXPasswordField passwordField = (MFXPasswordField) field;

            passwordField.getValidator().validProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    validationLabel.setVisible(false);
                    passwordField.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, false);
                }
            });

            passwordField.delegateFocusedProperty().addListener((observable, oldValue, newValue) -> {
                if (oldValue && !newValue) {
                    passwordField.validate();
                    passwordField.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, true);
                    validationLabel.setText(errorMessage);
                    validationLabel.setVisible(true);

                }
            });
        } else if (field instanceof MFXTextField) {
            MFXTextField textField = ((MFXTextField) field);

            textField.getValidator().validProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue) {
                    validationLabel.setVisible(false);
                    textField.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, false);
                }
            });

            //Add Listener to TextField
            textField.delegateFocusedProperty().addListener((observable, oldValue, newValue) -> {
                if (oldValue && !newValue) {
                    textField.validate();
                    textField.pseudoClassStateChanged(INVALID_PSEUDO_CLASS, true);
                    validationLabel.setText(errorMessage);
                    validationLabel.setVisible(true);

                }
            });

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














































  /*  @FXML
    private void setCheck(){
    }

    @FXML
    protected void setText() {

//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/userdb","root","pw123456");
//
//            Statement statement = connection.createStatement();
//            String sql = "SELECT * FROM userdb.users;";
//            ResultSet resultSet = statement.executeQuery(sql);
//
//            while(resultSet.next()){
//                fxTextArea.appendText(resultSet.getString("user_name")
//                        +"\t"+resultSet.getString("user_password")
//                        +"\t"+resultSet.getBoolean("skin")
//                        +"\t"+resultSet.getBoolean("remember")+"\n");
//            }
//
//            connection.close();
//        }catch (Exception e){
//            System.out.println("Error: "+e);
//        }
    }*/
