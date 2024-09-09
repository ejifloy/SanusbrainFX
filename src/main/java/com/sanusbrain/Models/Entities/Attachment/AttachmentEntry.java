package com.sanusbrain.Models.Entities.Attachment;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AttachmentEntry {
    private final StringProperty fileName_property;
    private final StringProperty fileType_property;
    private final StringProperty filePath_property;
    private final ObjectProperty<LocalDate> uploadDate_property;

    /**
     * Constructor for creating an Attachment-Entry object.
     *
     * @param fileName      Name of the associated file.
     * @param fileType      Type of the associated file.
     * @param filePath      Path of the associated file
     * @param uploadDate    Date of the Upload.
     */
    public AttachmentEntry(String fileName, String fileType, String filePath, LocalDate uploadDate) {
        this.fileName_property = new SimpleStringProperty(this, "fileName", fileName);
        this.fileType_property = new SimpleStringProperty(this, "fileType", fileType);
        this.filePath_property = new SimpleStringProperty(this, "filePath", filePath);
        this.uploadDate_property =new SimpleObjectProperty<>(this, "uploadDate", uploadDate);
    }

    // Getter
    public StringProperty fileName_propertyProperty() {
        return fileName_property;
    }
    public StringProperty fileType_propertyProperty() {
        return fileType_property;
    }
    public StringProperty filePath_propertyProperty() {
        return filePath_property;
    }
    public ObjectProperty<LocalDate> uploadDate_propertyProperty() {
        return uploadDate_property;
    }


    /**
     * Returns the upload date as a formatted string or an empty string if null.
     *
     * @return The formatted upload date string ("dd.MM.yyy").
     */
    public String getFormattedBirthdate(){
        LocalDate date = uploadDate_property.get();
        return date != null ? date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) : "";
    }
}
