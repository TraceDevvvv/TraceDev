
package com.example.dto;

import com.example.model.StudentDelay;
import java.util.Date;
import java.util.List;

/**
 * Data transfer object for log data.
 */
public class LogDataDTO {
    private Date date;
    private List<StudentDelay> delays;
    private int totalDelays;
    
    // Getters and setters
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public List<StudentDelay> getDelays() {
        return delays;
    }
    
    public void setDelays(List<StudentDelay> delays) {
        this.delays = delays;
    }
    
    public int getTotalDelays() {
        return totalDelays;
    }
    
    public void setTotalDelays(int totalDelays) {
        this.totalDelays = totalDelays;
    }
}
