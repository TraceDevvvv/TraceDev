package dtos;

/**
 * Data Transfer Object (DTO) for Teaching information.
 * Used to transfer data between layers, often representing a simplified or aggregated view
 * suitable for presentation.
 */
public class TeachingDto {
    // + id : String
    public String id;
    // + title : String
    public String title;
    // + instructor : String
    public String instructor;
    // + summary : String
    public String summary;

    /**
     * Constructor for TeachingDto.
     *
     * @param id The unique identifier for the teaching.
     * @param title The title of the teaching.
     * @param instructor The name of the instructor.
     * @param summary A brief summary of the teaching.
     */
    public TeachingDto(String id, String title, String instructor, String summary) {
        this.id = id;
        this.title = title;
        this.instructor = instructor;
        this.summary = summary;
    }

    // Getters and setters (or access fields directly as they are public based on UML)
    // For simplicity, using public fields as per '+' in UML, but typically would be private with getters/setters.
}