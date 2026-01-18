package com.example.justification.application.dto;

import java.util.Date;

/**
 * Data Transfer Object (DTO) for displaying absence information to the UI.
 * Contains simplified and formatted data relevant for presentation.
 */
public class AbsenceDisplayDTO {
    public String absenceId;
    public Date date;
    public String reason;
    public JustificationStatus status;
    public String justificationDetails;

    /**
     * Constructs a new AbsenceDisplayDTO.
     *
     * @param id The ID of the absence.
     * @param date The date of the absence.
     * @param reason The reason for the absence.
     * @param status The justification status of the absence.
     * @param details Additional justification details.
     */
    public AbsenceDisplayDTO(String id, Date date, String reason, JustificationStatus status, String details) {
        this.absenceId = id;
        this.date = date;
        this.reason = reason;
        this.status = status;
        this.justificationDetails = details;
    }

    @Override
    public String toString() {
        return "AbsenceDisplayDTO{" +
               "absenceId='" + absenceId + '\'' +
               ", date=" + date +
               ", reason='" + reason + '\'' +
               ", status=" + status +
               ", justificationDetails='" + justificationDetails + '\'' +
               '}';
    }
}