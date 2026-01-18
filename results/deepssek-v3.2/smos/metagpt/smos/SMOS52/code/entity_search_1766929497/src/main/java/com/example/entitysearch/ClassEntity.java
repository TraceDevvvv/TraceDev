package com.example.entitysearch;

/**
 * 班级实体类
 * 表示系统中的班级实体，继承自Entity基类
 * 包含班级特有的属性和方法
 */
public class ClassEntity extends Entity {
    private String teacherName;      // 班主任姓名
    private int studentCount;        // 学生人数
    private String grade;            // 年级
    private String department;       // 所属院系
    
    /**
     * 构造函数
     * 
     * @param id 班级唯一标识
     * @param name 班级名称
     * @param description 班级描述
     * @param teacherName 班主任姓名
     * @param studentCount 学生人数
     * @param grade 年级
     * @param department 所属院系
     */
    public ClassEntity(String id, String name, String description, 
                      String teacherName, int studentCount, String grade, String department) {
        super(id, name, description);
        this.teacherName = teacherName;
        this.studentCount = studentCount;
        this.grade = grade;
        this.department = department;
    }
    
    /**
     * 获取实体类型
     * 实现父类的抽象方法
     * 
     * @return 实体类型字符串 "班级"
     */
    @Override
    public String getType() {
        return "班级";
    }
    
    /**
     * 检查班级实体是否包含指定的关键词
     * 覆盖父类方法，添加班级特有字段的搜索
     * 
     * @param keyword 要搜索的关键词
     * @return 如果班级包含关键词返回true，否则返回false
     */
    @Override
    public boolean containsKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        
        String lowerKeyword = keyword.toLowerCase().trim();
        
        // 首先调用父类的搜索逻辑
        if (super.containsKeyword(keyword)) {
            return true;
        }
        
        // 检查班主任姓名是否包含关键词
        if (teacherName != null && teacherName.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查年级是否包含关键词
        if (grade != null && grade.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查院系是否包含关键词
        if (department != null && department.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查学生人数（如果关键词是数字）
        try {
            int keywordNumber = Integer.parseInt(keyword);
            if (studentCount == keywordNumber) {
                return true;
            }
        } catch (NumberFormatException e) {
            // 关键词不是数字，忽略
        }
        
        return false;
    }
    
    /**
     * 获取用于显示的班级信息
     * 覆盖父类方法，提供更详细的班级信息
     * 
     * @return 格式化的班级显示信息
     */
    @Override
    public String getDisplayInfo() {
        return name + " (" + grade + "年级) - 班主任: " + teacherName + 
               ", 学生: " + studentCount + "人, 院系: " + department;
    }
    
    /**
     * 获取详细的班级信息
     * 覆盖父类方法，提供完整的班级信息
     * 
     * @return 详细的班级信息字符串
     */
    @Override
    public String getDetailedInfo() {
        return getType() + " ID: " + id + 
               ", 名称: " + name + 
               ", 描述: " + description + 
               ", 班主任: " + teacherName + 
               ", 学生人数: " + studentCount + 
               ", 年级: " + grade + 
               ", 院系: " + department;
    }
    
    // Getter和Setter方法
    
    public String getTeacherName() {
        return teacherName;
    }
    
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    
    public int getStudentCount() {
        return studentCount;
    }
    
    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
    
    public String getGrade() {
        return grade;
    }
    
    public void setGrade(String grade) {
        this.grade = grade;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
}