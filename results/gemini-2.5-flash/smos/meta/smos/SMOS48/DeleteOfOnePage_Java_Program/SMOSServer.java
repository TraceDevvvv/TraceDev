import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Simulates a connection to the SMOS (Student Management and Operations System) server.
 * This class manages a collection of ReportCard objects and provides methods
 * for connecting, disconnecting, and performing operations like adding, retrieving,
 * and deleting report cards.
 */
public class SMOSServer {
    // A map to store report cards, using reportCardId as the key for quick access.
    private Map<String, ReportCard> reportCards;
    // Flag to indicate if the server connection is active.
    private boolean isConnected;

    /**
     * Constructs a new SMOSServer instance.
     * Initializes the report card storage and sets the connection status to disconnected.
     */
    public SMOSServer() {
        this.reportCards = new HashMap<>();
        this.isConnected = false;
    }

    /**
     * Simulates connecting to the SMOS server.
     *
     * @return true if the connection was successful, false if already connected.
     */
    public boolean connect() {
        if (isConnected) {
            System.out.println("SMOS Server: Already connected.");
            return false;
        }
        isConnected = true;
        System.out.println("SMOS Server: Connected successfully.");
        return true;
    }

    /**
     * Simulates disconnecting from the SMOS server.
     *
     * @return true if the disconnection was successful, false if already disconnected.
     */
    public boolean disconnect() {
        if (!isConnected) {
            System.out.println("SMOS Server: Already disconnected.");
            return false;
        }
        isConnected = false;
        System.out.println("SMOS Server: Disconnected.");
        return true;
    }

    /**
     * Checks if the server is currently connected.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Adds a report card to the server's storage.
     * This method is typically used for initial data population.
     *
     * @param reportCard The ReportCard object to add.
     * @return true if the report card was added, false if a report card with the same ID already exists.
     * @throws IllegalStateException if the server is not connected.
     */
    public boolean addReportCard(ReportCard reportCard) {
        if (!isConnected) {
            throw new IllegalStateException("SMOS Server: Cannot add report card. Not connected to server.");
        }
        if (reportCards.containsKey(reportCard.getReportCardId())) {
            System.out.println("SMOS Server: Report card with ID " + reportCard.getReportCardId() + " already exists.");
            return false;
        }
        reportCards.put(reportCard.getReportCardId(), reportCard);
        System.out.println("SMOS Server: Added report card: " + reportCard.getReportCardId());
        return true;
    }

    /**
     * Retrieves a specific report card by its ID.
     *
     * @param reportCardId The ID of the report card to retrieve.
     * @return The ReportCard object if found, null otherwise.
     * @throws IllegalStateException if the server is not connected.
     */
    public ReportCard getReportCard(String reportCardId) {
        if (!isConnected) {
            throw new IllegalStateException("SMOS Server: Cannot retrieve report card. Not connected to server.");
        }
        return reportCards.get(reportCardId);
    }

    /**
     * Retrieves an unmodifiable list of all report cards currently stored on the server.
     *
     * @return A List of all ReportCard objects.
     * @throws IllegalStateException if the server is not connected.
     */
    public List<ReportCard> getAllReportCards() {
        if (!isConnected) {
            throw new IllegalStateException("SMOS Server: Cannot retrieve all report cards. Not connected to server.");
        }
        return Collections.unmodifiableList(new ArrayList<>(reportCards.values()));
    }

    /**
     * Deletes a report card from the system based on its ID.
     * This is the core operation for the "DeleteOfOnePage" use case.
     *
     * @param reportCardId The ID of the report card to delete.
     * @return true if the report card was successfully deleted, false if it was not found.
     * @throws IllegalStateException if the server is not connected.
     */
    public boolean deleteReportCard(String reportCardId) {
        if (!isConnected) {
            throw new IllegalStateException("SMOS Server: Cannot delete report card. Not connected to server.");
        }
        if (reportCards.containsKey(reportCardId)) {
            reportCards.remove(reportCardId);
            System.out.println("SMOS Server: Report card with ID " + reportCardId + " successfully deleted.");
            return true;
        } else {
            System.out.println("SMOS Server: Report card with ID " + reportCardId + " not found.");
            return false;
        }
    }
}