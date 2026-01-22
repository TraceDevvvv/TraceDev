import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

/**
 * User  -            
 *         ：   、  、  
 *              ，           
 */
public class User {
    private String username;
    private String password;
    private String role; //     ：ADMIN, TEACHER, STUDENT
    private boolean isLoggedIn;
    private String userId;
    
    //         
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_TEACHER = "TEACHER";
    public static final String ROLE_STUDENT = "STUDENT";
    
    //         ，      （              ）
    private static final Map<String, User> USER_DATABASE = new HashMap<>();
    private static final Map<String, Set<String>> ROLE_PERMISSIONS = new HashMap<>();
    
    //       ：           
    static {
        //         
        //        
        USER_DATABASE.put("admin123", new User("admin123", "adminPass123", ROLE_ADMIN, "U001"));
        USER_DATABASE.put("teacher1", new User("teacher1", "teacherPass1", ROLE_TEACHER, "U002"));
        USER_DATABASE.put("student1", new User("student1", "studentPass1", ROLE_STUDENT, "U003"));
        
        //      
        //      
        Set<String> adminPermissions = new HashSet<>();
        adminPermissions.add("VIEW_ALL_NOTES");
        adminPermissions.add("EDIT_ALL_NOTES");
        adminPermissions.add("DELETE_ALL_NOTES");
        adminPermissions.add("VIEW_NOTE_DETAILS");
        adminPermissions.add("MANAGE_USERS");
        ROLE_PERMISSIONS.put(ROLE_ADMIN, adminPermissions);
        
        //     
        Set<String> teacherPermissions = new HashSet<>();
        teacherPermissions.add("VIEW_OWN_NOTES");
        teacherPermissions.add("EDIT_OWN_NOTES");
        teacherPermissions.add("VIEW_NOTE_DETAILS");
        ROLE_PERMISSIONS.put(ROLE_TEACHER, teacherPermissions);
        
        //     
        Set<String> studentPermissions = new HashSet<>();
        studentPermissions.add("VIEW_OWN_NOTES");
        studentPermissions.add("VIEW_NOTE_DETAILS");
        ROLE_PERMISSIONS.put(ROLE_STUDENT, studentPermissions);
    }
    
    //       
    public User() {
        this.isLoggedIn = false;
    }
    
    /**
     *        （        ）
     * @param username    
     * @param password   
     * @param role     
     * @param userId   ID
     */
    public User(String username, String password, String role, String userId) {
        this.username = username;
        this.password = password;
        this.role = role != null ? role : ROLE_STUDENT;
        this.userId = userId;
        this.isLoggedIn = false;
    }
    
    /**
     *       
     * @param username    
     * @param password   
     * @return       User  ，    null
     */
    public static User login(String username, String password) {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            System.out.println("  ：          ");
            return null;
        }
        
        //         
        User user = USER_DATABASE.get(username.trim());
        if (user == null) {
            System.out.println("  ：      - " + username);
            return null;
        }
        
        //     
        if (!user.password.equals(password)) {
            System.out.println("  ：     ");
            return null;
        }
        
        //          ，      
        User loggedInUser = new User(user.username, user.password, user.role, user.userId);
        loggedInUser.isLoggedIn = true;
        
