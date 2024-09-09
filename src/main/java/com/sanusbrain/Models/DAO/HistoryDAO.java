package com.sanusbrain.Models.DAO;

import com.sanusbrain.Models.Entities.History.HistoryData;
import com.sanusbrain.Models.Entities.History.HistoryEntry;
import com.sanusbrain.Models.Model;
import com.sanusbrain.Views.Enums.Database.HistoryKeys;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HistoryDAO {

    // Fetch history-data to fill the ListView
    public List<HistoryEntry> getAllHistoryEntries(int patient_id) {
        List<HistoryEntry> historyEntryList = new ArrayList<>();
        //TODO: Database Driver anpassung der getPatientSectionDataById, da hier ähnlichkeiten bestehen
        List<Map<String, Object>> results = Model.getInstance().getDatabaseDriver().getPatientSectionDataById("history",patient_id);

        for(Map<String, Object> row:results){
            String initials = (String) row.get(HistoryKeys.FXINITIALS.getLabel());
            LocalDate date = Optional.ofNullable((Date) row.get(HistoryKeys.FXCREATIONDATE.getLabel()))
                    .map(Date::toLocalDate)
                    .orElse(null);
            String author = (String) row.get(HistoryKeys.FXAUTHOR.getLabel());
            String category = (String) row.get(HistoryKeys.FXCATEGORY.getLabel());
            String title = (String) row.get(HistoryKeys.FXTITLE.getLabel());
            String description = (String) row.get(HistoryKeys.FXDESCRIPTION.getLabel());
            String priority = (String) row.get(HistoryKeys.FXPRIORITY.getLabel());
            boolean remind = ((Number) row.get(HistoryKeys.FXREMIND.getLabel())).intValue() == 1;
            LocalDate remind_date = Optional.ofNullable((Date) row.get(HistoryKeys.FXREMIND_DATE.getLabel()))
                    .map(Date::toLocalDate)
                    .orElse(null);
            String color = (String) row.get(HistoryKeys.FXCOLOR.getLabel());
            boolean status = ((Number) row.get(HistoryKeys.FXSTATUS.getLabel())).intValue() == 1;

            historyEntryList.add(new HistoryEntry(initials, date, author, category, title, description,
                    priority, remind, remind_date, color, status));
        }

        return historyEntryList;
    }

    /*public List<HistoryData> getAllHistoryDataById() {
        List<HistoryData> historyDataList = new ArrayList<>();

    }*/
}
