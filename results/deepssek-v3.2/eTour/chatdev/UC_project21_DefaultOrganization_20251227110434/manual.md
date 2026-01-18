```markdown
# InsertNews System - User Manual

## Overview
The InsertNews system is a Java-based application designed for Agency Operators to insert news articles into a centralized system. The application provides a graphical user interface (GUI) for data entry, validation, and storage with support for both in-memory and persistent file storage.

## System Requirements

### Software Requirements
- Java Development Kit (JDK) 8 or higher
- Operating System: Windows, macOS, or Linux

### Hardware Requirements
- Minimum 2GB RAM
- 100MB free disk space
- Screen resolution: 1024x768 or higher

## Installation Guide

### Step 1: Install Java
1. Download and install JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
2. Verify installation by opening terminal/command prompt and typing:
   ```bash
   java -version
   ```
   You should see Java version information if installed correctly.

### Step 2: Download the Application
1. Create a new directory for the application:
   ```bash
   mkdir InsertNewsApp
   cd InsertNewsApp
   ```
2. Create a file named `Main.java` and copy the provided Java code into it

### Step 3: Compile the Application
1. Open terminal/command prompt in the application directory
2. Compile the Java file:
   ```bash
   javac Main.java
   ```
   This will create compiled class files in the same directory.

## Running the Application

### Starting the Application
Run the application using:
```bash
java Main
```

The application window will open, displaying the Insert News form.

## User Guide

### Main Interface Components

#### 1. Title Field
- **Purpose**: Enter the news headline or title
- **Location**: Top of the form, labeled "Title:"
- **Validation**: Cannot be empty
- **Character limit**: No explicit limit (handled by JTextField)

#### 2. Content Area
- **Purpose**: Enter the full news content
- **Location**: Below the title field, labeled "Content:"
- **Features**:
  - Scrollable text area
  - Automatic word wrapping
  - Line numbering (if enabled by system)
- **Validation**: Cannot be empty
- **Character limit**: No explicit limit

#### 3. Control Buttons
- **Submit Button**: Processes the news insertion
- **Cancel Button**: Cancels the current operation and closes the application

### Workflow Steps

#### Step 1: Launch the Application
- Start the application as described above
- The login requirement is assumed to be handled by the agency's authentication system (Entry condition: "The agency has logged")

#### Step 2: Enter News Data
1. Click in the **Title** field
2. Type the news headline
3. Click in the **Content** area
4. Type the full news content

#### Step 3: Submit for Validation
1. Click the **Submit** button
2. The system validates:
   - Title is not empty
   - Content is not empty
3. If validation fails:
   - Error message appears
   - Focus returns to invalid fields
   - No data is saved

#### Step 4: Confirm Insertion
1. Upon successful validation, a confirmation dialog appears:
   ```
   Are you sure you want to insert this news?
   ```
2. Options:
   - **Yes**: Proceed with insertion
   - **No**: Return to form for editing

#### Step 5: Processing and Storage
When **Yes** is selected:
1. Submit button is temporarily disabled
2. News is processed in the background
3. Storage operations occur:
   - **Primary storage**: In-memory list (immediate)
   - **Secondary storage**: `news_data.txt` file (persistent)

#### Step 6: Completion Notification
Upon successful insertion:
- Success message: "News inserted successfully."
- Form is cleared automatically
- System is ready for next news entry

## Storage Details

### File Storage
- **File name**: `news_data.txt`
- **Location**: Same directory as the application
- **Format**: Each entry includes:
  - Timestamp: `[yyyy-MM-dd HH:mm:ss]`
  - Title: News headline
  - Content: Full news text
- **Example entry**:
  ```
  [2024-01-15 14:30:45] Title: Breaking News | Content: Important announcement...
  ```

### In-Memory Storage
- Temporary storage during session
- Cleared when application exits
- Used for quick retrieval during current session

## Error Handling

### Common Error Scenarios

#### 1. Empty Fields Error
- **Message**: "Title and Content must not be empty."
- **Action**: Fill both fields and try again

#### 2. Server Connection Error (ETOUR)
- **Initial warning**: Displayed when starting application without file access
- **During write**: Operation fails and in-memory storage is rolled back
- **Message**: "Operation failed due to server connection interruption (ETOUR)."

#### 3. Storage Errors
- **File permission issues**
- **Disk full errors**
- **Network interruptions**

### Error Recovery
1. System maintains data integrity
2. Failed operations don't corrupt existing data
3. Clear error messages guide user actions

## Advanced Features

### Background Processing
- Storage operations run in separate thread
- GUI remains responsive during file writes
- Progress indicators implicitly shown through button states

### Data Safety Features
1. **Transaction-like behavior**:
   - In-memory storage first
   - File storage second
   - Rollback on failure
2. **File backup**: Manual backup of `news_data.txt` recommended
3. **Thread safety**: Synchronized access to shared resources

### Customization Options

#### Window Customization
- Close button behavior: Exits application
- Window resizing: Enabled
- Position: Centers on screen

#### Data Format Customization
Modify the `SimpleDateFormat` pattern in code to change timestamp format:
```java
// Current format: "yyyy-MM-dd HH:mm:ss"
// Change to: "dd/MM/yyyy HH:mm" for different format
```

## Troubleshooting

### Common Issues

#### Issue 1: Application Won't Start
**Solution**:
1. Verify Java installation: `java -version`
2. Ensure correct compilation: `javac Main.java`
3. Check file permissions in directory

#### Issue 2: Can't Save News
**Solution**:
1. Check disk space
2. Verify write permissions in directory
3. Ensure `news_data.txt` isn't opened by another program
4. Restart application if server connection issues persist

#### Issue 3: GUI Won't Display Properly
**Solution**:
1. Update Java to latest version
2. Check display resolution settings
3. Verify system supports Java Swing

### Log Files
- Console output shows operation status
- Error messages include timestamps
- Storage failures logged to standard error

## Best Pract

### Data Entry
1. **Title Guidelines**:
   - Keep under 100 characters
   - Use clear, descriptive language
   - Avoid special characters that might cause file issues
2. **Content Guidelines**:
   - Structure with paragraphs
   - Include important information first
   - Save draft externally for long articles

### Application Management
1. **Regular Backups**:
   ```bash
   # Backup news_data.txt regularly
   cp news_data.txt news_data_backup_$(date +%Y%m%d).txt
   ```
2. **Monitoring**:
   - Check file size growth
   - Monitor disk space
   - Review console output for errors

### Security Considerations
1. **File Security**:
   - Store `news_data.txt` in secure location
   - Set appropriate file permissions
   - Regular backup to prevent data loss
2. **Access Control**:
   - Agency authentication assumed
   - Physical access control to machine
   - Network security if file sharing enabled

## Support and Contact

### Getting Help
1. **Technical Issues**:
   - Check console error messages
   - Review system requirements
   - Verify Java installation
2. **Feature Requests**:
   - Contact system administrator
   - Submit through agency channels

### Emergency Procedures

#### Data Recovery
If `news_data.txt` is corrupted:
1. Restore from latest backup
2. If no backup, check for temporary files
3. Contact IT support for file recovery options

#### Application Crash
1. Restart application
2. Check for Java updates
3. Review recent system changes

## Appendix

### Keyboard Shortcuts
- **Tab**: Move between fields
- **Enter** (in text fields): New line
- **Ctrl/Cmd + Z**: Undo (in text fields)
- **Ctrl/Cmd + C/V**: Copy/Paste

### System Messages Reference

#### Information Messages
- "News inserted successfully." - Operation completed
- "Warning: Cannot connect to server..." - Storage limitation warning

#### Error Messages
- "Title and Content must not be empty." - Validation failure
- "Operation failed due to server connection interruption..." - Storage failure
- "Error during storage: [details]" - Specific storage error

#### Confirmation Messages
- "Are you sure you want to insert this news?" - Insertion confirmation
- "Are you sure you want to cancel the insertion?" - Cancellation confirmation

### Version Information
- **Current Version**: 1.0
- **Release Date**: January 2024
- **Technology**: Java Swing GUI
- **Developer**: ChatDev Software Company

---

**Note**: This manual is for the InsertNews system version 1.0. Features and functionality may change in future updates. Always refer to the latest documentation provided with your software distribution.

For additional support or to report bugs, please contact your system administrator or the ChatDev support team.
```