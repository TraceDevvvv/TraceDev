import java.util.Scanner;

/**
 *        
 *            
 */
class UserSession {
    private boolean isLoggedIn;
    private String username;
    
    /**
     *     ，       
     */
    public UserSession() {
        this.isLoggedIn = false;
        this.username = null;
    }
    
    /**
     *       
     * @param username    
     * @param password   
     * @return       
     */
    public boolean login(String username, String password) {
        //          
        //       ，               
        if (username != null && !username.trim().isEmpty() && 
            password != null && !password.trim().isEmpty()) {
            this.username = username;
            this.isLoggedIn = true;
            System.out.println("   " + username + "     ！");
            return true;
        } else {
            System.out.println("    ：          ");
            return false;
        }
    }
    
    /**
     *       
     * @return       
     */
    public boolean logout() {
        //       ：       
        if (!isLoggedIn) {
            System.out.println("    ：     ");
            return false;
        }
        
        //       
        String loggedOutUser = this.username;
        this.username = null;
        this.isLoggedIn = false;
        
        System.out.println("   " + loggedOutUser + "      ");
        return true;
    }
    
    /**
     *          
     * @return     
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     *           
     * @return    ，        null
     */
    public String getUsername() {
        return username;
    }
}

/**
 *      
 *                
 */
class LoginForm {
    private Scanner scanner;
    
    /**
     *     
     */
    public LoginForm() {
        this.scanner = new Scanner(System.in);
    }
    
    /**
     *       
     * @param userSession       ，        
     */
    public void display(UserSession userSession) {
        System.out.println("\n===      ===");
        System.out.println("      ：");
        String username = scanner.nextLine();
        
        System.out.println("     ：");
        String password = scanner.nextLine();
        
        //     
        boolean loginSuccess = userSession.login(username, password);
        
        if (loginSuccess) {
            System.out.println("    ！      。");
        } else {
            System.out.println("    ，         。");
        }
    }
    
    /**
     *        
     */
    public void close() {
        scanner.close();
    }
}

/**
 *     
 *            
 */
public class LogoutDemo {
    
    /**
     *      
     */
    private static void displayMenu() {
        System.out.println("\n===      ===");
        System.out.println("1.   ");
        System.out.println("2.   ");
        System.out.println("3.       ");
        System.out.println("4.     ");
        System.out.print("      (1-4): ");
    }
    
    /**
     *     -     
     * @param args      
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserSession userSession = new UserSession();
        LoginForm loginForm = new LoginForm();
        
        System.out.println("===            ===");
        System.out.println("              ");
        
        boolean running = true;
        
        while (running) {
            displayMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        //     
                        if (userSession.isLoggedIn()) {
                            System.out.println("     ，        ");
                        } else {
                            loginForm.display(userSession);
                        }
                        break;
                        
                    case 2:
                        //     
                        System.out.println("\n===      ===");
                        
                        //       ：       
                        if (!userSession.isLoggedIn()) {
                            System.out.println("  ：     ，        ");
                            System.out.println("      ");
                            break;
                        }
                        
                        System.out.println("      : " + userSession.getUsername());
                        System.out.print("      ？(y/n): ");
                        String confirm = scanner.nextLine();
                        
                        if (confirm.equalsIgnoreCase("y")) {
                            //       
                            boolean logoutSuccess = userSession.logout();
                            
                            if (logoutSuccess) {
                                //            （         ）
                                System.out.println("\n    ！        ...");
                                //             ，       ，       
                                System.out.println("           ");
                            }
                        } else {
                            System.out.println("       ");
                        }
                        break;
                        
                    case 3:
                        //       
                        System.out.println("\n===        ===");
                        if (userSession.isLoggedIn()) {
                            System.out.println("  :    ");
                            System.out.println("   : " + userSession.getUsername());
                        } else {
                            System.out.println("  :    ");
                        }
                        break;
                        
                    case 4:
                        //     
                        System.out.println("    ，    ！");
                        running = false;
                        break;
                        
                    default:
                        System.out.println("    ，   1-4     ");
                }
                
            } catch (NumberFormatException e) {
                System.out.println("  ：        ");
            }
        }
        
        //     
        scanner.close();
        
        //   ：loginForm     Scanner，          
        //   loginForm  display     ，  display       Scanner
    }
}