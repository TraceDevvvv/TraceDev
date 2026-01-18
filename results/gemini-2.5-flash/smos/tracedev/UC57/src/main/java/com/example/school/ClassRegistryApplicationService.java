
package com.example.school;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList; // For Collections.emptyList() replacement

/**
 * Application service responsible for orchestrating the retrieval and aggregation
 * of class registry data from various repositories.
 * This service also handles mapping domain entities to DTOs.
 */
public class ClassRegistryApplicationService {
    private final IClassRepository classRepository;
    private final IAbsenceRepository absenceRepository;
    private final IDisciplinaryNoteRepository disciplinaryNoteRepository;
    private final IDelayRepository delayRepository;
    private final StudentNameResolver studentNameResolver; // Added for mapping studentId to studentName

    /**
     * Constructs a ClassRegistryApplicationService with necessary repository dependencies.
     * @param classRepo The repository for Class entities.
     * @param absRepo The repository for Absence entities.
     * @param noteRepo The repository for DisciplinaryNote entities.
     * @param delayRepo The repository for Delay entities.
     */
    public ClassRegistryApplicationService(IClassRepository classRepo, IAbsenceRepository absRepo,
                                           IDisciplinaryNoteRepository noteRepo, IDelayRepository delayRepo) {
        this.classRepository = classRepo;
        this.absenceRepository = absRepo;
        this.disciplinaryNoteRepository = noteRepo;
        this.delayRepository = delayRepo;
        this.studentNameResolver = new StudentNameResolver(); // Initialize mock student name resolver
    }

    /**
     * Retrieves comprehensive registry data for a specific class.
     * This method always retrieves the most current data from repositories to ensure accuracy and up-to-dateness per QR1.
     *
     * @param classId The ID of the class to retrieve registry data for.
     * @return A ClassRegistryDTO containing all relevant information.
     * @throws SMOSConnectionException if there's a connection issue with any underlying repository (ExC2).
     */
    public ClassRegistryDTO getClassRegistryData(String classId) throws SMOSConnectionException {
        System.out.println("\n[AppService] Getting class registry data for classId: " + classId);
        Class clazz = null;
        List<Absence> absences = new ArrayList<>();
        List<DisciplinaryNote> disciplinaryNotes = new ArrayList<>();
        List<Delay> delays = new ArrayList<>();

        try {
            // QR1: Always retrieves the most current data
            clazz = classRepository.findById(classId);
            if (clazz == null) {
                // If class not found, return an empty DTO or throw a specific "NotFound" exception
                // For simplicity, we'll return a DTO with null class name for now.
                System.out.println("[AppService] Class with ID " + classId + " not found.");
                return new ClassRegistryDTO(classId, "Class Not Found", new Date(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            }
            absences = absenceRepository.findByClassId(classId); // QR1: most current data
            disciplinaryNotes = disciplinaryNoteRepository.findByClassId(classId); // QR1: most current data
            delays = delayRepository.findByClassId(classId); // QR1: most current data

        } catch (SMOSConnectionException e) {
            // ExC2: If any repository call fails due to connection, re-throw the exception
            System.err.println("[AppService] SMOS connection error while fetching class registry data: " + e.getMessage());
            handleRepositoryError(e); // This method will re-throw the SMOSConnectionException
        }

        // Map entities to DTOs
        String className = (clazz != null) ? clazz.getName() : "Unknown Class";
        List<AbsenceDTO> absenceDTOs = absences.stream()
                .map(this::mapAbsenceToDTO)
                .collect(Collectors.toList());
        List<DisciplinaryNoteDTO> disciplinaryNoteDTOs = disciplinaryNotes.stream()
                .map(this::mapNoteToDTO)
                .collect(Collectors.toList());
        List<DelayDTO> delayDTOs = delays.stream()
                .map(this::mapDelayToDTO)
                .collect(Collectors.toList());

        // Aggregate into ClassRegistryDTO
        ClassRegistryDTO result = new ClassRegistryDTO(classId, className, new Date(),
                absenceDTOs, disciplinaryNoteDTOs, delayDTOs);
        System.out.println("[AppService] Successfully prepared ClassRegistryDTO for classId: " + classId);
        return result;
    }

    /**
     * Maps an Absence entity to an AbsenceDTO.
     * @param absence The Absence entity.
     * @return The corresponding AbsenceDTO.
     */
    private AbsenceDTO mapAbsenceToDTO(Absence absence) {
        String studentName = studentNameResolver.getStudentName(absence.getStudentId());
        return new AbsenceDTO(studentName, absence.getDate(), absence.getType(), absence.getJustification());
    }

    /**
     * Maps a DisciplinaryNote entity to a DisciplinaryNoteDTO.
     * @param note The DisciplinaryNote entity.
     * @return The corresponding DisciplinaryNoteDTO.
     */
    private DisciplinaryNoteDTO mapNoteToDTO(DisciplinaryNote note) {
        String studentName = studentNameResolver.getStudentName(note.getStudentId());
        return new DisciplinaryNoteDTO(studentName, note.getDate(), note.getDescription());
    }

    /**
     * Maps a Delay entity to a DelayDTO.
     * @param delay The Delay entity.
     * @return The corresponding DelayDTO.
     */
    private DelayDTO mapDelayToDTO(Delay delay) {
        String studentName = studentNameResolver.getStudentName(delay.getStudentId());
        return new DelayDTO(studentName, delay.getDate(), delay.getDurationMinutes(), delay.getJustification());
    }

    /**
     * Handles repository errors by re-throwing a SMOSConnectionException.
     * The sequence diagram indicates the AppService throws SMOSConnectionException to the Controller.
     * The Class Diagram method signature "returns ClassRegistryDTO throws SMOSConnectionException" is
     * slightly contradictory for a "handleRepositoryError" helper, so we prioritize the sequence diagram flow
     * where the exception propagates up.
     * Added to satisfy requirement ExC2.
     *
     * @param e The original exception from the repository.
     * @throws SMOSConnectionException The wrapped or original SMOSConnectionException.
     */
    private void handleRepositoryError(Exception e) throws SMOSConnectionException {
        // Log the error for internal diagnostics
        System.err.println("Error in repository operation: " + e.getMessage());
        // Re-throw as SMOSConnectionException to propagate to the controller
        if (e instanceof SMOSConnectionException) {
            throw (SMOSConnectionException) e;
        } else {
            throw new SMOSConnectionException("An unexpected repository error occurred.", e);
        }
    }
}
