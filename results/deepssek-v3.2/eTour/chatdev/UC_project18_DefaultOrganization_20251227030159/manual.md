```markdown
# CheckBannerNumber Application - User Manual

## Overview

CheckBannerNumber is a Java-based desktop application that simulates checking whether a refreshment point (such as those at conventions or events) can have a new banner added without exceeding its maximum allowed banner capacity. The application follows a specific use case workflow, handles edge cases, and includes server connection simulation with recovery mechanisms.

### Key Features
- **Banner Capacity Checking**: Verifies if a refreshment point has reached its banner limit
- **GUI Interface**: User-friendly graphical interface with status log
- **Server Simulation**: Simulated server interactions with random interruptions
- **State Recovery**: Ability to recover previous states after failed operations
- **Notification System**: User notifications for limit exceedance and server errors
- **Full Workflow Support**: Implements the complete use case from checking banners to state recovery

## System Requirements

### Minimum Requirements
- **Java**: Java 8 or later (JDK/JRE 1.8+)
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM
- **Storage**: 10MB free disk space

### Recommended Requirements
- **Java**: Java 11 or later
- **Operating System**: Windows 10, macOS 10.14+, or Ubuntu 18.04+
- **Memory**: 1GB RAM
- **Storage**: 50MB free disk space

## Installation

### Step 1: Install Java
1. **Check if Java is already installed**:
   ```
   java -version
   ```
   If you see version information, Java is installed. If not, proceed to Step 2.

2. **Download and Install Java**:
   - **Windows**: Download Java SE from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) and run the installer
   - **macOS**: Use Homebrew: `brew install openjdk` or download from Oracle
   - **Linux**: Use package manager:
     - Ubuntu/Debian: `sudo apt-get install openjdk-11-jdk`
     - Fedora/RHEL: `sudo yum install java-11-openjdk`

3. **Set JAVA_HOME environment variable** (optional but recommended):
   - **Windows**:
     ```
     setx JAVA_HOME "C:\Program Files\Java\jdk-11"
     ```
   - **macOS/Linux**:
     ```
     export JAVA_HOME=/usr/lib/jvm/java-11-openjdk
     ```

### Step 2: Install the Application
1. **Download the source code**:
   Save the `CheckBannerNumber.java` file to a directory of your choice.

2. **Compile the application**:
   Open a terminal/command prompt and navigate to the directory containing the file:
   ```
   javac CheckBannerNumber.java
   ```
   This will generate several `.class` files in the same directory.

3. **Run the application**:
   ```
   java CheckBannerNumber
   ```

### Alternative: Using an IDE (Recommended)
1. **Install an IDE**:
   - [IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
   - [Eclipse](https://www.eclipse.org/downloads/)
   - [NetBeans](https://netbeans.apache.org/download/index.html)

2. **Import the project**:
   - Create a new Java project
   - Copy the `CheckBannerNumber.java` file into the source folder
   - Or simply open the file and run it directly

## Quick Start Guide

### First-Time Setup
1. Ensure Java is installed and working
2. Compile the application as described above
3. Run the application using `java CheckBannerNumber`

### Understanding the Interface
When you launch the application, you'll see a window with these components:

```
┌──────────────────────────── CheckBannerNumber ───────────────────────────┐
│ [Status: Ready]                                                           │
│ [Check Banner] [Confirm Notification] [Recover State]                     │
│ ┌──────────────────────────────────────────────────────────────────────┐ │
│ │ Log Display Area                                                    │ │
│ │ - Shows all actions, notifications, and results                     │ │
│ └──────────────────────────────────────────────────────────────────────┘ │
│ Current Point: Main Convention Hall (ID: 1) - Banners: 3/5                │
└──────────────────────────────────────────────────────────────────────────┘
```

## Using the Application

### The Use Case Workflow

The application follows a 3-step workflow based on the use case specification:

#### Step 1: Check Banner Capacity
**Purpose**: Verify if a new banner can be added to the refreshment point.

**How to use**:
1. Observe the current point information at the bottom of the window
2. Click the **"Check Banner"** button
3. The system will:
   - Load data from the simulated server
   - Check current vs. maximum banners
   - Display results in the log area

**Possible outcomes**:
- **Success**: If banners < maximum, you'll be prompted to add a banner
- **Failure**: If banners ≥ maximum, a notification appears and confirm button enables
- **Server Error**: If connection fails, automatic state recovery occurs

#### Step 2: Confirm Notification Reading
**Purpose**: Acknowledge reading of error notifications (required when limit is reached).

**How to use**:
1. This button is only enabled when maximum banners are reached
2. Click **"Confirm Notification"** after reading the error message
3. This enables the recovery button for the next step

#### Step 3: Recover Previous State
**Purpose**: Return to the state before checking banners.

**How to use**:
1. Click **"Recover State"** (enabled after confirming notification)
2. System restores previous banner count
3. Ready for new banner check operations

### Demo Data

The application includes three sample refreshment points:

1. **Main Convention Hall** (ID: 1): 3 banners out of 5 maximum
2. **Outdoor Cafe** (ID: 2): 2 banners out of 3 maximum
3. **Lobby Stand** (ID: 3): 5 banners out of 5 maximum (full)

### Detailed Operation Scenarios

#### Scenario 1: Adding a Banner Successfully
1. Click **"Check Banner"**
2. System shows: "SUCCESS: Can add new banner"
3. Dialog asks if you want to add the banner
4. Click "Yes" to add, "No" to cancel
5. If added, banner count increases by 1

#### Scenario 2: Maximum Banners Reached
1. Click **"Check Banner"**
2. System shows: "FAILURE: Maximum banners reached"
3. Error notification appears
4. Click **"Confirm Notification"**
5. Click **"Recover State"** to reset
6. System is ready for new operations

#### Scenario 3: Server Connection Interruption
1. Click **"Check Banner"** or **"Add Banner"**
2. Randomly (10% chance), server connection fails
3. System automatically recovers previous state
4. Message: "ERROR: Connection to server interrupted"
5. System recovers and resets

### Application Controls

#### Buttons
- **Check Banner**: Initiates the banner capacity check
- **Confirm Notification**: Acknowledges reading of error messages
- **Recover State**: Restores previous application state

#### Status Area
- Shows current application status
- Provides guidance for next steps
- Updates in real-time during operations

#### Log Display
- Shows detailed operation logs
- Includes timestamps and operation results
- Useful for debugging and understanding workflow

#### Current Point Information
- Displays selected refreshment point details
- Shows banner count: current/maximum
- Updated after each operation

## Advanced Features

### Server Simulation
The application includes a simulated server with:
- **10% random interruption rate**: Tests recovery mechanisms
- **Data persistence**: Maintains banner counts between operations
- **Error handling**: Custom exceptions for server connectivity issues

### State Management
- **Automatic state saving**: Before each check operation
- **Manual recovery**: Via "Recover State" button
- **Server-based recovery**: Automatically reloads from server if needed

### Error Handling
The application handles several edge cases:
- **Server disconnections**: Automatic recovery
- **Maximum limit exceedance**: User notification flow
- **Invalid operations**: Prevented by button state management
- **Missing data**: Default values and error messages

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: "Java not found" error
**Solution**:
```
Check Java installation: java -version
Reinstall Java if necessary
Set JAVA_HOME environment variable
```

#### Issue 2: Compilation errors
**Solution**:
```
Ensure you're using Java 8 or later
Check the file encoding (should be UTF-8)
Try compiling in an IDE for better error messages
```

#### Issue 3: Application doesn't start
**Solution**:
```
Check if all .class files were generated
Try: java -cp . CheckBannerNumber
Run from command line to see error messages
```

#### Issue 4: GUI doesn't appear
**Solution**:
```
Check if you have Java GUI libraries installed
Try running from an IDE
Ensure you're not in headless mode
```

#### Issue 5: Buttons don't work properly
**Solution**:
```
Follow the workflow in correct order
Check log messages for guidance
Ensure you're not clicking too quickly
```

### Debug Mode
For debugging purposes, you can modify the server interruption rate:
1. Open `CheckBannerNumber.java`
2. Find `ServerSimulator` class
3. Look for `random.nextInt(100) < 10` (10% chance)
4. Change to `random.nextInt(100) < 0` to disable interruptions temporarily

## Best Pract

### For Users
1. **Follow the workflow**: Check → (Confirm if needed) → Recover
2. **Read log messages**: They provide important status information
3. **Wait for operations to complete**: Don't click buttons too quickly
4. **Save your work**: The application doesn't auto-save to disk

### For Developers
1. **Extend RefreshmentPoint class**: Add more properties as needed
2. **Modify ServerSimulator**: Connect to real databases or APIs
3. **Customize UI**: Edit `MainWindow` class for different layouts
4. **Add logging**: Integrate with log4j or similar frameworks

## Extending the Application

The application is designed to be extensible. Here are some enhancement ideas:

### Adding Database Support
Replace `ServerSimulator` with a real database connection:
- MySQL/PostgreSQL for persistent storage
- JDBC for database connectivity
- Connection pooling for performance

### Adding Multiple Points
Modify the interface to:
- Show list of all refreshment points
- Allow selection between points
- Bulk operations for multiple points

### Adding Authentication
Enhance with user management:
- Login system for operators
- Role-based access control
- Audit logging of all operations

### Export Features
Add data export capabilities:
- Export logs to CSV
- Generate reports
- Print summaries

## Technical Architecture

### Application Structure
```
CheckBannerNumber.java
├── Main Class (CheckBannerNumber)
│   └── Entry point, starts GUI
├── MainWindow (JFrame)
│   ├── UI components
│   ├── Event handlers
│   └── Business logic
├── RefreshmentPoint
│   └── Data model
├── ServerSimulator
│   └── Mock server logic
└── ServerConnectionException
    └── Custom exception
