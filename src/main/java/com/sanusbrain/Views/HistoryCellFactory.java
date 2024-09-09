package com.sanusbrain.Views;

import com.sanusbrain.Controllers.Primary.Patient.History.HistoryListCellController;
import com.sanusbrain.Models.Entities.History.HistoryEntry;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

import java.io.IOException;

/*
 * HistoryCellFactory handles every history-entry (object) for a Patient. Loading the
 * ListCell-FXML and passing each History-Object to the HistoryListCellController,
 * to set the information of the history within the Controllers FXML-Elements.
 * */
public class HistoryCellFactory extends ListCell<HistoryEntry> {
    //TODO Utility-Class establishing?
    @Override
    protected void updateItem(HistoryEntry historyEntry, boolean empty){
        super.updateItem(historyEntry,empty);

        if(empty || historyEntry == null){
            setText(null);
            setGraphic(null);
        }else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/primary/patient/history/historyListCell.fxml"));
            HistoryListCellController controller = new HistoryListCellController(historyEntry);
            loader.setController(controller);
            setText(null);

            try {
                setGraphic(loader.load());
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
    }
}
