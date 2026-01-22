import java.util.Scanner;
import java.util.List;

/**
 * Main  -      
 *      ViewNoteDetails    ：
 * 1.     （      ）
 * 2.         
 * 3.           
 * 4.         
 * 5.             
 */
public class Main {
    
    /**
     *     -     
     * @param args      
     */
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("       ViewNoteDetails    -   ");
        System.out.println("================================================");
        
        //   Scanner        
        Scanner scanner = new Scanner(System.in);
        
        try {
            //   1:     
            User currentUser = loginUser(scanner);
            if (currentUser == null) {
                System.out.println("    ，    。");
                return;
            }
            
            //   2:           （      ）
            if (!currentUser.isAdmin()) {
                System.out.println("  ：             ！");
                System.out.println("      : " + currentUser.getRole());
                System.out.println("    。");
                return;
            }
            
            //   3:            
            displayWelcomeMessage(currentUser);
            displayAvailableNotes();
            
            //   4:           
            boolean continueViewing = true;
            while (continueViewing) {
                String noteId = selectNoteToView(scanner);
                
                if (noteId == null || noteId.equalsIgnoreCase("exit")) {
                    System.out.println("      。");
                    break;
                }
                
                //   5:   ViewNoteDetails  
                boolean success = executeViewNoteDetails(currentUser, noteId);
                
                //   6:               
                continueViewing = askToContinue(scanner);
            }
            
            //   7:       （  ）
            demonstrateAdditionalFeatures(currentUser);
            
        } catch (Exception e) {
            System.err.println("           : " + e.getMessage());
            e.printStackTrace();
        } finally {
            //     
            scanner.close();
            System.out.println("\n================================================");
            System.out.println("       ViewNoteDetails    -   ");
            System.out.println("================================================");
        }
    }
    
    /**
     *       
     * @param scanner      
     * @return          ，    null
     */
    private static User loginUser(Scanner scanner) {
        System.out.println("\n---      ---");
        
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (attempts < MAX_ATTEMPTS) {
            System.out.print("      : ");
            String username = scanner.nextLine().trim();
            
            System.out.print("     : ");
            String password = scanner.nextLine().trim();
            
            //     
            User user = User.login(username, password);
            
            if (user != null && user.isLoggedIn()) {
                System.out.println("    ！");
                return user;
            } else {
                attempts++;
                int remainingAttempts = MAX_ATTEMPTS - attempts;
                if (remainingAttempts > 0) {
                    System.out.println("    ，   。      : " + remainingAttempts);
                }
            }
        }
        
        System.out.println("          ，    。");
        return null;
    }
    
    /**
     *       
     * @param user     
     */
    private static void displayWelcomeMessage(User user) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     ViewNoteDetails   ");
        System.out.println("=".repeat(50));
        System.out.println(user.getUserInfo());
        System.out.println("=".repeat(50));
        
        //       
        System.out.println("    :");
        for (String permission : user.getPermissions()) {
            System.out.println("  - " + permission);
        }
        System.out.println("=".repeat(50));
    }
    
    /**
     *          
     */
    private static void displayAvailableNotes() {
        System.out.println("\n---        ---");
        
        List<String> noteIds = ViewNoteDetails.getAllNoteIds();
        List<Note> allNotes = ViewNoteDetails.getAllNotes();
        
        if (noteIds.isEmpty()) {
            System.out.println("         。");
            return;
        }
        
        System.out.println("  ID\t\t  \t\t  \t\t    ");
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
        System.out.println("  " + noteIds.size() + "    ");
    }
    
    /**
     *           
     * @param scanner      
     * @return        ID， null/exit    
     */
    private static String selectNoteToView(Scanner scanner) {
        System.out.println("\n---          ---");
        System.out.println("         ID（   'list'       ，   'exit'   ）:");
        System.out.print("  ID: ");
        
        String input = scanner.nextLine().trim();
        
        if (input.equalsIgnoreCase("list")) {
            displayAvailableNotes();
            return selectNoteToView(scanner); //          
        }
        
        if (input.equalsIgnoreCase("exit")) {
            return "exit";
        }
        
        //     ID    
        Note note = ViewNoteDetails.getNoteById(input);
        if (note == null) {
            System.out.println("  ：  ID '" + input + "'    ！");
            System.out.println("              ID:");
            displayAvailableNotes();
            return selectNoteToView(scanner); //          
        }
        
        return input;
    }
    
    /**
     *   ViewNoteDetails  
     * @param user     （      ）
     * @param noteId       ID
     * @return       
     */
    private static boolean executeViewNoteDetails(User user, String noteId) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("   ViewNoteDetails   ...");
        System.out.println("=".repeat(60));
        
        //       （      ）
        long startTime = System.currentTimeMillis();
        
        //   ViewNoteDetails  
        boolean success = ViewNoteDetails.viewNoteDetails(user, noteId);
        
        //       
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        if (success) {
            System.out.println("      ！  : " + duration + "ms");
            
            //   SMOS       （        ）
            ViewNoteDetails.handleServerConnectionIssue();
            
            //       
            System.out.println("\n✓          ！");
            System.out.println("    、  、  、              。");
        } else {
            System.out.println("      ！  : " + duration + "ms");
            System.out.println("\n✗         ，   ：");
            System.out.println("  1.     （      ）");
            System.out.println("  2.   ID    ");
            System.out.println("  3.         ");
        }
        
        System.out.println("=".repeat(60));
        return success;
    }
    
    /**
     *               
     * @param scanner      
     * @return true    ，false    
     */
    private static boolean askToContinue(Scanner scanner) {
        System.out.print("\n          ？(yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        
        if (response.equals("yes") || response.equals("y")) {
            return true;
        } else if (response.equals("no") || response.equals("n")) {
            System.out.println("     ViewNoteDetails   ！");
            return false;
        } else {
            System.out.println("    ，    'yes'   'no'。");
            return askToContinue(scanner); //             
        }
    }
    
    /**
     *       （  ）
     * @param user     
     */
    private static void demonstrateAdditionalFeatures(User user) {
        System.out.println("\n---        ---");
        
        // 1.         
        System.out.println("1.       :");
        System.out.println("   " + ViewNoteDetails.getNoteStatistics());
        
        // 2.       
        System.out.println("\n2.       :");
        String[] searchKeywords = {"  ", "  ", "  "};
        for (String keyword : searchKeywords) {
            List<Note> searchResults = ViewNoteDetails.searchNotes(keyword);
            System.out.println("         '" + keyword + "'    " + searchResults.size() + "    ");
        }
        
        // 3.       
        System.out.println("\n3.       :");
        
        //       ID
        System.out.println("   a)       ID:");
        boolean invalidResult = ViewNoteDetails.viewNoteDetails(user, "INVALID_ID");
        System.out.println("        : " + (invalidResult ? "  " : "  （    ）"));
        
        //      ID
        System.out.println("   b)      ID:");
        boolean emptyResult = ViewNoteDetails.viewNoteDetails(user, "");
        System.out.println("        : " + (emptyResult ? "  " : "  （    ）"));
        
        // 4.       
        System.out.println("\n4.       :");
        ViewNoteDetails.generateTestReport();
        
        // 5.         
        System.out.println("5.       :");
        System.out.println("                 : " + user.canViewNoteDetails());
        System.out.println("             : " + user.isAdmin());
        System.out.println("           : " + user.getPermissions().size());
        
        System.out.println("\n---        ---");
    }
    
    /**
     *          
     *                    
     */
    public static void demonstrateUseCaseScenarios() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("        ViewNoteDetails       ");
        System.out.println("=".repeat(70));
        
        //   1:      -          
        System.out.println("\n  1:      -          ");
        System.out.println("-".repeat(50));
        User adminUser = User.login("admin123", "adminPass123");
        if (adminUser != null && adminUser.isLoggedIn()) {
            ViewNoteDetails.viewNoteDetails(adminUser, "N001");
        }
        
        //   2:      -           
        System.out.println("\n  2:      -         ");
        System.out.println("-".repeat(50));
        User studentUser = User.login("student1", "studentPass1");
        if (studentUser != null && studentUser.isLoggedIn()) {
            ViewNoteDetails.viewNoteDetails(studentUser, "N002");
        }
        
        //   3:          
        System.out.println("\n  3:          ");
        System.out.println("-".repeat(50));
        User notLoggedInUser = new User();
        ViewNoteDetails.viewNoteDetails(notLoggedInUser, "N003");
        
        //   4:         
        System.out.println("\n  4:         ");
        System.out.println("-".repeat(50));
        if (adminUser != null) {
            ViewNoteDetails.viewNoteDetails(adminUser, "NON_EXISTENT");
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("               ");
        System.out.println("=".repeat(70));
    }
    
    /**
     *       
     *                  
     */
    public static void runDemoMode() {
        System.out.println("\n" + "*".repeat(70));
        System.out.println("           ViewNoteDetails       ");
        System.out.println("*".repeat(70));
        
        //         
        demonstrateUseCaseScenarios();
        
        //       
        System.out.println("\n    :");
        System.out.println("      : " + User.getUserStatistics());
        System.out.println("      : " + ViewNoteDetails.getNoteStatistics());
        
        System.out.println("\n" + "*".repeat(70));
        System.out.println("                ");
        System.out.println("*".repeat(70));
    }
    
    /**
     *       
     *                  
     */
    public static void runTestMode() {
        System.out.println("\n" + "#".repeat(70));
        System.out.println("           ViewNoteDetails       ");
        System.out.println("#".repeat(70));
        
        int totalTests = 0;
        int passedTests = 0;
        
        //   1:     
        System.out.println("\n  1:       ");
        System.out.println("-".repeat(40));
        totalTests++;
        User admin = User.login("admin123", "adminPass123");
        if (admin != null && admin.isLoggedIn() && admin.isAdmin()) {
            System.out.println("✓     :        ");
            passedTests++;
        } else {
            System.out.println("✗     :        ");
        }
        
        //   2:       
        System.out.println("\n  2:         ");
        System.out.println("-".repeat(40));
        totalTests++;
        if (admin != null) {
            boolean result = ViewNoteDetails.viewNoteDetails(admin, "N001");
            if (result) {
                System.out.println("✓     :         ");
                passedTests++;
            } else {
                System.out.println("✗     :         ");
            }
        }
        
        //   3:     
        System.out.println("\n  3:       ");
        System.out.println("-".repeat(40));
        totalTests++;
        User student = User.login("student1", "studentPass1");
        if (student != null && !student.isAdmin()) {
            System.out.println("✓     :          ");
            passedTests++;
        } else {
            System.out.println("✗     :       ");
        }
        
        //   4:     
        System.out.println("\n  4:       ");
        System.out.println("-".repeat(40));
        totalTests++;
        List<Note> searchResults = ViewNoteDetails.searchNotes("  ");
        if (!searchResults.isEmpty()) {
            System.out.println("✓     :     " + searchResults.size() + "    ");
            passedTests++;
        } else {
            System.out.println("✗     :       ");
        }
        
        //       
        System.out.println("\n" + "=".repeat(40));
        System.out.println("      ");
        System.out.println("=".repeat(40));
        System.out.println("    : " + totalTests);
        System.out.println("    : " + passedTests);
        System.out.println("    : " + (totalTests - passedTests));
        System.out.println("   : " + (passedTests * 100 / totalTests) + "%");
        
        if (passedTests == totalTests) {
            System.out.println("\n✓       ！      。");
        } else {
            System.out.println("\n✗       ，       。");
        }
        
        System.out.println("\n" + "#".repeat(70));
        System.out.println("                ");
        System.out.println("#".repeat(70));
    }
}