package com.example.application.port.out;

import com.example.domain.SearchCriteria;
import com.example.ui.SearchForm;
import java.util.Map;

/**
 * Port for rendering and processing search forms.
 */
public interface SearchFormRenderer {
    /**
     * Renders a search form.
     * @return the rendered search form
     */
    SearchForm render();
    
    /**
     * Processes form submission data into search criteria.
     * @param formData map of form field names to values
     * @return populated search criteria
     */
    SearchCriteria processSubmission(Map<String, String> formData);
}