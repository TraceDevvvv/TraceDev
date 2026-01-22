import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * JustificationForm -          
 *           、      、      ，   Justification  。
 *                  。
 */
public class JustificationForm {
    
    //       ，         
    private static final DateTimeFormatter DATE_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    //     
    private String absenceRecordId;  //     ID（              ）
    private LocalDate justificationDate; //     
    private String reason;           //     
    private String adminId;          //    ID
    
    /**
     *       
     *            
     */
    public JustificationForm() {
        //       ，absenceRecordId               
        //                ID
        this.absenceRecordId = "ABS_001"; //       ID
        this.adminId = "admin001";        //      ID
    }
    
    /**
     *         
     * @param absenceRecordId     ID
     * @param adminId    ID
     */
    public JustificationForm(String absenceRecordId, String adminId) {
        this.absenceRecordId = absenceRecordId;
        this.adminId = adminId;
    }
    
    /**
     *         
     *             
     */
    public void displayForm() {
        System.out.println("\n===        ===");
        System.out.println("       ：");
        System.out.println("----------------------------------------");
        System.out.println("    ID: " + absenceRecordId + " (           )");
        System.out.println("   ID: " + adminId);
        System.out.println("----------------------------------------");
        System.out.println("       ：");
        System.out.println("1.      (  : YYYY-MM-DD)");
        System.out.println("2.      (  500   )");
        System.out.println("----------------------------------------");
        System.out.println("     ，   '  '  。");
        System.out.println("  '  '      。");
    }
    
