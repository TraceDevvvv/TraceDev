/**
 * Data model representing an absence record
 * Used in the registry screen where absences are displayed in red
 */
import java.time.LocalDate;
public class Absence {
    private int id;
    private String employeeName;
    private LocalDate absenceDate;
    private String reason;
    private boolean justified;
    public Absence(int id, String employeeName, LocalDate absenceDate, String reason) {
        this.id = id;
        this.employeeName = employeeName;
        this.absenceDate = absenceDate;
        this.reason = reason;
        this.justified = false; // Initially not justified
    }
    // Getters and setters
    public int getId() { return id; }
    public String getEmployeeName() { return employeeName; }
    public LocalDate getAbsenceDate() { return absenceDate; }
    public String getReason() { return reason; }
    public boolean isJustified() { return justified; }
    public void setJustified(boolean justified) { this.justified = justified; }
    @Override
    public String toString() {
        return employeeName + " - " + absenceDate + " - " + reason + 
               (justified ? " (Justified)" : " (Not Justified)");
    }
}