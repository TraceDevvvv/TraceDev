package application;

import dataaccess.IRegistrationRepository;
import dataaccess.NetworkConnectionException;
import domain.RegistrationRequest;
import shared.RegistrationRequestDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Application service responsible for business logic related to registration requests.
 * It orchestrates data access and domain object transformations.
 */
public class RegistrationService {
    private IRegistrationRepository registrationRepository;

    /**
     * Constructs a new RegistrationService with the given repository.
     * @param registrationRepository The data access repository for registration requests.
     */
    public RegistrationService(IRegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    /**
     * Retrieves a list of pending registration requests.
     * Converts domain entities to DTOs before returning them.
     *
     * @return A list of RegistrationRequestDTOs representing pending requests.
     * @throws NetworkConnectionException if there's an issue connecting to the data source.
     */
    public List<RegistrationRequestDTO> getPendingRegistrationRequests() throws NetworkConnectionException {
        System.out.println("[Service] Getting pending registration requests.");
        List<RegistrationRequest> domainRequests = registrationRepository.findPendingRequests();
        List<RegistrationRequestDTO> dtos = new ArrayList<>();

        System.out.println("[Service] Converting domain entities to DTOs.");
        for (RegistrationRequest request : domainRequests) {
            // Only convert pending requests as per the sequence diagram implied filter
            if ("pending".equalsIgnoreCase(request.getStatus())) {
                dtos.add(convertToDTO(request));
            }
        }
        return dtos;
    }

    /**
     * Converts a domain-layer RegistrationRequest object to a shared-layer RegistrationRequestDTO.
     * @param request The domain RegistrationRequest object.
     * @return The corresponding RegistrationRequestDTO.
     */
    private RegistrationRequestDTO convertToDTO(RegistrationRequest request) {
        return new RegistrationRequestDTO(
            request.getRequestId(),
            request.getStudentName(),
            request.getSubmissionDate()
        );
    }
}