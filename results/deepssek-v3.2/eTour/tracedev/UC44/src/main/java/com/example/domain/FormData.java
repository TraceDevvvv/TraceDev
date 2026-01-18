package com.example.domain;

import java.util.Map;

public class FormData {
    private final Map<String, String> values;

    public FormData(Map<String, String> values) {
        this.values = values;
    }

    public String getValue(String fieldName) {
        return values.get(fieldName);
    }

    public ConventionData toConventionData() {
        // This is a simplified conversion; real implementation would parse dates, build ContactDetails, etc.
        String agreementTerms = values.get("agreementTerms");
        // Assume other fields are parsed appropriately
        return new ConventionData(agreementTerms, null, null, null);
    }
}