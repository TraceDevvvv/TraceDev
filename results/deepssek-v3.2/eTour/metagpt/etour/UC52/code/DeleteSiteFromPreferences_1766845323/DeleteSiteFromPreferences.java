import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 * 主程序类，实现DeleteSiteFromPreferences用例的事件流程
 * 包括选择站点、提示移除、确认移除和删除站点等功能
 * 处理游客取消操作和服务器连接中断异常
 */
public class DeleteSiteFromPreferences {
    private BookmarkManager bookmarkManager;
    private Scanner scanner;
    
    /**
     * 构造方法，初始化书签管理器和输入扫描器
     */
    public DeleteSiteFromPreferences() {
        this.bookmarkManager = new BookmarkManager();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 程序的主入口点
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        DeleteSiteFromPreferences app = new DeleteSiteFromPreferences();
        app.run();
    }
    
    /**
     * 运行程序的主方法
     */
    public void run() {
        System.out.println("=== 旅游站点书签管理系统 ===");
        System.out.println("用例：DeleteSiteFromPreferences");
        
        // 初始化一些示例书签数据
        initializeSampleData();
        
        boolean continueRunning = true;
        
        while (continueRunning) {
            try {
                System.out.println("\n=== 主菜单 ===");
                System.out.println("1. 查看当前书签");
                System.out.println("2. 从书签中删除站点");
                System.out.println("3. 模拟服务器连接中断");
                System.out.println("4. 模拟服务器连接恢复");
                System.out.println("5. 退出程序");
                System.out.print("请选择操作 (1-5): ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        displayBookmarks();
                        break;
                    case "2":
                        deleteSiteFromBookmarks();
                        break;
                    case "3":
                        simulateConnectionInterruption();
                        break;
                    case "4":
                        simulateConnectionRecovery();
                        break;
                    case "5":
                        continueRunning = false;
                        System.out.println("感谢使用旅游站点书签管理系统，再见！");
                        break;
                    default:
                        System.out.println("无效的选择，请输入1-5之间的数字");
                }
            } catch (ETourConnectionException e) {
                System.out.println("错误: " + e.getMessage());
                System.out.println("操作已取消，请检查服务器连接后重试");
            } catch (Exception e) {
                System.out.println("发生未知错误: " + e.getMessage());
                System.out.println("请重试");
            }
        }
        
        scanner.close();
    }
    
    /**
     * 初始化示例书签数据
     */
    private void initializeSampleData() {
        try {
            // 创建一些示例站点
            TouristSite site1 = new TouristSite("S001", "埃菲尔铁塔", "巴黎的标志性建筑", "法国巴黎");
            TouristSite site2 = new TouristSite("S002", "故宫博物院", "中国古代宫殿建筑", "中国北京");
            TouristSite site3 = new TouristSite("S003", "自由女神像", "美国的象征", "美国纽约");
            TouristSite site4 = new TouristSite("S004", "金字塔", "古埃及文明的象征", "埃及开罗");
            TouristSite site5 = new TouristSite("S005", "悉尼歌剧院", "澳大利亚地标建筑", "澳大利亚悉尼");
            
            // 添加到书签列表
            bookmarkManager.addSiteToBookmarks(site1);
            bookmarkManager.addSiteToBookmarks(site2);
            bookmarkManager.addSiteToBookmarks(site3);
            bookmarkManager.addSiteToBookmarks(site4);
            bookmarkManager.addSiteToBookmarks(site5);
            
            System.out.println("已初始化5个示例书签站点");
        } catch (ETourConnectionException e) {
            System.out.println("初始化示例数据时发生连接错误: " + e.getMessage());
        }
    }
    
    /**
     * 显示所有书签站点
     */
    private void displayBookmarks() {
        try {
            bookmarkManager.displayAllBookmarks();
        } catch (ETourConnectionException e) {
            System.out.println("错误: " + e.getMessage());
        }
    }
    
