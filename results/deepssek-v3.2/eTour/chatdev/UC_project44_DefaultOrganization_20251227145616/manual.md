```markdown
# Convention Request System - User Manual

## Overview

The Convention Request System is a Java-based application that enables Restaurant Point Operators to create and submit convention requests to designated agencies. This system streamlines the process of establishing conventions between restaurants and agencies, ensuring proper validation and communication throughout the workflow.

## Main Features

### 1. **User Authentication Simulation**
   - Simulated authentication system for Restaurant Point Operators
   - Entry point only for authenticated users

### 2. **Comprehensive Form Collection**
   - Restaurant name input
   - Agency name input
   - Convention start and end dates (YYYY-MM-DD format)
   - Additional terms and conditions field

### 3. **Intelligent Data Validation**
   - Empty field detection
   - Date format validation
   - Logical date checking (start date before end date)
   - Future date validation
   - Comprehensive error messaging

### 4. **Confirmation Workflow**
   - Data review screen
   - Explicit confirmation requirement
   - Cancellation support at multiple points

### 5. **Server Communication**
   - Simulated server connection
   - Network delay simulation
   - Connection interruption handling
   - Success/failure notifications

## System Requirements

### Software Requirements
- **Java Development Kit (JDK) 8 or higher**
  - Required for LocalDate and Java 8+ features
  - Download from: https://www.oracle.com/java/technologies/downloads/
  - Or use OpenJDK from: https://adoptium.net/

### Hardware Requirements
- Minimum 512MB RAM
- 128MB free disk space
- Any modern CPU (1GHz or faster)

## Installation Guide

### Step 1: Install Java
1. **Windows/Mac Users:**
   - Download JDK from Oracle's website
   - Run the installer and follow prompts
   - Set JAVA_HOME environment variable (optional but recommended)

2. **Linux Users:**
   ```bash
   # Ubuntu/Debian
   sudo apt update
   sudo apt install openjdk-11-jdk
   
   # Fedora/RHEL
   sudo dnf install java-11-openjdk-devel
   
   # Verify installation
   java -version
   javac -version
   ```

### Step 2: Download the Application Files
1. Create a project directory:
   ```bash
   mkdir ConventionRequestSystem
   cd ConventionRequestSystem
   ```

2. Create three Java files:

   **Main.java** - Main application file
   ```java
   import java.util.Scanner;
   import java.time.LocalDate;
   import java.time.format.DateTimeParseException;
   import java.time.format.DateTimeFormatter;
   import java.time.format.ResolverStyle;
   
   public class Main {
       // All the classes and methods from the provided code
       // (Copy the complete Main class code here)
   }
   ```

   **ValidationResult.java** - Data validation class
   ```java
   public class ValidationResult {
       public boolean isValid;
       public String errorMessage;
       
       public ValidationResult(boolean isValid, String errorMessage) {
           this.isValid = isValid;
           this.errorMessage = errorMessage;
       }
       
       public boolean isValid() {
           return isValid;
       }
       
       public String getErrorMessage() {
           return errorMessage;
       }
   }
   ```

### Step 3: Verify File Structure
```
ConventionRequestSystem/
├── Main.java
└── ValidationResult.java
```

## How to Use the System

### Starting the Application

1. **Open Command Prompt/Terminal:**
   ```bash
   cd path/to/ConventionRequestSystem
   ```

2. **Compile the Java Files:**
   ```bash
   javac *.java
   ```

3. **Run the Application:**
   ```bash
   java Main
   ```

### Step-by-Step Usage Guide

#### Step 1: System Initialization
- The system starts with an authentication simulation
- You'll see the welcome message:
  ```
  === Welcome to Convention Request System ===
  User authenticated successfully.
  ```

#### Step 2: Creating a Convention Request
1. **Enable Functionality:**
   - System displays: "Convention Request functionality enabled."

2. **Fill Out the Form:**
   ```
   === Convention Request Form ===
   Enter Restaurant Name: [Your restaurant name]
   Enter Agency Name: [Agency name]
   Enter Convention Start Date (YYYY-MM-DD): [e.g., 2024-12-01]
   Enter Convention End Date (YYYY-MM-DD): [e.g., 2025-11-30]
   Enter Additional Terms: [Any special terms]
   ```

   **Example Valid Input:**
   ```
   Restaurant Name: Bella Italia
   Agency Name: Tourism Board
   Start Date: 2024-12-01
   End Date: 2025-11-30
   Additional Terms: 10% commission for group bookings
   ```

#### Step 3: Data Validation
- System automatically validates your input
- If errors exist, you'll see:
  ```
  Validation failed: [error message]
  Activating use case Errored.
  ```

- If valid, you'll see a review screen

#### Step 4: Review and Confirm
```
=== Please review your convention request ===
Convention ID: CONV-1234567890123
Restaurant: Bella Italia
Agency: Tourism Board
Start Date: 2024-12-01
End Date: 2025-11-30
Terms: 10% commission for group bookings
=============================================

