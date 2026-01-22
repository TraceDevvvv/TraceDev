    Administrator.java 。                 ，    、      、    、        。

```
/**
 * Administrator.java
 *     ，             
 *     、      、    、    、        
 */
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Administrator {
    private int adminId;                     //    ID
    private String adminName;                //      
    private String username;                 //      
    private String password;                 //     （          ）
    private boolean isLoggedIn;              //     
    private Date lastLoginTime;              //       
    private ReportSystem reportSystem;       //       
    private Scanner scanner;                 //        
    
    /**
     *       
     */
    public Administrator() {
        this.adminId = 1;
        this.adminName = "     ";
        this.username = "admin";
        this.password = "admin123"; //     ，          
        this.isLoggedIn = false;
        this.lastLoginTime = null;
        this.reportSystem = null;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     *         
     * @param adminId    ID
     * @param adminName      
     * @param username    
     * @param password   
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
     *         
     * @param reportSystem       
     */
    public void setReportSystem(ReportSystem reportSystem) {
        if (reportSystem == null) {
            throw new IllegalArgumentException("        ");
        }
        this.reportSystem = reportSystem;
    }
    
    // Getter   
    public int getAdminId() { return adminId; }
    public String getAdminName() { return adminName; }
    public String getUsername() { return username; }
    public boolean isLoggedIn() { return isLoggedIn; }
    public Date getLastLoginTime() { return lastLoginTime; }
    
    /**
     *      
     * @param inputUsername       
     * @param inputPassword      
     * @return         true，    false
     */
    public boolean login(String inputUsername, String inputPassword) {
        if (inputUsername == null || inputPassword == null) {
            System.err.println("  ：          ");
            return false;
        }
        
        if (inputUsername.equals(username) && inputPassword.equals(password)) {
            isLoggedIn = true;
            lastLoginTime = new Date();
            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("======================================");
            System.out.println("    " + adminName + "     ！");
            System.out.println("    : " + sdf.format(lastLoginTime));
            System.out.println("======================================");
            return true;
        } else {
            System.err.println("  ：         ");
            return false;
        }
    }
    
    /**
     *      
     * @return         true，    false
     */
    public boolean interactiveLogin() {
        if (isLoggedIn) {
            System.out.println("      ，      ");
            return true;
        }
        
        System.out.println("\n===       ===");
        System.out.print("      : ");
        String inputUsername = scanner.nextLine();
        
        System.out.print("     : ");
        String inputPassword = scanner.nextLine();
        
        return login(inputUsername, inputPassword);
    }
    
    /**
     *      
     */
    public void logout() {
        if (isLoggedIn) {
            isLoggedIn = false;
            System.out.println("    " + adminName + "    ");
        } else {
            System.out.println("     ");
        }
    }
    
    /**
     *       
     * @return        true，    false
     */
    private boolean checkLoginStatus() {
        if (!isLoggedIn) {
            System.err.println("  ：         ");
            System.out.println("          ...");
            return login("admin", "admin123");
        }
        return true;
    }
    
    /**
     *           
     * @return           true，    false
     */
    private boolean checkReportSystem() {
        if (reportSystem == null) {
            System.err.println("  ：        ");
            return false;
        }
        return true;
    }
    
    /**
     *      
     */
    public void showMainMenu() {
        if (!checkLoginStatus()) {
            return;
        }
        
        while (true) {
            System.out.println("\n===           ===");
            System.out.println("    : " + adminName + " (   )");
            System.out.println("1.       ");
            System.out.println("2.        ");
            System.out.println("3.        ");
            System.out.println("4.       ");
            System.out.println("5.     ");
            System.out.print("      (1-5): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    //     ：     "    "  
                    showAllClasses();
                    break;
                case "2":
                    //        
                    insertReportCardWorkflow();
                    break;
                case "3":
                    //      
                    viewReportCardWorkflow();
                    break;
                case "4":
                    //       
                    showSystemStatus();
                    break;
                case "5":
                    System.out.println("    ，  ！");
                    logout();
                    return;
                default:
                    System.err.println("  ：     ，     ");
            }
        }
    }
    
    /**
     *          （    ：  1）
     */
    public void showAllClasses() {
        if (!checkLoginStatus() || !checkReportSystem()) {
            return;
        }
        
        System.out.println("\n===          ===");
        System.out.println("  ：         \"   \"  ");
        System.out.println("========================\n");
        
        List<ClassRoom> classes = reportSystem.getAllClasses();
        if (classes.isEmpty()) {
            System.out.println("         ");
            return;
        }
        
        for (int i = 0; i < classes.size(); i++) {
            ClassRoom classRoom = classes.get(i);
            System.out.printf("%d. %-30s [     ]%n", 
                    i + 1, classRoom.getSimpleInfo());
        }
        
        System.out.println("\n            ，  0     ");
        System.out.print("   : ");
        
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                return;
            } else if (choice >= 1 && choice <= classes.size()) {
                showClassDetails(classes.get(choice - 1));
            } else {
                System.err.println("  ：       ");
            }
        } catch (NumberFormatException e) {
            System.err.println("  ：        ");
        }
    }
    
    /**
     *         （    ：  2）
     * @param classRoom     
     */
    private void showClassDetails(ClassRoom classRoom) {
        if (classRoom == null) {
            System.err.println("  ：      ");
            return;
        }
        
        classRoom.displayClassInfo();
        System.out.println();
        
        //         （    ：  3）
        showClassStudents(classRoom);
    }
    
    /**
     *         （    ：  3）
     * @param classRoom     
     */
    private void showClassStudents(ClassRoom classRoom) {
        System.out.println("\n===        ===");
        classRoom.displayAllStudents();
        
        System.out.println("\n  :");
        System.out.println("1.          ");
        System.out.println("2.       ");
        System.out.println("3.      ");
        System.out.print("   : ");
        
        String choice = scanner.nextLine();
        switch (choice) {
            case "1":
                selectStudentForReportCard(classRoom);
                break;
            case "2":
                showAllClasses();
                break;
            case "3":
                //        
                break;
            default:
                System.err.println("  ：     ");
        }
    }
    
    /**
     *          （    ：  4）
     * @param classRoom     
     */
    private void selectStudentForReportCard(ClassRoom classRoom) {
        List<Student> students = classRoom.getStudents();
        if (students.isEmpty()) {
            System.out.println("       ，       ");
            return;
        }
        
        System.out.println("\n===           ===");
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf("%d. %s%n", i + 1, student.getSimpleInfo());
        }
        
        System.out.print("        (  0  ): ");
        
        try {
            int studentIndex = Integer.parseInt(scanner.nextLine());
            if (studentIndex == 0) {
                System.out.println("     ");
                return;
            }
            
            if (studentIndex >= 1 && studentIndex <= students.size()) {
                Student selectedStudent = students.get(studentIndex - 1);
                System.out.println("     : " + selectedStudent.getName());
                
                //           （    ：  5）
                showReportCardForm(selectedStudent);
            } else {
                System.err.println("  ：       ");
            }
        } catch (NumberFormatException e) {
            System.err.println("  ：        ");
        }
    }
    
    /**
     *           （    ：  5）
     * @param student      
     */
    private void showReportCardForm(Student student) {
        System.out.println("\n===         ===");
        System.out.println("  : " + student.getName() + " (ID: " + student.getId() + ")");
        System.out.println("  : " + student.getClassId());
        System.out.println("         (    : 0-100)");
        System.out.println("   'done'     ，   'cancel'     ");
        System.out.println("================================\n");
        
        //          
        ReportCard reportCard = new ReportCard();
        reportCard.setStudentId(student.getId());
        reportCard.setStudentName(student.getName());
        reportCard.setSemester("2023-2024      ");
        
        //       
        if (reportSystem != null) {
            reportCard.setAcademicYear(reportSystem.getCurrentAcademicYear());
        }
        
        //     
        while (true) {
            System.out.print("        (    'done'/'cancel'): ");
            String subject = scanner.nextLine().trim();
            
            if (subject.equalsIgnoreCase("done")) {
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
                
                if (reportCard.addGrade(subject, score)) {
                    System.out.println("     : " + subject + "，  : " + score);
                } else {
                    System.err.println("      ，   ");
                }
            } catch (NumberFormatException e) {
                System.err.println("  ：        ");
            }
        }
        
        //        
        if (reportCard.getSubjectCount() == 0) {
            System.out.println("       ，    ");
            return;
        }
        
        //       
        System.out.print("        (  ，      ): ");
        String comment = scanner.nextLine().trim();
        if (!comment.isEmpty()) {
            reportCard.setTeacherComment(comment);
        }
        
        //     
        System.out.println("\n===       ===");
        reportCard.displayReportCard();
        
        System.out.print("\n        ？(yes/no): ");
        String confirm = scanner.nextLine().trim();
        
        if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
            //      （    ：  6）
            saveReportCard(reportCard, student);
        } else {
            System.out.println("     ");
        }
    }
    
    /**
     *      （    ：  6 +   7）
     * @param reportCard      
     * @param student     
     */
    private void saveReportCard(ReportCard reportCard, Student student) {
        if (!checkReportSystem()) {
            return;
        }
        
        try {
            //      
            boolean success = reportSystem.saveReportCard(reportCard);
            
            if (success) {
                System.out.println("\n======================================");
                System.out.println("       ！");
                System.out.println("======================================");
                
                //            
                System.out.println("   ID: " + reportCard.getId());
                System.out.println("  : " + student.getName());
                System.out.println("   : " + reportCard.getAverageScore());
                System.out.println("    : " + reportCard.getSubjectCount());
                
                //      
                System.out.print("          ？(yes/no): ");
                String archiveChoice = scanner.nextLine().trim();
                if (archiveChoice.equalsIgnoreCase("yes") || archiveChoice.equalsIgnoreCase("y")) {
                    if (reportSystem.archiveReportCard(reportCard.getId())) {
                        System.out.println("      ");
                    }
                }
                
                //                 （    ：  7）
                System.out.println("\n          ...");
                if (reportSystem != null) {
                    ClassRoom classRoom = reportSystem.getClassById(student.getClassId());
                    if (classRoom != null) {
                        showClassStudents(classRoom);
                    }
                }
            } else {
                System.err.println("  ：       ");
                System.out.println("    ： SMOS           ");
            }
        } catch (Exception e) {
            System.err.println("         : " + e.getMessage());
            System.out.println("    ：               ");
        }
    }
    
    /**
     *        
     */
    private void viewReportCardWorkflow() {
        if (!checkLoginStatus() || !checkReportSystem()) {
            return;
        }
        
        System.out.println("\n===       ===");
        System.out.println("1.    ID  ");
        System.out.println("2.           ");
        System.out.println("3.      ");
        System.out.print("   : ");
        
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
                System.err.println("  ：     ");
        }
    }
    
    /**
     *    ID     
     */
    private void viewReportCardByStudentId() {
        System.out.print("     ID: ");
        try {
            int studentId = Integer.parseInt(scanner.nextLine());
            
            if (reportSystem != null) {
                List<ReportCard> reportCards = reportSystem.getReportCardsByStudentId(studentId);
                
                if (reportCards.isEmpty()) {
                    System.out.println("          ");
                } else {
                    System.out.println("\n===         ===");
                    for (int i = 0; i < reportCards.size(); i++) {
                        ReportCard rc = reportCards.get(i);
                        System.out.printf("%d. %s%n", i + 1, rc.getSimpleInfo());
                    }
                    
                    System.out.print("\n        ，  0  : ");
                    int choice = Integer.parseInt(scanner.nextLine());
                    
                    if (choice > 0 && choice <= reportCards.size()) {
                        reportCards.get(choice - 1).displayReportCard();
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.err.println("  ：        ");
        }
    }
    
    /**
     *           
     */
    private void viewReportCardsByClass() {
        System.out.println("\n===      ===");
        List<ClassRoom> classes = reportSystem.getAllClasses();
        
        for (int i = 0; i < classes.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, classes.get(i).getClassName());
        }
        
        System.out.print("       : ");
        try {
            int classIndex = Integer.parseInt(scanner.nextLine());
            
            if (classIndex >= 1 && classIndex <= classes.size())