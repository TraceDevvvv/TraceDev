'''
Data class representing a disciplinary note.
'''
public class Note {
    private String id;
    private String studentName;
    private String description;
    private String date;
    private String parentEmail;
    private String parentPhone;
    /**
     * Constructor for creating a disciplinary note.
     * 
     * @param id unique note identifier
     * @param studentName name of the student
     * @param description description of disciplinary issue
     * @param date date of the incident
     * @param parentEmail parent's email for notification
     * @param parentPhone parent's phone for notification
     */
    public Note(String id, String studentName, String description, String date, 
                String parentEmail, String parentPhone) {
        this.id = id;
        this.studentName = studentName;
        this.description = description;
        this.date = date;
        this.parentEmail = parentEmail;
        this.parentPhone = parentPhone;
    }
    // Getters for note properties
    public String getId() { return id; }
    public String getStudentName() { return studentName; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getParentEmail() { return parentEmail; }
    public String getParentPhone() { return parentPhone; }
}