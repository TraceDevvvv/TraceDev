package application;

/**
 * Query object for fetching convention form data.
 * Part of CQRS pattern.
 */
public class GetConventionFormQuery {
    private final String restaurantId;

    public GetConventionFormQuery(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }
}