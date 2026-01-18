package com.example.adapters;

import com.example.domain.ConventionDataDTO;
import com.example.ports.RefreshmentPointRequestGateway;
import java.util.Map;

/**
 * Adapter implementing RefreshmentPointRequestGateway port using an external ETOUR service client.
 */
public class RefreshmentPointRequestGatewayAdapter implements RefreshmentPointRequestGateway {

    private final ETOURServiceClient etourServiceClient;

    public RefreshmentPointRequestGatewayAdapter(ETOURServiceClient etourServiceClient) {
        this.etourServiceClient = etourServiceClient;
    }

    @Override
    public ConventionDataDTO loadRequestData(String conventionId) {
        // Delegate to external client
        return etourServiceClient.getConventionData(conventionId);
    }
}

// Assumed external service client class
class ETOURServiceClient {
    public ConventionDataDTO getConventionData(String id) {
        // Simulate fetching data from external service
        return new ConventionDataDTO("Agreement Data for " + id, Map.of("key", "value"));
    }
}