import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Service layer for managing tourist account search operations.
 * It orchestrates business logic by interacting with the TouristManager.
 */
public class TouristSearchService {
    // Private field for the TouristManager dependency
    private final TouristManager touristManager;

    /**
     * Constructor for TouristSearchService, injecting the TouristManager dependency.
     *
     * @param touristManager The business logic component for tourist operations.
     */
    public TouristSearchService(TouristManager touristManager) {
        this.touristManager = touristManager;
    }

    /**
     * Finds tourist accounts based on the provided criteria.
     * This method acts as a bridge between the controller and the business logic.
     * It also handles conversion of domain models to DTOs.
     *
     * @param criteria The DTO containing search parameters.
     * @return A list of TouristAccountDTOs representing the matching accounts.
     * @throws ServiceException if a service-level error occurs,
     *                          potentially due to underlying business logic or database issues.
     */
    public List<TouristAccountDTO> findTouristAccounts(TouristSearchCriteriaDTO criteria) throws ServiceException {
        System.out.println("Service: Finding tourist accounts with criteria: " + criteria);
        try {
            // Delegate the search to the TouristManager (business logic layer)
            List<TouristAccount> touristAccounts = touristManager.searchAccounts(criteria);

            // Traceability: m22 - Converts List<TouristAccount> to List<TouristAccountDTO>.
            // Convert the list of domain models (TouristAccount) to DTOs (TouristAccountDTO)
            List<TouristAccountDTO> touristAccountDTOs = touristAccounts.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());

            System.out.println("Service: Found and converted " + touristAccountDTOs.size() + " accounts to DTOs.");
            return touristAccountDTOs;
        } catch (DatabaseConnectionException e) {
            // Catch DatabaseConnectionException from TouristManager and wrap it in a ServiceException.
            // This satisfies the sequence diagram's error propagation path (m16).
            System.err.println("Service: Caught DatabaseConnectionException, propagating as ServiceException. Message: " + e.getMessage());
            // In a real application, you might log the original exception with more detail
            throw new ServiceException("Failed to find tourist accounts due to database connection issue: " + e.getMessage(), e);
        }
    }

    /**
     * Helper method to convert a TouristAccount domain object to a TouristAccountDTO.
     *
     * @param account The TouristAccount domain object.
     * @return The corresponding TouristAccountDTO.
     */
    private TouristAccountDTO convertToDTO(TouristAccount account) {
        // Assuming TouristAccountDTO only needs id, name, and email as per class diagram
        TouristAccountDTO dto = new TouristAccountDTO();
        dto.id = account.id;
        dto.name = account.name;
        dto.email = account.email;
        return dto;
    }
}