package com.example.domain;

public class FieldDefinition {
    private final String name;
    private final String type;
    private final boolean required;

    public FieldDefinition(String name, String type, boolean required) {
        this.name = name;
        this.type = type;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isRequired() {
        return required;
    }
}