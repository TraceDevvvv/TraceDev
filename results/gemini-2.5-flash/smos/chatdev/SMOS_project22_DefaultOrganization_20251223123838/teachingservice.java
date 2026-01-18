/**
 * This class simulates a service layer responsible for fetching teaching data
 * and managing a simulated connection to an SMOS server.
 * In a real application, this would interact with a database or external API.
 */
import java.util.ArrayList;
import java.util.List;
public class TeachingService {
    private boolean smosConnected = false; // Simulated connection status
    /**
     * Simulates fetching a list of teachings from an archive.
     * This method provides hardcoded data for demonstration purposes.
     *
     * @return A list of Teaching objects.
     */
    public List<Teaching> getTeachings() {
        // In a real application, this would involve database queries or API calls
        List<Teaching> teachings = new ArrayList<>();
        teachings.add(new Teaching(101, "Introduction to Java Programming", "Learn the basics of Java language and object-oriented programming."));
        teachings.add(new Teaching(102, "Advanced Data Structures", "Deep dive into complex data structures like trees, heaps, and graphs."));
        teachings.add(new Teaching(103, "Web Development with Spring Boot", "Develop RESTful APIs and web applications using the Spring Boot framework."));
        teachings.add(new Teaching(104, "Database Management Systems", "Understand relational databases, SQL, and database design principles."));
        teachings.add(new Teaching(105, "Software Testing Fundamentals", "Explore different testing methodologies and tools for quality assurance."));
        return teachings;
    }
    /**
     * Simulates connecting to the SMOS server.
     * Sets the internal flag `smosConnected` to true.
     */
    public void connectSMOS() {
        smosConnected = true;
        System.out.println("Simulated: Connected to SMOS server.");
        // In a real scenario, this would attempt a network connection.
    }
    /**
     * Simulates disconnecting from the SMOS server.
     * Sets the internal flag `smosConnected` to false.
     */
    public void disconnectSMOS() {
        smosConnected = false;
        System.out.println("Simulated: Disconnected from SMOS server. (SMOS server connection interrupted)");
        // In a real scenario, this would close network resources.
    }
    /**
     * Checks if the SMOS server is currently simulated as connected.
     * @return true if connected, false otherwise.
     */
    public boolean isSmosConnected() {
        return smosConnected;
    }
}