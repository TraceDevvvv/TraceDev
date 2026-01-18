// SiteCardService.java
// 站点卡片服务类，负责业务逻辑：验证用户状态、调用数据库连接、处理异常并显示站点卡片信息。
// 这个类模拟游客已成功认证并处于特定区域时，查看站点详细信息的功能流程。

public class SiteCardService {
    private DatabaseConnector dbConnector;
    private boolean isTouristAuthenticated;  // 模拟游客是否已成功认证
    private String currentArea;              // 模拟当前所在区域（研究结果、已访问地点列表、收藏列表）

    // 构造函数
    public SiteCardService() {
        // 初始化数据库连接器（使用默认连接参数）
        this.dbConnector = new DatabaseConnector();
        this.isTouristAuthenticated = false;  // 默认未认证，需在流程中认证
        this.currentArea = null;              // 默认不在任何区域
    }

    /**
     * 模拟游客认证过程
     * 根据用例，进入条件是"游客已成功认证到系统"
     * 这里简化为设置认证状态为true
     */
    public void authenticateTourist() {
        this.isTouristAuthenticated = true;
        System.out.println("游客认证成功！");
    }

    /**
     * 模拟设置当前区域
     * 根据用例，进入条件是游客位于以下区域之一：研究结果、已访问地点列表、收藏列表
     * @param area 区域名称：必须是"Research Results"、"Visited Sites"或"Favorites List"
     */
    public void setCurrentArea(String area) {
        // 验证区域是否有效
        if (area.equals("Research Results") || area.equals("Visited Sites") || area.equals("Favorites List")) {
            this.currentArea = area;
            System.out.println("当前区域已设置为: " + area);
        } else {
            System.out.println("错误：无效的区域。必须是以下之一：Research Results, Visited Sites, Favorites List");
            this.currentArea = null;
        }
    }

    /**
     * 验证进入条件是否满足
     * @return 如果满足进入条件返回true，否则返回false
     */
    private boolean validateEntryConditions() {
        if (!isTouristAuthenticated) {
            System.out.println("错误：游客未认证。请先进行认证。");
            return false;
        }
        
        if (currentArea == null) {
            System.out.println("错误：游客不在有效区域。请设置当前区域为有效值。");
            return false;
        }
        
        System.out.println("验证通过：游客已认证，当前在 " + currentArea + " 区域。");
        return true;
    }

    /**
     * 显示站点卡片的主要业务方法
     * 模拟用例的事件流程：选择站点 -> 从数据库加载数据 -> 显示详细信息
     * @param siteId 要查看的站点ID
     */
    public void viewSiteCard(int siteId) {
        System.out.println("\n=== 开始查看站点卡片流程 ===");
        
        // 1. 验证进入条件
        if (!validateEntryConditions()) {
            System.out.println("无法查看站点卡片：不满足进入条件。");
            return;
        }
        
        Site site = null;
        boolean success = false;
        
        try {
            // 2. 连接到数据库（模拟）
            System.out.println("正在连接到ETOUR服务器...");
            dbConnector.connect();
            
            // 3. 从数据库获取站点数据（模拟事件流程：系统从数据库上传数据）
            System.out.println("正在从数据库加载站点信息...");
            site = dbConnector.getSiteById(siteId);
            
            if (site != null) {
                // 4. 成功获取数据，显示站点详情（模拟退出条件：系统显示所选地点的详细信息）
                System.out.println("\n=== 站点详细信息 ===");
                System.out.println(site.toString());
                System.out.println("=====================\n");
                success = true;
            } else {
                System.out.println("错误：未找到ID为 " + siteId + " 的站点。");
            }
            
        } catch (DatabaseConnectionException e) {
            // 处理连接中断异常（模拟用例中的异常情况：服务器ETOUR的连接中断）
            System.out.println("服务器连接异常: " + e.getMessage());
            System.out.println("建议：请检查网络连接，稍后重试。");
            System.out.println("错误代码: ETOUR_CONNECTION_FAILED");
            
        } finally {
            // 确保关闭数据库连接
            try {
                dbConnector.disconnect();
            } catch (Exception e) {
                System.out.println("断开连接时发生警告: " + e.getMessage());
            }
        }
        
        // 显示操作结果
        if (success) {
            System.out.println("站点卡片查看成功。");
        } else {
            System.out.println("站点卡片查看失败。");
        }
        System.out.println("=== 查看站点卡片流程结束 ===\n");
    }

    /**
     * 获取所有可用的站点ID列表，用于显示给用户选择
     * @return 站点ID数组，如果出错返回null
     */
    public int[] getAvailableSiteIds() {
        try {
            if (!dbConnector.isConnected()) {
                dbConnector.connect();
            }
            return dbConnector.getAllSiteIds();
        } catch (DatabaseConnectionException e) {
            System.out.println("获取站点列表失败: " + e.getMessage());
            return null;
        }
    }

    /**
     * 验证站点ID是否有效
     * @param siteId 要验证的站点ID
     * @return 如果有效返回true，否则返回false
     */
    public boolean validateSiteId(int siteId) {
        int[] availableIds = getAvailableSiteIds();
        if (availableIds == null) {
            return false;
        }
        
        for (int id : availableIds) {
            if (id == siteId) {
                return true;
            }
        }
        return false;
    }

    /**
     * 使用更友好的方式查看站点卡片，自动处理各种验证
     * 这是主要的对外接口方法
     */
    public void viewSiteCardWithValidation(int siteId) {
        if (!validateSiteId(siteId)) {
            System.out.println("错误：站点ID " + siteId + " 无效或不存在。");
            System.out.println("可用站点ID: ");
            int[] ids = getAvailableSiteIds();
            if (ids != null) {
                for (int id : ids) {
                    System.out.print(id + " ");
                }
                System.out.println();
            }
            return;
        }
        
        viewSiteCard(siteId);
    }

    // 辅助方法：获取当前状态信息
    public String getCurrentStatus() {
        return "认证状态: " + (isTouristAuthenticated ? "已认证" : "未认证") + 
               ", 当前区域: " + (currentArea != null ? currentArea : "未设置");
    }
}