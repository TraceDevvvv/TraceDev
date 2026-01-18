package com.example.studentdelay.dataaccess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for conveying student delay information
 * between layers, typically between the Application and Presentation layers.
 */
public class StudentDelayDTO {
    public String id;
    public String studentId;
    public String studentName; // Added to easily display in UI
    public LocalDate delayDate;
    public String reason;
    public LocalDateTime entryTimestamp;

    public StudentDelayDTO() {}

    public StudentDelayDTO(String id, String studentId, String studentName, LocalDate delayDate, String reason, LocalDateTime entryTimestamp) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.delayDate = delayDate;
        this.reason = reason;
        this.entryTimestamp = entryTimestamp;
    }

    // Getters and Setters (omitted for brevity in this DTO, public fields are sufficient based on diagram)

    @Override
    public String toString() {
        return "StudentDelayDTO{" +
               "id='" + id + '\'' +
               ", studentId='" + studentId + '\'' +
               ", studentName='" + studentName + '\'' +
               ", delayDate=" + delayDate +
               ", reason='" + reason + '\'' +
               ", entryTimestamp=" + entryTimestamp +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDelayDTO that = (StudentDelayDTO) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(studentId, that.studentId) &&
               Objects.equals(studentName, that.studentName) &&
               Objects.equals(delayDate, that.delayDate) &&
               Objects.equals(reason, that.reason) &&
               Objects.equals(entryTimestamp, that.entryTimestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, studentId, studentName, delayDate, reason, entryTimestamp);
    }
}