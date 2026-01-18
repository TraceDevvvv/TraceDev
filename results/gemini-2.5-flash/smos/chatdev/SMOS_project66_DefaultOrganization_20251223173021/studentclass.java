/**
 * Represents a student class (e.g., "10A"), linked to an academic year.
 */
package models;
public class StudentClass {
    private String id;
    private String name;
    private String academicYearId;
    public StudentClass(String id, String name, String academicYearId) {
        this.id = id;
        this.name = name;
        this.academicYearId = academicYearId;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAcademicYearId() {
        return academicYearId;
    }
    @Override
    public String toString() {
        // This is important for JList to display the friendly name
        return name;
    }
}