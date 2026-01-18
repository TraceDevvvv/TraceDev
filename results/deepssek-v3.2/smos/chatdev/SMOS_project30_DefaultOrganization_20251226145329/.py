### Option 2: Using an IDE
1. Import all Java files into your preferred IDE (Eclipse, IntelliJ IDEA, NetBeans)
2. Run the `Main.java` file as a Java application
## Application Flow
1. **Login Screen**: Enter administrator credentials
2. **Main Application**: Upon successful authentication, the main window appears with:
   - Date selection field (pre-populated with current date)
   - Form for entering student delay information (Student ID, Name, Delay Reason)
   - Table displaying entered records
   - Log area showing system messages and server communication status
3. **Data Entry**: Fill out the form and click "Save" to:
   - Save the record locally
   - Send data to the server (simulated)
   - Send notifications to parents (simulated)
   - Update the log display
## Server Simulation
The application includes a `MockServer` class that simulates:
- Successful data transmission
- Occasional server interruptions (every 3rd request fails to simulate SMOS server issues)
- Parent notification system
## File Structure
- `Main.java` - Entry point with authentication check
- `LoginDialog.java` - Login screen for administrator authentication
- `MainFrame.java` - Main application window with all functionality
- `StudentDelay.java` - Data model class for student delay records
- `MockServer.java` - Simulated server communication
- `README.md` - This documentation file
## Troubleshooting
- If login fails, verify you're using the correct credentials (admin/admin123)
- If compilation fails, ensure all Java files are in the same directory
- If server connection fails intermittently, this is simulated behavior as per requirements