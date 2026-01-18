/**
 * Manages report card operations including display, deletion, and retrieval.
 * Simulates a data store for report cards.
 */
import java.util.*;
public class ReportCardManager {
    private Map<Integer, ReportCard> reportCards; // studentId -> ReportCard
    private static ReportCardManager instance;
    /**
     * Private constructor to enforce singleton pattern.
     * Initializes with sample report cards.
     */
    private ReportCardManager() {
        reportCards = new HashMap<>();
        // Sample data for demonstration
        addSampleData();
    }
    /**
     * Returns the singleton instance of ReportCardManager.
     * @return The singleton instance.
     */
    public static synchronized ReportCardManager getInstance() {
        if (instance == null) {
            instance = new ReportCardManager();
        }
        return instance;
    }
    /**
     * Adds sample report cards to the manager for demonstration.
     */
    private void addSampleData() {
        Map<String, Double> grades1 = new HashMap<>();
        grades1.put("Math", 95.5);
        grades1.put("Science", 88.0);
        grades1.put("History", 92.0);
        reportCards.put(101, new ReportCard(101, "Alice Johnson", "Class 10A", grades1));
        Map<String, Double> grades2 = new HashMap<>();
        grades2.put("Math", 78.0);
        grades2.put("Science", 85.5);
        grades2.put("History", 90.0);
        reportCards.put(102, new ReportCard(102, "Bob Smith", "Class 10B", grades2));
        Map<String, Double> grades3 = new HashMap<>();
        grades3.put("Math", 88.5);
        grades3.put("Science", 92.0);
        grades3.put("History", 87.5);
        reportCards.put(103, new ReportCard(103, "Charlie Brown", "Class 10A", grades3));
    }
    /**
     * Displays a specific report card by student ID.
     * @param studentId The ID of the student whose report card to display.
     * @return The ReportCard object if found, null otherwise.
     */
    public ReportCard displayReportCard(int studentId) {
        return reportCards.get(studentId);
    }
    /**
     * Deletes a report card from the system.
     * @param studentId The ID of the student whose report card to delete.
     * @return true if deletion was successful, false if report card not found.
     */
    public boolean deleteReportCard(int studentId) {
        if (reportCards.containsKey(studentId)) {
            reportCards.remove(studentId);
            return true;
        }
        return false;
    }
    /**
     * Retrieves a list of all unique class names from report cards.
     * @return Set of class names.
     */
    public Set<String> getClassList() {
        Set<String> classes = new HashSet<>();
        for (ReportCard rc : reportCards.values()) {
            classes.add(rc.getClassName());
        }
        return classes;
    }
    /**
     * Retrieves all report cards for a given class.
     * @param className The name of the class.
     * @return List of ReportCard objects in the class.
     */
    public List<ReportCard> getReportCardsByClass(String className) {
        List<ReportCard> classReportCards = new ArrayList<>();
        for (ReportCard rc : reportCards.values()) {
            if (rc.getClassName().equals(className)) {
                classReportCards.add(rc);
            }
        }
        return classReportCards;
    }
    /**
     * Lists all report cards in the system.
     */
    public void listAllReportCards() {
        if (reportCards.isEmpty()) {
            System.out.println("No report cards available.");
        } else {
            System.out.println("All Report Cards:");
            for (ReportCard rc : reportCards.values()) {
                System.out.println(rc);
            }
        }
    }
}