```markdown
# Address Management System - User Manual

## Overview

The Address Management System is a Java application designed for administrators to manage address archives. It implements the "Delete Address" use case as specified, providing a graphical user interface (GUI) for viewing, inspecting, and deleting addresses from the system.

## Main Features

- **Address Archive Viewing**: Display all addresses in a scrollable list
- **Detailed Address Inspection**: View complete details of any selected address
- **Address Deletion**: Delete addresses with associated validation checks
- **Real-time Status Updates**: Visual feedback for all operations
- **Error Handling**: Proper validation and error messages for failed operations
- **SMOS Server Integration**: Simulated server connection management

## System Requirements

### Software Requirements
- Java Runtime Environment (JRE) 8 or higher
- Java Development Kit (JDK) 8 or higher (for compilation)
- Operating System: Windows, macOS, or Linux

### Hardware Requirements
- Minimum 2GB RAM
- 100MB free disk space
- 1024x768 screen resolution or higher

## Installation

### Step 1: Install Java
If Java is not already installed on your system:

**Windows:**
1. Download the latest Java JRE from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html)
2. Run the installer and follow the setup wizard
3. Verify installation by opening Command Prompt and typing `java -version`

**macOS:**
1. Download the Java installer from the same Oracle website
2. Open the .dmg file and follow installation instructions
3. Verify with `java -version` in Terminal

**Linux:**
1. Open terminal and run:
   ```bash
   sudo apt update
   sudo apt install default-jre
   ```
2. Verify with `java -version`

### Step 2: Download the Application
Download the Java source file (`main.java` or `AddressManager.java`) to your computer.

### Step 3: Compile the Application
Open a terminal/command prompt and navigate to the directory containing the Java file:

```bash
javac main.java
```

Or if using the AddressManager version:

```bash
javac AddressManager.java
```

### Step 4: Run the Application
```bash
java main
```

Or:

```bash
java AddressManager
```

## Using the Application

### Login and Initial Setup
1. When you start the application, it automatically logs you in as an administrator
2. The main window appears with:
   - A title bar showing "Address Management - Administrator"
   - An address list on the left panel
   - Address details panel on the right
   - Control buttons at the bottom
   - Status bar showing current status

### Viewing Addresses
1. The left panel shows all available addresses in the format: "ID - Street, City"
2. Scroll through the list to see all addresses

### Viewing Address Details
1. **Precondition**: You must be logged in as an administrator
2. Click on any address in the list
3. The right panel will display detailed information including:
   - Address ID
   - Street name
   - City
   - Associated classes status (YES/NO)
   - Deletion readiness status

### Deleting an Address
1. **Preconditions**:
   - Logged in as administrator
   - Have viewed address details (completed "viewdettaglizzazione")
   - Address details are displayed
   - "Delete" button is enabled (becomes active after selecting an address)

2. **Deletion Process**:
   a. Select the address you want to delete from the list
   b. Review the details in the right panel
   c. Click the "Delete Address" button
   d. A confirmation dialog will appear - click "Yes" to proceed
   e. The system will:
     - Check if the address has associated classes
     - If YES: Show error message "Unable to delete the address, delete the associated classes and try again"
     - If NO: Delete the address and update the list
     - Display the updated address list
     - Interrupt connection to SMOS server (simulated)

### Buttons and Controls

#### Delete Address Button
- **Function**: Deletes the currently selected address
- **State**: Enabled only when an address is selected
- **Validation**: Checks for associated classes before deletion

#### Refresh List Button
- **Function**: Updates the address list and resets the interface
- **Use**: Use this after operations to see current state

#### Exit Button
- **Function**: Closes the application
- **Note**: Any unsaved changes will be lost

### Status Indicators

#### Status Label (Bottom of Window)
- **Blue**: Normal operations, instructions
- **Green**: Success messages (e.g., "Address deleted successfully")
- **Red**: Error messages (e.g., deletion failures)

#### Address Status in Details
- "Cannot delete - has associated classes": Address has dependencies
- "Ready for deletion": Address can be safely removed

## Use Case Walkthrough

### Complete "Delete Address" Workflow

1. **Prerequisites Met**:
   - User is logged as administrator (simulated automatically)
   - User has completed "viewdettaglizzazione" (viewing detailed address information)

2. **Step-by-Step Execution**:
   ```
   1. Launch application
   2. Click on an address in the list (view details)
   3. Click "Delete Address" button
   4. Confirm deletion in dialog box
   5. System checks for associated classes
      - IF associated classes exist → Show error, stop
      - IF no associated classes → Proceed
   6. System deletes the address
   7. System displays updated address list
   8. System interrupts SMOS server connection (simulated)
   ```

### Common Scenarios

#### Successful Deletion
1. Select address "1 - 123 Main St, Springfield" (no associated classes)
2. Click Delete → Confirm → Success message appears
3. Address disappears from list
4. Status shows "Address deleted successfully"

#### Failed Deletion (Associated Classes Exist)
1. Select address "2 - 456 Oak Ave, Shelbyville" (has associated classes)
2. Click Delete → Confirm → Error dialog appears
3. Error message: "Unable to delete the address, delete the associated classes and try again"
4. Address remains in list
5. Details panel shows failure reason

## Troubleshooting

### Common Issues

#### Application Won't Start
- **Error**: "Could not find or load main class"
- **Solution**: Ensure you're in the correct directory and compiled the file first
- **Verify**: Run `javac -version` to check compiler installation

#### Delete Button Disabled
- **Cause**: No address selected or address details not displayed
- **Solution**: Click on an address in the list first

#### Compilation Errors
- **Common errors**: Missing Swing libraries
- **Solution**: Ensure you have Java SE with Swing support installed

#### GUI Display Issues
- **Problem**: Buttons or text not visible
- **Solution**: Ensure screen resolution is at least 1024x768
- **Alternative**: Maximize the window

### Logs and Debugging
The application prints console logs for:
- SMOS server connection status
- Deletion operations
- Error conditions

Check your terminal/command prompt for these messages.

## Sample Data

The application includes three sample addresses for testing:

1. **Address ID 1**: 123 Main St, Springfield
   - No associated classes
   - Can be deleted successfully

2. **Address ID 2**: 456 Oak Ave, Shelbyville
   - Has associated classes
   - Cannot be deleted (will show error)

3. **Address ID 3**: 789 Pine Rd, Capital City
   - No associated classes
   - Can be deleted successfully

## Postconditions

After successful address deletion:
1. The address is removed from the archive
2. The list is updated to reflect the change
3. Connection to SMOS server is interrupted (simulated in console)
4. User receives visual confirmation of success

## Security Notes

- This is a demonstration application
- In production systems, additional security measures would be required:
  - Actual user authentication
  - Database connection security
  - Input validation
  - Audit logging

## Support

For issues with this application:
1. Check the troubleshooting section above
2. Verify Java installation and version
3. Ensure all preconditions are met before attempting deletions
4. Review console output for error messages

## Version Information

- **Application Version**: 1.0
- **Java Version Required**: 8 or higher
- **Use Case Implemented**: Delete Address (Administrator)
- **Last Updated**: [Current Date]

---

**Note**: This application is designed for demonstration purposes and implements the specific use case requirements as specified. Modifications may be needed for production environments.
```