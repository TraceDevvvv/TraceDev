'''
Justification.java
Represents a justification entity with fields for id, date, and description.
Provides getters, setters, and validation methods.
'''
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
public class Justification {
    private int id;
    private LocalDate date;
    private String description;
    public Justification(int id, LocalDate date, String description) {
        this.id = id;
        this.date = date;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isValidDate(String dateStr) {
        if (dateStr == null || dateStr.trim().isEmpty()) {
            return false;
        }
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    @Override
    public String toString() {
        return String.format("Justification [id=%d, date=%s, description=%s]", 
                           id, date, description);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Justification that = (Justification) o;
        return id == that.id && 
               Objects.equals(date, that.date) && 
               Objects.equals(description, that.description);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, date, description);
    }
}