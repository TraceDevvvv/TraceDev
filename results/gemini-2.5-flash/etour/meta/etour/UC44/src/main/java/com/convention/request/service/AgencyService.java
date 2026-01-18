package com.convention.request.service;

import com.convention.request.exception.AgencyCommunicationException;
import com.convention.request.model.AgencyResponseDTO;
import com.convention.request.model.Convention;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * Service class responsible for communicating with the external agency system (ETOUR).
 * It handles sending convention requests to the agency and processing their responses.
 */
@Service
@Slf4j
public class AgencyService {

    @Value("${agency.api.url}")
    private String agencyApiUrl;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    /**
     * Constructor for AgencyService.
     * Initializes HttpClient with a timeout and ObjectMapper for JSON serialization/deserialization.
     */
    public AgencyService() {
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofSeconds(10)) // Connection timeout
                .build();
        this.objectMapper = new ObjectMapper();
        // Configure ObjectMapper if needed, e.g., for date formats
        // objectMapper.registerModule(new JavaTimeModule());
        // objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Sends a convention request to the external agency (ETOUR).
     * This method serializes the Convention object into JSON and sends it via HTTP POST.
     *
     * @param convention The Convention object to be sent to the agency.
     * @return An AgencyResponseDTO containing the agency's response.
     * @throws AgencyCommunicationException if there is an error during communication with the agency.
     */
    public AgencyResponseDTO sendRequest(Convention convention) {
        try {
            // Convert Convention object to JSON string
            String requestBody = objectMapper.writeValueAsString(convention);
            log.info("Sending convention request to agency: {}", requestBody);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(agencyApiUrl + "/conventions")) // Assuming an endpoint like /conventions
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .timeout(Duration.ofSeconds(15)) // Request timeout
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            log.info("Received response from agency. Status Code: {}, Body: {}", response.statusCode(), response.body());

            // Handle different HTTP status codes
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                // Successfully received a response
                return objectMapper.readValue(response.body(), AgencyResponseDTO.class);
            } else {
                // Agency returned an error status
                String errorMessage = String.format("Agency returned an error status: %d - %s", response.statusCode(), response.body());
                log.error(errorMessage);
                throw new AgencyCommunicationException(errorMessage);
            }

        } catch (IOException | InterruptedException e) {
            log.error("Failed to communicate with agency system at {}: {}", agencyApiUrl, e.getMessage(), e);
            Thread.currentThread().interrupt(); // Restore the interrupted status
            throw new AgencyCommunicationException("Failed to communicate with external agency system.", e);
        } catch (Exception e) {
            log.error("An unexpected error occurred while sending request to agency: {}", e.getMessage(), e);
            throw new AgencyCommunicationException("An unexpected error occurred during agency communication.", e);
        }
    }

    /**
     * Handles the response received from the external agency.
     * This method can be extended to implement specific logic based on the agency's response,
     * such as logging, triggering further actions, or updating internal states.
     *
     * @param response The AgencyResponseDTO received from the agency.
     */
    public void handleAgencyResponse(AgencyResponseDTO response) {
        log.info("Handling agency response for convention ID {}. Status: {}, Message: {}",
                response.getConventionId(), response.getStatus(), response.getMessage());

        // Example: Log different response statuses
        switch (response.getStatus()) {
            case "ACCEPTED":
                log.info("Convention {} was accepted by the agency.", response.getConventionId());
                // Potentially update convention status in DB to APPROVED here or in calling service
                break;
            case "REJECTED":
                log.warn("Convention {} was rejected by the agency. Reason: {}", response.getConventionId(), response.getMessage());
                // Potentially update convention status in DB to REJECTED
                break;
            case "PENDING":
                log.info("Convention {} is still pending with the agency.", response.getConventionId());
                break;
            default:
                log.warn("Unknown agency response status for convention {}: {}", response.getConventionId(), response.getStatus());
                break;
        }
        // In a real application, this might involve more complex business logic,
        // such as updating the Convention entity's status in the database,
        // sending notifications, etc. For this design, the ConventionService
        // will primarily handle the status updates based on this response.
    }
}