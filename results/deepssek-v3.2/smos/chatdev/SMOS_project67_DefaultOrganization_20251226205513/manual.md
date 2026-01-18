```markdown
# Student Registration System - User Manual

## Overview

The Student Registration System is a Java-based desktop application that allows students to register for a system through a graphical user interface (GUI). The application collects essential student information and securely stores registration requests.

## Main Functions

1. **User Registration Form**
   - Collects student information: name, surname, mobile phone, email, username, password
   - Password confirmation field for accuracy
   - Real-time input validation

2. **Input Validation**
   - Validates all required fields are filled
   - Validates email format
   - Validates mobile phone number format (8-15 digits)
   - Validates password matching

3. **Secure Data Storage**
   - Passwords are hashed using SHA-256 before storage
   - Registration data is stored in `registrations.txt` file
   - Timestamp is automatically recorded for each registration

4. **User Interface Features**
   - Clean, intuitive GUI using Java Swing
   - Clear error messages for invalid inputs
   - Success confirmation upon successful registration
   - Form auto-clearing after successful submission
   - Cancel button to exit application

## System Requirements

### Prerequisites
- **Operating System**: Windows, macOS, or Linux
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Java Development Kit (JDK)**: Version 8 or higher (for compilation)
- **Disk Space**: Minimum 10 MB free space

## Installation Instructions

### Step 1: Install Java
1. **Check if Java is installed**:
   - Open terminal (macOS/Linux) or command prompt (Windows)
   - Type: `java -version`
   - If Java is installed, you'll see version information
   - Minimum required: Java 8 (1.8)

2. **Download and Install Java (if not installed)**:
   - Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/)
   - Download JDK 8 or higher for your operating system
   - Follow installation instructions for your OS

### Step 2: Set Up Environment Variables (Windows only)
1. **Open System Properties**:
   - Right-click "This PC" > Properties > Advanced system settings
   - Click "Environment Variables"

2. **Add Java to PATH**:
   - Under System Variables, find "Path" and click Edit
   - Add the path to your Java bin folder (e.g., `C:\Program Files\Java\jdk-1.8\bin`)
   - Click OK to save

3. **Set JAVA_HOME (optional but recommended)**:
   - Click "New" under System Variables
   - Variable name: `JAVA_HOME`
   - Variable value: Path to your JDK folder (e.g., `C:\Program Files\Java\jdk-1.8`)

## How to Use the Software

### Step 1: Download and Compile the Application

1. **Save the Java file**:
   - Create a new file named `RegistrationAtSite.java`
   - Copy the provided Java code into this file
   - Save it in a directory of your choice (e.g., `C:\StudentRegistration`)

2. **Compile the application**:
   - Open terminal/command prompt
   - Navigate to the directory containing the Java file:
     ```
     cd C:\StudentRegistration
     ```
   - Compile the Java program:
     ```
     javac RegistrationAtSite.java
     ```
   - This will create `RegistrationAtSite.class` and `RegistrationFrame.class` files

### Step 2: Run the Application

1. **Start the application**:
   ```
   java RegistrationAtSite
   ```

2. **Application window will appear**:
   - Title: "Student Registration"
   - Window is centered on your screen
   - Contains input fields and buttons

### Step 3: Fill Out the Registration Form

**Required Fields**:

1. **Name**:
   - Enter your first name
   - Example: `John`

2. **Surname**:
   - Enter your last name
   - Example: `Doe`

3. **Mobile Phone**:
   - Enter your mobile number
   - Must contain only digits (8-15 digits)
   - Example: `1234567890`

4. **Email**:
   - Enter your email address
   - Must be valid email format
   - Example: `john.doe@example.com`

5. **Username**:
   - Choose a username
   - Must be unique (not validated by system)
   - Example: `johndoe2023`

6. **Password**:
   - Create a password
   - Password is hidden while typing
   - No specific complexity requirements in this version

7. **Confirm Password**:
   - Re-enter your password exactly
   - Must match the password field

### Step 4: Submit the Registration

1. **Click "Submit" button**:
   - System validates all inputs
   - If validation fails, error messages appear
   - Each error must be corrected before proceeding

2. **Successful registration**:
   - Success message: "Registration request submitted successfully!"
   - Data is saved to `registrations.txt` file
   - Form fields are automatically cleared

3. **View registration record**:
   - Open `registrations.txt` in the same directory
   - Contains all registration records
   - Each entry includes timestamp and hashed password

### Step 5: Exit the Application

**Two ways to exit**:

1. **Cancel button**:
   - Click "Cancel" to immediately close the application

2. **Close window**:
   - Click the "X" in the window corner
   - Gracefully closes the application

## Error Messages and Troubleshooting

### Common Errors and Solutions:

1. **"All fields are required!"**
   - **Cause**: One or more fields are empty
   - **Solution**: Fill in all fields before submitting

2. **"Please enter a valid email address!"**
   - **Cause**: Invalid email format
   - **Solution**: Enter a valid email (e.g., user@domain.com)

3. **"Please enter a valid mobile phone number (8-15 digits only)!"**
   - **Cause**: Mobile number contains non-digits or wrong length
   - **Solution**: Enter 8-15 digits only (no spaces, dashes, or letters)

4. **"Passwords do not match!"**
   - **Cause**: Password and confirmation don't match
   - **Solution**: Re-enter matching passwords

5. **"Error saving registration: [error message]"**
   - **Cause**: File write permission issues or disk full
   - **Solution**: Check directory permissions or disk space

6. **"'java' is not recognized as an internal or external command"**
   - **Cause**: Java not installed or PATH not set correctly
   - **Solution**: Install Java and set environment variables properly

### File Locations:
- **Application file**: `RegistrationAtSite.java`
- **Compiled classes**: `RegistrationAtSite.class`, `RegistrationFrame.class`
- **Output file**: `registrations.txt` (created when first registration submitted)

## Security Features

### Password Security:
- **Hashing**: All passwords are hashed using SHA-256 algorithm
- **No plain text storage**: Passwords are never stored in plain text
- **One-way encryption**: Hashed passwords cannot be converted back to original

### Data Storage:
- **Local storage**: Data is stored locally in text file
- **No network transmission**: No data is sent over the network in this version
- **Simple backup**: Registration data can be backed up by copying `registrations.txt`

## Best Pract

1. **Password Management**:
   - Choose a strong, unique password
   - Do not use the same password as other accounts
   - Keep your password confidential

2. **Data Privacy**:
   - The application stores data locally
   - Keep your `registrations.txt` file secure
   - Consider encrypting the file for additional security

3. **Backup Recommendations**:
   - Regularly backup the `registrations.txt` file
   - Store backups in a secure location
   - Consider version control for important registrations

## Uninstallation

To remove the application:

1. **Delete compiled files**:
   - Delete `RegistrationAtSite.class`
   - Delete `RegistrationFrame.class`

2. **Optional**: Delete source file:
   - Delete `RegistrationAtSite.java` (keep if you need to recompile)

3. **Optional**: Delete registration data:
   - Delete `registrations.txt` if no longer needed
   - **Warning**: This permanently deletes all registration records

## Support and Contact

### Technical Issues:
- **Java installation problems**: Consult Oracle's Java documentation
- **Compilation errors**: Ensure JDK is installed, not just JRE
- **Runtime errors**: Check Java version compatibility

### Application Issues:
- **Input not accepted**: Verify all field requirements are met
- **Data not saving**: Check file write permissions
- **Window not displaying**: Ensure Java Swing is supported on your system

### Future Enhancements:
- Network database integration
- Email verification system
- Password strength requirements
- User login functionality
- Admin dashboard for viewing registrations

---
*Note: This application is for demonstration purposes. For production use, implement additional security measures and database integration.*
```