/**
 * MainApplication class with the main method to run the complete program
 * and demonstrate the EditOfOnePage use case.
 * 
 * This program creates sample administrator and student data, handles login,
 * and executes the complete workflow as per the use case requirements.
 * It demonstrates the full functionality of editing a student's report card.
 */
public class MainApplication {
    
    /**
     * Main method - entry point of the application
     * 
     * @param args Command line arguments (not used in this application)
     */
    public static void main(String[] args) {
        System.out.println("=== Student Report Card Management System ===\n");
        
        // Create an administrator with credentials
        Administrator admin = new Administrator("admin", "admin123");
        
        // Create sample students with report cards
        System.out.println("Initializing system with sample data...");
        initializeSampleData(admin);
        
        // Administrator login (simulating the preconditions)
        System.out.println("\n--- Administrator Login ---");
        boolean loginSuccess = admin.login("admin", "admin123");
        
        if (!loginSuccess) {
            System.out.println("Login failed. Exiting system.");
            return;
        }
        
        System.out.println("Login successful! Administrator is now logged in.");
        
        // Create EditOfOnePage instance to handle the use case
        EditOfOnePage editUseCase = new EditOfOnePage(admin);
        
        // Execute the EditOfOnePage use case
        System.out.println("\n=== Executing EditOfOnePage Use Case ===\n");
        
        boolean useCaseSuccess = editUseCase.executeEditUseCase();
        
        if (useCaseSuccess) {
            System.out.println("\n✓ EditOfOnePage use case completed successfully!");
        } else {
            System.out.println("\n✗ EditOfOnePage use case failed or was cancelled.");
        }
        
        // Display final state of the system
        System.out.println("\n=== Final System State ===");
        System.out.println(admin.toString());
        displayAllStudentsWithReportCards(admin);
        
        // Logout administrator (simulating connection interruption)
        admin.logout();
        System.out.println("\nAdministrator logged out. Connection to SMOS server interrupted.");
        
        // Close resources
        editUseCase.close();
        
        System.out.println("\n=== Program Execution Complete ===");
    }
    
    /**
     * Initialize the system with sample student data
     * This simulates the system having existing data that can be edited
     * 
     * @param admin The administrator to add students to
     */
    private static void initializeSampleData(Administrator admin) {
        // Create sample report cards
        ReportCard reportCard1 = new ReportCard();
        reportCard1.addOrUpdateGrade("Mathematics", 85.5);
        reportCard1.addOrUpdateGrade("Physics", 78.0);
        reportCard1.addOrUpdateGrade("Chemistry", 92.5);
        
        ReportCard reportCard2 = new ReportCard();
        reportCard2.addOrUpdateGrade("Mathematics", 90.0);
        reportCard2.addOrUpdateGrade("English", 88.5);
        reportCard2.addOrUpdateGrade("History", 76.0);
        reportCard2.addOrUpdateGrade("Computer Science", 95.0);
        
        ReportCard reportCard3 = new ReportCard();
        reportCard3.addOrUpdateGrade("Biology", 82.0);
        reportCard3.addOrUpdateGrade("Geography", 79.5);
        
        // Create sample students
        Student student1 = new Student("S001", "John Smith", reportCard1);
        Student student2 = new Student("S002", "Emma Johnson", reportCard2);
        Student student3 = new Student("S003", "Michael Brown", reportCard3);
        
        // Add students to administrator (simulating DisplayedUnapagella)
        admin.addStudent(student1);
        admin.addStudent(student2);
        admin.addStudent(student3);
        
        System.out.println("Created " + admin.getStudentCount() + " sample students.");
    }
    
    /**
     * Display all students with their report cards
     * 
     * @param admin The administrator containing the students
     */
    private static void displayAllStudentsWithReportCards(Administrator admin) {
        System.out.println("\nAll Students in System:");
        System.out.println("=======================");
        
        for (Student student : admin.getAllStudents()) {
            System.out.println("\n" + student.toString());
        }
        System.out.println("=======================");
    }
    
    /**
     * Helper method to demonstrate the complete workflow programmatically
     * This method shows how the system could be used without user input
     * for testing purposes
     */
    private static void demonstrateProgrammaticUseCase() {
        System.out.println("\n--- Programmatic Demonstration ---");
        
        // Create a fresh administrator
        Administrator demoAdmin = new Administrator("demo", "demo123");
        demoAdmin.login("demo", "demo123");
        
        // Create a sample student
        ReportCard demoReportCard = new ReportCard();
        demoReportCard.addOrUpdateGrade("Math", 75.0);
        demoReportCard.addOrUpdateGrade("Science", 82.0);
        
        Student demoStudent = new Student("D001", "Demo Student", demoReportCard);
        demoAdmin.addStudent(demoStudent);
        
        System.out.println("Before edit:");
        System.out.println(demoStudent.toString());
        
        // Edit the report card programmatically
        java.util.Map<String, Double> newGrades = new java.util.HashMap<>();
        newGrades.put("Math", 88.0);  // Update existing grade
        newGrades.put("English", 91.0); // Add new subject
        
        boolean editSuccess = demoAdmin.editStudentReportCard("D001", newGrades);
        
        if (editSuccess) {
            System.out.println("\nAfter edit:");
            System.out.println(demoAdmin.findStudentById("D001").toString());
            System.out.println("✓ Report card edited successfully!");
        } else {
            System.out.println("✗ Failed to edit report card.");
        }
        
        demoAdmin.logout();
    }
}