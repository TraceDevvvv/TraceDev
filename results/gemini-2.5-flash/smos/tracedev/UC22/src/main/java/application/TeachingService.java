package application;

import dataaccess.ITeachingRepository;
import dataaccess.TeachingRepository;
import domain.Teaching;
import dtos.TeachingDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Service in the Application Layer.
 * Contains business logic related to teachings and orchestrates data access.
 */
public class TeachingService {
    // - teachingRepository : ITeachingRepository
    private ITeachingRepository teachingRepository;

    /**
     * Constructor for TeachingService.
     * Initializes the ITeachingRepository dependency with a concrete implementation.
     */
    public TeachingService() {
        // Initialize dependencies. In a real application, this would often be
        // handled by a dependency injection framework.
        this.teachingRepository = new TeachingRepository();
        System.out.println("TeachingService: Initialized with TeachingRepository.");
    }

    /**
     * Retrieves all teachings, converts them to DTOs, and returns them.
     *
     * @return A list of TeachingDto objects. Returns an empty list or null if an error occurs.
     */
    public List<TeachingDto> getTeachings() {
        System.out.println("TeachingService: Request to get teachings received.");

        // Service -> Repo : findAll()
        System.out.println("TeachingService: Calling ITeachingRepository to find all teachings.");
        List<Teaching> teachingEntities = teachingRepository.findAll();

        // Handle potential errors from repository (e.g., SMOS connection failure)
        if (teachingEntities == null) {
            System.err.println("TeachingService: Error: No teaching entities returned from repository (possibly connection issue).");
            return new ArrayList<>(); // Return an empty list to indicate no data
        }

        // Service -> Service : convertToTeachingDtos(teachingEntities)
        System.out.println("TeachingService: Converting Teaching entities to Teaching DTOs.");
        List<TeachingDto> teachingDtos = convertToTeachingDtos(teachingEntities);

        System.out.println("TeachingService: Returning " + teachingDtos.size() + " Teaching DTOs.");
        return teachingDtos;
    }

    /**
     * Converts a list of Teaching domain entities to a list of TeachingDto objects.
     *
     * @param teachings A list of Teaching domain entities.
     * @return A list of TeachingDto objects.
     */
    private List<TeachingDto> convertToTeachingDtos(List<Teaching> teachings) {
        System.out.println("TeachingService: Executing convertToTeachingDtos...");
        List<TeachingDto> dtos = new ArrayList<>();
        if (teachings != null) {
            for (Teaching teaching : teachings) {
                // Mapping Teaching to TeachingDto
                // TeachingDto: id, title, instructor, summary
                // Teaching: id, name, description, teacherName
                dtos.add(new TeachingDto(
                        teaching.id,
                        teaching.name,        // Assuming 'name' maps to 'title'
                        teaching.teacherName, // Assuming 'teacherName' maps to 'instructor'
                        teaching.description  // Assuming 'description' maps to 'summary'
                ));
            }
        }
        System.out.println("TeachingService: Converted " + (teachings != null ? teachings.size() : 0) + " entities to " + dtos.size() + " DTOs.");
        return dtos;
    }

    // Setter for ITeachingRepository, primarily for testing purposes or dynamic configuration
    public void setTeachingRepository(ITeachingRepository teachingRepository) {
        this.teachingRepository = teachingRepository;
    }
}