package com.example.component;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a registration form containing fields.
 */
public class RegistrationForm {
    private Map<String, Field> fields;

    // Constructor
    public RegistrationForm() {
        fields = new HashMap<>();
        // Initialize with some default fields for demonstration
        fields.put("username", new Field("username", "text", ""));
        fields.put("email", new Field("email", "email", ""));
        fields.put("password", new Field("password", "password", ""));
        fields.put("passwordConfirmation", new Field("passwordConfirmation", "password", ""));
    }

    // Getter and Setter
    public Map<String, Field> getFields() {
        return fields;
    }

    public void setFields(Map<String, Field> fields) {
        this.fields = fields;
    }

    /**
     * Renders the form as a string representation.
     * @return a string representation of the form.
     */
    public String render() {
        StringBuilder sb = new StringBuilder();
        sb.append("Registration Form:\n");
        for (Field field : fields.values()) {
            sb.append("- ").append(field.getName()).append(" (").append(field.getType()).append("): ").append(field.getValue()).append("\n");
        }
        return sb.toString();
    }
}