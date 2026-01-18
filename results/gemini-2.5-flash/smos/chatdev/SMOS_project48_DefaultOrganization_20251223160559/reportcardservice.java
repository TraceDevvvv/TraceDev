'''
Provides serv for managing report cards, simulating a data store.
In a real application, this would interact with a database.
'''
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
class ReportCardService {
    // In a real application, this would be a database connection or repository.
    private List<ReportCard> reportCards;
    /**
     * Constructs a ReportCardService and initializes it with some sample data.
     */
    public ReportCardService() {
        reportCards = new ArrayList<>();
        // Add some dummy data for demonstration
        reportCards.add(new ReportCard(101, "Alice Smith", "Math 101", 85.5));
        reportCards.add(new ReportCard(102, "Bob Johnson", "English Lit", 92.0));
        reportCards.add(new ReportCard(103, "Charlie Brown", "Physics I", 78.0));
        reportCards.add(new ReportCard(104, "Diana Prince", "Chemistry II", 90.0));
    }
    /**
     * Finds a report card by its ID.
     *
     * @param id The ID of the report card to find.
     * @return The ReportCard object if found, otherwise null.
     */
    public ReportCard findReportCardById(int id) {
        for (ReportCard rc : reportCards) {
            if (rc.getId() == id) {
                return rc;
            }
        }
        return null;
    }
    /**
     * Deletes a report card from the system based on its ID.
     *
     * @param id The ID of the report card to delete.
     * @return true if the report card was successfully deleted, false otherwise.
     */
    public boolean deleteReportCard(int id) {
        Iterator<ReportCard> iterator = reportCards.iterator();
        while (iterator.hasNext()) {
            ReportCard rc = iterator.next();
            if (rc.getId() == id) {
                iterator.remove();
                System.out.println("LOG: Report Card with ID " + id + " deleted from service.");
                return true;
            }
        }
        return false;
    }
    /**
     * Retrieves all report cards currently in the system.
     *
     * @return A list of all report cards.
     */
    public List<ReportCard> getAllReportCards() {
        // Return a defensive copy to prevent external modification of the internal list
        return new ArrayList<>(reportCards); 
    }
}