```markdown
# Digital Register System - User Manual

## Overview
The Digital Register System is a Java-based console application designed for educational administrators to view and manage digital register records for different academic years. The system implements the "ViewRegistersList" use case with proper authentication, data filtering, and error handling.

## Main Functions
1. **Administrator Authentication**: Secure login simulation for authorized users
2. **Academic Year Selection**: Interactive selection of academic years from available data
3. **Register Viewing**: Display all digital registers filtered by selected academic year
4. **Connection Management**: Simulated SMOS server connection with interruption handling
5. **Input Validation**: Robust error handling for user inputs

## System Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Operating System**: Windows, MacOS, or Linux
- **Memory**: Minimum 512MB RAM
- **Storage**: Minimum 10MB free space

## Installation Guide

### Step 1: Install Java
1. Download and install Java Development Kit (JDK) from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
2. Verify installation by opening terminal/command prompt and running:
   ```bash
   java -version
   ```
3. Ensure `JAVA_HOME` environment variable is set correctly

### Step 2: Prepare the Application
1. Create a new directory for the application:
   ```bash
   mkdir DigitalRegisterSystem
   cd DigitalRegisterSystem
   ```
2. Create a new Java file named `ViewRegistersList.java`:
   ```bash
   touch ViewRegistersList.java
   ```
3. Copy the provided Java code into `ViewRegistersList.java`

### Step 3: Compile the Application
1. Compile the Java program:
   ```bash
   javac ViewRegistersList.java
   ```
2. Verify compilation completed successfully (no error messages should appear)

## How to Use the Software

### Starting the Application
1. Run the compiled program:
   ```bash
   java ViewRegistersList
   ```
2. The system will start with a welcome message:
   ```
   === Starting Digital Register System ===
   ```

### Step-by-Step Usage

#### Step 1: Administrator Login
- The system automatically logs you in as an administrator
- You'll see confirmation:
  ```
  Logging in as administrator...
  Administrator login successful!
  ```

#### Step 2: Access Digital Register
- The system simulates clicking the "Digital Register" button
- You'll see:
  ```
  === Digital Register System ===
  Click on the 'Digital Register' button (simulated)
  ```

#### Step 3: Select Academic Year
1. The system displays available academic years:
   ```
   Available Academic Years:
   1. 2023-2024
   2. 2022-2023
   3. 2024-2025
   ```
2. Enter the number corresponding to your desired academic year
3. Example: To select "2023-2024", enter `1`
4. To cancel the operation, enter `0`

#### Step 4: View Registers
- The system retrieves and displays all digital registers for the selected year
- Sample output:
  ```
  === Digital Registers for 2023-2024 ===
  Total registers found: 3
  ----------------------------------------
  Class: Grade 10A | Register ID: REG001 | Academic Year: 2023-2024
  Class: Grade 10B | Register ID: REG002 | Academic Year: 2023-2024
  Class: Grade 11A | Register ID: REG003 | Academic Year: 2023-2024
  ----------------------------------------
  ```

#### Step 5: Connection Status
- The system simulates SMOS server connection status
- Possible messages:
  - `Connection to SMOS server stable.` (normal operation)
  - `Warning: Connection to SMOS server interrupted. Data displayed from local cache.` (simulated interruption)

#### Step 6: Continue or Exit
- After viewing registers, you'll be prompted:
  ```
  Would you like to view registers for another year? (yes/no):
  ```
- Enter `yes` or `y` to view another year
- Enter `no` or any other response to exit

## Sample Data Included
The system comes with pre-loaded sample data for demonstration:
- **2023-2024**: Grade 10A, Grade 10B, Grade 11A
- **2022-2023**: Grade 9A, Grade 9B
- **2024-2025**: Grade 12A

## Error Handling
The system includes comprehensive error handling for:
- **Non-administrator access**: Prevents unauthorized users from accessing registers
- **Invalid year selection**: Validates user input and provides helpful error messages
- **Empty results**: Handles cases where no registers exist for selected year

## Troubleshooting

### Common Issues and Solutions

#### Issue: "java: command not found"
**Solution**: 
- Ensure Java is properly installed
- Check that Java is in your system PATH
- Restart your terminal/command prompt

#### Issue: Compilation errors
**Solution**:
- Verify all code is copied correctly into ViewRegistersList.java
- Ensure no extra characters or whitespace at the beginning of the file
- Check Java version compatibility

#### Issue: Program runs but shows no data
**Solution**:
- Verify you're selecting a valid academic year number
- Check that you're logged in as administrator (should be automatic)

#### Issue: Input not working in terminal
**Solution**:
- Ensure you're typing the number correctly (no extra spaces)
- Try hitting Enter after your input
- Restart the application

## Technical Notes
- The application uses in-memory data storage for demonstration
- Real implementation would connect to a database or SMOS server
- Authentication is simulated; production systems would implement proper credential validation
- Connection interruption is randomly simulated to demonstrate error handling

## Security Considerations
⚠️ **Production Implementation Notes**:
- Current authentication is simulated - implement proper authentication mechanisms
- Add encryption for data transmission in production
- Implement proper user session management
- Add audit logging for administrative actions

## Support
For additional support:
1. Review this manual for troubleshooting steps
2. Check Java documentation for installation issues
3. Contact your system administrator for production deployment

## Exit the Application
To properly exit:
1. Enter `no` when prompted to view another year
2. The system will display:
   ```
   Thank you for using Digital Register System. Goodbye!
   ```
3. The application will terminate automatically

---

**Version**: 1.0  
**Last Updated**: Current Date  
**Compatibility**: Java 8+  
**License**: Educational Use
```