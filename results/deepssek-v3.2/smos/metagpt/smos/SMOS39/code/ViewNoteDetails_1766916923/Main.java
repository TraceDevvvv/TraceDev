import java.util.Scanner;
import java.util.List;

/**
 * Main类 - 程序入口点
 * 模拟完整的ViewNoteDetails用例流程：
 * 1. 用户登录（必须是管理员）
 * 2. 显示可用笔记列表
 * 3. 用户选择笔记查看详情
 * 4. 系统显示笔记详情
 * 5. 处理可能的异常和边界情况
 */
public class Main {
    
    /**
     * 主方法 - 程序入口
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("       ViewNoteDetails 系统 - 启动");
        System.out.println("================================================");
        
        // 创建Scanner对象用于用户输入
        Scanner scanner = new Scanner(System.in);
        
        try {
            // 步骤1: 用户登录
            User currentUser = loginUser(scanner);
            if (currentUser == null) {
                System.out.println("登录失败，程序退出。");
                return;
            }
            
            // 步骤2: 验证用户是否为管理员（根据用例要求）
            if (!currentUser.isAdmin()) {
                System.out.println("错误：只有管理员可以查看笔记详情！");
                System.out.println("当前用户角色: " + currentUser.getRole());
                System.out.println("程序退出。");
                return;
            }
            
            // 步骤3: 显示欢迎信息和可用笔记
            displayWelcomeMessage(currentUser);
            displayAvailableNotes();
            
            // 步骤4: 用户选择笔记查看详情
            boolean continueViewing = true;
            while (continueViewing) {
                String noteId = selectNoteToView(scanner);
                
                if (noteId == null || noteId.equalsIgnoreCase("exit")) {
                    System.out.println("退出笔记查看。");
                    break;
                }
                
                // 步骤5: 执行ViewNoteDetails用例
                boolean success = executeViewNoteDetails(currentUser, noteId);
                
                // 步骤6: 询问用户是否继续查看其他笔记
                continueViewing = askToContinue(scanner);
            }
            
            // 步骤7: 演示其他功能（可选）
            demonstrateAdditionalFeatures(currentUser);
            
        } catch (Exception e) {
            System.err.println("程序执行过程中发生错误: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 清理资源
            scanner.close();
            System.out.println("\n================================================");
            System.out.println("       ViewNoteDetails 系统 - 关闭");
            System.out.println("================================================");
        }
    }
    
    /**
     * 用户登录流程
     * @param scanner 输入扫描器
     * @return 登录成功的用户对象，失败返回null
     */
    private static User loginUser(Scanner scanner) {
        System.out.println("\n--- 用户登录 ---");
        
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("请输入用户名: ");
            String username = scanner.nextLine().trim();
            
            System.out.print("请输入密码: ");
            String password = scanner.nextLine().trim();
            
            // 尝试登录
            User user = User.login(username, password);
            
            if (user != null && user.isLoggedIn()) {
                System.out.println("登录成功！");
                return user;
            } else {
                attempts++;
                int remainingAttempts = MAX_ATTEMPTS - attempts;
                if (remainingAttempts > 0) {
                    System.out.println("登录失败，请重试。剩余尝试次数: " + remainingAttempts);
                }
            }
        }
        
