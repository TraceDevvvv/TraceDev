```markdown
# ViewOnePage Report Card System - User Manual

## Overview

ViewOnePage is a comprehensive Student Report Card viewing system designed for school administrators. This Java-based application provides a graphical user interface that follows a precise workflow for accessing and viewing student report cards according to academic years, classes, and specific time periods.

### Main Features

- **Administrator Authentication Simulation**: Simulated login interface showing administrator status
- **Academic Year Selection**: Browse and select from available academic years
- **Class-based Navigation**: View classes organized by academic year with direct report card access
- **Student Selection**: View student lists within selected classes
- **Time Period Filtering**: Select four-month periods (quarters) for report generation
- **Dynamic Report Generation**: Automatic generation of detailed report cards with grades, attendance, and teacher comments
- **Print Functionality**: Print reports directly from the application
- **Security Protocols**: Automatic server connection termination after report viewing

## System Requirements

### Software Requirements
- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE) 8 or higher
- Any modern operating system (Windows, macOS, Linux)

### Hardware Requirements
- Minimum 2GB RAM
- 100MB free disk space
- Display resolution: 1024x768 or higher

## Installation Guide

### Step 1: Install Java
1. Download the latest Java SE Development Kit from [Oracle's official website](https://www.oracle.com/java/technologies/downloads/)
2. Run the installer and follow the installation wizard
3. Verify installation by opening Command Prompt/Terminal and typing:
   ```bash
   java -version
   ```
4. Ensure you see version information similar to:
   ```
   java version "1.8.0_XXX"
   Java(TM) SE Runtime Environment (build 1.8.0_XXX-YYY)
   ```

### Step 2: Setup the Application
1. Create a new directory for the application:
   ```bash
   mkdir ViewOnePageApp
   cd ViewOnePageApp
   ```

2. Save the provided Java file as `ViewOnePageApp.java` in this directory

3. Compile the application:
   ```bash
   javac ViewOnePageApp.java
   ```

4. Run the application:
   ```bash
   java ViewOnePageApp
   ```

## How to Use the Application

### 1. Initial Login Screen
- When you start the application, you'll see the Administrator Portal
- The system simulates a logged-in administrator (Username: "admin")
- Click the **"Online Reports"** button to proceed to the main application

![Login Screen](login_screen.png)

### 2. Main Application Interface
The main interface is divided into sequential steps:

#### Step 1: Select Academic Year
- Dropdown menu showing available academic years (e.g., "2022-2023", "2023-2024")
- Select your desired academic year
- **System Action**: Displays classes with "View Report Cards" buttons

#### Step 2: Choose Class
- After selecting a year, you'll see a list of classes
- Each class has a **"View Report Cards"** button
- Click the button next to your desired class
- **System Action**: Displays student list for the selected class

#### Step 3: Select Student
- A dropdown menu appears showing all students in the selected class
- Select the student whose report you want to view
- **System Action**: Enables the report selection panel

#### Step 4: Select Four-Month Period
- Choose a time period from the dropdown (e.g., "Sep-Dec", "Jan-Apr", "May-Aug")
- Click the **"View Report Card"** button
- **System Action**: Generates and displays the complete report card

### 3. Report Card Features

Each generated report card includes:

#### Academic Information
- Student name
- Class designation
- Academic year
- Report period
- Generation date

#### Academic Performance
- Individual subject grades (Mathematics, Science, English, History, Physical Education)
- Grades are student-specific and consistent

#### Student Metrics
- Attendance percentage
- Participation level
- Homework completion rate

#### Teacher Assessment
- Personalized comments based on student and period
- Overall grade
- Student status

### 4. Report Card Actions

#### Print Report
- Click the **"Print Report"** button to print the current report
- The system will use your default printer settings
- A confirmation message appears upon successful printing

#### Clear Report
- Click **"Clear Report"** to remove the current report
- This allows you to view another student's report without restarting

## Workflow Example

1. **Start Application**: Launch `ViewOnePageApp`
2. **Login Screen**: Click "Online Reports" button
3. **Select Year**: Choose "2023-2024" from the dropdown
4. **Choose Class**: Click "View Report Cards" next to "Class B"
5. **Select Student**: Choose "Bob Johnson" from the student dropdown
6. **Choose Period**: Select "Sep-Dec" from the period dropdown
7. **View Report**: Click "View Report Card"
8. **Review**: Examine Bob Johnson's report for September-December 2023-2024
9. **Print** (Optional): Click "Print Report" for a hard copy
10. **Repeat**: Clear report and select another student or start over

## Application Navigation Tips

### Sequential Process
The application follows a strict sequential process. You must complete each step before proceeding to the next:
1. Year → 2. Class → 3. Student → 4. Period → Report

### Resetting Selections
To start over or change your selection:
- Change the academic year to reset everything
- Or click "Clear Report" and work backward through the steps

### Visual Indicators
- **Enabled buttons** indicate actions are available
- **Disabled buttons** indicate pending selections
- **Green text** confirms successful login status
- **Information dialogs** provide guidance and confirmations

## Error Handling

### Common Issues and Solutions

#### 1. "Incomplete Data" Warning
- **Cause**: Missing one or more required selections
- **Solution**: Ensure you have selected: Year, Class, Student, and Period

#### 2. Application Won't Start
- **Cause**: Java not installed or incorrect version
- **Solution**: Verify Java installation with `java -version`
- Reinstall Java if necessary

#### 3. Compilation Errors
- **Cause**: File naming issues or syntax errors
- **Solution**: Ensure file is saved as `ViewOnePageApp.java`
- Re-copy the exact code provided

#### 4. Blank Screen or Freezing
- **Cause**: Insufficient system resources
- **Solution**: Close other applications
- Restart the application

## Security Features

### Simulated Security Protocol
- The application simulates a secure educational system (SMOS server)
- After report display, the system automatically terminates the server connection
- A confirmation message appears indicating the security protocol was followed

### Data Privacy
- All student data is simulated and generated dynamically
- No real student information is stored or transmitted
- Reports are generated locally on your machine

## Technical Details

### Data Generation Algorithm
The application uses hashing algorithms to generate:
- Consistent grades per student-subject combination
- Realistic attendance percentages (85-100%)
- Varied participation levels
- Personalized teacher comments

### GUI Framework
- Built using Java Swing
- Event-driven architecture
- Responsive layout design
- Cross-platform compatibility

### Code Structure
- `ViewOnePageApp`: Main entry point
- `LoginPanel`: Initial login and "Online Reports" button
- `ReportCardViewer`: Main report viewing workflow
- `ReportDisplayPanel`: Report display and printing functionality

## Troubleshooting

### Performance Issues
- If the application runs slowly, ensure you have at least 2GB RAM free
- Close other Java applications running simultaneously

### Display Issues
- If text appears too small, adjust your system display settings
- The application uses standard system fonts for consistency

### Printing Problems
- Ensure your printer is properly connected and has paper
- Check printer permissions for Java applications
- Try printing a test page from your operating system first

## Best Pract

### For Administrators
1. Always follow the sequential workflow
2. Verify student selections before generating reports
3. Use the print function for official records
4. Clear reports between different student viewings

### For System Administrators
1. Regularly update Java to the latest secure version
2. Store generated reports in secure locations if saving locally
3. Ensure proper backup of any exported data

## Support and Contact

### Documentation Updates
- This manual is version-specific to the provided code
- Refer to the code comments for detailed technical information

### Getting Help
- Review the error messages displayed in the application
- Check the console output for system messages
- Ensure all preconditions are met before proceeding

### Customization
The application can be customized by modifying:
- Academic years in the `academicYears` array
- Class names in the `classes` array
- Student names in the `students` array
- Four-month periods in the `fourMonthPeriods` array
- Report content generation in the `generateReport` method

---

## Quick Reference Guide

### Keyboard Shortcuts
- **Tab**: Navigate between controls
- **Enter**: Activate selected button
- **Space**: Toggle dropdown menus
- **Ctrl+P**: Print (when report is displayed)
- **Esc**: Close dialog windows

### Status Messages
- **"Logged in as Administrator"**: Ready to begin
- **"Please select an academic year first"**: Year selection required
- **"Connection to SMOS server terminated"**: Security protocol completed
- **"Report sent to printer successfully"**: Print job completed

### System Messages (Console)
