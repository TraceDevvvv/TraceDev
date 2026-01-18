```markdown
# Report Card System - Teacher Portal
## User Manual

### Overview
The Report Card System is a comprehensive Java-based application that enables teachers to view and manage student report cards online through the SMOS server. This system provides a user-friendly graphical interface for teachers to browse academic years, classes, students, and view detailed report cards for specific quarters.

### ✨ Main Features

1. **Secure Authentication System**
   - Teacher login with username and password verification
   - SMOS server connection simulation
   - Session management

2. **Intuitive Dashboard**
   - Clean, professional interface
   - Clear navigation to report card functionality
   - Easy access to "On-line report cards" feature

3. **Report Card Browsing System**
   - Step-by-step selection process:
     - Select academic year
     - Choose class
     - Pick student
     - Select quarter
   - Real-time filtering of options based on selections

4. **Detailed Report Card Display**
   - Complete student information
   - Subject-by-subject grades with scores and letter grades
   - Teacher comments
   - Average score calculation
   - Overall grade determination

5. **SMOS Server Integration**
   - Simulated server connection
   - Proper connection/disconnection handling
   - Session management as per requirements

### 🛠️ System Requirements

#### Hardware Requirements
- 2 GHz or faster processor
- 4 GB RAM minimum (8 GB recommended)
- 500 MB available disk space
- 1280 x 800 minimum display resolution

#### Software Requirements
- **Operating System**: Windows 10+, macOS 10.14+, or Linux (Ubuntu 18.04+)
- **Java Runtime Environment**: Java SE 8 or higher (OpenJDK 11+ recommended)
- **Display**: Minimum 1280x800 resolution

### 📦 Installation Guide

#### Step 1: Install Java
1. Verify if Java is already installed:
   ```bash
   java -version
   ```
   
2. If Java is not installed or version is below 8, download and install:
   - **Windows/Mac**: Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
   - **Linux**: Use package manager:
     ```bash
     sudo apt update
     sudo apt install openjdk-11-jdk
     ```

#### Step 2: Set Up the Application
1. **Download the source code**: Obtain all Java files from the provided code package
2. **Create directory structure**:
   ```
   ReportCardSystem/
   ├── src/
   │   └── com/
   │       └── chatdev/
   │           └── reportcardsystem/
   │               ├── Main.java
   │               ├── model/
   │               │   ├── ReportCardSystem.java
   │               │   ├── Teacher.java
   │               │   ├── Student.java
   │               │   ├── AcademicYear.java
   │               │   ├── SchoolClass.java
   │               │   └── SMOSServerConnection.java
   │               ├── data/
   │               │   ├── ReportCard.java
   │               │   └── SubjectGrade.java
   │               └── gui/
   │                   ├── LoginFrame.java
   │                   ├── TeacherDashboardFrame.java
   │                   ├── MainTeacherFrame.java
   │                   └── ReportCardDisplayFrame.java
   └── README.md
   ```

3. **Compile the application**:
   ```bash
   cd ReportCardSystem/src
   javac -d ../build com/chatdev/reportcardsystem/Main.java
   ```

#### Step 3: Run the Application
```bash
cd ReportCardSystem
java -cp build com.chatdev.reportcardsystem.Main
```

### 🎮 How to Use the Software

#### Login Screen
1. Launch the application using the command above
2. The login screen will appear with:
   - Username field
   - Password field
   - Login button
3. **Default Credentials** (for testing):
   - Username: `teacher`
   - Password: `password123`

#### Teacher Dashboard
After successful login, you'll see:
1. **Welcome Message**: "Welcome, Teacher!"
2. **Main Navigation Buttons**:
   - **On-line report cards**: Primary feature for viewing report cards
   - **My Profile**: Placeholder for future expansion
   - **System Settings**: Placeholder for future expansion
   - **Logout**: Ends session and disconnects from server

#### Using the Report Card System

##### Step 1: Access Report Cards
1. Click the **"On-line report cards"** button from the Teacher Dashboard
2. The system automatically connects to the SMOS server

##### Step 2: Select Academic Year
1. From the drop-down menu labeled "Select Academic Year:"
2. View available academic years where you teach at least one class
3. **Sample data includes**: 2023-2024, 2022-2023, 2021-2022

##### Step 3: Choose Class
1. After selecting a year, the class selection becomes enabled
2. Select a class from the drop-down menu
3. **Sample classes available for 2023-2024**:
   - 10A (with students: John Smith, Emma Johnson, Michael Brown)
   - 10B (with students: Sarah Davis, David Wilson)
   - 11A (with students: Lisa Miller, James Taylor)

##### Step 4: Select Student
1. After choosing a class, the student selection becomes enabled
2. Choose a student from the drop-down menu
3. Students are displayed in format: "StudentID - Full Name"

##### Step 5: Select Quarter
1. After student selection, quarterly option becomes enabled
2. Choose from: Q1, Q2, Q3, Q4
3. **Note**: Sample data primarily shows Q1 reports

##### Step 6: View Report Card
1. Click **"View Report Card"** button
2. A detailed report card window opens showing:
   - Student information (ID, Name, Class, Year/Quarter)
   - Grades table with subject, score, and letter grade
   - Teacher comments
   - Average score and overall grade

### 📊 Sample Data Provided

The system includes pre-loaded sample data for demonstration:

#### Students and Report Cards:
1. **John Smith** (ID: S001)
   - Class: 10A, Year: 2023-2024, Quarter: Q1
   - Grades: Mathematics (85-A), English (90-A), Science (78-B), History (92-A), PE (88-A)
   - Comments: "Excellent performance. Shows great improvement in Science."

2. **Emma Johnson** (ID: S002)
   - Class: 10A, Year: 2023-2024, Quarter: Q1
   - Grades: Mathematics (95-A+), English (88-A), Science (92-A), History (85-A), PE (90-A)
   - Comments: "Outstanding performance across all subjects."

### 🔧 Troubleshooting

#### Common Issues and Solutions:

1. **Java Not Found**
   ```
   Error: Could not find or load main class Main
   ```
   **Solution**: Ensure Java is properly installed and added to PATH

2. **GUI Not Displaying Properly**
   - Check display resolution meets minimum requirements
   - Ensure Java Swing is supported on your system

3. **Login Failed**
   - Verify username and password (default: teacher/password123)
   - Check for caps lock on keyboard

4. **No Data Displayed**
   - Sample data is hardcoded; ensure you're selecting:
     - Year: 2023-2024
     - Class: 10A
     - Student: John Smith or Emma Johnson
     - Quarter: Q1

#### Logging Out Properly
1. Always use the **Logout** button to ensure:
   - SMOS server connection is properly terminated
   - Session data is cleared
   - Postcondition requirements are met

### 📝 Post-Usage Process

After completing your report card review:

1. **Close Report Card Window**: Click "Close" to return to the selection screen
2. **Return to Dashboard**: Use "Back to Dashboard" button
3. **Log Out Properly**: 
   - Click "Logout" button
   - Confirm logout when prompted
   - System confirms "Successfully disconnected from SMOS server"

### 🔒 Security Notes

1. **Default Credentials**: The system uses default credentials for demonstration. For production use:
   - Implement proper authentication
   - Connect to a real SMOS server
   - Use secure password storage

2. **Data Persistence**: Current implementation uses in-memory data. For production:
   - Connect to a database
   - Implement proper data persistence

### 📱 Interface Guidelines

#### Color Scheme
- Primary: Blue (#4682B4) for headers and primary buttons
- Secondary: Light blue (#F0F8FF) for information panels
- Text: Black for content, White for headers

#### Button Functions
- **Blue buttons**: Primary actions (Login, View Report Card)
- **Gray buttons**: Secondary actions (Profile, Settings)
- **Red-tinged buttons**: Destructive actions (Logout)

### 💡 Tips for Best Experience

1. **Navigation Flow**: Follow the step-by-step process for best results
2. **Screen Resolution**: Use at least 1280x800 for optimal viewing
3. **Demo Data**: Familiarize yourself with the sample students to test functionality
4. **Connection Status**: The system simulates server connection; real implementations would show connection status

### 🚀 Future Enhancements (Planned)

1. **Real Database Integration**: Replace sample data with live database connection
2. **Report Generation**: Export report cards as PDF
3. **Grade Editing**: Allow teachers to modify grades and comments
4. **Multi-Teacher Support**: Different teachers with different class assignments
5. **Parent Portal**: Share selected report data with parents
6. **Analytics Dashboard**: Class performance statistics and trends

### 📞 Support

For assistance with the Report Card System:
1. **Technical Issues**: Check Java installation and system requirements
2. **Functionality Questions**: Review this manual for step-by-step guidance
3. **Feature Requests**: Contact system administrator for enhancement requests

---

**Version**: 1.0.0  
**Last Updated**: October 2024  
**Compatibility**: Java SE 8+  
**License**: Educational/Internal Use
```

This user manual provides comprehensive guidance for teachers to use the Report Card System effectively. It covers installation, usage, troubleshooting, and best pract while maintaining a professional tone appropriate for educational software.