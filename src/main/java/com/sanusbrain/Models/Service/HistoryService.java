package com.sanusbrain.Models.Service;

import com.sanusbrain.Models.DAO.HistoryDAO;
import com.sanusbrain.Models.Entities.History.HistoryEntry;

import java.util.List;

public class HistoryService {

    private final HistoryDAO historyDAO = new HistoryDAO();

    public List<HistoryEntry> getAllHistoryEntries(int patient_id){
        return historyDAO.getAllHistoryEntries(patient_id);
    }

    /*public List<HistoryData> getHistoryDataById(int patientId) {
        return historyDAO.getAllHistoryData();
    }*/
}
