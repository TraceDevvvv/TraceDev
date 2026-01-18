package application;

/**
 * Data Transfer Object (DTO) for requesting the creation of a new banner.
 * Carries all necessary information from the presentation layer to the application layer.
 */
public class BannerCreationRequestDTO {
    private String imageUrl;
    private String pointOfRestaurantId;
    private String operatorId; // Added to satisfy requirement 'Entry Conditions: Point Of Restaurant Operator IS authenticated'

    /**
     * Constructs a new BannerCreationRequestDTO.
     *
     * @param imageUrl The URL of the image for the banner.
     * @param pointOfRestaurantId The ID of the PointOfRestaurant where the banner should be inserted.
     * @param operatorId The ID of the authenticated operator making the request.
     */
    public BannerCreationRequestDTO(String imageUrl, String pointOfRestaurantId, String operatorId) {
        this.imageUrl = imageUrl;
        this.pointOfRestaurantId = pointOfRestaurantId;
        this.operatorId = operatorId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPointOfRestaurantId() {
        return pointOfRestaurantId;
    }

    public String getOperatorId() {
        return operatorId;
    }

    @Override
    public String toString() {
        return "BannerCreationRequestDTO{" +
               "imageUrl='" + imageUrl + '\'' +
               ", pointOfRestaurantId='" + pointOfRestaurantId + '\'' +
               ", operatorId='" + operatorId + '\'' +
               '}';
    }
}