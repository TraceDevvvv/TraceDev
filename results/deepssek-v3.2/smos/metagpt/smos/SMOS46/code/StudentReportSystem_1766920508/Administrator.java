我将创建Administrator.java类。这个类将模拟管理员用户的行为和功能，包括登录、显示班级列表、选择学生、插入成绩单等操作。

```
/**
 * Administrator.java
 * 管理员类，模拟管理员用户的行为和功能
 * 包含登录、显示班级列表、选择班级、查看学生、插入成绩单等操作
 */
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Administrator {
    private int adminId;                     // 管理员ID
    private String adminName;                // 管理员姓名
    private String username;                 // 登录用户名
    private String password;                 // 登录密码（实际应用中应加密存储）
    private boolean isLoggedIn;              // 登录状态
    private Date lastLoginTime;              // 上次登录时间
    private ReportSystem reportSystem;       // 报告系统引用
    private Scanner scanner;                 // 用户输入扫描器
    
    /**
     * 默认构造函数
     */
    public Administrator() {
        this.adminId = 1;
        this.adminName = "系统管理员";
        this.username = "admin";
        this.password = "admin123"; // 简单密码，实际应用中应使用加密
        this.isLoggedIn = false;
        this.lastLoginTime = null;
        this.reportSystem = null;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 带参数的构造函数
     * @param adminId 管理员ID
     * @param adminName 管理员姓名
     * @param username 用户名
     * @param password 密码
     */
    public Administrator(int adminId, String adminName, String username, String password) {
        this.adminId = adminId;
        this.adminName = adminName;
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
        this.lastLoginTime = null;
        this.reportSystem = null;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * 设置报告系统引用
     * @param reportSystem 报告系统对象
     */
    public void setReportSystem(ReportSystem reportSystem) {
        if (reportSystem == null) {
            throw new IllegalArgumentException("报告系统不能为空");
        }
        this.reportSystem = reportSystem;
    }
    
    // Getter 方法
    public int getAdminId() { return adminId; }
    public String getAdminName() { return adminName; }
    public String getUsername() { return username; }
    public boolean isLoggedIn() { return isLoggedIn; }
    public Date getLastLoginTime() { return lastLoginTime; }
    
    /**
     * 管理员登录
     * @param inputUsername 输入的用户名
     * @param inputPassword 输入的密码
     * @return 如果登录成功返回true，否则返回false
     */
    public boolean login(String inputUsername, String inputPassword) {
        if (inputUsername == null || inputPassword == null) {
            System.err.println("错误：用户名和密码不能为空");
            return false;
        }
        
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            isLoggedIn = true;
            lastLoginTime = new Date();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("======================================");
            System.out.println("管理员 " + adminName + " 登录成功！");
            System.out.println("登录时间: " + sdf.format(lastLoginTime));
            System.out.println("======================================");
            return true;
        } else {
            System.err.println("错误：用户名或密码不正确");
            return false;
        }
    }
    
    /**
     * 交互式登录
     * @return 如果登录成功返回true，否则返回false
     */
    public boolean interactiveLogin() {
        if (isLoggedIn) {
            System.out.println("管理员已登录，无需重复登录");
            return true;
        }
        
        System.out.println("\n=== 管理员登录 ===");
        System.out.print("请输入用户名: ");
        String inputUsername = scanner.nextLine();
        
        System.out.print("请输入密码: ");
        String inputPassword = scanner.nextLine();
        
        return login(inputUsername, inputPassword);
    }
    
    /**
     * 管理员登出
     */
    public void logout() {
        if (isLoggedIn) {
            isLoggedIn = false;
            System.out.println("管理员 " + adminName + " 已登出");
        } else {
            System.out.println("当前未登录");
        }
    }
    
    /**
     * 检查登录状态
     * @return 如果已登录返回true，否则返回false
     */
    private boolean checkLoginStatus() {
        if (!isLoggedIn) {
            System.err.println("错误：请先登录管理员账户");
            System.out.println("尝试自动登录默认账户...");
            return login("admin", "admin123");
        }
        return true;
    }
    
    /**
     * 检查报告系统是否可用
     * @return 如果报告系统可用返回true，否则返回false
     */
    private boolean checkReportSystem() {
        if (reportSystem == null) {
            System.err.println("错误：报告系统未初始化");
            return false;
        }
        return true;
    }
    
    /**
     * 显示主菜单
     */
    public void showMainMenu() {
        if (!checkLoginStatus()) {
            return;
        }
        
        while (true) {
            System.out.println("\n=== 学生成绩单管理系统 ===");
            System.out.println("当前用户: " + adminName + " (管理员)");
            System.out.println("1. 查看所有班级");
            System.out.println("2. 插入学生成绩单");
            System.out.println("3. 查看学生成绩单");
            System.out.println("4. 查看系统状态");
            System.out.println("5. 退出系统");
            System.out.print("请选择操作 (1-5): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    // 前置条件：用户已点击"在线报告"按钮
                    showAllClasses();
                    break;
                case "2":
                    // 插入成绩单流程
                    insertReportCardWorkflow();
                    break;
                case "3":
                    // 查看成绩单
                    viewReportCardWorkflow();
                    break;
                case "4":
                    // 查看系统状态
                    showSystemStatus();
                    break;
                case "5":
                    System.out.println("谢谢使用，再见！");
                    logout();
                    return;
                default:
                    System.err.println("错误：无效的选择，请重新输入");
            }
        }
    }
    
    /**
     * 显示系统中所有班级（事件序列：系统1）
     */
    public void showAllClasses() {
        if (!checkLoginStatus() || !checkReportSystem()) {
            return;
        }
        
        System.out.println("\n=== 当前学年班级列表 ===");
        System.out.println("说明：每个班级旁边有一个\"成绩单\"按钮");
        System.out.println("========================\n");
        
        List<ClassRoom> classes = reportSystem.getAllClasses();
        if (classes.isEmpty()) {
            System.out.println("系统中暂无班级信息");
            return;
        }
        
        for (int i = 0; i < classes.size(); i++) {
            ClassRoom classRoom = classes.get(i);
            System.out.printf("%d. %-30s [成绩单按钮]%n", 
                    i + 1, classRoom.getSimpleInfo());
        }
        
        System.out.println("\n输入班级编号查看详细信息，输入0返回主菜单");
        System.out.print("请选择: ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                return;
            } else if (choice >= 1 && choice <= classes.size()) {
                showClassDetails(classes.get(choice - 1));
            } else {
                System.err.println("错误：无效的班级编号");
            }
        } catch (NumberFormatException e) {
            System.err.println("错误：请输入有效的数字");
        }
    }
    
    /**
     * 显示班级详细信息（事件序列：用户2）
     * @param classRoom 班级对象
     */
    private void showClassDetails(ClassRoom classRoom) {
        if (classRoom == null) {
            System.err.println("错误：班级对象为空");
            return;
        }
        
        classRoom.displayClassInfo();
        System.out.println();
        
        // 显示班级学生列表（事件序列：系统3）
        showClassStudents(classRoom);
    }
    
    /**
     * 显示班级学生列表（事件序列：系统3）
     * @param classRoom 班级对象
     */
    private void showClassStudents(ClassRoom classRoom) {
        System.out.println("\n=== 班级学生列表 ===");
        classRoom.displayAllStudents();
        
        System.out.println("\n操作:");
        System.out.println("1. 选择学生插入成绩单");
        System.out.println("2. 返回班级列表");
        System.out.println("3. 返回主菜单");
        System.out.print("请选择: ");
        
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                selectStudentForReportCard(classRoom);
                break;
            case "2":
                showAllClasses();
                break;
            case "3":
                // 直接返回主菜单
                break;
            default:
                System.err.println("错误：无效的选择");
        }
    }
    
    /**
     * 选择学生插入成绩单（事件序列：用户4）
     * @param classRoom 班级对象
     */
    private void selectStudentForReportCard(ClassRoom classRoom) {
        List<Student> students = classRoom.getStudents();
        if (students.isEmpty()) {
            System.out.println("该班级暂无学生，无法插入成绩单");
            return;
        }
        
        System.out.println("\n=== 选择学生插入成绩单 ===");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%d. %s%n", i + 1, student.getSimpleInfo());
        }
        
        System.out.print("请输入学生编号 (输入0取消): ");
        
        try {
            int studentIndex = Integer.parseInt(scanner.nextLine());
            if (studentIndex == 0) {
                System.out.println("操作已取消");
                return;
            }
            
            if (studentIndex >= 1 && studentIndex <= students.size()) {
                Student selectedStudent = students.get(studentIndex - 1);
                System.out.println("已选择学生: " + selectedStudent.getName());
                
                // 显示插入成绩单的表单（事件序列：系统5）
                showReportCardForm(selectedStudent);
            } else {
                System.err.println("错误：无效的学生编号");
            }
        } catch (NumberFormatException e) {
            System.err.println("错误：请输入有效的数字");
        }
    }
    
    /**
     * 显示插入成绩单的表单（事件序列：系统5）
     * @param student 选中的学生
     */
    private void showReportCardForm(Student student) {
        System.out.println("\n=== 插入成绩单表单 ===");
        System.out.println("学生: " + student.getName() + " (ID: " + student.getId() + ")");
        System.out.println("班级: " + student.getClassId());
        System.out.println("请按科目输入成绩 (分数范围: 0-100)");
        System.out.println("输入 'done' 完成输入，输入 'cancel' 取消操作");
        System.out.println("================================\n");
        
        // 创建新的成绩单对象
        ReportCard reportCard = new ReportCard();
        reportCard.setStudentId(student.getId());
        reportCard.setStudentName(student.getName());
        reportCard.setSemester("2023-2024学年第二学期");
        
        // 设置默认学年
        if (reportSystem != null) {
            reportCard.setAcademicYear(reportSystem.getCurrentAcademicYear());
        }
        
        // 输入成绩
        while (true) {
            System.out.print("请输入科目名称 (或输入 'done'/'cancel'): ");
            String subject = scanner.nextLine().trim();
            
            if (subject.equalsIgnoreCase("done")) {
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
                
                if (reportCard.addGrade(subject, score)) {
                    System.out.println("已添加科目: " + subject + "，分数: " + score);
                } else {
                    System.err.println("添加科目失败，请重试");
                }
            } catch (NumberFormatException e) {
                System.err.println("错误：请输入有效的数字");
            }
        }
        
        // 检查是否有科目
        if (reportCard.getSubjectCount() == 0) {
            System.out.println("未添加任何科目，操作取消");
            return;
        }
        
        // 输入教师评语
        System.out.print("请输入教师评语 (可选，直接回车跳过): ");
        String comment = scanner.nextLine().trim();
        if (!comment.isEmpty()) {
            reportCard.setTeacherComment(comment);
        }
        
        // 确认保存
        System.out.println("\n=== 成绩单预览 ===");
        reportCard.displayReportCard();
        
        System.out.print("\n是否保存此成绩单？(yes/no): ");
        String confirm = scanner.nextLine().trim();
        
        if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
            // 保存成绩单（事件序列：用户6）
            saveReportCard(reportCard, student);
        } else {
            System.out.println("已取消保存");
        }
    }
    
    /**
     * 保存成绩单（事件序列：用户6 + 系统7）
     * @param reportCard 成绩单对象
     * @param student 学生对象
     */
    private void saveReportCard(ReportCard reportCard, Student student) {
        if (!checkReportSystem()) {
            return;
        }
        
        try {
            // 保存到系统
            boolean success = reportSystem.saveReportCard(reportCard);
            
            if (success) {
                System.out.println("\n======================================");
                System.out.println("成绩单保存成功！");
                System.out.println("======================================");
                
                // 显示保存后的成绩单信息
                System.out.println("成绩单ID: " + reportCard.getId());
                System.out.println("学生: " + student.getName());
                System.out.println("平均分: " + reportCard.getAverageScore());
                System.out.println("科目数量: " + reportCard.getSubjectCount());
                
                // 归档成绩单
                System.out.print("是否立即归档此成绩单？(yes/no): ");
                String archiveChoice = scanner.nextLine().trim();
                if (archiveChoice.equalsIgnoreCase("yes") || archiveChoice.equalsIgnoreCase("y")) {
                    if (reportSystem.archiveReportCard(reportCard.getId())) {
                        System.out.println("成绩单已归档");
                    }
                }
                
                // 系统将用户返回到班级学生视图页面（事件序列：系统7）
                System.out.println("\n正在返回班级学生视图...");
                if (reportSystem != null) {
                    ClassRoom classRoom = reportSystem.getClassById(student.getClassId());
                    if (classRoom != null) {
                        showClassStudents(classRoom);
                    }
                }
            } else {
                System.err.println("错误：保存成绩单失败");
                System.out.println("后置条件：与SMOS服务器的连接可能已中断");
            }
        } catch (Exception e) {
            System.err.println("保存过程中发生错误: " + e.getMessage());
            System.out.println("后置条件：操作可能已被用户中断或连接中断");
        }
    }
    
    /**
     * 查看成绩单流程
     */
    private void viewReportCardWorkflow() {
        if (!checkLoginStatus() || !checkReportSystem()) {
            return;
        }
        
        System.out.println("\n=== 查看成绩单 ===");
        System.out.println("1. 按学生ID查看");
        System.out.println("2. 按班级查看所有成绩单");
        System.out.println("3. 返回主菜单");
        System.out.print("请选择: ");
        
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                viewReportCardByStudentId();
                break;
            case "2":
                viewReportCardsByClass();
                break;
            case "3":
                return;
            default:
                System.err.println("错误：无效的选择");
        }
    }
    
    /**
     * 按学生ID查看成绩单
     */
    private void viewReportCardByStudentId() {
        System.out.print("请输入学生ID: ");
        try {
            int studentId = Integer.parseInt(scanner.nextLine());
            
            if (reportSystem != null) {
                List<ReportCard> reportCards = reportSystem.getReportCardsByStudentId(studentId);
                
                if (reportCards.isEmpty()) {
                    System.out.println("未找到该学生的成绩单");
                } else {
                    System.out.println("\n=== 学生成绩单列表 ===");
                    for (int i = 0; i < reportCards.size(); i++) {
                        ReportCard rc = reportCards.get(i);
                        System.out.printf("%d. %s%n", i + 1, rc.getSimpleInfo());
                    }
                    
                    System.out.print("\n输入编号查看详情，输入0返回: ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    
                    if (choice > 0 && choice <= reportCards.size()) {
                        reportCards.get(choice - 1).displayReportCard();
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("错误：请输入有效的数字");
        }
    }
    
    /**
     * 按班级查看所有成绩单
     */
    private void viewReportCardsByClass() {
        System.out.println("\n=== 选择班级 ===");
        List<ClassRoom> classes = reportSystem.getAllClasses();
        
        for (int i = 0; i < classes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, classes.get(i).getClassName());
        }
        
        System.out.print("请选择班级编号: ");
        try {
            int classIndex = Integer.parseInt(scanner.nextLine());
            
            if (classIndex >= 1 && classIndex <= classes.size())