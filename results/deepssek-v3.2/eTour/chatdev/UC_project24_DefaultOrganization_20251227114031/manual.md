```markdown
# ViewFeedback Application - User Manual

## Overview

The ViewFeedback application is a Java-based desktop application that implements the "ViewFeedback" use case. It allows agency operators to view all feedback for a particular site. The application simulates a real-world scenario where an agency operator needs to access feedback data for various sites after logging into the system.

### Key Features

1. **User-Friendly GUI Interface**: Built with Java Swing for intuitive navigation
2. **Site Management**: Displays a list of sites (simulating results from SearchSite use case)
3. **Feedback Viewing**: Shows detailed feedback for selected sites
4. **Connection Simulation**: Simulates real database operations with connection interruption handling
5. **Status Updates**: Real-time status notifications
6. **Secure Logout**: Proper logout procedure with confirmation

## System Requirements

### Software Requirements
- Java Development Kit (JDK) 8 or higher
- Any operating system that supports Java (Windows, macOS, Linux)

### Hardware Requirements
- Minimum: 2GB RAM, 1GHz processor
- Recommended: 4GB RAM, 2GHz processor or faster
- At least 50MB free disk space

## Installation Instructions

### Step 1: Install Java Development Kit (JDK)

1. **Windows/Mac/Linux Users**:
   - Download JDK from [Oracle's official website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
   - Run the installer and follow the installation wizard
   - Set up JAVA_HOME environment variable (optional but recommended)

2. **Verify Installation**:
   Open command prompt/terminal and type:
   ```bash
   java -version
   ```
   This should display your Java version information.

### Step 2: Download Application Files

Download all the following files into a single directory:
- `Main.java` (entry point)
- `ViewFeedbackApp.java` (main application window)
- `DatabaseSimulator.java` (database operations)
- `Site.java` (site data class)
- `Feedback.java` (feedback data class)

### Step 3: Compile the Application

1. Open command prompt/terminal in the directory containing all Java files
2. Run the following command to compile all Java files:
   ```bash
   javac *.java
   ```
   This will create `.class` files for each Java file

### Step 4: Run the Application

After successful compilation, run the application with:
```bash
java Main
```

## Application Guide

### Launching the Application

Upon running the application, you'll see the main window titled "View Feedback Application":

```
┌─────────────────────────────────────────────────────────┐
│ View Feedback Application                               │
├─────────────────────────────────────────────────────────┤
│ Status: Logged in as Agency Operator                    │
├─────────────────────────────────────────────────────────┤
│ ┌───────────────┐                ┌───────────────┐     │
│ │Sites          │                │Feedback       │     │
│ │┌────────────┐ │                │┌────────────┐ │     │
│ ││ID │Name    │ │                ││No site sel │ │     │
│ │├────────────┤ │                ││ected.      │ │     │
│ ││1  │Central │ │                ││            │ │     │
│ ││   │Park    │ │                ││            │ │     │
│ │├────────────┤ │                ││            │ │     │
│ ││2  │City    │ │                ││            │ │     │
│ ││   │Museum  │ │                ││            │ │     │
│ │└────────────┘ │                │└────────────┘ │     │
│ └───────────────┘                └───────────────┘     │
├─────────────────────────────────────────────────────────┤
│ [View Feedback]                    [Logout]             │
└─────────────────────────────────────────────────────────┘
```

### Main Interface Components

1. **Status Bar (Top)**
   - Shows current application status
   - Indicates login status: "Logged in as Agency Operator"
   - Updates with operation results

2. **Sites Panel (Left)**
   - Displays list of available sites
   - Contains columns: ID and Site Name
   - Sites are loaded automatically on startup

3. **Feedback Panel (Right)**
   - Initially displays "No site selected."
   - Shows detailed feedback when a site is selected
   - Format: Rating and comments for each feedback entry

4. **Control Buttons (Bottom)**
   - **View Feedback**: Loads feedback for selected site
   - **Logout**: Exits the application with confirmation

### How to Use the Application

#### Step 1: Select a Site
1. Click on any site in the Sites panel
2. The selected row will be highlighted

#### Step 2: View Feedback
1. With a site selected, click the "View Feedback" button
2. The application will:
   - Connect to the database simulator
   - Retrieve all feedback for the selected site
   - Display it in the Feedback panel

#### Example Output in Feedback Panel:
```
Feedback for site: Central Park (ID: 1)
========================================
Rating: 5/5
Comment: Beautiful and well-maintained.
----------------------------------------
Rating: 3/5
Comment: Needs more benches.
----------------------------------------
```

#### Step 3: Log Out
1. Click the "Logout" button
2. A confirmation dialog will appear
3. Click "Yes" to exit the application, or "No" to cancel

### Error Handling

#### Connection Interruptions
- **Description**: The application simulates server connection interruptions with 10% probability
- **What Happens**: An error dialog will appear with the message "Connection to server interrupted."
- **Resolution**: Try clicking "View Feedback" again

#### No Site Selected
- **Scenario**: Clicking "View Feedback" without selecting a site
- **What Happens**: A warning dialog appears with "Please select a site first."
- **Resolution**: Select a site from the list and try again

#### Empty Feedback Results
- **Scenario**: Some sites may have no feedback
- **What Happens**: The Feedback panel displays "No feedback available for site: [Site Name]"
- **Resolution**: This is normal operation - not all sites have feedback

### Available Test Sites

The application comes with 5 pre-loaded test sites:

1. **Central Park** (ID: 1) - Has 2 feedback entries
2. **City Museum** (ID: 2) - Has 3 feedback entries
3. **Downtown Plaza** (ID: 3) - Has 2 feedback entries
4. **Tech Hub Office** (ID: 4) - Has 1 feedback entry
5. **Riverside Walkway** (ID: 5) - Has 2 feedback entries

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: "javac is not recognized"
**Solution**: Ensure JDK is properly installed and added to PATH environment variable

#### Issue 2: "Error: Could not find or load main class Main"
**Solution**: 
1. Make sure you're in the correct directory with all `.java` files
2. Verify compilation was successful (check for `.class` files)
3. Run from the directory containing `.class` files

#### Issue 3: GUI appears too small on high-resolution displays
**Solution**: The window size is fixed at 800x600 pixels. Future versions may include resizable windows.

#### Issue 4: Application freezes when clicking buttons
**Solution**: 
1. Ensure you have sufficient system memory
2. Wait a few seconds for operations to complete
3. Force quit and restart the application

### Reset Instructions

To reset the application to its initial state:
1. Close the application
2. Delete all `.class` files
3. Recompile with `javac *.java`
4. Restart with `java Main`

## Best Pract

1. **Select Site First**: Always select a site before clicking "View Feedback"
2. **Check Status Bar**: Monitor the status bar for operation feedback
3. **Handle Errors**: If connection errors occur, simply retry the operation
4. **Save Work**: Note that this is a simulation - real data should be saved externally

## Security Considerations

- **Authentication**: The current version assumes the user is already logged in as per the use case requirements
- **Data Security**: All data is simulated and stored in memory during application runtime
- **Session Management**: The application manages a single session with logout functionality
- **Future Enhancements**: Real implementations should include proper authentication and database security

## Use Case Implementation Details

The application implements the complete ViewFeedback use case flow:

### Entry Conditions
- ✓ Agency is logged in (simulated by default startup state)

### Flow of Events
1. ✓ User views list of sites (from SearchSite use case simulation)
2. ✓ User selects a site and activates "View Feedback" function
3. ✓ System uploads/loads site feedback selected
4. ✓ System displays all feedback regarding the site selected

### Exit Conditions
- ✓ System displays all feedback for selected site on screen
- ✓ User can view all feedback entries with ratings and comments

### Quality Requirements Met
- ✓ Complete, runnable Java program
- ✓ All necessary functions, classes, and imports included
- ✓ Comments explaining key logic in source code
- ✓ Handles connection interruptions
- ✓ Efficient design with separation of concerns
- ✓ GUI for better user experience

## Support and Contact

For issues with this application:
1. Check the Troubleshooting section above
2. Verify your Java installation
3. Ensure all files are present in the same directory

## Version Information

- **Current Version**: 1.0
- **Release Date**: Built for ChatDev demonstration
- **Java Version**: Compatible with JDK 8+
- **License**: Demonstration/Educational Use

## Future Enhancements

Planned features for future versions:
1. Resizable application window
2. Export feedback to CSV/PDF
3. Search functionality within feedback
4. Multi-language support
5. Database persistence with real database connection
6. User authentication screen
7. Site management (add/edit/delete sites)

---

**Disclaimer**: This is a demonstration application created for educational purposes. It simulates database operations and is not intended for production use with real data without proper security implementations.
```