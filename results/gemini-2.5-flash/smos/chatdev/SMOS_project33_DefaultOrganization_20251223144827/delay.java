/*
Delay.java
 DOCSTRING
    This class represents a Delay entry in the system.
    It encapsulates details about a student's late arrival,
    including student name, the date of the delay, and a reason.
    It also includes an ID for unique identification within the system.
*/
import java.time.LocalDate;
import java.util.Objects;
public class Delay {
    private int id; // Unique identifier for the delay
    private String studentName;
    private LocalDate date;
    private String reason;
    /**
     * Constructs a new Delay instance.
     * @param id The unique identifier for this delay.
     * @param studentName The name of the student who was late.
     * @param date The date on which the student was late.
     * @param reason The reason for the delay.
     */
    public Delay(int id, String studentName, LocalDate date, String reason) {
        this.id = id;
        this.studentName = studentName;
        this.date = date;
        this.reason = reason;
    }
    // --- Getter Methods ---
    public int getId() {
        return id;
    }
    public String getStudentName() {
        return studentName;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getReason() {
        return reason;
    }
    @Override
    public String toString() {
        return "Delay{" +
               "id=" + id +
               ", studentName='" + studentName + '\'' +
               ", date=" + date +
               ", reason='" + reason + '\'' +
               '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delay delay = (Delay) o;
        return id == delay.id; // Delays are considered equal if their IDs match
    }
    @Override
    public int hashCode() {
        return Objects.hash(id); // Hash code based on ID
    }
}