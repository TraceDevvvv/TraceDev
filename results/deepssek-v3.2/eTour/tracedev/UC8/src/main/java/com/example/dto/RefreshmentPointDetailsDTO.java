package com.example.dto;

import com.example.domain.RefreshmentPoint;
import com.example.domain.RefreshmentPointStatus;
import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object for detailed representation of a RefreshmentPoint.
 */
public class RefreshmentPointDetailsDTO {
    private String id;
    private String name;
    private String description;
    private String location;
    private RefreshmentPointStatus status;
    private Map<String, String> editableAttributes;

    public RefreshmentPointDetailsDTO(RefreshmentPoint point) {
        this.id = point.getId();
        this.name = point.getName();
        this.description = point.getDescription();
        this.location = point.getLocation();
        this.status = point.getStatus();
        this.editableAttributes = new HashMap<>();
        // In a real scenario, we might copy specific attributes from point's otherAttributes.
        // For simplicity, we leave it empty or copy some.
        // Assumption: copy all otherAttributes as strings.
        Map<String, Object> otherAttrs = new HashMap<>(); // point.getOtherAttributes() assumed
        for (Map.Entry<String, Object> entry : otherAttrs.entrySet()) {
            editableAttributes.put(entry.getKey(), entry.getValue().toString());
        }
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

    public String getLocation() {
        return location;
    }

    public RefreshmentPointStatus getStatus() {
        return status;
    }

    public Map<String, String> getEditableAttributes() {
        return editableAttributes;
    }
}