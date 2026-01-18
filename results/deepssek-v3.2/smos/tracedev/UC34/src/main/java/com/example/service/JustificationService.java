package com.example.service;

import com.example.dto.AbsenceDTO;
import com.example.dto.JustificationViewDTO;
import com.example.entity.Absence;
import com.example.enums.JustificationStatus;
import com.example.repository.AbsenceRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service orchestrating the retrieval and conversion of absence data.
 */
public class JustificationService {
    private AbsenceRepository absenceRepository;
    private JustificationLogic justificationLogic;

    /**
     * Constructor for JustificationService.
     * @param absenceRepository the repository for absence data
     * @param justificationLogic the logic service for processing absences
     */
    public JustificationService(AbsenceRepository absenceRepository, JustificationLogic justificationLogic) {
        this.absenceRepository = absenceRepository;
        this.justificationLogic = justificationLogic;
    }

    /**
     * Retrieves absences for a student and converts them into a DTO for viewing.
     * @param studentId the student identifier
     * @return a DTO containing separated justified and unjustified absences
     */
    public JustificationViewDTO getAbsencesForJustificationView(String studentId) {
        // Step 1: Retrieve absences from repository
        List<Absence> absenceList = absenceRepository.findAbsencesByStudentId(studentId);

        // Step 2: If no absences found, return empty DTO (sequence m10)
        if (absenceList == null || absenceList.isEmpty()) {
            return new JustificationViewDTO(new ArrayList<>(), new ArrayList<>());
        }

        // Step 3: Separate absences by status using business logic (sequence m13)
        Map<JustificationStatus, List<Absence>> separated = justificationLogic.separateAbsencesByStatus(absenceList);

        // Step 4: Convert each list of entities to list of DTOs (sequence m14)
        List<AbsenceDTO> justifiedDTOs = convertToDTO(separated.get(JustificationStatus.JUSTIFIED));
        List<AbsenceDTO> unjustifiedDTOs = convertToDTO(separated.get(JustificationStatus.TO_JUSTIFY));

        // Step 5: Create and return the view DTO (sequence m15)
        return new JustificationViewDTO(justifiedDTOs, unjustifiedDTOs);
    }

    /**
     * Converts a list of Absence entities to a list of AbsenceDTO objects.
     * @param absenceList the list of Absence entities
     * @return list of AbsenceDTOs
     */
    public List<AbsenceDTO> convertToDTO(List<Absence> absenceList) {
        List<AbsenceDTO> dtoList = new ArrayList<>();
        if (absenceList != null) {
            for (Absence absence : absenceList) {
                AbsenceDTO dto = new AbsenceDTO(
                    absence.getId(),
                    absence.getDate(),
                    absence.getReason(),
                    absence.getStatus()
                );
                dtoList.add(dto);
            }
        }
        return dtoList;
    }

    /**
     * Method representing sequence message m14: Convert Absence Lists to DTO Lists.
     * This is a wrapper for the existing convertToDTO method.
     * @param justified list of justified Absence entities
     * @param unjustified list of unjustified Absence entities
     * @return a map with DTO lists
     */
    public java.util.Map<String, List<AbsenceDTO>> convertAbsenceListsToDTOLists(List<Absence> justified, List<Absence> unjustified) {
        java.util.Map<String, List<AbsenceDTO>> result = new java.util.HashMap<>();
        result.put("justified", convertToDTO(justified));
        result.put("unjustified", convertToDTO(unjustified));
        return result;
    }
}