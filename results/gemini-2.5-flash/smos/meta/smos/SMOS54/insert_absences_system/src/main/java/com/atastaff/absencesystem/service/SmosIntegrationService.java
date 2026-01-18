package com.atastaff.absencesystem.service;

import com.atastaff.absencesystem.model.Absence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode; // Changed from HttpStatus to HttpStatusCode
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Service class for integrating with the external SMOS (Student Management and Operations System) server.
 * This service is responsible for sending absence data to the SMOS server.
 * It uses Spring's WebClient for making HTTP requests.
 */
@Service
public class SmosIntegrationService {

    private static final Logger logger = LoggerFactory.getLogger(SmosIntegrationService.class);

    private final WebClient webClient;

    @Value("${smos.api.absences.endpoint}")
    private String smosAbsencesEndpoint;

    /**
     * Constructor for SmosIntegrationService, injecting WebClient.Builder.
     *
     * @param webClientBuilder A builder for creating WebClient instances.
     */
    public SmosIntegrationService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    /**
     * Sends a list of absence records to the external SMOS server.
     * This method simulates an HTTP POST request to the SMOS API.
     *
     * @param absences A list of Absence entities to be sent to SMOS.
     * @return true if the data was successfully sent and acknowledged by SMOS, false otherwise.
     */
    public boolean sendAbsenceDataToSmos(List<Absence> absences) {
        if (absences == null || absences.isEmpty()) {
            logger.warn("No absences provided to send to SMOS server.");
            return true; // Consider it successful if there's nothing to send
        }

        logger.info("Attempting to send {} absence records to SMOS server at endpoint: {}", absences.size(), smosAbsencesEndpoint);

        try {
            // Simulate sending data to SMOS. In a real scenario, 'absences' would be mapped to a DTO
            // specific to the SMOS API requirements. For this simulation, we'll just send the list.
            // We're using block() here for simplicity in a synchronous service method.
            // In a reactive application, this would typically return a Mono<Boolean>.
            HttpStatusCode status = webClient.post() // Changed type to HttpStatusCode
                    .uri(smosAbsencesEndpoint)
                    .body(Mono.just(absences), List.class) // Assuming SMOS accepts a list of Absence objects or a similar DTO
                    .retrieve()
                    .toBodilessEntity() // We don't care about the response body, just the status
                    .map(response -> response.getStatusCode())
                    .block(); // Block until the response is received

            if (status != null && status.is2xxSuccessful()) {
                logger.info("Successfully sent {} absence records to SMOS server. Response status: {}", absences.size(), status);
                return true;
            } else {
                logger.error("Failed to send absence records to SMOS server. Unexpected status: {}", status);
                return false;
            }
        } catch (WebClientResponseException e) {
            logger.error("SMOS server responded with an error: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            return false;
        } catch (Exception e) {
            logger.error("Error communicating with SMOS server: {}", e.getMessage(), e);
            return false;
        }
    }
}