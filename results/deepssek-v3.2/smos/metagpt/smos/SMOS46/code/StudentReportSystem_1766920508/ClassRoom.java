/**
 * ClassRoom.java
 * 班级类，包含班级的基本信息和学生列表
 * 注意：使用ClassRoom而不是Class，以避免与Java关键字冲突
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ClassRoom {
    private int id;                     // 班级ID
    private String className;           // 班级名称
    private String academicYear;        // 学年
    private String teacherName;         // 班主任姓名
    private List<Student> students;     // 学生列表
    private int maxStudents;            // 最大学生数

    /**
     * 默认构造函数
     */
    public ClassRoom() {
        this.id = 0;
        this.className = "";
        this.academicYear = "2023-2024"; // 默认学年
        this.teacherName = "";
        this.students = new ArrayList<>();
        this.maxStudents = 30; // 默认最大30名学生
    }

    /**
     * 带参数的构造函数
     * @param id 班级ID
     * @param className 班级名称
     * @param academicYear 学年
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
     * 完整的构造函数
     * @param id 班级ID
     * @param className 班级名称
     * @param academicYear 学年
     * @param teacherName 班主任姓名
     * @param maxStudents 最大学生数
     */
    public ClassRoom(int id, String className, String academicYear, String teacherName, int maxStudents) {
        this.id = id;
        this.className = className;
        this.academicYear = academicYear;
        this.teacherName = teacherName;
        this.students = new ArrayList<>();
        this.maxStudents = maxStudents;
    }

    // Getter 和 Setter 方法

    /**
     * 获取班级ID
     * @return 班级ID
     */
    public int getId() {
        return id;
    }

    /**
     * 设置班级ID
     * @param id 班级ID
     */
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("班级ID不能为负数");
        }
        this.id = id;
    }

    /**
     * 获取班级名称
     * @return 班级名称
     */
    public String getClassName() {
        return className;
    }

    /**
     * 设置班级名称
     * @param className 班级名称
     */
    public void setClassName(String className) {
        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException("班级名称不能为空");
        }
        this.className = className.trim();
    }

    /**
     * 获取学年
     * @return 学年
     */
    public String getAcademicYear() {
        return academicYear;
    }

    /**
     * 设置学年
     * @param academicYear 学年
     */
    public void setAcademicYear(String academicYear) {
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("学年不能为空");
        }
        this.academicYear = academicYear.trim();
    }

    /**
     * 获取班主任姓名
     * @return 班主任姓名
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 设置班主任姓名
     * @param teacherName 班主任姓名
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName != null ? teacherName.trim() : "";
    }

    /**
     * 获取学生列表（返回副本以防止外部修改）
     * @return 学生列表的副本
     */
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    /**
     * 获取最大学生数
     * @return 最大学生数
     */
    public int getMaxStudents() {
        return maxStudents;
    }

    /**
     * 设置最大学生数
     * @param maxStudents 最大学生数
     */
    public void setMaxStudents(int maxStudents) {
        if (maxStudents < 1) {
            throw new IllegalArgumentException("最大学生数必须大于0");
        }
        this.maxStudents = maxStudents;
    }

    /**
     * 添加学生到班级
     * @param student 要添加的学生
     * @return 如果添加成功返回true，否则返回false
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            System.err.println("错误：不能添加空学生对象");
            return false;
        }
        
        if (students.size() >= maxStudents) {
            System.err.println("错误：班级已满，无法添加更多学生");
            return false;
        }
        
        // 检查学生是否已经在班级中
        if (students.contains(student)) {
            System.err.println("错误：学生已存在于班级中");
            return false;
        }
        
        // 设置学生的班级ID
        student.setClassId(this.id);
        students.add(student);
        return true;
    }

    /**
     * 从班级中移除学生
     * @param studentId 要移除的学生ID
     * @return 如果移除成功返回true，否则返回false
     */
    public boolean removeStudent(int studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId() == studentId) {
                students.remove(i);
                return true;
            }
        }
        System.err.println("错误：未找到ID为 " + studentId + " 的学生");
        return false;
    }

    /**
     * 根据学生ID查找学生
     * @param studentId 学生ID
     * @return 找到的学生对象，如果未找到则返回null
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
     * 根据学生姓名查找学生
     * @param studentName 学生姓名
     * @return 找到的学生列表（可能有同名的情况）
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
     * 获取班级学生数量
     * @return 学生数量
     */
    public int getStudentCount() {
        return students.size();
    }

    /**
     * 检查班级是否已满
     * @return 如果班级已满返回true，否则返回false
     */
    public boolean isFull() {
        return students.size() >= maxStudents;
    }

    /**
     * 显示班级信息
     */
    public void displayClassInfo() {
        System.out.println("=== 班级信息 ===");
        System.out.println("班级ID: " + id);
        System.out.println("班级名称: " + className);
        System.out.println("学年: " + academicYear);
        System.out.println("班主任: " + (teacherName.isEmpty() ? "未指定" : teacherName));
        System.out.println("学生数量: " + students.size() + "/" + maxStudents);
        System.out.println("状态: " + (isFull() ? "已满" : "未满"));
    }

    /**
     * 显示班级所有学生信息
     */
    public void displayAllStudents() {
        System.out.println("=== 班级 " + className + " 的学生列表 ===");
        if (students.isEmpty()) {
            System.out.println("班级中没有学生");
            return;
        }
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.println((i + 1) + ". " + student.getSimpleInfo());
        }
    }

    /**
     * 获取班级的简要信息
     * @return 格式化字符串
     */
    public String getSimpleInfo() {
        return String.format("班级ID:%d 名称:%s (学年:%s, 学生:%d/%d)", 
                id, className, academicYear, students.size(), maxStudents);
    }

    /**
     * 检查班级是否属于当前学年
     * @param currentYear 当前学年
     * @return 如果班级属于当前学年返回true
     */
    public boolean isCurrentAcademicYear(String currentYear) {
        return this.academicYear.equals(currentYear);
    }

    /**
     * 获取班级中所有学生的ID列表
     * @return 学生ID列表
     */
    public List<Integer> getAllStudentIds() {
        List<Integer> ids = new ArrayList<>();
        for (Student student : students) {
            ids.add(student.getId());
        }
        return ids;
    }

    /**
     * 清空班级中的所有学生
     */
    public void clearAllStudents() {
        students.clear();
        System.out.println("已清空班级 " + className + " 的所有学生");
    }

    /**
     * 重写equals方法，基于ID比较班级对象
     * @param obj 要比较的对象
     * @return 如果ID相同则返回true
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ClassRoom classRoom = (ClassRoom) obj;
        return id == classRoom.id;
    }

    /**
     * 重写hashCode方法，基于ID生成哈希值
     * @return 哈希值
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * 重写toString方法，提供班级信息的字符串表示
     * @return 班级信息字符串
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
     * 验证班级信息是否完整
     * @return 如果ID、班级名称和学年不为空，则返回true
     */
    public boolean isValid() {
        return id > 0 && 
               className != null && !className.trim().isEmpty() &&
               academicYear != null && !academicYear.trim().isEmpty();
    }
}