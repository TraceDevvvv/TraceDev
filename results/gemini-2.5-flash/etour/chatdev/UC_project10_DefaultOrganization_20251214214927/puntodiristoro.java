'''
DOCSTRING:
This class represents a "Point of Rest" (PuntoDiRistoro) data model.
It holds all the essential attributes for a restaurant or resting place.
'''
public class PuntoDiRistoro {
    private String id;
    private String name;
    private String address;
    private String description;
    private String type; // e.g., 'Restaurant', 'Cafe', 'Bar'
    private int capacity;
    private String contactPhone;
    private String website;
    /**
     * Constructor for PuntoDiRistoro.
     * @param id Unique identifier for the point of rest.
     * @param name Name of the point of rest.
     * @param address Physical address.
     * @param description A brief description.
     * @param type Category of the point of rest.
     * @param capacity Seating capacity.
     * @param contactPhone Contact telephone number.
     * @param website Official website URL.
     */
    public PuntoDiRistoro(String id, String name, String address, String description,
                          String type, int capacity, String contactPhone, String website) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.type = type;
        this.capacity = capacity;
        this.contactPhone = contactPhone;
        this.website = website;
    }
    // --- Getters for all attributes ---
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getAddress() {
        return address;
    }
    public String getDescription() {
        return description;
    }
    public int getCapacity() {
        return capacity;
    }
    public String getContactPhone() {
        return contactPhone;
    }
    public String getWebsite() {
        return website;
    }
    /**
     * Overrides toString() to provide a user-friendly representation,
     * useful for displaying in UI components like JComboBox or JList.
     * @return The name and type of the point of rest.
     */
    @Override
    public String toString() {
        return name + " (" + type + ")";
    }
}