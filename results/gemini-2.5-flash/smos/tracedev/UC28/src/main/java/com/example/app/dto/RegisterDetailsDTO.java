package com.example.app.dto;

import com.example.app.DTO;
import java.util.List;

/**
 * DTO for displaying detailed information about a class register,
 * including student attendance, justifications, and disciplinary notes for a specific date.
 */
public class RegisterDetailsDTO implements DTO {
    public String registerId;
    public String registerName;
    public String registerDescription;
    public String date; // Formatted date string
    public List<StudentAttendanceDTO> studentAttendanceList;
    public List<JustificationDTO> justifications;
    public List<DisciplinaryNoteDTO> disciplinaryNotes;

    /**
     * Constructs a new RegisterDetailsDTO.
     * @param registerId The ID of the register.
     * @param registerName The name of the register.
     * @param registerDescription The description of the register.
     * @param date The date for which details are retrieved (formatted string).
     * @param studentAttendanceList A list of student attendance details.
     * @param justifications A list of justifications for the given date.
     * @param disciplinaryNotes A list of disciplinary notes for the given date.
     */
    public RegisterDetailsDTO(String registerId, String registerName, String registerDescription, String date, List<StudentAttendanceDTO> studentAttendanceList, List<JustificationDTO> justifications, List<DisciplinaryNoteDTO> disciplinaryNotes) {
        this.registerId = registerId;
        this.registerName = registerName;
        this.registerDescription = registerDescription;
        this.date = date;
        this.studentAttendanceList = studentAttendanceList;
        this.justifications = justifications;
        this.disciplinaryNotes = disciplinaryNotes;
    }

    // Getters for public fields
    public String getRegisterId() {
        return registerId;
    }

    public String getRegisterName() {
        return registerName;
    }

    public String getRegisterDescription() {
        return registerDescription;
    }

    public String getDate() {
        return date;
    }

    public List<StudentAttendanceDTO> getStudentAttendanceList() {
        return studentAttendanceList;
    }

    public List<JustificationDTO> getJustifications() {
        return justifications;
    }

    public List<DisciplinaryNoteDTO> getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Register Details ---\n");
        sb.append(String.format("Register ID: %s\n", registerId));
        sb.append(String.format("Name: %s\n", registerName));
        sb.append(String.format("Description: %s\n", registerDescription));
        sb.append(String.format("Date: %s\n", date));
        sb.append("\n--- Student Attendance ---\n");
        if (studentAttendanceList == null || studentAttendanceList.isEmpty()) {
            sb.append("No student attendance records for this date.\n");
        } else {
            for (StudentAttendanceDTO sa : studentAttendanceList) {
                sb.append(sa.toString()).append("\n");
            }
        }
        sb.append("\n--- Justifications ---\n");
        if (justifications == null || justifications.isEmpty()) {
            sb.append("No justifications for this date.\n");
        } else {
            for (JustificationDTO j : justifications) {
                sb.append(j.toString()).append("\n");
            }
        }
        sb.append("\n--- Disciplinary Notes ---\n");
        if (disciplinaryNotes == null || disciplinaryNotes.isEmpty()) {
            sb.append("No disciplinary notes for this date.\n");
        } else {
            for (DisciplinaryNoteDTO dn : disciplinaryNotes) {
                sb.append(dn.toString()).append("\n");
            }
        }
        sb.append("------------------------\n");
        return sb.toString();
    }
}