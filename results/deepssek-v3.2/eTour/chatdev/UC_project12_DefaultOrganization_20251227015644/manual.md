```markdown
# Tourist Card Viewer - ETOUR System

## Overview

The Tourist Card Viewer is a Java Swing-based desktop application designed for travel agency operators to view detailed information about tourists. The application implements the **ViewTouristCard** use case, allowing operators to select tourists from a search list and display their complete information in a card format. The system includes robust error handling for server connection interruptions and follows proper software architecture principles.

## Main Features

### 1. **Search Tourist Panel**
- Displays a list of tourists retrieved from the ETOUR server
- Automatically loads tourist data upon successful agency login
- Provides refresh functionality to update the tourist list
- Supports single selection of tourists for detailed viewing

### 2. **Tourist Card Display**
- Shows comprehensive tourist information in a well-formatted card layout
- Displays the following details for each tourist:
  - Unique ID
  - Full Name
  - Nationality
  - Email Address
  - Phone Number
  - Passport Number
  - Date of Birth
- Clear visual separation with a titled border

### 3. **Server Connection Management**
- Implements automatic retry mechanism for server connection failures
- Handles connection interruptions with user-friendly error messages
- Simulates real-world server communication scenarios for testing
- Maximum 3 retry attempts with user confirmation for each retry

### 4. **Error Handling**
- Graceful handling of server connection interruptions
- Informative error messages with recovery options
- Clear user feedback for successful operations
- Failsafe mechanisms for data retrieval failures

## System Requirements

### Hardware Requirements
- **Processor**: 1 GHz or faster
- **Memory**: 512 MB RAM minimum (1 GB recommended)
- **Storage**: 10 MB available disk space
- **Display**: 1024×768 screen resolution or higher

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.10+, or Linux
- **Java Runtime Environment**: Java 8 or higher
- **Network**: Internet connection for server communication (simulated)

## Installation & Setup

### Step 1: Install Java
1. Verify if Java is already installed:
   ```bash
   java -version
   ```
2. If Java is not installed, download and install the latest Java Development Kit (JDK) from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK

### Step 2: Download the Application
1. Create a new directory for the application:
   ```bash
   mkdir TouristCardViewer
   cd TouristCardViewer
   ```
2. Download all required Java source files into this directory:
   - Tourist.java
   - ServerConnectionSimulator.java
   - ConnectionInterruptedException.java
   - ServerUtils.java
   - SearchTouristPanel.java
   - TouristCardPanel.java
   - ViewTouristCardGUI.java

### Step 3: Compile the Application
Compile all Java files using the following command:
```bash
javac *.java
```

### Step 4: Run the Application
Start the application using:
```bash
java ViewTouristCardGUI
```

## How to Use the Application

### Step 1: Launch the Application
1. Open a terminal or command prompt
2. Navigate to the application directory
3. Run the application using the command from Step 4 above

### Step 2: Agency Login Simulation
Upon launching, the application will simulate an agency operator login:
- A pop-up message will confirm successful login
- The entry condition "The agency has logged" is automatically satisfied
- Tourist data will be automatically loaded from the server

### Step 3: Search and Select Tourists
1. **Tourist List**: The left panel displays all available tourists
2. **Refresh List**: Click the "Refresh List" button to reload data from the server
3. **Select Tourist**: Click on any tourist in the list to select them
4. **View Details**: The "View Tourist Card" button becomes enabled when a tourist is selected

### Step 4: View Tourist Card
1. **Display Card**: Click "View Tourist Card" to show detailed information
2. **Card Layout**: The right panel displays the tourist's complete details in an organized card format
3. **Server Communication**: The system uploads data for the selected account (step 2 of use case flow)
4. **Connection Handling**: If server connection is interrupted, the system will prompt for retry options

### Step 5: Handle Connection Issues
- **Retry Prompt**: If connection fails, you'll be asked if you want to retry (up to 3 times)
- **Error Messages**: Clear error messages explain connection issues
- **Cancel Option**: You can cancel retry attempts at any time
- **Recovery**: After successful recovery, the tourist card will display normally

## Application Flow

### Normal Operation Flow
1. Agency operator launches application
2. System simulates login and validates entry conditions
3. Application fetches tourist list from ETOUR server
4. Operator selects a tourist from the list
5. System uploads data for the selected tourist account
6. System displays the tourist card with complete details
7. Operator can select another tourist or refresh the list

### Error Recovery Flow
1. Server connection interruption detected
2. System displays error message with retry option
3. User chooses to retry connection
4. System attempts reconnection (maximum 3 attempts)
5. If successful, continues normal operation
6. If all attempts fail, displays final error message

## Key Components

### 1. **Tourist Class**
- Models tourist data with proper encapsulation
- Includes all required personal information fields
- Provides getters and setters for data manipulation
- Implements toString() for debugging and display

### 2. **ServerConnectionSimulator**
- Simulates ETOUR server communication
- Includes connection interruption scenarios (30% chance)
- Provides methods for fetching tourist data and specific tourist details
- Used for testing error handling mechanisms

### 3. **ServerUtils**
- Centralized server operation management
- Implements retry logic with maximum 3 attempts
- Provides user-friendly error messages via dialog boxes
- Handles both success and failure scenarios

### 4. **SearchTouristPanel**
- Implements the SearchTourist use case functionality
- Displays selectable list of tourists
- Manages user interactions for selection and refresh
- Integrates with the main GUI for card display

### 5. **TouristCardPanel**
- Displays tourist information in a visually appealing card format
- Uses GridBagLayout for precise component placement
- Includes methods for displaying and clearing tourist data
- Provides clean, professional appearance

### 6. **ViewTouristCardGUI**
- Main application window and controller
- Manages overall application flow
- Implements use case steps including entry conditions
- Handles error scenarios and user interactions

## Troubleshooting

### Common Issues and Solutions

#### Issue 1: Application Won't Start
- **Solution**: Verify Java installation with `java -version`
- **Solution**: Ensure all .java files are in the same directory
- **Solution**: Check that compilation was successful (no errors)

#### Issue 2: No Tourist Data Displayed
- **Solution**: Click "Refresh List" button
- **Solution**: Check network connection (if using real server)
- **Solution**: Wait for server response (simulator has delay)

#### Issue 3: Connection Errors
- **Solution**: This is normal behavior - the simulator intentionally creates connection issues
- **Solution**: Choose "Yes" when prompted to retry connection
- **Solution**: After 3 failed attempts, refresh the list

#### Issue 4: Java Version Issues
- **Solution**: Ensure Java 8 or higher is installed
- **Solution**: Use the same version for compilation and execution

## Testing Scenarios

### Normal Usage Test
1. Launch application
2. Wait for automatic login and data load
3. Select a tourist from the list
4. Click "View Tourist Card"
5. Verify complete information displays correctly

### Error Handling Test
1. Launch application (simulator may randomly cause connection issues)
2. If no errors occur, close and restart (error chance increases with restarts)
3. Observe error handling when connection is interrupted
4. Test retry functionality by choosing "Yes" to retry
5. Test cancel functionality by choosing "No" to retry

### Edge Case Test
1. Try selecting a tourist without clicking "View Tourist Card"
2. Click "View Tourist Card" without selecting a tourist (button should be disabled)
3. Use refresh while viewing a tourist card
4. Test application with very slow network simulation

## Quality Requirements

The application meets the following quality requirements:

### 1. **Correctness**
- Implements all steps of the ViewTouristCard use case
- Handles all specified entry and exit conditions
- Properly manages server connection interruptions

### 2. **Efficiency**
- Uses efficient data structures and algorithms
- Minimizes unnecessary server calls
- Includes proper resource management

### 3. **Usability**
- Intuitive user interface with clear navigation
- Informative error messages and recovery options
- Responsive layout that works on different screen sizes

### 4. **Maintainability**
- Clean, well-documented code structure
- Proper separation of concerns between components
- Easy to extend with new features

## Development Notes

### Architecture
- **Model-View-Presenter** pattern with clear separation
- **Swing-based GUI** for cross-platform compatibility
- **Event-driven programming** for responsive user interface
- **Exception handling** for robust error management

### Extensibility Points
1. Replace `ServerConnectionSimulator` with actual server connection
2. Add database persistence for tourist data
3. Extend `Tourist` class with additional fields
4. Add search and filtering capabilities to `SearchTouristPanel`
5. Implement user authentication system
6. Add report generation and printing functionality

### Best Pract Implemented
- Proper Java package structure
- Meaningful class and method names
- Comprehensive Javadoc comments
- Appropriate use of access modifiers
- Consistent coding style and formatting

## Support

For technical support or questions about the application:

1. **Check this manual** for troubleshooting steps
2. **Review the source code comments** for implementation details
3. **Test with different scenarios** to understand application behavior
4. **Contact the development team** for unresolved issues

## Version History

### Version 1.0 (Current)
- Initial release with complete ViewTouristCard use case implementation
- All core functionality working
- Basic error handling and retry mechanisms
- Simulated server connection for testing

### Planned Features (Future Releases)
- Real server integration with REST API
- Advanced search and filtering options
- Data export functionality (PDF, CSV)
- Multi-language support
- User role-based access control

---

**Note**: This application uses simulated server connections for demonstration purposes. In production, replace the `ServerConnectionSimulator` with actual server communication code.

**Copyright**: © 2024 ETOUR System. All rights reserved.
```