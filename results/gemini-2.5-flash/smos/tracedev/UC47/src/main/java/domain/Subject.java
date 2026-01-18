package domain;

/**
 * Represents a subject entity in the domain.
 */
public class Subject {
    private String id;
    private String name;

    /**
     * Constructs a new Subject.
     * @param id The unique identifier for the subject.
     * @param name The name of the subject.
     */
    public Subject(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Setters can be added if mutable properties are desired, but not specified in CD.
}