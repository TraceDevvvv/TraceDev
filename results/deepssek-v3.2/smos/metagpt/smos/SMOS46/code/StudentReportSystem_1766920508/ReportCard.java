/**
 * ReportCard.java
 *     ，            
 *     、  、  、   
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ReportCard {
    private int id;                        //    ID
    private int studentId;                 //   ID
    private String studentName;            //     （    ，    ）
    private String academicYear;           //   
    private String semester;               //   （ ：    、    ）
    private Date issueDate;                //     
    private Map<String, SubjectGrade> grades;  //         
    private String teacherComment;         //     
    private String status;                 //   （ ：   、   、   ）
    private boolean isArchived;            //      
    
    //    ，      
    public static class SubjectGrade {
        private String subjectName;        //     
        private double score;              //   （0-100）
        private String grade;              //   （ ：A、B、C）
        private String comment;            //     
        
        /**
         *     
         * @param subjectName     
         * @param score   
         */
        public SubjectGrade(String subjectName, double score) {
            this.subjectName = subjectName;
            this.setScore(score);          //   setter    ，    
            this.comment = "";
        }
        
        /**
         *       
         * @param subjectName     
         * @param score   
         * @param grade   
         * @param comment   
         */
        public SubjectGrade(String subjectName, double score, String grade, String comment) {
            this.subjectName = subjectName;
            this.setScore(score);
            this.grade = grade != null ? grade : "";
            this.comment = comment != null ? comment : "";
        }
        
        // Getter   Setter   
        public String getSubjectName() {
            return subjectName;
        }
        
        public void setSubjectName(String subjectName) {
            if (subjectName == null || subjectName.trim().isEmpty()) {
                throw new IllegalArgumentException("        ");
            }
            this.subjectName = subjectName.trim();
        }
        
        public double getScore() {
            return score;
        }
        
        public void setScore(double score) {
            if (score < 0 || score > 100) {
                throw new IllegalArgumentException("     0-100  ");
            }
            this.score = score;
            //       
            this.grade = calculateGrade(score);
        }
        
        public String getGrade() {
            return grade;
        }
        
        public void setGrade(String grade) {
            this.grade = grade != null ? grade : "";
        }
        
        public String getComment() {
            return comment;
        }
        
        public void setComment(String comment) {
            this.comment = comment != null ? comment : "";
        }
        
        /**
         *         
         * @param score   
         * @return   
         */
        private String calculateGrade(double score) {
            if (score >= 90) return "A";
            else if (score >= 80) return "B";
            else if (score >= 70) return "C";
            else if (score >= 60) return "D";
            else return "F";
        }
        
        @Override
        public String toString() {
            return "SubjectGrade{" +
                    "subjectName='" + subjectName + '\'' +
                    ", score=" + score +
                    ", grade='" + grade + '\'' +
                    ", comment='" + comment + '\'' +
                    '}';
        }
    }
    
    /**
     *       
     */
    public ReportCard() {
        this.id = 0;
        this.studentId = 0;
        this.studentName = "";
        this.academicYear = "2023-2024";
        this.semester = "    ";
        this.issueDate = new Date(); //        
        this.grades = new HashMap<>();
        this.teacherComment = "";
        this.status = "   ";
        this.isArchived = false;
    }
    
    /**
     *         
     * @param id    ID
     * @param studentId   ID
     * @param studentName     
     * @param academicYear   
     */
    public ReportCard(int id, int studentId, String studentName, String academicYear) {
        this();
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.academicYear = academicYear;
    }
    
    // Getter   Setter   
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("   ID     ");
        }
        this.id = id;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        if (studentId < 0) {
            throw new IllegalArgumentException("  ID     ");
        }
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("        ");
        }
        this.studentName = studentName.trim();
    }
    
    public String getAcademicYear() {
        return academicYear;
    }
    
    public void setAcademicYear(String academicYear) {
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("      ");
        }
        this.academicYear = academicYear.trim();
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        if (semester == null || semester.trim().isEmpty()) {
            throw new IllegalArgumentException("      ");
        }
        this.semester = semester.trim();
    }
    
    public Date getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(Date issueDate) {
        if (issueDate == null) {
            throw new IllegalArgumentException("        ");
        }
        this.issueDate = issueDate;
    }
    
    public Map<String, SubjectGrade> getGrades() {
        return new HashMap<>(grades); //            
    }
    
    public String getTeacherComment() {
        return teacherComment;
    }
    
    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment != null ? teacherComment.trim() : "";
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("      ");
        }
        this.status = status.trim();
    }
    
    public boolean isArchived() {
        return isArchived;
    }
    
    public void setArchived(boolean archived) {
        isArchived = archived;
        if (archived) {
            this.status = "   ";
        }
    }
    
    /**
     *       
     * @param subjectName     
     * @param score   
     * @return         true，    false
     */
    public boolean addGrade(String subjectName, double score) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            System.err.println("  ：        ");
            return false;
        }
        
        if (score < 0 || score > 100) {
            System.err.println("  ：     0-100  ");
            return false;
        }
        
        SubjectGrade grade = new SubjectGrade(subjectName, score);
        grades.put(subjectName.trim(), grade);
        return true;
    }
    
    /**
     *            
     * @param subjectName     
     * @param score   
     * @param comment   
     * @return         true，    false
     */
    public boolean addGradeWithComment(String subjectName, double score, String comment) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            System.err.println("  ：        ");
            return false;
        }
        
        if (score < 0 || score > 100) {
            System.err.println("  ：     0-100  ");
            return false;
        }
        
        SubjectGrade grade = new SubjectGrade(subjectName, score, "", comment != null ? comment : "");
        grades.put(subjectName.trim(), grade);
        return true;
    }
    
    /**
     *       
     * @param subjectName     
     * @return         true，    false
     */
    public boolean removeGrade(String subjectName) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            System.err.println("  ：        ");
            return false;
        }
        
        if (grades.remove(subjectName.trim()) != null) {
            return true;
        } else {
            System.err.println("  ：      \"" + subjectName + "\"    ");
            return false;
        }
    }
    
    /**
     *          
     * @param subjectName     
     * @return       ，        null
     */
    public SubjectGrade getGrade(String subjectName) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            return null;
        }
        return grades.get(subjectName.trim());
    }
    
    /**
     *          
     * @param subjectName     
     * @return   ，        -1
     */
    public double getScore(String subjectName) {
        SubjectGrade grade = getGrade(subjectName);
        return grade != null ? grade.getScore() : -1;
    }
    
    /**
     *      
     * @return    ，         0
     */
    public double getAverageScore() {
        if (grades.isEmpty()) {
            return 0;
        }
        
        double total = 0;
        for (SubjectGrade grade : grades.values()) {
            total += grade.getScore();
        }
        return total / grades.size();
    }
    
    /**
     *     
     * @return   
     */
    public double getTotalScore() {
        double total = 0;
        for (SubjectGrade grade : grades.values()) {
            total += grade.getScore();
        }
        return total;
    }
    
    /**
     *       
     * @return     
     */
    public int getSubjectCount() {
        return grades.size();
    }
    
    /**
     *        
     * @return        ，         null
     */
    public SubjectGrade getHighestScoreSubject() {
        if (grades.isEmpty()) {
            return null;
        }
        
        SubjectGrade highest = null;
        for (SubjectGrade grade : grades.values()) {
            if (highest == null || grade.getScore() > highest.getScore()) {
                highest = grade;
            }
        }
        return highest;
    }
    
    /**
     *        
     * @return        ，         null
     */
    public SubjectGrade getLowestScoreSubject() {
        if (grades.isEmpty()) {
            return null;
        }
        
        SubjectGrade lowest = null;
        for (SubjectGrade grade : grades.values()) {
            if (lowest == null || grade.getScore() < lowest.getScore()) {
                lowest = grade;
            }
        }
        return lowest;
    }
    
    /**
     *            （  >=60）
     * @return            true，    false
     */
    public boolean isAllPassed() {
        for (SubjectGrade grade : grades.values()) {
            if (grade.getScore() < 60) {
                return false;
            }
        }
        return true;
    }
    
    /**
     *        
     */
    public void displayReportCard() {
        System.out.println("======================     ======================");
        System.out.println("   ID: " + id);
        System.out.println("  ID: " + studentId);
        System.out.println("    : " + studentName);
        System.out.println("  : " + academicYear);
        System.out.println("  : " + semester);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("    : " + sdf.format(issueDate));
        System.out.println("  : " + status);
        System.out.println("    : " + (isArchived ? "   " : "   "));
        System.out.println();
        System.out.println("    :");
        
        if (grades.isEmpty()) {
            System.out.println("        ");
        } else {
            int index = 1;
            for (SubjectGrade grade : grades.values()) {
                System.out.printf("  %d. %-15s:   =%-5.1f   =%-2s%n", 
                        index++, grade.getSubjectName(), grade.getScore(), grade.getGrade());
                if (!grade.getComment().isEmpty()) {
                    System.out.println("        : " + grade.getComment());
                }
            }
            
            System.out.println();
            System.out.printf("   : %.2f%n", getAverageScore());
            System.out.printf("  : %.2f%n", getTotalScore());
            System.out.println("    : " + getSubjectCount());
            
            if (getHighestScoreSubject() != null) {
                System.out.println("     : " + getHighestScoreSubject().getSubjectName() + 
                                 " (" + getHighestScoreSubject().getScore() + " )");
            }
            if (getLowestScoreSubject() != null) {
                System.out.println("     : " + getLowestScoreSubject().getSubjectName() + 
                                 " (" + getLowestScoreSubject().getScore() + " )");
            }
            
            System.out.println("        : " + (isAllPassed() ? "    " : "      "));
        }
        
        if (!teacherComment.isEmpty()) {
            System.out.println();
            System.out.println("    :");
            System.out.println("  " + teacherComment);
        }
        
        System.out.println("===================================================");
    }
    
    /**
     *           
     * @return       
     */
    public String getSimpleInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("   ID:%d   :%s (   :%.2f,   :%s)", 
                id, studentName, getAverageScore(), status);
    }
    
    /**
     *       
     */
    public void clearAllGrades() {
        grades.clear();
        System.out.println("         ");
    }
    
    /**
     *          
     * @return                  ，   true
     */
    public boolean isValid() {
        return id > 0 && 
               studentId > 0 && 
               studentName != null && !studentName.trim().isEmpty() &&
               !grades.isEmpty();
    }
    
    /**
     *      
     */
    public void archive() {
        this.isArchived = true;
        this.status = "   ";
        System.out.println("      ");
    }
    
    /**
     *      （            ）
     * @return         true，    false
     */
    public boolean submit() {
        if (grades.isEmpty()) {
            System.err.println("  ：        ");
            return false;
        }
        
        if ("   ".equals(status) || "   ".equals(status)) {
            System.err.println("  ：      " + status + "  ，      ");
            return false;
        }
        
        this.status = "   ";
        System.out.println("      ");
        return true;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ReportCard that = (ReportCard) obj;
        return id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "ReportCard{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", semester='" + semester + '\'' +
                ", issueDate=" + sdf.format(issueDate) +
                ", subjectCount=" + grades.size() +
                ", status='" + status + '\'' +
                ", isArchived=" + isArchived +
                '}';
    }
}