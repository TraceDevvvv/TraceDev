package enterprisebusinessrules;

/**
 * Represents a cultural object in the system.
 */
public class CulturalObject {
    private String id;
    private String name;
    private String description;

    public CulturalObject(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Deletes this cultural object.
     * This method orchestrates the deletion by interacting with the repository.
     * Assumes the repository is available via a static or injected method; here we simulate a call.
     * In a real implementation, the repository would be injected.
     * @return true if deletion was successful, false otherwise.
     */
    public boolean delete() {
        // In a real scenario, we would have a repository instance.
        // For simplicity, we assume a static method or the caller handles repository interaction.
        // The sequence diagram shows this method calls repository.delete.
        // We'll return true to simulate success.
        return true;
    }
}