```markdown
# Digital Register System - User Manual

## Overview
The Digital Register System is a desktop application that allows academic administrators (with the "Direction" role) to view and manage digital registers for different academic years. The system provides a graphical user interface (GUI) built with Java Swing that enables users to log in, select an academic year, and view associated register lists grouped by classes.

## Main Features

### 1. **Authentication System**
- Role-based login system specifically for "Direction" users
- Secure login interface with username, password, and role selection
- Input validation to ensure all fields are properly filled

### 2. **Academic Year Selection**
- Interactive year selection interface with a "Digital Register" button
- Dynamic generation of academic years (current year ±5 years)
- Immediate validation of user selections

### 3. **Register List View**
- Tabular display of digital registers grouped by class
- Shows register ID, assigned teacher, and current status
- Read-only interface for secure viewing
- Visual indication of register status (Active, Archived, Pending)

### 4. **Error Handling**
- Comprehensive error messages for invalid inputs
- Connection interruption handling with confirmation dialogs
- Graceful degradation when no registers are found

### 5. **Navigation**
- Back button to return to year selection
- Refresh button to reload register data
- Proper window closing with safety confirmation

## System Requirements

### Hardware Requirements:
- Minimum: 2 GB RAM
- Minimum: 200 MB free disk space
- Display: 1024×768 resolution or higher

### Software Requirements:

#### **Option 1: Runtime Environment (Running Pre-compiled Application)**
- Java Runtime Environment (JRE) 8 or higher
985
- Windows: Any modern Windows OS (Windows 10/11 recommended)
- macOS: macOS 10.12 or higher
- Linux: Most modern distributions

#### **Option 2: Development Environment (Compiling from Source)**
- Java Development Kit (JDK) 8 or higher (11+ recommended)
- Text editor or IDE (Optional):
  - IntelliJ IDEA
  - Eclipse
  - NetBeans
  - VS Code with Java extensions

## Installation and Setup

### **Option A: Using Pre-compiled JAR (Recommended for End Users)**

1. **Download the Application**
   - Obtain the `DigitalRegisterApp.jar` file from your system administrator or download location

2. **Verify Java Installation**
   ```bash
   java -version
   ```
   Ensure Java is installed (version 8 or higher)

3. **Run the Application**
   ```bash
   java -jar DigitalRegisterApp.jar
   ```

### **Option B: Compiling from Source Code**

1. **Install Java Development Kit (JDK)**
   - Download from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://adoptium.net/)
   - Set JAVA_HOME environment variable

   **Windows:**
   ```cmd
   set JAVA_HOME=C:\path\to\your\jdk
   set PATH=%JAVA_HOME%\bin;%PATH%
   ```

   **Linux/macOS:**
   ```bash
   export JAVA_HOME=/path/to/your/jdk
   export PATH=$JAVA_HOME/bin:$PATH
   ```

2. **Download Source Code Files**
   Download the following Java files into a single directory:
   - `Main.java` (or `DigitalRegisterApp.java`)
   - Or use the consolidated `main.java`

3. **Compile the Application**

   **Using Command Line:**
   ```bash
   # Navigate to directory containing Java files
   cd /path/to/source/code

   # Compile all Java files
   javac *.java

   # If using main.java
   javac main.java
   ```

   **Using an IDE:**
   - Create a new Java project
   - Add all Java files to the source folder
   - Build the project (usually Ctrl+F9 or equivalent)

4. **Run the Application**

   **Command Line:**
   ```bash
   java Main
   ```
   or
   ```bash
   java DigitalRegisterApp
   ```

   **IDE:**
   - Right-click on Main.java or DigitalRegisterApp.java
   - Select "Run" or "Run as Java Application"

## How to Use the Application

### **Step 1: Launch the Application**
- Double-click the JAR file (if using pre-compiled)
- Or run from command line/IDE as described above
- The login window will appear

![Login Screen](path/to/login_screenshot.png)

### **Step 2: Login as Direction User**

1. **Enter Credentials:**
   - **Username:** Enter any username (demo system)
   - **Password:** Enter any password (demo system)

2. **Select Role:**
   - From the dropdown menu, select **"Direction"**
   - Make sure to select "Direction" specifically

3. **Click Login:**
   - Click the "Login" button
   - If successful, you'll see a confirmation message
   - The system will automatically proceed to the Year Selection screen

**Note:** This is a demonstration system. In a production environment, credentials would be validated against a secure database.

### **Step 3: Select Academic Year**

1. **View Available Years:**
   - The dropdown shows academic years from 5 years ago to 1 year ahead
   - Current year is selected by default

2. **Make Selection:**
   - Click the dropdown and select your desired academic year
   - Example: "2024" for the 2024 academic year

3. **Access Digital Register:**
   - Click the **(Digital Register)** button
   - This triggers the system to search for registers in the selected year
   - The system proceeds to the Register List view

### **Step 4: View Register List**

The main register list screen displays all digital registers for the selected year, organized by class:

| Column | Description | Example |
|--------|-------------|---------|
| **Class** | Class identifier | 10A, 11B, 12C |
| **Register ID** | Unique register identifier | REG001, REG002 |
| **Teacher** | Assigned teacher | Mr. Smith, Ms. Johnson |
| **Status** | Current status | Active, Archived, Pending |

**Interacting with the List:**
- **Table is read-only:** You cannot edit data directly
- **Refresh:** Click **(Refresh)** to reload register data
- **Back:** Click **(Back)** to return to year selection

### **Sample Data Displayed:**
```
Class   Register ID   Teacher         Status
10A     REG001        Mr. Smith       Active
10B     REG002        Ms. Johnson     Archived
11A     REG003        Dr. Williams    Active
11B     REG004        Mrs. Brown      Pending
12A     REG005        Prof. Davis     Active
```

## Application Navigation Flow

```
Start → Login Screen → Year Selection → Register List
     ↖                                    ↓
      ← ← ← ← ← Back Button ← ← ← ← ← ← ← ←
