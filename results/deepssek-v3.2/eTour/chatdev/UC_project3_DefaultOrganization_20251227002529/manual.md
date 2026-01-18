# Cultural Heritage Management System - User Manual

## 📖 Overview

The Cultural Heritage Management System is a Java-based desktop application designed for agency operators to manage and modify cultural heritage data. This application implements the "ModifyCulturalHeritage" use case, providing a complete workflow for searching, viewing, editing, and updating cultural heritage records with proper validation, security, and data integrity controls.

### Key Features:
- **Secure Agency Authentication** - Role-based access control for agency operators
- **Cultural Heritage Search** - Powerful search functionality to find cultural items
- **Data Modification** - Comprehensive form-based editing of cultural heritage records
- **Data Validation** - Built-in validation to ensure data quality and completeness
- **Transaction Safety** - Prevents multiple submissions and handles server interruptions
- **User-Friendly Interface** - Intuitive GUI with clear navigation and feedback

## 🛠️ Installation Requirements

### System Requirements:
- **Operating System**: Windows 8/10/11, macOS 10.14+, or Linux with GUI support
- **RAM**: Minimum 2GB (4GB recommended)
- **Disk Space**: 50MB free space
- **Screen Resolution**: 1024×768 or higher

### Software Dependencies:
1. **Java Runtime Environment (JRE)**:
   - Version: Java 8 or higher
   - Download: [Oracle Java](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://openjdk.org/)
   
   **To check your Java version:**
   ```bash
   java -version
   ```

2. **Java Development Kit (JDK)** (for compilation only):
   - Required if you need to modify and recompile the source code
   - Version: JDK 8 or higher

## 📁 Application Structure

The application consists of two main Java files:

1. **`ModifyCulturalHeritage.java`** - Main application class with GUI
2. **`Agency.java`** - Agency authentication and management class

## 🚀 Installation Guide

### Option 1: Ready-to-Run JAR File (Recommended)

1. **Download the compiled application**:
   - Obtain the `CulturalHeritageSystem.jar` file from your system administrator or download location

2. **Run the application**:
   ```bash
   java -jar CulturalHeritageSystem.jar
   ```

### Option 2: Compile from Source

1. **Download the source files**:
   - Save both `ModifyCulturalHeritage.java` and `Agency.java` in the same directory

2. **Compile the Java files**:
   ```bash
   javac ModifyCulturalHeritage.java
   ```
   Note: The Agency class is an inner class and will be compiled automatically

3. **Run the application**:
   ```bash
   java ModifyCulturalHeritage
   ```

### Option 3: Using an IDE (Eclipse/IntelliJ IDEA)

1. **Create a new Java project**
2. **Add both .java files to the project**
3. **Set the main class** as `ModifyCulturalHeritage`
4. **Run the project**

## 👨‍💻 User Roles and Authentication

### Agency Operator Types:
The system supports multiple agency roles with different authentication credentials:

| Username | Password | Agency Name | Role |
|----------|----------|-------------|------|
| `agency1` | `pass123` | Cultural Heritage Agency | Standard Operator |
| `admin` | `admin123` | System Administrator | Administrator |
| `museum` | `museum456` | National Museum | Cultural Institution |
| `archive` | `archive789` | Historical Archive | Archive Manager |

## 📱 Application Interface Guide

### 1. Login Screen
- **Purpose**: Authenticate agency operators
- **Features**:
  - Username and password fields
  - Login button
  - Demo credentials displayed on failed login attempts

### 2. Search Screen
- **Purpose**: Browse and search cultural heritage items
- **Components**:
  - **Search Bar**: Enter keywords to filter results
  - **Heritage List**: Displays all cultural items in format: "ID: Name - Location"
  - **Agency Info**: Shows currently logged-in agency
  - **Navigation Buttons**:
    - **Edit Selected**: Opens edit form for selected item
    - **Logout**: Ends current session

### 3. Edit Screen
- **Purpose**: Modify cultural heritage data
- **Form Fields**:
  - **Name**: Name of the cultural heritage item
  - **Type**: Category/classification (e.g., Artifact, Document, Monument)
  - **Location**: Physical location or site
  - **Year**: Historical period or creation date
- **Control Buttons**:
  - **Submit Changes**: Validates and submits changes
  - **Confirm Save**: Final confirmation after server processing
  - **Cancel**: Discard changes and return to search

## 🔄 Step-by-Step Usage Guide

### Step 1: Login to System
1. Launch the application
2. Enter valid agency credentials (see demo credentials above)
3. Click "Login"
4. Successful login shows agency information and proceeds to search screen

### Step 2: Search for Cultural Heritage
1. **View All Items**: Leave search field empty to see all available cultural heritage items
2. **Search Specific Items**: Enter keywords in search box and click "Search"
3. **Select Item**: Click on any item in the list to select it

### Step 3: Edit Cultural Heritage Data
1. Select an item from the list
2. Click "Edit Selected" button
3. The edit form loads with current data
4. Modify any field as needed:
   - Edit name, type, location, or year information
   - All fields are mandatory

### Step 4: Submit Changes
1. Click "Submit Changes" button
2. System validates form data:
   - Shows error message if any field is empty
   - Returns to form if validation fails
3. Confirmation dialog appears showing:
   - Agency name
   - Item ID
   - Request confirmation

### Step 5: Final Confirmation
1. After submitting, the form is temporarily disabled to prevent multiple submissions
2. System simulates server processing (2-second delay)
3. Click "Confirm Save" button when enabled
4. Success message appears with:
   - Modified by agency
   - Item ID
   - Updated name
5. System automatically returns to search screen

## ⚠️ Important Behaviors and Features

### 1. Form Blocking Prevention
- After submitting changes, all form fields are disabled
- "Submit Changes" button becomes inactive
- This prevents duplicate submissions during processing
- Form re-enables only after operation completes or fails

### 2. Server Connection Simulation
- System may randomly simulate server connection loss (10% chance)
- If connection fails:
  - Operation is cancelled automatically
  - Error message displayed
  - Form re-enabled for retry
- Manual "Cancel" option always available

### 3. Data Validation
- **Empty Field Prevention**: All form fields are mandatory
- **Data Integrity**: Changes tracked to warn about unsaved data
- **Session Validation**: Continuous authentication checks

### 4. Session Management
- Automatic logout on navigation errors
- Session timeout simulation
- Clear agency tracking throughout workflow

## 🛡️ Security Features

| Feature | Purpose | Implementation |
|---------|---------|----------------|
| **Authentication** | Verify agency identity | Username/password validation |
| **Authorization** | Control access levels | Agency role-based permissions |
| **Session Control** | Prevent unauthorized access | Continuous session validation |
| **Transaction Safety** | Prevent data corruption | Form locking during processing |
| **Data Validation** | Ensure data quality | Mandatory field validation |

## 🐛 Troubleshooting Guide

### Common Issues and Solutions:

1. **Application Won't Start**
   - **Issue**: "Java not found" error
   - **Solution**: Install Java Runtime Environment (JRE) 8 or higher
   
2. **Login Fails**
   - **Issue**: Invalid credentials error
   - **Solution**: Use demo credentials listed above or contact system administrator

3. **Form Not Submitting**
   - **Issue**: Submit button not working
   - **Solution**: Check that all form fields are filled

4. **Connection Errors**
   - **Issue**: "Connection to server ETOUR interrupted" message
   - **Solution**: This is a simulation feature. Click "Cancel" and retry operation

5. **List Shows "No Results Found"**
   - **Issue**: Search returns no matches
   - **Solution**: Check search terms or leave field empty to view all items

### Performance Tips:
- Close other applications if system seems slow
- Use specific search terms for faster results
- Ensure proper Java version is installed

## 📊 Demo Data

The system comes pre-loaded with sample cultural heritage data:

| ID | Name | Type | Location | Year |
|----|------|------|----------|------|
| 1 | Ancient Vase | Artifact | Athens | 500 BC |
| 2 | Medieval Manuscript | Document | Florence | 1300 AD |
| 3 | Historic Fort | Monument | Delhi | 1600 AD |

## 🔧 Advanced Features

### 1. Multi-Agency Support
- Different agency operators can use the same system
- Each agency maintains its own session
- Agency information tracked in all operations

### 2. Audit Trail
- All modifications record:
  - Which agency made changes
  - When changes were made
  - What specific data was modified

### 3. Error Handling
- Comprehensive error messages
- Graceful failure recovery
- User-friendly error explanations

### 4. Data Persistence Simulation
- While this demo uses in-memory data, real implementation would connect to database
- Data structure designed for easy database integration
- Transaction management built-in

## 📞 Support and Contact

For technical support or system administration:
- **System Administrator**: admin (password: admin123)
- **Technical Documentation**: See inline code comments
- **System Logs**: Check application console output

## 🔄 Version Information

- **Current Version**: 1.0.0
- **Release Date**: October 2023
- **Java Version**: Compatible with Java 8+
- **GUI Framework**: Java Swing
- **Architecture**: Desktop Application

## 📝 License and Usage

This software is designed for cultural heritage management purposes. Proper authorization and credentials are required for system access. Unauthorized access or modification of cultural heritage data is strictly prohibited.

---

**Note**: This is a demonstration system for the "ModifyCulturalHeritage" use case. In a production environment, additional security measures, database integration, and backup systems would be implemented. Always ensure proper data backup procedures when modifying critical cultural heritage information.