import java.util.ArrayList;
import java.util.List;

/**
 * Main类是程序的入口点，包含main方法。
 * 负责初始化模拟数据、操作员和用户界面，启动评论修改流程。
 * 演示ModifyComment用例的完整实现。
 */
public class Main {
    
    /**
     * 程序主入口方法。
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("=== ModifyComment用例演示程序 ===");
        System.out.println("根据用例要求实现评论修改功能\n");
        
        // 步骤1：创建并登录机构操作员（满足进入条件：机构已登录）
        AgencyOperator operator = createAndLoginOperator();
        if (operator == null || !operator.isLoggedIn()) {
            System.err.println("错误：操作员登录失败，程序终止");
            return;
        }
        
        // 步骤2：创建模拟站点和反馈数据
        List<Site> sites = createMockSites();
        
        // 步骤3：初始化用户界面
        UserInterface ui = new UserInterface(operator, sites);
        
        // 步骤4：启动评论修改流程
        try {
            ui.startModificationProcess();
        } finally {
            // 步骤5：清理资源
            ui.close();
        }
        
        System.out.println("\n=== 程序执行完成 ===");
    }
    
    /**
     * 创建并登录机构操作员。
     * 模拟操作员登录过程，满足用例的进入条件。
     * @return 已登录的操作员对象，如果登录失败则返回null
     */
    private static AgencyOperator createAndLoginOperator() {
        System.out.println("正在初始化机构操作员...");
        
        // 创建操作员实例
        AgencyOperator operator = new AgencyOperator(1001, "张三");
        
        // 模拟登录过程
        System.out.println("操作员: " + operator.getOperatorName() + " (ID: " + operator.getOperatorId() + ")");
        
        // 尝试登录（模拟用户名和密码验证）
        boolean loginSuccess = operator.login("zhangsan", "password123");
        if (loginSuccess) {
            System.out.println("操作员登录成功");
            return operator;
        } else {
            System.err.println("错误：操作员登录失败");
            return null;
        }
    }
    
    /**
     * 创建模拟站点和反馈数据。
     * 模拟从SearchSite用例获取的站点列表。
     * @return 包含模拟数据的站点列表
     */
    private static List<Site> createMockSites() {
        System.out.println("正在创建模拟数据...");
        
        List<Site> sites = new ArrayList<>();
        
        // 创建站点1
        Site site1 = new Site(1, "技术论坛");
        site1.addFeedback(new Feedback(101, "这个功能非常好用，但希望能增加更多自定义选项。", 1));
        site1.addFeedback(new Feedback(102, "界面设计简洁明了，用户体验很好。", 1));
        site1.addFeedback(new Feedback(103, "响应速度有点慢，建议优化服务器性能。", 1));
        sites.add(site1);
        
        // 创建站点2
        Site site2 = new Site(2, "客户反馈平台");
        site2.addFeedback(new Feedback(201, "产品质量不错，但包装需要改进。", 2));
        site2.addFeedback(new Feedback(202, "客服响应及时，问题解决得很满意。", 2));
        site2.addFeedback(new Feedback(203, "物流速度太慢，希望能提供加急选项。", 2));
        site2.addFeedback(new Feedback(204, "价格合理，性价比高，会继续购买。", 2));
        sites.add(site2);
        
        // 创建站点3
        Site site3 = new Site(3, "内部评审系统");
        site3.addFeedback(new Feedback(301, "项目进度正常，团队协作良好。", 3));
        site3.addFeedback(new Feedback(302, "需要更多技术文档支持开发工作。", 3));
        sites.add(site3);
        
        System.out.println("已创建 " + sites.size() + " 个站点，包含 " + 
                          sites.stream().mapToInt(s -> s.getFeedbacks().size()).sum() + " 条反馈");
        
        return sites;
    }
    
    /**
     * 演示程序的不同测试场景。
     * 此方法可用于演示不同的用例执行路径。
     */
    private static void demonstrateTestScenarios() {
        System.out.println("\n=== 测试场景演示 ===");
        
        // 场景1：正常流程
        System.out.println("场景1：正常评论修改流程");
        System.out.println("预期：成功选择站点 -> 选择反馈 -> 编辑评论 -> 确认 -> 保存");
        
        // 场景2：取消操作
        System.out.println("场景2：用户取消操作");
        System.out.println("预期：在任何步骤输入'cancel'取消操作");
        
        // 场景3：无效数据
        System.out.println("场景3：输入无效评论数据");
        System.out.println("预期：系统激活Errored用例，显示错误信息");
        
        // 场景4：服务器连接中断
        System.out.println("场景4：服务器连接中断");
        System.out.println("预期：系统检测到连接中断，终止操作");
        
        System.out.println("\n注意：以上场景将在用户交互中演示");
    }
}