'''
Represents a Teacher entity in the system.
'''
public class Teacher {
    private int id;
    private String firstName;
    private String lastName;
    /**
     * Constructs a new Teacher object.
     * @param id The unique identifier for the teacher.
     * @param firstName The first name of the teacher.
     * @param lastName The last name of the teacher.
     */
    public Teacher(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    /**
     * Returns the ID of the teacher.
     * @return The teacher's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the teacher.
     * @param id The new ID for the teacher.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the first name of the teacher.
     * @return The teacher's first name.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Sets the first name of the teacher.
     * @param firstName The new first name for the teacher.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Returns the last name of the teacher.
     * @return The teacher's last name.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Sets the last name of the teacher.
     * @param lastName The new last name for the teacher.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return firstName + " " + lastName + " (ID: " + id + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}