package presentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Transfer Object for tag form input.
 */
public class TagFormDTO {
    private String name;
    private String description;
    private List<String> requiredFields;

    public TagFormDTO() {
        this.requiredFields = new ArrayList<>();
        requiredFields.add("name");
        requiredFields.add("description");
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

    public List<String> getRequiredFields() {
        return requiredFields;
    }

    public void setRequiredFields(List<String> requiredFields) {
        this.requiredFields = requiredFields;
    }

    /**
     * Validates that required fields are present.
     */
    public boolean validate() {
        for (String field : requiredFields) {
            if ("name".equals(field) && (name == null || name.trim().isEmpty())) {
                return false;
            }
            if ("description".equals(field) && (description == null || description.trim().isEmpty())) {
                return false;
            }
        }
        return true;
    }
}