/**
 * ReportCard.java
 * 成绩单类，包含学生成绩单的详细信息
 * 包括科目、分数、评语、总分等
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Date;
import java.text.SimpleDateFormat;

public class ReportCard {
    private int id;                        // 成绩单ID
    private int studentId;                 // 学生ID
    private String studentName;            // 学生姓名（冗余存储，便于查询）
    private String academicYear;           // 学年
    private String semester;               // 学期（如：春季学期、秋季学期）
    private Date issueDate;                // 发布日期
    private Map<String, SubjectGrade> grades;  // 科目和成绩的映射
    private String teacherComment;         // 教师评语
    private String status;                 // 状态（如：已保存、已提交、已归档）
    private boolean isArchived;            // 是否已归档
    
    // 内部类，表示科目成绩
    public static class SubjectGrade {
        private String subjectName;        // 科目名称
        private double score;              // 分数（0-100）
        private String grade;              // 等级（如：A、B、C）
        private String comment;            // 科目评语
        
        /**
         * 构造函数
         * @param subjectName 科目名称
         * @param score 分数
         */
        public SubjectGrade(String subjectName, double score) {
            this.subjectName = subjectName;
            this.setScore(score);          // 使用setter设置分数，包含验证
            this.comment = "";
        }
        
        /**
         * 完整构造函数
         * @param subjectName 科目名称
         * @param score 分数
         * @param grade 等级
         * @param comment 评语
         */
        public SubjectGrade(String subjectName, double score, String grade, String comment) {
            this.subjectName = subjectName;
            this.setScore(score);
            this.grade = grade != null ? grade : "";
            this.comment = comment != null ? comment : "";
        }
        
        // Getter 和 Setter 方法
        public String getSubjectName() {
            return subjectName;
        }
        
        public void setSubjectName(String subjectName) {
            if (subjectName == null || subjectName.trim().isEmpty()) {
                throw new IllegalArgumentException("科目名称不能为空");
            }
            this.subjectName = subjectName.trim();
        }
        
        public double getScore() {
            return score;
        }
        
        public void setScore(double score) {
            if (score < 0 || score > 100) {
                throw new IllegalArgumentException("分数必须在0-100之间");
            }
            this.score = score;
            // 自动计算等级
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
         * 根据分数计算等级
         * @param score 分数
         * @return 等级
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
     * 默认构造函数
     */
    public ReportCard() {
        this.id = 0;
        this.studentId = 0;
        this.studentName = "";
        this.academicYear = "2023-2024";
        this.semester = "春季学期";
        this.issueDate = new Date(); // 默认为当前日期
        this.grades = new HashMap<>();
        this.teacherComment = "";
        this.status = "已保存";
        this.isArchived = false;
    }
    
    /**
     * 带参数的构造函数
     * @param id 成绩单ID
     * @param studentId 学生ID
     * @param studentName 学生姓名
     * @param academicYear 学年
     */
    public ReportCard(int id, int studentId, String studentName, String academicYear) {
        this();
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.academicYear = academicYear;
    }
    
    // Getter 和 Setter 方法
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("成绩单ID不能为负数");
        }
        this.id = id;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        if (studentId < 0) {
            throw new IllegalArgumentException("学生ID不能为负数");
        }
        this.studentId = studentId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        if (studentName == null || studentName.trim().isEmpty()) {
            throw new IllegalArgumentException("学生姓名不能为空");
        }
        this.studentName = studentName.trim();
    }
    
    public String getAcademicYear() {
        return academicYear;
    }
    
    public void setAcademicYear(String academicYear) {
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("学年不能为空");
        }
        this.academicYear = academicYear.trim();
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        if (semester == null || semester.trim().isEmpty()) {
            throw new IllegalArgumentException("学期不能为空");
        }
        this.semester = semester.trim();
    }
    
    public Date getIssueDate() {
        return issueDate;
    }
    
    public void setIssueDate(Date issueDate) {
        if (issueDate == null) {
            throw new IllegalArgumentException("发布日期不能为空");
        }
        this.issueDate = issueDate;
    }
    
    public Map<String, SubjectGrade> getGrades() {
        return new HashMap<>(grades); // 返回副本以防止外部修改
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
            throw new IllegalArgumentException("状态不能为空");
        }
        this.status = status.trim();
    }
    
    public boolean isArchived() {
        return isArchived;
    }
    
    public void setArchived(boolean archived) {
        isArchived = archived;
        if (archived) {
            this.status = "已归档";
        }
    }
    
    /**
     * 添加科目成绩
     * @param subjectName 科目名称
     * @param score 分数
     * @return 如果添加成功返回true，否则返回false
     */
    public boolean addGrade(String subjectName, double score) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            System.err.println("错误：科目名称不能为空");
            return false;
        }
        
        if (score < 0 || score > 100) {
            System.err.println("错误：分数必须在0-100之间");
            return false;
        }
        
        SubjectGrade grade = new SubjectGrade(subjectName, score);
        grades.put(subjectName.trim(), grade);
        return true;
    }
    
    /**
     * 添加带有评语的科目成绩
     * @param subjectName 科目名称
     * @param score 分数
     * @param comment 评语
     * @return 如果添加成功返回true，否则返回false
     */
    public boolean addGradeWithComment(String subjectName, double score, String comment) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            System.err.println("错误：科目名称不能为空");
            return false;
        }
        
        if (score < 0 || score > 100) {
            System.err.println("错误：分数必须在0-100之间");
            return false;
        }
        
        SubjectGrade grade = new SubjectGrade(subjectName, score, "", comment != null ? comment : "");
        grades.put(subjectName.trim(), grade);
        return true;
    }
    
    /**
     * 移除科目成绩
     * @param subjectName 科目名称
     * @return 如果移除成功返回true，否则返回false
     */
    public boolean removeGrade(String subjectName) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            System.err.println("错误：科目名称不能为空");
            return false;
        }
        
        if (grades.remove(subjectName.trim()) != null) {
            return true;
        } else {
            System.err.println("错误：未找到科目 \"" + subjectName + "\" 的成绩");
            return false;
        }
    }
    
    /**
     * 获取指定科目的成绩
     * @param subjectName 科目名称
     * @return 科目成绩对象，如果未找到则返回null
     */
    public SubjectGrade getGrade(String subjectName) {
        if (subjectName == null || subjectName.trim().isEmpty()) {
            return null;
        }
        return grades.get(subjectName.trim());
    }
    
    /**
     * 获取指定科目的分数
     * @param subjectName 科目名称
     * @return 分数，如果未找到则返回-1
     */
    public double getScore(String subjectName) {
        SubjectGrade grade = getGrade(subjectName);
        return grade != null ? grade.getScore() : -1;
    }
    
    /**
     * 获取平均分
     * @return 平均分，如果没有科目则返回0
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
     * 获取总分
     * @return 总分
     */
    public double getTotalScore() {
        double total = 0;
        for (SubjectGrade grade : grades.values()) {
            total += grade.getScore();
        }
        return total;
    }
    
    /**
     * 获取科目数量
     * @return 科目数量
     */
    public int getSubjectCount() {
        return grades.size();
    }
    
    /**
     * 获取最高分科目
     * @return 最高分科目对象，如果没有科目则返回null
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
     * 获取最低分科目
     * @return 最低分科目对象，如果没有科目则返回null
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
     * 检查是否所有科目都及格（分数>=60）
     * @return 如果所有科目都及格返回true，否则返回false
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
     * 显示成绩单详情
     */
    public void displayReportCard() {
        System.out.println("====================== 成绩单 ======================");
        System.out.println("成绩单ID: " + id);
        System.out.println("学生ID: " + studentId);
        System.out.println("学生姓名: " + studentName);
        System.out.println("学年: " + academicYear);
        System.out.println("学期: " + semester);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("发布日期: " + sdf.format(issueDate));
        System.out.println("状态: " + status);
        System.out.println("归档状态: " + (isArchived ? "已归档" : "未归档"));
        System.out.println();
        System.out.println("科目成绩:");
        
        if (grades.isEmpty()) {
            System.out.println("  暂无成绩记录");
        } else {
            int index = 1;
            for (SubjectGrade grade : grades.values()) {
                System.out.printf("  %d. %-15s: 分数=%-5.1f 等级=%-2s%n", 
                        index++, grade.getSubjectName(), grade.getScore(), grade.getGrade());
                if (!grade.getComment().isEmpty()) {
                    System.out.println("      评语: " + grade.getComment());
                }
            }
            
            System.out.println();
            System.out.printf("平均分: %.2f%n", getAverageScore());
            System.out.printf("总分: %.2f%n", getTotalScore());
            System.out.println("科目数量: " + getSubjectCount());
            
            if (getHighestScoreSubject() != null) {
                System.out.println("最高分科目: " + getHighestScoreSubject().getSubjectName() + 
                                 " (" + getHighestScoreSubject().getScore() + "分)");
            }
            if (getLowestScoreSubject() != null) {
                System.out.println("最低分科目: " + getLowestScoreSubject().getSubjectName() + 
                                 " (" + getLowestScoreSubject().getScore() + "分)");
            }
            
            System.out.println("所有科目及格状态: " + (isAllPassed() ? "全部及格" : "有不及格科目"));
        }
        
        if (!teacherComment.isEmpty()) {
            System.out.println();
            System.out.println("教师总评:");
            System.out.println("  " + teacherComment);
        }
        
        System.out.println("===================================================");
    }
    
    /**
     * 获取成绩单的简要信息
     * @return 格式化字符串
     */
    public String getSimpleInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("成绩单ID:%d 学生:%s (平均分:%.2f, 状态:%s)", 
                id, studentName, getAverageScore(), status);
    }
    
    /**
     * 清空所有成绩
     */
    public void clearAllGrades() {
        grades.clear();
        System.out.println("已清空所有科目成绩");
    }
    
    /**
     * 检查成绩单是否有效
     * @return 如果成绩单有基本信息和至少一个科目，则返回true
     */
    public boolean isValid() {
        return id > 0 && 
               studentId > 0 && 
               studentName != null && !studentName.trim().isEmpty() &&
               !grades.isEmpty();
    }
    
    /**
     * 归档成绩单
     */
    public void archive() {
        this.isArchived = true;
        this.status = "已归档";
        System.out.println("成绩单已归档");
    }
    
    /**
     * 提交成绩单（从保存状态变为已提交状态）
     * @return 如果提交成功返回true，否则返回false
     */
    public boolean submit() {
        if (grades.isEmpty()) {
            System.err.println("错误：无法提交空成绩单");
            return false;
        }
        
        if ("已提交".equals(status) || "已归档".equals(status)) {
            System.err.println("错误：成绩单已处于" + status + "状态，无法再次提交");
            return false;
        }
        
        this.status = "已提交";
        System.out.println("成绩单已提交");
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