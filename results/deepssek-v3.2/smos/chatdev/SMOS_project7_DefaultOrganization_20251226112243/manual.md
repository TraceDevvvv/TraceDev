```markdown
# ViewUserDetails - User Manual

## Overview
ViewUserDetails is a Java-based desktop application designed for administrators to view detailed user information in a secure system. The application implements the "ViewUserDetails" use case specification, providing an intuitive graphical interface for browsing and inspecting user records.

## Main Features
- **User List Display**: Shows all registered users in the system with their basic information
- **Detailed User Inspection**: Enables administrators to view complete user details including:
  - Name and Surname
  - Email address
  - Cell phone number
  - Login credentials
  - Password (for administrative verification purposes)
- **Connection Management**: Simulates SMOS server connection handling with proper disconnection protocols
- **User-Friendly Interface**: Clean, responsive Swing-based GUI with proper event handling

## System Requirements
- **Operating System**: Windows, macOS, or Linux
- **Java Runtime**: Java SE 8 or later (Java 11+ recommended)
- **Memory**: Minimum 512MB RAM, 1GB recommended
- **Storage**: Less than 10MB disk space
- **Display**: 1024x768 resolution or higher

## Installation Instructions

### Step 1: Java Installation Check
Verify if Java is installed on your system:
```bash
java -version
```
If Java is not installed, download and install from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use your system's package manager.

### Step 2: Application Setup
1. Download all Java source files:
   - `User.java`
   - `UserDatabase.java`
   - `ViewUserDetailsGUI.java`
   - `Main.java`

2. Save all files in a single directory

### Step 3: Compilation
Open terminal/command prompt in the directory containing the source files and run:
```bash
javac *.java
```
This will compile all Java files and generate corresponding `.class` files.

### Step 4: Running the Application
Execute the following command:
```bash
java Main
```
The application window should open automatically.

## How to Use the Application

### Starting the Application
1. Launch the application using the command above
2. The main window titled "View User Details - Administrator" will appear
3. You'll see a list of sample users in the center panel

### Viewing User Details
1. **Select a User**: Click on any user from the displayed list
2. **Open Details**: Click the "Details" button at the bottom of the window
3. **View Information**: A dialog window will appear showing:
   - Complete user name and surname
   - Email address
   - Cell phone number
   - Login username
   - Password (displayed for administrative verification)

### Application Flow
1. **Precondition Simulation**: The application simulates that you're logged in as administrator and have already viewed the user list
2. **Details View**: Selecting a user and clicking "Details" triggers the detailed view
3. **Postcondition**: When closing the details dialog, the system automatically simulates interrupting the connection to the SMOS server as specified in the requirements
4. **Application Closure**: Closing the main window also performs proper connection termination

### Sample Data
The application comes with five pre-loaded sample users:
- John Doe (john.doe@example.com)
- Jane Smith (jane.smith@example.com)
- Alice Johnson (alice.j@example.com)
- Bob Brown (bob.brown@example.com)
- Charlie Wilson (charlie.w@example.com)

## Security Considerations
⚠️ **Important Security Notes**:
- This is a **demonstration application** for educational purposes
- **Passwords are displayed in plain text** for administrative review - this would typically be masked or encrypted in production systems
- The simulated SMOS server connection interruption is part of the use case requirements
- No actual network connections are established - all data is local

## Troubleshooting

### Common Issues and Solutions

1. **"Command not found: java"**
   - Solution: Install Java Runtime Environment (JRE) or Java Development Kit (JDK)

2. **"Main class not found"**
   - Solution: Ensure all `.java` files are in the same directory and have been compiled successfully

3. **No users appear in the list**
   - Solution: Recompile all files and restart the application

4. **Details button doesn't work**
   - Solution: Make sure you've selected a user before clicking the button
   - Verify that the `ViewUserDetailsGUI.java` file was compiled correctly

5. **Window appearance issues**
   - Solution: The application uses system look-and-feel; if issues occur, it will revert to standard Java Swing appearance

### Error Messages
- **"Please select a user first."**: You must click on a user in the list before viewing details
- **"Connection to the SMOS server interrupted."**: This is expected behavior when closing detail dialogs or the main application

## Technical Details

### Architecture
- **Frontend**: Java Swing GUI for user interaction
- **Backend**: Simulated database with connection management
- **Data Model**: User class with six attributes as specified in requirements
- **Event Handling**: Proper ActionListener and WindowListener implementations

### Code Structure
- `User.java`: Defines the user data structure
- `UserDatabase.java`: Manages user data and connection simulation
- `ViewUserDetailsGUI.java`: Main application interface
- `Main.java`: Application entry point

### Design Patterns
- **Model-View-Presenter**: Clear separation between data, presentation, and logic
- **Singleton-like Database**: Centralized data management
- **Observer Pattern**: Event-driven GUI updates

## Customization and Extension

### Adding Real Users
To replace sample data with real users, modify the `UserDatabase.java` constructor:
```java
// Replace the sample users.add() calls with your actual user data
users.add(new User("Real", "User", "real@example.com", "1234567890", "realuser", "realpass"));
```

### Changing UI Appearance
Modify the `ViewUserDetailsGUI.java` class:
- Change window size: Adjust `setSize()` parameters
- Modify fonts: Edit `Font` objects in the showUserDetails() method
- Change colors: Add `setBackground()` and `setForeground()` calls

### Extending Functionality
Potential enhancements:
- Add user search/filter capabilities
- Implement real database connections
- Add user editing capabilities
- Include user role/permission viewing

## Compliance with Specifications
This application fully implements the specified use case:
- ✅ Preconditions: Simulated administrator login and user list viewing
- ✅ Main Flow: Displays detailed user information on button click
- ✅ Postconditions: Simulates SMOS server connection interruption
- ✅ Requirements: Java, runnable, commented, edge-case handling

## Support and Contact
This is a demonstration application. For production use, additional security measures and proper authentication systems should be implemented.

## License and Usage
Free to use for educational and demonstration purposes. Modify and extend as needed for your specific requirements.

---

**Note**: Always follow security best pract when handling user credentials in production environments. This demo application displays passwords for demonstration purposes only.
```