'''
Main business logic class for managing class operations.
Centralizes the core functionality.
'''
public class ClassManager {
    private DatabaseSimulator db;
    public ClassManager(DatabaseSimulator db) {
        this.db = db;
    }
    /**
     * Deletes a class and handles all related operations.
     * This is the core method implementing the DeleteClass use case.
     * 
     * @param classId The ID of the class to delete
     * @return true if successful, false otherwise
     */
    public boolean deleteClass(int classId) {
        // Validate input
        if (classId <= 0) {
            System.err.println("Invalid class ID: " + classId);
            return false;
        }
        // Check if class exists
        Class cls = db.getClassById(classId);
        if (cls == null) {
            System.err.println("Class not found with ID: " + classId);
            return false;
        }
        // Perform deletion
        boolean success = db.deleteClass(classId);
        if (success) {
            System.out.println("Successfully deleted class: " + cls.getName());
            // Postcondition: Connection to SMOS server is interrupted
            // (handled in DatabaseSimulator.deleteClass())
            return true;
        }
        return false;
    }
    /**
     * Gets all classes for display after deletion.
     * @return Array of all current classes
     */
    public Class[] getAllClasses() {
        return db.getAllClasses().toArray(new Class[0]);
    }
    /**
     * Gets a class by its ID.
     * @param id The class ID
     * @return The class or null if not found
     */
    public Class getClassDetails(int id) {
        return db.getClassById(id);
    }
}