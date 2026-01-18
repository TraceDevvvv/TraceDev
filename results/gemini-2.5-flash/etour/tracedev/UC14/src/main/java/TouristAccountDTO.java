/**
 * Data Transfer Object (DTO) for representing a subset of TouristAccount information.
 * Used for displaying search results to avoid exposing full entity details.
 */
public class TouristAccountDTO {
    public String id; // Unique identifier
    public String name; // Tourist's name
    public String email; // Tourist's email

    /**
     * Default constructor.
     */
    public TouristAccountDTO() {
    }

    /**
     * Parameterized constructor for convenience.
     *
     * @param id The unique ID.
     * @param name The tourist's name.
     * @param email The tourist's email.
     */
    public TouristAccountDTO(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "TouristAccountDTO{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}