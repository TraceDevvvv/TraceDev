import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 登录系统主类
 * 实现用例中的所有需求：
 * 1. 检查用户名和密码长度>=5
 * 2. 在用户数据库中搜索匹配项
 * 3. 登录成功显示工作空间并断开SMOS连接
 */
public class LoginSystem {
    
    /**
     * 用户类，表示一个可登录的用户
     */
    static class User {
        private String username;
        private String password;
        
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public String getUsername() {
            return username;
        }
        
        public String getPassword() {
            return password;
        }
    }
    
    /**
     * 模拟用户数据库
     * 使用HashMap存储用户信息，键为用户名，值为User对象
     */
    private static Map<String, User> userDatabase;
    
    /**
     * 初始化用户数据库
     * 添加一些示例用户用于测试
     */
    private static void initializeDatabase() {
        userDatabase = new HashMap<>();
        // 添加示例用户
        userDatabase.put("alice123", new User("alice123", "password123"));
        userDatabase.put("bob456", new User("bob456", "securepass456"));
        userDatabase.put("charlie789", new User("charlie789", "mypassword789"));
        userDatabase.put("david101", new User("david101", "davidpass101"));
        userDatabase.put("emma202", new User("emma202", "emmapass202"));
    }
    
    /**
     * 验证用户名和密码长度是否大于等于5
     * @param username 用户名
     * @param password 密码
     * @return true如果长度符合要求，false否则
     */
    private static boolean validateLength(String username, String password) {
        return username.length() >= 5 && password.length() >= 5;
    }
    
    /**
     * 在数据库中搜索用户
     * @param username 输入的用户名
     * @param password 输入的密码
     * @return true如果找到匹配的用户，false否则
     */
    private static boolean searchUserInDatabase(String username, String password) {
        // 检查用户名是否存在
        if (!userDatabase.containsKey(username)) {
            return false;
        }
        
        // 验证密码是否匹配
        User user = userDatabase.get(username);
        return user.getPassword().equals(password);
    }
    
    /**
     * 模拟登录成功后的操作
     * 1. 显示注册用户的工作空间
     * 2. 中断与SMOS服务器的连接
     * @param username 登录成功的用户名
     */
    private static void performPostLoginActions(String username) {
        System.out.println("\n=== 登录成功 ===");
        System.out.println("欢迎，" + username + "!");
        System.out.println("正在加载您的工作空间...");
        
        // 模拟工作空间显示
        displayWorkspace(username);
        
        // 中断与SMOS服务器的连接
        disconnectFromSMOS();
        
        System.out.println("\n=== 登录流程完成 ===");
    }
    
    /**
     * 显示用户工作空间（模拟）
     * @param username 用户名
     */
    private static void displayWorkspace(String username) {
        System.out.println("\n--- 工作空间 ---");
        System.out.println("用户: " + username);
        System.out.println("1. 仪表板");
        System.out.println("2. 个人信息");
        System.out.println("3. 设置");
        System.out.println("4. 消息中心");
        System.out.println("5. 退出");
        System.out.println("----------------");
    }
    
    /**
     * 模拟中断与SMOS服务器的连接
     */
    private static void disconnectFromSMOS() {
        System.out.println("\n正在断开与SMOS服务器的连接...");
        System.out.println("连接已成功中断。");
    }
    
    /**
     * 处理登录流程
     * 按照用例中的事件序列执行：
     * 1. 验证长度
     * 2. 搜索用户
     * 3. 执行登录后操作
     */
    private static void handleLogin() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== 用户登录系统 ===");
        System.out.print("请输入用户名: ");
        String username = scanner.nextLine().trim();
        
        System.out.print("请输入密码: ");
        String password = scanner.nextLine().trim();
        
        // 步骤1: 检查用户名和密码长度是否大于等于5
        if (!validateLength(username, password)) {
            System.out.println("\n错误: 用户名和密码长度必须至少为5个字符！");
            System.out.println("请重新启动程序并输入有效的凭据。");
            return;
        }
        
        // 步骤2: 在数据库中搜索用户
        if (!searchUserInDatabase(username, password)) {
            System.out.println("\n错误: 用户名或密码不正确！");
            System.out.println("请检查您的凭据并重试。");
            return;
        }
        
        // 步骤3: 如果搜索成功，执行登录后操作
        performPostLoginActions(username);
    }
    
    /**
     * 主方法 - 程序入口点
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        // 初始化用户数据库
        initializeDatabase();
        
        // 处理登录流程
        handleLogin();
        
        System.out.println("\n程序结束。");
    }
}