    /**
     *       
     *           ，    ，   Justification  
     * @param scanner      
     * @return Justification  ，              null
     */
    public Justification collectInput(Scanner scanner) {
        try {
            System.out.println("\n---        ---");
            
            //            
            System.out.print("      ？(  ' '  ，'  '    ): ");
            String confirmation = scanner.nextLine().trim();
            
            if ("  ".equalsIgnoreCase(confirmation)) {
                System.out.println("        。");
                throw new OperationInterruptedException("           。");
            }
            
            //       
            LocalDate date = collectDate(scanner);
            if (date == null) {
                return null; //          
            }
            
            //       
            String reasonText = collectReason(scanner);
            if (reasonText == null) {
                return null; //          
            }
            
            //      Justification  
            Justification justification = new Justification(
                absenceRecordId, date, reasonText, adminId
            );
            
            System.out.println("\n---        ---");
            System.out.println("      ：");
            System.out.println("    : " + Justification.formatDate(date));
            System.out.println("    : " + 
                (reasonText.length() > 50 ? reasonText.substring(0, 50) + "..." : reasonText));
            
            return justification;
            
        } catch (OperationInterruptedException e) {
            System.out.println("       : " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("           : " + e.getMessage());
            return null;
        }
    }
    
    /**
     *       
     *          ，        
     * @param scanner      
     * @return    LocalDate  ，         null
     */
    private LocalDate collectDate(Scanner scanner) {
        int maxAttempts = 3; //       
        int attempts = 0;
        
        while (attempts < maxAttempts) {
            try {
                System.out.print("\n        (  : YYYY-MM-DD，  : 2024-12-28): ");
                String dateInput = scanner.nextLine().trim();
                
                //          
                if ("  ".equalsIgnoreCase(dateInput)) {
                    System.out.println("       。");
                    throw new OperationInterruptedException("         。");
                }
                
                //           
                if (dateInput.isEmpty()) {
                    System.out.println("  ：      。");
                    attempts++;
                    continue;
                }
                
                //       
                if (!isValidDateFormat(dateInput)) {
                    System.out.println("  ：       ，   YYYY-MM-DD  。");
                    attempts++;
                    continue;
                }
                
                //       
                LocalDate date = LocalDate.parse(dateInput, DATE_FORMATTER);
                
                //         （       ，           ）
                LocalDate today = LocalDate.now();
                LocalDate minValidDate = today.minusYears(1); //           
                
                if (date.isAfter(today)) {
                    System.out.println("  ：           。");
                    System.out.print("    ？(  ' '       ，' '    ): ");
                    String confirm = scanner.nextLine().trim();
                    if (" ".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                } else if (date.isBefore(minValidDate)) {
                    System.out.println("  ：        （    ）。");
                    System.out.print("    ？(  ' '       ，' '    ): ");
                    String confirm = scanner.nextLine().trim();
                    if (" ".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                }
                
                return date;
                
            } catch (DateTimeParseException e) {
                System.out.println("  ：      ，         。");
                attempts++;
            } catch (OperationInterruptedException e) {
                throw e; //         
            } catch (Exception e) {
                System.out.println("  ：       - " + e.getMessage());
                attempts++;
            }
        }
        
        System.out.println("  ：         ，      。");
        return null;
    }
    
    /**
     *       
     *            ，       
     * @param scanner      
     * @return           ，         null
     */
    private String collectReason(Scanner scanner) {
        int maxAttempts = 3; //       
        int attempts = 0;
        
        while (attempts < maxAttempts) {
            try {
                System.out.println("\n        (  500   ): ");
                System.out.print("> ");
                String reasonInput = scanner.nextLine().trim();
                
                //          
                if ("  ".equalsIgnoreCase(reasonInput)) {
                    System.out.println("         。");
                    throw new OperationInterruptedException("           。");
                }
                
                //         
                if (reasonInput.isEmpty()) {
                    System.out.println("  ：        。");
                    attempts++;
                    continue;
                }
                
                //       
                if (reasonInput.length() > 500) {
                    System.out.println("  ：      500  ，    。");
                    System.out.println("    : " + reasonInput.length() + "   ");
                    System.out.print("    ？(  ' '     ，' '    ): ");
                    String confirm = scanner.nextLine().trim();
                    
                    if (" ".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                    
                    //    500  
                    reasonInput = reasonInput.substring(0, 500);
                    System.out.println("    500  。");
                }
                
                //       （    ）
                if (reasonInput.trim().length() < 5) {
                    System.out.println("  ：      ，         。");
                    System.out.print("    ？(  ' '  ，' '    ): ");
                    String confirm = scanner.nextLine().trim();
                    
                    if (" ".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                }
                
                //             
                if (containsInvalidContent(reasonInput)) {
                    System.out.println("  ：            。");
                    System.out.print("    ？(  ' '  ，' '    ): ");
                    String confirm = scanner.nextLine().trim();
                    
                    if (" ".equalsIgnoreCase(confirm)) {
                        attempts++;
                        continue;
                    }
                }
                
                return reasonInput;
                
            } catch (OperationInterruptedException e) {
                throw e; //         
            } catch (Exception e) {
                System.out.println("  ：         - " + e.getMessage());
                attempts++;
            }
        }
        
        System.out.println("  ：         ，        。");
        return null;
    }
    
    /**
     *           
     * @param dateString      
     * @return true      
     */
    private boolean isValidDateFormat(String dateString) {
        //       ：YYYY-MM-DD
        if (dateString == null || dateString.length() != 10) {
            return false;
        }
        
        //      
        if (dateString.charAt(4) != '-' || dateString.charAt(7) != '-') {
            return false;
        }
        
        //            
        try {
            String[] parts = dateString.split("-");
            if (parts.length != 3) {
                return false;
            }
            
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            
            //       
            if (year < 1900 || year > 2100) {
                return false;
            }
            if (month < 1 || month > 12) {
                return false;
            }
            if (day < 1 || day > 31) {
                return false;
            }
            
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     *             
     * @param text       
     * @return true        
     */
    private boolean containsInvalidContent(String text) {
        if (text == null || text.trim().isEmpty()) {
            return false;
        }
        
        String lowerText = text.toLowerCase();
        
        //               
        if (text.trim().matches("^[\\p{P}\\s]+$")) {
            return true;
        }
        
        //             
        String[] placeholders = {"test", "  ", "  ", "example", "placeholder", "xxx"};
        for (String placeholder : placeholders) {
            if (lowerText.contains(placeholder)) {
                return true;
            }
        }
        
        //          （         ）
        return false;
    }
    
    /**
     *       ID
     * @return     ID
     */
    public String getAbsenceRecordId() {
        return absenceRecordId;
    }
    
    /**
     *       ID
     * @param absenceRecordId     ID
     */
    public void setAbsenceRecordId(String absenceRecordId) {
        this.absenceRecordId = absenceRecordId;
    }
    
    /**
     *      ID
     * @return    ID
     */
    public String getAdminId() {
        return adminId;
    }
    
    /**
     *      ID
     * @param adminId    ID
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    
    /**
     *        （   ）
     */
    public static class OperationInterruptedException extends Exception {
        public OperationInterruptedException(String message) {
            super(message);
        }
    }
}