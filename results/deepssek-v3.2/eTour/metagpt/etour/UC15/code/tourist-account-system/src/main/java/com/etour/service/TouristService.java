package com.etour.service;

import com.etour.dto.TouristDTO;
import com.etour.dto.TouristResponse;
import com.etour.dto.TouristUpdateRequest;
import com.etour.dto.ValidationErrorDTO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Map;

/**
 * Service interface for tourist account management.
 * Defines business logic for tourist operations including the ModifyTouristAccountData use case.
 */
public interface TouristService {
    
    /**
     * Retrieves a tourist by ID.
     * 
     * @param id Tourist ID
     * @return TouristDTO if found
     * @throws com.etour.exception.TouristNotFoundException if tourist not found
     */
    TouristDTO getTouristById(Long id);
    
    /**
     * Retrieves a tourist by email.
     * 
     * @param email Tourist email
     * @return TouristDTO if found
     * @throws com.etour.exception.TouristNotFoundException if tourist not found
     */
    TouristDTO getTouristByEmail(String email);
    
    /**
     * Searches for tourists based on criteria.
     * Used by the SearchTourist use case referenced in ModifyTouristAccountData.
     * 
     * @param firstName First name (partial match, optional)
     * @param lastName Last name (partial match, optional)
     * @param email Email (exact match, optional)
     * @param nationality Nationality (exact match, optional)
     * @param active Active status (optional)
     * @return List of matching tourists
     */
    List<TouristDTO> searchTourists(String firstName, String lastName, String email, 
                                   String nationality, Boolean active);
    
    /**
     * Updates a tourist account with the provided data.
     * Implements the core logic of the ModifyTouristAccountData use case.
     * 
     * @param id Tourist ID to update
     * @param updateRequest Update request with new values (partial update supported)
     * @param operatorUsername Username of the agency operator performing the update
     * @param request HttpServletRequest for audit logging (IP, user agent)
     * @return TouristResponse with the result of the update operation
     */
    TouristResponse updateTourist(Long id, TouristUpdateRequest updateRequest, 
                                 String operatorUsername, HttpServletRequest request);
    
    /**
     * Validates a tourist update request.
     * Checks for data validity, uniqueness constraints, and business rules.
     * 
     * @param id Tourist ID being updated
     * @param updateRequest Update request to validate
     * @return ValidationErrorDTO with validation errors if any
     */
    ValidationErrorDTO validateTouristUpdate(Long id, TouristUpdateRequest updateRequest);
    
    /**
     * Prepares a tourist update confirmation.
     * Shows the changes that will be made and asks for confirmation.
     * Step 4 in the ModifyTouristAccountData flow.
     * 
     * @param id Tourist ID being updated
     * @param updateRequest Update request
     * @return TouristResponse with confirmation details
     */
    TouristResponse prepareUpdateConfirmation(Long id, TouristUpdateRequest updateRequest);
    
    /**
     * Confirms and applies a tourist update after validation.
     * Step 5-6 in the ModifyTouristAccountData flow.
     * 
     * @param id Tourist ID being updated
     * @param updateRequest Update request
     * @param operatorUsername Username of the agency operator
     * @param request HttpServletRequest for audit logging
     * @param confirmationToken Optional confirmation token for idempotency
     * @return TouristResponse with the updated tourist data
     */
    TouristResponse confirmAndUpdateTourist(Long id, TouristUpdateRequest updateRequest,
                                          String operatorUsername, HttpServletRequest request,
                                          String confirmationToken);
    
    /**
     * Activates or deactivates a tourist account.
     * 
     * @param id Tourist ID
     * @param active New active status
     * @param operatorUsername Username of the agency operator
     * @param request HttpServletRequest for audit logging
     * @return TouristResponse with the result
     */
    TouristResponse setTouristActiveStatus(Long id, Boolean active, 
                                         String operatorUsername, HttpServletRequest request);
    
    /**
     * Gets the change history for a tourist account.
     * 
     * @param id Tourist ID
     * @param limit Maximum number of history entries to return
     * @return List of audit log summaries
     */
    List<Map<String, Object>> getTouristChangeHistory(Long id, int limit);
    
    /**
     * Creates a new tourist account.
     * 
     * @param touristDTO Tourist data
     * @param operatorUsername Username of the agency operator
     * @param request HttpServletRequest for audit logging
     * @return TouristResponse with the created tourist
     */
    TouristResponse createTourist(TouristDTO touristDTO, String operatorUsername, 
                                HttpServletRequest request);
    
    /**
     * Checks if a tourist exists by ID.
     * 
     * @param id Tourist ID
     * @return true if tourist exists, false otherwise
     */
    boolean touristExists(Long id);
    
    /**
     * Checks if email is available for a tourist (unique constraint check).
     * 
     * @param email Email to check
     * @param excludeTouristId Tourist ID to exclude from the check (for updates)
     * @return true if email is available, false if already in use
     */
    boolean isEmailAvailable(String email, Long excludeTouristId);
    
    /**
     * Checks if passport number is available for a tourist (unique constraint check).
     * 
     * @param passportNumber Passport number to check
     * @param excludeTouristId Tourist ID to exclude from the check (for updates)
     * @return true if passport number is available, false if already in use
     */
    boolean isPassportNumberAvailable(String passportNumber, Long excludeTouristId);
    
    /**
     * Gets a summary of tourist statistics.
     * 
     * @return Map with tourist counts by status, nationality, etc.
     */
    Map<String, Object> getTouristStatistics();
    
    /**
     * Searches tourists with pagination support.
     * 
     * @param firstName First name filter (optional)
     * @param lastName Last name filter (optional)
     * @param email Email filter (optional)
     * @param nationality Nationality filter (optional)
     * @param active Active status filter (optional)
     * @param page Page number (0-based)
     * @param size Page size
     * @return Map containing tourist list and pagination metadata
     */
    Map<String, Object> searchTouristsWithPagination(String firstName, String lastName, 
                                                    String email, String nationality, 
                                                    Boolean active, int page, int size);
}