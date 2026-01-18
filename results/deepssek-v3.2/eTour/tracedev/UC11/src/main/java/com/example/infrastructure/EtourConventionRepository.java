package com.example.infrastructure;

import com.example.domain.Convention;
import com.example.domain.PointOfRest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository implementation that fetches data from an external eTour server.
 */
public class EtourConventionRepository implements ConventionRepository {

    private final EtourServerClient etourClient;

    public EtourConventionRepository(EtourServerClient etourClient) {
        this.etourClient = etourClient;
    }

    @Override
    public List<Convention> findByPointOfRestId(String pointOfRestId) {
        try {
            List<ConventionData> dataList = etourClient.fetchConventionsForPoint(pointOfRestId);
            // Convert ConventionData to Domain Convention (resolving data transformation inconsistency)
            return dataList.stream()
                    .map(this::convertToConvention)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            // In case of connection interruption, return empty list as per sequence diagram
            return List.of();
        }
    }

    /**
     * Converts external data format to domain entity.
     */
    private Convention convertToConvention(ConventionData data) {
        // Assuming point of rest details are fetched elsewhere; here we create a placeholder.
        PointOfRest point = new PointOfRest(data.getPointId(), "Unknown", "Unknown");
        LocalDateTime timestamp = LocalDateTime.parse(data.getTimestamp(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return new Convention(data.getId(), data.getDetails(), point, timestamp);
    }
}