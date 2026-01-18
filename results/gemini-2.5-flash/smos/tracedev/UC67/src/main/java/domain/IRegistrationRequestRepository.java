package domain;

/**
 * Infrastructure Layer Interface: Defines the contract for data access operations
 * related to RegistrationRequest objects.
 * This ensures loose coupling between the application layer and the specific persistence mechanism.
 */
public interface IRegistrationRequestRepository {

    /**
     * Saves a RegistrationRequest object to the persistent storage.
     *
     * @param request The RegistrationRequest to be saved.
     */
    void save(RegistrationRequest request);

    /**
     * Finds a RegistrationRequest by its unique username.
     *
     * @param username The username to search for.
     * @return The found RegistrationRequest, or null if not found.
     */
    RegistrationRequest findByUsername(String username);
}