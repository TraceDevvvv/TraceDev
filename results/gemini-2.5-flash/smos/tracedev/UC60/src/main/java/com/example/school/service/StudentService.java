package com.example.school.service;

import com.example.school.model.ClassRegistryDTO;
import com.example.school.model.ClassRegistryEntry;
import com.example.school.repository.StudentRepository;
import com.example.school.mapper.ClassRegistryMapper;

import java.util.Collections;
import java.util.List;

/**
 * Service layer for student-related operations, specifically retrieving class registry data.
 * This class orchestrates calls to the repository and mappers.
 */
public class StudentService {

    private final StudentRepository studentRepository;

    /**
     * Constructs a StudentService with a given StudentRepository.
     *
     * @param studentRepository The repository to use for fetching student data.
     */
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /**
     * Retrieves the class registry for a specified student and transforms it into DTOs.
     * This method follows the sequence diagram flow:
     * - Calls the repository to get raw entries.
     * - If successful, uses the mapper to convert entries to DTOs.
     *
     * @param studentId The ID of the student.
     * @return A list of {@link ClassRegistryDTO} for the student, or an empty list if not found or an error occurs.
     *         Returns null or throws an exception in a more complex error handling scenario.
     */
    public List<ClassRegistryDTO> getStudentClassRegistry(String studentId) {
        System.out.println("StudentService: Getting class registry for student: " + studentId);
        List<ClassRegistryEntry> classRegistryEntries;

        try {
            // SD: Service -> Repository : findClassRegistryEntries(studentId)
            classRegistryEntries = studentRepository.findClassRegistryEntries(studentId);

            if (classRegistryEntries == null || classRegistryEntries.isEmpty()) {
                System.out.println("StudentService: No class registry entries found for student " + studentId + " or SMOS server unavailable.");
                // SD: Repository --> Service : Error: "SMOS Server Unavailable" (m15 handled by empty list here)
                return Collections.emptyList(); // Return empty list for no data or server unavailable (m15)
            }

            // SD: Service -> Mapper : toDTOs(classRegistryEntries) (m16)
            List<ClassRegistryDTO> classRegistryDTOs = ClassRegistryMapper.toDTOs(classRegistryEntries);
            System.out.println("StudentService: Successfully retrieved and mapped " + classRegistryDTOs.size() + " DTOs. (Trace: m16)");
            return classRegistryDTOs; // Return classRegistryDTOs for success (m18)

        } catch (Exception e) {
            System.err.println("StudentService: An unexpected error occurred while fetching class registry for student " + studentId + ": " + e.getMessage());
            // In a real application, you might want to wrap this in a custom business exception.
            // SD: Service --> Controller : error (m18 for error path)
            return null; // Indicating a significant failure, handled as 'error' in m18
        }
    }
}