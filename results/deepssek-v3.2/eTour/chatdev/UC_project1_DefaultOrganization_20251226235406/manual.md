# Cultural Heritage Management System - User Manual

## Overview

The Cultural Heritage Management System is a comprehensive software application designed for agency operators to manage cultural heritage objects. The primary function is to securely delete cultural heritage items from the system while maintaining data integrity and preventing unauthorized operations.

## Main Features

### 1. **Secure Authentication System**
- Agency operator login with credential verification
- Session-based access control
- Protection against unauthorized operations

### 2. **Cultural Heritage Management**
- Browse and search cultural heritage items
- View detailed information (ID, Name, Type, Location, Description)
- Multi-criteria search functionality

### 3. **Delete Cultural Heritage Use Case**
- Complete implementation of the DeleteCulturalHeritage use case
- Step-by-step confirmation process
- Prevention of multiple simultaneous submissions
- Connection status monitoring with server interruption handling

### 4. **Quality Control Features**
- Input blocking during operations to prevent multiple submissions
- Real-time connection monitoring to ETOUR server
- Operation status tracking and cancellation capability
- User-friendly error handling and notifications

## System Requirements

### Minimum Hardware Requirements
- Processor: 1 GHz or faster
- RAM: 512 MB minimum (1 GB recommended)
- Storage: 100 MB free disk space
- Display: 1024x768 resolution or higher

### Software Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **No additional libraries or frameworks required**

## Installation Guide

