Student Monitoring System - Direction
-------------------------------------
This Java application provides a GUI for the Direction actor to monitor students
based on notes and absences thresholds.
Requirements:
- Java 8 or higher
- No external libraries required; uses standard Swing for GUI.
How to run:
1. Ensure all .java files (Main.java, Student.java, StudentDataService.java) are in the same directory.
2. Compile all Java files:
   javac *.java
3. Run the main class:
   java Main
Usage:
1. Launch the application.
2. Enter integer thresholds for notes and absences in the text fields.
3. Click "Search Students".
4. The table will update with students whose notes and absences counts exceed both thresholds.
5. If the simulated SMOS server is disconnected, an error message will appear.
Notes:
- The data is dummy data generated within the application for demonstration.
- In a real system, the StudentDataService would connect to a real database or web service.
- The GUI is built with Swing and is thread-safe.
Edge cases handled:
- Non-integer input in threshold fields.
- Negative thresholds.
- Simulated server disconnection.
- Empty result set.
Implementation aligns with the use case "PerformStudentMonitoring":
 interesting:Preconditions: User logged in as Direction (simulated by launching app)
- Event sequence: 
  1. Look for students with absences and notes superior to threshold
  2. Display recovered information
- Postconditions: Information collected is displayed; connection to SMOS server is simulated