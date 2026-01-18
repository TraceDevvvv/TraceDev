/**
 * Represents a Tourist Account domain entity.
 * This class holds detailed information about a tourist.
 * Traceability: Fully implemented as per class diagram, including attributes (+id, +name, +email, +nationality, +bookingCount).
 */
public class TouristAccount {
    public String id; // Unique identifier for the tourist account
    public String name; // Name of the tourist
    public String email; // Email address of the tourist
    public String nationality; // Nationality of the tourist
    public int bookingCount; // Number of bookings made by the tourist

    /**
     * Default constructor.
     */
    public TouristAccount() {
    }

    /**
     * Parameterized constructor for convenience.
     *
     * @param id The unique ID.
     * @param name The tourist's name.
     * @param email The tourist's email.
     * @param nationality The tourist's nationality.
     * @param bookingCount The number of bookings.
     */
    public TouristAccount(String id, String name, String email, String nationality, int bookingCount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nationality = nationality;
        this.bookingCount = bookingCount;
    }

    @Override
    public String toString() {
        return "TouristAccount{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", nationality='" + nationality + '\'' +
               ", bookingCount=" + bookingCount +
               '}';
    }
}