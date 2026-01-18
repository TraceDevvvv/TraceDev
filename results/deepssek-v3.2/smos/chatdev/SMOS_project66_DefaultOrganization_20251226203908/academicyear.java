/**
 * AcademicYear.java
 * Represents an academic year in the system
 */
public class AcademicYear {
    private String id;
    private String startYear;
    private String endYear;
    private boolean isCurrent;
    public AcademicYear(String id, String startYear, String endYear, boolean isCurrent) {
        this.id = id;
        this.startYear = startYear;
        this.endYear = endYear;
        this.isCurrent = isCurrent;
    }
    public String getId() {
        return id;
    }
    public String getStartYear() {
        return startYear;
    }
    public String getEndYear() {
        return endYear;
    }
    public boolean isCurrent() {
        return isCurrent;
    }
    @Override
    public String toString() {
        String label = startYear + "-" + endYear;
        if (isCurrent) {
            label += " (Current)";
        }
        return label;
    }
}