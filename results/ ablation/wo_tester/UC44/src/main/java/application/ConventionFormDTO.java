package application;

import java.util.List;
import java.util.Map;

/**
 * Data Transfer Object containing all data needed to render the convention form.
 */
public class ConventionFormDTO {
    private final Map<String, Object> restaurantDetails;
    private final List<AgencyDTO> agencyOptions;
    private final Map<String, Object> formSchema;

    public ConventionFormDTO(Map<String, Object> restaurantDetails, List<AgencyDTO> agencyOptions, Map<String, Object> formSchema) {
        this.restaurantDetails = restaurantDetails;
        this.agencyOptions = agencyOptions;
        this.formSchema = formSchema;
    }

    public Map<String, Object> getRestaurantDetails() {
        return restaurantDetails;
    }

    public List<AgencyDTO> getAgencyOptions() {
        return agencyOptions;
    }

    public Map<String, Object> getFormSchema() {
        return formSchema;
    }
}