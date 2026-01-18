# Tourist Account Deletion System - User Manual

## Introduction

The Tourist Account Deletion System is a Java-based GUI application designed for agency operators to manage tourist accounts in the ETOUR system. This application allows operators to search for tourists and delete their accounts with proper confirmation workflows and error handling.

### Key Features
- **Search Functionality**: Integrated search capability to find tourists by name, ID, or email
- **Account Deletion**: Secure deletion of tourist accounts with confirmation dialogs
- **Input Validation**: Comprehensive validation to prevent accidental deletions
- **Logging System**: Detailed logging for system events and debugging
- **Connection Simulation**: Simulates server connection interruptions as per ETOUR requirements

## System Requirements

### Software Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM recommended
- **Storage**: At least 50MB free disk space

### Dependencies
The application uses standard Java libraries:
- `javax.swing` for GUI components
- `java.util.logging` for logging functionality
- `java.awt` for window management

## Installation Guide

### Step 1: Install Java
1. **Download JDK**: Visit [Oracle's JDK download page](https://www.oracle.com/java/technologies/downloads/) or use OpenJDK
2. **Install JDK**: Follow the installation instructions for your operating system
3. **Set Environment Variables**:
   - **Windows**:
     - Set `JAVA_HOME` to your JDK installation path (e.g., `C:\Program Files\Java\jdk-21`)
     - Add `%JAVA_HOME%\bin` to your system PATH
   - **macOS/Linux**:
     - Set environment variables in `~/.bash_profile` or `~/.bashrc`:
       ```
       export JAVA_HOME=/usr/lib/jvm/java-21-openjdk
       export PATH=$JAVA_HOME/bin:$PATH
       ```

### Step 2: Verify Installation
1. Open a terminal/command prompt
2. Run the following command to verify Java installation:
   ```bash
   java -version
   ```
3. You should see output similar to:
   ```
   java version "21.0.1"
   Java(TM) SE Runtime Environment
   ```

### Step 3: Set Up the Application Files
1. Create a project directory:
   ```bash
   mkdir TouristDeletionSystem
   cd TouristDeletionSystem
   ```

2. Create the necessary Java files in this directory:
   - `DeleteTouristAccount.java` (Main application)
   - `SearchTourist.java` (Search functionality module)

3. Copy the provided source code into their respective files

## How to Compile and Run

### Compilation Instructions

1. **Navigate to the project directory**:
   ```bash
   cd TouristDeletionSystem
   ```

2. **Compile both Java files**:
   ```bash
   javac DeleteTouristAccount.java SearchTourist.java
   ```

3. If compilation is successful, you'll see class files created:
   - `DeleteTouristAccount.class`
   - `DeleteTouristAccount$Tourist.class`
   - `SearchTourist.class`
   - `SearchTourist$Tourist.class`

### Running the Application

1. **Start the application**:
   ```bash
   java DeleteTouristAccount
   ```

2. **Expected behavior**: A GUI window will open with the title "Tourist Account Deletion System"

## Application Usage Guide

### Main Interface Layout

The application interface consists of three main sections:

1. **Search Panel (Top)**:
   - Search field for entering search queries
   - Search button to initiate searches

2. **Tourist List (Center)**:
   - Displays all available tourists by default
   - Shows search results after performing a search
   - Allows single selection of tourists

3. **Action Panel (Bottom)**:
   - Delete button for removing selected tourist accounts

### Step-by-Step Usage

#### Step 1: Search for Tourists
1. Enter a search term in the search field
   - Search works across tourist names, IDs, and email addresses
   - Search is case-insensitive
   - Example searches: "john", "T001", "@example.com"

2. Click the "Search" button to filter the list
3. To view all tourists again, clear the search field and click "Search"

#### Step 2: Select a Tourist
1. Click on a tourist from the displayed list
2. The selected tourist will be highlighted
3. Ensure you have selected exactly one tourist before proceeding

#### Step 3: Delete Tourist Account
1. Click the "Delete Selected Account" button
2. A confirmation dialog will appear asking:
   - "Are you sure you want to delete tourist account: [Tourist ID]?"
3. Choose your action:
   - Click **"Yes"** to proceed with deletion
   - Click **"No"** to cancel the operation

### Successful Deletion Flow

1. **Confirmation**: Click "Yes" on the confirmation dialog
2. **System Actions**:
   - Tourist account is removed from the system
   - List view is updated immediately
   - Success message is displayed
   - System logs the deletion event
3. **Expected Messages**:
   - "Tourist account [ID] has been successfully deleted."
   - Warning log: "Connection to server ETOUR interrupted (simulated)"

### Cancellation Flow

1. **Cancel Deletion**: Click "No" on the confirmation dialog
2. **System Response**:
   - Operation is aborted
   - No changes are made to the system
   - Information message: "Deletion cancelled."

## Error Handling and Messages

### Common Error Messages

1. **No Tourist Selected**:
   - Message: "Please select a tourist from the list."
   - Resolution: Select a tourist from the list before clicking delete

2. **No Search Results**:
   - Message: (Displayed in list) "No tourists found."
   - Resolution: Try a different search term or clear the search

3. **Invalid Selection**:
   - Message: "No tourist selected. Please search and select a valid tourist."
   - Resolution: Ensure you select a valid tourist (not the "No tourists found" message)

4. **Deletion Errors**:
   - Message: "Error deleting tourist account. Please try again."
   - Resolution: Try the deletion again or check system logs

### Logging System

The application uses a comprehensive logging system:
- **Location**: Console output
- **Log Levels**:
  - INFO: Application startup, successful deletions
  - WARNING: Connection interruptions, unusual events
  - SEVERE: Critical errors, parsing failures

To view detailed logs, ensure the console window remains open while the application runs.

## Sample Data

The application comes with pre-loaded sample data for testing:

| Tourist ID | Name | Email |
|------------|------|-------|
| T001 | John Doe | john.doe@example.com |
| T002 | Jane Smith | jane.smith@example.com |
| T003 | Alice Johnson | alice.johnson@example.com |
| T004 | Bob Brown | bob.brown@example.com |

## Troubleshooting

### Common Issues and Solutions

1. **Application Won't Start**:
   - **Issue**: "Error: Could not find or load main class DeleteTouristAccount"
   - **Solution**: Ensure you're in the correct directory and compiled the files properly

2. **GUI Doesn't Appear**:
   - **Issue**: Application starts but no window appears
   - **Solution**: Check system logs for Java version compatibility issues

3. **Unresponsive Interface**:
   - **Issue**: Application freezes or becomes unresponsive
   - **Solution**: Check system memory usage; restart the application

4. **Search Not Working**:
   - **Issue**: Search returns no results or incorrect results
   - **Solution**: Ensure search terms match sample data format; check for typos

### Performance Tips
- Keep search results focused by using specific search terms
- Close unnecessary applications to free system resources
- Regularly check application logs for performance insights

## Security Notes

- All deletions require explicit confirmation
- Operation is logged for audit trail
- User can cancel deletions at confirmation stage
- No automatic deletions occur

## Support

For technical support or to report issues:

1. **Check Logs**: Review console output for error messages
2. **Verify Installation**: Ensure Java is properly installed and configured
3. **Documentation**: Refer to this manual for usage instructions

**Note**: This is a simulation system designed for testing purposes. Real-world integration with ETOUR would require additional network and security configurations.

## License and Copyright

This software is provided for demonstration purposes. Use in production environments requires proper testing and customization.

---

*Last Updated: November 2023*
*Version: 1.0*
*System: Tourist Account Deletion System*