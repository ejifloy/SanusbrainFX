package com.sanusbrain.Models.Entities.History;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The HistoryEntry class represents a history entity with various properties.
 * It is used to display data for a history entry within the listview.
 */
public class HistoryEntry {

    // Properties-Variables
    private final StringProperty initials_property;
    private final ObjectProperty<LocalDate> creationDate_property;
    private final StringProperty author_property;
    private final StringProperty category_property;
    private final StringProperty title_property;
    private final StringProperty description_property;
    private final StringProperty priority_property;
    private final BooleanProperty remind_property;
    private final ObjectProperty<LocalDate> remind_date_property;
    private final StringProperty color_property;
    private final BooleanProperty status_property;
    private final ObservableList<String> attachments_list;

    /**
     * Constructor for creating a HistoryEntry object.
     *
     * @param initials     initials of the author.
     * @param creationDate creation-date of the entry.
     * @param author       author name of the entry.
     * @param category     category of the entry.
     * @param title        title of the entry.
     * @param description  description of the entry.
     */
    public HistoryEntry(String initials, LocalDate creationDate, String author, String category, String title,
                        String description, String priority, boolean remind, LocalDate remind_date, String color, boolean status) {
        this.initials_property = new SimpleStringProperty(this,"initials", initials);
        this.creationDate_property = new SimpleObjectProperty<>(this, "creation_date", creationDate);
        this.author_property = new SimpleStringProperty(this, "author", author);
        this.category_property = new SimpleStringProperty(this, "category", category);
        this.title_property = new SimpleStringProperty(this, "title", title);
        this.description_property = new SimpleStringProperty(this, "description", description);
        this.priority_property = new SimpleStringProperty(this, "priority", priority);
        this.remind_property = new SimpleBooleanProperty(this, "remind", remind);
        this.remind_date_property = new SimpleObjectProperty<>(this,"remindDate",  remind_date);
        this.color_property = new SimpleStringProperty(this, "color", color);
        this.status_property = new SimpleBooleanProperty(this, "status", status);
        this.attachments_list = FXCollections.observableArrayList();
    }

    // Getter for Properties
    public StringProperty getInitials_Property() {
        return initials_property;
    }
    public ObjectProperty<LocalDate> creationDate_Property() {
        return creationDate_property;
    }
    public StringProperty getAuthor_Property() {
        return author_property;
    }
    public StringProperty getCategory_Property() {
        return category_property;
    }
    public StringProperty getTitle_Property() {
        return title_property;
    }
    public StringProperty getDescription_Property() {
        return description_property;
    }
    public StringProperty getPriority_property() {
        return priority_property;
    }
    public BooleanProperty getRemind_Property() {
        return remind_property;
    }
    public ObjectProperty<LocalDate> getRemind_date_Property() {
        return remind_date_property;
    }
    public StringProperty getColor_Property() {
        return color_property;
    }
    public BooleanProperty getStatus_Property(){
        return status_property;
    }
    public ObservableList<String> getAttachments_List() {
        return attachments_list;
    }


    // Attachments Utility-Functions
    public void addAttachment(String attachment){
        attachments_list.add(attachment);
    }
    public void removeAttachment(String attachment){
        attachments_list.remove(attachment);
    }

    /**
     * Returns the date as a formatted string or an empty string if null.
     *
     * @return The formatted date string ("dd.MM.yyy").
     */
    public String getFormattedDate(){
        LocalDate date = creationDate_property.get();
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "";
    }

}
