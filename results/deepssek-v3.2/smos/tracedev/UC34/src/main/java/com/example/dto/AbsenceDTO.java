package com.example.dto;

import com.example.enums.JustificationStatus;
import java.util.Date;

/**
 * Data Transfer Object for Absence.
 */
public class AbsenceDTO {
    private String id;
    private Date date;
    private String reason;
    private JustificationStatus status;

    /**
     * Constructor for AbsenceDTO.
     * @param id the absence identifier
     * @param date the date of absence
     * @param reason the reason for absence
     * @param status the justification status
     */
    public AbsenceDTO(String id, Date date, String reason, JustificationStatus status) {
        this.id = id;
        this.date = date;
        this.reason = reason;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getReason() {
        return reason;
    }

    public JustificationStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "AbsenceDTO{id='" + id + "', date=" + date + ", reason='" + reason + "', status=" + status + "}";
    }
}