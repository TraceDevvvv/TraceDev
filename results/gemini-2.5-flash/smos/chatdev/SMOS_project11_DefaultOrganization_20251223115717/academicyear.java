'''
Represents an Academic Year entity.
'''
public class AcademicYear {
    private int id;
    private String name;
    /**
     * Constructs a new AcademicYear object.
     * @param id The unique identifier for the academic year.
     * @param name The name or description of the academic year (e.g., "2023-2024").
     */
    public AcademicYear(int id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Returns the ID of the academic year.
     * @return The academic year's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the academic year.
     * @param id The new ID for the academic year.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the name of the academic year.
     * @return The academic year's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the academic year.
     * @param name The new name for the academic year.
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
        AcademicYear that = (AcademicYear) o;
        return id == that.id;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}