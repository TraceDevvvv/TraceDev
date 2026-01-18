package dataaccess;

import domain.RegistrationRequest;
import java.util.List;

/**
 * Interface for data access operations related to RegistrationRequest entities.
 */
public interface IRegistrationRepository {

    /**
     * Finds and returns a list of pending registration requests.
     *
     * @return A list of RegistrationRequest objects with 'pending' status.
     * @throws NetworkConnectionException if there's an issue connecting to the data source.
     */
    List<RegistrationRequest> findPendingRequests() throws NetworkConnectionException;
}