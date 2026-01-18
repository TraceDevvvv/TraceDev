/**
 * Register class represents a digital register entry in the system.
 * It stores the class name and register ID.
 */
public class Register {
    private String className;
    private String registerId;
    private String academicYear;
    public Register(String className, String registerId, String academicYear) {
        this.className = className;
        this.registerId = registerId;
        this.academicYear = academicYear;
    }
    public String getClassName() {
        return className;
    }
    public String getRegisterId() {
        return registerId;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    @Override
    public String toString() {
        return "Class: " + className + " | Register ID: " + registerId + " | Academic Year: " + academicYear;
    }
}