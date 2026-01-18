package application;

/**
 * Data Transfer Object for Agency information.
 */
public class AgencyDTO {
    private final String id;
    private final String name;

    public AgencyDTO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}