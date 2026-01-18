package com.example.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Business logic service for managing Class-related operations.
 * This class corresponds to the 'ClassService' class in the UML Class Diagram.
 */
public class ClassService {
    private IClassRepository classRepository;

    /**
     * Constructs a new ClassService instance.
     *
     * @param repository The data repository for Class entities.
     */
    public ClassService(IClassRepository repository) {
        this.classRepository = repository;
    }

    /**
     * Retrieves detailed information for a specific class.
     *
     * @param classId The ID of the class to retrieve details for.
     * @return A ClassDetailsDTO containing the class information.
     * @throws ConnectionException If there's an issue with the database connection (R12).
     */
    public ClassDetailsDTO getClassDetails(String classId) throws ConnectionException {
        System.out.println("Service: getClassDetails(" + classId + ") - Requesting class data from repository.");
        // Sequence Diagram: Service -> Repository: findById(classId)
        Class classObject = classRepository.findById(classId); // Can throw ConnectionException

        if (classObject == null) {
            System.out.println("Service: Class with ID " + classId + " not found in repository.");
            // Assuming null indicates not found, might throw a custom NotFoundException in a real app.
            return null;
        }

        System.out.println("Service: Received class object. Converting to DTO.");
        // Sequence Diagram: Service -> Service: createClassDetailsDTO(classObject)
        ClassDetailsDTO dto = createClassDetailsDTO(classObject);
        System.out.println("Service: DTO created: " + dto.getName());
        // Sequence Diagram: Service --> Controller: classDetailsDTO
        return dto;
    }

    /**
     * Retrieves a list of all classes for a given academic year. (R5)
     *
     * @param academicYear The academic year to filter classes by.
     * @return A list of ClassDetailsDTOs for the specified year.
     * @throws ConnectionException If there's an issue with the database connection.
     */
    public List<ClassDetailsDTO> getAllClassesForYear(String academicYear) throws ConnectionException {
        System.out.println("Service: getAllClassesForYear(" + academicYear + ") - Retrieving all classes.");
        // This is a simplification. A real repository would have a method like findAllBySchoolYear.
        // For demonstration, we'll iterate through all classes in the mocked DB of the repository.
        // Assuming ClassRepositoryImpl has access to all classes for this demo,
        // which might not be ideal but serves the purpose of showing interaction.
        // In a real scenario, IClassRepository would have a `findAllBySchoolYear(String year)` method.

        // Simulating fetching all classes and then filtering (less efficient but works for demo)
        // A more correct implementation would involve a repository method for filtering.
        List<Class> allClasses = new ArrayList<>();
        // This is a workaround since our mock repository only has findById.
        // A proper implementation would extend IClassRepository with a findAll() method or similar.
        // For now, let's assume `classRepository` can give us a 'list' of classes.
        // As per the diagram, ClassRepositoryImpl manages Class. Let's assume it can provide all its managed classes.
        // This is an assumption made due to missing `findAll` in IClassRepository.
        
        // This section won't be called by the sequence diagram, but fulfills the class diagram.
        // To properly implement, `IClassRepository` should have `List<Class> findAll()` or `findAllBySchoolYear()`.
        // Given the current diagram, I'll return an empty list or throw an UnsupportedOperationException
        // as the repository doesn't expose a method to get ALL classes.
        System.out.println("Service: Warning - `getAllClassesForYear` not fully implemented as `IClassRepository` lacks a 'findAll' method.");
        // Returning an empty list as a placeholder.
        return new ArrayList<>();
    }

    /**
     * Converts a Class domain object to a ClassDetailsDTO.
     * This is a private helper method as per the UML Class Diagram.
     *
     * @param classObject The Class object to convert.
     * @return A ClassDetailsDTO with relevant information.
     */
    private ClassDetailsDTO createClassDetailsDTO(Class classObject) {
        System.out.println("Service: createClassDetailsDTO - Converting " + classObject.getId() + " to DTO.");
        return new ClassDetailsDTO(classObject.getName(), classObject.getAddress(), classObject.getSchoolYear());
    }
}