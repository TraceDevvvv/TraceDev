'''
Represents a school class object with its name, address, and academic year.
This is a simple Plain Old Java Object (POJO) to hold class data.
'''
public class SchoolClass {
    private String name;
    private String address;
    private int academicYear;
    /**
     * Constructs a new SchoolClass object.
     * @param name The name of the class.
     * @param address The address associated with the class.
     * @param academicYear The starting year of the academic year for the class.
     */
    public SchoolClass(String name, String address, int academicYear) {
        this.name = name;
        this.address = address;
        this.academicYear = academicYear;
    }
    /**
     * Gets the name of the class.
     * @return The class name.
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name of the class.
     * @param name The new class name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Gets the address of the class.
     * @return The class address.
     */
    public String getAddress() {
        return address;
    }
    /**
     * Sets the address of the class.
     * @param address The new class address.
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Gets the academic year of the class.
     * @return The academic year.
     */
    public int getAcademicYear() {
        return academicYear;
    }
    /**
     * Sets the academic year of the class.
     * @param academicYear The new academic year.
     */
    public void setAcademicYear(int academicYear) {
        this.academicYear = academicYear;
    }
    /**
     * Provides a string representation of the SchoolClass object.
     * @return A string containing the class's name, address, and academic year.
     */
    @Override
    public String toString() {
        return "Class Name: '" + name + "', Address: '" + address + "', Academic Year: " + academicYear;
    }
}