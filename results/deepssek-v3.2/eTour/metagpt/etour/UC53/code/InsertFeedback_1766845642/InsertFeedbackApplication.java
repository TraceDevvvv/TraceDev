import java.util.Scanner;

/**
 * InsertFeedbackApplication - 主应用程序类
 * 演示InsertFeedback用例的完整流程，包含main方法、用例演示和各种场景测试
 * 这个类是程序的入口点，协调所有组件来演示完整的用例流程
 */
public class InsertFeedbackApplication {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("      InsertFeedback 应用程序启动       ");
        System.out.println("========================================");
        System.out.println("描述：为选定的站点插入反馈");
        System.out.println("参与者：游客");
        System.out.println("入口条件：游客卡片在特定站点\n");
        
        try {
            // 1. 初始化数据存储和服务
            DataStorage dataStorage = new DataStorage();
            FeedbackService feedbackService = new FeedbackService(dataStorage);
            
            // 2. 显示初始数据状态
            displayInitialData(dataStorage);
            
            // 3. 创建用户输入扫描器
            Scanner scanner = new Scanner(System.in);
            
            // 4. 主交互循环
            boolean exit = false;
            while (!exit) {
                displayMainMenu();
                
                String choice = scanner.nextLine().trim();
                
                switch (choice) {
                    case "1":
                        // 执行完整的反馈流程
                        executeFeedbackProcess(feedbackService, scanner);
                        break;
                        
                    case "2":
                        // 运行测试场景
                        feedbackService.runTestScenarios();
                        break;
                        
                    case "3":
                        // 显示当前数据状态
                        displayCurrentData(dataStorage);
                        break;
                        
                    case "4":
                        // 检查游客是否可以提供反馈
                        checkFeedbackEligibility(feedbackService, scanner);
                        break;
                        
                    case "5":
                        // 演示不同退出条件
                        demonstrateExitConditions(feedbackService, scanner);
                        break;
                        
                    case "6":
                        // 重置数据
                        dataStorage.clearAllStorage();
                        System.out.println("数据已重置为初始状态。");
                        displayCurrentData(dataStorage);
                        break;
                        
                    case "0":
                        exit = true;
                        System.out.println("感谢使用InsertFeedback应用程序！");
                        break;
                        
                    default:
                        System.out.println("无效的选择，请重新输入。");
                }
            }
            
            scanner.close();
            
        } catch (Exception e) {
            System.err.println("应用程序发生错误: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 显示主菜单
     */
    private static void displayMainMenu() {
        System.out.println("\n=== 主菜单 ===");
        System.out.println("1. 执行反馈流程");
        System.out.println("2. 运行测试场景");
        System.out.println("3. 显示当前数据状态");
        System.out.println("4. 检查反馈资格");
        System.out.println("5. 演示退出条件");
        System.out.println("6. 重置数据");
        System.out.println("0. 退出程序");
        System.out.print("请选择操作: ");
    }
    
    /**
     * 显示初始数据状态
     * @param dataStorage 数据存储实例
     */
    private static void displayInitialData(DataStorage dataStorage) {
        System.out.println("=== 初始数据状态 ===");
        System.out.println(dataStorage.getStorageStats());
        
        System.out.println("可用的游客:");
        for (Tourist tourist : dataStorage.getAllTourists()) {
            System.out.println("  " + tourist.getTouristId() + ": " + tourist.getName());
        }
        
        System.out.println("\n可用的站点:");
        for (Site site : dataStorage.getAllSites()) {
            System.out.println("  " + site.getSiteId() + ": " + site.getName() + 
                             " - " + site.getDescription());
        }
        System.out.println();
    }
    
    /**
     * 显示当前数据状态
     * @param dataStorage 数据存储实例
     */
    private static void displayCurrentData(DataStorage dataStorage) {
        System.out.println("\n=== 当前数据状态 ===");
        System.out.println(dataStorage.getStorageStats());
        
        // 显示所有反馈
        System.out.println("所有反馈:");
        for (Feedback feedback : dataStorage.getAllFeedback()) {
            System.out.println("  " + feedback.getFeedbackId() + 
                             ": 游客 " + feedback.getTouristId() + 
                             " 给站点 " + feedback.getSiteId() + 
                             " 评分 " + feedback.getRating() + 
                             " 星，评论: " + feedback.getComment());
        }
    }
    
    /**
     * 执行反馈流程（对应用例中的完整流程）
     * @param feedbackService 反馈服务实例
     * @param scanner 用户输入扫描器
     */
    private static void executeFeedbackProcess(FeedbackService feedbackService, Scanner scanner) {
        System.out.println("\n=== 执行反馈流程 ===");
        System.out.println("说明：此流程演示完整的InsertFeedback用例");
        
        // 获取游客和站点信息
        String touristId = getInput("请输入游客ID (例如: T001, T002): ", scanner);
        String siteId = getInput("请输入站点ID (例如: S001, S002): ", scanner);
        
        System.out.println("\n开始InsertFeedback用例流程...");
        
        try {
            // 对应于用例中的步骤1：激活反馈功能
            System.out.println("步骤1: 激活反馈功能...");
            
            // 执行反馈流程
            FeedbackService.InsertFeedbackResult result = 
                feedbackService.executeInsertFeedback(touristId, siteId);
            
            // 显示结果
            System.out.println("\n=== 反馈流程结果 ===");
            System.out.println("成功: " + result.isSuccess());
            System.out.println("消息: " + result.getMessage());
            
            if (result.isSuccess()) {
                System.out.println("反馈ID: " + result.getFeedbackId());
                System.out.println("退出条件: 系统已通知反馈成功提交到站点");
            }
            
        } catch (FeedbackAlreadyReleasedException e) {
            System.out.println("\n=== 反馈流程结果 ===");
            System.out.println("退出条件: 激活FeedbackAlreadyReleased用例");
            System.out.println("错误: " + e.getMessage());
            
        } catch (InvalidFeedbackException e) {
            System.out.println("\n=== 反馈流程结果 ===");
            System.out.println("退出条件: 激活Errored用例");
            System.out.println("错误: " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("\n=== 反馈流程结果 ===");
            System.out.println("系统错误: " + e.getMessage());
        }
    }
    
    /**
     * 检查游客是否可以提供反馈
     * @param feedbackService 反馈服务实例
     * @param scanner 用户输入扫描器
     */
    private static void checkFeedbackEligibility(FeedbackService feedbackService, Scanner scanner) {
        System.out.println("\n=== 检查反馈资格 ===");
        
        String touristId = getInput("请输入游客ID: ", scanner);
        String siteId = getInput("请输入站点ID: ", scanner);
        
        boolean canProvide = feedbackService.canTouristProvideFeedback(touristId, siteId);
        
        System.out.println("\n反馈资格检查结果:");
        System.out.println("游客: " + touristId);
        System.out.println("站点: " + siteId);
        System.out.println("可以提供反馈: " + (canProvide ? "是" : "否"));
        
        if (!canProvide) {
            System.out.println("可能的原因:");
            if (!feedbackService.getDataStorage().touristExists(touristId)) {
                System.out.println("  - 游客不存在");
            }
            if (!feedbackService.getDataStorage().siteExists(siteId)) {
                System.out.println("  - 站点不存在");
            }
            if (feedbackService.getDataStorage().hasTouristGivenFeedback(touristId, siteId)) {
                System.out.println("  - 游客已为该站点发布过反馈");
            }
        }
    }
    
    /**
     * 演示不同退出条件
     * @param feedbackService 反馈服务实例
     * @param scanner 用户输入扫描器
     */
    private static void demonstrateExitConditions(FeedbackService feedbackService, Scanner scanner) {
        System.out.println("\n=== 演示退出条件 ===");
        System.out.println("1. 正常退出 - 成功提交反馈");
        System.out.println("2. 游客取消操作");
        System.out.println("3. 游客已发布反馈 (FeedbackAlreadyReleased)");
        System.out.println("4. 数据无效 (Errored)");
        System.out.println("5. 连接中断");
        System.out.print("请选择要演示的退出条件: ");
        
        String choice = scanner.nextLine().trim();
        
        switch (choice) {
            case "1":
                System.out.println("\n演示: 正常退出 - 成功提交反馈");
                // 使用新游客和新站点演示成功流程
                FeedbackService.InsertFeedbackResult result = 
                    feedbackService.processFeedback("T003", "S003");
                System.out.println("结果: " + result.getMessage());
                System.out.println("退出条件: 系统通知反馈成功提交到站点");
                break;
                
            case "2":
                System.out.println("\n演示: 游客取消操作");
                System.out.println("说明: 在表单输入过程中，输入 'cancel' 可以取消操作");
                System.out.println("请输入 'cancel' 来测试取消操作:");
                
                // 创建一个新的游客和站点来演示取消
                System.out.println("开始反馈流程...");
                FeedbackService.InsertFeedbackResult cancelResult = 
                    feedbackService.processFeedback("T003", "S001");
                System.out.println("结果: " + cancelResult.getMessage());
                System.out.println("退出条件: 游客取消操作");
                break;
                
            case "3":
                System.out.println("\n演示: 游客已发布反馈");
                System.out.println("说明: 首先创建一个反馈，然后尝试再次为相同站点创建反馈");
                
                // 先创建一个反馈
                feedbackService.processFeedback("T001", "S001");
                
                // 然后尝试再次创建反馈，会触发FeedbackAlreadyReleasedException
                System.out.println("\n尝试为相同站点再次创建反馈...");
                FeedbackService.InsertFeedbackResult duplicateResult = 
                    feedbackService.processFeedback("T001", "S001");
                System.out.println("结果: " + duplicateResult.getMessage());
                System.out.println("退出条件: 激活FeedbackAlreadyReleased用例");
                break;
                
            case "4":
                System.out.println("\n演示: 数据无效");
                System.out.println("说明: 演示输入无效数据的情况");
                
                // 注意：这个方法需要修改FeedbackForm来模拟无效输入
                // 在实际演示中，用户可以手动输入无效数据进行测试
                System.out.println("请运行主菜单选项1，然后输入无效数据（如评分6或空评论）进行测试");
                System.out.println("退出条件: 激活Errored用例");
                break;
                
            case "5":
                System.out.println("\n演示: 连接中断");
                System.out.println("说明: 检查服务器连接");
                
                DataStorage dataStorage = feedbackService.getDataStorage();
                FeedbackForm form = new FeedbackForm("T001", "S001");
                
                if (form.checkServerConnection()) {
                    System.out.println("服务器连接正常");
                } else {
                    System.out.println("服务器连接中断");
                    System.out.println("退出条件: 连接到ETOUR服务器的连接中断");
                }
                
                form.close();
                break;
                
            default:
                System.out.println("无效的选择");
        }
    }
    
    /**
     * 获取用户输入
     * @param prompt 提示信息
     * @param scanner 用户输入扫描器
     * @return 用户输入的字符串
     */
    private static String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     * 显示用例流程图，帮助理解整个用例的执行过程
     */
    private static void displayUseCaseFlow() {
        System.out.println("\n=== InsertFeedback 用例流程图 ===");
        System.out.println("入口条件: 游客卡片在特定站点");
        System.out.println("步骤1: 激活反馈功能");
        System.out.println("步骤2: 验证游客尚未发布反馈");
        System.out.println("       ├─ 如果已发布 → 激活FeedbackAlreadyReleased用例");
        System.out.println("       └─ 如果未发布 → 显示反馈表单");
        System.out.println("步骤3: 填写表单（评分 + 评论）");
        System.out.println("       ├─ 用户取消 → 操作取消");
        System.out.println("       └─ 用户提交 → 进入步骤4");
        System.out.println("步骤4: 验证输入数据");
        System.out.println("       ├─ 数据无效 → 激活Errored用例");
        System.out.println("       └─ 数据有效 → 进入步骤5");
        System.out.println("步骤5: 确认发布反馈");
        System.out.println("步骤6: 记录反馈并更新已访问站点列表");
        System.out.println("退出条件: 成功提交/用户取消/连接中断");
    }
}