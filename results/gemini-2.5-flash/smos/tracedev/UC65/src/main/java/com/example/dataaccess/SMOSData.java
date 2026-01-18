package com.example.dataaccess;

/**
 * Data Transfer Object (DTO) for raw data fetched from SMOS.
 * Contains strings that would typically be parsed into domain objects.
 */
public class SMOSData {
    public String rawRegistryInfo;
    public String rawStudentStatuses;
    public String rawJustifications;
    public String rawDisciplinaryNotes;

    /**
     * Constructs an SMOSData object.
     * @param rawRegistryInfo Raw string data for registry information.
     * @param rawStudentStatuses Raw string data for student statuses.
     * @param rawJustifications Raw string data for justifications.
     * @param rawDisciplinaryNotes Raw string data for disciplinary notes.
     */
    public SMOSData(String rawRegistryInfo, String rawStudentStatuses, String rawJustifications, String rawDisciplinaryNotes) {
        this.rawRegistryInfo = rawRegistryInfo;
        this.rawStudentStatuses = rawStudentStatuses;
        this.rawJustifications = rawJustifications;
        this.rawDisciplinaryNotes = rawDisciplinaryNotes;
    }

    // Getters for properties
    public String getRawRegistryInfo() {
        return rawRegistryInfo;
    }

    public String getRawStudentStatuses() {
        return rawStudentStatuses;
    }

    public String getRawJustifications() {
        return rawJustifications;
    }

    public String getRawDisciplinaryNotes() {
        return rawDisciplinaryNotes;
    }
}