    /**
     * 实现用例的主要流程：从书签中删除站点
     * 遵循用例的事件流程：
     * 1. 选择要移除的站点
     * 2. 提示移除
     * 3. 确认移除
     * 4. 从书签列表中移除所选的站点
     */
    private void deleteSiteFromBookmarks() {
        System.out.println("\n=== 从书签中删除站点 ===");
        
        try {
            // 1. 显示当前书签列表供用户选择
            List<TouristSite> sites = bookmarkManager.getAllBookmarkedSites();
            
            if (sites.isEmpty()) {
                System.out.println("书签列表为空，没有可删除的站点");
                return;
            }
            
            System.out.println("当前书签列表：");
            for (int i = 0; i < sites.size(); i++) {
                TouristSite site = sites.get(i);
                System.out.println((i + 1) + ". ID: " + site.getSiteId() + 
                                 ", 名称: " + site.getName());
            }
            
            // 2. 用户选择要删除的站点（用例步骤1：选择通过特定功能从书签列表中移除您的站点）
            System.out.print("\n请输入要删除的站点编号 (1-" + sites.size() + ")，或输入'0'取消操作: ");
            String input = scanner.nextLine();
            
            // 检查用户是否取消操作（退出条件之一：游客取消操作）
            if ("0".equals(input)) {
                System.out.println("操作已取消");
                return;
            }
            
            int selectedIndex;
            try {
                selectedIndex = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("无效的输入，请输入数字");
                return;
            }
            
            if (selectedIndex < 1 || selectedIndex > sites.size()) {
                System.out.println("无效的选择，请输入1-" + sites.size() + "之间的数字");
                return;
            }
            
            TouristSite selectedSite = sites.get(selectedIndex - 1);
            
            // 3. 提示移除（用例步骤2：提示移除）
            System.out.println("\n您选择的站点是：");
            System.out.println("ID: " + selectedSite.getSiteId());
            System.out.println("名称: " + selectedSite.getName());
            System.out.println("描述: " + selectedSite.getDescription());
            System.out.println("位置: " + selectedSite.getLocation());
            
            // 4. 确认移除（用例步骤3：确认移除）
            System.out.print("\n确实要从书签中删除此站点吗？(输入'y'确认删除，其他任意键取消): ");
            String confirmation = scanner.nextLine();
            
            if (!"y".equalsIgnoreCase(confirmation)) {
                System.out.println("删除操作已取消");
                return;
            }
            
            // 5. 执行删除操作（用例步骤4：从书签列表中移除所选的站点）
            boolean isDeleted = bookmarkManager.deleteSiteFromBookmarks(selectedSite.getSiteId());
            
            if (isDeleted) {
                // 用例退出条件之一：通知系统已从站点书签中移除
                System.out.println("\n=== 操作完成 ===");
                System.out.println("成功从书签中删除站点: " + selectedSite.getName());
                System.out.println("通知系统：站点已从书签中移除");
                
                // 显示剩余书签数量
                int remainingCount = bookmarkManager.getBookmarkCount();
                System.out.println("当前剩余书签数量: " + remainingCount + " 个");
            } else {
                System.out.println("删除操作失败，请重试");
            }
            
        } catch (ETourConnectionException e) {
            // 用例退出条件之一：服务器ETOUR的连接中断
            System.out.println("错误: " + e.getMessage());
            System.out.println("操作已取消，由于服务器连接中断");
        }
    }
    
    /**
     * 模拟服务器连接中断（用于测试异常处理）
     */
    private void simulateConnectionInterruption() {
        bookmarkManager.setConnectionStatus(false);
        System.out.println("已模拟服务器连接中断状态");
        System.out.println("尝试进行删除操作将会触发ETourConnectionException");
    }
    
    /**
     * 模拟服务器连接恢复
     */
    private void simulateConnectionRecovery() {
        bookmarkManager.setConnectionStatus(true);
        System.out.println("已模拟服务器连接恢复");
    }
    
    /**
     * 一个实用的方法：通过站点ID直接删除站点
     * 提供更直接的方式来删除站点，不需用户从列表中选择
     * 
     * @param siteId 要删除的站点ID
     * @return 如果删除成功返回true，否则返回false
     */
    public boolean deleteSiteById(String siteId) {
        try {
            return bookmarkManager.deleteSiteFromBookmarks(siteId);
        } catch (ETourConnectionException e) {
            System.out.println("删除失败，原因: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * 安全地执行删除操作，自动处理连接异常
     * 
     * @param siteId 要删除的站点ID
     * @param confirm 是否需要确认删除
     * @return 操作结果消息
     */
    public String deleteSiteSafely(String siteId, boolean confirm) {
        try {
            TouristSite site = bookmarkManager.findSiteById(siteId);
            
            if (site == null) {
                return "未找到ID为 '" + siteId + "' 的站点";
            }
            
            if (confirm) {
                System.out.println("即将删除站点: " + site.getName());
                System.out.print("确认删除？(y/n): ");
                String response = scanner.nextLine();
                
                if (!"y".equalsIgnoreCase(response)) {
                    return "操作已取消";
                }
            }
            
            boolean success = bookmarkManager.deleteSiteFromBookmarks(siteId);
            
            return success ? "成功删除站点: " + site.getName() : "删除操作失败";
            
        } catch (ETourConnectionException e) {
            return "删除失败，服务器连接中断: " + e.getMessage();
        }
    }
}