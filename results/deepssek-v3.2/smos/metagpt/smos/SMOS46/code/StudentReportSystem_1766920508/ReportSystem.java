```
/**
 * ReportSystem.java
 *                
 *       ：    、    、     、     
 *               
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ReportSystem {
    private static int nextReportCardId = 1001;      //    ID   
    private static int nextStudentId = 2001;         //   ID   
    private static int nextClassId = 3001;           //   ID   
    
    private String currentAcademicYear;              //     
    private List<ClassRoom> classRooms;              //       
    private List<Student> allStudents;               //       （    ，    ）
    private Map<Integer, ReportCard> reportCards;    //      （ ID  ）
    private SMOSServer smosServer;                   // SMOS     
    private Administrator currentAdmin;              //      
    private Random random;                           //       
    
    /**
     *       
     *           
     */
    public ReportSystem() {
        this.currentAcademicYear = "2023-2024"; //     
        this.classRooms = new ArrayList<>();
        this.allStudents = new ArrayList<>();
        this.reportCards = new HashMap<>();
        this.smosServer = new SMOSServer();
        this.currentAdmin = null;
        this.random = new Random();
        
        //        
        initializeDemoData();
        
        System.out.println("            ");
        System.out.println("    : " + currentAcademicYear);
    }
    
    /**
     *        
     *       、              
     */
    private void initializeDemoData() {
        //     
        ClassRoom class1 = new ClassRoom(nextClassId++, "     ", currentAcademicYear, "   ", 30);
        ClassRoom class2 = new ClassRoom(nextClassId++, "     ", currentAcademicYear, "   ", 30);
        ClassRoom class3 = new ClassRoom(nextClassId++, "     ", currentAcademicYear, "   ", 30);
        
        //        
        classRooms.add(class1);
        classRooms.add(class2);
        classRooms.add(class3);
        
        //             
        Student[] students1 = {
            new Student(nextStudentId++, "  ", class1.getId(), 7),
            new Student(nextStudentId++, "  ", class1.getId(), 7),
            new Student(nextStudentId++, "  ", class1.getId(), 8)
        };
        
        Student[] students2 = {
            new Student(nextStudentId++, "  ", class2.getId(), 7),
            new Student(nextStudentId++, "  ", class2.getId(), 7),
            new Student(nextStudentId++, "  ", class2.getId(), 8)
        };
        
        Student[] students3 = {
            new Student(nextStudentId++, "  ", class3.getId(), 8),
            new Student(nextStudentId++, "  ", class3.getId(), 8),
            new Student(nextStudentId++, "   ", class3.getId(), 9)
        };
        
        //          
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
        
        //          
        createDemoReportCards();
        
        System.out.println("         : " + 
                          classRooms.size() + "   , " + 
                          allStudents.size() + "   , " + 
                          reportCards.size() + "    ");
    }
    
    /**
     *          
     */
    private void createDemoReportCards() {
        //                   
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
     *             
     * @param student     
     * @return       
     */
    private ReportCard createReportCardForStudent(Student student) {
        if (student == null) {
            return null;
        }
        
        ReportCard reportCard = new ReportCard(nextReportCardId++, 
                                              student.getId(), 
                                              student.getName(), 
                                              currentAcademicYear);
        reportCard.setSemester("2023-2024      ");
        
        //         
        String[] subjects = {"  ", "  ", "  ", "  ", "  "};
        for (String subject : subjects) {
            double score = 60 + random.nextInt(40); // 60-100       
            reportCard.addGrade(subject, score);
        }
        
        reportCard.setTeacherComment(student.getName() + "         ，    ！");
        reportCard.setStatus("   ");
        reportCard.setArchived(true);
        
        return reportCard;
    }
    
    /**
     *          
     * @return     
     */
    public List<ClassRoom> getAllClasses() {
        return new ArrayList<>(classRooms);
    }
    
    /**
     *            
     * @return          
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
     *     ID      
     * @param classId   ID
     * @return     ，        null
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
     *      
     * @param className     
     * @param teacherName      
     * @param maxStudents      
     * @return        ，         null
     */
    public ClassRoom createNewClass(String className, String teacherName, int maxStudents) {
        if (className == null || className.trim().isEmpty()) {
            System.err.println("  ：        ");
            return null;
        }
        
        ClassRoom newClass = new ClassRoom(nextClassId++, className.trim(), 
                                          currentAcademicYear, teacherName, maxStudents);
        classRooms.add(newClass);
        
        System.out.println("       : " + newClass.getSimpleInfo());
        return newClass;
    }
    
    /**
     *     ID      
     * @param studentId   ID
     * @return     ，        null
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
     *      
     * @param name     
     * @param classId   ID
     * @param age   
     * @return        ，         null
     */
    public Student createNewStudent(String name, int classId, int age) {
        ClassRoom classRoom = getClassById(classId);
        if (classRoom == null) {
            System.err.println("  ：         (ID: " + classId + ")");
            return null;
        }
        
        if (classRoom.isFull()) {
            System.err.println("  ：    ，       ");
            return null;
        }
        
        Student newStudent = new Student(nextStudentId++, name, classId, age);
        
        if (classRoom.addStudent(newStudent)) {
            allStudents.add(newStudent);
            System.out.println("       : " + newStudent.getSimpleInfo());
            return newStudent;
        } else {
            System.err.println("      ");
            return null;
        }
    }
    
    /**
     *         
     * @param reportCard        
     * @return         true，    false
     */
    public boolean saveReportCard(ReportCard reportCard) {
        if (reportCard == null) {
            System.err.println("  ：        ");
            return false;
        }
        
        //         
        Student student = getStudentById(reportCard.getStudentId());
        if (student == null) {
            System.err.println("  ：      (ID: " + reportCard.getStudentId() + ")");
            return false;
        }
        
        //          
        if (!reportCard.isValid()) {
            System.err.println("  ：        ");
            return false;
        }
        
        //        ID，      ID
        if (reportCard.getId() <= 0) {
            reportCard.setId(nextReportCardId++);
        }
        
        //         
        if (!reportCard.submit()) {
            System.err.println("  ：       ");
            return false;
        }
        
        //      
        reportCards.put(reportCard.getId(), reportCard);
        
        System.out.println("       : " + reportCard.getSimpleInfo());
        
        //      SMOS   （      ）
        syncReportCardToSMOS(reportCard);
        
        return true;
    }
    
    /**
     *       SMOS   
     * @param reportCard        
     */
    private void syncReportCardToSMOS(ReportCard reportCard) {
        new Thread(() -> {
            System.out.println("        SMOS   ...");
            
            //        
            if (!smosServer.checkConnection()) {
                System.out.println("     SMOS   ...");
                if (!smosServer.connect()) {
                    System.err.println("  ：     SMOS   ，         ");
                    return;
                }
            }
            
            //     
            if (smosServer.syncData(reportCard)) {
                System.out.println("      SMOS     ");
            } else {
                System.err.println("  ：      SMOS     ");
                System.err.println("    ： SMOS           ");
                
                //       
                smosServer.simulateConnectionInterruption();
            }
        }).start();
    }
    
    /**
     *      
     * @param reportCardId    ID
     * @return         true，    false
     */
    public boolean archiveReportCard(int reportCardId) {
        ReportCard reportCard = reportCards.get(reportCardId);
        if (reportCard == null) {
            System.err.println("  ：       (ID: " + reportCardId + ")");
            return false;
        }
        
        if (reportCard.isArchived()) {
            System.out.println("      ，      ");
            return true;
        }
        
        //        
        if (!smosServer.checkConnection()) {
            System.out.println("     SMOS   ...");
            if (!smosServer.connect()) {
                System.err.println("  ：     SMOS   ，      ");
                return false;
            }
        }
        
        //    SMOS   
        if (smosServer.archiveReportCard(reportCard)) {
            System.out.println("       : " + reportCardId);
            return true;
        } else {
            System.err.println("  ：       ");
            System.err.println("    ： SMOS           ");
            return false;
        }
    }
    
    /**
     *     ID       
     * @param studentId   ID
     * @return          
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
     *     ID             
     * @param classId   ID
     * @return        
     */
    public List<ReportCard> getReportCardsByClassId(int classId) {
        List<ReportCard> result = new ArrayList<>();
        
        //            
        ClassRoom classRoom = getClassById(classId);
        if (classRoom == null) {
            return result;
        }
        
        List<Integer> studentIds = classRoom.getAllStudentIds();
        
        //           
        for (ReportCard reportCard : reportCards.values()) {
            if (studentIds.contains(reportCard.getStudentId())) {
                result.add(reportCard);
            }
        }
        
        return result;
    }
    
    /**
     *        
     * @param reportCardId    ID
     * @return      ，        null
     */
    public ReportCard getReportCard(int reportCardId) {
        return reportCards.get(reportCardId);
    }
    
    /**
     *          
     * @return      
     */
    public int getReportCardCount() {
        return reportCards.size();
    }
    
    /**
     *       
     * @return     
     */
    public String getCurrentAcademicYear() {
        return currentAcademicYear;
    }
    
    /**
     *       
     * @param academicYear   
     */
    public void setCurrentAcademicYear(String academicYear) {
        if (academicYear == null || academicYear.trim().isEmpty()) {
            throw new IllegalArgumentException("      ");
        }
        this.currentAcademicYear = academicYear.trim();
        System.out.println("        : " + currentAcademicYear);
    }
    
    /**
     *        
     * @param admin      
     */
    public void setCurrentAdmin(Administrator admin) {
        this.currentAdmin = admin;
        if (admin != null) {
            admin.setReportSystem(this);
        }
    }
    
    /**
     *        
     * @return        
     */
    public Administrator getCurrentAdmin() {
        return currentAdmin;
    }
    
    /**
     *   SMOS     
     * @return SMOS     
     */
    public SMOSServer getSMOSServer() {
        return smosServer;
    }
    
    /**
     *         
     */
    public void displaySystemStats() {
        System.out.println("\n===        ===");
        System.out.println("    : " + currentAcademicYear);
        System.out.println("    : " + classRooms.size());
        System.out.println("    : " + allStudents.size());
        System.out.println("     : " + reportCards.size());
        System.out.println("      : " + smosServer.getArchivedReportCount());
        System.out.println("SMOS     : " + 
                          (smosServer.checkConnection() ? "   " : "   "));
        
        //          
        System.out.println("\n    :");
        for (ClassRoom classRoom : classRooms) {
            System.out.println("  " + classRoom.getSimpleInfo());
        }
    }
    
    /**
     *         
     *               
     */
    public void handleUserInterruption() {
        System.out.println("\n===        ===");
        System.out.println("         ");
        System.out.println("       ");
        System.out.println("      ...");
    }
    
    /**
     *          
     * @param reportCard         （   null）
     */
    public void handleServerConnectionInterruption(ReportCard reportCard) {
        System.out.println("\n=== SMOS        ===");
        System.out.println("  ： SMOS         ");
        
        if (reportCard != null) {
            System.out.println("        : " + reportCard.getSimpleInfo());
            System.out.println("         ，           ");
            
            //          
            reportCard.setStatus("    ");
        }
        
        System.out.println("          ");
        System.out.println("            ");
    }
    
    /**
     *               
     *            
     */
    public void simulateInsertPageWorkflow() {
        System.out.println("\n========================================");
        System.out.println("           ...");
        System.out.println("  ：InsertPage");
        System.out.println("   ：   ");
        System.out.println("========================================\n");
        
        //     ：         
        System.out.println("[      ]");
        System.out.println("1.               - ✓");
        System.out.println("2.     \"    \"   - ✓");
        System.out.println();
        
        //     ：  1 -       
        System.out.println("[  