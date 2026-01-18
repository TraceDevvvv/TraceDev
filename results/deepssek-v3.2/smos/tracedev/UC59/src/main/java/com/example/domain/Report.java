package com.example.domain;

import java.util.Date;

/**
 * Represents a report entity in the domain.
 */
public class Report {
    public String id;
    public String title;
    public String content;
    public Date date;
    
    public Report(String id, String title, String content, Date date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }
    
    public String getId() {
        return id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getContent() {
        return content;
    }
    
    public Date getDate() {
        return date;
    }
    
    /**
     * Returns detailed information about the report.
     */
    public String viewDetails() {
        return "Report ID: " + id + "\nTitle: " + title + "\nContent: " + content + "\nDate: " + date;
    }
}