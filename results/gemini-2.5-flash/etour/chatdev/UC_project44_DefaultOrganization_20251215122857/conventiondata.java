/*
 * DOCSTRING:
 * This class represents the data model for a Convention Request.
 * It holds details like convention name, agency, start and end dates, and terms.
 * It also includes a basic validation method to check if the data is sufficient.
 */
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
class ConventionData {
    private String conventionName;
    private String agencyName;
    private String startDate;
    private String endDate;
    private String terms;
    /**
     * Constructor for ConventionData.
     * @param conventionName The name or title of the convention.
     * @param agencyName The name of the agency involved.
     * @param startDate The proposed start date of the convention (e.g., "YYYY-MM-DD").
     * @param endDate The proposed end date of the convention (e.g., "YYYY-MM-DD").
     * @param terms The terms and conditions of the convention.
     */
    public ConventionData(String conventionName, String agencyName, String startDate, String endDate, String terms) {
        this.conventionName = conventionName;
        this.agencyName = agencyName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.terms = terms;
    }
    // --- Getters for the fields ---
    public String getConventionName() {
        return conventionName;
    }
    public String getAgencyName() {
        return agencyName;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public String getTerms() {
        return terms;
    }
    /**
     * Validates the convention data.
     * Checks if all required fields are non-empty, and additionally validates
     * the format and logical order of start and end dates.
     *
     * In a real-world scenario, this would include date format validation,
     * logical date order checks (start before end), etc.
     *
     * @return true if all essential fields are filled and dates are valid, false otherwise.
     */
    public boolean isValid() {
        // Basic check for all fields being non-null and non-empty after trimming.
        if (conventionName == null || conventionName.trim().isEmpty() ||
            agencyName == null || agencyName.trim().isEmpty() ||
            startDate == null || startDate.trim().isEmpty() ||
            endDate == null || endDate.trim().isEmpty() ||
            terms == null || terms.trim().isEmpty()) {
            return false;
        }
        // Robust date validation: Check format and logical order (start_date <= end_date).
        try {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            // Ensure start date is not after end date.
            if (start.isAfter(end)) {
                return false;
            }
        } catch (DateTimeParseException e) {
            // If date strings are not in YYYY-MM-DD format, parsing will fail.
            // This is considered invalid data as per the use case's need for correct input.
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "Convention Details:\n" +
               "  Name: " + conventionName + "\n" +
               "  Agency: " + agencyName + "\n" +
               "  Start Date: " + startDate + "\n" +
               "  End Date: " + endDate + "\n" +
               "  Terms: " + terms;
    }
}