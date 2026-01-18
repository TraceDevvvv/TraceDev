/**
 * A simple Plain Old Java Object (POJO) representing a school class.
 * Contains basic information about a class.
 */
public class SchoolClass {
    private String id;
    private String name;
    private String academicYear;
    public SchoolClass(String id, String name, String academicYear) {
        this.id = id;
        this.name = name;
        this.academicYear = academicYear;
    }
    // Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    @Override
    public String toString() {
        return name + " (" + academicYear + ")";
    }
}