Do you confirm the request? (yes/no):
```

#### Step 5: Submission
- Type "yes" to confirm or "no" to cancel
- If confirmed:
  ```
  Sending convention request to server...
  Convention request sent successfully.
  Notification about the call for the Convention to the Agency has been sent.
  ```

## Best Pract for Data Entry

### Restaurant and Agency Names
- Use proper business names
- Avoid special characters unless necessary
- Maximum of 100 characters recommended

### Date Formatting
- **Required format:** YYYY-MM-DD
- **Examples of valid dates:**
  - 2024-12-01 (December 1, 2024)
  - 2025-06-15 (June 15, 2025)
  - 2024-02-29 (Leap year date - valid)

### Additional Terms
- Be specific and clear
- Include commission rates if applicable
- Mention special conditions
- Keep it concise (under 500 characters)

## Troubleshooting Guide

### Common Error Messages and Solutions

1. **"Restaurant name cannot be empty."**
   - **Solution:** Enter a valid restaurant name

2. **"Invalid date format. Please use YYYY-MM-DD format."**
   - **Solution:** Ensure dates are exactly 10 characters
   - Verify month is 01-12
   - Verify day is valid for the month

3. **"Start date must be before end date."**
   - **Solution:** End date must be later than start date

4. **"Start date cannot be in the past."**
   - **Solution:** Start date must be today or in the future

5. **"Connection to server ETOUR interrupted."**
   - **Solution:** Check your internet connection
   - Restart the application

### Compilation Errors

**Error:** "cannot find symbol"
- **Solution:** Ensure all Java files are in the same directory
- Run: `javac Main.java ValidationResult.java`

**Error:** "UnsupportedClassVersionError"
- **Solution:** Update to JDK 8 or higher
- Check Java version: `java -version`

### Runtime Issues

**Application closes immediately:**
- Run from command line to see error messages
- Ensure terminal has correct permissions

**Input not working properly:**
- Ensure you're typing in the correct window
- Check for special characters causing issues

## Advanced Features

### Testing Edge Cases
The system includes methods for testing various scenarios:

1. **Simulating Server Disconnection:**
   ```java
   // In the code, you can simulate server issues:
   ConventionRequest.setServerConnected(false);
   ```

2. **Testing Date Validation:**
   - Try invalid dates like 2024-13-01
   - Test dates in the past
   - Test end dates before start dates

### Customization Options

**Modifying Date Format:**
- Edit the DATE_FORMATTER in ConventionRequest class
- Example: Change to "dd/MM/yyyy" for European format

**Adding More Validation:**
- Extend the `validateConventionData` method
- Add checks for business-specific requirements

## Security Considerations

### Authentication
- Current implementation simulates authentication
- Production systems should implement proper authentication
- Consider adding password protection

### Data Protection
- Convention data is stored only in memory during session
- No persistent storage in current implementation
- Add encryption for production use

### Network Security
- Current server communication is simulated
- Real implementation should use HTTPS
- Implement proper API authentication

## Testing Your Implementation

### Quick Test Scenarios

1. **Happy Path Test:**
   - Enter all valid data
   - Confirm "yes"
   - Should see success message

2. **Validation Failure Test:**
   - Leave restaurant name blank
   - Should see validation error

3. **Cancellation Test:**
   - Enter valid data
   - Type "no" when asked for confirmation
   - Should see cancellation message

4. **Server Disconnect Test:**
   - Uncomment server disconnect simulation
   - Test system behavior

## Support and Maintenance

### Getting Help
1. **Check Error Messages:** Most issues are explained in error messages
2. **Verify Java Installation:** Ensure Java is properly installed
3. **Review Input Format:** Double-check date formats

### Version Information
- **Current Version:** 1.0
- **Java Version:** Requires JDK 8+
- **Last Updated:** November 2023

### Future Updates
- Database integration for persistent storage
- Email notification system
- PDF generation for convention documents
- Multi-language support
- Web interface version

## Glossary

**Convention:** A formal agreement between a restaurant and an agency
**Restaurant Point Operator:** Authorized restaurant representative
**Agency:** Organization establishing conventions with restaurants
**ETOUR:** External server system for processing requests
**Validation:** Process of checking data correctness and completeness

## Contact Information

For technical support or feature requests:
- **System Administrator:** [Your organization's admin contact]
- **Technical Support:** [Support email/phone]
- **Development Team:** [Development team contact]

---

**Important Note:** This is a simulation system for convention requests. For actual business use, additional security features, data persistence, and integration with existing systems would be required.
```