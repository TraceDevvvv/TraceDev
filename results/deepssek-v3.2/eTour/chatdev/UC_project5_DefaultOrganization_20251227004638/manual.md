```markdown
# Cultural Heritage Management System - User Manual

## Overview

The Cultural Heritage Management System is a Java-based application designed for agency operators to view detailed information about cultural heritage items. The system implements the **ViewCulturalHeritageCard** use case, allowing users to search for cultural heritage items and view comprehensive details in a structured card format.

### Key Features
- **Login Simulation**: Simulates agency operator authentication with entry condition validation
- **Search Functionality**: Implements the SearchCulturalHeritage use case to browse available cultural items
- **Detailed Card View**: Displays complete information about selected cultural heritage items
- **Server Connection Management**: Handles ETOUR server connections with interruption handling
- **User-Friendly Interface**: Graphical interface with intuitive navigation and selection mechanisms

## System Requirements

### Hardware Requirements
- Processor: 1.8 GHz or faster processor
- RAM: 2 GB minimum, 4 GB recommended
- Disk Space: 100 MB available space

### Software Requirements
- **Operating System**: Windows 7/8/10/11, macOS 10.14+, or Linux with Java support
- **Java Runtime**: Java SE 8 or later
- **Java Development Kit**: JDK 8 or later (for compilation)

## Installation Guide

### Step 1: Install Java Runtime Environment

1. **Download Java**
   - Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/) for Oracle JDK
   - Or download [OpenJDK](https://openjdk.java.net/install/) for open-source implementation

2. **Install Java**
   - **Windows**: Run the downloaded executable and follow installation wizard
   - **macOS**: Open the downloaded DMG file and follow installation instructions
   - **Linux**: Use package manager (e.g., `sudo apt install openjdk-11-jdk`)

3. **Verify Installation**
   ```bash
   java -version
   javac -version
   ```
   Both commands should display Java version information.

### Step 2: Download Application Files

Download all necessary Java files:
1. `CulturalHeritageApplication.java`
2. `HeritageCardDialog.java`
3. `CulturalHeritage.java`
4. `SearchCulturalHeritagePanel.java`
5. `ETOURServerConnection.java`
6. `ServerConnectionException.java`

### Step 3: Compile the Application

Open command prompt/terminal in the directory containing the Java files:

```bash
javac *.java
```

This will compile all Java files and generate corresponding `.class` files.

## Running the Application

### Method 1: Command Line Execution

```bash
java CulturalHeritageApplication
```

### Method 2: Using an IDE

1. Open your preferred Java IDE (Eclipse, IntelliJ IDEA, NetBeans)
2. Create a new Java project
3. Import all Java files into the project
4. Run the `CulturalHeritageApplication` class

## User Guide

### Login Procedure

The system automatically simulates agency operator login:
- Upon starting the application, you'll see "Agency Operator logged in successfully" in the console
- No username/password entry is required for this demonstration version

### Searching Cultural Heritage Items

1. **View Cultural Heritage List**
   - After starting the application, a dialog appears showing available cultural heritage items
   - Items are displayed in format: "Item Name (Item Type)"

2. **Select an Item**
   - Use the dropdown list to view all available cultural heritage items
   - Select the item you want to view in detail
   - Click "OK" to proceed or "Cancel" to exit

### Viewing Cultural Heritage Card

Once you select an item, the Cultural Heritage Card dialog appears with detailed information:

#### Card Sections

1. **Title Section**
   - Displays the name of the cultural heritage item in large, bold text

2. **Details Section**
   - **ID**: Unique identifier
   - **Type**: Cultural heritage category
   - **Location**: Geographical location
   - **Period**: Historical period
   - **Status**: Current protection/management status

3. **Images Section**
   - Lists available image files for the item
   - Displays "No images available" if no images exist

4. **Additional Information Section**
   - **Description**: Detailed description of the cultural heritage
   - **Historical Significance**: Importance in historical context
   - **Conservation Status**: Current preservation state and measures

### Navigation Controls

#### In Selection Dialog
- **Dropdown**: Select different cultural heritage items
- **OK**: Confirm selection and view details
- **Cancel**: Exit the application

#### In Heritage Card Dialog
- **Scrollbar**: Navigate through content if it exceeds window size
- **Close**: Return to main selection dialog or exit

### Sample Data

The application includes two sample cultural heritage items:

1. **Great Wall of China**
   - Type: Historical Monument
   - Location: Northern China
   - Period: 7th century BC
   - Status: Protected
   - Images: GreatWall_1.jpg, GreatWall_2.jpg

2. **Pyramids of Giza**
   - Type: Ancient Monument
   - Location: Giza, Egypt
   - Period: 2580–2560 BC
   - Status: UNESCO World Heritage
   - Images: Pyramids_1.jpg

## Advanced Features

### Server Connection Management

The system includes simulated ETOUR server connection with error handling:

#### Normal Operation
- Server connection is maintained during data retrieval
- Data loading includes simulated delay for realism

#### Connection Interruption
- Random chance (15%) of server connection loss
- System displays error message when connection is interrupted
- User is prompted to try again later

### Error Handling

1. **No Items Found**
   - If search returns empty results, informational message is displayed

2. **Server Connection Issues**
   - Connection errors are caught and displayed in console
   - User-friendly error dialog appears for server interruptions

3. **User Cancellation**
   - If user cancels selection, application gracefully returns null result

## Troubleshooting

### Common Issues

#### Java Not Found
**Symptom**: "java is not recognized as an internal or external command"
**Solution**: 
1. Add Java to PATH environment variable
2. On Windows: Add `C:\Program Files\Java\jdk-<version>\bin` to PATH
3. Restart command prompt after changes

#### Compilation Errors
**Symptom**: Errors when running `javac *.java`
**Solution**:
1. Ensure all 6 Java files are in the same directory
2. Check Java version compatibility
3. Verify no syntax errors in code files

#### ClassNotFoundException
**Symptom**: Error when running application
**Solution**:
1. Recompile all Java files
2. Ensure running from correct directory containing .class files
3. Check class name spelling: `java CulturalHeritageApplication`

### Performance Tips

1. **Memory Management**
   - Application is lightweight (<100MB RAM usage)
   - No performance tuning required for standard systems

2. **Network Considerations**
   - Server connections are simulated locally
   - No actual network connection required
   - No firewall configuration needed

## Application Architecture

### Components

1. **Main Application** (`CulturalHeritageApplication.java`)
   - Entry point and application coordinator
   - Handles main workflow and exception management

2. **Data Model** (`CulturalHeritage.java`)
   - Defines structure of cultural heritage items
   - Contains getters for all properties

3. **Search Panel** (`SearchCulturalHeritagePanel.java`)
   - Implements item search and selection interface
   - Displays available items in dialog format

4. **Server Connection** (`ETOURServerConnection.java`)
   - Simulates server communication
   - Handles connection interruptions
   - Provides mock data for demonstration

5. **Card Dialog** (`HeritageCardDialog.java`)
   - Displays detailed cultural heritage information
   - Organizes data into logical sections
   - Provides navigation controls

6. **Exception Class** (`ServerConnectionException.java`)
   - Custom exception for server connection errors
   - Extends standard Java Exception class

### Design Patterns

1. **Model-View-Controller (MVC)**
   - Model: `CulturalHeritage`, `ETOURServerConnection`
   - View: `HeritageCardDialog`, `SearchCulturalHeritagePanel`
   - Controller: `CulturalHeritageApplication`

2. **Exception Handling**
   - Centralized error handling in main application
   - Custom exception for domain-specific errors
   - User-friendly error messages

3. **Data Encapsulation**
   - Private fields with public getters
   - Immutable data model where appropriate
   - Separation of concerns between components

## Extending the Application

### Adding New Cultural Heritage Items

Modify the `createMockData()` method in `ETOURServerConnection.java`:

```java
private List<CulturalHeritage> createMockData() {
    List<CulturalHeritage> mockData = new ArrayList<>();
    // Add new item
    List<String> images = new ArrayList<>();
    images.add("new_image.jpg");
    mockData.add(new CulturalHeritage(
        "CH003",                    // ID
        "New Heritage Item",        // Name
        "New Type",                 // Type
        "Location",                 // Location
        "Period",                   // Period
        "Status",                   // Status
        images,                     // Images
        "Description here",         // Description
        "Historical significance",  // Historical Significance
        "Conservation status"       // Conservation Status
    ));
    return mockData;
}
```

### Customizing User Interface

1. **Change Dialog Size**
   - Modify `setSize(600, 500)` in `HeritageCardDialog` constructor
   - Adjust dimensions as needed for your display

2. **Modify Fonts and Colors**
   - Edit font settings in `layoutComponents()` method
   - Change colors using `setBackground()` and `setForeground()` methods

3. **Add New Sections**
   - Extend `createDetailsSection()` or add new section methods
   - Update CulturalHeritage model to include new properties

### Customizing Server Behavior

1. **Change Connection Failure Rate**
   - Modify `if (Math.random() < 0.15)` in `checkConnection()` method
   - Adjust the probability as needed

2. **Simulate Different Delay**
   - Modify `Thread.sleep(500)` in `searchCulturalHeritage()` method
   - Adjust milliseconds to simulate different network speeds

## Best Pract

### For Users
1. **Regular Usage**
   - Always close card dialogs after viewing
   - Use the scrollbar for lengthy content
   - Report any connection issues to system administrator

2. **Data Interpretation**
   - Period information is formatted as historical ranges
   - Status indicates current management condition
   - Images list shows available documentation files

### For Administrators
1. **System Maintenance**
   - Regularly update Java runtime for security patches
   - Monitor application performance logs
   - Keep backup of customized configuration

2. **Data Management**
   - Consider integrating with actual database systems
   - Implement proper data validation for new entries
   - Establish regular data backup procedures

## Support and Contact

### Getting Help
1. **Technical Support**
   - Check troubleshooting section first
   - Verify Java installation and version
   - Ensure all required files are present

2. **Bug Reporting**
   - Note error messages exactly
   - Document steps to reproduce issue
   - Report system configuration details

### System Limitations

1. **Current Version**
   - Mock data only (no database integration)
   - Simulated server connection
   - Basic error handling

2. **Planned Enhancements**
   - Database integration for real data
   - Image preview functionality
   - Advanced search filters
   - Multi-language support
   - Export capabilities

### Feedback

We welcome feedback to improve the Cultural Heritage Management System. Please report any issues or suggestions to the development team.

## License and Usage

This system is provided for educational and demonstration purposes. Users are free to study, modify, and distribute the code as needed, subject to standard open-source licensing considerations.

---

**Last Updated**: November 2023  
**Version**: 1.0.0  
**Developer**: Cultural Heritage Management Team  
**Contact**: heritage-support@example.com

> Note: This manual is provided with the Cultural Heritage Management System. For the most up-to-date information, always refer to the latest version of documentation accompanying the software distribution.
```