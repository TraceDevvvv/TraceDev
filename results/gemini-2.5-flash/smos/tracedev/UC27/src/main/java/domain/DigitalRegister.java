
package domain;

/**
 * Represents a digital register record, containing academic content.
 */
public class DigitalRegister {
    private String registerId;
    private String title;
    private String content; // Content is simplified as a String for this example
    private String academicYearId;
    private String academicClassId;

    /**
     * Constructs a new DigitalRegister.
     *
     * @param registerId The unique identifier for the digital register.
     * @param title The title of the register.
     * @param content The content or body of the register.
     * @param academicYearId The ID of the academic year this register belongs to.
     * @param academicClassId The ID of the academic class this register belongs to.
     */
    public DigitalRegister(String registerId, String title, String content, String academicYearId, String academicClassId) {
        this.registerId = registerId;
        this.title = title;
        this.content = content;
        this.academicYearId = academicYearId;
        this.academicClassId = academicClassId;
    }

    /**
     * Gets the unique identifier of the digital register.
     *
     * @return The register's ID.
     */
    public String getRegisterId() {
        return registerId;
    }

    /**
     * Gets the title of the digital register.
     *
     * @return The register's title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the content of the digital register.
     *
     * @return The register's content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Gets the ID of the academic year this register belongs to.
     *
     * @return The academic year ID.
     */
    public String getAcademicYearId() {
        return academicYearId;
    }

    /**
     * Gets the ID of the academic class this register belongs to.
     *
     * @return The academic class ID.
     */
    public String getAcademicClassId() {
        return academicClassId;
    }

    @Override
    public String toString() {
        return "DigitalRegister{" +
               "registerId='" + registerId + '\'' +
               ", title='" + title + '\'' +
               ", academicYearId='" + academicYearId + '\'' +
               ", academicClassId='" + academicClassId + '\'' +
               '}';
    }
}
