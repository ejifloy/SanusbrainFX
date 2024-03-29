package com.sanusbrain.Views;

import com.sanusbrain.App;
import com.sanusbrain.Controllers.PrimaryController;
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

public class ViewFactory {

    private double x,y= 0;
    /*
    * @param dashboardView = used to load the dashboard fxml
    * */
    private AnchorPane dashboardView;

    public ViewFactory(){}

    public AnchorPane getDashboardView() throws IOException{
        if(dashboardView == null)
            dashboardView = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml")).load();

        return dashboardView;
    }


    public void showLoginWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        createStage(loader);
    }

    public void showDashboardWindow() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary.fxml"));
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
        //stage.setOnCloseRequest(event -> MongoDBServerHandler.closeServer());
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
