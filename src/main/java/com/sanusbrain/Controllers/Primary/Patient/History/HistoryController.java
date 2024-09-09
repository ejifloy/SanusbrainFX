package com.sanusbrain.Controllers.Primary.Patient.History;

import com.sanusbrain.Models.Entities.History.HistoryEntry;
import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.HistoryCellFactory;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoryController implements Initializable {

    @FXML
    private MFXTextField mfxSearch;

    @FXML
    private ListView<HistoryEntry> historyListView;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     *
     * @param url  The location used to resolve relative paths for the root object, or null if the location is unknown.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    /**
     * Loads History-Entries associated with the selected Patient and
     * then fills them into the ListView within the History-Section.
     *
     * @param patient_id ID of the selected Patient.
     */
    public void loadHistoryEntries(int patient_id) {
        // Loads History-Entries associated with the selected Patient.
        Model.getInstance().loadHistoryEntries(patient_id);

        // Fill History-Entries into the ListView
        historyListView.setItems(Model.getInstance().getHistoryEntries());

        // Loads HistoryCellFactory for each History-Entry
        historyListView.setCellFactory(e -> new HistoryCellFactory());

    }
}
