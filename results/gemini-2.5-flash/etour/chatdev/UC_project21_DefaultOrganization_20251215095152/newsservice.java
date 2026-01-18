import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Random;
/**
 * `NewsService` acts as the business logic layer for handling news operations.
 * It simulates data storage and potential external system interactions like
 * connection interruptions.
 */
public class NewsService {
    // Defines the probability (as a decimal) of a simulated connection error occurring.
    private static final double CONNECTION_ERROR_RATE = 0.2; // 20% chance of a simulated connection error
    /**
     * Simulates the process of storing a new news article in the system.
     * This method includes server-side data validation and a simulation of
     * a connection interruption to an external server (ETOUR).
     *
     * @param news The `News` object containing the data to be stored.
     * @throws ValidationException If any critical news field is empty or `null`,
     *                             representing invalid or insufficient data, or if the date format is invalid.
     * @throws RuntimeException    If a simulated interruption of the connection
     *                             to the server (ETOUR) occurs.
     */
    public void saveNews(News news) throws ValidationException {
        // Step 4: Verify the data entered. This is a server-side validation,
        // complementing any client-side checks to ensure data integrity.
        if (news == null || news.getTitle() == null || news.getTitle().trim().isEmpty() ||
            news.getContent() == null || news.getContent().trim().isEmpty() ||
            news.getAuthor() == null || news.getAuthor().trim().isEmpty() ||
            news.getPublicationDate() == null || news.getPublicationDate().trim().isEmpty()) {
            throw new ValidationException("All news fields must be filled out for storage.");
        }
        // Server-side validation for publication date format and validity.
        // It's good practice for the service layer to re-validate critical data,
        // even if client-side validation is also present.
        try {
            // Attempt to parse the date string. This validates both the format (YYYY-MM-DD)
            // and the chronological validity of the date (e.g., no 'Feb 30').
            LocalDate.parse(news.getPublicationDate());
        } catch (DateTimeParseException e) {
            // Catches errors if the date string is not in YYYY-MM-DD format or is not a valid date.
            throw new ValidationException("Invalid Publication Date format or value. Please use YYYY-MM-DD and ensure it's a valid calendar date.");
        }
        // Simulate a potential connection interruption to the server, as per ETOUR exit condition.
        Random random = new Random();
        if (random.nextDouble() < CONNECTION_ERROR_RATE) {
            throw new RuntimeException("Connection to server ETOUR interrupted. Please try again.");
        }
        // Step 6: Stores the data of the new news.
        // In a production environment, this would involve integrating with a database,
        // a file system, or calling an external API.
        // For this example, data storage is simulated by printing to the console.
        System.out.println("--- SYSTEM: Attempting to store new news ---");
        System.out.println(news.toString());
        System.out.println("--- SYSTEM: News stored successfully (simulated) ---");
        // Notification of proper placement is handled by the calling UI component.
    }
}