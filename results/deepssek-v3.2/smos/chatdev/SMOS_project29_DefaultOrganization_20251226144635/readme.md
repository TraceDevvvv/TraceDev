# EnterAbsencesAdmin Application
This Java Swing application allows administrators to record student attendance and absences. It includes functionality to select a date, mark students as present/absent, save data (simulated server), and send email notifications to parents of absent students.
## Features
- **Date Selection**: Choose from predefined dates (Today, Yesterday, or specific dates).
- **Attendance Table**: View list of students with ability to toggle attendance status.
- **Save Functionality**: Save attendance data to a simulated server.
- **Email Notifications**: Automatically sends email notifications to parents of absent students (simulated).
- **Error Handling**: Handles server connection interruptions gracefully.
## Classes
1. **main**: Entry point; launches the GUI.
2. **Student**: Represents a student with ID, name, and parent email.
3. **AttendanceGUI**: Main GUI window with all interactive components.
## Running the Application
Compile and run `main.java`. Ensure you have Java installed (Java 8 or later recommended).