import java.util.*;
import java.util.stream.Collectors;

/**
 * Main class for the Assign/RemoveStudentsToAParent use case.
 * This program simulates an administrator managing student-parent associations.
 * It includes a console-based interface for demonstration purposes.
 */
public class AssignRemoveStudentsToAParent {
    
    /**
     * Represents a Student entity with basic information.
     */
    static class Student {
        private final String id;
        private final String name;
        
        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }
        
        public String getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Student student = (Student) o;
            return Objects.equals(id, student.id);
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
        
        @Override
        public String toString() {
            return "Student{id='" + id + "', name='" + name + "'}";
        }
    }
    
    /**
     * Represents a Parent entity with associated students.
     */
    static class Parent {
        private final String id;
        private final String name;
        private final Set<Student> assignedStudents;
        
        public Parent(String id, String name) {
            this.id = id;
            this.name = name;
            this.assignedStudents = new HashSet<>();
        }
        
        public String getId() {
            return id;
        }
        
        public String getName() {
            return name;
        }
        
        public Set<Student> getAssignedStudents() {
            return new HashSet<>(assignedStudents);
        }
        
        /**
         * Assigns a student to this parent if not already assigned.
         * @param student The student to assign
         * @return true if student was added, false if already assigned
         */
        public boolean assignStudent(Student student) {
            return assignedStudents.add(student);
        }
        
        /**
         * Removes a student from this parent if currently assigned.
         * @param student The student to remove
         * @return true if student was removed, false if not assigned
         */
        public boolean removeStudent(Student student) {
            return assignedStudents.remove(student);
        }
        
        /**
         * Bulk assign multiple students.
         * @param students Collection of students to assign
         * @return Number of newly assigned students
         */
        public int assignStudents(Collection<Student> students) {
            int count = 0;
            for (Student student : students) {
                if (assignedStudents.add(student)) {
                    count++;
                }
            }
            return count;
        }
        
        /**
         * Bulk remove multiple students.
         * @param students Collection of students to remove
         * @return Number of successfully removed students
         */
        public int removeStudents(Collection<Student> students) {
            int count = 0;
            for (Student student : students) {
                if (assignedStudents.remove(student)) {
                    count++;
                }
            }
            return count;
        }
        
        @Override
        public String toString() {
            return "Parent{id='" + id + "', name='" + name + "', assignedStudents=" + assignedStudents.size() + "}";
        }
    }
    
    /**
     * Represents an Administrator user with authentication.
     */
    static class Administrator {
        private final String username;
        private boolean loggedIn;
        
        public Administrator(String username) {
            this.username = username;
            this.loggedIn = false;
        }
        
        public String getUsername() {
            return username;
        }
        
        public boolean isLoggedIn() {
            return loggedIn;
        }
        
        /**
         * Simulates login process.
         * @param password Dummy password for demonstration
         * @return true if login successful
         */
        public boolean login(String password) {
            // In a real system, this would validate credentials
            loggedIn = "admin123".equals(password);
            return loggedIn;
        }
        
        public void logout() {
            loggedIn = false;
        }
    }
    
    /**
     * Main system class that orchestrates the use case.
     */
    static class StudentParentManagementSystem {
        private final Map<String, Parent> parents;
        private final Map<String, Student> students;
        private Administrator currentAdmin;
        
        public StudentParentManagementSystem() {
            this.parents = new HashMap<>();
            this.students = new HashMap<>();
            this.currentAdmin = null;
        }
        
        /**
         * Administrator login method.
         * @param username Administrator username
         * @param password Administrator password
         * @return true if login successful
         */
        public boolean administratorLogin(String username, String password) {
            Administrator admin = new Administrator(username);
            if (admin.login(password)) {
                currentAdmin = admin;
                return true;
            }
            return false;
        }
        
        /**
         * Administrator logout method.
         */
        public void administratorLogout() {
            if (currentAdmin != null) {
                currentAdmin.logout();
                currentAdmin = null;
            }
        }
        
        /**
         * Checks if an administrator is logged in.
         * @return true if administrator is logged in
         */
        public boolean isAdministratorLoggedIn() {
            return currentAdmin != null && currentAdmin.isLoggedIn();
        }
        
        /**
         * Adds a parent to the system.
         * @param parent The parent to add
         */
        public void addParent(Parent parent) {
            parents.put(parent.getId(), parent);
        }
        
        /**
         * Adds a student to the system.
         * @param student The student to add
         */
        public void addStudent(Student student) {
            students.put(student.getId(), student);
        }
        
        /**
         * Gets a parent by ID.
         * @param parentId The parent ID
         * @return The parent object or null if not found
         */
        public Parent getParent(String parentId) {
            return parents.get(parentId);
        }
        
        /**
         * Gets all available students.
         * @return List of all students
         */
        public List<Student> getAllStudents() {
            return new ArrayList<>(students.values());
        }
        
        /**
         * Gets students by their IDs.
         * @param studentIds Collection of student IDs
         * @return List of student objects
         */
        public List<Student> getStudentsByIds(Collection<String> studentIds) {
            List<Student> result = new ArrayList<>();
            for (String id : studentIds) {
                Student student = students.get(id);
                if (student != null) {
                    result.add(student);
                }
            }
            return result;
        }
        
        /**
         * Main method for assigning/removing students to/from a parent.
         * This implements the core use case logic.
         * @param parentId The parent ID
         * @param studentIdsToAssign List of student IDs to assign
         * @param studentIdsToRemove List of student IDs to remove
         * @return Operation result message
         */
        public String manageParentStudentAssociations(String parentId, 
                                                     List<String> studentIdsToAssign,
                                                     List<String> studentIdsToRemove) {
            // Check preconditions
            if (!isAdministratorLoggedIn()) {
                return "Error: Administrator must be logged in.";
            }
            
            Parent parent = getParent(parentId);
            if (parent == null) {
                return "Error: Parent not found with ID: " + parentId;
            }
            
            // Get student objects for assignment
            List<Student> studentsToAssign = getStudentsByIds(studentIdsToAssign);
            List<Student> studentsToRemove = getStudentsByIds(studentIdsToRemove);
            
            // Check for invalid student IDs
            List<String> invalidAssignIds = new ArrayList<>();
            for (String id : studentIdsToAssign) {
                if (!students.containsKey(id)) {
                    invalidAssignIds.add(id);
                }
            }
            
            List<String> invalidRemoveIds = new ArrayList<>();
            for (String id : studentIdsToRemove) {
                if (!students.containsKey(id)) {
                    invalidRemoveIds.add(id);
                }
            }
            
            // Perform assignments and removals
            int assignedCount = parent.assignStudents(studentsToAssign);
            int removedCount = parent.removeStudents(studentsToRemove);
            
            // Build result message
            StringBuilder result = new StringBuilder();
            result.append("Operation completed successfully.\n");
            result.append("Parent: ").append(parent.getName()).append(" (ID: ").append(parentId).append(")\n");
            result.append("Newly assigned students: ").append(assignedCount).append("\n");
            result.append("Removed students: ").append(removedCount).append("\n");
            
            if (!invalidAssignIds.isEmpty()) {
                result.append("Warning: Invalid student IDs for assignment: ").append(invalidAssignIds).append("\n");
            }
            
            if (!invalidRemoveIds.isEmpty()) {
                result.append("Warning: Invalid student IDs for removal: ").append(invalidRemoveIds).append("\n");
            }
            
            result.append("Total students now assigned: ").append(parent.getAssignedStudents().size());
            
            return result.toString();
        }
        
        /**
         * Displays parent details including assigned students.
         * Simulates the "viewdetTailsente" use case mentioned in preconditions.
         * @param parentId The parent ID
         * @return Formatted parent details
         */
        public String displayParentDetails(String parentId) {
            Parent parent = getParent(parentId);
            if (parent == null) {
                return "Parent not found with ID: " + parentId;
            }
            
            StringBuilder details = new StringBuilder();
            details.append("=== PARENT DETAILS ===\n");
            details.append("ID: ").append(parent.getId()).append("\n");
            details.append("Name: ").append(parent.getName()).append("\n");
            details.append("Assigned Students (").append(parent.getAssignedStudents().size()).append("):\n");
            
            if (parent.getAssignedStudents().isEmpty()) {
                details.append("  No students assigned.\n");
            } else {
                for (Student student : parent.getAssignedStudents()) {
                    details.append("  - ").append(student.getName()).append(" (ID: ").append(student.getId()).append(")\n");
                }
            }
            
            return details.toString();
        }
        
        /**
         * Displays the child management form for a parent.
         * This simulates the system displaying the form after clicking "Parentela" button.
         * @param parentId The parent ID
         * @return Formatted management form with all available students
         */
        public String displayChildManagementForm(String parentId) {
            Parent parent = getParent(parentId);
            if (parent == null) {
                return "Parent not found with ID: " + parentId;
            }
            
            Set<String> assignedStudentIds = parent.getAssignedStudents().stream()
                                                  .map(Student::getId)
                                                  .collect(Collectors.toSet());
            
            StringBuilder form = new StringBuilder();
            form.append("=== CHILD MANAGEMENT FORM ===\n");
            form.append("Parent: ").append(parent.getName()).append(" (ID: ").append(parentId).append(")\n\n");
            form.append("Available Students:\n");
            
            List<Student> allStudents = getAllStudents();
            if (allStudents.isEmpty()) {
                form.append("  No students available in the system.\n");
            } else {
                for (Student student : allStudents) {
                    String status = assignedStudentIds.contains(student.getId()) ? "[ASSIGNED]" : "[NOT ASSIGNED]";
                    form.append("  ").append(student.getId()).append(": ").append(student.getName())
                        .append(" ").append(status).append("\n");
                }
            }
            
            form.append("\nInstructions:\n");
            form.append("1. Enter student IDs to assign (comma-separated)\n");
            form.append("2. Enter student IDs to remove (comma-separated)\n");
            form.append("3. Enter 'send' to submit or 'cancel' to abort\n");
            
            return form.toString();
        }
    }
    
    /**
     * Console-based user interface for the system.
     * This simulates the administrator interaction described in the use case.
     */
    static class ConsoleInterface {
        private final Scanner scanner;
        private final StudentParentManagementSystem system;
        
        public ConsoleInterface() {
            this.scanner = new Scanner(System.in);
            this.system = new StudentParentManagementSystem();
            initializeSampleData();
        }
        
        /**
         * Initializes the system with sample data for demonstration.
         */
        private void initializeSampleData() {
            // Create sample parents
            Parent parent1 = new Parent("P001", "John Smith");
            Parent parent2 = new Parent("P002", "Maria Garcia");
            system.addParent(parent1);
            system.addParent(parent2);
            
            // Create sample students
            system.addStudent(new Student("S001", "Alice Johnson"));
            system.addStudent(new Student("S002", "Bob Williams"));
            system.addStudent(new Student("S003", "Charlie Brown"));
            system.addStudent(new Student("S004", "Diana Miller"));
            system.addStudent(new Student("S005", "Ethan Davis"));
            
            // Pre-assign some students
            parent1.assignStudent(system.getStudentsByIds(Arrays.asList("S001", "S002")).get(0));
            parent1.assignStudent(system.getStudentsByIds(Arrays.asList("S003")).get(0));
        }
        
        /**
         * Main menu loop.
         */
        public void run() {
            System.out.println("=== STUDENT-PARENT MANAGEMENT SYSTEM ===\n");
            
            // Step 1: Administrator login (simulating preconditions)
            if (!loginAdministrator()) {
                System.out.println("Login failed. Exiting system.");
                return;
            }
            
            boolean running = true;
            while (running) {
                System.out.println("\n=== MAIN MENU ===");
                System.out.println("1. View parent details");
                System.out.println("2. Manage parent-student associations");
                System.out.println("3. Logout and exit");
                System.out.print("Select option: ");
                
                String choice = scanner.nextLine().trim();
                
                switch (choice) {
                    case "1":
                        viewParentDetails();
                        break;
                    case "2":
                        manageAssociations();
                        break;
                    case "3":
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            
            // Simulate postcondition: administrator interrupts connection
            system.administratorLogout();
            System.out.println("\nAdministrator logged out. Connection to SMOS server interrupted.");
        }
        
        /**
         * Handles administrator login.
         * @return true if login successful
         */
        private boolean loginAdministrator() {
            System.out.println("=== ADMINISTRATOR LOGIN ===");
            System.out.print("Username: ");
            String username = scanner.nextLine().trim();
            System.out.print("Password: ");
            String password = scanner.nextLine().trim();
            
            boolean success = system.administratorLogin(username, password);
            if (success) {
                System.out.println("Login successful! Welcome, " + username + ".");
            } else {
                System.out.println("Login failed. Invalid credentials.");
            }
            
            return success;
        }
        
        /**
         * Handles viewing parent details (simulating "viewdetTailsente" use case).
         */
        private void viewParentDetails() {
            System.out.print("\nEnter parent ID to view details (e.g., P001): ");
            String parentId = scanner.nextLine().trim();
            
            String details = system.displayParentDetails(parentId);
            System.out.println("\n" + details);
            
            System.out.print("\nPress Enter to continue...");
            scanner.nextLine();
        }
        
        /**
         * Main method for the Assign/RemoveStudentsToAParent use case.
         * Implements the complete event sequence from the requirements.
         */
        private void manageAssociations() {
            System.out.println("\n=== MANAGE PARENT-STUDENT ASSOCIATIONS ===\n");
            
            // Get parent ID (simulating that user has already viewed parent details)
            System.out.print("Enter parent ID (must be an existing parent, e.g., P001): ");
            String parentId = scanner.nextLine().trim();
            
            // Check if parent exists
            if (system.getParent(parentId) == null) {
                System.out.println("Error: Parent not found. Please check the parent ID.");
                return;
            }
            
            // Simulate clicking "Parentela" button - system displays child management form
            System.out.println("\n" + system.displayChildManagementForm(parentId));
            
            // User selects students to assign/remove
            List<String> studentIdsToAssign = new ArrayList<>();
            List<String> studentIdsToRemove = new ArrayList<>();
            
            System.out.print("\nEnter student IDs to ASSIGN (comma-separated, or press Enter for none): ");
            String assignInput = scanner.nextLine().trim();
            if (!assignInput.isEmpty()) {
                studentIdsToAssign = Arrays.asList(assignInput.split("\\s*,\\s*"));
            }
            
            System.out.print("Enter student IDs to REMOVE (comma-separated, or press Enter for none): ");
            String removeInput = scanner.nextLine().trim();
            if (!removeInput.isEmpty()) {
                studentIdsToRemove = Arrays.asList(removeInput.split("\\s*,\\s*"));
            }
            
            // User clicks "Send" button
            System.out.print("\nType 'send' to confirm or 'cancel' to abort: ");
            String confirmation = scanner.nextLine().trim().toLowerCase();
            
            if ("send".equals(confirmation)) {
                // System assigns/removes students
                String result = system.manageParentStudentAssociations(parentId, studentIdsToAssign, studentIdsToRemove);
                System.out.println("\n" + result);
                
                // Display updated parent details
                System.out.println("\n" + system.displayParentDetails(parentId));
                
                // Postcondition: One or more children were associated or removed
                System.out.println("\nOperation completed. Students have been associated/removed as requested.");
            } else {
                System.out.println("Operation cancelled.");
            }
            
            System.out.print("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    /**
     * Main entry point of the program.
     * Creates and runs the console interface.
     */
    public static void main(String[] args) {
        try {
            ConsoleInterface console = new ConsoleInterface();
            console.run();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}