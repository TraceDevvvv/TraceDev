import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *       
 *           ：
 * 1.           >=5
 * 2.             
 * 3.              SMOS  
 */
public class LoginSystem {
    
    /**
     *    ，          
     */
    static class User {
        private String username;
        private String password;
        
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
        
        public String getUsername() {
            return username;
        }
        
        public String getPassword() {
            return password;
        }
    }
    
    /**
     *        
     *   HashMap      ，     ，  User  
     */
    private static Map<String, User> userDatabase;
    
    /**
     *         
     *             
     */
    private static void initializeDatabase() {
        userDatabase = new HashMap<>();
        //       
        userDatabase.put("alice123", new User("alice123", "password123"));
        userDatabase.put("bob456", new User("bob456", "securepass456"));
        userDatabase.put("charlie789", new User("charlie789", "mypassword789"));
        userDatabase.put("david101", new User("david101", "davidpass101"));
        userDatabase.put("emma202", new User("emma202", "emmapass202"));
    }
    
    /**
     *                 5
     * @param username    
     * @param password   
     * @return true        ，false  
     */
    private static boolean validateLength(String username, String password) {
        return username.length() >= 5 && password.length() >= 5;
    }
    
    /**
     *          
     * @param username       
     * @param password      
     * @return true         ，false  
     */
    private static boolean searchUserInDatabase(String username, String password) {
        //          
        if (!userDatabase.containsKey(username)) {
            return false;
        }
        
        //         
        User user = userDatabase.get(username);
        return user.getPassword().equals(password);
    }
    
    /**
     *           
     * 1.            
     * 2.    SMOS      
     * @param username         
     */
    private static void performPostLoginActions(String username) {
        System.out.println("\n===      ===");
        System.out.println("  ，" + username + "!");
        System.out.println("          ...");
        
        //         
        displayWorkspace(username);
        
        //    SMOS      
        disconnectFromSMOS();
        
        System.out.println("\n===        ===");
    }
    
    /**
     *         （  ）
     * @param username    
     */
    private static void displayWorkspace(String username) {
        System.out.println("\n---      ---");
        System.out.println("  : " + username);
        System.out.println("1.    ");
        System.out.println("2.     ");
        System.out.println("3.   ");
        System.out.println("4.     ");
        System.out.println("5.   ");
        System.out.println("----------------");
    }
    
    /**
     *      SMOS      
     */
    private static void disconnectFromSMOS() {
        System.out.println("\n     SMOS      ...");
        System.out.println("       。");
    }
    
    /**
     *       
     *             ：
     * 1.     
     * 2.     
     * 3.        
     */
    private static void handleLogin() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("===        ===");
        System.out.print("      : ");
        String username = scanner.nextLine().trim();
        
        System.out.print("     : ");
        String password = scanner.nextLine().trim();
        
        //   1:                 5
        if (!validateLength(username, password)) {
            System.out.println("\n  :              5   ！");
            System.out.println("               。");
            return;
        }
        
        //   2:          
        if (!searchUserInDatabase(username, password)) {
            System.out.println("\n  :          ！");
            System.out.println("          。");
            return;
        }
        
        //   3:       ，       
        performPostLoginActions(username);
    }
    
    /**
     *     -      
     * @param args      
     */
    public static void main(String[] args) {
        //         
        initializeDatabase();
        
        //       
        handleLogin();
        
        System.out.println("\n    。");
    }
}