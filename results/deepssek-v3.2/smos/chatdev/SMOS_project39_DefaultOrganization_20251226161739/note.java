'''
Note.java
Represents a single note with student, teacher, description and date.
This is a simple data model class.
'''
public class Note {
    private int id;
    private String student;
    private String description;
    private String teacher;
    private String date;
    // Constructor
    public Note(int id, String student, String description, String teacher, String date) {
        this.id = id;
        this.student = student;
        this.description = description;
        this.teacher = teacher;
        this.date = date;
    }
    // Getters
    public int getId() {
        return id;
    }
    public String getStudent() {
        return student;
    }
    public String getDescription() {
        return description;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getDate() {
        return date;
    }
    // Setters for completeness
    public void setStudent(String student) {
        this.student = student;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "Note #" + id + ": " + student + " - " + 
               (description.length() > 30 ? description.substring(0, 30) + "..." : description);
    }
}