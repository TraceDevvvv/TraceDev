```markdown
# Address Management System - User Manual

## Overview
The Address Management System is a Java-based desktop application designed for administrators to view and manage address lists. The system features a secure login interface, address archive viewing capabilities, and simulated SMOS server connection management.

## System Architecture
The application follows a modular design with the following key components:
- **Main.java**: Application entry point
- **LoginFrame.java**: Secure login interface
- **AddressManagerFrame.java**: Main address management interface
- **AddressDatabase.java**: Simulated address archive
- **SMOSServerConnection.java**: Simulated server connection manager
- **Address.java**: Data model for address entries
- **Role.java**: User role enumeration

## Prerequisites

### System Requirements
- Java Development Kit (JDK) 8 or higher
- Minimum 2GB RAM
- 500MB free disk space

### Software Dependencies
- Java Swing (included with JDK)
- No external libraries required

## Installation

### Step 1: Install Java Development Kit
1. Download JDK from Oracle's official website: https://www.oracle.com/java/technologies/javase-downloads.html
2. Run the installer and follow platform-specific instructions
3. Set JAVA_HOME environment variable:
   - **Windows**:
     ```bash
     setx JAVA_HOME "C:\Program Files\Java\jdk-version"
     ```
   - **macOS/Linux**:
     ```bash
     export JAVA_HOME=/usr/lib/jvm/java-version
     ```

### Step 2: Verify Installation
Open terminal/command prompt and run:
```bash
java -version
javac -version
```
You should see Java version information displayed.

## Building and Running the Application

### Method 1: Using IDE (Recommended)
1. Download or clone the source code
2. Open in your preferred Java IDE (Eclipse, IntelliJ IDEA, or NetBeans)
3. Import all Java files into a new project
4. Run `Main.java` as the main class

### Method 2: Command Line Compilation
1. Create a directory structure:
   ```bash
   mkdir AddressManagementSystem
   cd AddressManagementSystem
   ```
2. Copy all `.java` files to this directory
3. Compile the application:
   ```bash
   javac *.java
   ```
4. Run the application:
   ```bash
   java Main
   ```

## Getting Started

### First Launch
1. Upon launching, you'll see the login window titled "Address Management System - Login"
2. The interface displays:
   - Username and password fields
   - Login button
   - Current SMOS server connection status

### Login Credentials
- **Default Administrator Credentials**:
  - Username: `admin`
  - Password: `admin123`

**Security Note**: In a production environment, these credentials should be stored securely in a database with proper encryption.

## Using the Address Management System

### Step 1: Login
1. Enter `admin` in the username field
2. Enter `admin123` in the password field
3. Click the "Login" button
4. Upon successful authentication, you'll see a welcome message and the main Address Manager window will open

### Step 2: View Address List
1. The Address Manager window automatically displays the address list upon login
2. The interface contains:
   - **Title**: "Address List" at the top
   - **Address Table**: Shows all addresses in the archive with columns for:
     - Name
     - Street
     - City
     - State
     - Zip Code
     - Country
   - **Status Panel**: Shows total address count and server connection status
   - **Control Buttons**: Refresh List and Exit

### Step 3: Understanding System Behavior
The application follows a specific sequence of events:
1. **Login**: User authenticates with administrator credentials
2. **Address Display**: System immediately shows the address list upon successful login
3. **Server Interruption**: As per requirements, the SMOS server connection is automatically interrupted after displaying the address list
4. **Status Update**: The server status changes from "Connected" (blue) to "Disconnected" (red)

## Features and Controls

### Address Table
- **Read-only Display**: The address table is non-editable for viewing purposes
- **Column Sorting**: Click column headers to sort addresses
- **Scroll Navigation**: Use scroll bars or mouse wheel to navigate long lists

### Control Buttons
1. **Refresh List Button**
   - Updates the address display with current archive data
   - Shows confirmation message upon completion
   - Does not re-establish server connection

2. **Exit Button**
   - Closes the application completely
   - All unsaved operations are terminated

### Server Status Indicators
- **Blue Text**: Server is connected
- **Red Text**: Server connection is interrupted
- Status updates automatically based on system events

## Sample Address Data
The system includes sample addresses for demonstration:
1. John Doe - 123 Main St, Springfield, IL 62704, USA
2. Jane Smith - 456 Oak Ave, Metropolis, NY 10001, USA
3. Bob Johnson - 789 Pine Rd, Gotham, CA 90210, USA
4. Alice Brown - 101 Maple Ln, Star City, TX 73301, USA
5. Charlie Wilson - 202 Elm Blvd, Central City, FL 33101, USA

## Troubleshooting

### Common Issues
1. **"Could not find or load main class Main"**
   - Ensure you're in the correct directory with compiled `.class` files
   - Verify compilation was successful

2. **Login fails even with correct credentials**
   - Check for typos in username/password
   - Ensure Caps Lock is off

3. **GUI appears corrupted or misaligned**
   - Update Java to latest version
   - Check system display scaling settings

4. **Application runs but no addresses appear**
   - Verify `AddressDatabase.java` contains the sample data initialization
   - Check console for error messages

### Console Output Monitoring
The application provides debug information in the console/terminal:
- Login success/failure messages
- Address loading status
- Server connection status updates
- Error messages (if any)

## Security Considerations

### Current Implementation
- Hardcoded credentials for demonstration only
- No persistent authentication
- No data encryption

### For Production Use
1. Implement proper user authentication (database-driven)
2. Add password hashing (SHA-256 or bcrypt)
3. Implement session management
4. Add audit logging
5. Include input validation and sanitization

## Extending the System

### Adding Real Database Connectivity
Replace `AddressDatabase.java` with:
1. JDBC connectivity for SQL databases
2. Connection pooling for performance
3. Prepared statements for security

### Adding Additional Features
1. **Address Management**:
   - Add/Edit/Delete functionality
   - Search and filter capabilities
   - Import/export options

2. **Enhanced UI**:
   - Multiple view modes (grid, list, cards)
   - Export to PDF/CSV
   - Printing support

3. **Server Management**:
   - Reconnect functionality
   - Server health monitoring
   - Connection logging

## Best Pract

### For Administrators
1. Change default credentials immediately
2. Regularly back up address data
3. Monitor server connection status
4. Use strong, unique passwords

### For Developers
1. Follow Java naming conventions
2. Add comprehensive error handling
3. Document all changes
4. Implement unit testing
5. Use version control

## Support and Contact

### Getting Help
- Check console output for error messages
- Review this manual for troubleshooting steps
- Verify Java installation and environment variables

### Reporting Issues
When reporting issues, include:
1. Java version (`java -version`)
2. Operating system and version
3. Steps to reproduce the issue
4. Screenshots of errors
5. Console output

## Uninstallation
To remove the application:
1. Delete all project files
2. No registry entries or system files are modified
3. No persistent data is stored locally

---

**Note**: This is a demonstration system designed to showcase the use case requirements. For production deployment, additional security, error handling, and data persistence features should be implemented.
```