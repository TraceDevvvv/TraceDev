package com.etour.repository;

import com.etour.entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Tourist entity.
 * Provides CRUD operations and custom queries for tourist data access.
 * Extends JpaRepository to inherit standard Spring Data JPA functionality.
 */
@Repository
public interface TouristRepository extends JpaRepository<Tourist, Long> {
    
    /**
     * Finds a tourist by email address.
     * 
     * @param email Email address to search for
     * @return Optional containing Tourist if found
     */
    Optional<Tourist> findByEmail(String email);
    
    /**
     * Finds a tourist by passport number.
     * 
     * @param passportNumber Passport number to search for
     * @return Optional containing Tourist if found
     */
    Optional<Tourist> findByPassportNumber(String passportNumber);
    
    /**
     * Finds tourists by active status.
     * 
     * @param active Active status (true/false)
     * @return List of tourists with the specified active status
     */
    List<Tourist> findByActive(Boolean active);
    
    /**
     * Finds tourists by nationality.
     * 
     * @param nationality Nationality to search for
     * @return List of tourists with the specified nationality
     */
    List<Tourist> findByNationality(String nationality);
    
    /**
     * Searches tourists by first name (case-insensitive partial match).
     * 
     * @param firstName First name or partial first name to search for
     * @return List of matching tourists
     */
    List<Tourist> findByFirstNameContainingIgnoreCase(String firstName);
    
    /**
     * Searches tourists by last name (case-insensitive partial match).
     * 
     * @param lastName Last name or partial last name to search for
     * @return List of matching tourists
     */
    List<Tourist> findByLastNameContainingIgnoreCase(String lastName);
    
    /**
     * Searches tourists by full name (first name + last name, case-insensitive).
     * 
     * @param firstName First name or partial first name
     * @param lastName Last name or partial last name
     * @return List of matching tourists
     */
    List<Tourist> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(
            String firstName, String lastName);
    
    /**
     * Finds tourists born before a specific date.
     * 
     * @param date The cutoff date
     * @return List of tourists born before the specified date
     */
    List<Tourist> findByDateOfBirthBefore(LocalDate date);
    
    /**
     * Finds tourists born after a specific date.
     * 
     * @param date The cutoff date
     * @return List of tourists born after the specified date
     */
    List<Tourist> findByDateOfBirthAfter(LocalDate date);
    
    /**
     * Complex search query combining multiple search criteria.
     * Used by the SearchTourist use case referenced in ModifyTouristAccountData.
     * All parameters are optional - null parameters are ignored in the query.
     * 
     * @param firstName First name (partial match, case-insensitive)
     * @param lastName Last name (partial match, case-insensitive)
     * @param email Email (exact match)
     * @param nationality Nationality (exact match)
     * @param active Active status
     * @return List of matching tourists
     */
    @Query("SELECT t FROM Tourist t WHERE " +
           "(:firstName IS NULL OR LOWER(t.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) AND " +
           "(:lastName IS NULL OR LOWER(t.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) AND " +
           "(:email IS NULL OR t.email = :email) AND " +
           "(:nationality IS NULL OR t.nationality = :nationality) AND " +
           "(:active IS NULL OR t.active = :active) " +
           "ORDER BY t.lastName, t.firstName")
    List<Tourist> searchTourists(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("email") String email,
            @Param("nationality") String nationality,
            @Param("active") Boolean active);
    
    /**
     * Checks if a tourist with the given email exists (excluding a specific tourist).
     * Used for validation during updates to ensure email uniqueness.
     * 
     * @param email Email to check
     * @param id Tourist ID to exclude from the check
     * @return true if another tourist with the email exists, false otherwise
     */
    @Query("SELECT COUNT(t) > 0 FROM Tourist t WHERE t.email = :email AND t.id != :id")
    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") Long id);
    
    /**
     * Checks if a tourist with the given passport number exists (excluding a specific tourist).
     * Used for validation during updates to ensure passport number uniqueness.
     * 
     * @param passportNumber Passport number to check
     * @param id Tourist ID to exclude from the check
     * @return true if another tourist with the passport number exists, false otherwise
     */
    @Query("SELECT COUNT(t) > 0 FROM Tourist t WHERE t.passportNumber = :passportNumber AND t.id != :id")
    boolean existsByPassportNumberAndIdNot(@Param("passportNumber") String passportNumber, @Param("id") Long id);
    
    /**
     * Finds tourists created within a date range.
     * 
     * @param startDate Start date (inclusive)
     * @param endDate End date (inclusive)
     * @return List of tourists created within the date range
     */
    List<Tourist> findByCreatedAtBetween(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
    
    /**
     * Finds tourists updated within a date range.
     * 
     * @param startDate Start date (inclusive)
     * @param endDate End date (inclusive)
     * @return List of tourists updated within the date range
     */
    List<Tourist> findByUpdatedAtBetween(java.time.LocalDateTime startDate, java.time.LocalDateTime endDate);
    
    /**
     * Counts tourists by active status.
     * 
     * @param active Active status
     * @return Number of tourists with the specified active status
     */
    long countByActive(Boolean active);
    
    /**
     * Counts tourists by nationality.
     * 
     * @param nationality Nationality
     * @return Number of tourists with the specified nationality
     */
    long countByNationality(String nationality);
}