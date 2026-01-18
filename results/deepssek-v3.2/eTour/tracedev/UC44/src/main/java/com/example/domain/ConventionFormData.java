package com.example.domain;

import java.util.List;
import java.util.Map;

public class ConventionFormData {
    private final Map<String, FieldDefinition> fields;

    public ConventionFormData(Map<String, FieldDefinition> fields) {
        this.fields = fields;
    }

    public ConventionFormData() {
        this.fields = Map.of();
    }

    public List<FieldDefinition> getFieldDefinitions() {
        return List.copyOf(fields.values());
    }
}