```

## Troubleshooting Guide

### **Common Issues and Solutions**

| Issue | Possible Cause | Solution |
|-------|---------------|----------|
| **Application won't start** | Java not installed | Install Java JRE 8+ |
| **"Login Error" message** | Empty fields | Fill all login fields |
| **"Authorization Error"** | Wrong role selected | Select "Direction" role |
| **No registers displayed** | No data for selected year | Try different year |
| **Window closes unexpectedly** | System interruption | Restart application |

### **Error Messages**

1. **"Username and password cannot be empty."**
   - **Action:** Enter both username and password

2. **"Please select a user role."**
   - **Action:** Select "Direction" from dropdown

3. **"Access denied. This application requires 'Direction' role."**
   - **Action:** Make sure "Direction" is selected

4. **"No registers found for the selected year."**
   - **Action:** Try selecting a different academic year

5. **"Connection to SMOS server interrupted."**
   - **Action:** Reconnect to network and restart application

## Security and Data Handling

### **Security Features:**
- Role-based authentication ensures only authorized users access data
- Password fields are masked for security
- All data displayed is read-only to prevent accidental modifications
- Connection interruption warnings prevent data corruption

### **Data Privacy:**
- This demonstration uses simulated data
- In production, the application would connect to a secure school management system
- User credentials and register data would be encrypted
- No personal data is stored locally in this demonstration version

## Best Pract

### **For Administrators:**
1. **Session Management:**
   - Always log out when leaving workstation
   - Use strong passwords in production environment

2. **Data Verification:**
   - Verify selected academic year before proceeding
   - Use Refresh button to ensure latest data is displayed

3. **Backup:**
   - Ensure regular backups of your digital register data

### **For Technical Support:**
1. **Logs and Debugging:**
   - Check Java console for error messages
   - Verify Java version compatibility
   - Ensure sufficient system resources

2. **Network Issues:**
   - Verify network connectivity for production version
   - Check firewall settings for SMOS server connections

## Frequently Asked Questions (FAQ)

**Q1: What is the "SMOS server" mentioned in error messages?**
- **A:** SMOS (School Management Operating System) is a hypothetical server that would store actual register data in a production environment. In this demonstration, data is simulated.

**Q2: Can I edit the register information?**
- **A:** No, this version is read-only. It's designed for viewing register lists only.

**Q3: How do I add registers for a new academic year?**
- **A:** This functionality is not included in this viewing application. Contact your system administrator for data entry.

**Q4: The application runs slow on my computer.**
- **A:** Ensure you have the latest Java version installed. Close other applications to free up memory.

**Q5: Can I run this on a Mac/Linux?**
- **A:** Yes, as long as Java is installed, the application runs on any platform supporting Java.

**Q6: Is my login information secure?**
- **A:** In this demonstration version, credentials are not validated. In production, these would be encrypted and validated against a secure database.

## Advanced Usage

### **Command Line Arguments:**
```bash
# Set initial window size and position
java -Dsun.awt.keepWorkingSetOnMinimize=true DigitalRegisterApp

# Enable additional logging
java -Djava.util.logging.config.file=logging.properties DigitalRegisterApp
```

### **Customizing the Application:**
For advanced users, the source code can be modified to:

1. Extend the year range
   - Modify `getAcademicYears()` method in `YearSelectionFrame`

2. Add new register statuses
   - Modify the `Register` inner class in `RegisterListFrame`

3. Change login roles
   - Modify the JComboBox initialization in `LoginFrame`

## Support and Contact

### **For Technical Issues:**
- **System Administrator:** Contact your institution's IT department
- **Application Support:** software.support@institution.edu

### **For Feature Requests:**
Submit requests through your institution's designated software request channel

### **Emergency Support:**
For critical issues affecting academic operations, contact:
- Phone: [Your IT Support Phone Number]
- Email: emergency.support@institution.edu

## Version Information

- **Current Version:** 1.0.0 (Demo)
- **Release Date:** [Current Date]
- **Java Version Required:** 8 or higher
- **Last Updated:** [Current Date]

## Important Notes

1. **Demonstration Only:** This application demonstrates functionality. Actual data handling would require integration with a school management system.

2. **Data Persistence:** No data is saved between sessions in this demonstration version.

3. **System Integration:** In a production environment, this application would connect to existing school databases and authentication systems.

4. **Scalability:** The current design supports small to medium-sized institutions. Larger deployments may require additional optimization.

## Copyright and License

This software is provided for educational and demonstration purposes. For production use, ensure appropriate licensing and compliance with your institution's software policies.

---

**Need Help?** Refer to this manual or contact your system administrator for assistance with the Digital Register System.
```