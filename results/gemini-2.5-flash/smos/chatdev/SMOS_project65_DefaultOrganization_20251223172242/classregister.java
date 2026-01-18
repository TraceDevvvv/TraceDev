import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
/*
 * Manages all register entries for a particular class over different dates
 * within an academic year.
 */
public class ClassRegister {
    private String className;
    private String academicYear;
    // Maps each date to a list of RegisterEntry objects for that date
    private Map<LocalDate, List<RegisterEntry>> registerEntriesByDate;
    /**
     * Constructs a new ClassRegister object.
     * @param className The name of the class (e.g., "10A").
     * @param academicYear The academic year (e.g., "2023-2024").
     */
    public ClassRegister(String className, String academicYear) {
        this.className = className;
        this.academicYear = academicYear;
        this.registerEntriesByDate = new HashMap<>();
    }
    /**
     * Returns the class name.
     * @return The class name.
     */
    public String getClassName() {
        return className;
    }
    /**
     * Sets the class name.
     * @param className The new class name.
     */
    public void setClassName(String className) {
        this.className = className;
    }
    /**
     * Returns the academic year.
     * @return The academic year.
     */
    public String getAcademicYear() {
        return academicYear;
    }
    /**
     * Sets the academic year.
     * @param academicYear The new academic year.
     */
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    /**
     * Retrieves the list of register entries for a specific date.
     * If no entries exist for the date, an empty list is returned.
     * @param date The date for which to retrieve register entries.
     * @return A list of RegisterEntry objects for the given date.
     */
    public List<RegisterEntry> getRegisterForDate(LocalDate date) {
        return registerEntriesByDate.getOrDefault(date, new ArrayList<>());
    }
    /**
     * Adds or updates a register entry for a specific date.
     * If an entry for the student already exists for the given date, it is updated.
     * Otherwise, a new entry is added.
     * @param date The date of the register entry.
     * @param newEntry The RegisterEntry object to add or update.
     */
    public void addOrUpdateEntry(LocalDate date, RegisterEntry newEntry) {
        List<RegisterEntry> entries = registerEntriesByDate.getOrDefault(date, new ArrayList<>());
        // Check if an entry for this student already exists for this date
        boolean found = false;
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getStudent().equals(newEntry.getStudent())) {
                entries.set(i, newEntry); // Update existing entry
                found = true;
                break;
            }
        }
        if (!found) {
            entries.add(newEntry); // Add new entry
        }
        registerEntriesByDate.put(date, entries); // Store or update the list for the date
    }
    /**
     * Removes a register entry for a specific date and student.
     * @param date The date of the entry.
     * @param studentId The ID of the student whose entry to remove.
     * @return True if the entry was found and removed, false otherwise.
     */
    public boolean removeEntry(LocalDate date, String studentId) {
        List<RegisterEntry> entries = registerEntriesByDate.get(date);
        if (entries != null) {
            return entries.removeIf(entry -> entry.getStudent().getId().equals(studentId));
        }
        return false;
    }
    /**
     * Returns a set of all dates for which register entries exist.
     * @return A list of LocalDate objects representing the dates with registers.
     */
    public List<LocalDate> getAvailableDates() {
        List<LocalDate> dates = new ArrayList<>(registerEntriesByDate.keySet());
        dates.sort(LocalDate::compareTo); // Sort dates chronologically
        return dates;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassRegister that = (ClassRegister) o;
        return Objects.equals(className, that.className) &&
               Objects.equals(academicYear, that.academicYear);
    }
    @Override
    public int hashCode() {
        return Objects.hash(className, academicYear);
    }
}