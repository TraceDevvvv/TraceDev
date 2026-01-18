# EnterAbsencesAdmin Application
This Java Swing application allows administrators to record student attendance and absences. It includes functionality to select a date, mark students as present/absent, save data (simulated server), and send email notifications to parents of absent students.
## Features
- **Date Selection**: Choose from predefined dates (Today, Yesterday, or specific dates).
- **Attendance Table**: View list of students with ability to toggle attendance status; absent students highlighted in red.
- **Save Functionality**: Save attendance data to a simulated server with interruption handling.
- **Email Notifications**: Automatically sends email notifications to parents of absent students (simulated in console).
- **Error Handling**: Handles SMOS server connection interruptions gracefully, saving data locally and alerting the administrator.
## Classes
1. **Main**: Entry point; launches the GUI.
2. **Student**: Represents a student with ID, name, and parent email.
3. **AttendanceGUI**: Main GUI window with all interactive components.
## Running the Application
Compile and run `Main.java`. Ensure you have Java installed (Java 8 or later recommended).
## Dependencies
- Java Swing (included in standard Java SE)
- No external libraries required for the simulated version
## Assumptions & Simplifications
- Student data is hard‑coded; a real system would load from a database.
- Server communication is simulated with a random chance of interruption to mimic the "interrupted SMOS server" scenario.
- Email notifications are printed to console; in production, integrate with an SMTP server (e.g., JavaMail API).
- Date handling is simplified for demonstration; consider using LocalDate for more robust date operations.
## Extending the Application
To adapt this for real use:
1. Replace hard‑coded student list with a database query (e.g., JDBC).
2. Implement actual HTTP/REST calls or database persistence in `sendToServer()`.
3. Integrate a JavaMail‑based email sender in `sendEmailNotification()`.
4. Add user authentication and role‑based access control (administrator login as per preconditions).
5. Implement the "SeveralTetTingloregister" use case for initial date selection.