import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Main program to simulate the ViewOnePage use case.
 * This program demonstrates: parent login, student selection, 
 * report card viewing, and connection to SMOS server.
 */
public class ViewOnePage {
    
    /**
     * Represents a student with basic information.
     */
    static class Student {
        private final String name;
        private final int studentId;
        private final int age;
        private final String grade;
        
        public Student(String name, int studentId, int age, String grade) {
            this.name = name;
            this.studentId = studentId;
            this.age = age;
            this.grade = grade;
        }
        
        public String getName() {
            return name;
        }
        
        public int getStudentId() {
            return studentId;
        }
        
        public String getGrade() {
            return grade;
        }
        
        @Override
        public String toString() {
            return "Student: " + name + " (ID: " + studentId + ", Grade: " + grade + ")";
        }
    }
    
    /**
     * Represents a report card with subject grades.
     */
    static class ReportCard {
        private final int reportId;
        private final int studentId;
        private final String term;
        private final List<SubjectGrade> grades;
        
        public ReportCard(int reportId, int studentId, String term) {
            this.reportId = reportId;
            this.studentId = studentId;
            this.term = term;
            this.grades = new ArrayList<>();
        }
        
        public void addGrade(String subject, double score) {
            grades.add(new SubjectGrade(subject, score));
        }
        
        public int getReportId() {
            return reportId;
        }
        
        public int getStudentId() {
            return studentId;
        }
        
        public String getTerm() {
            return term;
        }
        
        public List<SubjectGrade> getGrades() {
            return new ArrayList<>(grades);
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Report Card ID: ").append(reportId)
              .append("\nStudent ID: ").append(studentId)
              .append("\nTerm: ").append(term)
              .append("\nGrades:\n");
            
            double total = 0;
            for (SubjectGrade grade : grades) {
                sb.append("  ").append(grade).append("\n");
                total += grade.getScore();
            }
            
            if (!grades.isEmpty()) {
                sb.append("Average: ").append(String.format("%.2f", total / grades.size()));
            }
            
            return sb.toString();
        }
    }
    
    /**
     * Represents a subject with its corresponding grade.
     */
    static class SubjectGrade {
        private final String subject;
        private final double score;
        
        public SubjectGrade(String subject, double score) {
            this.subject = subject;
            this.score = score;
        }
        
        public String getSubject() {
            return subject;
        }
        
        public double getScore() {
            return score;
        }
        
        @Override
        public String toString() {
            return subject + ": " + String.format("%.2f", score);
        }
    }
    
    /**
     * Simulates connection to SMOS server (Student Management and Operations System).
     */
    static class SMOSServerConnection {
        private boolean connected = false;
        
        /**
         * Connects to the SMOS server.
         * Simulates a connection that might be interrupted.
         * 
         * @return true if connection successful, false otherwise
         */
        public boolean connect() {
            System.out.println("Attempting to connect to SMOS server...");
            
            // Simulate connection attempt with potential interruption
            try {
                // Simulate network delay
                Thread.sleep(500);
                
                // Simulate connection success (80% success rate)
                if (Math.random() > 0.2) {
                    connected = true;
                    System.out.println("Successfully connected to SMOS server.");
                    return true;
                } else {
                    System.out.println("Warning: SMOS server connection was interrupted!");
                    connected = false;
                    return false;
                }
            } catch (InterruptedException e) {
                System.out.println("Connection to SMOS server was interrupted!");
                connected = false;
                return false;
            }
        }
        
        /**
         * Disconnects from SMOS server.
         */
        public void disconnect() {
            if (connected) {
                System.out.println("Disconnected from SMOS server.");
                connected = false;
            }
        }
        
        public boolean isConnected() {
            return connected;
        }
    }
    
    /**
     * Main system class that handles the ViewOnePage use case.
     */
    static class StudentReportSystem {
        private final List<Student> students;
        private final List<ReportCard> reportCards;
        private final SMOSServerConnection serverConnection;
        private Scanner scanner;
        
        public StudentReportSystem() {
            this.students = new ArrayList<>();
            this.reportCards = new ArrayList<>();
            this.serverConnection = new SMOSServerConnection();
            this.scanner = new Scanner(System.in);
            
            // Initialize sample data
            initializeSampleData();
        }
        
        /**
         * Initialize sample students and report cards for demonstration.
         */
        private void initializeSampleData() {
            // Sample students
            students.add(new Student("Alice Johnson", 1001, 10, "10th Grade"));
            students.add(new Student("Bob Smith", 1002, 11, "11th Grade"));
            students.add(new Student("Charlie Brown", 1003, 9, "9th Grade"));
            
            // Sample report cards for Alice
            ReportCard aliceReport1 = new ReportCard(2001, 1001, "Fall 2023");
            aliceReport1.addGrade("Mathematics", 95.5);
            aliceReport1.addGrade("Science", 88.0);
            aliceReport1.addGrade("English", 92.5);
            reportCards.add(aliceReport1);
            
            ReportCard aliceReport2 = new ReportCard(2002, 1001, "Spring 2024");
            aliceReport2.addGrade("Mathematics", 97.0);
            aliceReport2.addGrade("Science", 91.5);
            aliceReport2.addGrade("English", 94.0);
            reportCards.add(aliceReport2);
            
            // Sample report cards for Bob
            ReportCard bobReport = new ReportCard(2003, 1002, "Fall 2023");
            bobReport.addGrade("Mathematics", 85.0);
            bobReport.addGrade("Science", 82.5);
            bobReport.addGrade("History", 88.0);
            reportCards.add(bobReport);
            
            // Sample report cards for Charlie
            ReportCard charlieReport = new ReportCard(2004, 1003, "Fall 2023");
            charlieReport.addGrade("Mathematics", 78.5);
            charlieReport.addGrade("Science", 81.0);
            charlieReport.addGrade("Art", 95.0);
            reportCards.add(charlieReport);
        }
        