        System.out.println("    ！   " + user.username + " (" + user.role + ")");
        return loggedInUser;
    }
    
    /**
     *     
     */
    public void logout() {
        if (this.isLoggedIn) {
            System.out.println("   " + this.username + "    ");
            this.isLoggedIn = false;
        }
    }
    
    /**
     *             
     * @param permission     
     * @return          true，    false
     */
    public boolean hasPermission(String permission) {
        if (!isLoggedIn || role == null || permission == null) {
            return false;
        }
        
        Set<String> permissions = ROLE_PERMISSIONS.get(role);
        return permissions != null && permissions.contains(permission);
    }
    
    /**
     *             
     * @return         true，    false
     */
    public boolean isAdmin() {
        return isLoggedIn && ROLE_ADMIN.equals(role);
    }
    
    /**
     *            
     * @return        true，    false
     */
    public boolean isTeacher() {
        return isLoggedIn && ROLE_TEACHER.equals(role);
    }
    
    /**
     *            
     * @return        true，    false
     */
    public boolean isStudent() {
        return isLoggedIn && ROLE_STUDENT.equals(role);
    }
    
    // Getter Setter  
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    //   ：            ，           
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    /**
     *          
     * @return        
     */
    public String getUserInfo() {
        String loginStatus = isLoggedIn ? "   " : "   ";
        return String.format(
            "=== User Information ===\n" +
            "User ID: %s\n" +
            "Username: %s\n" +
            "Role: %s\n" +
            "Login Status: %s\n" +
            "Permissions Count: %d\n" +
            "========================",
            userId, username, role, loginStatus, 
            ROLE_PERMISSIONS.getOrDefault(role, new HashSet<>()).size()
        );
    }
    
    /**
     *      （    ）
     * @param username    
     * @param password   
     * @param role     
     * @return       User  ，    null
     */
    public static User register(String username, String password, String role) {
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            System.out.println("  ：          ");
            return null;
        }
        
        if (USER_DATABASE.containsKey(username)) {
            System.out.println("  ：       - " + username);
            return null;
        }
        
        //         
        if (!ROLE_ADMIN.equals(role) && !ROLE_TEACHER.equals(role) && !ROLE_STUDENT.equals(role)) {
            System.out.println("  ：      - " + role);
            return null;
        }
        
        //     ID
        String userId = "U" + String.format("%03d", USER_DATABASE.size() + 1);
        
        //      
        User newUser = new User(username, password, role, userId);
        
        //         
        USER_DATABASE.put(username, newUser);
        
        System.out.println("      ！   : " + username + ",   : " + role);
        return newUser;
    }
    
    /**
     *       （    ）
     * @param password   
     * @return           true，    false
     */
    public static boolean isPasswordStrong(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        
        //                  
        boolean hasDigit = false;
        boolean hasLetter = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (Character.isLetter(c)) {
                hasLetter = true;
            }
        }
        
        return hasDigit && hasLetter;
    }
    
    /**
     *         
     * @return     
     */
    public Set<String> getPermissions() {
        if (!isLoggedIn || role == null) {
            return new HashSet<>();
        }
        
        Set<String> permissions = ROLE_PERMISSIONS.get(role);
        return permissions != null ? new HashSet<>(permissions) : new HashSet<>();
    }
    
    /**
     *           ViewNoteDetails  
     *       ，             
     * @return               true
     */
    public boolean canViewNoteDetails() {
        return isAdmin() || hasPermission("VIEW_NOTE_DETAILS");
    }
    
    /**
     *       （    ）
     * @param username    
     * @param newPassword    
     * @return       true，    false
     */
    public static boolean resetPassword(String username, String newPassword) {
        User user = USER_DATABASE.get(username);
        if (user == null) {
            System.out.println("  ：      - " + username);
            return false;
        }
        
        if (!isPasswordStrong(newPassword)) {
            System.out.println("  ：      ，    6         ");
            return false;
        }
        
        user.setPassword(newPassword);
        System.out.println("      ！  : " + username);
        return true;
    }
    
    @Override
    public String toString() {
        return String.format("User[ID:%s, Username:%s, Role:%s, LoggedIn:%s]", 
                userId, username, role, isLoggedIn);
    }
    
    /**
     *         
     * @return           
     */
    public static String getUserStatistics() {
        int adminCount = 0;
        int teacherCount = 0;
        int studentCount = 0;
        
        for (User user : USER_DATABASE.values()) {
            if (ROLE_ADMIN.equals(user.role)) {
                adminCount++;
            } else if (ROLE_TEACHER.equals(user.role)) {
                teacherCount++;
            } else if (ROLE_STUDENT.equals(user.role)) {
                studentCount++;
            }
        }
        
        return String.format("User Statistics: Total=%d, Admins=%d, Teachers=%d, Students=%d",
                USER_DATABASE.size(), adminCount, teacherCount, studentCount);
    }
}