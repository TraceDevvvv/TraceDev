```markdown
# Tourism Agency Account Management System

A comprehensive software solution for tourism agency operators to search for and modify tourist account information. This system follows a well-defined use case workflow to ensure data integrity and operational efficiency.

## 📋 Overview

The Tourism Agency Account Management System enables authorized agency operators to:
- Search for tourist accounts based on various criteria
- Select specific tourist accounts for modification
- Update tourist information through a user-friendly interface
- Validate data integrity and handle server connection issues

### Key Features
- **Search Functionality**: Find tourists by name, email, or passport number
- **Data Modification**: Edit comprehensive tourist profiles with validation
- **Connection Management**: Handle ETOUR server connections with automatic reconnection
- **Data Validation**: Comprehensive field validation to ensure data quality
- **User-Friendly Interface**: Intuitive GUI built with Java Swing

## 🛠️ Installation Requirements

### System Requirements
- **Operating System**: Windows 10+, macOS 10.12+, or Linux (any modern distribution)
- **Java Version**: Java SE Development Kit (JDK) 11 or higher
- **Memory**: Minimum 512MB RAM (1GB recommended)
- **Disk Space**: 50MB available storage

### Java Installation
1. **Download JDK**:
   - Visit [Oracle JDK download page](https://www.oracle.com/java/technologies/downloads/)
   - Download the appropriate version for your operating system
   - Alternatively, use OpenJDK from your package manager:
     - Ubuntu/Debian: `sudo apt-get install openjdk-11-jdk`
     - macOS: `brew install openjdk11`
     - Windows: Download from AdoptOpenJDK

2. **Set Environment Variables**:
   - **Windows**:
     - Set `JAVA_HOME` to your JDK installation path (e.g., `C:\Program Files\Java\jdk-11`)
     - Add `%JAVA_HOME%\bin` to your system PATH
   - **macOS/Linux**:
     ```bash
     export JAVA_HOME=$(dirname $(dirname $(readlink -f $(which javac))))
     export PATH=$JAVA_HOME/bin:$PATH
     ```

### Verification
Verify your installation by running:
```bash
java -version
```
You should see output similar to:
```
java version "11.0.15" 2022-04-19 LTS
Java(TM) SE Runtime Environment 18.9 (build 11.0.15+8-LTS-149)
Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.15+8-LTS-149, mixed mode)
```

## 📂 Project Structure

```
com/etour/agency/
├── MainApp.java              # Main application entry point
├── Tourist.java             # Tourist model class
├── TouristDAO.java          # Data Access Object interface
├── TouristDAOImpl.java      # DAO implementation with mock data
├── TouristService.java      # Service layer for business logic
├── ValidationUtils.java     # Validation utilities
├── SearchTouristFrame.java  # Search tourist GUI
└── ModifyTouristFrame.java  # Modify tourist GUI
```

## 🚀 Getting Started

### Step 1: Compile the Application
1. **Create project directory**:
   ```bash
   mkdir TourismAgencySystem
   cd TourismAgencySystem
   ```

2. **Create package structure**:
   ```bash
   mkdir -p com/etour/agency
   ```

3. **Copy all Java files** to `com/etour/agency/` directory

4. **Compile all classes**:
   ```bash
   javac com/etour/agency/*.java
   ```

### Step 2: Run the Application
```bash
java com.etour.agency.MainApp
```

## 📖 User Guide

### Main Screen - Search Tourist
When you launch the application, you'll see the **Search Tourist** screen:

![Search Screen Interface](https://placehold.co/800x500/4a90e2/white?text=Search+Tourist+Interface)
*Visual representation of the search interface*

#### Available Actions:
1. **Search Bar**:
   - Enter any text to search by: First Name, Last Name, Email, or Passport Number
   - Results update in real-time as you type

2. **Tourist Table**:
   - Displays tourist ID, name, email, phone, nationality, and passport number
   - Click on any tourist row to select it

3. **Action Buttons**:
   - **Refresh**: Reload all tourist data from the server
   - **Select Tourist to Modify**: Opens modification screen for selected tourist
   - **Exit**: Closes the application

### Modifying Tourist Information
#### Step 1: Select a Tourist
1. Use the search bar to find the tourist you want to modify
2. Click on the tourist's row in the table
3. Click **"Select Tourist to Modify"**

#### Step 2: Review and Modify Data
The modification screen displays all tourist information:

**Form Fields** (all fields marked with * are required):
- **Tourist ID**: Read-only identifier
- **First Name/Last Name**: 2-50 characters, letters only
- **Email**: Valid email format (e.g., user@example.com)
- **Phone**: International format (e.g., +1-555-0101)
- **Nationality**: Country of citizenship
- **Passport Number**: 6-12 alphanumeric characters
- **Date of Birth**: YYYY-MM-DD format (e.g., 1985-05-15)
- **Emergency Contact**: Contact information
- **Address**: Full address information

#### Step 3: Save Changes
1. Click **"Save Changes"** button
2. System validates all fields automatically
3. If validation fails: Error message shows specific issues to fix
4. If validation passes: Confirmation dialog appears
5. Click **"Yes"** to confirm changes or **"No"** to cancel

### Sample Tourist Data
The system comes with five pre-loaded tourist accounts for demonstration:

1. **John Doe** (ID: TOUR001)
   - Email: john.doe@example.com
   - Passport: A12345678
   - Nationality: American

2. **Maria Garcia** (ID: TOUR002)
   - Email: maria.garcia@example.com
   - Passport: B98765432
   - Nationality: Spanish

3. **Kenji Tanaka** (ID: TOUR003)
   - Email: kenji.tanaka@example.com
   - Passport: C55555555
   - Nationality: Japanese

4. **Sophie Martin** (ID: TOUR004)
   - Email: sophie.martin@example.com
   - Passport: D44444444
   - Nationality: French

5. **Ahmed Khan** (ID: TOUR005)
   - Email: ahmed.khan@example.com
   - Passport: E33333333
   - Nationality: Pakistani

## 🛡️ Error Handling

### Common Error Scenarios

#### 1. Connection Errors
- **Symptom**: "Connection to ETOUR server interrupted" message
- **Cause**: Simulated server disconnection (10% chance in demonstration)
- **Action**:
  - Click "Yes" to attempt reconnection
  - System automatically attempts to reconnect
  - If reconnection fails, returns to search screen

#### 2. Validation Errors
- **Symptom**: "Invalid Data" error dialog with specific field issues
- **Common Issues**:
  - Invalid email format
  - Invalid phone number (must be international format)
  - Name contains numbers or special characters
  - Date not in YYYY-MM-DD format
- **Action**: Correct the highlighted field and try again

#### 3. No Selection Error
- **Symptom**: "Select Tourist to Modify" button is disabled
- **Cause**: No tourist selected from the list
- **Action**: Click on a tourist row in the table to select it

### Troubleshooting Steps

1. **Application won't start**:
   ```bash
   # Check Java installation
   java -version
   
   # Check compilation
   javac com/etour/agency/MainApp.java
   ```

2. **GUI displays incorrectly**:
   - Ensure Java Swing is properly installed
   - Check display resolution and scaling settings
   - Verify Java version compatibility

3. **Persistent connection errors**:
   - The demonstration system simulates random connection failures
   - In production, this would involve actual server connectivity checks

## 🔧 Advanced Features

### Data Persistence
- **Mock Data**: Demo version uses in-memory data storage
- **Production Ready**: DAO interface allows easy integration with real databases
- **No Data Loss**: System works with copies to prevent accidental data corruption

### Validation Framework
- **Email Validation**: Standard email format checking
- **Phone Validation**: International phone number patterns
- **Passport Validation**: Country-agnostic passport number patterns
- **Date Validation**: Strict YYYY-MM-DD format enforcement

### Security Considerations
- **Read-only ID**: Tourist ID cannot be modified
- **Data Integrity**: All changes validated before saving
- **Confirmation Required**: User must confirm all modifications
- **Audit Trail**: Console logs track connection changes and updates

## 📊 Usage Best Pract

### For Agency Operators
1. **Before Modifying**:
   - Verify the correct tourist is selected
   - Review all current data carefully
   - Have updated information ready before starting modifications

2. **During Modification**:
   - Fill all required fields marked with *
   - Use standard formats for dates and phone numbers
   - Check for real-time validation feedback

3. **After Saving**:
   - Verify success message appears
   - Consider making a note of changes for agency records
   - The system automatically returns to search for next operation

### System Administration
1. **Backup Considerations**:
   - The mock implementation doesn't persist data between sessions
   - For production use, implement proper database backup procedures

2. **Monitoring**:
   - Check console output for connection status messages
   - Monitor for validation error patterns to identify common user mistakes

3. **Performance**:
   - All operations are performed locally in the demonstration version
   - Real server integrations may require network latency considerations

## 🔄 Lifecycle Management

### Application Flow
1. **Startup** → Search Tourist Screen
2. **Search/Select** → Search or select tourist
3. **Modification** → Update tourist data
4. **Validation** → System validates inputs
5. **Confirmation** → User confirms changes
6. **Update** → System saves changes
7. **Return** → Back to Search Tourist Screen

### Error Flow
1. **Error Detection** → System identifies issue
2. **User Notification** → Error message displayed
3. **Correction Guidance** → Specific issues highlighted
4. **Resolution** → User corrects and retries
5. **Acknowledgment** → Success or final failure message

## 📝 Field Specifications

### Mandatory Fields
| Field | Format | Constraints | Example |
|-------|--------|-------------|---------|
| First Name | Text | 2-50 letters only | "John" |
| Last Name | Text | 2-50 letters only | "Doe" |
| Email | Email format | Standard email pattern | user@example.com |
| Phone | Phone number | International format | +1-555-0101 |
| Nationality | Text | Non-empty | "American" |
| Passport | Alphanumeric | 6-12 characters | A12345678 |
| Date of Birth | Date | YYYY-MM-DD format | 1985-05-15 |
| Address | Text | Non-empty | "123 Main St" |
| Emergency Contact | Text | Non-empty | "+1-555-0102" |

### Optional Features
- **Search Optimization**: Real-time filtering as you type
- **Data Protection**: Original data preserved until confirmation
- **User Guidance**: Clear error messages with correction hints
- **Session Management**: Proper cleanup on exit

## 🆘 Support and Resources

### Quick Reference Commands
```bash
# Compile all files
find com/etour/agency -name "*.java" > sources.txt
javac @sources.txt

# Run application
java -cp . com.etour.agency.MainApp

# Compile and run in one command
javac com/etour/agency/*.java && java -cp . com.etour.agency.MainApp
```

### Common Issues and Solutions

**Issue**: "Error: Could not find or load main class"
**Solution**: Ensure you're in the correct directory (parent of com/) and using `-cp .`

**Issue**: Form doesn't validate correctly
**Solution**: Check that you're using correct formats, especially for dates and phone numbers

**Issue**: Application freezes
**Solution**: Use Ctrl+C to terminate, then restart with fresh compilation

### Getting Help
For issues with this demonstration system:
1. Review the error messages carefully
2. Check console output for additional debugging information
3. Verify all Java files are in `com/etour/agency/` directory
4. Ensure JDK 11+ is properly installed

## 🚀 Future Enhancements

The current demonstration version includes these potential areas for expansion:

### Planned Features
1. **Database Integration**: Replace mock data with actual database connection
2. **Advanced Search**: Add more search filters (nationality, date ranges, etc.)
3. **Bulk Operations**: Apply changes to multiple tourists simultaneously
4. **Reporting**: Generate reports of tourist information
5. **Export/Import**: Export data to CSV or import from external sources
6. **User Authentication**: Secure login for agency operators
7. **Audit Logging**: Track all changes made to tourist accounts
8. **Data Backup**: Automated backup functionality
9. **Multi-language Support**: Interface in multiple languages
10. **Accessibility**: Enhanced support for users with disabilities

### Integration Possibilities
- Integration with tourism booking systems
- Connection to government passport verification APIs
- Email notification system for account updates
- Mobile companion application for field operations

---

## 📄 License and Acknowledgments

This demonstration software is provided for educational and demonstration purposes. The architecture follows best pract for Java Swing applications and showcases proper separation of concerns between Model-View-Controller components.

**Note**: This is a demonstration version. For production use, additional security measures, data persistence, and error handling would be required.

---

*For questions or feedback about this demonstration system, please consult the implementation details in the source code. The system architecture is designed to be easily extensible for real-world tourism agency operations.*
```