package com.example.serv;

import com.example.dtos.DailyRegistryDTO;
import com.example.dtos.RegistryDetailsDTO;
import com.example.dtos.StudentStatusDTO;
import com.example.entities.Registry;
import com.example.entities.RegistryEntry;
import com.example.entities.StudentEntry;
import com.example.exceptions.SMOSConnectionException;
import com.example.repositories.RegistryRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for registry details operations.
 */
public class RegistryDetailsService {
    private RegistryRepository registryRepository;

    public RegistryDetailsService(RegistryRepository registryRepository) {
        this.registryRepository = registryRepository;
    }

    public RegistryDetailsDTO getRegistryDetails(String academicYear, String className) {
        Registry registry = registryRepository.findByAcademicYearAndClassName(academicYear, className);
        if (registry == null) {
            // Return empty DTO if registry not found
            return new RegistryDetailsDTO(academicYear, className, new ArrayList<>());
        }

        RegistryDetailsDTO dto = new RegistryDetailsDTO(academicYear, className, new ArrayList<>());
        for (RegistryEntry entry : registry.getRegistryEntries()) {
            DailyRegistryDTO dailyDTO = new DailyRegistryDTO(entry.getDate(), new ArrayList<>());
            for (StudentEntry studentEntry : entry.getStudentEntries()) {
                StudentStatusDTO studentDTO = new StudentStatusDTO(
                    studentEntry.getStudentId(),
                    studentEntry.getAttendanceStatus(),
                    studentEntry.isDelayed(),
                    studentEntry.getJustification(),
                    studentEntry.getDisciplinaryNote()
                );
                dailyDTO.addStudent(studentDTO);
            }
            dto.addDailyRegistry(dailyDTO);
        }
        return dto;
    }

    public void updateJustification(String studentId, String justification) throws SMOSConnectionException {
        // Simplified: Find registry and update justification for the student
        // In a real system, we would need to know which registry and date
        // For this example, we'll create a dummy registry and update
        Registry registry = createOrGetRegistry();
        findStudentInRegistry(registry, studentId);
        updateStudentField(registry, studentId, justification, true);
        registryRepository.save(registry);
    }

    public void updateDisciplinaryNote(String studentId, String note) throws SMOSConnectionException {
        Registry registry = createOrGetRegistry();
        findStudentInRegistry(registry, studentId);
        updateStudentField(registry, studentId, note, false);
        registryRepository.save(registry);
    }

    private Registry createOrGetRegistry() {
        // Simplified: Returns a dummy registry for demonstration
        // In real system, would fetch actual registry
        return registryRepository.findByAcademicYearAndClassName("2023-2024", "Class A");
    }

    private void updateStudentField(Registry registry, String studentId, String value, boolean isJustification) {
        for (RegistryEntry entry : registry.getRegistryEntries()) {
            for (StudentEntry studentEntry : entry.getStudentEntries()) {
                if (studentEntry.getStudentId().equals(studentId)) {
                    if (isJustification) {
                        studentEntry.setJustification(value);
                    } else {
                        studentEntry.setDisciplinaryNote(value);
                    }
                    return;
                }
            }
        }
        // If student not found, add new entry (simplified)
        RegistryEntry firstEntry = registry.getRegistryEntries().get(0);
        StudentEntry newEntry = new StudentEntry(studentId, null, false, "", "");
        if (isJustification) {
            newEntry.setJustification(value);
        } else {
            newEntry.setDisciplinaryNote(value);
        }
        firstEntry.addStudentEntry(newEntry);
    }

    // Methods corresponding to sequence diagram messages
    public void findStudentInRegistry(Registry registry, String studentId) {
        // Implementation for finding student in registry
    }

    public void updateJustification(String studentId, String justification, Registry registry) {
        // Implementation for updating justification
        updateStudentField(registry, studentId, justification, true);
    }

    public void updateDisciplinaryNote(String studentId, String note, Registry registry) {
        // Implementation for updating disciplinary note
        updateStudentField(registry, studentId, note, false);
    }

    public void errorSaveFailed(String message) {
        // Implementation for error save failed message
    }
}