package domain;

/**
 * Represents an academic class or course in the domain model.
 */
public class AcademicClass {
    private String classId;
    private String name;

    /**
     * Constructs a new AcademicClass.
     *
     * @param classId The unique identifier for the academic class.
     * @param name The name of the academic class.
     */
    public AcademicClass(String classId, String name) {
        this.classId = classId;
        this.name = name;
    }

    /**
     * Gets the unique identifier of the academic class.
     *
     * @return The class's ID.
     */
    public String getClassId() {
        return classId;
    }

    /**
     * Gets the name of the academic class.
     *
     * @return The class's name.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "AcademicClass{" +
               "classId='" + classId + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}