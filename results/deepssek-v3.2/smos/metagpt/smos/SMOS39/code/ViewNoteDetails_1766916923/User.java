import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

/**
 * User类 - 处理用户认证和权限检查
 * 包含用户基本信息：用户名、密码、角色
 * 提供登录验证和权限检查功能，特别关注管理员角色验证
 */
public class User {
    private String username;
    private String password;
    private String role; // 用户角色：ADMIN, TEACHER, STUDENT
    private boolean isLoggedIn;
    private String userId;
    
    // 预定义的角色常量
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_TEACHER = "TEACHER";
    public static final String ROLE_STUDENT = "STUDENT";
    
    // 简单的内存数据库，存储用户信息（在实际应用中应使用持久化存储）
    private static final Map<String, User> USER_DATABASE = new HashMap<>();
    private static final Map<String, Set<String>> ROLE_PERMISSIONS = new HashMap<>();
    
    // 静态初始化块：初始化用户数据库和权限
    static {
        // 初始化用户数据库
        // 默认管理员账户
        USER_DATABASE.put("admin123", new User("admin123", "adminPass123", ROLE_ADMIN, "U001"));
        USER_DATABASE.put("teacher1", new User("teacher1", "teacherPass1", ROLE_TEACHER, "U002"));
        USER_DATABASE.put("student1", new User("student1", "studentPass1", ROLE_STUDENT, "U003"));
        
        // 初始化权限
        // 管理员权限
        Set<String> adminPermissions = new HashSet<>();
        adminPermissions.add("VIEW_ALL_NOTES");
        adminPermissions.add("EDIT_ALL_NOTES");
        adminPermissions.add("DELETE_ALL_NOTES");
        adminPermissions.add("VIEW_NOTE_DETAILS");
        adminPermissions.add("MANAGE_USERS");
        ROLE_PERMISSIONS.put(ROLE_ADMIN, adminPermissions);
        
        // 教师权限
        Set<String> teacherPermissions = new HashSet<>();
        teacherPermissions.add("VIEW_OWN_NOTES");
        teacherPermissions.add("EDIT_OWN_NOTES");
        teacherPermissions.add("VIEW_NOTE_DETAILS");
        ROLE_PERMISSIONS.put(ROLE_TEACHER, teacherPermissions);
        
        // 学生权限
        Set<String> studentPermissions = new HashSet<>();
        studentPermissions.add("VIEW_OWN_NOTES");
        studentPermissions.add("VIEW_NOTE_DETAILS");
        ROLE_PERMISSIONS.put(ROLE_STUDENT, studentPermissions);
    }
    
    // 默认构造函数
    public User() {
        this.isLoggedIn = false;
    }
    
    /**
     * 参数化构造函数（注册新用户时使用）
     * @param username 用户名
     * @param password 密码
     * @param role 用户角色
     * @param userId 用户ID
     */
    public User(String username, String password, String role, String userId) {
        this.username = username;
        this.password = password;
        this.role = role != null ? role : ROLE_STUDENT;
        this.userId = userId;
        this.isLoggedIn = false;
    }
    
    /**
     * 登录验证方法
     * @param username 用户名
     * @param password 密码
     * @return 登录成功返回User对象，失败返回null
     */
    public static User login(String username, String password) {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            System.out.println("错误：用户名和密码不能为空");
            return null;
        }
        
        // 检查用户是否存在
        User user = USER_DATABASE.get(username.trim());
        if (user == null) {
            System.out.println("错误：用户不存在 - " + username);
            return null;
        }
        
        // 验证密码
        if (!user.password.equals(password)) {
            System.out.println("错误：密码不正确");
            return null;
        }
        
        // 创建登录用户的副本，设置登录状态
        User loggedInUser = new User(user.username, user.password, user.role, user.userId);
        loggedInUser.isLoggedIn = true;
        
