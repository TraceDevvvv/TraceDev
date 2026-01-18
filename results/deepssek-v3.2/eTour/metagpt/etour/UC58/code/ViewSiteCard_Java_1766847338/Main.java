// Main.java
// 主类，包含main方法作为程序入口，初始化并启动用户界面。
// 这个类负责启动整个旅游站点卡片查看系统，是程序的起点。

public class Main {
    /**
     * 主方法 - 程序入口点
     * 初始化用户界面并启动系统
     * 
     * @param args 命令行参数（本程序未使用）
     */
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("  旅游站点卡片查看系统 - ViewSiteCard用例实现");
        System.out.println("================================================");
        System.out.println("用例名称: ViewSiteCard");
        System.out.println("描述: 查看特定地点的详细信息");
        System.out.println("参与参与者: 游客");
        System.out.println("================================================");
        System.out.println("系统初始化中...");
        
        try {
            // 创建用户界面实例
            SiteCardUI ui = new SiteCardUI();
            
            // 启动用户界面
            System.out.println("用户界面初始化完成。");
            System.out.println("注意：这是一个模拟系统，使用内存数据库。");
            System.out.println("系统将模拟数据库连接和可能的连接中断。");
            System.out.println("================================================");
            
            // 运行用户界面
            ui.run();
            
        } catch (Exception e) {
            // 捕获并处理任何未预期的异常
            System.err.println("系统启动过程中发生严重错误: " + e.getMessage());
            System.err.println("错误类型: " + e.getClass().getName());
            System.err.println("建议：请检查系统配置并重新启动程序。");
            e.printStackTrace();
            
            // 退出程序，返回错误代码
            System.exit(1);
        }
        
        System.out.println("================================================");
        System.out.println("  程序正常结束");
        System.out.println("================================================");
    }
}