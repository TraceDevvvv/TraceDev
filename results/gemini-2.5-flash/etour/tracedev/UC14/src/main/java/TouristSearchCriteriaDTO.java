/**
 * Data Transfer Object (DTO) for carrying tourist search criteria.
 * Used to encapsulate parameters for searching tourist accounts.
 */
public class TouristSearchCriteriaDTO {
    public String name; // Search by tourist name (can be partial)
    public String nationality; // Search by tourist nationality
    public int minBookingCount; // Search for tourists with at least this many bookings

    /**
     * Default constructor.
     */
    public TouristSearchCriteriaDTO() {
        // Initialize with default values if needed, e.g., minBookingCount = 0
        this.minBookingCount = 0;
    }

    @Override
    public String toString() {
        return "TouristSearchCriteriaDTO{" +
               "name='" + name + '\'' +
               ", nationality='" + nationality + '\'' +
               ", minBookingCount=" + minBookingCount +
               '}';
    }
}