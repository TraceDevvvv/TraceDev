/*
 * Represents a teacher in the system.
 * This class holds basic teacher information like ID and name.
 * It's used to associate academic years and classes with a particular teacher
 * for data filtering, simulating a logged-in user.
 */
class Teacher {
    private String id;
    private String name;
    /**
     * Constructs a new Teacher object.
     * @param id The unique identifier for the teacher.
     * @param name The full name of the teacher.
     */
    public Teacher(String id, String name) {
        this.id = id;
        this.name = name;
    }
    /**
     * Returns the teacher's ID.
     * @return The teacher ID.
     */
    public String getId() {
        return id;
    }
    /**
     * Returns the teacher's name.
     * @return The teacher name.
     */
    public String getName() {
        return name;
    }
    /**
     * Provides a string representation of the teacher, suitable for display or logging.
     * @return The teacher's name and ID.
     */
    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
    /**
     * Compares this Teacher object to another object for equality.
     * Two teachers are considered equal if their IDs are the same.
     * @param o The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id.equals(teacher.id);
    }
    /**
     * Returns a hash code value for the Teacher object.
     * The hash code is based on the teacher's ID.
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}