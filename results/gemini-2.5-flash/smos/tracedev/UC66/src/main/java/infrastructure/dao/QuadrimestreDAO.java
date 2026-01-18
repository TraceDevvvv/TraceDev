package infrastructure.dao;

/**
 * Data Access Object for Quadrimestre information.
 * Represents the structure of quadrimestre period data as stored in the persistence layer.
 */
public class QuadrimestreDAO {
    public String id;
    public String name;

    /**
     * Constructs a new QuadrimestreDAO.
     * @param id The unique identifier of the quadrimestre.
     * @param name The name of the quadrimestre (e.g., "Q1").
     */
    public QuadrimestreDAO(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}