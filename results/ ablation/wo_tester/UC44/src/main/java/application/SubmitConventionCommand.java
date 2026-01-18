package application;

import java.util.Map;

/**
 * Command object for submitting a convention.
 * Part of CQRS pattern.
 */
public class SubmitConventionCommand {
    private final String restaurantId;
    private final String agencyId;
    private final Map<String, Object> conventionData;

    public SubmitConventionCommand(String restaurantId, String agencyId, Map<String, Object> conventionData) {
        this.restaurantId = restaurantId;
        this.agencyId = agencyId;
        this.conventionData = conventionData;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public Map<String, Object> getConventionData() {
        return conventionData;
    }
}