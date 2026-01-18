package presentation;

import application.TeachingService;
import dtos.TeachingDto;

import java.util.List;

/**
 * Controller in the Presentation Layer.
 * Acts as an intermediary between the UI and the Application Layer (Service).
 */
public class TeachingController {
    // - teachingService : TeachingService
    private TeachingService teachingService;

    /**
     * Constructor for TeachingController.
     * Initializes the TeachingService dependency.
     */
    public TeachingController() {
        // Initialize dependencies.
        this.teachingService = new TeachingService();
        System.out.println("TeachingController: Initialized.");
    }

    /**
     * Handles the request to view all available teachings.
     *
     * @return A list of TeachingDto objects representing the teachings.
     */
    public List<TeachingDto> viewTeachings() {
        System.out.println("TeachingController: Received request to view teachings.");

        // Controller -> Service : getTeachings()
        System.out.println("TeachingController: Calling TeachingService to get teachings.");
        List<TeachingDto> teachingDtos = teachingService.getTeachings();

        System.out.println("TeachingController: Received " + (teachingDtos != null ? teachingDtos.size() : 0) + " teaching DTOs from service.");
        return teachingDtos;
    }

    // Setter for TeachingService, primarily for testing purposes or dynamic configuration
    public void setTeachingService(TeachingService teachingService) {
        this.teachingService = teachingService;
    }
}