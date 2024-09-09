package com.sanusbrain.Models.Entities.Attachment;

import java.time.LocalDate;

public class AttachmentData {
    private int id;
    private int historyId;
    private String fileName;
    private String fileType;
    private String filePath;
    private LocalDate uploadDate;

    // Getter
    public int getId() {
        return id;
    }
    public int getHistoryId() {
        return historyId;
    }
    public String getFileName() {
        return fileName;
    }
    public String getFileType() {
        return fileType;
    }
    public String getFilePath() {
        return filePath;
    }
    public LocalDate getUploadDate() {
        return uploadDate;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }
}
