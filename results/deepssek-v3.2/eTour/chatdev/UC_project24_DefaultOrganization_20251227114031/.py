VIEWFEEDBACK APPLICATION - COMPILATION AND RUN INSTRUCTIONS
This is a Java application that implements the ViewFeedback use case.
FILES INCLUDED:
1. main.java - Entry point of the application
2. ViewFeedbackApp.java - Main GUI application window
3. DatabaseSimulator.java - Simulates database operations
4. Site.java - Data class for Site objects
5. Feedback.java - Data class for Feedback objects
6. README.txt - This file
COMPILATION INSTRUCTIONS:
Using javac compiler:
javac *.java
RUNNING THE APPLICATION:
Using java runtime:
java main
APPLICATION FEATURES:
1. GUI Interface - Uses Java Swing for user-friendly interface
2. Site Selection - Displays list of sites (simulating SearchSite use case results)
3. Feedback Viewing - Shows all feedback for selected site
4. Error Handling - Simulates connection interruption (10% chance)
5. Status Updates - Shows current application status
6. Logout Functionality - Proper logout procedure
USE CASE IMPLEMENTATION:
Entry Condition: The agency has logged (simulated as logged in by default)
Flow of Events:
  1. User views list of sites (from SearchSite use case simulation)
  2. User selects a site and activates "View Feedback" function
  3. System uploads/loads site feedback selected
  4. System displays all feedback regarding the site selected
Exit Condition: System displays all feedback for selected site
Edge Cases Handled:
- No site selected when trying to view feedback
- Connection interruption simulation
- Empty feedback results
- Proper logout confirmation
QUALITY REQUIREMENTS:
- Complete, runnable Java program
- All necessary functions, classes, and imports included
- Comments explaining key logic
- Efficient design with separation of concerns
- GUI for better user experience
This application is fully runnable and demonstrates the complete ViewFeedback use case implementation.