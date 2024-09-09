package com.sanusbrain.Controllers.Primary.Patient.History;

import com.sanusbrain.Models.Entities.History.HistoryEntry;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryListCellController implements Initializable {

    private final HistoryEntry historyEntry;

    @FXML
    private MFXCheckbox mfxCheckBox;

    @FXML
    private Label fxInitials;

    @FXML
    private Label fxCreationDate;

    @FXML
    private Label fxCategory;

    @FXML
    private Label fxTitle;

    @FXML
    private Label fxStatus;


    /**
     * Constructor for creating a HistoryListCellController, to fill with a history-entry object.
     *
     * @param historyEntry The patient associated with this cell.
     */
    public HistoryListCellController(HistoryEntry historyEntry){
        this.historyEntry = historyEntry;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     *
     * @param location  The location used to resolve relative paths for the root object, or null if the location is unknown.
     * @param resources The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Custom binding for status to handle boolean values and representation
        StringBinding statusBinding = Bindings.createStringBinding(
                () -> historyEntry.getStatus_Property().get() ? "Aktiv" : "Inaktiv", historyEntry.getStatus_Property()
        );

        fxInitials.setStyle("-fx-background-color: -fx-blue");
        fxInitials.textProperty().bind(historyEntry.getInitials_Property());
        fxCreationDate.textProperty().bind(Bindings.createStringBinding(historyEntry::getFormattedDate));
        fxCategory.textProperty().bind(historyEntry.getCategory_Property());
        fxTitle.textProperty().bind(historyEntry.getTitle_Property());
        fxStatus.textProperty().bind(statusBinding);
    }
}
