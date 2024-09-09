package com.sanusbrain.Utils;

import com.sanusbrain.Models.Model;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Utility class for setting up dialog controllers.
 */
public class DialogControllerUtil {

    // Key for the custom property to track if the listener has been added
    private static final String LISTENER_ADDED_PROPERTY = "listenerAdded";

    /**
     * Utility Method
     * <p>
     * Sets up the dialog controller by adding listeners to the parent component's scene and window properties.
     * This method ensures that the setup is only done once for the given parent.
     *
     * @param parent The parent Pane to which the listeners are added.
     */
    public static void setupDialogController(Pane parent) {
        // Retrieve the custom property to check if the listener has been added
        BooleanProperty listenerAdded = (BooleanProperty) parent.getProperties().get(LISTENER_ADDED_PROPERTY);
        if (listenerAdded == null) {
            listenerAdded = new SimpleBooleanProperty(false);
            parent.getProperties().put(LISTENER_ADDED_PROPERTY, listenerAdded);
        }

        // If the listener has not been added yet, add it and mark the property as true
        if (!listenerAdded.get()) {
            parent.sceneProperty().addListener((observableValue, oldScene, newScene) -> {
                if (newScene != null) {
                    newScene.windowProperty().addListener((obs, oldWindow, newWindow) -> {
                        if (newWindow != null)
                            Model.getInstance().setDialogController((Stage) newWindow, parent);
                    });
                }
            });
            listenerAdded.set(true);
        }
    }
}
