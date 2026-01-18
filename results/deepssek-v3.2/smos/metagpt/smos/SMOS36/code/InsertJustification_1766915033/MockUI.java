```
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * MockUI - 模拟用户界面类
 * 提供控制台界面来展示应用程序流程，包括登录、表单显示、用户交互等功能。
 * 这个类模拟了管理员插入请假理由的完整用户界面流程。
 */
public class MockUI {
    
    private Scanner scanner;
    private boolean isLoggedIn;
    private String currentAdminId;
    private JustificationRepository repository;
    
    /**
     * 默认构造函数
     * 初始化UI组件和状态
     */
    public MockUI() {
        this.scanner = new Scanner(System.in);
        this.isLoggedIn = false;
        this.currentAdminId = null;
        this.repository = new JustificationRepository();
    }
    
    /**
     * 启动用户界面
     */
    public void start() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║          请假理由管理系统 - 管理员界面          ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        
        try {
            // 模拟安全检查
            performSystemChecks();
            
            // 主要应用程序循环
            while (true) {
                displayMainMenu();
                
                int choice = -1;
                try {
                    System.out.print("\n请选择操作 (1-5): ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); // 消耗换行符
                } catch (InputMismatchException e) {
                    scanner.nextLine(); // 清除无效输入
                    System.out.println("错误：请输入有效的数字选项。");
                    continue;
                }
                
                switch (choice) {
                    case 1:
                        handleLogin();
                        break;
                    case 2:
                        if (checkLogin()) {
                            handleInsertJustification();
                        } else {
                            System.out.println("请先登录系统。");
                        }
                        break;
                    case 3:
                        if (checkLogin()) {
                            handleViewJustifications();
                        } else {
                            System.out.println("请先登录系统。");
                        }
                        break;
                    case 4:
                        handleSystemStatus();
                        break;
                    case 5:
                        handleExit();
                        return;
                    default:
                        System.out.println("无效的选择，请重新输入。");
                }
                
                System.out.println("\n按Enter键继续...");
                scanner.nextLine();
            }
            
        } catch (Exception e) {
            System.out.println("系统发生错误: " + e.getMessage());
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
    
    /**
     * 显示主菜单
     */
    private void displayMainMenu() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                    主菜单");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("1. 管理员登录");
        System.out.println("2. 插入请假理由");
        System.out.println("3. 查看请假理由");
        System.out.println("4. 系统状态");
        System.out.println("5. 退出系统");
        System.out.println("══════════════════════════════════════════════════");
        
        if (isLoggedIn) {
            System.out.println("当前用户: " + currentAdminId + " (已登录)");
        } else {
            System.out.println("当前用户: 未登录");
        }
    }
    
    /**
     * 执行系统检查
     */
    private void performSystemChecks() {
        System.out.println("\n正在执行系统检查...");
        
        // 检查服务器连接
        boolean serverConnected = repository.testConnection();
        if (!serverConnected) {
            System.out.println("⚠ 警告：与SMOS服务器连接不稳定。");
        } else {
            System.out.println("✓ SMOS服务器连接正常。");
        }
        
        // 检查前置用例执行情况（模拟）
        System.out.println("\n检查前置用例执行情况:");
        System.out.println("✓ SviewTetTingloregister用例状态: 已执行");
        System.out.println("✓ viewellacogiustifies用例状态: 已执行");
        System.out.println("✓ 红色缺勤记录检测: 已检测到");
        
        // 检查数据存储初始化
        try {
            int count = repository.count();
            System.out.println("✓ 数据存储初始化完成，现有 " + count + " 条请假理由记录。");
        } catch (JustificationRepository.RepositoryException e) {
            System.out.println("⚠ 警告：数据存储检查失败 - " + e.getMessage());
        }
        
        System.out.println("\n系统检查完成，可以正常使用。");
    }
    
    /**
     * 处理管理员登录
     */
    private void handleLogin() {
        if (isLoggedIn) {
            System.out.println("\n当前已登录为管理员: " + currentAdminId);
            System.out.print("是否要注销并重新登录？(y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (!response.equals("y") && !response.equals("yes")) {
                System.out.println("取消重新登录。");
                return;
            }
            
            isLoggedIn = false;
            currentAdminId = null;
        }
        
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                    管理员登录");
        System.out.println("══════════════════════════════════════════════════");
        
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print("\n管理员用户名: ");
                String username = scanner.nextLine().trim();
                
                System.out.print("密码: ");
                String password = scanner.nextLine().trim();
                
                // 模拟管理员验证
                boolean isValid = validateAdminCredentials(username, password);
                
                if (isValid) {
                    isLoggedIn = true;
                    currentAdminId = "admin_" + username;
                    
                    System.out.println("\n✅ 登录成功！");
                    System.out.println("欢迎，管理员 " + username);
                    
                    // 模拟登录后操作
                    simulatePostLoginOperations();
                    
                    return;
                } else {
                    attempts++;
                    int remainingAttempts = MAX_ATTEMPTS - attempts;
                    
                    if (remainingAttempts > 0) {
                        System.out.println("\n❌ 登录失败，用户名或密码不正确。");
                        System.out.println("剩余尝试次数: " + remainingAttempts);
                    } else {
                        System.out.println("\n❌ 已达到最大尝试次数，登录失败。");
                    }
                }
                
            } catch (Exception e) {
                attempts++;
                System.out.println("登录过程中发生错误: " + e.getMessage());
            }
        }
    }
    
    /**
     * 验证管理员凭据
     * @param username 用户名
     * @param password 密码
     * @return true如果验证成功
     */
    private boolean validateAdminCredentials(String username, String password) {
        // 模拟管理员验证逻辑
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        // 检查服务器连接
        if (!repository.isConnected()) {
            System.out.println("⚠ 警告：服务器连接异常，使用本地验证。");
        }
        
        // 简单的验证逻辑（实际应用中会有更复杂的验证机制）
        return "admin".equals(username) && "admin123".equals(password);
    }
    
    /**
     * 模拟登录后操作
     */
    private void simulatePostLoginOperations() {
        System.out.println("\n正在进行登录后初始化...");
        
        try {
            // 模拟前置用例检查
            Thread.sleep(500);
            System.out.println("✓ 检查前置用例执行情况... 完成");
            
            // 模拟加载用户数据
            Thread.sleep(300);
            System.out.println("✓ 加载用户配置... 完成");
            
            // 模拟检查选中的缺勤记录
            Thread.sleep(400);
            System.out.println("✓ 检查选中的红色缺勤记录... 完成");
            
            System.out.println("登录后初始化完成，可以开始操作。");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * 处理插入请假理由操作
     * 这是主要的用例实现
     */
    private void handleInsertJustification() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                  插入请假理由");
        System.out.println("══════════════════════════════════════════════════");
        
        // 检查前置条件
        if (!checkPreconditionsForInsertion()) {
            System.out.println("插入操作取消。");
            return;
        }
        
        try {
            // 第一步：检查服务器连接
            System.out.println("\n1. 检查服务器连接...");
            if (!repository.isConnected()) {
                System.out.println("❌ 错误：无法连接到SMOS服务器。");
                throw new ServerConnectionException("SMOS服务器连接中断");
            }
            System.out.println("✓ 服务器连接正常");
            
            // 第二步：显示红色缺勤记录信息
            System.out.println("\n2. 显示选中的缺勤记录...");
            displaySelectedAbsenceRecord();
            
            // 第三步：创建并显示表单
            System.out.println("\n3. 显示请假理由表单...");
            JustificationForm form = new JustificationForm("ABS_RED_001", currentAdminId);
            form.displayForm();
            
            // 第四步：收集用户输入
            System.out.println("\n4. 填写表单...");
            Justification justification = form.collectInput(scanner);
            
            if (justification == null) {
                System.out.println("表单填写已取消。");
                return;
            }
            
            // 第五步：验证数据
            System.out.println("\n5. 验证输入数据...");
            if (ValidationUtils.validateJustification(justification)) {
                System.out.println("✓ 数据验证通过");
            } else {
                System.out.println("❌ 数据验证失败，请检查输入。");
                return;
            }
            
            // 第六步：确认保存
            System.out.println("\n6. 确认保存...");
            System.out.println(justification);
            System.out.print("\n是否确认保存请假理由？(y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (!confirm.equals("y") && !confirm.equals("yes")) {
                System.out.println("保存操作已取消。");
                return;
            }
            
            // 第七步：保存到数据库
            System.out.println("\n7. 保存到数据库...");
            boolean saved = repository.save(justification);
            
            if (saved) {
                System.out.println("\n✅ 请假理由已成功保存到系统！");
                
                // 第八步：模拟返回登记屏幕
                System.out.println("\n8. 返回登记屏幕...");
                simulateReturnToRegistryScreen();
                
                // 显示保存的详细信息
                System.out.println("\n══════════════════════════════════════════════════");
                System.out.println("                   保存详细信息");
                System.out.println("══════════════════════════════════════════════════");
                System.out.println(justification.toDetailedString());
                System.out.println("══════════════════════════════════════════════════");
            } else {
                System.out.println("❌ 保存失败，请稍后重试。");
            }
            
        } catch (ServerConnectionException e) {
            System.out.println("❌ 错误：" + e.getMessage());
            System.out.println("操作中断：连接SMOS服务器失败。");
        } catch (JustificationForm.OperationInterruptedException e) {
            System.out.println("操作中断：" + e.getMessage());
        } catch (JustificationRepository.RepositoryException e) {
            System.out.println("数据库错误：" + e.getMessage());
        } catch (Exception e) {
            System.out.println("系统错误：" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * 检查插入操作的前置条件
     * @return true如果满足所有前置条件
     */
    private boolean checkPreconditionsForInsertion() {
        System.out.println("检查插入操作的前置条件...");
        
        boolean allConditionsMet = true;
        
        // 条件1: 管理员必须已登录
        if (!isLoggedIn) {
            System.out.println("❌ 条件未满足：管理员未登录");
            allConditionsMet = false;
        } else {
            System.out.println("✓ 管理员已登录：" + currentAdminId);
        }
        
        // 条件2: 必须已执行SviewTetTingloregister用例（模拟）
        System.out.println("✓ 用例SviewTetTingloregister已执行");
        
        // 条件3: 必须已执行viewellacogiustifies用例（模拟）
        System.out.println("✓ 用例viewellacogiustifies已执行");
        
        // 条件4: 用户必须点击了红色缺勤记录（模拟）
        System.out.println("✓ 已选择红色缺勤记录");
        
        if (allConditionsMet) {
            System.out.println("✓ 所有前置条件已满足");
        } else {
            System.out.println("❌ 部分前置条件未满足");
        }
        
        return allConditionsMet;
    }
    
    /**
     * 显示选中的缺勤记录信息
     */
    private void displaySelectedAbsenceRecord() {
        System.out.println("选中的缺勤记录：");
        System.out.println("  记录ID: ABS_RED_001");
        System.out.println("  员工姓名: 张三");
        System.out.println("  缺勤日期: 2024-12-25");
        System.out.println("  缺勤类型: 未请假缺勤");
        System.out.println("  状态: 红色标记（需要请假理由）");
        System.out.println("  备注: 该记录已被选中，需要填写请假理由");
    }
    
    /**
     * 模拟返回登记屏幕
     */
    private void simulateReturnToRegistryScreen() {
        System.out.println("正在返回登记屏幕...");
        
        try {
            // 模拟屏幕切换延迟
            for (int i = 0; i < 3; i++) {
                Thread.sleep(200);
                System.out.print(".");
            }
            
            System.out.println("\n已成功返回到员工考勤登记屏幕。");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * 处理查看请假理由操作
     */
    private void handleViewJustifications() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                  查看请假理由");
        System.out.println("══════════════════════════════════════════════════");
        
        try {
            List<Justification> justifications = repository.findAll();
            
            if (justifications.isEmpty()) {
                System.out.println("暂无请假理由记录。");
                return;
            }
            
            System.out.println("请假理由列表 (共 " + justifications.size() + " 条记录):");
            System.out.println("══════════════════════════════════════════════════");
            
            for (int i = 0; i < justifications.size(); i++) {
                Justification j = justifications.get(i);
                System.out.println("记录 #" + (i + 1));
                System.out.println(j);
                System.out.println("──────────────────────────────────────────────");
            }
            
            // 提供详细查看选项
            System.out.print("\n输入记录编号查看详细信息 (0返回): ");
            try {
                int selection = scanner.nextInt();
                scanner.nextLine(); // 消耗换行符
                
                if (selection > 0 && selection <= justifications.size()) {
                    Justification selected = justifications.get(selection - 1);
                    System.out.println("\n══════════════════════════════════════════════════");
                    System.out.println("                   详细记录信息");
                    System.out.println("══════════════════════════════════════════════════");
                    System.out.println(selected.toDetailedString());
                    System.out.println("══════════════════════════════════════════════════");
                } else if (selection != 0) {
                    System.out.println("无效的选择。");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); // 清除无效输入
                System.out.println("请输入有效的数字。");
            }
            
        } catch (JustificationRepository.RepositoryException e) {
            System.out.println("获取请假理由失败: " + e.getMessage());
        }
    }
    
    /**
     * 处理系统状态显示
     */
    private void handleSystemStatus() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                    系统状态");
        System.out.println("══════════════════════════════════════════════════");
        
        // 系统信息
        System.out.println("系统信息:");
        System.out.println("  系统名称: 请假理由管理系统");
        System.out.println("  版本: 1.0.0");
        System.out.println("  开发团队: 系统管理组");
        
        // 用户状态
        System.out.println("\n用户状态:");
        System.out.println("  登录状态: " + (isLoggedIn ? "已登录" : "未登录"));
        System.out.println("  用户ID: " + (currentAdminId != null ? currentAdminId : "无"));
        
        // 连接状态
        System.out.println("\n连接状态:");
        System.out.println("  SMOS服务器: " + (repository.isConnected() ? "在线" : "离线"));
        
        // 数据库状态
        System.out.println("\n数据库状态:");
        try {
            int count = repository.count();
            System.out.println("  请假理由记录数: " + count);
            System.out.println("  数据存储状态: 正常");
        } catch (JustificationRepository.RepositoryException e) {
            System.out.println("  数据存储状态: 异常 - " + e.getMessage());
        }
        
        // 最后活动时间
        System.out.println("\n最后活动时间: " + LocalDate.now());
        System.out.println("══════════════════════════════════════════════════");
    }
    
    /**
     * 处理系统退出
     */
    private void handleExit() {
        System.out.println("\n正在退出系统...");
        
        try {
            if (isLoggedIn) {
                System.out.println("正在注销管理员账户...");
                Thread.sleep(500);
                System.out.println("管理员 " + currentAdminId + " 已注销。");
           