
package com.example.external;

import com.example.dto.AddressDetailDTO;
import java.time.LocalDateTime;

/**
 * External SMOS server simulation.
 * Represents an external system that provides address details.
 */
public class SMOSServer {
    /**
     * Fetches address details from the SMOS server.
     * Sequence: Called by AddressRepository.fetchFromSMOSServer.
     * 
     * This method simulates a network call. For demonstration, it returns a mock DTO.
     * In a real system, this would be a remote call (e.g., HTTP request).
     */
    public AddressDetailDTO fetchAddressData(Long id) {
        // Simulate network delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Connection interrupted", e);
        }

        // Simulate connection error randomly (for demonstration)
        if (Math.random() < 0.2) { // 20% chance of connection error
            throw new RuntimeException("SMOS server connection error");
        }

        // Return mock address data
        return new AddressDetailDTO(
                id,
                "Sample Address " + id,
                "123 Main St",
                "Sample City",
                "Sample Country",
                "12345",
                LocalDateTime.now().minusDays(30),
                LocalDateTime.now()
        );
    }

    // Added method to represent the return message m6: returns address data
    // This is already covered by fetchAddressData method, but added for traceability.
    public AddressDetailDTO returnsAddressData(Long addressId) {
        return fetchAddressData(addressId);
    }

    // Added method to represent the return message m13: connection error
    // This is simulated by throwing a RuntimeException in fetchAddressData.
    // Added a method that explicitly throws a connection error for traceability.
    public void connectionError() {
        throw new RuntimeException("SMOS server connection error");
    }
}