```

### Design Patterns Used
1. **MVC Pattern**: Separates UI, data, and logic
2. **Observer Pattern**: Event handling in Swing
3. **Exception Handling**: Robust error management
4. **State Pattern**: Application state management

### Dependencies
- **Java Swing**: GUI framework
- **Java AWT**: Basic windowing
- **Java Util**: Collections and utilities
- **No external dependencies**: Pure Java implementation

## Support

### Getting Help
- **Check the log messages**: Most issues are explained in the log
- **Review this manual**: Common solutions are documented here
- **Examine the source code**: Well-commented for understanding

### Reporting Issues
If you encounter bugs:
1. Note the exact error message
2. Check the log output
3. Try to reproduce the issue
4. Consider submitting a fix to the source code

### Contact
This is a demonstration application. For production use, consider:
- Adding proper logging
- Implementing database persistence
- Creating user documentation
- Adding unit tests

## FAQ

**Q: Why does the server connection fail randomly?**
A: This is a demonstration feature to test the recovery mechanism. In production, this would be replaced with real error handling.

**Q: Can I change the sample data?**
A: Yes, modify the `ServerSimulator` constructor to add different refreshment points.

**Q: Is this application production-ready?**
A: It's a demonstration application. For production use, you would need to add features like database persistence, proper logging, and user authentication.

**Q: How do I add more refreshment points?**
A: Edit the `ServerSimulator` class constructor and add more entries to the `points` HashMap.

**Q: Can I run this without a GUI?**
A: The application is designed with Swing GUI. For command-line use, you would need to create a separate console-based interface.

**Q: How do I persist data between runs?**
A: Currently data is in-memory. Add file I/O or database support for persistence.

## Conclusion

CheckBannerNumber is a complete, runnable Java application that demonstrates:
- Java GUI programming with Swing
- Use case implementation
- Error handling and state recovery
- Server interaction simulation
- Professional coding pract with comments

The application is ready to use as a demonstration tool, training example, or as a starting point for more complex banner management systems.
```