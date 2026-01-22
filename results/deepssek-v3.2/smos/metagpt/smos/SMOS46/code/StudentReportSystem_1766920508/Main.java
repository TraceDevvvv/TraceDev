     Main.java ，        ，               。         、    Java  ，           。

```java
/**
 * Main.java
 *           
 *               ，       、      、    、      
 *             
 */
import java.util.Scanner;

public class Main {
    /**
     *     -      
     * @param args      
     */
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("             v1.0");
        System.out.println("========================================");
        
        //       
        ReportSystem reportSystem = new ReportSystem();
        
        //        
        Administrator admin = new Administrator();
        
        //         
        reportSystem.setCurrentAdmin(admin);
        
        //            
        Scanner scanner = new Scanner(System.in);
        
        //      
        boolean running = true;
        while (running) {
            System.out.println("\n===     ===");
            System.out.println("1.      ");
            System.out.println("2.            ");
            System.out.println("3.       ");
            System.out.println("4.   SMOS     ");
            System.out.println("5.          ");
            System.out.println("6.     ");
            System.out.print("      (1-6): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    //      
                    handleAdminLogin(admin, scanner);
                    break;
                case "2":
                    //            
                    demonstrateInsertPageWorkflow(reportSystem, admin, scanner);
                    break;
                case "3":
                    //       
                    reportSystem.displaySystemStats();
                    break;
                case "4":
                    //   SMOS     
                    testSMOSServerConnection(reportSystem);
                    break;
                case "5":
                    //          
                    displayAllClassesAndStudents(reportSystem);
                    break;
                case "6":
                    //     
                    System.out.println("             ，  ！");
                    running = false;
                    break;
                default:
                    System.err.println("  ：     ，     ");
            }
        }
        
        scanner.close();
    }
    
    /**
     *        
     * @param admin      
     * @param scanner      
     */
    private static void handleAdminLogin(Administrator admin, Scanner scanner) {
        System.out.println("\n===       ===");
        
        if (admin.isLoggedIn()) {
            System.out.println("      ，      ");
            return;
        }
        
        System.out.print("       (  : admin): ");
        String username = scanner.nextLine();
        if (username.trim().isEmpty()) {
            username = "admin";
        }
        
        System.out.print("      (  : admin123): ");
        String password = scanner.nextLine();
        if (password.trim().isEmpty()) {
            password = "admin123";
        }
        
        boolean loginSuccess = admin.login(username, password);
        if (loginSuccess) {
            System.out.println("    ！");
        } else {
            System.out.println("    ，         ");
        }
    }
    
    /**
     *             
     *            
     * @param reportSystem       
     * @param admin      
     * @param scanner      
     */
    private static void demonstrateInsertPageWorkflow(ReportSystem reportSystem, Administrator admin, Scanner scanner) {
        System.out.println("\n========================================");
        System.out.println("           ...");
        System.out.println("  ：InsertPage");
        System.out.println("   ：   ");
        System.out.println("========================================\n");
        
        //       
        System.out.println("[      ]");
        System.out.println("1.              ");
        
        if (!admin.isLoggedIn()) {
            System.out.println("   ❌       ，        ...");
            boolean autoLogin = admin.login("admin", "admin123");
            if (!autoLogin) {
                System.err.println("   ❌       ，         ");
                return;
            }
            System.out.println("   ✓       ");
        } else {
            System.out.println("   ✓       ");
        }
        
        System.out.println("2.     \"    \"  ");
        System.out.println("   ✓         ");
        System.out.println();
        
        //     ：  1 -       
        System.out.println("[    ：  1]");
        System.out.println("             ，         \"   \"  ");
        System.out.println();
        
        //            
        java.util.List<ClassRoom> classes = reportSystem.getClassesForCurrentAcademicYear();
        if (classes.isEmpty()) {
            System.err.println("  ：         ");
            return;
        }
        
        System.out.println("===          ===");
        System.out.println("  : " + reportSystem.getCurrentAcademicYear());
        System.out.println("         \"   \"  ");
        System.out.println("------------------------");
        
        for (int i = 0; i < classes.size(); i++) {
            ClassRoom classRoom = classes.get(i);
            System.out.printf("%d. %-20s [     ]%n", 
                    i + 1, classRoom.getSimpleInfo());
        }
        System.out.println();
        
        //     ：  2 -     
        System.out.println("[    ：  2]");
        System.out.println("               ");
        System.out.print("            (1-" + classes.size() + "): ");
        
        int classChoice = 0;
        try {
            classChoice = Integer.parseInt(scanner.nextLine());
            if (classChoice < 1 || classChoice > classes.size()) {
                System.err.println("  ：       ，    ");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("  ：        ，    ");
            return;
        }
        
        ClassRoom selectedClass = classes.get(classChoice - 1);
        System.out.println("     : " + selectedClass.getClassName());
        System.out.println();
        
        //     ：  3 -         
        System.out.println("[    ：  3]");
        System.out.println("               ");
        System.out.println();
        
        System.out.println("===        ===");
        System.out.println("  : " + selectedClass.getClassName());
        System.out.println("------------------------");
        
        java.util.List<Student> students = selectedClass.getStudents();
        if (students.isEmpty()) {
            System.err.println("  ：       ，    ");
            return;
        }
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%d. %s%n", i + 1, student.getSimpleInfo());
        }
        System.out.println();
        
        //     ：  4 -     
        System.out.println("[    ：  4]");
        System.out.println("                    ");
        System.out.print("            (1-" + students.size() + "): ");
        
        int studentChoice = 0;
        try {
            studentChoice = Integer.parseInt(scanner.nextLine());
            if (studentChoice < 1 || studentChoice > students.size()) {
                System.err.println("  ：       ，    ");
                return;
            }
        } catch (NumberFormatException e) {
            System.err.println("  ：        ，    ");
            return;
        }
        
        Student selectedStudent = students.get(studentChoice - 1);
        System.out.println("     : " + selectedStudent.getName() + " (ID: " + selectedStudent.getId() + ")");
        System.out.println();
        
        //     ：  5 -           
        System.out.println("[    ：  5]");
        System.out.println("            ");
        System.out.println();
        
        System.out.println("===         ===");
        System.out.println("  : " + selectedStudent.getName());
        System.out.println("  : " + selectedClass.getClassName());
        System.out.println("  : " + reportSystem.getCurrentAcademicYear());
        System.out.println("------------------------");
        
        //        
        ReportCard newReportCard = new ReportCard();
        newReportCard.setStudentId(selectedStudent.getId());
        newReportCard.setStudentName(selectedStudent.getName());
        newReportCard.setAcademicYear(reportSystem.getCurrentAcademicYear());
        newReportCard.setSemester("2023-2024      ");
        
        //     ：  6 -        
        System.out.println("[    ：  6]");
        System.out.println("          （     ）");
        System.out.println("       （   'done'   ，   'cancel'   ）");
        System.out.println("    : 0-100");
        System.out.println();
        
        boolean addingGrades = true;
        while (addingGrades) {
            System.out.print("       : ");
            String subject = scanner.nextLine().trim();
            
            if (subject.equalsIgnoreCase("done")) {
                if (newReportCard.getSubjectCount() == 0) {
                    System.out.println("  ：          ，         'cancel'   ");
                    continue;
                }
                addingGrades = false;
                break;
            } else if (subject.equalsIgnoreCase("cancel")) {
                System.out.println("     ");
                return;
            } else if (subject.isEmpty()) {
                System.err.println("  ：        ");
                continue;
            }
            
            System.out.print("    " + subject + "       (0-100): ");
            String scoreInput = scanner.nextLine().trim();
            
            try {
                double score = Double.parseDouble(scoreInput);
                if (score < 0 || score > 100) {
                    System.err.println("  ：     0-100  ");
                    continue;
                }
                
                if (newReportCard.addGrade(subject, score)) {
                    System.out.println("✓      : " + subject + "，  : " + score);
                    System.out.println("      " + newReportCard.getSubjectCount() + "    ");
                } else {
                    System.err.println("  ：      ");
                }
            } catch (NumberFormatException e) {
                System.err.println("  ：        ");
            }
            
            System.out.println();
        }
        
        //       
        System.out.print("        (  ，      ): ");
        String comment = scanner.nextLine().trim();
        if (!comment.isEmpty()) {
            newReportCard.setTeacherComment(comment);
        }
        
        //        
        System.out.println("\n===       ===");
        newReportCard.displayReportCard();
        
        //     
        System.out.print("\n        ？(yes/no): ");
        String confirm = scanner.nextLine().trim();
        
        if (!confirm.equalsIgnoreCase("yes") && !confirm.equalsIgnoreCase("y")) {
            System.out.println("     ");
            
            //     ：      
            System.out.println("\n[    ：      ]");
            System.out.println("            ");
            reportSystem.handleUserInterruption();
            return;
        }
        
        //      
        System.out.println("\n       ...");
        
        //     ：  7 -         
        System.out.println("[    ：  7]");
        System.out.println("            ，               ");
        System.out.println();
        
        boolean saveSuccess = reportSystem.saveReportCard(newReportCard);
        
        if (saveSuccess) {
            System.out.println("✓        ！");
            System.out.println("   ID: " + newReportCard.getId());
            System.out.println("  : " + selectedStudent.getName());
            System.out.println("   : " + newReportCard.getAverageScore());
            System.out.println("    : " + newReportCard.getSubjectCount());
            
            //     ：           
            System.out.println("\n[    ：           ]");
            System.out.println("✓               ");
            
            //       
            System.out.print("\n           SMOS   ？(yes/no): ");
            String archiveChoice = scanner.nextLine().trim();
            if (archiveChoice.equalsIgnoreCase("yes") || archiveChoice.equalsIgnoreCase("y")) {
                System.out.println("       ...");
                boolean archiveSuccess = reportSystem.archiveReportCard(newReportCard.getId());
                
                if (!archiveSuccess) {
                    //     ： SMOS        
                    System.out.println("\n[    ： SMOS        ]");
                    System.out.println("⚠     ： SMOS           ");
                    reportSystem.handleServerConnectionInterruption(newReportCard);
                } else {
                    System.out.println("✓        ");
                }
            }
            
            //         
            System.out.println("\n            ...");
            System.out.println("\n===        ===");
            System.out.println("  : " + selectedClass.getClassName());
            selectedClass.displayAllStudents();
            
        } else {
            System.err.println("❌        ");
            
            //     ： SMOS        
            System.out.println("\n[    ： SMOS        ]");
            System.out.println("⚠     ： SMOS           ");
            reportSystem.handleServerConnectionInterruption(newReportCard);
        }
        
        System.out.println("\n========================================");
        System.out.println("           ");
        System.out.println("========================================");
    }
    
    /**
     *   SMOS     
     * @param reportSystem       
     */
    private static void testSMOSServerConnection(ReportSystem reportSystem) {
        System.out.println("\n===   SMOS      ===");
        
        SMOSServer server = reportSystem.getSMOSServer();
        if (server == null) {
            System.err.println("  ：SMOS       ");
            return;
        }
        
        server.displayServerStatus();
        
        System.out.print("      ？(yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim();
        
        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
            boolean success = server.testConnection();
            if (success) {
                System.out.println("✓ SMOS         ");
            } else {
                System.out.println("❌ SMOS         ");
            }
        }
    }
    
    /**
     *          
     * @param reportSystem       
     */
    private static void displayAllClassesAndStudents(ReportSystem reportSystem) {
        System.out.println("\n===           ===");
        
        java.util.List<ClassRoom> classes = reportSystem.getAllClasses();
        if (classes.isEmpty()) {
            System.out.println("         ");
            return;
        }
        
        System.out.println("      " + classes.size() + "    :");
        System.out.println();
        
        for (ClassRoom classRoom : classes) {
            classRoom.displayClassInfo();
            System.out.println();
            
            java.util.List<Student> students = classRoom.getStudents();
            if (!students.isEmpty()) {
                System.out.println("      :");
                for (int i = 0; i < students.size(); i++) {
                    Student student = students.get(i);
                    System.out.printf("  %d. %s%n", i + 1, student.getSimpleInfo());
                    
                    //          
                    java.util.List<ReportCard> reportCards = reportSystem.getReportCardsByStudentId(student.getId());
                    if (!reportCards.isEmpty()) {
                        System.out.println("       :");
                        for (ReportCard reportCard : reportCards) {
                            System.out.println("      - " + reportCard.getSimpleInfo());
                        }
                    }
                }
            } else {
                System.out.println("         ");
            }
            
            System.out.println("------------------------");
        }
    }
    
    /**
     *          
     *         ，           
     */
    private static void demonstrateSystemFeatures() {
        System.out.println("\n===        ===");
        
        //       
        ReportSystem system = new ReportSystem();
        
        //       
        system.displaySystemStats();
        
        //   SMOS   
        SMOSServer server = system.getSMOSServer();
        if (server != null) {
            System.out.println("\n  SMOS     ...");
            boolean connected = server.connect();
            System.out.println("    : " + (connected ? "  " : "  "));
            
            if (connected) {
                System.out.println("     :");
                server.displayServerStatus();
            }
        }
        
        //      
        System.out.println("\n     ...");
        ClassRoom newClass = system.createNewClass("     ", "   ", 35);
        if (newClass != null) {
            System.out.println("       : " + newClass.getClassName());
        }
        
        //      
        System.out.println("\n     ...");
        if (newClass != null) {
            Student newStudent = system.createNewStudent("    ", newClass.getId(), 9);
           