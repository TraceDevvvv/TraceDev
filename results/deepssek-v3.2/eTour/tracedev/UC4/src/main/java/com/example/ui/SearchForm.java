package com.example.ui;

import java.util.Map;

/**
 * Represents a search form with fields.
 */
public class SearchForm {
    private Map<String, Field> fields;
    
    public SearchForm(Map<String, Field> fields) {
        this.fields = fields;
    }
    
    public Map<String, Field> getFields() {
        return fields;
    }
    
    public void setFieldValue(String name, String value) {
        if (fields.containsKey(name)) {
            fields.get(name).setValue(value);
        }
    }
    
    /**
     * Validates the form data.
     * @return true if form is valid
     */
    public boolean validate() {
        // Basic validation - check that required fields are filled
        // For this example, all fields are optional
        return true;
    }
}