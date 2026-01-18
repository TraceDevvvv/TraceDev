import java.util.Map;

/**
 * Data Transfer Object for refreshment point presentation.
 */
public class RefreshmentPointDTO {
    private String id;
    private String name;
    private String description;
    private String location;
    private String formattedDetails;

    public RefreshmentPointDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFormattedDetails() {
        return formattedDetails;
    }

    public void setFormattedDetails(String formattedDetails) {
        this.formattedDetails = formattedDetails;
    }

    // Static mapping method (REQ-008)
    public static RefreshmentPointDTO fromEntity(RefreshmentPoint entity) {
        RefreshmentPointDTO dto = new RefreshmentPointDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setLocation(entity.getLocation());
        // Format details map to a string
        Map<String, Object> details = entity.getDetails();
        if (details != null) {
            dto.setFormattedDetails(details.toString());
        } else {
            dto.setFormattedDetails("No additional details");
        }
        return dto;
    }
}