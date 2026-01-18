# ModifyDataRefreshmentPointAgency System User Manual

## Introduction

The **ModifyDataRefreshmentPointAgency System** is a comprehensive Java-based application designed for agency operators to manage and modify refreshment point (point of rest) data. This system implements the complete workflow specified in the use case documentation, providing a secure, user-friendly interface for editing refreshment point information.

### Key Features

- **Secure User Authentication**: Agency operator login with credential verification
- **Refreshment Point Management**: View, select, and modify point of rest data
- **Form-Based Editing**: Interactive form for modifying refreshment point attributes
- **Data Validation**: Comprehensive validation to ensure data integrity
- **Transaction Safety**: Form locking to prevent multiple submissions
- **Error Handling**: Robust error management and logging system
- **Server Connectivity**: ETOUR server communication simulation
- **Real-time Updates**: Live data modification and storage

## System Requirements

### Software Prerequisites
- **Java Development Kit (JDK) 8 or higher**
- **Terminal/Command Line Interface**
- **Basic text editor** (optional, for code review)

### Hardware Requirements
- **Memory**: Minimum 512 MB RAM
- **Storage**: 50 MB free disk space
- **Processor**: Any modern CPU capable of running Java

## Installation Guide

### Step 1: Install Java Development Kit (JDK)

#### Windows:
1. Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Run the installer
3. Set JAVA_HOME environment variable:
   ```
   setx JAVA_HOME "C:\Program Files\Java\jdk-version"
   ```
4. Add Java to PATH:
   ```
   setx PATH "%PATH%;%JAVA_HOME%\bin"
   ```

#### macOS:
```bash
brew install openjdk@11
```

#### Linux (Ubuntu/Debian):
```bash
sudo apt update
sudo apt install default-jdk
```

### Step 2: Verify Java Installation
```bash
java -version
javac -version
```

## Getting Started

### File Structure
The application consists of two main Java files:

1. **RefreshmentPoint.java** - Data model class
2. **ModifyDataRefreshmentPointAgency.java** - Main application class

### Compilation Instructions

1. **Save both Java files** in the same directory
2. **Open terminal/command prompt** in that directory
3. **Compile both files**:
   ```bash
   javac RefreshmentPoint.java
   javac ModifyDataRefreshmentPointAgency.java
   ```
   Or compile both at once:
   ```bash
   javac *.java
   ```

### Running the Application
```bash
java ModifyDataRefreshmentPointAgency
```

## Usage Guide

### 1. Startup
Upon running the application, you'll see the welcome message:
```
=== Modify Data Refreshment Point Agency System ===
```

### 2. Login Process
The system requires authentication before accessing any features:
```
Enter agency operator username: [your_username]
Enter password: [your_password]
```

**Note**: For demonstration purposes, any non-empty username and password combination is accepted.

### 3. Main Menu
After successful login:
```
--- Main Menu (User: [username]) ---
1. View and select refreshment point to modify
2. View all refreshment points
3. Exit
Select option:
```

### 4. Key Functionality

#### Option 1: Modify Refreshment Point
This option guides you through the complete modification workflow:

##### Step A: Select Refreshment Point
```
--- Available Refreshment Points ---
ID  Name                    Status
------------------------------------------------
1   Rest Area A            Open
3   Traveler's Oasis       Under Maintenance
4   Desert Stop            Open
5   Alpine Rest Area       Open

0. Cancel operation
Select refreshment point ID:
```

**Note**: Only active points (not "Closed") are shown as per use case requirements.

##### Step B: View Current Data
The system displays the selected point's current information:
```
--- Current Data for Rest Area A ---
ID: 1
Name: Rest Area A
Location: Highway 1, Mile 45
Status: Open
Capacity: 50
Operating Hours: 24/7
```

##### Step C: Edit Form
Enter the edit form to modify data:
```
--- Edit Form ---
Enter new values (press Enter to keep current value):
[Form locked to prevent multiple submissions]
Name [Rest Area A]:
Location [Highway 1, Mile 45]:
Status (Open/Closed/Under Maintenance) [Open]:
Capacity [50]:
Operating Hours [24/7]:
```

**Note**: The form is automatically locked to prevent multiple submissions, satisfying quality requirements.

##### Step D: Data Validation
The system validates all entered data:
```
--- Validating Data ---
✓ All data is valid.
```

If invalid data is detected, the Errored use case is activated:
```
✗ ERROR: Status must be 'Open', 'Closed', or 'Under Maintenance'.
Activating Errored use case...
```

##### Step E: Confirmation
Review changes before saving:
```
--- Confirm Changes ---
Please review the changes:

Current Data:
Name: Rest Area A
Location: Highway 1, Mile 45
Status: Open
Capacity: 50
Operating Hours: 24/7

New Data:
Name: Enhanced Rest Area A
Location: Highway 1, Mile 45 (Northbound)
Status: Open
Capacity: 75
Operating Hours: 24/7

Do you want to save these changes? (yes/no):
```

