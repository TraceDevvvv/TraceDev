package com.example.school;

import java.util.Date;
import java.util.List;
import java.util.Collections; // For unmodifiable lists

/**
 * Data Transfer Object (DTO) for a complete class registry,
 * aggregating absences, disciplinary notes, and delays.
 */
public class ClassRegistryDTO {
    private String classId;
    private String className;
    private Date registryDate;
    private List<AbsenceDTO> absences;
    private List<DisciplinaryNoteDTO> disciplinaryNotes;
    private List<DelayDTO> delays;

    /**
     * Constructs a new ClassRegistryDTO.
     * @param classId The unique identifier of the class.
     * @param className The name of the class.
     * @param registryDate The date this registry data was generated.
     * @param absences A list of absence DTOs.
     * @param disciplinaryNotes A list of disciplinary note DTOs.
     * @param delays A list of delay DTOs.
     */
    public ClassRegistryDTO(String classId, String className, Date registryDate,
                            List<AbsenceDTO> absences, List<DisciplinaryNoteDTO> disciplinaryNotes, List<DelayDTO> delays) {
        this.classId = classId;
        this.className = className;
        this.registryDate = registryDate;
        // Defensive copying to ensure immutability from external modification
        this.absences = absences != null ? List.copyOf(absences) : Collections.emptyList();
        this.disciplinaryNotes = disciplinaryNotes != null ? List.copyOf(disciplinaryNotes) : Collections.emptyList();
        this.delays = delays != null ? List.copyOf(delays) : Collections.emptyList();
    }

    /**
     * Gets the unique identifier of the class.
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Gets the name of the class.
     * @return The class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Gets the date this registry data was generated.
     * @return The registry date.
     */
    public Date getRegistryDate() {
        return registryDate;
    }

    /**
     * Gets an unmodifiable list of absence DTOs.
     * @return A list of absences.
     */
    public List<AbsenceDTO> getAbsences() {
        return absences;
    }

    /**
     * Gets an unmodifiable list of disciplinary note DTOs.
     * @return A list of disciplinary notes.
     */
    public List<DisciplinaryNoteDTO> getDisciplinaryNotes() {
        return disciplinaryNotes;
    }

    /**
     * Gets an unmodifiable list of delay DTOs.
     * @return A list of delays.
     */
    public List<DelayDTO> getDelays() {
        return delays;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ClassRegistryDTO {\n");
        sb.append("  classId='").append(classId).append("',\n");
        sb.append("  className='").append(className).append("',\n");
        sb.append("  registryDate=").append(registryDate).append(",\n");
        sb.append("  absences=[\n");
        absences.forEach(a -> sb.append(a).append(",\n"));
        if (!absences.isEmpty()) sb.setLength(sb.length() - 2); // Remove trailing comma and newline
        sb.append("\n  ],\n");
        sb.append("  disciplinaryNotes=[\n");
        disciplinaryNotes.forEach(d -> sb.append(d).append(",\n"));
        if (!disciplinaryNotes.isEmpty()) sb.setLength(sb.length() - 2);
        sb.append("\n  ],\n");
        sb.append("  delays=[\n");
        delays.forEach(d -> sb.append(d).append(",\n"));
        if (!delays.isEmpty()) sb.setLength(sb.length() - 2);
        sb.append("\n  ]\n");
        sb.append("}");
        return sb.toString();
    }
}