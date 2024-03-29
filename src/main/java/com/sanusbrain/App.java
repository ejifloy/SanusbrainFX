package com.sanusbrain;

import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.ViewFactory;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Set MaterialFX Styles to enable Style on whole App
        UserAgentBuilder.builder()
                .themes(MaterialFXStylesheets.DEFAULT)
                .setDeploy(true)
                .setResolveAssets(true)
                .build()
                .setGlobal();

        //Use ViewFactory through Model-Singleton-Object and call showLoginWindow-Methode to show Login
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}