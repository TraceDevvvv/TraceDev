# Address Management System - User Manual

## Overview

The Address Management System is a Java-based application designed for administrators to manage address archives. It implements the "EnterNewAddress" use case, allowing authorized users to add new addresses to the system archive with proper validation and error handling.

## System Requirements

### Hardware Requirements
- Processor: 1 GHz or faster
- RAM: 2 GB minimum (4 GB recommended)
- Storage: 50 MB available space

### Software Requirements
- Operating System: Windows, macOS, or Linux
- Java Development Kit (JDK): Version 8 or higher (Version 11+ recommended)
- Terminal/Command Prompt access

## Installation Guide

### Step 1: Install Java
1. **Windows/Mac/Linux Users:**
   - Download JDK from [Oracle's official website](https://www.oracle.com/java/technologies/javase-downloads.html)
   - Follow installation instructions for your operating system
   - Verify installation by opening terminal/command prompt and typing:
     ```bash
     java -version
     ```

### Step 2: Download Application Files
Download the following files to a single directory:
- `addressmanager.java` - Core archive management logic
- `addressmanagementapp.java` - Main application interface
- `run.bat` (Windows) or `run.sh` (Linux/Mac) - Execution scripts (optional)

### Step 3: Compile the Application
#### Using Scripts:
- **Windows:** Double-click `run.bat` or run from Command Prompt
- **Linux/Mac:** 
  ```bash
  chmod +x run.sh
  ./run.sh
  ```

#### Manual Compilation:
Open terminal/command prompt in the directory containing the files and run:
```bash
javac addressmanager.java addressmanagementapp.java
```

## User Guide

### Starting the Application
After successful compilation, run:
```bash
java AddressManagementApp
```

### Login Procedure
1. **Default Credentials:**
   - Username: `admin`
   - Password: `admin123`
2. You have 3 login attempts before system lockout
3. Successful login displays: "Welcome, Administrator!"

### Main Menu Options

#### 1. View Addresses (Precondition)
- **Purpose:** View current address archive
- **Prerequisite:** Must complete before adding new addresses
- **Display:** Shows all addresses with numbering
- **Note:** Empty archive will display "No addresses in the archive"

#### 2. Click 'New Address' Button
- **Requirements:** Must complete Option 1 first
- **Form Fields:** Address name (2-100 characters)
- **Allowed Characters:** Alphanumeric, spaces, hyphens, commas, periods, #
- **Validation:** Automatic format and length checking
- **Cancel Operation:** Type "cancel" at any time

#### 3. Search Addresses
- **Function:** Search archive by keyword
- **Case-insensitive:** Searches across all addresses
- **Results:** Displays matching addresses with numbering

#### 4. View System Statistics
- **Information Displayed:**
  - Total addresses in archive
  - Current user role
  - Address viewing status

#### 5. Logout and Exit
- **Action:** Safely terminates application
- **Cleanup:** Properly closes system resources

### Address Entry Workflow

#### Step-by-Step Process:
1. **Login** → Verify administrator credentials
2. **View Addresses** → Meet precondition for new address entry
3. **Select 'New Address'** → Access address entry form
4. **Form Completion:**
   - Enter address name following format rules
   - Address must be 2-100 characters
   - Use allowed characters only
5. **Save Process:**
   - Click "Save" button (simulated in console)
   - System validates data
   - Inserts address into archive
   - Returns success/failure notification

### Error Handling

#### Validation Errors:
1. **Empty Address:**
   - Message: "Address name cannot be empty"
   - Action: Re-enter valid address

2. **Format Errors:**
   - Invalid characters detected
   - Length violations (too short/long)
   - Duplicate address detection

3. **Server Errors:**
   - Simulated SMOS server interruptions (20% chance)
   - Message: "Connection to SMOS server interrupted"

#### Error Recovery:
- System gracefully handles failures
- Returns to main menu without crashing
- Clear error messages guide corrective action

## Key Features

### Archive Management
- **Singleton Pattern:** Single instance ensures consistent data management
- **Thread Safety:** Synchronized methods prevent data corruption
- **Data Persistence:** In-memory storage during session

### Address Validation
- **Length Checks:** 2-100 character limits
- **Format Validation:** Regex pattern matching
- **Duplicate Prevention:** Case-insensitive comparison
- **Real-time Feedback:** Immediate error notification

### User Experience
- **Intuitive Menu:** Number-based navigation
- **Clear Instructions:** Step-by-step guidance
- **Progress Feedback:** Success/failure notifications
- **Error Recovery:** Graceful failure handling

## Advanced Features

### Address Search
 - **Function:** Full-text search across all addresses
 - **Options:** Case-insensitive, partial matches
 - **Results:** Numbered list of matches

### System Statistics
 - **Metrics:** Address count, user status, precondition status
 - **Use:** Monitoring and audit trail

## Troubleshooting

### Common Issues:

1. **Compilation Errors:**
   - Ensure Java is properly installed
   - Check file encoding (UTF-8 required)
   - Verify all files are in same directory

2. **Login Failures:**
   - Verify username/password (admin/admin123)
   - Check caps lock status
   - Reset by restarting application

3. **Address Entry Issues:**
   - Ensure proper format (alphanumeric + allowed symbols)
   - Check length requirements (2-100 characters)
   - Verify no duplicates exist

4. **Server Connection Issues:**
   - Simulated errors are random (20% probability)
   - Retry operation after error
   - No actual network configuration required

### Quick Fixes:
- **Application Won't Start:**
  ```bash
  java -version  # Verify Java installation
  javac *.java   # Recompile all files
  ```

- **Empty Archive:**
  - Normal behavior - first-time users start with empty archive
  - Add addresses using the workflow described above

## Security Notes

### Current Implementation:
- **Demo Credentials:** admin/admin123 (change for production)
- **Memory Storage:** Addresses stored in RAM (not persistent)
- **Session-based:** Data lost on application exit

### For Production Use:
- Implement secure password storage/hashing
- Add database persistence
- Implement role-based access control
- Add audit logging

## Support and Maintenance

### Getting Help:
- Review this manual for common issues
- Check error messages for specific guidance
- Contact system administrator for complex issues

### Application Updates:
- Recompile after code modifications
- Test all workflows after changes
- Backup existing archives before major updates

## Frequently Asked Questions

**Q1: Can I change the administrator password?**
A: Currently hardcoded for demo purposes. Modify `AddressManagementApp.java` login() method for custom credentials.

**Q2: Where are addresses stored permanently?**
A: Current version uses in-memory storage. For persistence, modify `ArchiveManager.java` to use file or database storage.

**Q3: Can multiple users access simultaneously?**
A: Current version is single-user. The architecture supports multi-user extension through proper synchronization.

**Q4: How do I export addresses?**
A: Add export functionality to `ArchiveManager.java` using file I/O operations.

**Q5: Is the server error simulation required?**
A: No, modify the probability in `ArchiveManager.insertAddress()` method to adjust or remove simulation.

## Best Pract

1. **Regular Backups:** Export addresses periodically
2. **Password Security:** Change default credentials in production
3. **Session Management:** Logout after completing tasks
4. **Data Validation:** Always verify address format before submission
5. **Error Review:** Check error messages for troubleshooting clues

---

*For additional support or feature requests, contact your system administrator or development team.*