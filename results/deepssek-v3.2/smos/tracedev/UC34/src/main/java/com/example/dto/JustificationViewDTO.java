package com.example.dto;

import java.util.List;

/**
 * Data Transfer Object containing lists of justified and unjustified absences.
 */
public class JustificationViewDTO {
    private List<AbsenceDTO> justifiedAbsences;
    private List<AbsenceDTO> unjustifiedAbsences;

    /**
     * Constructor for JustificationViewDTO.
     * @param justifiedAbsences list of justified absence DTOs
     * @param unjustifiedAbsences list of unjustified absence DTOs
     */
    public JustificationViewDTO(List<AbsenceDTO> justifiedAbsences, List<AbsenceDTO> unjustifiedAbsences) {
        this.justifiedAbsences = justifiedAbsences;
        this.unjustifiedAbsences = unjustifiedAbsences;
    }

    public List<AbsenceDTO> getJustifiedAbsences() {
        return justifiedAbsences;
    }

    public List<AbsenceDTO> getUnjustifiedAbsences() {
        return unjustifiedAbsences;
    }

    @Override
    public String toString() {
        return "JustificationViewDTO{justifiedAbsences=" + justifiedAbsences + ", unjustifiedAbsences=" + unjustifiedAbsences + "}";
    }

    /**
     * Method corresponding to sequence message m15: new JustificationViewDTO(justified, unjustified)
     * This is the constructor, but we add a static factory method for clarity.
     */
    public static JustificationViewDTO newJustificationViewDTO(List<AbsenceDTO> justified, List<AbsenceDTO> unjustified) {
        return new JustificationViewDTO(justified, unjustified);
    }
}