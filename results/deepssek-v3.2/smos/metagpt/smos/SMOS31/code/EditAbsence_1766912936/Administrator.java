/**
 * Administrator类 - 表示系统管理员
 * 包含管理员的基本信息和状态
 */
public class Administrator {
    private String id;
    private String name;
    private boolean isLoggedIn;
    
    /**
     * 构造函数
     * @param id 管理员ID
     * @param name 管理员姓名
     */
    public Administrator(String id, String name) {
        this.id = id;
        this.name = name;
        this.isLoggedIn = false;
    }
    
    /**
     * 登录系统
     * @return 登录成功返回true，失败返回false
     */
    public boolean login() {
        // 在实际系统中，这里会有身份验证逻辑
        // 为简化演示，假设总是成功登录
        this.isLoggedIn = true;
        System.out.println("管理员 " + name + " (ID: " + id + ") 已登录。");
        return true;
    }
    
    /**
     * 登出系统
     */
    public void logout() {
        this.isLoggedIn = false;
        System.out.println("管理员 " + name + " 已登出。");
    }
    
    /**
     * 检查管理员是否已登录
     * @return 登录状态
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     * 获取管理员ID
     * @return 管理员ID
     */
    public String getId() {
        return id;
    }
    
    /**
     * 获取管理员姓名
     * @return 管理员姓名
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置管理员姓名
     * @param name 新姓名
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 模拟管理员执行操作
     * @param operation 操作描述
     * @return 操作是否成功
     */
    public boolean performOperation(String operation) {
        if (!isLoggedIn) {
            System.out.println("错误：管理员未登录，无法执行操作。");
            return false;
        }
        
        System.out.println("管理员 " + name + " 正在执行操作：" + operation);
        return true;
    }
    
    /**
     * 模拟管理员中断操作
     * @param operation 被中断的操作
     */
    public void interruptOperation(String operation) {
        System.out.println("管理员 " + name + " 中断了操作：" + operation);
    }
    
    @Override
    public String toString() {
        return "Administrator{id='" + id + "', name='" + name + "', isLoggedIn=" + isLoggedIn + "}";
    }
}