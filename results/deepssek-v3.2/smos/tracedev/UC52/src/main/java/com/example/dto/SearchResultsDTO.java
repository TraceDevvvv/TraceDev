package com.example.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Data Transfer Object for consolidated search results.
 * Contains separate lists for each entity type and total count.
 */
public class SearchResultsDTO {
    private List<SearchResultDTO> classes = new ArrayList<>();
    private List<SearchResultDTO> teachings = new ArrayList<>();
    private List<SearchResultDTO> addresses = new ArrayList<>();
    private List<SearchResultDTO> users = new ArrayList<>();
    private Integer totalResults = 0;

    /**
     * Adds a search result to the appropriate list based on entity type.
     * Increments total results count.
     * @param result the SearchResultDTO to add.
     */
    public void addResult(SearchResultDTO result) {
        if (result == null) return;
        
        switch (result.getEntityType()) {
            case "Class":
                classes.add(result);
                break;
            case "Teaching":
                teachings.add(result);
                break;
            case "Address":
                addresses.add(result);
                break;
            case "User":
                users.add(result);
                break;
            default:
                // If type unknown, add to classes as default (or could throw exception)
                classes.add(result);
        }
        totalResults++;
    }

    /**
     * Gets all search results consolidated into a single list.
     * @return combined list of all results.
     */
    public List<SearchResultDTO> getAllResults() {
        List<SearchResultDTO> allResults = new ArrayList<>();
        allResults.addAll(classes);
        allResults.addAll(teachings);
        allResults.addAll(addresses);
        allResults.addAll(users);
        return allResults;
    }

    // Getters and Setters
    public List<SearchResultDTO> getClasses() {
        return classes;
    }

    public void setClasses(List<SearchResultDTO> classes) {
        this.classes = classes != null ? classes : new ArrayList<>();
        recalculateTotal();
    }

    public List<SearchResultDTO> getTeachings() {
        return teachings;
    }

    public void setTeachings(List<SearchResultDTO> teachings) {
        this.teachings = teachings != null ? teachings : new ArrayList<>();
        recalculateTotal();
    }

    public List<SearchResultDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<SearchResultDTO> addresses) {
        this.addresses = addresses != null ? addresses : new ArrayList<>();
        recalculateTotal();
    }

    public List<SearchResultDTO> getUsers() {
        return users;
    }

    public void setUsers(List<SearchResultDTO> users) {
        this.users = users != null ? users : new ArrayList<>();
        recalculateTotal();
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Recalculates the total results count from all lists.
     */
    private void recalculateTotal() {
        totalResults = classes.size() + teachings.size() + addresses.size() + users.size();
    }
}