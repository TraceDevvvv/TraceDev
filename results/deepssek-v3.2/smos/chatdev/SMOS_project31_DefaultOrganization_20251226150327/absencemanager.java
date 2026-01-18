import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * Manages absence records and operations.
 */
public class AbsenceManager {
    private List<Absence> absences;
    private ServerConnection serverConnection;
    private EmailService emailService;
    public AbsenceManager(ServerConnection serverConnection, EmailService emailService) {
        this.absences = new ArrayList<>();
        this.serverConnection = serverConnection;
        this.emailService = emailService;
    }
    /**
     * Update screen based on selected date (simulated).
     */
    public List<Absence> getAbsencesForDate(LocalDate date) {
        List<Absence> result = new ArrayList<>();
        for (Absence absence : absences) {
            if (absence.getDate().equals(date)) {
                result.add(absence);
            }
        }
        return result;
    }
    /**
     * Change absence (insert or delete) and save.
     * Handles both updating existing records and inserting new ones.
     */
    public void changeAbsence(Absence absence, boolean newStatus) {
        try {
            // Check if this absence already exists
            boolean isNewAbsence = !absences.contains(absence);
            // Store old status for email notification
            boolean oldStatus = isNewAbsence ? !newStatus : absence.isPresent();
            // Update absence status
            absence.setPresent(newStatus);
            // If it's a new absence, add to the list
            if (isNewAbsence) {
                absences.add(absence);
            }
            // Save to server
            serverConnection.saveAbsenceData(absence);
            // Send email notification if status changed
            if (oldStatus != newStatus) {
                String changeType = newStatus ? "marked as present" : "marked as absent";
                emailService.sendAbsenceChangeNotification(
                    absence.getStudent().getParentEmail(),
                    absence.getStudent().getName(),
                    absence.getDate().toString(),
                    changeType
                );
            }
            System.out.println("Absence updated successfully.");
        } catch (RuntimeException e) {
            System.err.println("Server error: " + e.getMessage());
            throw e; // Re-throw to handle in UI
        } catch (CustomException e) {
            System.err.println("Email error: " + e.getMessage() + " (notification not sent)");
            // Continue even if email fails
        }
    }
    /**
     * Add sample data for demonstration.
     */
    public void addSampleAbsences() {
        Student student1 = new Student("S001", "John Doe", "parent1@example.com");
        Student student2 = new Student("S002", "Jane Smith", "parent2@example.com");
        absences.add(new Absence(student1, LocalDate.of(2024, 1, 15), false));
        absences.add(new Absence(student2, LocalDate.of(2024, 1, 15), true));
        absences.add(new Absence(student1, LocalDate.of(2024, 1, 16), true));
    }
}