```markdown
# InsertAbsences System - User Manual

## 1. Overview

The InsertAbsences system is a Java-based desktop application designed for ATA (Administrative, Technical, and Auxiliary) staff to record student absences and automatically notify parents via email. The system provides a user-friendly graphical interface for selecting absent students and handles the complete workflow from data entry to notification delivery.

### Key Features:
- **Student Management**: View and manage students organized by class
- **Absence Recording**: Intuitive radio button interface to mark students as present/absent
- **Email Notifications**: Automatic email notifications sent to parents of absent students
- **Data Persistence**: Simulated database storage for absence records
- **Error Handling**: Robust error handling for connectivity issues and user interruptions
- **Simulation Mode**: Safe demonstration mode that simulates email sending without requiring SMTP configuration

## 2. System Requirements

### Hardware Requirements:
- **Processor**: 1 GHz or faster processor
- **Memory**: 512 MB RAM minimum (1 GB recommended)
- **Storage**: 50 MB available disk space
- **Display**: 1024×768 screen resolution or higher

### Software Requirements:
- **Operating System**: Windows 7+, macOS 10.10+, or Linux with X11
- **Java Runtime**: Java SE 8 or later (Java 11 recommended)
- **Network Connectivity**: Internet connection for email notifications (optional for simulation mode)

## 3. Installation

### Installing Java (if not already installed):

**Windows:**
1. Download Java from [Oracle's website](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/)
2. Run the installer and follow the setup wizard
3. Verify installation by opening Command Prompt and typing `java -version`

**macOS:**
1. macOS includes Java by default, but you may need to install updates
2. Run in Terminal: `java -version` to check current version
3. If needed, install from [Oracle](https://www.oracle.com/java/technologies/downloads/) or use Homebrew: `brew install openjdk`

**Linux:**
1. Ubuntu/Debian: `sudo apt update && sudo apt install default-jre`
2. Fedora/RHEL: `sudo dnf install java-11-openjdk`
3. Verify with: `java -version`

### Installing the Application:

There are three ways to install and run the InsertAbsences system:

**Method 1: Using Pre-compiled JAR (Recommended)**
1. Download the `InsertAbsences.jar` file
2. Double-click the JAR file, or run from terminal: `java -jar InsertAbsences.jar`

**Method 2: Compiling from Source**
1. Download all Java source files:
   - Main.java
   - Student.java
   - InsertAbsencesFrame.java
   - AbsenceService.java
   - DatabaseService.java
   - EmailService.java
2. Compile all files: `javac *.java`
3. Run the application: `java Main`

**Method 3: Using IDE (Eclipse, IntelliJ, NetBeans)**
1. Create a new Java project
2. Add all source files to the project
3. Ensure the project uses Java 8 or higher
4. Run the Main class

## 4. Configuration

### Email Configuration (Optional - for Real Email Sending):

For production use with actual email sending (instead of simulation mode):

1. **Add JavaMail Dependencies**:
   Add these to your project's build path:
   ```
   javax.mail:javax.mail-api:1.6.2
   com.sun.mail:javax.mail:1.6.2
   ```

2. **Configure System Properties**:
   Set these properties when running the application:
   ```bash
   java -Dsmtp.host=smtp.your-email-provider.com \
        -Dsmtp.port=587 \
        -Dsmtp.username=your-email@example.com \
        -Dsmtp.password=your-email-password \
        -jar InsertAbsences.jar
   ```

3. **Supported Email Providers**:
   - Gmail: `smtp.gmail.com` (port 587, requires App Password)
   - Outlook: `smtp.office365.com` (port 587)
   - Yahoo: `smtp.mail.yahoo.com` (port 587)

### Default Settings:
- **Simulation Mode**: Enabled by default (no email setup required)
- **Sample Data**: Pre-loaded with dummy student data
- **Default Class**: "Class A" is selected on startup

## 5. Getting Started

### First Launch:
1. **Launch the Application**: Double-click the JAR file or run from command line
2. **Main Window**: The application opens with the main absence entry screen
3. **Student List**: You'll see a list of sample students with their parent email addresses
4. **Default Status**: All students are initially marked as "Present"

### Main Interface Elements:

```
┌─────────────────────────────────────────────────────────────┐
│  Insert Absences - ATA Staff System                        [×]│
├─────────────────────────────────────────────────────────────┤
│  Insert Absences - Class A (Header)                         │
│                                                             │
│  [Instructions Panel]                                       │
│  Select students who are absent...                          │
│                                                             │
│  ┌─ Students - Select Absences  ──────────────────────────┐ │
│  │                                                        │ │
│  │  John Doe                                             │ │
│  │  Parent Email: john.doe.parent@example.com             │ │
│  │                                       (●)Present ( )Absent││
│  │                                                        │ │
│  │  Jane Smith                                           │ │
│  │  Parent Email: jane.smith.parent@example.com           │ │
│  │                                       (●)Present ( )Absent││
│  │                                                        │ │
│  │  [Scrollable Area - 5 students total]                 │ │
│  │                                                        │ │
│  └────────────────────────────────────────────────────────┘ │
│                                                             │
│   ┌─────────────────────────────────────────────────────────┐│
│  │                         [Cancel] [Save]                 ││
│  └─────────────────────────────────────────────────────────┘│
└─────────────────────────────────────────────────────────────┘
```

## 6. Using the Application

### Recording Absences - Step by Step:

**Step 1: Review Student List**
- The main window displays all students in the selected class
- Each student entry shows:
  - Student name
  - Parent email address
  - Two radio buttons: Present (selected by default) and Absent

**Step 2: Select Absent Students**
1. Locate the student who is absent
2. Click the **"Absent"** radio button next to that student's name
3. The student's status changes from present to absent
4. You can select multiple absent students as needed

**Step 3: Save Absences**
1. Click the **"Save"** button at the bottom right
2. A confirmation dialog appears if no students are marked as absent
3. Click "Yes" to save anyway, or "No" to make changes

**Step 4: Processing and Notifications**
1. A progress dialog appears: "Saving absence data and sending notifications..."
2. The system performs these actions:
   - Saves absence data to the simulated database
   - Sends email notifications to parents of absent students
   - (In simulation mode, displays notification content in console)

**Step 5: Confirmation and Reset**
1. Success message: "Absence data has been saved successfully. Email notifications have been sent to parents."
2. The form automatically resets:
   - All students return to "Present" status
   - The interface refreshes for the next entry session

### Additional Functions:

**Cancel/Reset:**
- Click **"Cancel"** button to reset all selections to default (all present)
- Useful for starting over without saving

**Window Close/Exit:**
- Click the window close button (X) to exit
- Confirmation dialog: "Are you sure you want to cancel? Any unsaved changes will be lost."
- Choose "Yes" to exit, "No" to continue working

**Simulation vs Real Email Mode:**
- **Simulation Mode (Default)**: Shows email content in console, no actual emails sent
- **Real Email Mode**: Requires SMTP configuration, sends actual emails to parents

## 7. Sample Data

The application includes sample student data for two classes:

**Class A Students:**
1. John Doe - john.doe.parent@example.com
2. Jane Smith - jane.smith.parent@example.com
3. Bob Johnson - bob.johnson.parent@example.com
4. Alice Williams - alice.williams.parent@example.com
5. Charlie Brown - charlie.brown.parent@example.com

**Class B Students:**
1. David Miller - david.miller.parent@example.com
2. Emma Davis - emma.davis.parent@example.com
3. Frank Wilson - frank.wilson.parent@example.com
4. Grace Moore - grace.moore.parent@example.com
5. Henry Taylor - henry.taylor.parent@example.com

## 8. Error Handling

### Common Error Scenarios:

**No Internet Connection:**
- **Symptom**: "Connection to the SMOS server was interrupted" error message
- **Solution**: Check network connection and try again
- **Note**: This is simulated; the application will work offline in simulation mode

**Email Sending Failures:**
- **In Simulation Mode**: Email content appears in console only
- **In Real Mode**: Check SMTP configuration and credentials
- **Error Message**: "Failed to send email notification" with details

**Application Crashes:**
- **Recovery**: Simply restart the application
- **Data**: No data is permanently stored in simulation mode

**User Interruption:**
- If you cancel during save operation, no data is saved
- Confirmation dialog prevents accidental cancellation

## 9. Troubleshooting

### Common Issues and Solutions:

**1. Application Won't Start:**
```
Error: Could not find or load main class Main
```
- **Solution**: Ensure all Java files are compiled and in the same directory
- **Verify**: Run `java -version` to check Java installation

**2. GUI Looks Odd or Wrong Size:**
- **Solution**: The application uses system look and feel; some Linux systems may need GTK
- **Workaround**: Run with default Swing look: No action needed - falls back automatically

**3. Email Notifications Not Working in Real Mode:**
```
JavaMail API not found
```
- **Solution**: Add JavaMail JAR files to classpath
- **Alternative**: Use simulation mode (default)

**4. Slow Performance:**
- **Cause**: Simulated delays for database and email operations
- **Solution**: Delays are minimal (300ms-1000ms) for realistic simulation

**5. Cannot Select Multiple Absences:**
- **Note**: This is by design - use checkboxes for multiple selections
- **Current Design**: Radio buttons allow only Present/Absent per student

### Console Output (for Debugging):

When running the application, monitor console output for:
- **Database Operations**: Shows which students are marked absent/present
- **Email Notifications**: Displays email content in simulation mode
- **Error Messages**: Detailed error information for troubleshooting

## 10. Security Considerations

### Data Privacy:
- **Student Information**: Sample data uses fictional names and emails
- **Production Use**: Replace with actual student data in production
- **Email Content**: Includes student name and class information

### Email Security:
- **Credentials**: Never hardcode SMTP credentials in source code
- **Configuration**: Use system properties or environment variables
- **TLS/SSL**: Real email implementation uses STARTTLS for encryption

### Application Security:
- **No Authentication**: This demo version doesn't include login (as per use case preconditions)
- **Production Enhancement**: Add user authentication for ATA staff only
- **Data Validation**: All inputs are handled safely within the application

## 11. Extending the Application

### For Developers:

**Adding Real Database:**
1. Replace `DatabaseService.saveAbsences()` with actual JDBC code
2. Implement connection pooling for production use
3. Add proper transaction management

**Adding Authentication:**
1. Create login screen before main application
2. Implement user roles (ATA staff only)
3. Add session management

**Adding Multiple Classes:**
1. Modify `AbsenceService.getStudentsByClass()` to query real database
2. Add class selection dropdown to GUI
3. Implement pagination for large class sizes

**Enhancing Email:**
1. Add HTML email templates
2. Implement email queuing for bulk sending
3. Add email delivery tracking

### Code Structure:
```
Main.java                # Application entry point
Student.java             # Data model for students
InsertAbsencesFrame.java # Main GUI (MVC View)
AbsenceService.java      # Business logic (MVC Controller)
DatabaseService.java     # Data persistence layer
EmailService.java        # Email notification service
```

## 12. Best Pract

### For ATA Staff Users:
1. **Verify Selections**: Double-check absent students before saving
2. **Regular Saving**: Save frequently to avoid data loss
3. **Email Verification**: Check console/sent folder for email confirmations
4. **Close Properly**: Always use the close button with confirmation

### For System Administrators:
1. **Backup Configuration**: Save SMTP settings separately
2. **Monitor Logs**: Check console output for errors
3. **Update Regularly**: Keep Java and dependencies updated
4. **Test Periodically**: Verify email functionality regularly

## 13. Support and Contact

### Getting Help:
- **Documentation**: Refer to this manual first
- **Console Output**: Check for error messages and details
- **Sample Data**: Use provided sample students for testing

### System Limitations:
- **Demo Purpose**: Intended for demonstration and learning
- **No Persistent Storage**: Data resets on application restart (in simulation)
- **Single Class**: Currently shows only one class at a time

### Feedback and Improvements:
This is a demonstration application. For production use:
1. Add proper database integration
2. Implement user authentication
3. Add reporting features
4. Enhance error recovery mechanisms

## 14. License and Credits

### License:
This demonstration software is provided for educational purposes. Modify and use as needed for learning Java GUI application development.

### Credits:
- **Framework**: Java Swing for GUI
- **Design Pattern**: MVC (Model-View-Controller)
- **Simulation**: Simulated serv for database and email
- **Use Case**: Based on school administration requirements

### Version Information:
- **Current Version**: 1.0 (Demonstration)
- **Last Updated**: [Current Date]
- **Java Version**: Compatible with Java 8+

---

**Note**: This application is a complete, runnable demonstration. It simulates real-world operations while maintaining safety (no real emails sent by default, no database required). Perfect for understanding the complete workflow of a school administration system.
```