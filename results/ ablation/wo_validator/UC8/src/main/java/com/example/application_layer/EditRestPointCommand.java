package com.example.application_layer;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Application Layer: Command object capturing user input for editing a rest point.
 * Follows the Command pattern.
 */
public class EditRestPointCommand {
    public String restPointId;
    public String name;
    public String location;
    public int capacity;
    public List<String> amenities;
    public String status;

    /**
     * Returns a map representation of fields for validation.
     * @return Map of field names to values.
     */
    public Map<String, Object> getFields() {
        Map<String, Object> fields = new HashMap<>();
        fields.put("restPointId", restPointId);
        fields.put("name", name);
        fields.put("location", location);
        fields.put("capacity", capacity);
        fields.put("amenities", amenities);
        fields.put("status", status);
        return fields;
    }
}