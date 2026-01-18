/**
 * Student class representing a student with personal information,
 * absences count, and average notes/grade
 */
public class Student {
    private String id;
    private String name;
    private int absences;
    private double averageGrade;
    /**
     * Constructor to create a new Student instance
     * 
     * @param id          Unique student identifier
     * @param name        Full name of the student
     * @param absences    Number of absences in current school year
     * @param averageGrade Average grade/notes (0-100 scale)
     */
    public Student(String id, String name, int absences, double averageGrade) {
        this.id = id;
        this.name = name;
        this.absences = absences;
        this.averageGrade = averageGrade;
    }
    // Getters
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAbsences() {
        return absences;
    }
    public double getAverageGrade() {
        return averageGrade;
    }
    /**
     * Check if student exceeds monitoring thresholds
     * @param absenceThreshold Students who have absences above this value are flagged
     * @param gradeThreshold   Students who have average grade below this value are flagged
     * @return true if student exceeds absence threshold OR falls below grade threshold
     */
    public boolean exceedsThreshold(int absenceThreshold, double gradeThreshold) {
        return absences > absenceThreshold || averageGrade < gradeThreshold;
    }
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Absences: %d, Average Grade: %.2f",
                id, name, absences, averageGrade);
    }
}