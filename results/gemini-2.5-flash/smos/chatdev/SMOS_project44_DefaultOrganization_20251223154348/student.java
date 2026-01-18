'''
Represents a student entity with properties like ID, name, number of absences, and associated notes.
'''
public class Student {
    private int id;
    private String name;
    private int absences;
    private String notes; // Could be a single string of notes or a list for more complex scenarios
    /**
     * Constructs a new Student object.
     *
     * @param id The unique identifier for the student.
     * @param name The full name of the student.
     * @param absences The total number of absences for the student.
     * @param notes Any relevant notes associated with the student (e.g., disciplinary notes).
     */
    public Student(int id, String name, int absences, String notes) {
        this.id = id;
        this.name = name;
        this.absences = absences;
        this.notes = notes;
    }
    /**
     * Returns the student's ID.
     * @return The student's ID.
     */
    public int getId() {
        return id;
    }
    /**
     * Returns the student's name.
     * @return The student's name.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the student's number of absences.
     * @return The student's absences count.
     */
    public int getAbsences() {
        return absences;
    }
    /**
     * Returns the notes associated with the student.
     * @return The student's notes.
     */
    public String getNotes() {
        return notes;
    }
    /**
     * Provides a string representation of the Student object.
     * @return A string containing student ID, name, absences, and notes.
     */
    @Override
    public String toString() {
        return "Student{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", absences=" + absences +
               ", notes='" + notes + '\'' +
               '}';
    }
}