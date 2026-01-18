package infrastructure;

import application.AgencyDTO;

import java.util.Arrays;
import java.util.List;

/**
 * Client for communicating with external Agency Service.
 * For simplicity, we simulate HTTP calls.
 */
public class AgencyServiceClient {
    private HttpClient httpClient;

    public AgencyServiceClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Simulates fetching agencies from external service.
     * @return list of AgencyDTO
     */
    public List<AgencyDTO> getAgencies() {
        // Simulate network call; in real app would make HTTP GET /api/agencies
        // For simulation, return some dummy data.
        return Arrays.asList(
                new AgencyDTO("1", "Agency Alpha"),
                new AgencyDTO("2", "Agency Beta"),
                new AgencyDTO("3", "Agency Gamma")
        );
    }

    /**
     * Simulates submitting a convention to agency system.
     * @param conventionId the convention id
     * @return true if successful
     */
    public boolean submitConvention(String conventionId) {
        // Simulate submission
        return true;
    }
}