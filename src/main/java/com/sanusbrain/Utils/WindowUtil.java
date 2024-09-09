package com.sanusbrain.Utils;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Utility Class for managing window resizing, maximizing, and restoring.
 * This class handles the logic for maximizing a window to fit the screen,
 * including handling multiple monitors.
 */
public class WindowUtil {
    private final Stage stage;
    private double previousX, previousY, previousWidth, previousHeight;

    /**
     * Constructor for WindowUtil
     *
     * @param stage The Stage object to manage maximizing for.
     */
    public WindowUtil(Stage stage){
        this.stage = stage;
        configureStage();
    }

    /**
     * Configures the stage properties and add a listener.
     */
    private void configureStage() {
        stage.maximizedProperty().addListener((observableValue, wasMaximized, isNowMaximized) -> {
            if(isNowMaximized){
                maximize();
            }else
                unmaximize();
        });
    }

    /**
     * Maximizes the window to fit the current screen's visual bounds.
     */
    private void maximize() {
        Screen screen = getScreenForStage(stage);
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
    }

    /**
     * Restores the window to fit the current screen's visual bounds.
     */
    private void unmaximize() {
        // Set stage to previous size and position
        stage.setX(previousX);
        stage.setY(previousY);
        stage.setWidth(previousWidth);
        stage.setHeight(previousHeight);
    }

    /**
     * Gets the screen on which the stage is currently located.
     *
     * @param stage The Stage object.
     * @return      The Screen object where the stage is located.
     */
    private Screen getScreenForStage(Stage stage){
        double stageCenterX = stage.getX() + stage.getWidth() / 2;
        double stageCenterY = stage.getY() + stage.getHeight() / 2;

        return Screen.getScreensForRectangle(stageCenterX, stageCenterY, 1, 1).get(0);
    }

    /**
     * Saves the current bounds of the window before maximizing.
     */
    public void savePreviousBounds(){
        previousX = stage.getX();
        previousY = stage.getY();
        previousWidth= stage.getWidth();
        previousHeight = stage.getHeight();
    }

    /**
     * Toggles between maximized and restored state of the window.
     */
    public void toggleMaximize(){
        if(stage.isMaximized()){
            stage.setMaximized(false);
        }else{
            savePreviousBounds();
            stage.setMaximized(true);
        }
    }
}
