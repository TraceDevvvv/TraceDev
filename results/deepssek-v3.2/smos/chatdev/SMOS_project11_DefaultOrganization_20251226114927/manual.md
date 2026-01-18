```markdown
# Teaching Management System - User Manual

## Overview

The Teaching Management System is a Java-based desktop application that allows administrators to assign and remove teaching assignments for teachers. This system follows a structured workflow where administrators can select academic years, classes, and corresponding teachings to manage teacher assignments efficiently.

## Main Features

### 1. **Administrator Authentication**
- Ensures only authorized administrators can access the system
- Simulated authentication for demonstration purposes
- Server connection status validation

### 2. **Teacher Management Interface**
- Visual form for managing teaching assignments
- Clean, intuitive user interface with step-by-step workflow
- Real-time updates based on selections

### 3. **Dynamic Data Selection**
- **Academic Year Selection**: Choose from available academic years (2023-2024, 2024-2025)
- **Class Selection**: View classes available for the selected academic year
- **Teaching Selection**: See all teachings associated with selected class

### 4. **Assignment Operations**
- **Assign Teachings**: Add multiple teachings to a teacher's portfolio
- **Remove Teachings**: Remove existing teaching assignments
- **Bulk Operations**: Support for selecting multiple teachings at once

### 5. **Server Connection Management**
- Visual indicator of SMOS server connection status
- Graceful handling of server disconnections
- Appropriate error messages for failed operations

## System Requirements

### Software Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Swing Library**: Included with standard Java distributions

### Hardware Requirements
- Minimum 2GB RAM
- 100MB free disk space
- Display resolution: 1024x768 or higher

## Installation Guide

### Step 1: Install Java
1. Download and install Java JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
2. Verify installation by opening terminal/command prompt and typing:
   ```bash
   java -version
   javac -version
   ```

### Step 2: Download the Application
1. Create a new directory for the project:
   ```bash
   mkdir TeachingManagementSystem
   cd TeachingManagementSystem
   ```

2. Create the following Java files in the directory:
   - `Teacher.java`
   - `TeachingManagementForm.java`
   - `Main.java`

3. Copy the provided code into each respective file

### Step 3: Compile the Application
In the project directory, compile all Java files:
```bash
javac Teacher.java TeachingManagementForm.java Main.java
```

This will generate `.class` files for each Java class.

## How to Use the Application

### Step 1: Start the Application
Run the main program:
```bash
java Main
```

### Step 2: Application Interface Overview
The main window displays the Teaching Management Form with the following sections:

1. **Form Area** (Top/Middle):
   - Step 1: Select Academic Year dropdown
   - Step 2: Select Class dropdown
   - Step 3: Available Teachings list (scrollable)

2. **Server Status Indicator**:
   - Shows "Connected" (green) or "Disconnected" (red)

3. **Control Buttons** (Bottom):
   - Assign Selected
   - Remove Selected
   - Close

### Step 3: Manage Teaching Assignments

#### To Assign Teachings:
1. **Select Academic Year**: Choose an academic year from the dropdown (e.g., "2024-2025")
2. **Select Class**: Choose a class from the updated dropdown (e.g., "Class 10A")
3. **Select Teachings**: Click on teachings in the list (hold Ctrl/Cmd for multiple selections)
4. **Assign**: Click "Assign Selected" button
5. **Confirmation**: A success message appears showing assigned teachings

#### To Remove Teachings:
1. **Select Academic Year**: Choose the academic year
2. **Select Class**: Choose the relevant class
3. **Select Teachings**: Select the teachings to remove
4. **Remove**: Click "Remove Selected" button
5. **Confirmation**: A success message appears showing removed teachings

### Step 4: Exit the Application
- Click the "Close" button or use the window close button
- The application will terminate cleanly

## Troubleshooting

### Common Issues and Solutions:

#### 1. **"Cannot assign teachings: SMOS server connection interrupted"**
- **Cause**: Server connection is simulated as disconnected
- **Solution**: The application simulates server status. In production, ensure network connectivity to SMOS server

#### 2. **No items in class dropdown**
- **Cause**: No classes defined for selected academic year
- **Solution**: Verify data initialization in `initializeData()` method

#### 3. **Application won't start or crashes**
- **Cause**: Java not properly installed or wrong version
- **Solution**: Reinstall Java JDK 8 or higher and ensure PATH is set correctly

#### 4. **"Please select at least one teaching" warning**
- **Cause**: No teachings selected before clicking assign/remove
- **Solution**: Select one or more teachings from the list before performing operations

#### 5. **Buttons are disabled**
- **Cause**: Server connection is marked as disconnected
- **Solution**: Check server connection status indicator at top of form

## Data Structure

The system uses the following simulated data structure:

### Academic Years:
- 2023-2024
- 2024-2025

### Classes per Year:
- **2023-2024**: Class 10A, Class 10B, Class 11A
- **2024-2025**: Class 10A, Class 10B, Class 11A, Class 11B

### Teachings per Class:
- **Class 10A**: Mathematics, Physics, Chemistry
- **Class 10B**: Mathematics, Biology, English
- **Class 11A**: Advanced Math, Computer Science, History
- **Class 11B**: Literature, Geography, Art

## Customization

### Extending the System:

#### Add More Academic Years:
Edit the `initializeData()` method in `TeachingManagementForm.java`:
```java
yearToClasses.put("2025-2026", Arrays.asList("Class 12A", "Class 12B"));
```

#### Add More Classes:
```java
yearToClasses.put("2024-2025", Arrays.asList("Class 10A", "Class 10B", "Class 11A", "Class 11B", "Class 12A"));
```

#### Add More Teachings:
```java
classToTeachings.put("Class 10A", Arrays.asList("Mathematics", "Physics", "Chemistry", "Biology"));
```

#### Customize Teacher Details:
Modify the `Teacher` constructor in `Main.java`:
```java
Teacher teacher = new Teacher("Jane Smith");
```

## Best Pract

1. **Backup Data**: Regularly backup teacher assignment data in production
2. **User Training**: Train administrators on the workflow sequence
3. **System Maintenance**: Schedule regular server maintenance
4. **Data Validation**: Implement additional validation for production use
5. **Access Control**: Enhance authentication for production environments

## Support and Contact

For technical support or customization requests:
               
- **System Requirements**: Ensure Java 8+ is installed
- **Documentation**: Refer to this manual for setup and usage
- **Code Location**: All source files in the project directory

## Security Notes

### Current Implementation (Demonstration):
- Administrator authentication is simulated
- Server connection status is simulated
- No actual database connection implemented

### For Production Use:
- Implement proper database connectivity
- Add secure authentication mechanism
- Implement role-based access control
- Add audit logging for all operations
- Secure network communications

## Version Information

- **Current Version**: 1.0
- **Last Updated**: [Current Date]
- **Java Version**: Compatible with Java 8+
- **Dependencies**: Standard Java Swing library

---

**Note**: This is a demonstration application. For production use, additional security, database connectivity, and error handling should be implemented.

Happy Teaching Management!
```