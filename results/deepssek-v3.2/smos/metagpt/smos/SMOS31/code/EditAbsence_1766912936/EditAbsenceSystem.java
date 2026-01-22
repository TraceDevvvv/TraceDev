import java.util.*;
import java.time.LocalDate;

/**
 *     ：EditAbsenceSystem
 *                 
 *                
 */
public class EditAbsenceSystem {
    
    /**
     *     -      
     *        、    、    、            
     */
    public static void main(String[] args) {
        System.out.println("===          ===");
        
        // 1.        （    ）
        Administrator admin = loginAsAdministrator();
        if (admin == null) {
            System.out.println("  ：       。    。");
            return;
        }
        
        System.out.println("    " + admin.getName() + "      。");
        
        // 2.    "SviewTetTingloregistration"      
        LocalDate selectedDate = selectDateForAbsenceEdit();
        System.out.println("     ：" + selectedDate);
        
        // 3.             （      1）
        updateScreenBasedOnDate(selectedDate);
        
        // 4.        （     ）   "  "（      2）
        boolean isAbsenceAdded = changeAbsence(selectedDate);
        
        // 5.           （      3）
        boolean serverResponse = sendDataToServer(selectedDate, isAbsenceAdded);
        
        if (serverResponse) {
            // 6.              
            sendEmailToParent(isAbsenceAdded);
            
            //     ：              ，        
            System.out.println("\n===        ===");
            System.out.println("1.        ");
            System.out.println("2.          ");
            System.out.println("3.           ");
        } else {
            //             
            System.out.println("\n===      ===");
            System.out.println("  ： SMOS        。");
            System.out.println("        。");
        }
    }
    
    /**
     *        
     * @return       Administrator  ，    null
     */
    private static Administrator loginAsAdministrator() {
        //       ，          
        //      ，          
        return new Administrator("admin001", "     ");
    }
    
    /**
     *             
     * @return      
     */
    private static LocalDate selectDateForAbsenceEdit() {
        //       ，          
        //      ，        
        return LocalDate.now();
    }
    
    /**
     *           
     * @param date      
     */
    private static void updateScreenBasedOnDate(LocalDate date) {
        System.out.println("  ：         " + date + "      ...");
        //         
        System.out.println("     ，   " + date + "      。");
    }
    
    /**
     *       （     ）
     * @param date   
     * @return true      ，false      
     */
    private static boolean changeAbsence(LocalDate date) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n      (1-    , 2-    ): ");
        int choice = scanner.nextInt();
        
        boolean isAdded = (choice == 1);
        String action = isAdded ? "  " : "  ";
        System.out.println(" " + action + " " + date + "      。");
        
        return isAdded;
    }
    
    /**
     *         
     * @param date   
     * @param isAdded       
     * @return true         ，false      
     */
    private static boolean sendDataToServer(LocalDate date, boolean isAdded) {
        System.out.println("         SMOS   ...");
        
        //        
        SMOSServer server = new SMOSServer();
        boolean isConnected = server.connect();
        
        if (!isConnected) {
            System.out.println("  ：        。");
            //          
            Random random = new Random();
            if (random.nextBoolean()) {
                System.out.println("  ： SMOS        ！");
                return false;
            }
        }
        
        //     
        String data = String.format("{\"date\": \"%s\", \"action\": \"%s\"}", 
                                   date.toString(), isAdded ? "ADD" : "DELETE");
        boolean success = server.sendData(data);
        
        server.disconnect();
        
        if (success) {
            System.out.println("           。");
            return true;
        } else {
            System.out.println("      。");
            return false;
        }
    }
    
    /**
     *          
     * @param isAdded       
     */
    private static void sendEmailToParent(boolean isAdded) {
        EmailService emailService = new EmailService();
        Student student = new Student("S001", "  ", new Parent("P001", "  ", "zhangfu@example.com"));
        
        String action = isAdded ? "  " : "  ";
        String subject = "        ";
        String message = String.format("     ，     %s        %s。         。", 
                                      student.getName(), action);
        
        boolean emailSent = emailService.sendEmail(student.getParent(), subject, message);
        
        if (emailSent) {
            System.out.println("           " + student.getParent().getName() + "。");
        } else {
            System.out.println("  ：      ，        。");
        }
    }
}