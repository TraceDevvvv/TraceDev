```markdown
# Address Details Viewer - User Manual

## Overview
The **Address Details Viewer** is a Java-based application that implements the "ViewAddressDetails" use case for administrators. This software allows authorized users to view detailed information about addresses after logging in and accessing an address list. The application simulates a complete workflow including user authentication, address list viewing, and detailed address information display.

## System Requirements
### Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher
- **Terminal/Command Prompt**: For running the application
- **Text Editor or IDE**: For viewing and modifying code (optional)

### Supported Platforms
- Windows 10/11
- macOS 10.15+
- Linux distributions (Ubuntu 20.04+, CentOS 8+)

## Installation

### 1. Install Java
If you don't have Java installed, follow these steps:

**Windows:**
1. Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [Adoptium](https://adoptium.net/)
2. Run the installer and follow the prompts
3. Add Java to PATH during installation or manually

**macOS:**
```bash
brew install openjdk@11
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install openjdk-11-jdk
```

### 2. Verify Installation
Open a terminal/command prompt and run:
```bash
java -version
```
You should see Java version information. If not, verify your PATH settings.

### 3. Download and Setup Application Files
1. Create a new directory for the application:
```bash
mkdir AddressDetailsViewer
cd AddressDetailsViewer
```

2. Create two Java files in this directory:
   - **Address.java** - Contains the Address class
   - **Main.java** - Contains the main application logic

3. Copy the provided code into the respective files from your source.

## Getting Started

### 1. Compile the Application
In the terminal, navigate to your application directory and run:
```bash
javac Address.java Main.java
```
This will create `.class` files for both classes.

### 2. Run the Application
```bash
java Main
```

## Application Flow

### Step 1: User Login
When you start the application, you'll see:
```
=== Address Details Viewer ===
Enter username:
Enter password:
```
- Enter any non-empty username and password
- Example: `admin` for username and `password123` for password
- **Authentication**: Any non-empty credentials will be accepted in this simulation

### Step 2: View Address List
After successful login:
```
--- Simulating 'ViewingLenchIndirizzi' use case ---

=== Address List ===
1. Main Street
2. Broadway
3. Oxford Street
4. Champs-Élysées
===================
```
This displays the preloaded address list as part of the ViewingLenchIndirizzi use case simulation.

### Step 3: Select Address for Details
```
--- Address Selection ---
Enter address number to view details (1-4):
```
- Enter a number between 1 and 4
- Press Enter to confirm your selection

### Step 4: View Address Details
The system displays detailed information about the selected address:
```
=== Address Details ===
Address Name: [Selected Address Name]
City: [City]
Country: [Country]
Zip Code: [Zip Code]
=======================
Postcondition: User has displayed detailed information relating to a single address.
```

### Step 5: Server Disconnection
Finally, the application simulates SMOS server disconnection:
```
Postcondition: Connection to the SMOS server interrupted.
Application terminated.
```

## Features

### Core Functionality
1. **User Authentication Simulator**
   - Simulates login process
   - Validates non-empty credentials
   - Sets user session state

2. **Address Management**
   - Preloaded sample addresses
   - Comprehensive address details display
   - Address selection with validation

3. **Use Case Simulation**
   - Complete ViewAddressDetails workflow
   - Precondition validation (login + address list viewing)
   - Postcondition handling (server disconnection)

### Data Model
The application uses an `Address` class that contains:
- **Name**: Street/address name
- **City**: City location
- **Country**: Country location
- **Zip Code**: Postal code

### Error Handling
The application handles:
- **Empty credentials**: Prevents login
- **Invalid address selection**: Validates input range
- **Non-numeric input**: Catches number format exceptions
- **Empty address list**: Prevents details viewing

## How to Customize

### Add New Addresses
To add more sample addresses, modify the `initializeAddressList()` method in Main.java:
```java
addressList.add(new Address("Your Street", "Your City", "Your Country", "Your Zip"));
```

### Modify Authentication
For more realistic authentication, update the `simulateLogin()` method with actual credential validation.

### Change Sample Data
Edit the parameters in the Address constructor calls within `initializeAddressList()`.

## Troubleshooting

### Common Issues

1. **"Error: Could not find or load main class Main"**
   - Ensure you're in the correct directory
   - Verify the Main.class file exists
   - Try: `java -cp . Main`

2. **"javac: command not found"**
   - Java is not installed or not in PATH
   - Reinstall Java or update PATH variable

3. **"Login failed - credentials cannot be empty."**
   - You entered empty username or password
   - Provide any non-empty values

4. **Number input errors**
   - Only enter numbers 1-4 for address selection
   - Do not include spaces or other characters

### Debug Mode
For troubleshooting, you can add debug statements by modifying the Main.java file:
```java
System.out.println("DEBUG: Current selection index = " + choice);
```

## Security Notes
⚠️ **Important Security Considerations**:
- This application uses simulated authentication for demonstration
- In production, implement proper authentication mechanisms
- Never use this code for real user authentication without security enhancements
- Consider using secure password storage and encryption

## Performance Considerations
- The application loads all addresses into memory
- For large address lists, consider implementing pagination
- Real implementation should connect to a database or external service

## Extending the Application
To extend this application for production use:
1. Replace simulated authentication with real authentication system
2. Connect to a database for address storage
3. Implement proper SMOS server connection and disconnection
4. Add GUI interface using JavaFX or Swing
5. Implement logging and monitoring

## Support
For issues with this application:
1. Check the troubleshooting section
2. Verify Java installation and configuration
3. Ensure correct file naming and compilation
4. Contact your system administrator for environment issues

---

*Note: This is a simulation application for demonstrating the ViewAddressDetails use case. For production deployment, additional security, validation, and error handling should be implemented.*
```