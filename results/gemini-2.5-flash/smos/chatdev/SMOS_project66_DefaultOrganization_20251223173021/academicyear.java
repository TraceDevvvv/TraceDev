/**
 * Represents an academic year, with an ID and a display name.
 */
package models;
public class AcademicYear {
    private String id;
    private String name;
    public AcademicYear(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        // This is important for JComboBox to display the friendly name
        return name;
    }
}