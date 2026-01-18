/*
 * Represents a classroom.
 * This class stores the name of the class and the academic year it belongs to.
 * It overrides toString for proper display in GUI components.
 */
class Classroom {
    private String name;
    private AcademicYear academicYear; // The academic year this class belongs to
    /**
     * Constructs a new Classroom object.
     * @param name The name of the class (e.g., "5A").
     * @param academicYear The AcademicYear object this class is part of.
     */
    public Classroom(String name, AcademicYear academicYear) {
        this.name = name;
        this.academicYear = academicYear;
    }
    /**
     * Returns the name of the classroom.
     * @return The class name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the academic year associated with this classroom.
     * @return The AcademicYear object.
     */
    public AcademicYear getAcademicYear() {
        return academicYear;
    }
    /**
     * Provides a string representation of the classroom, suitable for display.
     * @return The class name.
     */
    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Classroom classroom = (Classroom) o;
        return name.equals(classroom.name) && academicYear.equals(classroom.academicYear);
    }
    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + academicYear.hashCode();
        return result;
    }
}