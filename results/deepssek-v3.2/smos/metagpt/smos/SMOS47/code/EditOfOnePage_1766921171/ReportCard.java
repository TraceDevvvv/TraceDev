import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ReportCard class to store and manage student grades for different subjects.
 * Includes functionality to add, update, and remove grades, calculate average,
 * and validate grade values. Handles edge cases like invalid grades.
 */
public class ReportCard {
    private Map<String, Double> grades;
    
    /**
     * Default constructor - initializes an empty grade map
     */
    public ReportCard() {
        this.grades = new HashMap<>();
    }
    
    /**
     * Constructor with initial grades
     * 
     * @param grades Initial map of subject-grade pairs
     */
    public ReportCard(Map<String, Double> grades) {
        this.grades = new HashMap<>(grades);
    }
    
    /**
     * Add or update a grade for a subject
     * Validates the grade is within valid range (0-100)
     * 
     * @param subject The subject name
     * @param grade The grade value (0-100)
     * @return true if grade was successfully added/updated, false if grade is invalid
     */
    public boolean addOrUpdateGrade(String subject, double grade) {
        if (!isValidGrade(grade)) {
            return false;
        }
        grades.put(subject, grade);
        return true;
    }
    
    /**
     * Remove a grade for a specific subject
     * 
     * @param subject The subject name to remove
     * @return true if grade was removed, false if subject didn't exist
     */
    public boolean removeGrade(String subject) {
        return grades.remove(subject) != null;
    }
    
    /**
     * Get grade for a specific subject
     * 
     * @param subject The subject name
     * @return The grade value, or null if subject doesn't exist
     */
    public Double getGrade(String subject) {
        return grades.get(subject);
    }
    
    /**
     * Get all grades as an unmodifiable map
     * 
     * @return Map of all subject-grade pairs
     */
    public Map<String, Double> getAllGrades() {
        return new HashMap<>(grades);
    }
    
    /**
     * Calculate the average grade across all subjects
     * Handles edge case when there are no grades
     * 
     * @return The average grade, or 0.0 if no grades exist
     */
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (double grade : grades.values()) {
            sum += grade;
        }
        return sum / grades.size();
    }
    
    /**
     * Get the highest grade among all subjects
     * 
     * @return The highest grade, or 0.0 if no grades exist
     */
    public double getHighestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double highest = Double.MIN_VALUE;
        for (double grade : grades.values()) {
            if (grade > highest) {
                highest = grade;
            }
        }
        return highest;
    }
    
    /**
     * Get the lowest grade among all subjects
     * 
     * @return The lowest grade, or 0.0 if no grades exist
     */
    public double getLowestGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double lowest = Double.MAX_VALUE;
        for (double grade : grades.values()) {
            if (grade < lowest) {
                lowest = grade;
            }
        }
        return lowest;
    }
    
    /**
     * Check if a grade value is valid (between 0 and 100 inclusive)
     * 
     * @param grade The grade to validate
     * @return true if grade is valid, false otherwise
     */
    public static boolean isValidGrade(double grade) {
        return grade >= 0.0 && grade <= 100.0;
    }
    
    /**
     * Check if the report card has any grades
     * 
     * @return true if there are grades, false otherwise
     */
    public boolean hasGrades() {
        return !grades.isEmpty();
    }
    
    /**
     * Get the number of subjects with grades
     * 
     * @return Count of subjects
     */
    public int getSubjectCount() {
        return grades.size();
    }
    
    /**
     * Clear all grades from the report card
     */
    public void clearAllGrades() {
        grades.clear();
    }
    
    /**
     * String representation of the ReportCard
     * Shows all subjects and grades, plus average
     * 
     * @return Formatted string with report card details
     */
    @Override
    public String toString() {
        if (grades.isEmpty()) {
            return "Report Card: No grades available";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("Report Card:\n");
        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            sb.append(String.format("  %s: %.2f\n", entry.getKey(), entry.getValue()));
        }
        sb.append(String.format("Average Grade: %.2f", calculateAverage()));
        return sb.toString();
    }
    
    /**
     * Check if two report cards are equal
     * Two report cards are equal if they have the same grades
     * 
     * @param obj Object to compare with
     * @return true if report cards have same grades, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ReportCard that = (ReportCard) obj;
        return Objects.equals(grades, that.grades);
    }
    
    /**
     * Generate hash code based on grades map
     * 
     * @return Hash code for the report card
     */
    @Override
    public int hashCode() {
        return Objects.hash(grades);
    }
}