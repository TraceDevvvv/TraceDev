package com.example.entitysearch;

/**
 * 用户实体类
 * 表示系统中的用户实体，继承自Entity基类
 * 包含用户相关的属性和方法，如用户名、邮箱、角色等
 */
public class User extends Entity {
    private String username;         // 用户名
    private String email;            // 邮箱地址
    private String role;             // 用户角色（管理员、教师、学生等）
    private String phoneNumber;      // 电话号码
    private boolean isActive;        // 用户是否激活
    private String department;       // 所属部门
    
    /**
     * 构造函数
     * 
     * @param id 用户唯一标识
     * @param name 用户姓名
     * @param description 用户描述
     * @param username 用户名
     * @param email 邮箱地址
     * @param role 用户角色
     * @param phoneNumber 电话号码
     * @param isActive 用户是否激活
     * @param department 所属部门
     */
    public User(String id, String name, String description,
               String username, String email, String role,
               String phoneNumber, boolean isActive, String department) {
        super(id, name, description);
        this.username = username;
        this.email = email;
        this.role = role;
        this.phoneNumber = phoneNumber;
        this.isActive = isActive;
        this.department = department;
    }
    
    /**
     * 获取实体类型
     * 实现父类的抽象方法
     * 
     * @return 实体类型字符串 "用户"
     */
    @Override
    public String getType() {
        return "用户";
    }
    
    /**
     * 检查用户实体是否包含指定的关键词
     * 覆盖父类方法，添加用户特有字段的搜索
     * 
     * @param keyword 要搜索的关键词
     * @return 如果用户包含关键词返回true，否则返回false
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
        
        // 检查用户名是否包含关键词
        if (username != null && username.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查邮箱地址是否包含关键词
        if (email != null && email.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查用户角色是否包含关键词
        if (role != null && role.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查电话号码是否包含关键词
        if (phoneNumber != null && phoneNumber.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查部门是否包含关键词
        if (department != null && department.toLowerCase().contains(lowerKeyword)) {
            return true;
        }
        
        // 检查激活状态（关键词为"激活"或"未激活"）
        if ("激活".equalsIgnoreCase(keyword) && isActive) {
            return true;
        }
        if ("未激活".equalsIgnoreCase(keyword) && !isActive) {
            return true;
        }
        if ("active".equalsIgnoreCase(keyword) && isActive) {
            return true;
        }
        if ("inactive".equalsIgnoreCase(keyword) && !isActive) {
            return true;
        }
        
        return false;
    }
    
    /**
     * 获取用于显示的用户信息
     * 覆盖父类方法，提供更详细的用户信息
     * 
     * @return 格式化的用户显示信息
     */
    @Override
    public String getDisplayInfo() {
        String status = isActive ? "激活" : "未激活";
        return name + " (" + username + ") - 角色: " + role + 
               ", 邮箱: " + email + ", 状态: " + status + 
               (department != null && !department.isEmpty() ? ", 部门: " + department : "");
    }
    
    /**
     * 获取详细的用户信息
     * 覆盖父类方法，提供完整的用户信息
     * 
     * @return 详细的用户信息字符串
     */
    @Override
    public String getDetailedInfo() {
        return getType() + " ID: " + id + 
               ", 姓名: " + name + 
               ", 描述: " + description + 
               ", 用户名: " + username + 
               ", 邮箱: " + email + 
               ", 角色: " + role + 
               ", 电话: " + phoneNumber + 
               ", 状态: " + (isActive ? "激活" : "未激活") + 
               ", 部门: " + department;
    }
    
    /**
     * 获取用户状态描述
     * 
     * @return 用户状态描述字符串
     */
    public String getStatusDescription() {
        return isActive ? "用户已激活，可以正常使用系统" : "用户未激活，需要管理员激活后才能使用";
    }
    
    // Getter和Setter方法
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
}