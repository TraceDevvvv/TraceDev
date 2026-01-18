/**
 * AcademicYear.java
 * Class representing an academic year with a unique ID, start year, and end year.
 * This class provides methods to access academic year information.
 */
public class AcademicYear {
    private String yearId;
    private String startYear;
    private String endYear;
    
    /**
     * Constructor to create a new AcademicYear object.
     * 
     * @param yearId The unique identifier for the academic year
     * @param startYear The starting year of the academic year (e.g., "2023")
     * @param endYear The ending year of the academic year (e.g., "2024")
     */
    public AcademicYear(String yearId, String startYear, String endYear) {
        this.yearId = yearId;
        this.startYear = startYear;
        this.endYear = endYear;
    }
    
    /**
     * Gets the academic year ID.
     * 
     * @return The academic year ID
     */
    public String getYearId() {
        return yearId;
    }
    
    /**
     * Gets the starting year of the academic year.
     * 
     * @return The start year as a string
     */
    public String getStartYear() {
        return startYear;
    }
    
    /**
     * Gets the ending year of the academic year.
     * 
     * @return The end year as a string
     */
    public String getEndYear() {
        return endYear;
    }
    
    /**
     * Gets the academic year range in format "startYear-endYear".
     * 
     * @return The formatted year range string
     */
    public String getYearRange() {
        return startYear + "-" + endYear;
    }
    
    /**
     * Sets the academic year ID.
     * 
     * @param yearId The new academic year ID
     */
    public void setYearId(String yearId) {
        this.yearId = yearId;
    }
    
    /**
     * Sets the starting year of the academic year.
     * 
     * @param startYear The new start year
     */
    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }
    
    /**
     * Sets the ending year of the academic year.
     * 
     * @param endYear The new end year
     */
    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }
    
    /**
     * Returns a string representation of the academic year.
     * 
     * @return String representation in format: "AcademicYear{id=..., range=...}"
     */
    @Override
    public String toString() {
        return "AcademicYear{id=" + yearId + ", range=" + getYearRange() + "}";
    }
    
    /**
     * Checks if this academic year is equal to another object.
     * Two academic years are considered equal if they have the same year ID.
     * 
     * @param obj The object to compare with
     * @return true if equal, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AcademicYear that = (AcademicYear) obj;
        return yearId.equals(that.yearId);
    }
    
    /**
     * Returns a hash code value for the academic year.
     * 
     * @return Hash code based on year ID
     */
    @Override
    public int hashCode() {
        return yearId.hashCode();
    }
}