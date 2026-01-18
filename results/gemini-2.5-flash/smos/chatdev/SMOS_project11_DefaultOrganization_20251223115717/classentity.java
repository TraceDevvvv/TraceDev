'''
Represents a Class entity (e.g., Grade 1A, Grade 2B).
Renamed to ClassEntity to avoid conflict with java.lang.Class.
'''
public class ClassEntity {
    private int id;
    private int academicYearId;
    private String name; // e.g., "Grade 1A", "Grade 2B"
    /**
     * Constructs a new ClassEntity object.
     * @param id The unique identifier for the class.
     * @param academicYearId The ID of the academic year this class belongs to.
     * @param name The name of the class (e.g., "Grade 1A").
     */
    public ClassEntity(int id, int academicYearId, String name) {
        this.id = id;
        this.academicYearId = academicYearId;
        this.name = name;
    }
    /**
     * Returns the ID of the class.
     * @return The class's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the class.
     * @param id The new ID for the class.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the academic year ID this class belongs to.
     * @return The academic year ID.
     */
    public int getAcademicYearId() {
        return academicYearId;
    }
    /**
     * Sets the academic year ID for this class.
     * @param academicYearId The new academic year ID.
     */
    public void setAcademicYearId(int academicYearId) {
        this.academicYearId = academicYearId;
    }
    /**
     * Returns the name of the class.
     * @return The class's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the class.
     * @param name The new name for the class.
     */
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassEntity that = (ClassEntity) o;
        return id == that.id;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}