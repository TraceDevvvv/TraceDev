'''
Represents a Teaching entity (e.g., "Math", "Science", "History").
This could also include specific course codes or levels.
'''
public class Teaching {
    private int id;
    private int classId; // The class this teaching typically belongs to
    private String name; // e.g., "Mathematics", "Science Lab", "World History"
    /**
     * Constructs a new Teaching object.
     * @param id The unique identifier for the teaching.
     * @param classId The ID of the class this teaching is usually associated with.
     * @param name The name of the teaching.
     */
    public Teaching(int id, int classId, String name) {
        this.id = id;
        this.classId = classId;
        this.name = name;
    }
    /**
     * Returns the ID of the teaching.
     * @return The teaching's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Sets the ID of the teaching.
     * @param id The new ID for the teaching.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * Returns the class ID this teaching is associated with.
     * @return The class ID.
     */
    public int getClassId() {
        return classId;
    }
    /**
     * Sets the class ID for this teaching.
     * @param classId The new class ID.
     */
    public void setClassId(int classId) {
        this.classId = classId;
    }
    /**
     * Returns the name of the teaching.
     * @return The teaching's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the teaching.
     * @param name The new name for the teaching.
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
        Teaching teaching = (Teaching) o;
        return id == teaching.id;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}