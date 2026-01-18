```markdown
# SearchRefreshmentPoint - User Manual

## Overview

SearchRefreshmentPoint is a Java-based desktop application that allows users to search for refreshment points (rest areas, cafes, diners, etc.) in a system. The application provides a user-friendly graphical interface to search based on criteria such as name, minimum rating, and maximum distance, and displays the matching results in a clear format.

## Main Features

1. **Intuitive Search Interface**: A simple form-based GUI for entering search criteria
2. **Multiple Search Parameters**:
   - Name (partial matching, case-insensitive)
   - Minimum rating (0.0 to 5.0)
   - Maximum distance (in kilometers)
3. **Responsive Results Display**: Clear presentation of search results with formatting
4. **Error Handling**: 
   - Input validation
   - Server connection interruption handling
   - Graceful error messages
5. **Performance**: All operations complete within 15 seconds as per quality requirements
6. **Non-blocking UI**: Uses SwingWorker to keep the interface responsive during searches

## System Requirements

### Hardware Requirements
- Processor: 1 GHz or faster (modern processors recommended)
- RAM: 512 MB minimum (1 GB recommended)
- Disk Space: 10 MB for application files

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.10+, or Linux with Java support
- **Java Runtime Environment (JRE)**: Java 8 or higher
- **Development Environment** (if modifying code): Java Development Kit (JDK) 8+

## Installation

### For End Users (Running the Application)

1. **Install Java Runtime Environment (JRE)**:
   - If you don't have Java installed, download and install it from [Oracle's website](https://www.oracle.com/java/technologies/javase-jre8-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/)
   - To verify installation, open a terminal/command prompt and type:
     ```bash
     java -version
     ```

2. **Download the Application**:
   - Download the `SearchRefreshmentPointApp.java` file from your provider

3. **Compile the Application**:
   - Open a terminal/command prompt
   - Navigate to the directory containing `SearchRefreshmentPointApp.java`
   - Compile the program:
     ```bash
     javac SearchRefreshmentPointApp.java
     ```

4. **Run the Application**:
   ```bash
   java SearchRefreshmentPointApp
   ```

### For Developers (Building from Source)

1. **Install JDK 8 or higher** from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/)

2. **Clone or download the source files**:
   - `SearchRefreshmentPointApp.java` (main application)
   - Alternatively, use the all-in-one file which contains all necessary classes

3. **Compile**:
   ```bash
   javac SearchRefreshmentPointApp.java
   ```

4. **Run**:
   ```bash
   java SearchRefreshmentPointApp
   ```

## How to Use

### Starting the Application

1. Launch the application using the command: `java SearchRefreshmentPointApp`
2. The main window will appear with the title "Search Refreshment Point"

### The User Interface

The application window is divided into three main sections:

1. **Search Form (Top Section)**:
   - **Name Field**: Enter part or all of a refreshment point's name (optional)
   - **Minimum Rating Spinner**: Set the lowest acceptable rating (0.0 to 5.0 in 0.5 increments)
   - **Maximum Distance Spinner**: Set the maximum distance in kilometers (0.0 to 1000.0 in 1.0 km increments)
   - **Search Button**: Click to initiate the search

2. **Results Area (Middle Section)**:
   - Displays search results in a scrollable text area
   - Shows "No refreshment points found" if no matches
   - Lists matched points with name, rating, and distance

3. **Status Bar (Bottom)**:
   - Shows current status: "Ready to search," "Searching... Please wait," or "Search completed successfully"
   - Displays error messages if search fails

### Performing a Search

#### Basic Search
1. Leave all fields blank or set to 0 for the widest search
2. Click "Search Refreshment Points"
3. View all available refreshment points in the results area

#### Filtered Search
1. **By Name**:
   - Enter text in the Name field (e.g., "Cafe" finds "Cafe Central")
   - Matching is partial and case-insensitive

2. **By Rating**:
   - Set the Minimum Rating to your desired quality level
   - Example: Set to 4.0 to find only highly-rated locations

3. **By Distance**:
   - Set the Maximum Distance to your travel radius
   - Example: Set to 10.0 to find locations within 10 km

4. **Combined Search**:
   - Use multiple criteria for precise results
   - Example: Name="Cafe", Minimum Rating=4.0, Maximum Distance=5.0

### Understanding Search Results

Results are displayed in this format:
```
Found 3 refreshment point(s):

