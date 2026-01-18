```markdown
# InsertFeedback System - User Manual

## Overview
The InsertFeedback System is a Java-based application designed for tourists to provide feedback on visited sites. The system ensures data integrity, handles edge cases, and simulates real-world scenarios such as server connectivity issues and user cancellations.

## Main Features
1. **Feedback Submission**: Allows tourists to submit feedback (vote 1-5 and comment) for a specific site.
2. **Duplicate Prevention**: Prevents multiple feedback submissions for the same site by the same tourist.
3. **Data Validation**: Validates input data (vote range, comment length) before submission.
4. **Server Connectivity Simulation**: Simulates connection to the ETOUR server and handles interruptions gracefully.
5. **Visited Sites Tracking**: Automatically records visited sites after feedback submission.
6. **User Cancellation Support**: Allows tourists to cancel the operation at any point.

## System Architecture
The application consists of the following key components:
- **Main.java**: Main entry point and user interaction logic.
- **Feedback.java**: Represents a feedback entry with validation.
- **Tourist.java**: Represents a tourist user.
- **Site.java**: Represents a tourist attraction site.
- **DatabaseManager.java**: Manages in-memory storage for feedbacks and visited sites.

## Prerequisites

### Required Software
1. **Java Development Kit (JDK)** version 8 or higher
2. **Text Editor or IDE** (e.g., IntelliJ IDEA, Eclipse, VS Code, or any plain text editor)

### System Requirements
- Minimum 2GB RAM
- 500MB free disk space
- Operating System: Windows 7+, macOS 10.10+, or Linux (any modern distribution)

## Installation Guide

### Step 1: Install Java
1. **Windows/MacOS**: Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK from [AdoptOpenJDK](https://adoptopenjdk.net/).
2. **Linux**: Use your package manager:
   ```bash
   # Ubuntu/Debian
   sudo apt update
   sudo apt install openjdk-11-jdk
   
   # Fedora/RHEL
   sudo dnf install java-11-openjdk-devel
   ```

3. Verify installation:
   ```bash
   java -version
   javac -version
   ```

### Step 2: Download and Prepare Source Files
1. Create a new directory for the project:
   ```bash
   mkdir InsertFeedbackSystem
   cd InsertFeedbackSystem
   ```

2. Create the following Java files in the project directory:
   - `Main.java`
   - `Feedback.java`
   - `Tourist.java`
   - `Site.java`
   - `DatabaseManager.java`

3. Copy the source code provided in the initial documentation into their respective files.

### Step 3: Verify File Structure
Your project directory should look like this:
```
InsertFeedbackSystem/
├── Main.java
├── Feedback.java
├── Tourist.java
├── Site.java
└── DatabaseManager.java
```

## How to Use

### Running the Application

#### Method 1: Using Command Line
1. Compile all Java files:
   ```bash
   javac *.java
   ```

2. Run the application:
   ```bash
   java Main
   ```

#### Method 2: Using an IDE
1. Open your IDE and create a new Java project.
2. Import the five Java files into the project's source folder.
3. Run the `Main` class.

### User Interaction Flow

#### Step 1: Application Startup
```
Welcome to the Feedback System!
Tourist: John Doe
Current Site: Eiffel Tower
```

#### Step 2: Activation
```
Activating feedback feature...
Do you want to proceed? (yes/no):
```
- Enter `yes` to continue or `no` to cancel.
- Type `cancel` at any point to exit the operation.

#### Step 3: Duplicate Check
The system checks if the tourist has already submitted feedback for this site. If yes, the process terminates with an appropriate message.

#### Step 4: Feedback Form
```
=== Feedback Form for Eiffel Tower ===
Enter vote (1-5, where 5 is best):
```
- Enter a number between 1 and 5.
- Press Enter to continue.

```
Enter comment (minimum 10 characters, maximum 500):
```
- Enter your comment (minimum 10 characters).
- Comments cannot exceed 500 characters.

#### Step 5: Confirmation
```
=== Feedback Summary ===
Site: Eiffel Tower
Vote: 4/5
Comment: Amazing view from the top!

