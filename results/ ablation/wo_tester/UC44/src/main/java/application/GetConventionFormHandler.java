package application;

import infrastructure.AgencyServiceClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Query handler for fetching convention form data.
 * Fetches restaurant details, agency options, and form schema.
 */
public class GetConventionFormHandler {
    private final AgencyServiceClient agencyServiceClient;

    public GetConventionFormHandler(AgencyServiceClient agencyServiceClient) {
        this.agencyServiceClient = agencyServiceClient;
    }

    public ConventionFormDTO handle(GetConventionFormQuery query) {
        // In a real application, we might fetch restaurant details from a restaurant service
        Map<String, Object> restaurantDetails = new HashMap<>();
        restaurantDetails.put("id", query.getRestaurantId());
        restaurantDetails.put("name", "Restaurant " + query.getRestaurantId()); // Placeholder

        // Fetch agency options from external service
        List<AgencyDTO> agencyOptions = agencyServiceClient.getAgencies();

        // Form schema (could be loaded from configuration)
        Map<String, Object> formSchema = new HashMap<>();
        formSchema.put("fields", new ArrayList<>()); // Placeholder for actual schema

        return new ConventionFormDTO(restaurantDetails, agencyOptions, formSchema);
    }
}