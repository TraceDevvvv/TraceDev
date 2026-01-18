/**
 * Student类 - 表示学生
 * 包含学生的基本信息和关联的家长信息
 */
public class Student {
    private String studentId;
    private String name;
    private int grade;
    private String className;
    private Parent parent;
    
    /**
     * 构造函数
     * @param studentId 学生ID
     * @param name 学生姓名
     * @param parent 家长对象
     */
    public Student(String studentId, String name, Parent parent) {
        this.studentId = studentId;
        this.name = name;
        this.parent = parent;
        this.grade = 1; // 默认年级
        this.className = "A班"; // 默认班级
    }
    
    /**
     * 完整构造函数
     * @param studentId 学生ID
     * @param name 学生姓名
     * @param grade 年级
     * @param className 班级名称
     * @param parent 家长对象
     */
    public Student(String studentId, String name, int grade, String className, Parent parent) {
        this.studentId = studentId;
        this.name = name;
        this.grade = grade;
        this.className = className;
        this.parent = parent;
    }
    
    /**
     * 检查学生信息是否完整有效
     * @return 信息是否有效
     */
    public boolean validate() {
        if (studentId == null || studentId.trim().isEmpty()) {
            System.out.println("错误：学生ID不能为空。");
            return false;
        }
        
        if (name == null || name.trim().isEmpty()) {
            System.out.println("错误：学生姓名不能为空。");
            return false;
        }
        
        if (parent == null) {
            System.out.println("错误：学生必须有关联的家长信息。");
            return false;
        }
        
        if (!parent.validate()) {
            System.out.println("错误：家长信息无效。");
            return false;
        }
        
        if (grade < 1 || grade > 12) {
            System.out.println("警告：年级应在1-12范围内。");
        }
        
        return true;
    }
    
    /**
     * 获取学生完整信息
     * @return 格式化后的学生信息
     */
    public String getFullInfo() {
        return String.format("学生ID: %s | 姓名: %s | 年级: %d | 班级: %s | 家长: %s",
                           studentId, name, grade, className, parent.getName());
    }
    
    // Getter和Setter方法
    
    public String getStudentId() {
        return studentId;
    }
    
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public Parent getParent() {
        return parent;
    }
    
    public void setParent(Parent parent) {
        this.parent = parent;
    }
    
    @Override
    public String toString() {
        return String.format("Student{studentId='%s', name='%s', grade=%d, className='%s', parent=%s}",
                           studentId, name, grade, className, parent);
    }
}

/**
 * Parent类 - 表示学生家长
 * 包含家长的基本信息和联系方式
 */
class Parent {
    private String parentId;
    private String name;
    private String email;
    private String phone;
    
    /**
     * 构造函数
     * @param parentId 家长ID
     * @param name 家长姓名
     * @param email 邮箱地址
     */
    public Parent(String parentId, String name, String email) {
        this.parentId = parentId;
        this.name = name;
        this.email = email;
        this.phone = "未设置";
    }
    
    /**
     * 完整构造函数
     * @param parentId 家长ID
     * @param name 家长姓名
     * @param email 邮箱地址
     * @param phone 电话号码
     */
    public Parent(String parentId, String name, String email, String phone) {
        this.parentId = parentId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    /**
     * 验证家长信息是否有效
     * @return 信息是否有效
     */
    public boolean validate() {
        if (parentId == null || parentId.trim().isEmpty()) {
            System.out.println("错误：家长ID不能为空。");
            return false;
        }
        
        if (name == null || name.trim().isEmpty()) {
            System.out.println("错误：家长姓名不能为空。");
            return false;
        }
        
        if (email == null || email.trim().isEmpty()) {
            System.out.println("错误：家长邮箱不能为空。");
            return false;
        }
        
        // 简单的邮箱格式验证
        if (!email.contains("@")) {
            System.out.println("警告：邮箱格式可能不正确：" + email);
        }
        
        return true;
    }
    
    /**
     * 获取家长联系方式
     * @return 格式化后的联系方式
     */
    public String getContactInfo() {
        return String.format("姓名: %s | 邮箱: %s | 电话: %s", name, email, phone);
    }
    
    // Getter和Setter方法
    
    public String getParentId() {
        return parentId;
    }
    
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Override
    public String toString() {
        return String.format("Parent{parentId='%s', name='%s', email='%s', phone='%s'}",
                           parentId, name, email, phone);
    }
}