Confirm submission? (yes/no):
```
- Review your feedback.
- Enter `yes` to submit or `no` to cancel.

#### Step 6: Success Notification
```
✓ Success: Feedback submitted for Eiffel Tower
Exit condition: The system shall notify the successful combination of feedback to the site
```

## Error Handling and Special Cases

### 1. Feedback Already Exists
If you try to submit feedback for a site you've already reviewed:
```
Error: Feedback already released for this site.
Activating use case: FeedbackAlreadyReleased.
```

### 2. Server Connection Issues
If the connection to the ETOUR server is interrupted:
```
Error: Connection to ETOUR server interrupted.
Exit condition: Interruption of the connection to the server ETOUR.
```

### 3. Validation Errors
**Invalid Vote**:
```
Error: Vote must be between 1 and 5. Enter 'cancel' to exit.
```

**Invalid Comment**:
```
Error: Comment must be at least 10 characters. Enter 'cancel' to exit.
Error: Comment cannot exceed 500 characters. Enter 'cancel' to exit.
```

### 4. Cancellation
You can cancel the operation at any time by typing `cancel` when prompted for input.

## Testing the System

### Testing Server Interruption
To test server interruption scenarios, uncomment the following line in `Main.java`:
```java
// Uncomment to test server interruption scenario:
// simulateServerInterruption();
```

### Testing with Different Data
You can modify the initial data in `DatabaseManager.java`:

1. **Change sample data**:
   ```java
   feedbacks.add(new Feedback(1, 101, 2, 4, "Great experience at the Louvre Museum. The artwork was amazing!"));
   ```

2. **Change tourist and site information**:
   ```java
   Site currentSite = new Site(1, "Eiffel Tower");
   Tourist currentTourist = new Tourist(101, "John Doe");
   ```

## Data Management

### Data Storage
The system uses in-memory storage with the following structures:
- **Feedbacks**: Stored in a thread-safe list
- **Visited Sites**: Stored in a map (touristId → set of siteIds)

### Data Persistence
Note: The current implementation uses in-memory storage. For production use:
1. Connect to a real database (MySQL, PostgreSQL, etc.)
2. Replace `DatabaseManager` methods with actual database operations
3. Implement proper transaction management

## Troubleshooting

### Common Issues

1. **Compilation Errors**:
   - Ensure all files are in the same directory
   - Check Java version compatibility
   - Verify file names match class names exactly

2. **Runtime Errors**:
   - Check input validation requirements
   - Ensure you're entering data in the correct format
   - Verify server connection simulation settings

3. **Memory Issues**:
   - The system uses minimal memory
   - If experiencing memory issues, reduce the sample data size in `DatabaseManager`

### Debug Tips
1. Enable debugging by adding print statements in the code
2. Check console output for specific error messages
3. Verify data types and boundaries

## Best Pract

### For Regular Users
1. Always save your work before submitting lengthy comments
2. Review your feedback before confirmation
3. Keep comments concise and relevant
4. Use the cancellation feature if uncertain

### For Developers
1. Follow the existing validation patterns when extending the system
2. Maintain thread safety in database operations
3. Add comprehensive unit tests for new features
4. Document any changes to the data model

## Security Considerations
- The current system is for demonstration purposes
- In production, implement:
  - User authentication and authorization
  - Secure password storage
  - Input sanitization to prevent injection attacks
  - HTTPS for network communication
  - Regular security audits

## Support and Maintenance

### Getting Help
If you encounter issues:
1. Check this manual for troubleshooting tips
2. Review the console error messages
3. Verify your Java installation
4. Ensure all source files are correctly formatted

### Reporting Issues
For bug reports or feature requests:
1. Document the exact steps to reproduce the issue
2. Include your Java version and operating system
3. Provide any relevant error messages

## Future Enhancements
Potential improvements for future versions:
1. Database integration for persistent storage
2. Web interface for remote access
3. Mobile application version
4. Integration with mapping serv
5. Social sharing capabilities
6. Admin dashboard for site managers

## Changelog
- **Version 1.0**: Initial release with core feedback functionality
- Includes: Basic CRUD operations, validation, error handling, and cancellation support

---

**Note**: This is a simulation system for demonstrating the InsertFeedback use case. For production deployment, additional considerations such as database persistence, user authentication, and enhanced security measures should be implemented.

For more information or support, contact your system administrator or development team.
```