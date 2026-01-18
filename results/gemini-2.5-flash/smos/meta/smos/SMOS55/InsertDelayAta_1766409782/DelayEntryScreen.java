import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Simulates a UI screen for ATA staff to register student delays.
 * This screen allows staff to view students, mark them as delayed,
 * specify delay times, and then confirm or interrupt the operation.
 * It interacts with ClassData to get student information and with
 * ServerProxy to send the delay data.
 */
public class DelayEntryScreen {
    private ClassData classData;
    private ServerProxy serverProxy;
    private Scanner scanner;
    private boolean userLoggedIn; // Precondition: User must be logged in
    private boolean classSelected; // Precondition: User has selected a class

    /**
     * Constructs a new DelayEntryScreen.
     *
     * @param classData The ClassData object containing the students for the selected class.
     * @param serverProxy The ServerProxy object to handle communication with the server.
     */
    public DelayEntryScreen(ClassData classData, ServerProxy serverProxy) {
        if (classData == null) {
            throw new IllegalArgumentException("ClassData cannot be null.");
        }
        if (serverProxy == null) {
            throw new IllegalArgumentException("ServerProxy cannot be null.");
        }
        this.classData = classData;
        this.serverProxy = serverProxy;
        this.scanner = new Scanner(System.in);
        // Assume preconditions are met when this screen is instantiated
        this.userLoggedIn = true;
        this.classSelected = true;
    }

