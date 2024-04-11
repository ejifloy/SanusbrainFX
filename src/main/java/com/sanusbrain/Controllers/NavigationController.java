package com.sanusbrain.Controllers;

import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.AdminViewOptions;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class NavigationController {


    @FXML
    private VBox navBar;

    @FXML
    void onHomeEvent(ActionEvent event) {
        Model.getInstance().getViewFactory().getAdminSelectedViewItem().set(AdminViewOptions.DASHBOARD);
    }

    @FXML
    void onPatientsEvent(ActionEvent event) {
        Model.getInstance().getViewFactory().getAdminSelectedViewItem().set(AdminViewOptions.PATIENTS);
    }

    @FXML
    void onSettingsEvent(ActionEvent event) {
        Model.getInstance().getViewFactory().getAdminSelectedViewItem().set(AdminViewOptions.SETTINGS);
    }

    @FXML
    void onLogoutEvent(ActionEvent event) throws IOException {
        Model.getInstance().getViewFactory().closeWindow(((Stage) navBar.getScene().getWindow()));
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    @FXML
    void onExitEvent(ActionEvent event) {
        //TODO: save Potential Data...
        System.exit(0);
    }
}