        System.out.println("登录成功！欢迎 " + user.username + " (" + user.role + ")");
        return loggedInUser;
    }
    
    /**
     * 用户登出
     */
    public void logout() {
        if (this.isLoggedIn) {
            System.out.println("用户 " + this.username + " 已登出");
            this.isLoggedIn = false;
        }
    }
    
    /**
     * 检查用户是否具有特定权限
     * @param permission 权限名称
     * @return 如果用户有权限返回true，否则返回false
     */
    public boolean hasPermission(String permission) {
        if (!isLoggedIn || role == null || permission == null) {
            return false;
        }
        
        Set<String> permissions = ROLE_PERMISSIONS.get(role);
        return permissions != null && permissions.contains(permission);
    }
    
    /**
     * 检查当前用户是否是管理员
     * @return 如果是管理员返回true，否则返回false
     */
    public boolean isAdmin() {
        return isLoggedIn && ROLE_ADMIN.equals(role);
    }
    
    /**
     * 检查当前用户是否是教师
     * @return 如果是教师返回true，否则返回false
     */
    public boolean isTeacher() {
        return isLoggedIn && ROLE_TEACHER.equals(role);
    }
    
    /**
     * 检查当前用户是否是学生
     * @return 如果是学生返回true，否则返回false
     */
    public boolean isStudent() {
        return isLoggedIn && ROLE_STUDENT.equals(role);
    }
    
    // Getter和Setter方法
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    // 注意：实际应用中密码应加密存储，这里为简单起见使用明文
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     * 获取用户的完整信息
     * @return 用户信息字符串
     */
    public String getUserInfo() {
        String loginStatus = isLoggedIn ? "已登录" : "未登录";
        return String.format(
            "=== User Information ===\n" +
            "User ID: %s\n" +
            "Username: %s\n" +
            "Role: %s\n" +
            "Login Status: %s\n" +
            "Permissions Count: %d\n" +
            "========================",
            userId, username, role, loginStatus, 
            ROLE_PERMISSIONS.getOrDefault(role, new HashSet<>()).size()
        );
    }
    
    /**
     * 注册新用户（简化版本）
     * @param username 用户名
     * @param password 密码
     * @param role 用户角色
     * @return 注册成功返回User对象，失败返回null
     */
    public static User register(String username, String password, String role) {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            System.out.println("错误：用户名和密码不能为空");
            return null;
        }
        
        if (USER_DATABASE.containsKey(username)) {
            System.out.println("错误：用户名已存在 - " + username);
            return null;
        }
        
        // 验证角色是否有效
        if (!ROLE_ADMIN.equals(role) && !ROLE_TEACHER.equals(role) && !ROLE_STUDENT.equals(role)) {
            System.out.println("错误：无效的角色 - " + role);
            return null;
        }
        
        // 生成用户ID
        String userId = "U" + String.format("%03d", USER_DATABASE.size() + 1);
        
        // 创建新用户
        User newUser = new User(username, password, role, userId);
        
        // 添加到用户数据库
        USER_DATABASE.put(username, newUser);
        
        System.out.println("用户注册成功！用户名: " + username + ", 角色: " + role);
        return newUser;
    }
    
    /**
     * 验证密码强度（简化版本）
     * @param password 密码
     * @return 如果密码强度足够返回true，否则返回false
     */
    public static boolean isPasswordStrong(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        
        // 检查是否包含至少一个数字和一个字母
        boolean hasDigit = false;
        boolean hasLetter = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }
        
        return hasDigit && hasLetter;
    }
    
    /**
     * 获取用户权限列表
     * @return 权限集合
     */
    public Set<String> getPermissions() {
        if (!isLoggedIn || role == null) {
            return new HashSet<>();
        }
        
        Set<String> permissions = ROLE_PERMISSIONS.get(role);
        return permissions != null ? new HashSet<>(permissions) : new HashSet<>();
    }
    
    /**
     * 检查用户是否能够执行ViewNoteDetails操作
     * 根据用例要求，只有管理员可以查看笔记详情
     * @return 如果用户可以查看笔记详情返回true
     */
    public boolean canViewNoteDetails() {
        return isAdmin() || hasPermission("VIEW_NOTE_DETAILS");
    }
    
    /**
     * 重置用户密码（简化版本）
     * @param username 用户名
     * @param newPassword 新密码
     * @return 重置成功返回true，失败返回false
     */
    public static boolean resetPassword(String username, String newPassword) {
        User user = USER_DATABASE.get(username);
        if (user == null) {
            System.out.println("错误：用户不存在 - " + username);
            return false;
        }
        
        if (!isPasswordStrong(newPassword)) {
            System.out.println("错误：密码强度不足，必须至少6位且包含字母和数字");
            return false;
        }
        
        user.setPassword(newPassword);
        System.out.println("密码重置成功！用户: " + username);
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("User[ID:%s, Username:%s, Role:%s, LoggedIn:%s]", 
                userId, username, role, isLoggedIn);
    }
    
    /**
     * 获取用户统计信息
     * @return 包含用户数量的字符串
     */
    public static String getUserStatistics() {
        int adminCount = 0;
        int teacherCount = 0;
        int studentCount = 0;
        
        for (User user : USER_DATABASE.values()) {
            if (ROLE_ADMIN.equals(user.role)) {
                adminCount++;
            } else if (ROLE_TEACHER.equals(user.role)) {
                teacherCount++;
            } else if (ROLE_STUDENT.equals(user.role)) {
                studentCount++;
            }
        }
        
        return String.format("User Statistics: Total=%d, Admins=%d, Teachers=%d, Students=%d",
                USER_DATABASE.size(), adminCount, teacherCount, studentCount);
    }
}