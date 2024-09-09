package com.sanusbrain.Models.Entities.History;

import com.sanusbrain.Models.Entities.Attachment.AttachmentData;

import java.time.LocalDate;
import java.util.List;

public class HistoryData {

    private String initials;
    private LocalDate creationDate;
    private String author;
    private String category;
    private String title;
    private String description;
    private boolean remind;
    private LocalDate remindDate;
    private String color;
    private boolean status;
    private List<AttachmentData> attachments;

    // Getter
    public String getInitials() {
        return initials;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public String getAuthor() {
        return author;
    }
    public String getCategory() {
        return category;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean isRemind() {
        return remind;
    }
    public LocalDate getRemindDate() {
        return remindDate;
    }
    public String getColor() {
        return color;
    }
    public boolean isStatus() {
        return status;
    }
    public List<AttachmentData> getAttachments() {
        return attachments;
    }

    // Setter
    public void setInitials(String initials) {
        this.initials = initials;
    }
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setRemind(boolean remind) {
        this.remind = remind;
    }
    public void setRemindDate(LocalDate remindDate) {
        this.remindDate = remindDate;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setStatus(boolean status){
        this.status = status;
    }
    public void setAttachments(List<AttachmentData> attachments) {
        this.attachments = attachments;
    }

    /**
     * Generates initials value for the patient.
     *
     * @return provides the initials as string value.
     */
    public String generateInitials(){
        initials = (author.charAt(0)+""+author.charAt(author.indexOf(' ')+1)).toUpperCase();
        System.out.println("Check initials: "+initials);
        return initials;
    }
}
