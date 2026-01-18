/**
 * This class represents the data model for class details.
 * It encapsulates the information of a class: name, address, and school year.
 */
public class ClassDetails {
    private String className;
    private String address;
    private String schoolYear;
    /**
     * Constructor to initialize class details.
     * @param className The name of the class
     * @param address The address of the class
     * @param schoolYear The school year of the class
     */
    public ClassDetails(String className, String address, String schoolYear) {
        this.className = className;
        this.address = address;
        this.schoolYear = schoolYear;
    }
    /**
     * Gets the class name.
     * @return The class name
     */
    public String getClassName() {
        return className;
    }
    /**
     * Sets the class name.
     * @param className The new class name
     */
    public void setClassName(String className) {
        this.className = className;
    }
    /**
     * Gets the address.
     * @return The address
     */
    public String getAddress() {
        return address;
    }
    /**
     * Sets the address.
     * @param address The new address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Gets the school year.
     * @return The school year
     */
    public String getSchoolYear() {
        return schoolYear;
    }
    /**
     * Sets the school year.
     * @param schoolYear The new school year
     */
    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }
    /**
     * Returns a string representation of the class details.
     * @return A formatted string with all details
     */
    @Override
    public String toString() {
        return "ClassDetails{" +
                "className='" + className + '\'' +
                ", address='" + address + '\'' +
                ", schoolYear='" + schoolYear + '\'' +
                '}';
    }
}