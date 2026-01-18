import java.util.Objects;

/**
 * Represents a digital register, associating it with a specific academic year and class.
 * Each digital register has a unique identifier, a descriptive name, and is linked
 * to an AcademicYear and a ClassInfo object.
 */
public class DigitalRegister {
    private final String registerId;
    private final String registerName;
    private final AcademicYear academicYear;
    private final ClassInfo classInfo;

    /**
     * Constructs a new DigitalRegister object.
     *
     * @param registerId   A unique identifier for this digital register.
     * @param registerName A descriptive name for the register (e.g., "Attendance Q1", "Grades Midterm").
     * @param academicYear The academic year this register belongs to.
     * @param classInfo    The class this register is associated with.
     * @throws IllegalArgumentException if registerId or registerName is null or empty,
     *                                  or if academicYear or classInfo is null.
     */
    public DigitalRegister(String registerId, String registerName, AcademicYear academicYear, ClassInfo classInfo) {
        if (registerId == null || registerId.trim().isEmpty()) {
            throw new IllegalArgumentException("Register ID cannot be null or empty.");
        }
        if (registerName == null || registerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Register name cannot be null or empty.");
        }
        if (academicYear == null) {
            throw new IllegalArgumentException("AcademicYear cannot be null.");
        }
        if (classInfo == null) {
            throw new IllegalArgumentException("ClassInfo cannot be null.");
        }

        this.registerId = registerId;
        this.registerName = registerName;
        this.academicYear = academicYear;
        this.classInfo = classInfo;
    }

    /**
     * Returns the unique identifier of the digital register.
     *
     * @return The register ID.
     */
    public String getRegisterId() {
        return registerId;
    }

    /**
     * Returns the descriptive name of the digital register.
     *
     * @return The register name.
     */
    public String getRegisterName() {
        return registerName;
    }

    /**
     * Returns the academic year this register belongs to.
     *
     * @return The AcademicYear object.
     */
    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Returns the class this register is associated with.
     *
     * @return The ClassInfo object.
     */
    public ClassInfo getClassInfo() {
        return classInfo;
    }

    /**
     * Returns a string representation of the DigitalRegister object.
     *
     * @return A formatted string containing register details.
     */
    @Override
    public String toString() {
        return "DigitalRegister{" +
               "ID='" + registerId + '\'' +
               ", Name='" + registerName + '\'' +
               ", AcademicYear=" + academicYear +
               ", Class=" + classInfo.getClassName() + " (" + classInfo.getClassId() + ")" +
               '}';
    }

    /**
     * Compares this DigitalRegister object to the specified object. The result is true if and only if
     * the argument is not null and is a DigitalRegister object that represents the same register ID.
     *
     * @param o The object to compare this DigitalRegister against.
     * @return true if the given object represents a DigitalRegister equivalent to this one, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DigitalRegister that = (DigitalRegister) o;
        return registerId.equals(that.registerId);
    }

    /**
     * Returns a hash code for this DigitalRegister object.
     *
     * @return A hash code value for this object, based on its register ID.
     */
    @Override
    public int hashCode() {
        return Objects.hash(registerId);
    }
}