package presentation;

import application.RegistrationService;
import dataaccess.NetworkConnectionException;
import shared.RegistrationRequestDTO;

import java.util.Collections;
import java.util.List;

/**
 * ViewModel for the RegistrationList. It holds the data to be displayed
 * and interacts with the Application Layer to fetch that data.
 */
public class RegistrationListViewModel {
    private List<RegistrationRequestDTO> registrationRequests;

    /**
     * Constructs a new RegistrationListViewModel.
     */
    public RegistrationListViewModel() {
        this.registrationRequests = Collections.emptyList(); // Initialize as empty
        System.out.println("[ViewModel] ViewModel initialized.");
    }

    /**
     * Loads pending registration requests using the provided RegistrationService.
     *
     * @param service The RegistrationService to use for data fetching.
     * @throws NetworkConnectionException if the underlying service encounters a network issue.
     */
    public void loadPendingRequests(RegistrationService service) throws NetworkConnectionException {
        System.out.println("[ViewModel] ViewModel uses the provided service instance to load data.");
        // Call to application service to get the data
        this.registrationRequests = service.getPendingRegistrationRequests();
        System.out.println("[ViewModel] ViewModel state updated with " + registrationRequests.size() + " requests.");
    }

    /**
     * Gets the list of registration requests held by the ViewModel.
     * @return An unmodifiable list of RegistrationRequestDTOs.
     */
    public List<RegistrationRequestDTO> getRegistrationRequests() {
        return Collections.unmodifiableList(registrationRequests);
    }
}