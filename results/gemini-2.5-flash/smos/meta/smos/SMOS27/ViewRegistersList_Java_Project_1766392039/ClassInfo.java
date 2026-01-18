/**
 * Represents class information, such as class name and ID.
 * This class provides methods to access details about a specific academic class.
 */
public class ClassInfo {
    private final String classId;
    private final String className;

    /**
     * Constructs a new ClassInfo object.
     *
     * @param classId   The unique identifier for the class (e.g., "10A", "Grade 5 Math").
     * @param className The descriptive name of the class (e.g., "Tenth Grade - Section A", "Fifth Grade Mathematics").
     * @throws IllegalArgumentException if classId or className is null or empty.
     */
    public ClassInfo(String classId, String className) {
        if (classId == null || classId.trim().isEmpty()) {
            throw new IllegalArgumentException("Class ID cannot be null or empty.");
        }
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("Class name cannot be null or empty.");
        }
        this.classId = classId;
        this.className = className;
    }

    /**
     * Returns the unique identifier of the class.
     *
     * @return The class ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Returns the descriptive name of the class.
     *
     * @return The class name.
     */
    public String getClassName() {
        return className;
    }

    /**
     * Returns a string representation of the ClassInfo object.
     *
     * @return A string in the format "Class ID: [classId], Name: [className]".
     */
    @Override
    public String toString() {
        return "Class ID: " + classId + ", Name: " + className;
    }

    /**
     * Compares this ClassInfo object to the specified object. The result is true if and only if
     * the argument is not null and is a ClassInfo object that represents the same class ID.
     *
     * @param o The object to compare this ClassInfo against.
     * @return true if the given object represents a ClassInfo equivalent to this class, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassInfo classInfo = (ClassInfo) o;
        return classId.equals(classInfo.classId);
    }

    /**
     * Returns a hash code for this ClassInfo object.
     *
     * @return A hash code value for this object, based on its class ID.
     */
    @Override
    public int hashCode() {
        return classId.hashCode();
    }
}