##### Step F: Save Operation
The system connects to the ETOUR server and saves the data:
```
--- Saving Data ---
Connecting to ETOUR server...
✓ Connected to ETOUR server.
✓ Data successfully saved to ETOUR server!
✓ Refreshment point 'Enhanced Rest Area A' has been updated.
✓ System has been reporting the information required by the point of rest.
```

**Note**: The system simulates server connectivity and handles disconnection scenarios.

#### Option 2: View All Refreshment Points
Displays a comprehensive list of all refreshment points:
```
--- All Refreshment Points ---
ID  Name                    Location                Status           Capacity        Hours
------------------------------------------------------------------------------------------------
1   Rest Area A            Highway 1, Mile 45      Open            50              24/7
2   Mountain View Cafe     Scenic Route 7          Closed          30              9AM-6PM
...
Total points: 5
```

#### Option 3: Exit
Gracefully exits the application:
```
Logging out. Goodbye, [username]!
```

## Important System Behaviors

### 1. Form Locking
- Prevents multiple submissions during an active operation
- Automatically unlocks after successful completion or error
- Provides visual feedback: `[Form locked to prevent multiple submissions]`

### 2. Error Handling
The system handles various error scenarios:
- **Invalid login credentials**: Returns to login prompt
- **Connection interruption**: Activates Errored use case
- **Invalid data entry**: Provides specific error messages
- **Unexpected errors**: Logs details with timestamp and user information

### 3. Exit Conditions
As per use case specifications, the application exits when:
- Operator cancels the operation (selects 0 during point selection)
- ETOUR server connection is interrupted (simulated with 20% failure rate)
- User chooses to exit from main menu

### 4. Data Integrity
- All modifications are validated before saving
- Only active and functional points can be modified
- Built-in validation for all data fields
- Prevents negative or unrealistic capacity values (1-1000 range)

## Customization Options

### Sample Data Modification
To change sample data, edit the `initializeSampleData()` method in `ModifyDataRefreshmentPointAgency.java`.

### Authentication Enhancement
For production use, modify the `login()` method to include proper authentication logic.

### Server Connection
Adjust `checkServerConnection()` for real server integration:
- Replace simulated connection with actual network calls
- Implement timeout handling
- Add retry logic for better reliability

## Troubleshooting

### Common Issues

#### Issue 1: "Error: Could not find or load main class"
**Solution**: Ensure you're in the correct directory and compiled the class:
```bash
javac ModifyDataRefreshmentPointAgency.java
java ModifyDataRefreshmentPointAgency
```

#### Issue 2: "Compilation Error: Class not found"
**Solution**: Make sure both files are in the same directory and compile simultaneously:
```bash
javac *.java
```

#### Issue 3: Input issues on Windows
**Solution**: Use a proper command prompt (avoid older Windows Command Prompt limitations):
```bash
# Use Windows PowerShell or Windows Terminal
```

#### Issue 4: Authentication not working
**Solution**: Ensure you enter non-empty values for both username and password fields.

### Error Messages Guide

| Error Message | Cause | Solution |
|--------------|-------|----------|
| "Login failed. Exiting system." | Empty username/password | Enter valid credentials |
| "Form is locked. Another operation is in progress." | Previous operation in progress | Wait for operation to complete |
| "ETOUR server connection interrupted." | Simulated network failure | Try again (80% success rate) |
| "Invalid input. Please enter a number." | Non-numeric input for numeric fields | Enter numbers only |
| "Invalid selection." | Selected non-existent ID | Choose from displayed options |

## Best Pract

1. **Regular Backups**: Maintain backups of modified data
2. **Session Management**: Log out after completing operations
3. **Data Verification**: Always verify changes during confirmation
4. **Error Documentation**: Note error messages for technical support
5. **Network Stability**: Ensure stable connection for server operations

## Support and Contact

For technical support or feature requests, contact:
- **System Administrator**: [Your System Admin Contact]
- **Development Team**: [Your Development Team Contact]
- **Documentation**: Refer to code comments for implementation details

## Version Information

- **Current Version**: 1.0.0
- **Last Updated**: [Current Date]
- **Java Compatibility**: JDK 8+
- **License**: Proprietary - Internal Use Only

## Security Notes

- This is a demonstration system
- For production use, implement:
  - Encrypted password storage
  - SSL/TLS for network communication
  - Database encryption
  - Role-based access control
  - Audit logging

---

**Remember**: Always ensure you have proper authorization before modifying refreshment point data. Unauthorized modifications may affect system operations and user experience.