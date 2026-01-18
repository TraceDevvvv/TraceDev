package com.example.studentdelay.presentation;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Data Transfer Object (DTO) for form data submitted from the UI
 * to the controller when recording a student delay.
 */
public class StudentDelayFormData {
    public LocalDate selectedDate;
    public String studentId;
    public String delayReason;

    public StudentDelayFormData() {
    }

    public StudentDelayFormData(LocalDate selectedDate, String studentId, String delayReason) {
        this.selectedDate = selectedDate;
        this.studentId = studentId;
        this.delayReason = delayReason;
    }

    // Getters and Setters (omitted for brevity in this DTO, public fields are sufficient based on diagram)

    @Override
    public String toString() {
        return "StudentDelayFormData{" +
               "selectedDate=" + selectedDate +
               ", studentId='" + studentId + '\'' +
               ", delayReason='" + delayReason + '\'' +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDelayFormData that = (StudentDelayFormData) o;
        return Objects.equals(selectedDate, that.selectedDate) &&
               Objects.equals(studentId, that.studentId) &&
               Objects.equals(delayReason, that.delayReason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(selectedDate, studentId, delayReason);
    }
}