```markdown
# Search Tourist System - User Manual

## Overview

The Search Tourist System is a Java-based application designed for travel agency operators to efficiently search and retrieve tourist account information from the ETOUR system. The application provides a user-friendly GUI interface for searching tourists by name, email, or tourist ID, with robust connection handling for ETOUR server interactions.

## Main Features

### 1. Search Functionality
- **Flexible Search Criteria**: Search tourists by name (partial match), email (exact match), or tourist ID (exact match)
- **Multiple Criteria Combination**: Combine multiple search parameters for precise filtering
- **Real-time Results Display**: View search results in a tabular format immediately after search execution

### 2. Connection Management
- **Automatic Connection Handling**: Establishes connection to ETOUR server on startup
- **Connection Resilience**: Handles connection interruptions with automatic reconnection attempts
- **Connection Status Monitoring**: Validates connection before executing queries

### 3. User Interface
- **Intuitive Form**: Clean, organized search form with labeled fields
- **Results Table**: Clear presentation of tourist information with proper column headers
- **Error Handling**: User-friendly error messages for connection issues and search errors
- **Responsive Design**: GUI adjusts to different screen sizes and window resizing

### 4. Data Management
- **Data Validation**: Validates search criteria before query execution
- **Results Management**: Clear results functionality to reset search outcomes
- **Mock Data Support**: Includes sample tourist data for demonstration and testing

## System Requirements

### Minimum Requirements
- **Operating System**: Windows 7+, macOS 10.10+, or Linux with GUI support
- **Java Runtime Environment**: JDK 8 or later
- **Memory**: Minimum 512 MB RAM (1 GB recommended)
- **Storage**: 50 MB available disk space

### Dependencies
- **Java Swing**: Built into Java Standard Edition (no external dependencies required)
- **Java AWT**: Included in Java SE
- **No External Libraries**: The application uses only standard Java libraries

## Installation Guide

### Prerequisites
1. **Verify Java Installation**:
   ```bash
   java -version
   ```
   Ensure you have Java 8 or later installed. If not, download and install from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK.

2. **Set up Java Environment**:
   - Ensure `JAVA_HOME` environment variable is set
   - Verify Java is in your system PATH

### Installation Steps

#### Option 1: Using Compiled JAR (Recommended)

1. **Download the Application**:
   - Download `SearchTouristApp.jar` and supporting files from the distribution package

2. **Run the Application**:
   ```bash
   java -jar SearchTouristApp.jar
   ```

#### Option 2: Compile from Source

1. **Create Project Structure**:
   ```
   SearchTouristSystem/
   ├── SearchTouristApp.java
   ├── SearchTouristFrame.java
   ├── TouristService.java
   ├── Tourist.java
   ├── SearchCriteria.java
   ├── ETOURConnectionManager.java
   └── ConnectionInterruptedException.java
   ```

2. **Compile All Java Files**:
   ```bash
   javac *.java
   ```

3. **Run the Application**:
   ```bash
   java SearchTouristApp
   ```

### Testing Installation
After installation, verify the application runs by executing:
```bash
java SearchTouristApp
```
You should see the main application window with the search form appear on your screen.

## How to Use

### Launching the Application

1. **Windows**:
   - Double-click `SearchTouristApp.jar` (if associated with Java)
   - Or run from command prompt: `java -jar SearchTouristApp.jar`

2. **macOS/Linux**:
   - Open terminal in the application directory
   - Execute: `java -jar SearchTouristApp.jar`

### Main Interface Components

The application window consists of two main sections:

1. **Search Criteria Section (Top)**:
   - Name field: Enter tourist name (partial matches accepted)
   - Email field: Enter exact email address
   - Tourist ID field: Enter exact tourist identification number
   - Search button: Execute search with entered criteria
   - Clear button: Reset all fields and clear results

2. **Results Section (Bottom)**:
   - Table displaying search results with columns:
     - Tourist ID
     - Name
     - Email
     - Phone
     - Nationality

### Performing Searches

#### Basic Search
1. **Access Search Functionality**:
   - Application opens directly to the search interface
   - Form is automatically ready for input

2. **Enter Search Criteria**:
   - Fill in one or more of the search fields:
     - **Name Field**: Enter any part of a tourist's name (e.g., "john" finds "John Smith")
     - **Email Field**: Enter complete email address (e.g., "john.smith@example.com")
     - **Tourist ID Field**: Enter exact tourist ID (e.g., "T001")

3. **Submit Search**:
   - Click the **Search** button to execute the search
   - Results appear in the table below the search form

#### Advanced Search Tips
- **Combined Search**: Use multiple fields for more specific searches
  - Example: Search by name "John" and email domain "example.com"
- **Partial Name Search**: 
  - "Mar" will find "Maria Garcia"
  - "Smith" will find "John Smith"
- **Clear Search**:
  - Click **Clear** to reset all fields and results
  - Or manually clear each field

### Understanding Results

#### Sample Tourist Data
The application includes the following demonstration tourist records:
```
Tourist ID: T001, Name: John Smith, Email: john.smith@example.com, Phone: +1-555-0101, Nationality: USA
Tourist ID: T002, Name: Maria Garcia, Email: maria.garcia@example.com, Phone: +34-555-0202, Nationality: Spain
Tourist ID: T003, Name: Chen Wei, Email: chen.wei@example.com, Phone: +86-555-0303, Nationality: China
Tourist ID: T004, Name: Anna Schmidt, Email: anna.schmidt@example.com, Phone: +49-555-0404, Nationality: Germany
Tourist ID: T005, Name: David Brown, Email: david.brown@example.com, Phone: +44-555-0505, Nationality: UK
```

#### Search Examples

1. **Search by name containing "an"**:
   - Returns: T003 (Chen Wei), T004 (Anna Schmidt), T005 (David Brown)

2. **Search by exact email**:
   - Input: "maria.garcia@example.com"
   - Returns: T002 (Maria Garcia)

3. **Search by tourist ID**:
   - Input: "T001"
   - Returns: T001 (John Smith)

4. **No match found**:
   - Displays message: "No tourists found matching the search criteria"

### Handling Connection Issues

#### Connection Status
The application automatically:
1. Connects to the ETOUR server on startup
2. Validates connection before each search
3. Attempts reconnection if connection is lost

#### Error Scenarios

1. **Server Unavailable**:
   ```
   Error: Connection to ETOUR server was interrupted: [error details]
   ```
   - Application cannot connect to the simulated server
   - Try restarting the application

2. **Connection During Search**:
   ```
   Error: Search failed due to connection interruption. Please try again.
   ```
   - Connection was lost during search execution
   - Application attempts automatic reconnection
   - Try the search again

3. **Reconnection Failure**:
   ```
   Error: Search failed and reconnection failed: [error details]
   ```
   - Both search and reconnection attempts failed
   - Close and restart the application

### Best Pract

1. **Start Broad**: Begin with a single search criterion and refine as needed
2. **Use Partial Names**: When uncertain about exact names, use partial matches
3. **Combine Criteria**: For precise results, use multiple search fields
4. **Clear Between Searches**: Use the Clear button between unrelated searches
5. **Check Connection**: If experiencing issues, verify network connectivity

## Troubleshooting

### Common Issues

1. **Application Won't Start**:
   - Verify Java is installed: `java -version`
   - Check Java version: Must be Java 8 or later
   - Ensure sufficient memory is available

2. **GUI Not Displaying**:
   - Verify your system has GUI capabilities
   - For headless servers, use a virtual display or remote desktop

3. **Connection Errors Persist**:
   - The application uses simulated connection failures for demonstration
   - In production, verify ETOUR server availability
   - Check firewall settings and network connectivity

4. **No Results Displayed**:
   - Verify search criteria matches sample data
   - Check for typos in search fields
   - Use partial matches for names

### Error Messages

**"Application initialization failed"**:
- Java runtime issue
- Missing Java Swing components
- Insufficient system resources

**"Not connected to ETOUR server"**:
- Initial connection failed
- Network issues
- Server unavailable

**"Connection to ETOUR server was interrupted"**:
- Network disruption during operation
- Server-side issues

## Technical Notes

### Connection Simulation
The application includes simulated connection behavior:
- 100ms connection establishment delay
- 50ms query execution delay
- 10% chance of simulated connection interruption during queries
- 200ms reconnection delay

### Mock Data
Five sample tourist records are included for demonstration:
- International representation (USA, Spain, China, Germany, UK)
- Realistic contact information formats
- Unique IDs for exact matching

### Performance Considerations
- Search operations are performed in memory for demonstration
- Real implementation would connect to database backend
- GUI updates are performed on the Event Dispatch Thread (EDT) for smooth performance

## Support and Contact

For technical support or issues:
1. Check this manual for troubleshooting steps
2. Verify system requirements are met
3. Ensure Java is properly installed and configured
4. Contact your system administrator for network-related issues

### Application Logs
- Check console output for detailed error information
- Connection status and search queries are logged to console
- Error messages include detailed context for debugging

## Future Enhancements

Planned features for future versions:
1. Database integration for real tourist data
2. Advanced search filters (date ranges, activity types)
3. Export functionality for search results
4. User authentication and session management
5. Multi-language support
6. Advanced filtering and sorting options
7. Batch operations for multiple tourists

## License and Disclaimer

This software is provided "as-is" for demonstration and educational purposes. In a production environment, additional security measures, data validation, and error handling should be implemented.

---

*Search Tourist System v1.0 - Last Updated: November 2024*
```