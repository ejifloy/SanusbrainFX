package com.sanusbrain;

import com.sanusbrain.Utils.AsyncTaskRunner;
import com.sanusbrain.Models.Model;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

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

        // Preload Model instance
        Model.getInstance();
        // Start preloading views in a separate thread
        AsyncTaskRunner.runAsync(()->{
            Model.getInstance().getViewFactory().preloadAllViews();
            return null;
        });
        //Use ViewFactory through Model-Singleton-Object and call showLoginWindow-Methode to show Login
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) {
        launch();
    }
}