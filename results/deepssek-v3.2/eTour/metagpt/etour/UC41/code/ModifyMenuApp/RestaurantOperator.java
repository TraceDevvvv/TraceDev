import java.util.HashMap;
import java.util.Map;

/**
 * RestaurantOperator.java
 * Represents a restaurant operator with authentication capabilities.
 * This class handles operator authentication, password hashing, 
 * and manages the operator database for the restaurant menu system.
 */
public class RestaurantOperator {
    private String operatorId;
    private String name;
    private String hashedPassword;
    
    // In-memory storage for demo purposes (in production, use database)
    private static final Map<String, RestaurantOperator> OPERATOR_DATABASE = new HashMap<>();
    
    static {
        // Initialize with sample operators for demonstration
        OPERATOR_DATABASE.put("OP001", new RestaurantOperator("OP001", "John Smith", "password123"));
        OPERATOR_DATABASE.put("OP002", new RestaurantOperator("OP002", "Jane Doe", "secure456"));
        OPERATOR_DATABASE.put("ADMIN", new RestaurantOperator("ADMIN", "System Administrator", "admin123"));
    }
    
    /**
     * Private constructor for creating operator instances.
     * Used internally by the static initializer and authenticate method.
     * 
     * @param operatorId Unique operator identifier
     * @param name Operator's full name
     * @param password Plain text password (will be hashed)
     */
    private RestaurantOperator(String operatorId, String name, String password) {
        this.operatorId = operatorId;
        this.name = name;
        // In production, use proper password hashing like BCrypt or Argon2
        // For demonstration, using a simple hash
        this.hashedPassword = simpleHash(password);
    }
    
    /**
     * Returns the operator's unique ID.
     * 
     * @return Operator ID
     */
    public String getOperatorId() {
        return operatorId;
    }
    
    /**
     * Returns the operator's full name.
     * 
     * @return Operator name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Authenticates an operator using ID and password.
     * This is a static method that validates credentials against the operator database.
     * 
     * @param operatorId Operator ID to authenticate
     * @param password Plain text password to verify
     * @return Authenticated RestaurantOperator object if successful, null otherwise
     */
    public static RestaurantOperator authenticate(String operatorId, String password) {
        if (operatorId == null || password == null || operatorId.trim().isEmpty()) {
            return null;
        }
        
        RestaurantOperator operator = OPERATOR_DATABASE.get(operatorId.trim());
        if (operator != null) {
            String inputHash = simpleHash(password);
            if (inputHash.equals(operator.hashedPassword)) {
                return operator;
            }
        }
        return null;
    }
    
    /**
     * Simple hash function for demonstration purposes.
     * WARNING: In production, use proper cryptographic hashing algorithms
     * like BCrypt, Argon2, or PBKDF2 with salt.
     * 
     * @param input String to hash
     * @return Hashed representation of the input
     */
    private static String simpleHash(String input) {
        if (input == null) {
            return "0";
        }
        
        // This is a simple demonstration hash 
        // In production, never use this for real password hashing
        int hash = input.hashCode();
        
        // Simple transformation to make it slightly less trivial
        hash = hash ^ (hash >>> 16);
        
        return Integer.toHexString(hash);
    }
    
    /**
     * Checks if the operator database contains a specific operator ID.
     * Useful for validation before attempting authentication.
     * 
     * @param operatorId Operator ID to check
     * @return true if operator exists, false otherwise
     */
    public static boolean operatorExists(String operatorId) {
        return OPERATOR_DATABASE.containsKey(operatorId);
    }
    
    /**
     * Returns the number of operators in the database.
     * Useful for system monitoring and auditing.
     * 
     * @return Number of registered operators
     */
    public static int getOperatorCount() {
        return OPERATOR_DATABASE.size();
    }
    
    /**
     * Returns a string representation of the operator.
     * Includes operator ID and name for logging and display purposes.
     * 
     * @return String representation of the operator
     */
    @Override
    public String toString() {
        return "RestaurantOperator{id='" + operatorId + "', name='" + name + "'}";
    }
    
    /**
     * For demonstration purposes: adds a new operator to the database.
     * In production, this would include proper validation and secure storage.
     * 
     * @param operatorId New operator ID
     * @param name Operator name
     * @param password Plain text password
     * @return Newly created operator, or null if operatorId already exists
     */
    public static RestaurantOperator addOperator(String operatorId, String name, String password) {
        if (OPERATOR_DATABASE.containsKey(operatorId)) {
            return null;
        }
        
        RestaurantOperator newOperator = new RestaurantOperator(operatorId, name, password);
        OPERATOR_DATABASE.put(operatorId, newOperator);
        return newOperator;
    }
    
    /**
     * For demonstration purposes: removes an operator from the database.
     * 
     * @param operatorId Operator ID to remove
     * @return true if removed successfully, false if operator not found
     */
    public static boolean removeOperator(String operatorId) {
        return OPERATOR_DATABASE.remove(operatorId) != null;
    }
    
    /**
     * Clears all operators from the database (for testing/reset purposes).
     * WARNING: This method should not be exposed in production.
     */
    public static void clearAllOperators() {
        OPERATOR_DATABASE.clear();
        // Re-initialize with default operators
        OPERATOR_DATABASE.put("OP001", new RestaurantOperator("OP001", "John Smith", "password123"));
        OPERATOR_DATABASE.put("OP002", new RestaurantOperator("OP002", "Jane Doe", "secure456"));
    }
}