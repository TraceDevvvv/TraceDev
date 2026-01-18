/**
 * Simulates database operations for the application
 * In a real application, this would connect to an actual database
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class DatabaseSimulator {
    private static DatabaseSimulator instance;
    private List<Absence> absences;
    private List<Justification> justifications;
    private DatabaseSimulator() {
        // Initialize with sample data
        absences = new ArrayList<>();
        justifications = new ArrayList<>();
        // Add some sample absences
        absences.add(new Absence(1, "John Doe", LocalDate.now().minusDays(3), "Sick leave"));
        absences.add(new Absence(2, "Jane Smith", LocalDate.now().minusDays(2), "Personal reasons"));
        absences.add(new Absence(3, "Bob Johnson", LocalDate.now().minusDays(1), "Doctor appointment"));
    }
    public static synchronized DatabaseSimulator getInstance() {
        if (instance == null) {
            instance = new DatabaseSimulator();
        }
        return instance;
    }
    /**
     * Gets all absences that are not yet justified
     * @return List of unjustified absences
     */
    public List<Absence> getUnjustifiedAbsences() {
        List<Absence> unjustified = new ArrayList<>();
        for (Absence absence : absences) {
            if (!absence.isJustified()) {
                unjustified.add(absence);
            }
        }
        return unjustified;
    }
    /**
     * Saves a justification and marks the corresponding absence as justified
     * @param justification The justification to save
     * @return true if saved successfully, false otherwise
     */
    public boolean saveJustification(Justification justification) {
        try {
            // Simulate potential server interruption
            if (Math.random() < 0.1) { // 10% chance of interruption for simulation
                throw new RuntimeException("Connection to the SMOS server interrupted");
            }
            // Save justification
            justifications.add(justification);
            // Mark absence as justified
            for (Absence absence : absences) {
                if (absence.getId() == justification.getAbsenceId()) {
                    absence.setJustified(true);
                    break;
                }
            }
            return true;
        } catch (Exception e) {
            System.err.println("Error saving justification: " + e.getMessage());
            return false;
        }
    }
    /**
     * Gets all justifications for display or verification
     * @return List of all justifications
     */
    public List<Justification> getAllJustifications() {
        return new ArrayList<>(justifications);
    }
}