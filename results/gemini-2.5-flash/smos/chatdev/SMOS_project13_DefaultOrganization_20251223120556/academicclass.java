'''
Represents a single academic class with properties such as ID, name, academic year, and instructor.
This is a simple POJO (Plain Old Java Object) for transferring class data.
'''
public class AcademicClass {
    private String id;
    private String name;
    private String academicYear;
    private String instructor;
    /**
     * Constructs a new AcademicClass object.
     *
     * @param id           The unique identifier for the class.
     * @param name         The name of the class (e.g., "Introduction to Programming").
     * @param academicYear The academic year the class belongs to (e.g., "2023-2024").
     * @param instructor   The name of the instructor teaching the class.
     */
    public AcademicClass(String id, String name, String academicYear, String instructor) {
        this.id = id;
        this.name = name;
        this.academicYear = academicYear;
        this.instructor = instructor;
    }
    /**
     * Gets the ID of the class.
     *
     * @return The class ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Sets the ID of the class.
     *
     * @param id The new class ID.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * Gets the name of the class.
     *
     * @return The class name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the class.
     *
     * @param name The new class name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the academic year of the class.
     *
     * @return The academic year.
     */
    public String getAcademicYear() {
        return academicYear;
    }
    /**
     * Sets the academic year of the class.
     *
     * @param academicYear The new academic year.
     */
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    /**
     * Gets the instructor of the class.
     *
     * @return The instructor's name.
     */
    public String getInstructor() {
        return instructor;
    }
    /**
     * Sets the instructor of the class.
     *
     * @param instructor The new instructor's name.
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    /**
     * Returns a string representation of the AcademicClass object.
     *
     * @return A string containing the class ID, name, academic year, and instructor.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Year: " + academicYear + ", Instructor: " + instructor;
    }
}