    /**
     * Displays the delay entry screen and handles user interactions.
     * This method simulates the event sequence described in the use case.
     *
     * @return true if delay data was successfully sent, false if interrupted or failed.
     */
    public boolean displayScreen() {
        if (!userLoggedIn || !classSelected) {
            System.out.println("Preconditions not met: User not logged in or class not selected.");
            return false;
        }

        System.out.println("\n--- Insert Delay Data for Class: " + classData.getClassName() + " ---");
        System.out.println("Welcome, ATA Staff. Please register student delays.");

        // Store initial state to revert if user interrupts
        List<Student> initialStudentStates = new ArrayList<>();
        for (Student student : classData.getStudents()) {
            // Create a copy or store relevant state (ID, initial delay status, initial delay minutes)
            // For simplicity, we'll just store the current state and reset later.
            // In a real application, deep copies or Memento pattern might be used.
            initialStudentStates.add(new Student(student.getStudentId(), student.getStudentName()));
            initialStudentStates.get(initialStudentStates.size() - 1).setDelayed(student.isDelayed());
            initialStudentStates.get(initialStudentStates.size() - 1).setDelayMinutes(student.getDelayMinutes());
        }


        while (true) {
            // 1. The system shows a screen with all the students present, alongside a "delay" checkbox.
            //    If selected you activate Select fields to the side to select the time of the delay
            printStudentList();

            System.out.println("\n--- Options ---");
            System.out.println("  [student ID] [delay minutes] - Mark student as delayed with specified minutes (e.g., S001 15)");
            System.out.println("  [student ID] 0              - Unmark student as delayed (e.g., S001 0)");
            System.out.println("  C                           - Confirm and send data to server");
            System.out.println("  I                           - Interrupt operations (discard changes, disconnect server)");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("C")) {
                // 3. The user clicks "Confirm"
                // 4. The system sends data to the server.
                boolean success = sendDelayData();
                if (success) {
                    System.out.println("Postcondition: Delay data has been entered in the system.");
                    System.out.println("Postcondition: The initial screen is shown again (returning to main menu).");
                } else {
                    System.out.println("Failed to send delay data. Please try again.");
                    // If sending fails, we might want to allow the user to retry or interrupt.
                    // For this simulation, we'll just return false.
                }
                return success; // Exit screen after confirmation attempt
            } else if (input.equalsIgnoreCase("I")) {
                // The user interrupts the operations.
                System.out.println("User interrupted operations. Discarding changes.");
                // Postcondition: Connection to the interrupted SMOS server.
                serverProxy.disconnect();
                // Reset student states to what they were before this screen was displayed
                resetStudentStates(initialStudentStates);
                System.out.println("Postcondition: The initial screen is shown again (returning to main menu).");
                return false; // Indicate interruption
            } else {
                // 2. The user selects delays (simulated by entering student ID and minutes)
                processStudentDelayInput(input);
            }
        }
    }

    /**
     * Prints the current list of students with their delay status and minutes.
     */
    private void printStudentList() {
        System.out.println("\nStudents in " + classData.getClassName() + ":");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-10s %-20s %-10s %-10s\n", "ID", "Name", "Delayed?", "Minutes");
        System.out.println("-------------------------------------------------");
        for (Student student : classData.getStudents()) {
            String delayedStatus = student.isDelayed() ? "YES" : "NO";
            String delayMinutes = student.isDelayed() ? String.valueOf(student.getDelayMinutes()) : "-";
            System.out.printf("%-10s %-20s %-10s %-10s\n",
                              student.getStudentId(), student.getStudentName(), delayedStatus, delayMinutes);
        }
        System.out.println("-------------------------------------------------");
    }

    /**
     * Processes user input for marking/unmarking students as delayed.
     *
     * @param input The user's input string.
     */
    private void processStudentDelayInput(String input) {
        String[] parts = input.split(" ");
        if (parts.length == 2) {
            String studentId = parts[0];
            try {
                int delayMinutes = Integer.parseInt(parts[1]);
                if (delayMinutes < 0) {
                    System.err.println("Error: Delay minutes cannot be negative. Please enter a non-negative number.");
                    return;
                }

                Student targetStudent = null;
                for (Student student : classData.getStudents()) {
                    if (student.getStudentId().equalsIgnoreCase(studentId)) {
                        targetStudent = student;
                        break;
                    }
                }

                if (targetStudent != null) {
                    if (delayMinutes > 0) {
                        targetStudent.setDelayed(true);
                        targetStudent.setDelayMinutes(delayMinutes);
                        System.out.println("Student " + targetStudent.getStudentName() + " (" + studentId + ") marked as delayed for " + delayMinutes + " minutes.");
                    } else { // delayMinutes == 0
                        targetStudent.setDelayed(false);
                        targetStudent.setDelayMinutes(0); // Ensure minutes are reset
                        System.out.println("Student " + targetStudent.getStudentName() + " (" + studentId + ") unmarked as delayed.");
                    }
                } else {
                    System.err.println("Error: Student with ID '" + studentId + "' not found.");
                }
            } catch (NumberFormatException e) {
                System.err.println("Error: Invalid delay minutes. Please enter a number.");
            }
        } else {
            System.err.println("Invalid input format. Please use '[student ID] [delay minutes]' or 'C' or 'I'.");
        }
    }

    /**
     * Sends the delay data for all currently delayed students to the server via ServerProxy.
     *
     * @return true if the data was successfully sent, false otherwise.
     */
    private boolean sendDelayData() {
        List<Student> delayedStudents = new ArrayList<>();
        for (Student student : classData.getStudents()) {
            if (student.isDelayed()) {
                delayedStudents.add(student);
            }
        }

        if (delayedStudents.isEmpty()) {
            System.out.println("No students marked as delayed. Sending no delay data.");
            return true; // Consider it a success if nothing needs to be sent
        }

        System.out.println("Attempting to send delay data for " + delayedStudents.size() + " students...");
        boolean success = serverProxy.sendDelayData(delayedStudents);

        if (success) {
            System.out.println("Delay data sent successfully.");
            // After successful submission, reset delay status for all students
            // as the data has been "processed" and the screen is "reset" for new entries.
            for (Student student : classData.getStudents()) {
                student.setDelayed(false);
                student.setDelayMinutes(0);
            }
        } else {
            System.err.println("Failed to send delay data to the server.");
        }
        return success;
    }

    /**
     * Resets the delay status and minutes for all students in the class
     * to their initial states. This is called when the user interrupts.
     *
     * @param initialStates A list of Student objects representing the state before modifications.
     */
    private void resetStudentStates(List<Student> initialStates) {
        for (Student currentStudent : classData.getStudents()) {
            for (Student initialStateStudent : initialStates) {
                if (currentStudent.getStudentId().equals(initialStateStudent.getStudentId())) {
                    currentStudent.setDelayed(initialStateStudent.isDelayed());
                    currentStudent.setDelayMinutes(initialStateStudent.getDelayMinutes());
                    break;
                }
            }
        }
        System.out.println("All student delay statuses have been reset.");
    }
}