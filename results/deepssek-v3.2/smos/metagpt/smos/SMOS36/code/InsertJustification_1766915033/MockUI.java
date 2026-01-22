```
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * MockUI -        
 *                 ，    、    、       。
 *                         。
 */
public class MockUI {
    
    private Scanner scanner;
    private boolean isLoggedIn;
    private String currentAdminId;
    private JustificationRepository repository;
    
    /**
     *       
     *    UI     
     */
    public MockUI() {
        this.scanner = new Scanner(System.in);
        this.isLoggedIn = false;
        this.currentAdminId = null;
        this.repository = new JustificationRepository();
    }
    
    /**
     *       
     */
    public void start() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                   -                ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
        
        try {
            //       
            performSystemChecks();
            
            //         
            while (true) {
                displayMainMenu();
                
                int choice = -1;
                try {
                    System.out.print("\n      (1-5): ");
                    choice = scanner.nextInt();
                    scanner.nextLine(); //      
                } catch (InputMismatchException e) {
                    scanner.nextLine(); //       
                    System.out.println("  ：          。");
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
                            System.out.println("      。");
                        }
                        break;
                    case 3:
                        if (checkLogin()) {
                            handleViewJustifications();
                        } else {
                            System.out.println("      。");
                        }
                        break;
                    case 4:
                        handleSystemStatus();
                        break;
                    case 5:
                        handleExit();
                        return;
                    default:
                        System.out.println("     ，     。");
                }
                
                System.out.println("\n Enter   ...");
                scanner.nextLine();
            }
            
        } catch (Exception e) {
            System.out.println("      : " + e.getMessage());
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
    
    /**
     *      
     */
    private void displayMainMenu() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                       ");
        System.out.println("══════════════════════════════════════════════════");
        System.out.println("1.      ");
        System.out.println("2.       ");
        System.out.println("3.       ");
        System.out.println("4.     ");
        System.out.println("5.     ");
        System.out.println("══════════════════════════════════════════════════");
        
        if (isLoggedIn) {
            System.out.println("    : " + currentAdminId + " (   )");
        } else {
            System.out.println("    :    ");
        }
    }
    
    /**
     *       
     */
    private void performSystemChecks() {
        System.out.println("\n        ...");
        
        //        
        boolean serverConnected = repository.testConnection();
        if (!serverConnected) {
            System.out.println("⚠   ： SMOS        。");
        } else {
            System.out.println("✓ SMOS       。");
        }
        
        //           （  ）
        System.out.println("\n          :");
        System.out.println("✓ SviewTetTingloregister    :    ");
        System.out.println("✓ viewellacogiustifies    :    ");
        System.out.println("✓         :     ");
        
        //          
        try {
            int count = repository.count();
            System.out.println("✓          ，   " + count + "        。");
        } catch (JustificationRepository.RepositoryException e) {
            System.out.println("⚠   ：         - " + e.getMessage());
        }
        
        System.out.println("\n      ，      。");
    }
    
    /**
     *        
     */
    private void handleLogin() {
        if (isLoggedIn) {
            System.out.println("\n         : " + currentAdminId);
            System.out.print("          ？(y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            
            if (!response.equals("y") && !response.equals("yes")) {
                System.out.println("      。");
                return;
            }
            
            isLoggedIn = false;
            currentAdminId = null;
        }
        
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                         ");
        System.out.println("══════════════════════════════════════════════════");
        
        int attempts = 0;
        final int MAX_ATTEMPTS = 3;
        
        while (attempts < MAX_ATTEMPTS) {
            try {
                System.out.print("\n      : ");
                String username = scanner.nextLine().trim();
                
                System.out.print("  : ");
                String password = scanner.nextLine().trim();
                
                //        
                boolean isValid = validateAdminCredentials(username, password);
                
                if (isValid) {
                    isLoggedIn = true;
                    currentAdminId = "admin_" + username;
                    
                    System.out.println("\n✅     ！");
                    System.out.println("  ，    " + username);
                    
                    //        
                    simulatePostLoginOperations();
                    
                    return;
                } else {
                    attempts++;
                    int remainingAttempts = MAX_ATTEMPTS - attempts;
                    
                    if (remainingAttempts > 0) {
                        System.out.println("\n❌     ，         。");
                        System.out.println("      : " + remainingAttempts);
                    } else {
                        System.out.println("\n❌          ，    。");
                    }
                }
                
            } catch (Exception e) {
                attempts++;
                System.out.println("         : " + e.getMessage());
            }
        }
    }
    
    /**
     *        
     * @param username    
     * @param password   
     * @return true      
     */
    private boolean validateAdminCredentials(String username, String password) {
        //          
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        //        
        if (!repository.isConnected()) {
            System.out.println("⚠   ：       ，      。");
        }
        
        //        （               ）
        return "admin".equals(username) && "admin123".equals(password);
    }
    
    /**
     *        
     */
    private void simulatePostLoginOperations() {
        System.out.println("\n          ...");
        
        try {
            //         
            Thread.sleep(500);
            System.out.println("✓           ...   ");
            
            //         
            Thread.sleep(300);
            System.out.println("✓       ...   ");
            
            //            
            Thread.sleep(400);
            System.out.println("✓            ...   ");
            
            System.out.println("        ，      。");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     *           
     *          
     */
    private void handleInsertJustification() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                        ");
        System.out.println("══════════════════════════════════════════════════");
        
        //       
        if (!checkPreconditionsForInsertion()) {
            System.out.println("      。");
            return;
        }
        
        try {
            //    ：       
            System.out.println("\n1.        ...");
            if (!repository.isConnected()) {
                System.out.println("❌   ：     SMOS   。");
                throw new ServerConnectionException("SMOS       ");
            }
            System.out.println("✓        ");
            
            //    ：          
            System.out.println("\n2.          ...");
            displaySelectedAbsenceRecord();
            
            //    ：       
            System.out.println("\n3.         ...");
            JustificationForm form = new JustificationForm("ABS_RED_001", currentAdminId);
            form.displayForm();
            
            //    ：      
            System.out.println("\n4.     ...");
            Justification justification = form.collectInput(scanner);
            
            if (justification == null) {
                System.out.println("       。");
                return;
            }
            
            //    ：    
            System.out.println("\n5.       ...");
            if (ValidationUtils.validateJustification(justification)) {
                System.out.println("✓       ");
            } else {
                System.out.println("❌       ，     。");
                return;
            }
            
            //    ：    
            System.out.println("\n6.     ...");
            System.out.println(justification);
            System.out.print("\n          ？(y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            
            if (!confirm.equals("y") && !confirm.equals("yes")) {
                System.out.println("       。");
                return;
            }
            
            //    ：      
            System.out.println("\n7.       ...");
            boolean saved = repository.save(justification);
            
            if (saved) {
                System.out.println("\n✅             ！");
                
                //    ：        
                System.out.println("\n8.       ...");
                simulateReturnToRegistryScreen();
                
                //          
                System.out.println("\n══════════════════════════════════════════════════");
                System.out.println("                         ");
                System.out.println("══════════════════════════════════════════════════");
                System.out.println(justification.toDetailedString());
                System.out.println("══════════════════════════════════════════════════");
            } else {
                System.out.println("❌     ，     。");
            }
            
        } catch (ServerConnectionException e) {
            System.out.println("❌   ：" + e.getMessage());
            System.out.println("    ：  SMOS     。");
        } catch (JustificationForm.OperationInterruptedException e) {
            System.out.println("    ：" + e.getMessage());
        } catch (JustificationRepository.RepositoryException e) {
            System.out.println("     ：" + e.getMessage());
        } catch (Exception e) {
            System.out.println("    ：" + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     *            
     * @return true          
     */
    private boolean checkPreconditionsForInsertion() {
        System.out.println("           ...");
        
        boolean allConditionsMet = true;
        
        //   1:         
        if (!isLoggedIn) {
            System.out.println("❌      ：      ");
            allConditionsMet = false;
        } else {
            System.out.println("✓       ：" + currentAdminId);
        }
        
        //   2:      SviewTetTingloregister  （  ）
        System.out.println("✓   SviewTetTingloregister   ");
        
        //   3:      viewellacogiustifies  （  ）
        System.out.println("✓   viewellacogiustifies   ");
        
        //   4:              （  ）
        System.out.println("✓          ");
        
        if (allConditionsMet) {
            System.out.println("✓          ");
        } else {
            System.out.println("❌          ");
        }
        
        return allConditionsMet;
    }
    
    /**
     *            
     */
    private void displaySelectedAbsenceRecord() {
        System.out.println("       ：");
        System.out.println("    ID: ABS_RED_001");
        System.out.println("      :   ");
        System.out.println("      : 2024-12-25");
        System.out.println("      :      ");
        System.out.println("    :     （      ）");
        System.out.println("    :        ，        ");
    }
    
    /**
     *         
     */
    private void simulateReturnToRegistryScreen() {
        System.out.println("        ...");
        
        try {
            //         
            for (int i = 0; i < 3; i++) {
                Thread.sleep(200);
                System.out.print(".");
            }
            
            System.out.println("\n              。");
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     *           
     */
    private void handleViewJustifications() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                        ");
        System.out.println("══════════════════════════════════════════════════");
        
        try {
            List<Justification> justifications = repository.findAll();
            
            if (justifications.isEmpty()) {
                System.out.println("        。");
                return;
            }
            
            System.out.println("       (  " + justifications.size() + "    ):");
            System.out.println("══════════════════════════════════════════════════");
            
            for (int i = 0; i < justifications.size(); i++) {
                Justification j = justifications.get(i);
                System.out.println("   #" + (i + 1));
                System.out.println(j);
                System.out.println("──────────────────────────────────────────────");
            }
            
            //         
            System.out.print("\n             (0  ): ");
            try {
                int selection = scanner.nextInt();
                scanner.nextLine(); //      
                
                if (selection > 0 && selection <= justifications.size()) {
                    Justification selected = justifications.get(selection - 1);
                    System.out.println("\n══════════════════════════════════════════════════");
                    System.out.println("                         ");
                    System.out.println("══════════════════════════════════════════════════");
                    System.out.println(selected.toDetailedString());
                    System.out.println("══════════════════════════════════════════════════");
                } else if (selection != 0) {
                    System.out.println("     。");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine(); //       
                System.out.println("        。");
            }
            
        } catch (JustificationRepository.RepositoryException e) {
            System.out.println("        : " + e.getMessage());
        }
    }
    
    /**
     *         
     */
    private void handleSystemStatus() {
        System.out.println("\n══════════════════════════════════════════════════");
        System.out.println("                        ");
        System.out.println("══════════════════════════════════════════════════");
        
        //     
        System.out.println("    :");
        System.out.println("      :         ");
        System.out.println("    : 1.0.0");
        System.out.println("      :      ");
        
        //     
        System.out.println("\n    :");
        System.out.println("      : " + (isLoggedIn ? "   " : "   "));
        System.out.println("    ID: " + (currentAdminId != null ? currentAdminId : " "));
        
        //     
        System.out.println("\n    :");
        System.out.println("  SMOS   : " + (repository.isConnected() ? "  " : "  "));
        
        //      
        System.out.println("\n     :");
        try {
            int count = repository.count();
            System.out.println("         : " + count);
            System.out.println("        :   ");
        } catch (JustificationRepository.RepositoryException e) {
            System.out.println("        :    - " + e.getMessage());
        }
        
        //       
        System.out.println("\n      : " + LocalDate.now());
        System.out.println("══════════════════════════════════════════════════");
    }
    
    /**
     *       
     */
    private void handleExit() {
        System.out.println("\n      ...");
        
        try {
            if (isLoggedIn) {
                System.out.println("         ...");
                Thread.sleep(500);
                System.out.println("    " + currentAdminId + "    。");
           