我需要完成Main.java类，这是程序的入口点，将演示完整的学生成绩单系统功能。我将创建一个完整的、可运行的Java程序，包含所有必要的类和功能。

```java
/**
 * Main.java
 * 学生成绩单系统的主类
 * 演示完整的学生成绩单插入流程，包括管理员登录、显示班级列表、选择学生、插入成绩单等
 * 按照用例要求实现所有功能
 */
import java.util.Scanner;

public class Main {
    /**
     * 主方法 - 程序入口点
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   学生成绩单管理系统 v1.0");
        System.out.println("========================================");
        
        // 创建系统实例
        ReportSystem reportSystem = new ReportSystem();
        
        // 创建管理员实例
        Administrator admin = new Administrator();
        
        // 设置管理员到系统
        reportSystem.setCurrentAdmin(admin);
        
        // 创建扫描器用于用户输入
        Scanner scanner = new Scanner(System.in);
        
        // 主程序循环
        boolean running = true;
        while (running) {
            System.out.println("\n=== 主菜单 ===");
            System.out.println("1. 管理员登录");
            System.out.println("2. 演示完整成绩单插入流程");
            System.out.println("3. 查看系统状态");
            System.out.println("4. 测试SMOS服务器连接");
            System.out.println("5. 查看所有班级和学生");
            System.out.println("6. 退出系统");
            System.out.print("请选择操作 (1-6): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    // 管理员登录
                    handleAdminLogin(admin, scanner);
                    break;
                case "2":
                    // 演示完整成绩单插入流程
                    demonstrateInsertPageWorkflow(reportSystem, admin, scanner);
                    break;
                case "3":
                    // 查看系统状态
                    reportSystem.displaySystemStats();
                    break;
                case "4":
                    // 测试SMOS服务器连接
                    testSMOSServerConnection(reportSystem);
                    break;
                case "5":
                    // 查看所有班级和学生
                    displayAllClassesAndStudents(reportSystem);
                    break;
                case "6":
                    // 退出系统
                    System.out.println("感谢使用学生成绩单管理系统，再见！");
                    running = false;
                    break;
                default:
                    System.err.println("错误：无效的选择，请重新输入");
            }
        }
        
        scanner.close();
    }
    
    /**
     * 处理管理员登录
     * @param admin 管理员对象
     * @param scanner 扫描器对象
     */
    private static void handleAdminLogin(Administrator admin, Scanner scanner) {
        System.out.println("\n=== 管理员登录 ===");
        
        if (admin.isLoggedIn()) {
            System.out.println("管理员已登录，无需重复登录");
            return;
        }
        
        System.out.print("请输入用户名 (默认: admin): ");
        String username = scanner.nextLine();
        if (username.trim().isEmpty()) {
            username = "admin";
        }
        
        System.out.print("请输入密码 (默认: admin123): ");
        String password = scanner.nextLine();
        if (password.trim().isEmpty()) {
            password = "admin123";
        }
        
        boolean loginSuccess = admin.login(username, password);
        if (loginSuccess) {
            System.out.println("登录成功！");
        } else {
            System.out.println("登录失败，请检查用户名和密码");
        }
    }
    
    /**
     * 演示完整的成绩单插入流程
     * 按照用例的事件序列执行
     * @param reportSystem 报告系统对象
     * @param admin 管理员对象
     * @param scanner 扫描器对象
     */
    private static void demonstrateInsertPageWorkflow(ReportSystem reportSystem, Administrator admin, Scanner scanner) {
        System.out.println("\n========================================");
        System.out.println("开始演示成绩单插入流程...");
        System.out.println("用例：InsertPage");
        System.out.println("参与者：管理员");
        System.out.println("========================================\n");
        
        // 前置条件检查
        System.out.println("[前置条件检查]");
        System.out.println("1. 用户已以管理员身份登录系统");
        
        if (!admin.isLoggedIn()) {
            System.out.println("   ❌ 管理员未登录，正在尝试自动登录...");
            boolean autoLogin = admin.login("admin", "admin123");
            if (!autoLogin) {
                System.err.println("   ❌ 自动登录失败，请先登录管理员账户");
                return;
            }
            System.out.println("   ✓ 自动登录成功");
        } else {
            System.out.println("   ✓ 管理员已登录");
        }
        
        System.out.println("2. 用户点击\"在线报告\"按钮");
        System.out.println("   ✓ 模拟用户点击操作");
        System.out.println();
        
        // 事件序列：系统1 - 显示班级列表
        System.out.println("[事件序列：系统1]");
        System.out.println("系统显示当前学年的班级列表，每个班级旁边有一个\"成绩单\"按钮");
        System.out.println();
        
        // 获取当前学年的班级列表
        java.util.List<ClassRoom> classes = reportSystem.getClassesForCurrentAcademicYear();
        if (classes.isEmpty()) {
            System.err.println("错误：系统中没有班级数据");
            return;
        }
        
        System.out.println("=== 当前学年班级列表 ===");
        System.out.println("学年: " + reportSystem.getCurrentAcademicYear());
        System.out.println("每个班级旁边有一个\"成绩单\"按钮");
        System.out.println("------------------------");
        
        for (int i = 0; i < classes.size(); i++) {
            ClassRoom classRoom = classes.get(i);
            System.out.printf("%d. %-20s [成绩单按钮]%n", 
                    i + 1, classRoom.getSimpleInfo());
        }
        System.out.println();
        
        // 事件序列：用户2 - 选择班级
        System.out.println("[事件序列：用户2]");
        System.out.println("用户选择要插入成绩单的学生班级");
        System.out.print("请输入要选择的班级编号 (1-" + classes.size() + "): ");
        
        int classChoice = 0;
        try {
            classChoice = Integer.parseInt(scanner.nextLine());
            if (classChoice < 1 || classChoice > classes.size()) {
                System.err.println("错误：无效的班级编号，流程终止");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("错误：请输入有效的数字，流程终止");
            return;
        }
        
        ClassRoom selectedClass = classes.get(classChoice - 1);
        System.out.println("已选择班级: " + selectedClass.getClassName());
        System.out.println();
        
        // 事件序列：系统3 - 显示班级学生列表
        System.out.println("[事件序列：系统3]");
        System.out.println("系统显示用户选择的班级学生列表");
        System.out.println();
        
        System.out.println("=== 班级学生列表 ===");
        System.out.println("班级: " + selectedClass.getClassName());
        System.out.println("------------------------");
        
        java.util.List<Student> students = selectedClass.getStudents();
        if (students.isEmpty()) {
            System.err.println("错误：该班级没有学生，流程终止");
            return;
        }
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%d. %s%n", i + 1, student.getSimpleInfo());
        }
        System.out.println();
        
        // 事件序列：用户4 - 选择学生
        System.out.println("[事件序列：用户4]");
        System.out.println("用户通过点击相关键选择要插入成绩单的学生");
        System.out.print("请输入要选择的学生编号 (1-" + students.size() + "): ");
        
        int studentChoice = 0;
        try {
            studentChoice = Integer.parseInt(scanner.nextLine());
            if (studentChoice < 1 || studentChoice > students.size()) {
                System.err.println("错误：无效的学生编号，流程终止");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("错误：请输入有效的数字，流程终止");
            return;
        }
        
        Student selectedStudent = students.get(studentChoice - 1);
        System.out.println("已选择学生: " + selectedStudent.getName() + " (ID: " + selectedStudent.getId() + ")");
        System.out.println();
        
        // 事件序列：系统5 - 显示插入成绩单的表单
        System.out.println("[事件序列：系统5]");
        System.out.println("系统显示插入成绩单的表单");
        System.out.println();
        
        System.out.println("=== 插入成绩单表单 ===");
        System.out.println("学生: " + selectedStudent.getName());
        System.out.println("班级: " + selectedClass.getClassName());
        System.out.println("学年: " + reportSystem.getCurrentAcademicYear());
        System.out.println("------------------------");
        
        // 创建新的成绩单
        ReportCard newReportCard = new ReportCard();
        newReportCard.setStudentId(selectedStudent.getId());
        newReportCard.setStudentName(selectedStudent.getName());
        newReportCard.setAcademicYear(reportSystem.getCurrentAcademicYear());
        newReportCard.setSemester("2023-2024学年第二学期");
        
        // 事件序列：用户6 - 插入成绩单分数
        System.out.println("[事件序列：用户6]");
        System.out.println("用户插入成绩单的分数（全部或部分）");
        System.out.println("请输入科目成绩（输入 'done' 完成，输入 'cancel' 取消）");
        System.out.println("分数范围: 0-100");
        System.out.println();
        
        boolean addingGrades = true;
        while (addingGrades) {
            System.out.print("请输入科目名称: ");
            String subject = scanner.nextLine().trim();
            
            if (subject.equalsIgnoreCase("done")) {
                if (newReportCard.getSubjectCount() == 0) {
                    System.out.println("警告：至少需要添加一个科目，请继续输入或输入 'cancel' 取消");
                    continue;
                }
                addingGrades = false;
                break;
            } else if (subject.equalsIgnoreCase("cancel")) {
                System.out.println("操作已取消");
                return;
            } else if (subject.isEmpty()) {
                System.err.println("错误：科目名称不能为空");
                continue;
            }
            
            System.out.print("请输入 " + subject + " 科目的分数 (0-100): ");
            String scoreInput = scanner.nextLine().trim();
            
            try {
                double score = Double.parseDouble(scoreInput);
                if (score < 0 || score > 100) {
                    System.err.println("错误：分数必须在0-100之间");
                    continue;
                }
                
                if (newReportCard.addGrade(subject, score)) {
                    System.out.println("✓ 已添加科目: " + subject + "，分数: " + score);
                    System.out.println("当前已添加 " + newReportCard.getSubjectCount() + " 个科目");
                } else {
                    System.err.println("错误：添加科目失败");
                }
            } catch (NumberFormatException e) {
                System.err.println("错误：请输入有效的数字");
            }
            
            System.out.println();
        }
        
        // 输入教师评语
        System.out.print("请输入教师评语 (可选，直接回车跳过): ");
        String comment = scanner.nextLine().trim();
        if (!comment.isEmpty()) {
            newReportCard.setTeacherComment(comment);
        }
        
        // 显示成绩单预览
        System.out.println("\n=== 成绩单预览 ===");
        newReportCard.displayReportCard();
        
        // 确认保存
        System.out.print("\n是否保存此成绩单？(yes/no): ");
        String confirm = scanner.nextLine().trim();
        
        if (!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("y")) {
            System.out.println("操作已取消");
            
            // 后置条件：用户中断操作
            System.out.println("\n[后置条件：用户中断操作]");
            System.out.println("用户已取消成绩单保存操作");
            reportSystem.handleUserInterruption();
            return;
        }
        
        // 保存成绩单
        System.out.println("\n正在保存成绩单...");
        
        // 事件序列：系统7 - 保存成绩单并返回
        System.out.println("[事件序列：系统7]");
        System.out.println("系统将输入的分数存入档案，并将用户返回到班级学生视图页面");
        System.out.println();
        
        boolean saveSuccess = reportSystem.saveReportCard(newReportCard);
        
        if (saveSuccess) {
            System.out.println("✓ 成绩单保存成功！");
            System.out.println("成绩单ID: " + newReportCard.getId());
            System.out.println("学生: " + selectedStudent.getName());
            System.out.println("平均分: " + newReportCard.getAverageScore());
            System.out.println("科目数量: " + newReportCard.getSubjectCount());
            
            // 后置条件：学生的报告已添加到系统
            System.out.println("\n[后置条件：学生的报告已添加到系统]");
            System.out.println("✓ 学生的成绩单已成功添加到系统");
            
            // 询问是否归档
            System.out.print("\n是否立即归档此成绩单到SMOS服务器？(yes/no): ");
            String archiveChoice = scanner.nextLine().trim();
            if (archiveChoice.equalsIgnoreCase("yes") || archiveChoice.equalsIgnoreCase("y")) {
                System.out.println("正在归档成绩单...");
                boolean archiveSuccess = reportSystem.archiveReportCard(newReportCard.getId());
                
                if (!archiveSuccess) {
                    // 后置条件：与SMOS服务器的连接中断
                    System.out.println("\n[后置条件：与SMOS服务器的连接中断]");
                    System.out.println("⚠ 归档失败：与SMOS服务器的连接可能已中断");
                    reportSystem.handleServerConnectionInterruption(newReportCard);
                } else {
                    System.out.println("✓ 成绩单归档成功");
                }
            }
            
            // 返回班级学生视图
            System.out.println("\n正在返回班级学生视图页面...");
            System.out.println("\n=== 班级学生视图 ===");
            System.out.println("班级: " + selectedClass.getClassName());
            selectedClass.displayAllStudents();
            
        } else {
            System.err.println("❌ 成绩单保存失败");
            
            // 后置条件：与SMOS服务器的连接中断
            System.out.println("\n[后置条件：与SMOS服务器的连接中断]");
            System.out.println("⚠ 保存失败：与SMOS服务器的连接可能已中断");
            reportSystem.handleServerConnectionInterruption(newReportCard);
        }
        
        System.out.println("\n========================================");
        System.out.println("成绩单插入流程演示完成");
        System.out.println("========================================");
    }
    
    /**
     * 测试SMOS服务器连接
     * @param reportSystem 报告系统对象
     */
    private static void testSMOSServerConnection(ReportSystem reportSystem) {
        System.out.println("\n=== 测试SMOS服务器连接 ===");
        
        SMOSServer server = reportSystem.getSMOSServer();
        if (server == null) {
            System.err.println("错误：SMOS服务器未初始化");
            return;
        }
        
        server.displayServerStatus();
        
        System.out.print("是否测试连接？(yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();
        
        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
            boolean success = server.testConnection();
            if (success) {
                System.out.println("✓ SMOS服务器连接测试成功");
            } else {
                System.out.println("❌ SMOS服务器连接测试失败");
            }
        }
    }
    
    /**
     * 显示所有班级和学生
     * @param reportSystem 报告系统对象
     */
    private static void displayAllClassesAndStudents(ReportSystem reportSystem) {
        System.out.println("\n=== 所有班级和学生信息 ===");
        
        java.util.List<ClassRoom> classes = reportSystem.getAllClasses();
        if (classes.isEmpty()) {
            System.out.println("系统中没有班级数据");
            return;
        }
        
        System.out.println("系统中共有 " + classes.size() + " 个班级:");
        System.out.println();
        
        for (ClassRoom classRoom : classes) {
            classRoom.displayClassInfo();
            System.out.println();
            
            java.util.List<Student> students = classRoom.getStudents();
            if (!students.isEmpty()) {
                System.out.println("班级学生列表:");
                for (int i = 0; i < students.size(); i++) {
                    Student student = students.get(i);
                    System.out.printf("  %d. %s%n", i + 1, student.getSimpleInfo());
                    
                    // 显示该学生的成绩单
                    java.util.List<ReportCard> reportCards = reportSystem.getReportCardsByStudentId(student.getId());
                    if (!reportCards.isEmpty()) {
                        System.out.println("    成绩单:");
                        for (ReportCard reportCard : reportCards) {
                            System.out.println("      - " + reportCard.getSimpleInfo());
                        }
                    }
                }
            } else {
                System.out.println("  该班级暂无学生");
            }
            
            System.out.println("------------------------");
        }
    }
    
    /**
     * 演示系统的基本功能
     * 这是一个辅助方法，用于展示系统的各种功能
     */
    private static void demonstrateSystemFeatures() {
        System.out.println("\n=== 系统功能演示 ===");
        
        // 创建系统实例
        ReportSystem system = new ReportSystem();
        
        // 显示系统状态
        system.displaySystemStats();
        
        // 测试SMOS服务器
        SMOSServer server = system.getSMOSServer();
        if (server != null) {
            System.out.println("\n测试SMOS服务器连接...");
            boolean connected = server.connect();
            System.out.println("连接状态: " + (connected ? "成功" : "失败"));
            
            if (connected) {
                System.out.println("服务器状态:");
                server.displayServerStatus();
            }
        }
        
        // 创建新班级
        System.out.println("\n创建新班级...");
        ClassRoom newClass = system.createNewClass("三年级一班", "陈老师", 35);
        if (newClass != null) {
            System.out.println("新班级创建成功: " + newClass.getClassName());
        }
        
        // 创建新学生
        System.out.println("\n创建新学生...");
        if (newClass != null) {
            Student newStudent = system.createNewStudent("测试学生", newClass.getId(), 9);
           