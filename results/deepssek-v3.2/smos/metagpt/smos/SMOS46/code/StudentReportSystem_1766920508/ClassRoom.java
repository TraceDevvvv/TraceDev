/**
 * ClassRoom.java
 *    ，              
 *   ：  ClassRoom   Class，    Java     
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassRoom {
    private int id;                     //   ID
    private String className;           //     
    private String academicYear;        //   
    private String teacherName;         //      
    private List<Student> students;     //     
    private int maxStudents;            //      

    /**
     *       
     */
    public ClassRoom() {
        this.id = 0;
        this.className = "";
        this.academicYear = "2023-2024"; //     
        this.teacherName = "";
        this.students = new ArrayList<>();
        this.maxStudents = 30; //     30   
    }

    /**
     *         
     * @param id   ID
     * @param className     
     * @param academicYear   
     */
    public ClassRoom(int id, String className, String academicYear) {
        this.id = id;
        this.className = className;
        this.academicYear = academicYear;
        this.teacherName = "";
        this.students = new ArrayList<>();
        this.maxStudents = 30;
    }

    /**
     *        
     * @param id   ID
     * @param className     
     * @param academicYear   
     * @param teacherName      
     * @param maxStudents      
     */
    public ClassRoom(int id, String className, String academicYear, String teacherName, int maxStudents) {
        this.id = id;
        this.className = className;
        this.academicYear = academicYear;
        this.teacherName = teacherName;
        this.students = new ArrayList<>();
        this.maxStudents = maxStudents;
    }

    // Getter   Setter   

    /**
     *     ID
     * @return   ID
     */
    public int getId() {
        return id;
    }

    /**
     *     ID
     * @param id   ID
     */
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("  ID     ");
        }
        this.id = id;
    }

    /**
     *       
     * @return     
     */
    public String getClassName() {
        return className;
    }

    /**
     *       
     * @param className     
     */
    public void setClassName(String className) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("        ");
        }
        this.className = className.trim();
    }

    /**
     *     
     * @return   
     */
    public String getAcademicYear() {
        return academicYear;
    }

    /**
     *     
     * @param academicYear   
     */
    public void setAcademicYear(String academicYear) {
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("      ");
        }
        this.academicYear = academicYear.trim();
    }

    /**
     *        
     * @return      
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     *        
     * @param teacherName      
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName != null ? teacherName.trim() : "";
    }

    /**
     *       （           ）
     * @return        
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     *        
     * @return      
     */
    public int getMaxStudents() {
        return maxStudents;
    }

    /**
     *        
     * @param maxStudents      
     */
    public void setMaxStudents(int maxStudents) {
        if (maxStudents < 1) {
            throw new IllegalArgumentException("         0");
        }
        this.maxStudents = maxStudents;
    }

    /**
     *        
     * @param student       
     * @return         true，    false
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            System.err.println("  ：         ");
            return false;
        }
        
        if (students.size() >= maxStudents) {
            System.err.println("  ：    ，        ");
            return false;
        }
        
        //             
        if (students.contains(student)) {
            System.err.println("  ：         ");
            return false;
        }
        
        //        ID
        student.setClassId(this.id);
        students.add(student);
        return true;
    }

    /**
     *         
     * @param studentId       ID
     * @return         true，    false
     */
    public boolean removeStudent(int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId) {
                students.remove(i);
                return true;
            }
        }
        System.err.println("  ：   ID  " + studentId + "    ");
        return false;
    }

    /**
     *     ID    
     * @param studentId   ID
     * @return        ，        null
     */
    public Student findStudentById(int studentId) {
        for (Student student : students) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    /**
     *           
     * @param studentName     
     * @return        （        ）
     */
    public List<Student> findStudentsByName(String studentName) {
        List<Student> result = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(studentName)) {
                result.add(student);
            }
        }
        return result;
    }

    /**
     *         
     * @return     
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     *         
     * @return         true，    false
     */
    public boolean isFull() {
        return students.size() >= maxStudents;
    }

    /**
     *       
     */
    public void displayClassInfo() {
        System.out.println("===      ===");
        System.out.println("  ID: " + id);
        System.out.println("    : " + className);
        System.out.println("  : " + academicYear);
        System.out.println("   : " + (teacherName.isEmpty() ? "   " : teacherName));
        System.out.println("    : " + students.size() + "/" + maxStudents);
        System.out.println("  : " + (isFull() ? "  " : "  "));
    }

    /**
     *           
     */
    public void displayAllStudents() {
        System.out.println("===    " + className + "       ===");
        if (students.isEmpty()) {
            System.out.println("       ");
            return;
        }
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + ". " + student.getSimpleInfo());
        }
    }

    /**
     *          
     * @return       
     */
    public String getSimpleInfo() {
        return String.format("  ID:%d   :%s (  :%s,   :%d/%d)", 
                id, className, academicYear, students.size(), maxStudents);
    }

    /**
     *             
     * @param currentYear     
     * @return             true
     */
    public boolean isCurrentAcademicYear(String currentYear) {
        return this.academicYear.equals(currentYear);
    }

    /**
     *           ID  
     * @return   ID  
     */
    public List<Integer> getAllStudentIds() {
        List<Integer> ids = new ArrayList<>();
        for (Student student : students) {
            ids.add(student.getId());
        }
        return ids;
    }

    /**
     *           
     */
    public void clearAllStudents() {
        students.clear();
        System.out.println("      " + className + "      ");
    }

    /**
     *   equals  ，  ID      
     * @param obj       
     * @return   ID     true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ClassRoom classRoom = (ClassRoom) obj;
        return id == classRoom.id;
    }

    /**
     *   hashCode  ，  ID     
     * @return    
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     *   toString  ，            
     * @return        
     */
    @Override
    public String toString() {
        return "ClassRoom{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", studentCount=" + students.size() +
                ", maxStudents=" + maxStudents +
                '}';
    }

    /**
     *           
     * @return   ID、          ，   true
     */
    public boolean isValid() {
        return id > 0 && 
               className != null && !className.trim().isEmpty() &&
               academicYear != null && !academicYear.trim().isEmpty();
    }
}