### Step 1: Install Java Development Kit (JDK)
1. Download the latest JDK from [Oracle's official website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Run the installer and follow the on-screen instructions
3. Set up JAVA_HOME environment variable:
   - **Windows**:
     ```
     setx -m JAVA_HOME "C:\Program Files\Java\jdk-xx"
     ```
   - **macOS/Linux**:
     ```bash
     export JAVA_HOME=/usr/lib/jvm/java-xx-openjdk
     echo 'export JAVA_HOME=/usr/lib/jvm/java-xx-openjdk' >> ~/.bashrc
     ```
4. Add Java to your PATH:
   - **Windows**: Add `%JAVA_HOME%\bin` to Path system variable
   - **macOS/Linux**: Add `$JAVA_HOME/bin` to PATH in shell configuration

### Step 2: Verify Installation
Open a terminal/command prompt and run:
```bash
java -version
javac -version
```
You should see the installed Java version information.

### Step 3: Download Application Files
Download all required Java source files:
1. `CulturalHeritage.java`
2. `CulturalHeritageDatabase.java`
3. `DeleteCulturalHeritageController.java`
4. `ServerConnection.java`
5. `DeleteCulturalHeritageGUI.java`
6. `MainApp.java`

### Step 4: Organize Project Structure
Create a project directory and place all files together:
```
cultural-heritage-system/
├── CulturalHeritage.java
├── CulturalHeritageDatabase.java
├── DeleteCulturalHeritageController.java
├── ServerConnection.java
├── DeleteCulturalHeritageGUI.java
└── MainApp.java
```

## How to Run the Application

### Method 1: Using Command Line
1. Open terminal/command prompt in the project directory
2. Compile all Java files:
   ```bash
   javac *.java
   ```
3. Run the application:
   ```bash
   java MainApp
   ```

### Method 2: Using an IDE (Recommended)
1. Open your preferred Java IDE (Eclipse, IntelliJ IDEA, or NetBeans)
2. Create a new Java project
3. Import all source files into the project
4. Set `MainApp.java` as the main class
5. Run the project

## User Guide

### Step 1: Login
1. Launch the application
2. Enter agency operator credentials:
   - **Username**: `agency`
   - **Password**: `password123`
3. Click "Login" button
4. Successful login will display the main Cultural Heritage Management interface

### Step 2: Browse Cultural Heritage Items
1. The main screen displays a table of cultural heritage items
2. View information including:
   - Item ID
   - Name
   - Type (Architecture, Art, etc.)
   - Location
   - Description
3. Use the search bar to filter items by name, description, or location

### Step 3: Delete a Cultural Heritage Item

#### **Approach 1: Immediate Deletion**
1. **Select an item** from the table by clicking on it
2. Click the **"Delete Selected Item"** button
3. A confirmation dialog will appear asking to confirm deletion
4. Click **"Yes"** to proceed or **"No"** to cancel
5. If confirmed:
   - "Confirm Deletion" button becomes enabled
   - "Cancel Operation" button becomes enabled
   - Search and table selection are disabled
6. Click **"Confirm Deletion"** to complete the operation
7. **Success Message**: "SUCCESS: Cultural heritage item has been successfully deleted"
8. **Failure Scenarios**:
   - "ERROR: Connection to ETOUR server interrupted. Operation cancelled."
   - "ERROR: Failed to delete cultural heritage item."
   - "ERROR: An unexpected error occurred: [error detail]"

#### **Approach 2: Cancellation during Process**
1. Initiate deletion as described above
2. During the confirmation stage, click **"Cancel Operation"**
3. The system will:
   - Reset all controls to their normal state
   - Display cancellation notification
   - Allow new operations to be initiated

### Step 4: Search Functionality
1. Enter search terms in the search field
2. The system automatically filters results in real-time
3. Search criteria include:
   - Item name (partial match)
   - Description (partial match)
   - Location (partial match)
4. Clear the search field to show all items

## Special Features Explained

### **Operation Locking Mechanism**
- Prevents multiple simultaneous deletion operations
- Disables UI controls during active operations
- Ensures data integrity and prevents race conditions

### **Server Connection Monitoring**
- Simulates 10% chance of server interruption
- Automatically detects connection issues
- Cancels operations safely when connection is lost
- Provides appropriate error messages

### **Input Validation**
- Prevents deletion without item selection
- Validates item existence before deletion
- Ensures proper authentication before operations

## Sample Cultural Heritage Items

The system comes pre-loaded with five sample cultural heritage items:

1. **Colosseum** - Ancient Roman amphitheater in Rome, Italy (Architecture)
2. **Great Wall of China** - Series of fortifications in China (Architecture)
3. **Mona Lisa** - Famous painting by Leonardo da Vinci in Paris, France (Art)
4. **Pyramids of Giza** - Ancient Egyptian pyramids in Giza, Egypt (Architecture)
5. **Sistine Chapel** - Renaissance chapel with Michelangelo frescoes in Vatican City (Art)

## Troubleshooting Guide

### **Common Issues and Solutions**

#### **Issue 1: Java not found**
```
Error: 'java' is not recognized as an internal or external command
```
**Solution**: Ensure Java JDK is installed and PATH environment variable is properly set.

#### **Issue 2: Compilation errors**
```
Error: cannot find symbol
```
**Solution**: Make sure all Java files are in the same directory and compile them all together.

#### **Issue 3: GUI not displaying properly**
**Solution**: 
1. Ensure Java Swing is supported on your system
2. Run with administrative privileges if needed
3. Check for conflicting applications

#### **Issue 4: Login always fails**
**Solution**: Use exact credentials: Username: `agency`, Password: `password123`

#### **Issue 5: Server connection errors appear frequently**
**Solution**: This is a simulation feature. The system intentionally simulates 10% chance of server interruption to demonstrate error handling.

### **Logging and Error Messages**

The application provides different types of messages:

1. **Status Messages** (displayed in status bar)
   - "Logged in as Agency Operator"
   - "Select a cultural heritage item to delete"
   - "Deletion initiated. Please confirm the operation."

2. **Success Messages** (popup dialog)
   - "SUCCESS: Cultural heritage item has been successfully deleted."
   - "Deletion operation has been cancelled."

3. **Error Messages** (popup dialog)
   - "Invalid credentials. Please try again."
   - "ERROR: Connection to ETOUR server interrupted."
   - "Please select a cultural heritage item to delete."

## Security Notes

### **Authentication**
- Credentials are hard-coded for demonstration purposes
- In production, integrate with proper authentication system
- Session management prevents unauthorized access

### **Data Protection**
- No actual database connection required
- All data is stored in memory during runtime
- Data modifications persist only for the current session

## Best Pract for Users

1. **Always confirm selections** before deleting
2. **Monitor the status bar** for operation status
3. **Use search functionality** to quickly find items
4. **Cancel operations promptly** if changes are needed
5. **Log out properly** by closing the application

## Technical Details

### **Architecture**
- **Model**: CulturalHeritage class
- **Database**: CulturalHeritageDatabase class
- **Controller**: DeleteCulturalHeritageController class
- **View**: DeleteCulturalHeritageGUI class
- **Server**: ServerConnection class

### **Design Patterns**
- **MVC (Model-View-Controller)** for separation of concerns
- **Singleton-like** for database access
- **Observer** for UI event handling

### **Error Handling**
- Synchronized methods for thread safety
- Try-catch blocks for exception handling
- User-friendly error messages
- Graceful failure recovery

## Support and Maintenance

### **Getting Help**
For issues with the application, ensure:
1. Java version is 8 or higher
2. All files are present in the same directory
3. No compilation warnings are ignored

### **Extending the Application**
To add more cultural heritage items, modify the `initializeSampleData()` method in `CulturalHeritageDatabase.java`.

### **Customizing Credentials**
To change login credentials, modify the `loginAgencyOperator()` method in `DeleteCulturalHeritageController.java`.

## Conclusion

The Cultural Heritage Management System provides a robust, user-friendly interface for managing cultural heritage deletions. By following this manual, agency operators can efficiently perform their duties while maintaining data integrity and system security.

**Note**: This is a demonstration application. For production use, implement proper database integration, enhanced security measures, and comprehensive audit logging.