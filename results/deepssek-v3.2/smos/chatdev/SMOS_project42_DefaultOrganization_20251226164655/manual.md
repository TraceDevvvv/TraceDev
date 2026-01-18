```markdown
# Justification Management System - Admin Module

##  📋 Overview

The Justification Management System is a Java-based desktop application designed for administrators to manage employee justifications (such as leave requests, absence explanations, etc.). The system simulates a real-world scenario where administrators can view justification details and perform deletion operations with proper confirmation and error handling.

##  ✨ Main Features

### 1. **Justification Details View**
- Displays comprehensive details of a selected justification
- Shows Justification ID, Date, Reason, and Status
- Clean, organized layout for easy information review

### 2. **Delete Functionality**
- Secure deletion with confirmation dialog
- Simulates backend server communication
- Handles server interruptions gracefully
- Proper success/error feedback to users

### 3. **Connection Status Monitoring**
- Real-time display of SMOS server connection status
- Automatic status updates during operations
- Clear indication of server interruptions

### 4. **Navigation System**
- Seamless transition between screens
- Cancel operation support
- Return to registry screen after operations
- Back navigation from registry screen

### 5. **Error Handling**
- Network interruption simulation
- User-friendly error messages
- Safe operation cancellation
- Thread-safe GUI updates

## ️ Installation & Setup

### Prerequisites

#### **Java Development Kit (JDK)**
- **Required**: JDK 8 or higher
- **Recommended**: JDK 11 or JDK 17 for better performance

**Check Java Installation:**
```bash
java -version
javac -version
```

#### **Installation Steps:**

1. **Install Java (if not already installed):**
   - **Windows**: Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or use [AdoptOpenJDK](https://adoptopenjdk.net/)
   - **macOS**: Use Homebrew: `brew install openjdk@11`
   - **Linux**: Use package manager:
     ```bash
     # Ubuntu/Debian
     sudo apt-get update
     sudo apt-get install openjdk-11-jdk
     
     # CentOS/RHEL
     sudo yum install java-11-openjdk-devel
     ```

2. **Set JAVA_HOME (optional but recommended):**
   - **Windows**:
     ```powershell
     setx JAVA_HOME "C:\Program Files\Java\jdk-11"
     setx PATH "%PATH%;%JAVA_HOME%\bin"
     ```
   - **macOS/Linux**:
     ```bash
     export JAVA_HOME=$(/usr/libexec/java_home -v 11)
     export PATH=$JAVA_HOME/bin:$PATH
     ```
     Add to `~/.bashrc` or `~/.zshrc` for persistence

3. **Download or Create the Java File:**
   - Save the provided `justificationmanager.java` code to a file
   - Ensure the filename matches the public class name

##  🚀 How to Run the Application

### **Method 1: Using Command Line**

1. **Navigate to the project directory:**
   ```bash
   cd /path/to/project
   ```

2. **Compile the Java program:**
   ```bash
   javac justificationmanager.java
   ```
   *This creates a `JustificationManager.class` file*

3. **Run the application:**
   ```bash
   java JustificationManager
   ```

### **Method 2: Using an IDE (Recommended)**

#### **Visual Studio Code:**
1. Install VS Code from [code.visualstudio.com](https://code.visualstudio.com/)
2. Install Extension Pack for Java (from Microsoft)
3. Open the project folder in VS Code
4. Run using the built-in Java runner (F5)

#### **IntelliJ IDEA:**
1. Download IntelliJ IDEA Community Edition from [jetbrains.com](https://www.jetbrains.com/idea/download/)
2. Create a new Java project
3. Add the `justificationmanager.java` file
4. Run the main method

#### **Eclipse:**
1. Download Eclipse IDE for Java Developers from [eclipse.org](https://www.eclipse.org/downloads/)
2. Create a new Java project
3. Create a new class with the provided code
4. Run as Java Application

##  🎮 How to Use the Application

### **Starting the Application**
Upon launching, you'll see the main interface with:
- Top section: Connection status and screen title
- Center section: Justification details
- Bottom section: Action buttons (Delete and Cancel)

### **Understanding the Interface**

#### **1. Status Area**
- Shows "Connected to SMOS server" when operational
- Updates to "Connection to SMOS server interrupted" if server connection fails
- Located at the top of the window

#### **2. Justification Details**
Displays simulated justification information:
- **Justification ID**: Unique identifier (e.g., JUST001)
- **Date**: Request date (e.g., 2023-10-05)
- **Reason**: Description of the justification (e.g., Annual leave request)
- **Status**: Current state (e.g., Pending)

### **Performing Operations**

#### **A. Delete a Justification**
1. **Click** the "Delete" button
2. **Confirm** the deletion in the pop-up dialog:
   - Click "Yes" to proceed with deletion
   - Click "No" to cancel the operation
3. **Wait** for the operation to complete (simulated 2-second delay)
4. **Observe** the result:
   - **Success**: Green success message, application navigates to Registry Screen
   - **Failure**: Red error message, connection status updates

#### **B. Cancel an Operation**
1. **Click** the "Cancel" button
2. **View** the cancellation confirmation message
3. The application automatically navigates to the Registry Screen

### **Navigation Between Screens**

#### **From Details Screen to Registry Screen:**
- Occurs automatically after:
  - Successful deletion
  - Cancelled deletion
  - Manual cancellation
- Shows a simple registry interface with navigation option

#### **From Registry Screen Back to Details:**
1. **Click** the "Back to Justification Details" button
2. The application returns to the main justification details view

## 🔧 Technical Details

### **Simulated Network Behavior**
The application includes simulated network behavior:
- **Success Rate**: 90% (default)
- **Failure Rate**: 10% (randomly simulates server interruption)
- **Network Delay**: 2 seconds for all operations
- These values can be modified in the `performDeletion()` method

### **Thread Safety**
- GUI created on Event Dispatch Thread (EDT)
- Network operations simulated with thread sleep
- Proper exception handling for thread interruptions

### **Error Scenarios Handled**
1. **Server Connection Interruption**
   - User receives error message
   - Connection status updated
   - Operation fails gracefully

2. **Operation Cancellation**
   - User receives confirmation
   - Safe navigation to registry

3. **Thread Interruption**
   - Proper thread state handling
   - Safe termination of operations

##  Troubleshooting

### **Common Issues and Solutions:**

#### **1. "javac is not recognized"**
- Solution: Ensure JAVA_HOME is set and bin directory is in PATH
- Verify installation: `where java` (Windows) or `which java` (macOS/Linux)

#### **2. "Error: Could not find or load main class"**
- Solution: Ensure class name matches filename
- Compile first: `javac justificationmanager.java`
- Run from the same directory: `java JustificationManager`

#### **3. GUI Not Appearing or Freezing**
- Solution: Ensure running on EDT
- Verify no infinite loops in event handlers
- Check for unhandled exceptions

#### **4. Slow Performance**
- Solution: The 2-second delay is intentional simulation
- Modify `Thread.sleep(2000)` in `performDeletion()` if needed
- Reduce to 500 for testing: `Thread.sleep(500)`

### **Debug Mode**
To enable additional debugging:
1. Modify the code to add print statements:
   ```java
   System.out.println("Operation started...");
   ```
2. Recompile and run to see console output

##  📊 Simulation Customization

### **Modify Success/Failure Rate**
Edit line in `performDeletion()` method:
```java
// Change 0.1 to adjust failure probability
if (Math.random() < 0.1) {  // Currently 10% failure rate
    throw new RuntimeException("SMOS server connection interrupted");
}
```

### **Adjust Network Delay**
Edit the sleep duration:
```java
Thread.sleep(2000);  // Change 2000 to desired milliseconds
```

### **Customize Justification Data**
Modify the labels in `createAndShowGUI()`:
```java
detailsPanel.add(new JLabel("Justification ID: JUST001"));
// Change to display different data
```

##  🔒 Security Considerations

### **Current Implementation**
  - Confirmation dialogs for destructive operations
  - No actual database connection (simulated only)
  - Client-side only application

### **For Production Use**
1. **Add Authentication:**
   - Implement proper login system
   - Role-based access control

2. **Secure Data Storage:**
   - Use encrypted database connections
   - Implement proper data validation

3. **Audit Logging:**
   - Log all deletion attempts
   - Track administrator actions

##  📱 System Requirements

### **Minimum Requirements:**
- **OS**: Windows 7+, macOS 10.12+, or Linux with GUI
- **Java**: JRE 8 or higher
- **RAM**: 512 MB minimum
- **Disk Space**: 5 MB for application

### **Recommended Requirements:**
- **OS**: Windows 10, macOS 11+, or Ubuntu 20.04+
- **Java**: JRE 11 or higher
- **RAM**: 1 GB
- **Disk Space**: 10 MB
- **Screen Resolution**: 1024x768 minimum

##  📝 Best Pract

### **For Administrators:**
1. Always verify justification details before deletion
2. Use the confirmation dialog as a final check
3. Monitor connection status during critical operations
4. Cancel operations if unsure about deletion

### **For Developers:**
1. Extend the Justification class to add more fields
2. Implement actual database connectivity
3. Add unit tests for business logic
4. Consider adding undo functionality

##  🔄 Future Enhancements

### **Planned Features:**
1. **Actual Database Integration**
   - MySQL/PostgreSQL connectivity
   - ORM implementation (Hibernate/JPA)

2. **Extended Functionality**
   - Create new justifications
   - Edit existing justifications
   - Search and filter capabilities

3. **Enhanced UI**
   - Multi-language support
   - Theme customization
   - Responsive design

4. **Reporting**
   - Export to PDF/Excel
   - Statistical reports
   - Audit trails

## 📚 Additional Resources

### **Learning Java GUI Programming:**
- [Oracle Java Tutorials - Swing](https://docs.oracle.com/javase/tutorial/uiswing/)
- [Java Swing Documentation](https://docs.oracle.com/javase/8/docs/api/javax/swing/package-summary.html)

### **Troubleshooting Resources:**
- [Stack Overflow Java Tag](https://stackoverflow.com/questions/tagged/java)
- [Oracle Java SE Troubleshooting](https://www.oracle.com/java/technologies/troubleshoot-javase.html)

### **Community Support:**
- [r/javahelp on Reddit](https://www.reddit.com/r/javahelp/)
- [Java Community Process](https://jcp.org/en/home/index)

##  🤝 Contributing

This is a demonstration application. For actual contributions:
1. Fork the repository (if applicable)
2. Create a feature branch
3. Implement changes with proper comments
4. Submit a pull request

## 📄 License

This software is provided for educational and demonstration purposes. For production use, proper licensing and compliance with organizational policies should be ensured.

---

*Last Updated: October 2023 | Version: 1.0.0 | For demonstration purposes only*
```

This comprehensive manual provides administrators with all necessary information to install, configure, and use the Justification Management System effectively. It covers technical requirements, step-by-step instructions, troubleshooting guides, and best pract to ensure smooth operation.