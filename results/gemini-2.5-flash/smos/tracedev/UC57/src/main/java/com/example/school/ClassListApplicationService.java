package com.example.school;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList; // For empty list

/**
 * Application service responsible for retrieving a list of classes
 * for a given professor.
 * Added to satisfy requirement EC3.
 */
public class ClassListApplicationService {
    private final IClassRepository classRepository;

    /**
     * Constructs a ClassListApplicationService with its repository dependency.
     * Added to satisfy requirement EC3.
     * @param classRepo The repository for Class entities.
     */
    public ClassListApplicationService(IClassRepository classRepo) {
        this.classRepository = classRepo;
    }

    /**
     * Retrieves a list of classes taught by a specific professor.
     * This method always retrieves the most current data from repositories to ensure accuracy (QR1).
     * Added to satisfy requirement EC3, ExC2.
     *
     * @param professorId The ID of the professor.
     * @return A list of ClassDTOs representing the classes.
     * @throws SMOSConnectionException if there's a connection issue with the class repository.
     */
    public List<ClassDTO> getProfessorClasses(String professorId) throws SMOSConnectionException {
        System.out.println("\n[ClassListAppService] Getting classes for professorId: " + professorId);
        try {
            // QR1: Always retrieves the most current data
            List<Class> classes = classRepository.findByProfessorId(professorId);
            System.out.println("[ClassListAppService] Found " + classes.size() + " classes for professorId: " + professorId);
            return classes.stream()
                    .map(clazz -> new ClassDTO(clazz.getClassId(), clazz.getName()))
                    .collect(Collectors.toList());
        } catch (SMOSConnectionException e) {
            // ExC2: Propagate the connection error up the call stack
            System.err.println("[ClassListAppService] SMOS connection error while fetching professor's classes: " + e.getMessage());
            throw e; // Re-throw the exception as specified by the sequence diagram
        }
    }
}