Name: Cafe Central | Rating: 4.5 | Distance: 2.3 km
Name: City Plaza Cafe | Rating: 4.0 | Distance: 0.5 km
```

Each result includes:
- **Name**: The refreshment point's name
- **Rating**: Quality rating from 0.0 to 5.0
- **Distance**: How far the point is from your location (in kilometers)

### Sample Database

The application includes a sample database of refreshment points for demonstration:

| Name | Rating | Distance (km) |
|------|--------|---------------|
| Cafe Central | 4.5 | 2.3 |
| Rest Stop Alpha | 3.8 | 5.7 |
| Mountain View Diner | 4.9 | 12.4 |
| Quick Bite | 2.5 | 1.1 |
| Lakeside Lounge | 4.2 | 8.9 |
| Highway Oasis | 3.5 | 15.0 |
| Green Valley Rest Area | 4.7 | 25.3 |
| City Plaza Cafe | 4.0 | 0.5 |

## Troubleshooting

### Common Issues

1. **"java: command not found"**
   - Java is not installed or not in your PATH
   - Solution: Install Java JRE and ensure it's in your system PATH

2. **No results found**
   - Search criteria may be too restrictive
   - Solution: Broaden your search by reducing minimum rating or increasing maximum distance

3. **Application doesn't start**
   - Java version may be too old
   - Solution: Update to Java 8 or higher

4. **Connection errors**
   - The application simulates server connection issues with 10% probability
   - Solution: Try the search again

5. **Slow performance**
   - The application includes simulated delay (up to 3 seconds) for realism
   - This is well within the 15-second quality requirement
   - Wait for the search to complete

### Error Messages

- **"Connection to server ETOUR interrupted"**: The simulated server connection failed (intentional test case)
- **"Rating must be between 0 and 5"**: Invalid rating value entered
- **"Distance must be non-negative"**: Negative distance value entered

## Technical Details

### Architecture
- **Frontend**: Java Swing for the graphical interface
- **Business Logic**: Java classes handling search logic and data processing
- **Data Layer**: In-memory mock database for demonstration purposes

### Classes
- `SearchRefreshmentPointApp`: Main application class with GUI
- `RefreshmentPoint`: Data model representing a refreshment point
- `SearchService`: Service class handling search logic
- `ServerConnectionException`: Custom exception for connection issues

### Key Technologies
- **SwingWorker**: Ensures responsive UI during searches
- **GridBagLayout**: Flexible layout manager for the form
- **Exception Handling**: Comprehensive error handling for robustness

## Extending the Application

### Adding More Refreshment Points
To add more sample data, modify the `initializeMockDatabase()` method in the `SearchService` class:

```java
mockDatabase.add(new RefreshmentPoint("New Point Name", 4.0, 10.0));
```

### Modifying Search Logic
The search logic is in the `searchRefreshmentPoints()` method. You can:
- Add new search criteria
- Modify matching algorithms
- Change the simulated delay or error probability

### Connecting to Real Data Sources
For production use, replace the mock database with:
- Database connections (JDBC)
- REST API calls
- File-based data storage

## Performance Notes

- **Response Time**: All searches complete within 15 seconds (simulated delay max 3 seconds)
- **Memory Usage**: Lightweight application with minimal memory footprint
- **UI Responsiveness**: Non-blocking design ensures interface remains usable during searches

## Support

For issues with the application:
1. Check the troubleshooting section above
2. Verify Java installation and version
3. Ensure proper file permissions
4. Contact technical support if problems persist

## Version History

- **Version 1.0**: Initial release with basic search functionality, GUI interface, and sample data

## License

This application is provided as-is for demonstration purposes. Modify and distribute as needed for educational or personal use.

---

*Note: This is a demonstration application. In a production environment, it would connect to a real server (ETOUR) and database system.*
```