import java.util.Scanner;

/**
 * 用户会话管理类
 * 负责管理用户的登录状态
 */
class UserSession {
    private boolean isLoggedIn;
    private String username;
    
    /**
     * 构造函数，初始化用户会话
     */
    public UserSession() {
        this.isLoggedIn = false;
        this.username = null;
    }
    
    /**
     * 用户登录方法
     * @param username 用户名
     * @param password 密码
     * @return 登录是否成功
     */
    public boolean login(String username, String password) {
        // 简单的登录验证逻辑
        // 在实际应用中，这里应该连接数据库验证用户凭据
        if (username != null && !username.trim().isEmpty() && 
            password != null && !password.trim().isEmpty()) {
            this.username = username;
            this.isLoggedIn = true;
            System.out.println("用户 " + username + " 登录成功！");
            return true;
        } else {
            System.out.println("登录失败：用户名或密码不能为空");
            return false;
        }
    }
    
    /**
     * 用户注销方法
     * @return 注销是否成功
     */
    public boolean logout() {
        // 检查前置条件：用户必须已登录
        if (!isLoggedIn) {
            System.out.println("注销失败：用户未登录");
            return false;
        }
        
        // 执行注销操作
        String loggedOutUser = this.username;
        this.username = null;
        this.isLoggedIn = false;
        
        System.out.println("用户 " + loggedOutUser + " 已成功注销");
        return true;
    }
    
    /**
     * 检查用户是否已登录
     * @return 登录状态
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * 获取当前登录的用户名
     * @return 用户名，如果未登录则返回null
     */
    public String getUsername() {
        return username;
    }
}

/**
 * 登录表单类
 * 负责显示登录表单和处理用户输入
 */
class LoginForm {
    private Scanner scanner;
    
    /**
     * 构造函数
     */
    public LoginForm() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 显示登录表单
     * @param userSession 用户会话对象，用于执行登录操作
     */
    public void display(UserSession userSession) {
        System.out.println("\n=== 登录表单 ===");
        System.out.println("请输入用户名：");
        String username = scanner.nextLine();
        
        System.out.println("请输入密码：");
        String password = scanner.nextLine();
        
        // 尝试登录
        boolean loginSuccess = userSession.login(username, password);
        
        if (loginSuccess) {
            System.out.println("登录成功！欢迎使用系统。");
        } else {
            System.out.println("登录失败，请检查用户名和密码。");
        }
    }
    
    /**
     * 关闭扫描器资源
     */
    public void close() {
        scanner.close();
    }
}

/**
 * 主程序类
 * 演示注销功能的完整流程
 */
public class LogoutDemo {
    
    /**
     * 显示主菜单
     */
    private static void displayMenu() {
        System.out.println("\n=== 系统菜单 ===");
        System.out.println("1. 登录");
        System.out.println("2. 注销");
        System.out.println("3. 检查登录状态");
        System.out.println("4. 退出程序");
        System.out.print("请选择操作 (1-4): ");
    }
    
    /**
     * 主方法 - 程序入口
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserSession userSession = new UserSession();
        LoginForm loginForm = new LoginForm();
        
        System.out.println("=== 用户注销功能演示程序 ===");
        System.out.println("本程序演示完整的注销功能流程");
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        // 登录操作
                        if (userSession.isLoggedIn()) {
                            System.out.println("您已经登录，请先注销当前用户");
                        } else {
                            loginForm.display(userSession);
                        }
                        break;
                        
                    case 2:
                        // 注销操作
                        System.out.println("\n=== 注销操作 ===");
                        
                        // 检查前置条件：用户必须已登录
                        if (!userSession.isLoggedIn()) {
                            System.out.println("错误：用户未登录，无法执行注销操作");
                            System.out.println("请先登录系统");
                            break;
                        }
                        
                        System.out.println("当前登录用户: " + userSession.getUsername());
                        System.out.print("确认要注销吗？(y/n): ");
                        String confirm = scanner.nextLine();
                        
                        if (confirm.equalsIgnoreCase("y")) {
                            // 执行注销操作
                            boolean logoutSuccess = userSession.logout();
                            
                            if (logoutSuccess) {
                                // 注销成功后显示登录表单（根据用例的事件序列）
                                System.out.println("\n注销成功！现在显示登录表单...");
                                // 这里可以立即显示登录表单，但为了菜单清晰，我们让用户选择
                                System.out.println("您现在可以重新登录系统");
                            }
                        } else {
                            System.out.println("注销操作已取消");
                        }
                        break;
                        
                    case 3:
                        // 检查登录状态
                        System.out.println("\n=== 当前登录状态 ===");
                        if (userSession.isLoggedIn()) {
                            System.out.println("状态: 已登录");
                            System.out.println("用户名: " + userSession.getUsername());
                        } else {
                            System.out.println("状态: 未登录");
                        }
                        break;
                        
                    case 4:
                        // 退出程序
                        System.out.println("感谢使用，程序结束！");
                        running = false;
                        break;
                        
                    default:
                        System.out.println("无效选择，请输入1-4之间的数字");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("错误：请输入有效的数字");
            }
        }
        
        // 清理资源
        scanner.close();
        
        // 注意：loginForm也有自己的Scanner，但我们不在这里关闭它
        // 因为loginForm只在display方法中使用，而且display方法会创建新的Scanner
    }
}