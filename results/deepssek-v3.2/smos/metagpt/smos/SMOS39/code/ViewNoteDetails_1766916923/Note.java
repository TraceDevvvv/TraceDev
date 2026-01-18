import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Note类 - 表示笔记的数据模型
 * 包含笔记的基本信息：学生、描述、老师、日期
 */
public class Note {
    private String student;      // 学生姓名
    private String description;  // 笔记描述/内容
    private String teacher;      // 老师姓名
    private Date date;          // 笔记创建日期
    private String noteId;      // 笔记ID，用于唯一标识

    // 日期格式器，用于日期格式化和解析
    private static final SimpleDateFormat DATE_FORMAT = 
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 默认构造函数
     */
    public Note() {
        this.date = new Date(); // 默认使用当前日期
    }

    /**
     * 参数化构造函数
     * @param noteId 笔记ID
     * @param student 学生姓名
     * @param description 笔记描述
     * @param teacher 老师姓名
     * @param date 笔记日期（字符串格式）
     * @throws ParseException 如果日期格式不正确
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
     * 参数化构造函数（带Date对象）
     * @param noteId 笔记ID
     * @param student 学生姓名
     * @param description 笔记描述
     * @param teacher 老师姓名
     * @param date 笔记日期（Date对象）
     */
    public Note(String noteId, String student, String description, 
                String teacher, Date date) {
        this.noteId = noteId;
        this.student = student;
        this.description = description;
        this.teacher = teacher;
        this.date = date;
    }

    // Getter和Setter方法

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
     * 获取格式化后的日期字符串
     * @return 格式化后的日期字符串
     */
    public String getFormattedDate() {
        return DATE_FORMAT.format(date);
    }

    /**
     * 检查笔记是否有效（所有必需字段都不为空）
     * @return 如果笔记有效返回true，否则返回false
     */
    public boolean isValid() {
        return student != null && !student.trim().isEmpty() &&
               description != null && !description.trim().isEmpty() &&
               teacher != null && !teacher.trim().isEmpty() &&
               date != null && noteId != null && !noteId.trim().isEmpty();
    }

    /**
     * 获取笔记的简要信息摘要
     * @return 笔记摘要字符串
     */
    public String getSummary() {
        return String.format("Note[ID:%s, Student:%s, Teacher:%s, Date:%s]", 
                noteId, student, teacher, getFormattedDate());
    }

    /**
     * 获取笔记的完整详细信息
     * @return 笔记详情字符串
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
     * 克隆当前笔记对象
     * @return 新的Note对象副本
     */
    public Note cloneNote() {
        return new Note(noteId, student, description, teacher, 
                       new Date(date.getTime()));
    }

    /**
     * 比较两个笔记是否相等（基于noteId）
     * @param obj 要比较的对象
     * @return 如果noteId相同则返回true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Note other = (Note) obj;
        return noteId != null && noteId.equals(other.noteId);
    }

    /**
     * 返回笔记的哈希码（基于noteId）
     * @return 哈希码
     */
    @Override
    public int hashCode() {
        return noteId != null ? noteId.hashCode() : 0;
    }

    /**
     * 返回笔记的字符串表示
     * @return 笔记的字符串表示
     */
    @Override
    public String toString() {
        return getSummary();
    }
}