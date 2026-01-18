/**
 * Model class representing a Student and their report card grades.
 * Contains fields for student identification and subject grades.
 */
public class Student {
    private String id;
    private String name;
    private String grade; // Grade level (e.g., 10th grade)
    private String mathGrade;
    private String scienceGrade;
    private String englishGrade;
    private String historyGrade;
    private String artGrade;
    // Constructor
    public Student(String id, String name, String grade, String mathGrade, 
                   String scienceGrade, String englishGrade, String historyGrade, 
                   String artGrade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.mathGrade = mathGrade;
        this.scienceGrade = scienceGrade;
        this.englishGrade = englishGrade;
        this.historyGrade = historyGrade;
        this.artGrade = artGrade;
    }
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
    public String getMathGrade() { return mathGrade; }
    public void setMathGrade(String mathGrade) { this.mathGrade = mathGrade; }
    public String getScienceGrade() { return scienceGrade; }
    public void setScienceGrade(String scienceGrade) { this.scienceGrade = scienceGrade; }
    public String getEnglishGrade() { return englishGrade; }
    public void setEnglishGrade(String englishGrade) { this.englishGrade = englishGrade; }
    public String getHistoryGrade() { return historyGrade; }
    public void setHistoryGrade(String historyGrade) { this.historyGrade = historyGrade; }
    public String getArtGrade() { return artGrade; }
    public void setArtGrade(String artGrade) { this.artGrade = artGrade; }
    /**
     * Returns string representation for display.
     * @return formatted string with student details
     */
    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Grade: %s, Math: %s, Science: %s, English: %s, History: %s, Art: %s",
                id, name, grade, mathGrade, scienceGrade, englishGrade, historyGrade, artGrade);
    }
}