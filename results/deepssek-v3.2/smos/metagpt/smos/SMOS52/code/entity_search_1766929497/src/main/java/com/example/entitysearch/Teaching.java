package com.example.entitysearch;

/**
 * 教学实体类
 * 表示系统中的教学实体，继承自Entity基类
 * 包含教学相关的属性和方法，如课程、教师、学时等
 */
public class Teaching extends Entity {
    private String courseName;       // 课程名称
    private String teacherId;        // 教师ID
    private String teacherName;      // 教师姓名
    private int creditHours;         // 学时
    private String semester;         // 学期
    private String classroom;        // 教室
    
    /**
     * 构造函数
     * 
     * @param id 教学唯一标识
     * @param name 教学名称（通常与课程名称一致）
     * @param description 教学描述
     * @param courseName 课程名称
     * @param teacherId 教师ID
     * @param teacherName 教师姓名
     * @param creditHours 学时
     * @param semester 学期
     * @param classroom 教室
     */
    public Teaching(String id, String name, String description, 
                   String courseName, String teacherId, String teacherName,
                   int creditHours, String semester, String classroom) {
        super(id, name, description);
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.creditHours = creditHours;
        this.semester = semester;
        this.classroom = classroom;
    }
    
    /**
     * 获取实体类型
     * 实现父类的抽象方法
     * 
     * @return 实体类型字符串 "教学"
     */
    @Override
    public String getType() {
        return "教学";
    }
    
    /**
     * 检查教学实体是否包含指定的关键词
     * 覆盖父类方法，添加教学特有字段的搜索
     * 
     * @param keyword 要搜索的关键词
     * @return 如果教学包含关键词返回true，否则返回false
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
        
        // 检查课程名称是否包含关键词
        if (courseName != null && courseName.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查教师ID是否包含关键词
        if (teacherId != null && teacherId.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查教师姓名是否包含关键词
        if (teacherName != null && teacherName.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查学期是否包含关键词
        if (semester != null && semester.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查教室是否包含关键词
        if (classroom != null && classroom.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查学时（如果关键词是数字）
        try {
            int keywordNumber = Integer.parseInt(keyword);
            if (creditHours == keywordNumber) {
                return true;
            }
        } catch (NumberFormatException e) {
            // 关键词不是数字，忽略
        }
        
        return false;
    }
    
    /**
     * 获取用于显示的教学信息
     * 覆盖父类方法，提供更详细的教学信息
     * 
     * @return 格式化的教学显示信息
     */
    @Override
    public String getDisplayInfo() {
        return courseName + " - 教师: " + teacherName + 
               ", 学期: " + semester + 
               ", 教室: " + classroom + 
               ", 学时: " + creditHours + "小时";
    }
    
    /**
     * 获取详细的教学信息
     * 覆盖父类方法，提供完整的教学信息
     * 
     * @return 详细的教学信息字符串
     */
    @Override
    public String getDetailedInfo() {
        return getType() + " ID: " + id + 
               ", 教学名称: " + name + 
               ", 描述: " + description + 
               ", 课程名称: " + courseName + 
               ", 教师ID: " + teacherId + 
               ", 教师姓名: " + teacherName + 
               ", 学时: " + creditHours + 
               ", 学期: " + semester + 
               ", 教室: " + classroom;
    }
    
    // Getter和Setter方法
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getTeacherId() {
        return teacherId;
    }
    
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }
    
    public String getTeacherName() {
        return teacherName;
    }
    
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    
    public int getCreditHours() {
        return creditHours;
    }
    
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    public String getClassroom() {
        return classroom;
    }
    
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}