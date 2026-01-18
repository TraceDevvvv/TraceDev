// SiteCardUI.java
// 用户界面类，提供控制台界面让用户选择站点并查看详细信息，模拟用户认证和区域设置。
// 这个类实现了用例中的用户交互部分：用户选择功能，系统显示结果。

import java.util.InputMismatchException;
import java.util.Scanner;

public class SiteCardUI {
    private SiteCardService siteCardService;  // 业务逻辑服务类
    private Scanner scanner;                  // 用于读取用户输入
    
    // 构造函数
    public SiteCardUI() {
        this.siteCardService = new SiteCardService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 显示主菜单
     */
    public void showMainMenu() {
        System.out.println("\n==================================");
        System.out.println("      旅游站点卡片查看系统");
        System.out.println("==================================");
        System.out.println("根据用例：ViewSiteCard");
        System.out.println("描述：查看特定地点的详细信息");
        System.out.println("==================================");
        System.out.println("当前状态: " + siteCardService.getCurrentStatus());
        System.out.println("==================================");
        System.out.println("请选择操作：");
        System.out.println("1. 模拟游客认证");
        System.out.println("2. 设置当前区域");
        System.out.println("3. 查看可用站点列表");
        System.out.println("4. 查看站点详细信息");
        System.out.println("5. 退出系统");
        System.out.print("请输入选项 (1-5): ");
    }
    
    /**
     * 处理用户选择
     */
    public void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                handleAuthentication();
                break;
            case 2:
                handleAreaSelection();
                break;
            case 3:
                handleShowAvailableSites();
                break;
            case 4:
                handleViewSiteCard();
                break;
            case 5:
                System.out.println("感谢使用旅游站点卡片查看系统，再见！");
                break;
            default:
                System.out.println("错误：无效的选项，请输入1-5之间的数字。");
        }
    }
    
    /**
     * 处理用户认证
     */
    private void handleAuthentication() {
        System.out.println("\n--- 游客认证 ---");
        System.out.println("根据用例，进入条件是'游客已成功认证到系统'");
        System.out.println("正在模拟认证过程...");
        
        // 模拟认证过程
        siteCardService.authenticateTourist();
        
        System.out.println("认证完成。");
        System.out.println("当前状态: " + siteCardService.getCurrentStatus());
    }
    
    /**
     * 处理区域选择
     */
    private void handleAreaSelection() {
        System.out.println("\n--- 设置当前区域 ---");
        System.out.println("根据用例，游客必须位于以下区域之一：");
        System.out.println("1. Research Results (研究结果)");
        System.out.println("2. Visited Sites (已访问地点列表)");
        System.out.println("3. Favorites List (收藏列表)");
        System.out.print("请选择区域 (1-3): ");
        
        try {
            int areaChoice = scanner.nextInt();
            scanner.nextLine(); // 清除换行符
            
            String area;
            switch (areaChoice) {
                case 1:
                    area = "Research Results";
                    break;
                case 2:
                    area = "Visited Sites";
                    break;
                case 3:
                    area = "Favorites List";
                    break;
                default:
                    System.out.println("错误：无效的选择，区域设置失败。");
                    return;
            }
            
            siteCardService.setCurrentArea(area);
            
        } catch (InputMismatchException e) {
            System.out.println("错误：请输入有效的数字 (1-3)。");
            scanner.nextLine(); // 清除无效输入
        }
    }
    
    /**
     * 显示可用站点列表
     */
    private void handleShowAvailableSites() {
        System.out.println("\n--- 可用站点列表 ---");
        System.out.println("正在从数据库获取站点列表...");
        
        int[] siteIds = siteCardService.getAvailableSiteIds();
        
        if (siteIds == null || siteIds.length == 0) {
            System.out.println("错误：无法获取站点列表或数据库为空。");
            return;
        }
        
        System.out.println("系统中可用的站点ID:");
        for (int i = 0; i < siteIds.length; i++) {
            System.out.print(siteIds[i]);
            if (i < siteIds.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("\n提示：您可以选择任意站点ID查看详细信息。");
    }
    
    /**
     * 处理查看站点卡片
     */
    private void handleViewSiteCard() {
        System.out.println("\n--- 查看站点详细信息 ---");
        System.out.println("根据用例：用户选择显示所选地点卡片的功能");
        
        // 首先检查用户是否已认证并设置了区域
        System.out.println("检查进入条件...");
        
        // 显示可用站点ID，方便用户选择
        int[] siteIds = siteCardService.getAvailableSiteIds();
        if (siteIds == null || siteIds.length == 0) {
            System.out.println("错误：无法获取站点列表，无法继续。");
            return;
        }
        
        System.out.print("可用站点ID: ");
        for (int i = 0; i < siteIds.length; i++) {
            System.out.print(siteIds[i]);
            if (i < siteIds.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        
        // 获取用户输入的站点ID
        System.out.print("请输入要查看的站点ID: ");
        
        try {
            int siteId = scanner.nextInt();
            scanner.nextLine(); // 清除换行符
            
            // 验证站点ID是否有效
            if (!siteCardService.validateSiteId(siteId)) {
                System.out.println("错误：站点ID " + siteId + " 无效。");
                System.out.println("请从可用站点ID中选择。");
                return;
            }
            
            // 调用服务类查看站点卡片
            System.out.println("正在查看站点ID为 " + siteId + " 的详细信息...");
            siteCardService.viewSiteCardWithValidation(siteId);
            
        } catch (InputMismatchException e) {
            System.out.println("错误：请输入有效的数字作为站点ID。");
            scanner.nextLine(); // 清除无效输入
        }
    }
    
    /**
     * 运行用户界面主循环
     */
    public void run() {
        System.out.println("欢迎使用旅游站点卡片查看系统！");
        System.out.println("本系统模拟用例：ViewSiteCard - 查看特定地点的详细信息");
        System.out.println("请注意：这是一个模拟系统，使用内存数据库。");
        
        boolean running = true;
        
        while (running) {
            showMainMenu();
            
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // 清除换行符
                
                if (choice == 5) {
                    running = false;
                }
                
                handleUserChoice(choice);
                
            } catch (InputMismatchException e) {
                System.out.println("错误：请输入有效的数字 (1-5)。");
                scanner.nextLine(); // 清除无效输入
            } catch (Exception e) {
                System.out.println("发生意外错误: " + e.getMessage());
                System.out.println("建议：重新启动程序。");
                running = false;
            }
        }
        
        // 关闭扫描器
        scanner.close();
        System.out.println("系统资源已释放。");
    }
    
    /**
     * 主方法，用于独立测试UI
     */
    public static void main(String[] args) {
        SiteCardUI ui = new SiteCardUI();
        ui.run();
    }
}