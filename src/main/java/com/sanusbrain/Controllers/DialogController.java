package com.sanusbrain.Controllers;

import com.sanusbrain.Views.Enums.FXML.DialogType;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialog;
import io.github.palexdev.materialfx.dialogs.MFXGenericDialogBuilder;
import io.github.palexdev.materialfx.dialogs.MFXStageDialog;
import io.github.palexdev.materialfx.enums.ScrimPriority;
import io.github.palexdev.mfxresources.fonts.MFXFontIcon;
import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Map;


public class DialogController{


    private MFXGenericDialog dialogContent;
    private MFXStageDialog dialog;

    private MFXButton confirmButton;
    private MFXButton cancelButton;

    /**
     * Initializes the dialog window and sets default properties.
     *
     * @param stage     The stage where the dialog will be displayed
     * @param ownerNode The owner node of the dialog
     */
    public DialogController(Stage stage, Pane ownerNode) {
        initializeDialogWindow(stage, ownerNode);
    }

    private void initializeDialogWindow(Stage stage, Pane ownerNode) {
        Platform.runLater(() -> {
            this.dialogContent = MFXGenericDialogBuilder.build()
                    .makeScrollable(true)
                    .setShowMinimize(false)
                    .setShowAlwaysOnTop(false)
                    .get();
            this.dialog = MFXGenericDialogBuilder.build(dialogContent)
                    .toStageDialogBuilder()
                    .initOwner(stage)
                    .initModality(Modality.APPLICATION_MODAL)
                    .setDraggable(true)
                    .setOwnerNode(ownerNode)
                    .setScrimPriority(ScrimPriority.WINDOW)
                    .setScrimOwner(true)
                    .get();

            dialogContent.setMaxSize(400, 200);

            confirmButton = new MFXButton("OK");
            confirmButton.setOnAction(actionEvent -> dialog.close());

            cancelButton = new MFXButton("Abbrechen");
            cancelButton.setOnAction(actionEvent -> dialog.close());

            dialogContent.addActions(cancelButton);
        });
    }

    /**
     * Shows an information dialog.
     *
     * @param header  The header text of the dialog.
     * @param content The content text of the dialog.
     */
    public void showInfoDialog(String header, String content) {
        setupDialogContent(DialogType.INFO, header, content, confirmButton);
        Platform.runLater(() -> dialog.showDialog());
    }

    /**
     * Shows a success dialog.
     *
     * @param header  The header text of the dialog.
     * @param content The content text of the dialog.
     */
    public void showSuccessDialog(String header, String content) {
        setupDialogContent(DialogType.SUCCESS, header, content, confirmButton);
        Platform.runLater(() -> dialog.showDialog());
    }

    /**
     * Shows a warning dialog with a confirmation callback.
     *
     * @param header     The header text of the dialog.
     * @param content    The content text of the dialog.
     * @param onConfirm  The runnable to execute when the confirm button is clicked.
     */
    public void showWarningDialog(String header, String content, Runnable onConfirm) {
        setupDialogContent(DialogType.WARNING, header, content, cancelButton);

        dialogContent.clearActions();
        dialogContent.addActions(
                Map.entry(setupMFXButton(onConfirm), mouseEvent -> {}),
                Map.entry(cancelButton, mouseEvent -> dialog.close())
        );
        Platform.runLater(() -> dialog.showDialog());
    }

    /**
     * Shows an error dialog.
     *
     * @param header  The header text of the dialog.
     * @param content The content text of the dialog.
     */
    public void showErrorDialog(String header, String content) {
        setupDialogContent(DialogType.ERROR, header, content, confirmButton);
        Platform.runLater(() -> dialog.showDialog());
    }

    /**
     * This method sets the content and style for the dialog window.
     *
     * @param type          The dialog icon- and style-description.
     * @param header        The dialog header.
     * @param content       The dialog content message.
     */
    private void setupDialogContent(DialogType type, String header, String content, MFXButton button) {
        MFXFontIcon infoIcon = new MFXFontIcon(type.getIcon(), 18);
        dialogContent.setHeaderIcon(infoIcon);
        dialogContent.setHeaderText(header);
        dialogContent.setContentText(content);
        convertDialogTo(type.getStyleClass());

        // Clear existing actions and re-add the cancel button
        dialogContent.clearActions();
        dialogContent.addActions(button);
    }

    /**
     * Creates a new MFXButton with specific Event.
     *
     * @param onConfirm Custom Event when pressed.
     * @return Returns the MFXButton.
     */
    private MFXButton setupMFXButton(Runnable onConfirm){
        MFXButton setupButton =new MFXButton("Bestätigen");
        setupButton.setOnAction(actionEvent -> {
            onConfirm.run();
            dialog.close();
        });

        return setupButton;
    }

    private void convertDialogTo(String styleClass) {
        dialogContent.getStyleClass().removeIf(
                s -> s.equals("mfx-info-dialog") || s.equals("mfx-warn-dialog") || s.equals("mfx-error-dialog")
        );

        if (styleClass != null)
            dialogContent.getStyleClass().add(styleClass);
    }
}