        System.out.println("登录尝试次数超过限制，登录失败。");
        return null;
    }
    
    /**
     * 显示欢迎信息
     * @param user 当前用户
     */
    private static void displayWelcomeMessage(User user) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("欢迎使用 ViewNoteDetails 系统");
        System.out.println("=".repeat(50));
        System.out.println(user.getUserInfo());
        System.out.println("=".repeat(50));
        
        // 显示用户权限
        System.out.println("用户权限:");
        for (String permission : user.getPermissions()) {
            System.out.println("  - " + permission);
        }
        System.out.println("=".repeat(50));
    }
    
    /**
     * 显示可用的笔记列表
     */
    private static void displayAvailableNotes() {
        System.out.println("\n--- 可用笔记列表 ---");
        
        List<String> noteIds = ViewNoteDetails.getAllNoteIds();
        List<Note> allNotes = ViewNoteDetails.getAllNotes();
        
        if (noteIds.isEmpty()) {
            System.out.println("当前没有可用的笔记。");
            return;
        }
        
        System.out.println("笔记ID\t\t学生\t\t老师\t\t创建时间");
        System.out.println("-".repeat(60));
        
        for (int i = 0; i < allNotes.size(); i++) {
            Note note = allNotes.get(i);
            System.out.printf("%-10s\t%-10s\t%-10s\t%-20s\n",
                    note.getNoteId(),
                    note.getStudent(),
                    note.getTeacher(),
                    note.getFormattedDate());
        }
        
        System.out.println("-".repeat(60));
        System.out.println("共 " + noteIds.size() + " 条笔记");
    }
    
    /**
     * 用户选择要查看的笔记
     * @param scanner 输入扫描器
     * @return 用户选择的笔记ID，或null/exit表示退出
     */
    private static String selectNoteToView(Scanner scanner) {
        System.out.println("\n--- 选择笔记查看详情 ---");
        System.out.println("请输入要查看的笔记ID（输入 'list' 重新显示列表，输入 'exit' 退出）:");
        System.out.print("笔记ID: ");
        
        String input = scanner.nextLine().trim();
        
        if (input.equalsIgnoreCase("list")) {
            displayAvailableNotes();
            return selectNoteToView(scanner); // 递归调用以重新选择
        }
        
        if (input.equalsIgnoreCase("exit")) {
            return "exit";
        }
        
        // 验证笔记ID是否存在
        Note note = ViewNoteDetails.getNoteById(input);
        if (note == null) {
            System.out.println("错误：笔记ID '" + input + "' 不存在！");
            System.out.println("请从以下列表中选择有效的笔记ID:");
            displayAvailableNotes();
            return selectNoteToView(scanner); // 递归调用以重新选择
        }
        
        return input;
    }
    
    /**
     * 执行ViewNoteDetails用例
     * @param user 当前用户（必须是管理员）
     * @param noteId 要查看的笔记ID
     * @return 执行是否成功
     */
    private static boolean executeViewNoteDetails(User user, String noteId) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("执行 ViewNoteDetails 用例...");
        System.out.println("=".repeat(60));
        
        // 记录开始时间（用于性能监控）
        long startTime = System.currentTimeMillis();
        
        // 执行ViewNoteDetails用例
        boolean success = ViewNoteDetails.viewNoteDetails(user, noteId);
        
        // 记录结束时间
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        if (success) {
            System.out.println("用例执行成功！耗时: " + duration + "ms");
            
            // 模拟SMOS服务器连接中断（根据用例后置条件）
            ViewNoteDetails.handleServerConnectionIssue();
            
            // 显示成功消息
            System.out.println("\n✓ 笔记详情已成功显示！");
            System.out.println("  学生、描述、老师、日期等信息已按照用例要求显示。");
        } else {
            System.out.println("用例执行失败！耗时: " + duration + "ms");
            System.out.println("\n✗ 无法显示笔记详情，请检查：");
            System.out.println("  1. 用户权限（必须是管理员）");
            System.out.println("  2. 笔记ID是否正确");
            System.out.println("  3. 系统状态是否正常");
        }
        
        System.out.println("=".repeat(60));
        return success;
    }
    
    /**
     * 询问用户是否继续查看其他笔记
     * @param scanner 输入扫描器
     * @return true表示继续，false表示停止
     */
    private static boolean askToContinue(Scanner scanner) {
        System.out.print("\n是否继续查看其他笔记？(yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("yes") || response.equals("y")) {
            return true;
        } else if (response.equals("no") || response.equals("n")) {
            System.out.println("感谢使用 ViewNoteDetails 系统！");
            return false;
        } else {
            System.out.println("无效输入，请输入 'yes' 或 'no'。");
            return askToContinue(scanner); // 递归调用直到获得有效输入
        }
    }
    
    /**
     * 演示其他功能（可选）
     * @param user 当前用户
     */
    private static void demonstrateAdditionalFeatures(User user) {
        System.out.println("\n--- 演示其他功能 ---");
        
        // 1. 显示笔记统计信息
        System.out.println("1. 笔记统计信息:");
        System.out.println("   " + ViewNoteDetails.getNoteStatistics());
        
        // 2. 搜索功能演示
        System.out.println("\n2. 搜索功能演示:");
        String[] searchKeywords = {"数学", "物理", "英语"};
        for (String keyword : searchKeywords) {
            List<Note> searchResults = ViewNoteDetails.searchNotes(keyword);
            System.out.println("   搜索关键词 '" + keyword + "' 找到 " + searchResults.size() + " 条笔记");
        }
        
        // 3. 测试边界情况
        System.out.println("\n3. 边界情况测试:");
        
        // 测试无效笔记ID
        System.out.println("   a) 测试无效笔记ID:");
        boolean invalidResult = ViewNoteDetails.viewNoteDetails(user, "INVALID_ID");
        System.out.println("      结果: " + (invalidResult ? "成功" : "失败（预期行为）"));
        
        // 测试空笔记ID
        System.out.println("   b) 测试空笔记ID:");
        boolean emptyResult = ViewNoteDetails.viewNoteDetails(user, "");
        System.out.println("      结果: " + (emptyResult ? "成功" : "失败（预期行为）"));
        
        // 4. 生成测试报告
        System.out.println("\n4. 系统测试报告:");
        ViewNoteDetails.generateTestReport();
        
        // 5. 演示用户权限检查
        System.out.println("5. 用户权限验证:");
        System.out.println("   当前用户是否可以查看笔记详情: " + user.canViewNoteDetails());
        System.out.println("   当前用户是否是管理员: " + user.isAdmin());
        System.out.println("   当前用户权限数量: " + user.getPermissions().size());
        
        System.out.println("\n--- 功能演示完成 ---");
    }
    
    /**
     * 演示用例的各种场景
     * 这个方法可以被调用来演示不同的测试场景
     */
    public static void demonstrateUseCaseScenarios() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("        ViewNoteDetails 用例场景演示");
        System.out.println("=".repeat(70));
        
        // 场景1: 正常流程 - 管理员查看有效笔记
        System.out.println("\n场景1: 正常流程 - 管理员查看有效笔记");
        System.out.println("-".repeat(50));
        User adminUser = User.login("admin123", "adminPass123");
        if (adminUser != null && adminUser.isLoggedIn()) {
            ViewNoteDetails.viewNoteDetails(adminUser, "N001");
        }
        
        // 场景2: 权限不足 - 非管理员用户尝试查看
        System.out.println("\n场景2: 权限不足 - 学生用户尝试查看");
        System.out.println("-".repeat(50));
        User studentUser = User.login("student1", "studentPass1");
        if (studentUser != null && studentUser.isLoggedIn()) {
            ViewNoteDetails.viewNoteDetails(studentUser, "N002");
        }
        
        // 场景3: 未登录用户尝试查看
        System.out.println("\n场景3: 未登录用户尝试查看");
        System.out.println("-".repeat(50));
        User notLoggedInUser = new User();
        ViewNoteDetails.viewNoteDetails(notLoggedInUser, "N003");
        
        // 场景4: 查看不存在的笔记
        System.out.println("\n场景4: 查看不存在的笔记");
        System.out.println("-".repeat(50));
        if (adminUser != null) {
            ViewNoteDetails.viewNoteDetails(adminUser, "NON_EXISTENT");
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("       用例场景演示完成");
        System.out.println("=".repeat(70));
    }
    
    /**
     * 运行演示模式
     * 这个方法可以独立运行来演示所有功能
     */
    public static void runDemoMode() {
        System.out.println("\n" + "*".repeat(70));
        System.out.println("           ViewNoteDetails 系统演示模式");
        System.out.println("*".repeat(70));
        
        // 演示所有用例场景
        demonstrateUseCaseScenarios();
        
        // 显示系统信息
        System.out.println("\n系统信息:");
        System.out.println("  用户统计: " + User.getUserStatistics());
        System.out.println("  笔记统计: " + ViewNoteDetails.getNoteStatistics());
        
        System.out.println("\n" + "*".repeat(70));
        System.out.println("          演示模式完成");
        System.out.println("*".repeat(70));
    }
    
    /**
     * 运行测试模式
     * 这个方法可以独立运行来测试所有功能
     */
    public static void runTestMode() {
        System.out.println("\n" + "#".repeat(70));
        System.out.println("           ViewNoteDetails 系统测试模式");
        System.out.println("#".repeat(70));
        
        int totalTests = 0;
        int passedTests = 0;
        
        // 测试1: 用户登录
        System.out.println("\n测试1: 用户登录功能");
        System.out.println("-".repeat(40));
        totalTests++;
        User admin = User.login("admin123", "adminPass123");
        if (admin != null && admin.isLoggedIn() && admin.isAdmin()) {
            System.out.println("✓ 测试通过: 管理员登录成功");
            passedTests++;
        } else {
            System.out.println("✗ 测试失败: 管理员登录失败");
        }
        
        // 测试2: 笔记详情查看
        System.out.println("\n测试2: 笔记详情查看功能");
        System.out.println("-".repeat(40));
        totalTests++;
        if (admin != null) {
            boolean result = ViewNoteDetails.viewNoteDetails(admin, "N001");
            if (result) {
                System.out.println("✓ 测试通过: 笔记详情查看成功");
                passedTests++;
            } else {
                System.out.println("✗ 测试失败: 笔记详情查看失败");
            }
        }
        
        // 测试3: 权限验证
        System.out.println("\n测试3: 权限验证功能");
        System.out.println("-".repeat(40));
        totalTests++;
        User student = User.login("student1", "studentPass1");
        if (student != null && !student.isAdmin()) {
            System.out.println("✓ 测试通过: 学生用户不是管理员");
            passedTests++;
        } else {
            System.out.println("✗ 测试失败: 权限验证错误");
        }
        
        // 测试4: 笔记搜索
        System.out.println("\n测试4: 笔记搜索功能");
        System.out.println("-".repeat(40));
        totalTests++;
        List<Note> searchResults = ViewNoteDetails.searchNotes("数学");
        if (!searchResults.isEmpty()) {
            System.out.println("✓ 测试通过: 搜索到 " + searchResults.size() + " 条笔记");
            passedTests++;
        } else {
            System.out.println("✗ 测试失败: 搜索功能异常");
        }
        
        // 显示测试结果
        System.out.println("\n" + "=".repeat(40));
        System.out.println("测试结果汇总");
        System.out.println("=".repeat(40));
        System.out.println("总测试数: " + totalTests);
        System.out.println("通过测试: " + passedTests);
        System.out.println("失败测试: " + (totalTests - passedTests));
        System.out.println("通过率: " + (passedTests * 100 / totalTests) + "%");
        
        if (passedTests == totalTests) {
            System.out.println("\n✓ 所有测试通过！系统功能正常。");
        } else {
            System.out.println("\n✗ 部分测试失败，请检查系统功能。");
        }
        
        System.out.println("\n" + "#".repeat(70));
        System.out.println("          测试模式完成");
        System.out.println("#".repeat(70));
    }
}