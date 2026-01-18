```
/**
 * ReportSystem.java
 * 学生成绩单系统的核心业务逻辑类
 * 整合所有组件：班级管理、学生管理、成绩单管理、服务器连接
 * 实现完整的学生成绩单插入流程
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ReportSystem {
    private static int nextReportCardId = 1001;      // 成绩单ID生成器
    private static int nextStudentId = 2001;         // 学生ID生成器
    private static int nextClassId = 3001;           // 班级ID生成器
    
    private String currentAcademicYear;              // 当前学年
    private List<ClassRoom> classRooms;              // 所有班级列表
    private List<Student> allStudents;               // 所有学生列表（冗余存储，便于查询）
    private Map<Integer, ReportCard> reportCards;    // 所有成绩单（按ID存储）
    private SMOSServer smosServer;                   // SMOS服务器连接
    private Administrator currentAdmin;              // 当前管理员
    private Random random;                           // 随机数生成器
    
    /**
     * 默认构造函数
     * 初始化系统数据和组件
     */
    public ReportSystem() {
        this.currentAcademicYear = "2023-2024"; // 当前学年
        this.classRooms = new ArrayList<>();
        this.allStudents = new ArrayList<>();
        this.reportCards = new HashMap<>();
        this.smosServer = new SMOSServer();
        this.currentAdmin = null;
        this.random = new Random();
        
        // 初始化演示数据
        initializeDemoData();
        
        System.out.println("学生成绩单系统初始化完成");
        System.out.println("当前学年: " + currentAcademicYear);
    }
    
    /**
     * 初始化演示数据
     * 创建一些班级、学生和成绩单用于演示系统功能
     */
    private void initializeDemoData() {
        // 创建班级
        ClassRoom class1 = new ClassRoom(nextClassId++, "一年级一班", currentAcademicYear, "张老师", 30);
        ClassRoom class2 = new ClassRoom(nextClassId++, "一年级二班", currentAcademicYear, "李老师", 30);
        ClassRoom class3 = new ClassRoom(nextClassId++, "二年级一班", currentAcademicYear, "王老师", 30);
        
        // 添加班级到系统
        classRooms.add(class1);
        classRooms.add(class2);
        classRooms.add(class3);
        
        // 创建一些学生并分配到班级
        Student[] students1 = {
            new Student(nextStudentId++, "张三", class1.getId(), 7),
            new Student(nextStudentId++, "李四", class1.getId(), 7),
            new Student(nextStudentId++, "王五", class1.getId(), 8)
        };
        
        Student[] students2 = {
            new Student(nextStudentId++, "赵六", class2.getId(), 7),
            new Student(nextStudentId++, "钱七", class2.getId(), 7),
            new Student(nextStudentId++, "孙八", class2.getId(), 8)
        };
        
        Student[] students3 = {
            new Student(nextStudentId++, "周九", class3.getId(), 8),
            new Student(nextStudentId++, "吴十", class3.getId(), 8),
            new Student(nextStudentId++, "郑十一", class3.getId(), 9)
        };
        
        // 将学生添加到班级中
        for (Student student : students1) {
            class1.addStudent(student);
            allStudents.add(student);
        }
        
        for (Student student : students2) {
            class2.addStudent(student);
            allStudents.add(student);
        }
        
        for (Student student : students3) {
            class3.addStudent(student);
            allStudents.add(student);
        }
        
        // 创建一些示例成绩单
        createDemoReportCards();
        
        System.out.println("演示数据初始化完成: " + 
                          classRooms.size() + "个班级, " + 
                          allStudents.size() + "名学生, " + 
                          reportCards.size() + "份成绩单");
    }
    
    /**
     * 创建演示用的成绩单
     */
    private void createDemoReportCards() {
        // 为每个班级的第一名学生创建一个成绩单
        if (!classRooms.isEmpty()) {
            for (ClassRoom classRoom : classRooms) {
                List<Student> students = classRoom.getStudents();
                if (!students.isEmpty()) {
                    Student student = students.get(0);
                    ReportCard reportCard = createReportCardForStudent(student);
                    if (reportCard != null) {
                        reportCards.put(reportCard.getId(), reportCard);
                    }
                }
            }
        }
    }
    
    /**
     * 为指定学生创建示例成绩单
     * @param student 学生对象
     * @return 创建的成绩单
     */
    private ReportCard createReportCardForStudent(Student student) {
        if (student == null) {
            return null;
        }
        
        ReportCard reportCard = new ReportCard(nextReportCardId++, 
                                              student.getId(), 
                                              student.getName(), 
                                              currentAcademicYear);
        reportCard.setSemester("2023-2024学年第二学期");
        
        // 添加示例科目成绩
        String[] subjects = {"语文", "数学", "英语", "科学", "体育"};
        for (String subject : subjects) {
            double score = 60 + random.nextInt(40); // 60-100之间的随机分数
            reportCard.addGrade(subject, score);
        }
        
        reportCard.setTeacherComment(student.getName() + "同学本学期表现良好，继续保持！");
        reportCard.setStatus("已归档");
        reportCard.setArchived(true);
        
        return reportCard;
    }
    
    /**
     * 获取系统中所有班级
     * @return 班级列表
     */
    public List<ClassRoom> getAllClasses() {
        return new ArrayList<>(classRooms);
    }
    
    /**
     * 获取当前学年的班级列表
     * @return 当前学年的班级列表
     */
    public List<ClassRoom> getClassesForCurrentAcademicYear() {
        List<ClassRoom> result = new ArrayList<>();
        for (ClassRoom classRoom : classRooms) {
            if (classRoom.isCurrentAcademicYear(currentAcademicYear)) {
                result.add(classRoom);
            }
        }
        return result;
    }
    
    /**
     * 根据班级ID获取班级对象
     * @param classId 班级ID
     * @return 班级对象，如果未找到则返回null
     */
    public ClassRoom getClassById(int classId) {
        for (ClassRoom classRoom : classRooms) {
            if (classRoom.getId() == classId) {
                return classRoom;
            }
        }
        return null;
    }
    
    /**
     * 创建新班级
     * @param className 班级名称
     * @param teacherName 班主任姓名
     * @param maxStudents 最大学生数
     * @return 创建的班级对象，如果创建失败则返回null
     */
    public ClassRoom createNewClass(String className, String teacherName, int maxStudents) {
        if (className == null || className.trim().isEmpty()) {
            System.err.println("错误：班级名称不能为空");
            return null;
        }
        
        ClassRoom newClass = new ClassRoom(nextClassId++, className.trim(), 
                                          currentAcademicYear, teacherName, maxStudents);
        classRooms.add(newClass);
        
        System.out.println("新班级创建成功: " + newClass.getSimpleInfo());
        return newClass;
    }
    
    /**
     * 根据学生ID获取学生对象
     * @param studentId 学生ID
     * @return 学生对象，如果未找到则返回null
     */
    public Student getStudentById(int studentId) {
        for (Student student : allStudents) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }
    
    /**
     * 创建新学生
     * @param name 学生姓名
     * @param classId 班级ID
     * @param age 年龄
     * @return 创建的学生对象，如果创建失败则返回null
     */
    public Student createNewStudent(String name, int classId, int age) {
        ClassRoom classRoom = getClassById(classId);
        if (classRoom == null) {
            System.err.println("错误：指定的班级不存在 (ID: " + classId + ")");
            return null;
        }
        
        if (classRoom.isFull()) {
            System.err.println("错误：班级已满，无法添加新学生");
            return null;
        }
        
        Student newStudent = new Student(nextStudentId++, name, classId, age);
        
        if (classRoom.addStudent(newStudent)) {
            allStudents.add(newStudent);
            System.out.println("新学生创建成功: " + newStudent.getSimpleInfo());
            return newStudent;
        } else {
            System.err.println("创建学生失败");
            return null;
        }
    }
    
    /**
     * 保存成绩单到系统
     * @param reportCard 要保存的成绩单
     * @return 如果保存成功返回true，否则返回false
     */
    public boolean saveReportCard(ReportCard reportCard) {
        if (reportCard == null) {
            System.err.println("错误：无法保存空成绩单");
            return false;
        }
        
        // 检查学生是否存在
        Student student = getStudentById(reportCard.getStudentId());
        if (student == null) {
            System.err.println("错误：学生不存在 (ID: " + reportCard.getStudentId() + ")");
            return false;
        }
        
        // 检查成绩单是否有效
        if (!reportCard.isValid()) {
            System.err.println("错误：成绩单信息不完整");
            return false;
        }
        
        // 如果成绩单没有ID，则分配一个新ID
        if (reportCard.getId() <= 0) {
            reportCard.setId(nextReportCardId++);
        }
        
        // 设置状态为已提交
        if (!reportCard.submit()) {
            System.err.println("错误：无法提交成绩单");
            return false;
        }
        
        // 保存到内存
        reportCards.put(reportCard.getId(), reportCard);
        
        System.out.println("成绩单保存成功: " + reportCard.getSimpleInfo());
        
        // 尝试同步到SMOS服务器（模拟异步操作）
        syncReportCardToSMOS(reportCard);
        
        return true;
    }
    
    /**
     * 同步成绩单到SMOS服务器
     * @param reportCard 要同步的成绩单
     */
    private void syncReportCardToSMOS(ReportCard reportCard) {
        new Thread(() -> {
            System.out.println("开始同步成绩单到SMOS服务器...");
            
            // 确保服务器连接
            if (!smosServer.checkConnection()) {
                System.out.println("尝试连接到SMOS服务器...");
                if (!smosServer.connect()) {
                    System.err.println("错误：无法连接到SMOS服务器，成绩单将在本地保存");
                    return;
                }
            }
            
            // 同步数据
            if (smosServer.syncData(reportCard)) {
                System.out.println("成绩单同步到SMOS服务器成功");
            } else {
                System.err.println("警告：成绩单同步到SMOS服务器失败");
                System.err.println("后置条件：与SMOS服务器的连接可能已中断");
                
                // 模拟连接中断
                smosServer.simulateConnectionInterruption();
            }
        }).start();
    }
    
    /**
     * 归档成绩单
     * @param reportCardId 成绩单ID
     * @return 如果归档成功返回true，否则返回false
     */
    public boolean archiveReportCard(int reportCardId) {
        ReportCard reportCard = reportCards.get(reportCardId);
        if (reportCard == null) {
            System.err.println("错误：未找到成绩单 (ID: " + reportCardId + ")");
            return false;
        }
        
        if (reportCard.isArchived()) {
            System.out.println("成绩单已归档，无需重复操作");
            return true;
        }
        
        // 确保服务器连接
        if (!smosServer.checkConnection()) {
            System.out.println("尝试连接到SMOS服务器...");
            if (!smosServer.connect()) {
                System.err.println("错误：无法连接到SMOS服务器，归档操作失败");
                return false;
            }
        }
        
        // 归档到SMOS服务器
        if (smosServer.archiveReportCard(reportCard)) {
            System.out.println("成绩单归档成功: " + reportCardId);
            return true;
        } else {
            System.err.println("错误：成绩单归档失败");
            System.err.println("后置条件：与SMOS服务器的连接可能已中断");
            return false;
        }
    }
    
    /**
     * 根据学生ID获取所有成绩单
     * @param studentId 学生ID
     * @return 该学生的成绩单列表
     */
    public List<ReportCard> getReportCardsByStudentId(int studentId) {
        List<ReportCard> result = new ArrayList<>();
        
        for (ReportCard reportCard : reportCards.values()) {
            if (reportCard.getStudentId() == studentId) {
                result.add(reportCard);
            }
        }
        
        return result;
    }
    
    /**
     * 根据班级ID获取该班级所有学生的成绩单
     * @param classId 班级ID
     * @return 班级成绩单列表
     */
    public List<ReportCard> getReportCardsByClassId(int classId) {
        List<ReportCard> result = new ArrayList<>();
        
        // 先获取该班级的所有学生
        ClassRoom classRoom = getClassById(classId);
        if (classRoom == null) {
            return result;
        }
        
        List<Integer> studentIds = classRoom.getAllStudentIds();
        
        // 查找这些学生的成绩单
        for (ReportCard reportCard : reportCards.values()) {
            if (studentIds.contains(reportCard.getStudentId())) {
                result.add(reportCard);
            }
        }
        
        return result;
    }
    
    /**
     * 获取指定成绩单
     * @param reportCardId 成绩单ID
     * @return 成绩单对象，如果未找到则返回null
     */
    public ReportCard getReportCard(int reportCardId) {
        return reportCards.get(reportCardId);
    }
    
    /**
     * 获取所有成绩单数量
     * @return 成绩单数量
     */
    public int getReportCardCount() {
        return reportCards.size();
    }
    
    /**
     * 获取当前学年
     * @return 当前学年
     */
    public String getCurrentAcademicYear() {
        return currentAcademicYear;
    }
    
    /**
     * 设置当前学年
     * @param academicYear 学年
     */
    public void setCurrentAcademicYear(String academicYear) {
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("学年不能为空");
        }
        this.currentAcademicYear = academicYear.trim();
        System.out.println("当前学年已更新为: " + currentAcademicYear);
    }
    
    /**
     * 设置当前管理员
     * @param admin 管理员对象
     */
    public void setCurrentAdmin(Administrator admin) {
        this.currentAdmin = admin;
        if (admin != null) {
            admin.setReportSystem(this);
        }
    }
    
    /**
     * 获取当前管理员
     * @return 当前管理员对象
     */
    public Administrator getCurrentAdmin() {
        return currentAdmin;
    }
    
    /**
     * 获取SMOS服务器连接
     * @return SMOS服务器对象
     */
    public SMOSServer getSMOSServer() {
        return smosServer;
    }
    
    /**
     * 显示系统统计信息
     */
    public void displaySystemStats() {
        System.out.println("\n=== 系统统计信息 ===");
        System.out.println("当前学年: " + currentAcademicYear);
        System.out.println("班级数量: " + classRooms.size());
        System.out.println("学生总数: " + allStudents.size());
        System.out.println("成绩单总数: " + reportCards.size());
        System.out.println("已归档成绩单: " + smosServer.getArchivedReportCount());
        System.out.println("SMOS服务器状态: " + 
                          (smosServer.checkConnection() ? "已连接" : "未连接"));
        
        // 显示每个班级的信息
        System.out.println("\n班级详情:");
        for (ClassRoom classRoom : classRooms) {
            System.out.println("  " + classRoom.getSimpleInfo());
        }
    }
    
    /**
     * 处理用户中断操作
     * 模拟用户在中途取消操作的情况
     */
    public void handleUserInterruption() {
        System.out.println("\n=== 用户中断操作 ===");
        System.out.println("用户已取消当前操作");
        System.out.println("系统状态已保存");
        System.out.println("返回到主菜单...");
    }
    
    /**
     * 处理服务器连接中断
     * @param reportCard 正在处理的成绩单（可能为null）
     */
    public void handleServerConnectionInterruption(ReportCard reportCard) {
        System.out.println("\n=== SMOS服务器连接中断 ===");
        System.out.println("警告：与SMOS服务器的连接已中断");
        
        if (reportCard != null) {
            System.out.println("正在处理的成绩单: " + reportCard.getSimpleInfo());
            System.out.println("成绩单已保存到本地，将在连接恢复后自动同步");
            
            // 标记为等待同步状态
            reportCard.setStatus("等待同步");
        }
        
        System.out.println("系统将继续在本地运行");
        System.out.println("请稍后检查服务器连接状态");
    }
    
    /**
     * 模拟完整的学生成绩单插入流程
     * 按照用例的事件序列执行
     */
    public void simulateInsertPageWorkflow() {
        System.out.println("\n========================================");
        System.out.println("开始模拟成绩单插入流程...");
        System.out.println("用例：InsertPage");
        System.out.println("参与者：管理员");
        System.out.println("========================================\n");
        
        // 前置条件：用户已登录为管理员
        System.out.println("[前置条件检查]");
        System.out.println("1. 用户已以管理员身份登录系统 - ✓");
        System.out.println("2. 用户点击\"在线报告\"按钮 - ✓");
        System.out.println();
        
        // 事件序列：系统1 - 显示班级列表
        System.out.println("[事件