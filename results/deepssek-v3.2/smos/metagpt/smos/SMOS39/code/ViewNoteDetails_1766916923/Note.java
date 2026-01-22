import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Note  -          
 *          ：  、  、  、  
 */
public class Note {
    private String student;      //     
    private String description;  //     /  
    private String teacher;      //     
    private Date date;          //       
    private String noteId;      //   ID，      

    //      ，          
    private static final SimpleDateFormat DATE_FORMAT = 
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     *       
     */
    public Note() {
        this.date = new Date(); //         
    }

    /**
     *        
     * @param noteId   ID
     * @param student     
     * @param description     
     * @param teacher     
     * @param date     （     ）
     * @throws ParseException          
     */
    public Note(String noteId, String student, String description, 
                String teacher, String date) throws ParseException {
        this.noteId = noteId;
        this.student = student;
        this.description = description;
        this.teacher = teacher;
        this.date = DATE_FORMAT.parse(date);
    }

    /**
     *        （ Date  ）
     * @param noteId   ID
     * @param student     
     * @param description     
     * @param teacher     
     * @param date     （Date  ）
     */
    public Note(String noteId, String student, String description, 
                String teacher, Date date) {
        this.noteId = noteId;
        this.student = student;
        this.description = description;
        this.teacher = teacher;
        this.date = date;
    }

    // Getter Setter  

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) throws ParseException {
        this.date = DATE_FORMAT.parse(date);
    }

    public String getNoteId() {
        return noteId;
    }

    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    /**
     *             
     * @return           
     */
    public String getFormattedDate() {
        return DATE_FORMAT.format(date);
    }

    /**
     *         （          ）
     * @return         true，    false
     */
    public boolean isValid() {
        return student != null && !student.trim().isEmpty() &&
               description != null && !description.trim().isEmpty() &&
               teacher != null && !teacher.trim().isEmpty() &&
               date != null && noteId != null && !noteId.trim().isEmpty();
    }

    /**
     *            
     * @return        
     */
    public String getSummary() {
        return String.format("Note[ID:%s, Student:%s, Teacher:%s, Date:%s]", 
                noteId, student, teacher, getFormattedDate());
    }

    /**
     *            
     * @return        
     */
    public String getDetails() {
        return String.format(
            "=== Note Details ===\n" +
            "Note ID: %s\n" +
            "Student: %s\n" +
            "Description: %s\n" +
            "Teacher: %s\n" +
            "Date: %s\n" +
            "==================",
            noteId, student, description, teacher, getFormattedDate()
        );
    }

    /**
     *         
     * @return   Note    
     */
    public Note cloneNote() {
        return new Note(noteId, student, description, teacher, 
                       new Date(date.getTime()));
    }

    /**
     *           （  noteId）
     * @param obj       
     * @return   noteId     true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Note other = (Note) obj;
        return noteId != null && noteId.equals(other.noteId);
    }

    /**
     *         （  noteId）
     * @return    
     */
    @Override
    public int hashCode() {
        return noteId != null ? noteId.hashCode() : 0;
    }

    /**
     *           
     * @return         
     */
    @Override
    public String toString() {
        return getSummary();
    }
}