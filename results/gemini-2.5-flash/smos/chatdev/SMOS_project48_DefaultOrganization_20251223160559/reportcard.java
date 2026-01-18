'''
Represents a report card for a student, containing basic information.
'''
class ReportCard {
    private int id;
    private String studentName;
    private String className;
    private double grade;
    /**
     * Constructs a new ReportCard with the specified details.
     *
     * @param id          Unique identifier for the report card.
     * @param studentName The name of the student.
     * @param className   The name of the class/course.
     * @param grade       The grade received by the student.
     */
    public ReportCard(int id, String studentName, String className, double grade) {
        this.id = id;
        this.studentName = studentName;
        this.className = className;
        this.grade = grade;
    }
    /**
     * Gets the ID of the report card.
     *
     * @return The report card ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Gets the student name.
     *
     * @return The student's name.
     */
    public String getStudentName() {
        return studentName;
    }
    /**
     * Gets the class name.
     *
     * @return The class name.
     */
    public String getClassName() {
        return className;
    }
    /**
     * Gets the grade.
     *
     * @return The grade received.
     */
    public double getGrade() {
        return grade;
    }
    /**
     * Provides a string representation of the ReportCard object.
     *
     * @return A string containing the report card's details.
     */
    @Override
    public String toString() {
        return "ID: " + id + ", Student: " + studentName + ", Class: " + className + ", Grade: " + grade;
    }
}