        /**
         * Simulates parent login process.
         * 
         * @return true if login successful, false otherwise
         */
        public boolean loginAsParent() {
            System.out.println("=== Parent Login ===");
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            
            System.out.print("Enter password: ");
            String password = scanner.nextLine();
            
            // Simple authentication simulation
            if (!username.isEmpty() && !password.isEmpty()) {
                System.out.println("Login successful! Welcome, " + username + ".");
                return true;
            } else {
                System.out.println("Login failed. Invalid credentials.");
                return false;
            }
        }
        
        /**
         * Simulates clicking on the "Magine" button to select a child.
         * Displays available students and lets parent select one.
         * 
         * @return the selected Student, or null if cancelled
         */
        public Student selectChild() {
            System.out.println("\n=== Select Your Child ===");
            
            if (students.isEmpty()) {
                System.out.println("No students available.");
                return null;
            }
            
            // Display available students
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i));
            }
            
            System.out.println("0. Cancel");
            System.out.print("Select a student (1-" + students.size() + "): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                if (choice == 0) {
                    System.out.println("Selection cancelled.");
                    return null;
                }
                
                if (choice >= 1 && choice <= students.size()) {
                    Student selected = students.get(choice - 1);
                    System.out.println("Selected: " + selected.getName());
                    return selected;
                } else {
                    System.out.println("Invalid selection.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                return null;
            }
        }
        
        /**
         * View report cards for a specific student.
         * 
         * @param student the student whose report cards to view
         * @return the selected ReportCard, or null if none selected
         */
        public ReportCard viewStudentReportCards(Student student) {
            if (student == null) {
                System.out.println("No student selected.");
                return null;
            }
            
            System.out.println("\n=== Report Cards for " + student.getName() + " ===");
            
            // Get all report cards for this student
            List<ReportCard> studentReports = new ArrayList<>();
            for (ReportCard report : reportCards) {
                if (report.getStudentId() == student.getStudentId()) {
                    studentReports.add(report);
                }
            }
            
            if (studentReports.isEmpty()) {
                System.out.println("No report cards available for this student.");
                return null;
            }
            
            // Display available report cards
            for (int i = 0; i < studentReports.size(); i++) {
                System.out.println((i + 1) + ". Term: " + studentReports.get(i).getTerm());
            }
            
            System.out.println("0. Back");
            System.out.print("Select a report card (1-" + studentReports.size() + "): ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                if (choice == 0) {
                    return null;
                }
                
                if (choice >= 1 && choice <= studentReports.size()) {
                    ReportCard selected = studentReports.get(choice - 1);
                    return selected;
                } else {
                    System.out.println("Invalid selection.");
                    return null;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                return null;
            }
        }
        
        /**
         * Display details of a selected report card.
         * 
         * @param reportCard the report card to display
         */
        public void displayReportCardDetails(ReportCard reportCard) {
            if (reportCard == null) {
                System.out.println("No report card selected.");
                return;
            }
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("REPORT CARD DETAILS");
            System.out.println("=".repeat(50));
            System.out.println(reportCard);
            System.out.println("=".repeat(50));
        }
        
        /**
         * Main method to run the ViewOnePage use case simulation.
         * Follows the sequence: login -> select child -> view reports -> display details.
         */
        public void run() {
            System.out.println("=== Student Report System ===");
            
            try {
                // Step 1: Parent login (Precondition)
                if (!loginAsParent()) {
                    System.out.println("Cannot proceed without login.");
                    return;
                }
                
                // Step 2: Select child (Magine button click simulation)
                Student selectedStudent = selectChild();
                if (selectedStudent == null) {
                    System.out.println("No student selected. Exiting.");
                    return;
                }
                
                // Step 3: View selected student report cards (Event 1)
                ReportCard selectedReport = viewStudentReportCards(selectedStudent);
                if (selectedReport == null) {
                    System.out.println("No report card selected. Exiting.");
                    return;
                }
                
                // Step 4: Attempt connection to SMOS server (Postcondition)
                boolean connected = serverConnection.connect();
                
                // Step 5: Display report card details (Events 2 & 3)
                displayReportCardDetails(selectedReport);
                
                // Postcondition note
                if (!connected) {
                    System.out.println("\nNote: Connection to SMOS server was interrupted.");
                    System.out.println("Report is displayed from cached data.");
                } else {
                    System.out.println("\nReport data synchronized with SMOS server.");
                }
                
                // Cleanup
                serverConnection.disconnect();
                
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                e.printStackTrace();
            } finally {
                if (scanner != null) {
                    scanner.close();
                }
            }
        }
    }
    
    /**
     * Main entry point of the program.
     * 
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        // Create and run the student report system
        StudentReportSystem system = new StudentReportSystem();
        system.run();
        
        System.out.println("\n=== Program Complete ===");
    }
}