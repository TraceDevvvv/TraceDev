package com.culturalheritage;

import java.time.Year;

/**
 * Represents a search form for cultural heritage objects.
 * This class captures the search criteria entered by the user.
 */
public class SearchForm {
    private String name;        // Name or part of the name to search for
    private String type;        // Type/category of cultural object
    private String era;         // Historical era/period
    private String location;    // Location to search in
    private String countryOfOrigin; // Country of origin
    private Year minYear;       // Minimum creation year
    private Year maxYear;       // Maximum creation year
    private Boolean isProtected; // Whether to search for protected items only
    
    /**
     * Default constructor for creating an empty search form.
     */
    public SearchForm() {
        // Empty form - all fields remain null
    }
    
    /**
     * Constructor for creating a search form with basic criteria.
     * 
     * @param name name or part of name to search for
     * @param type type/category of cultural object
     * @param era historical era/period
     * @param location location to search in
     */
    public SearchForm(String name, String type, String era, String location) {
        this.name = name;
        this.type = type;
        this.era = era;
        this.location = location;
    }
    
    /**
     * Constructor for creating a search form with detailed criteria.
     * 
     * @param name name or part of name to search for
     * @param type type/category of cultural object
     * @param era historical era/period
     * @param location location to search in
     * @param countryOfOrigin country of origin
     * @param minYear minimum creation year
     * @param maxYear maximum creation year
     * @param isProtected whether to search for protected items only
     */
    public SearchForm(String name, String type, String era, String location,
                     String countryOfOrigin, Year minYear, Year maxYear,
                     Boolean isProtected) {
        this.name = name;
        this.type = type;
        this.era = era;
        this.location = location;
        this.countryOfOrigin = countryOfOrigin;
        this.minYear = minYear;
        this.maxYear = maxYear;
        this.isProtected = isProtected;
    }
    
    // Getters and Setters
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getEra() {
        return era;
    }
    
    public void setEra(String era) {
        this.era = era;
    }
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }
    
    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
    
    public Year getMinYear() {
        return minYear;
    }
    
    public void setMinYear(Year minYear) {
        this.minYear = minYear;
    }
    
    public Year getMaxYear() {
        return maxYear;
    }
    
    public void setMaxYear(Year maxYear) {
        this.maxYear = maxYear;
    }
    
    public Boolean getIsProtected() {
        return isProtected;
    }
    
    public void setIsProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }
    
    /**
     * Checks if this search form is empty (has no search criteria).
     * 
     * @return true if all search fields are null or empty
     */
    public boolean isEmpty() {
        return (name == null || name.isEmpty()) &&
               (type == null || type.isEmpty()) &&
               (era == null || era.isEmpty()) &&
               (location == null || location.isEmpty()) &&
               (countryOfOrigin == null || countryOfOrigin.isEmpty()) &&
               minYear == null &&
               maxYear == null &&
               isProtected == null;
    }
    
    /**
     * Validates the search form for logical consistency.
     * For example, ensures minYear is not greater than maxYear if both are provided.
     * 
     * @return true if the form is valid, false otherwise
     */
    public boolean isValid() {
        // Check if minYear is not greater than maxYear when both are provided
        if (minYear != null && maxYear != null) {
            if (minYear.isAfter(maxYear)) {
                return false;
            }
        }
        
        // All other validations passed
        return true;
    }
    
    /**
     * Gets a summary of the search criteria for display purposes.
     * 
     * @return a string summary of the search criteria
     */
    public String getCriteriaSummary() {
        StringBuilder summary = new StringBuilder("Search Criteria: ");
        boolean hasCriteria = false;
        
        if (name != null && !name.isEmpty()) {
            summary.append("Name contains '").append(name).append("' ");
            hasCriteria = true;
        }
        
        if (type != null && !type.isEmpty()) {
            summary.append("Type = '").append(type).append("' ");
            hasCriteria = true;
        }
        
        if (era != null && !era.isEmpty()) {
            summary.append("Era = '").append(era).append("' ");
            hasCriteria = true;
        }
        
        if (location != null && !location.isEmpty()) {
            summary.append("Location contains '").append(location).append("' ");
            hasCriteria = true;
        }
        
        if (countryOfOrigin != null && !countryOfOrigin.isEmpty()) {
            summary.append("Country = '").append(countryOfOrigin).append("' ");
            hasCriteria = true;
        }
        
        if (minYear != null) {
            summary.append("From Year ").append(minYear).append(" ");
            hasCriteria = true;
        }
        
        if (maxYear != null) {
            summary.append("To Year ").append(maxYear).append(" ");
            hasCriteria = true;
        }
        
        if (isProtected != null) {
            summary.append("Protected = ").append(isProtected ? "Yes" : "No").append(" ");
            hasCriteria = true;
        }
        
        if (!hasCriteria) {
            summary.append("(No criteria specified - will return all items)");
        }
        
        return summary.toString();
    }
    
    /**
     * Creates a search form from command line arguments (for testing/demo purposes).
     * This method parses command line arguments into a SearchForm object.
     * 
     * @param args command line arguments
     * @return SearchForm populated with parsed criteria
     */
    public static SearchForm fromCommandLineArgs(String[] args) {
        SearchForm form = new SearchForm();
        
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--name":
                    if (i + 1 < args.length) form.setName(args[++i]);
                    break;
                case "--type":
                    if (i + 1 < args.length) form.setType(args[++i]);
                    break;
                case "--era":
                    if (i + 1 < args.length) form.setEra(args[++i]);
                    break;
                case "--location":
                    if (i + 1 < args.length) form.setLocation(args[++i]);
                    break;
                case "--country":
                    if (i + 1 < args.length) form.setCountryOfOrigin(args[++i]);
                    break;
                case "--minYear":
                    if (i + 1 < args.length) {
                        try {
                            form.setMinYear(Year.of(Integer.parseInt(args[++i])));
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid year format: " + args[i]);
                        }
                    }
                    break;
                case "--maxYear":
                    if (i + 1 < args.length) {
                        try {
                            form.setMaxYear(Year.of(Integer.parseInt(args[++i])));
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid year format: " + args[i]);
                        }
                    }
                    break;
                case "--protected":
                    if (i + 1 < args.length) {
                        form.setIsProtected(Boolean.parseBoolean(args[++i]));
                    }
                    break;
            }
        }
        
        return form;
    }
}