package com.example.adapter.out.web;

import com.example.application.port.out.SearchFormRenderer;
import com.example.domain.SearchCriteria;
import com.example.ui.Field;
import com.example.ui.SearchForm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Web form renderer implementation.
 * Processes web form data into search criteria.
 */
public class WebSearchFormRenderer implements SearchFormRenderer {
    
    @Override
    public SearchForm render() {
        Map<String, Field> fields = new HashMap<>();
        fields.put("title", new Field("title", "text", ""));
        fields.put("creator", new Field("creator", "text", ""));
        fields.put("objectType", new Field("objectType", "select", ""));
        fields.put("dateRangeStart", new Field("dateRangeStart", "date", ""));
        fields.put("dateRangeEnd", new Field("dateRangeEnd", "date", ""));
        
        return new SearchForm(fields);
    }
    
    @Override
    public SearchCriteria processSubmission(Map<String, String> formData) {
        SearchCriteria criteria = new SearchCriteria();
        
        if (formData.containsKey("title")) {
            criteria.setTitle(formData.get("title"));
        }
        
        if (formData.containsKey("creator")) {
            criteria.setCreator(formData.get("creator"));
        }
        
        if (formData.containsKey("objectType")) {
            criteria.setObjectType(formData.get("objectType"));
        }
        
        // Note: In a real application, date parsing would be more robust
        if (formData.containsKey("dateRangeStart") && !formData.get("dateRangeStart").isEmpty()) {
            try {
                // Simple date parsing for demonstration
                String dateStr = formData.get("dateRangeStart");
                // This is simplified - real implementation would use SimpleDateFormat or DateTimeFormatter
                criteria.setDateRangeStart(new Date()); // Placeholder
            } catch (Exception e) {
                System.out.println("Error parsing start date: " + e.getMessage());
            }
        }
        
        if (formData.containsKey("dateRangeEnd") && !formData.get("dateRangeEnd").isEmpty()) {
            try {
                // Simple date parsing for demonstration
                String dateStr = formData.get("dateRangeEnd");
                criteria.setDateRangeEnd(new Date()); // Placeholder
            } catch (Exception e) {
                System.out.println("Error parsing end date: " + e.getMessage());
            }
        }
        
        return criteria;
    }
}