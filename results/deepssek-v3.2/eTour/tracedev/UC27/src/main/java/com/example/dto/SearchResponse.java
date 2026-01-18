package com.example.dto;

import com.example.dto.SiteDTO;
import java.util.List;

/**
 * Data transfer object for search response.
 * Contains the result of the search operation.
 */
public class SearchResponse {
    private List<SiteDTO> sites;
    private boolean isSuccessful;
    private String errorMessage;

    /**
     * Default constructor.
     */
    public SearchResponse() {}

    /**
     * Constructs a SearchResponse with the specified parameters.
     * @param sites the list of site DTOs
     * @param isSuccessful true if the operation was successful
     * @param errorMessage the error message if operation failed
     */
    public SearchResponse(List<SiteDTO> sites, boolean isSuccessful, String errorMessage) {
        this.sites = sites;
        this.isSuccessful = isSuccessful;
        this.errorMessage = errorMessage;
    }

    /**
     * Gets the list of site DTOs.
     * @return the list of site DTOs
     */
    public List<SiteDTO> getSites() {
        return sites;
    }

    /**
     * Sets the list of site DTOs.
     * @param sites the list of site DTOs
     */
    public void setSites(List<SiteDTO> sites) {
        this.sites = sites;
    }

    /**
     * Checks if the operation was successful.
     * @return true if successful
     */
    public boolean isSuccessful() {
        return isSuccessful;
    }

    /**
     * Sets the success status.
     * @param successful true if successful
     */
    public void setSuccessful(boolean successful) {
        isSuccessful = successful;
    }

    /**
     * Gets the error message.
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     * @param errorMessage the error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}