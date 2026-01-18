Here's the comprehensive user manual for the Statistical Report System:

# Statistical Report System - User Manual

## Overview

The Statistical Report System is a Java-based desktop application designed for agency operators to view comprehensive statistical reports for various locations. The system allows users to select a location from available options and generates detailed statistical reports based on site feedback data, including ratings, visitor counts, and comments.

## System Requirements

### Hardware Requirements
- Minimum RAM: 512 MB
- Minimum Storage: 100 MB free space
- Display: 1024x768 resolution or higher

### Software Requirements
- **Operating System**: Windows 7+, macOS 10.12+, Linux (with GUI)
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Java Development Kit (JDK)**: Version 8 or higher (for development/compilation)

## Installation Guide

### Step 1: Install Java
1. **Check if Java is installed**:
   ```bash
   java -version
   ```
   If Java is not installed or version is below 8, proceed to step 2.

2. **Download and install Java**:
   - Visit [Oracle Java Downloads](https://www.oracle.com/java/technologies/downloads/) or [Adoptium](https://adoptium.net/)
   - Download Java 8 or higher for your operating system
   - Follow the installation wizard instructions

### Step 2: Download the Application
1. Obtain the application files from your system administrator or download location
2. Ensure all required Java files are included in the same directory:
   - `MainApp.java`
   - `ReportDashboard.java`
   - `StatisticalReport.java`
   - `Location.java`
   - `SiteFeedback.java`
   - `ReportService.java`
   - `MockDataService.java`
   - `ReportData.java`
   - `SearchSite.java`

### Step 3: Compile the Application
1. Open a terminal or command prompt
2. Navigate to the directory containing the Java files
3. Compile all Java files:
   ```bash
   javac *.java
   ```

### Step 4: Run the Application
```bash
java MainApp
```

## Getting Started

### Launching the Application
1. Start the application using the command above
2. The main dashboard window will appear, centered on your screen

### Interface Overview

#### Main Dashboard
The main dashboard consists of:
1. **Title Bar**: Displays "Report Statistics Dashboard"
2. **Header**: "Statistical Report System" title
3. **Location Selection Area**: 
   - Dropdown list of available locations
   - "Select a Location" prompt as the default selection
4. **Action Button**: "View Statistical Report" button
5. **Status Bar**: Shows current status messages at the bottom

### First Time Setup
1. Upon first launch, the system automatically loads available locations
2. No additional configuration is required for basic operation
3. The system uses mock data for demonstration purposes

## Core Features

### 1. Location Selection
**Purpose**: Choose the location for which you want to generate a statistical report.

**How to Use**:
1. Click on the dropdown menu labeled "Select Location:"
2. Browse available locations:
   - Central Park
   - Metropolitan Museum
   - Times Square
   - Statue of Liberty
   - Brooklyn Bridge
   - Natural History Museum
   - Empire State Building
3. Click your desired location

**Tips**:
- Locations are sorted alphabetically
- The default "-- Select a Location --" option prevents accidental report generation
- If locations fail to load, default locations are provided

### 2. Generating Reports
**Purpose**: Create comprehensive statistical reports for selected locations.

**How to Use**:
1. Select a location from the dropdown
2. Click the "View Statistical Report" button
3. Wait for the system to process (status updates shown in real-time)
4. The report window will automatically open

**What Happens Behind the Scenes**:
- System retrieves site feedback data using the SearchSite use case
- Calculates statistics including averages, totals, and distributions
- Prepares data for display in multiple formats
- Handles network interruptions gracefully

### 3. Report Navigation
**Purpose**: View and analyze statistical data in multiple formats.

**Report Window Features**:
- **Title**: Shows "Statistical Report - [Location Name]"
- **Tabbed Interface**: Three different report views

**Tab 1: Summary**
- Shows key statistics in a clean, readable format
- Displays:
  - Total Feedback Entries
  - Average Rating (1-5 scale)
  - Minimum and Maximum Ratings
  - Total Visitors
  - Average Daily Visitors
  - Data Range (date coverage)
  - Rating Distribution

**Tab 2: Detailed Data**
- Shows raw feedback entries in a sortable table
- Columns:
  - Date (YYYY-MM-DD format)
  - Rating (1.0 to 5.0 with 0.5 increments)
  - Comments (user feedback text)
  - Visitor Count (daily visitors)
- Features:
  - Click column headers to sort data
  - Scrollable table for large datasets
  - Entry count display at the bottom

**Tab 3: Full Report**
- Complete text-based report
- Includes all statistics and feedback data
- Formatted for readability and printing
- Shows report generation timestamp

## Advanced Features

### Connection Handling
The system includes robust error handling for network issues:

1. **Connection Interruption Detection**: Automatically detects when server connections fail
2. **Graceful Degradation**: Provides helpful error messages instead of crashing
3. **Retry Mechanism**: Allow users to try again with a single click
4. **Status Updates**: Real-time feedback during data retrieval

### Data Validation
All data undergoes rigorous validation:
- Ratings must be between 1.0 and 5.0
- Visitor counts cannot be negative
- Dates must be valid and properly formatted
- All calculations handle edge cases appropriately

### Performance Optimization
- Asynchronous data loading to keep UI responsive
- Efficient data structures for handling large datasets
- Caching mechanisms for frequently accessed data
- Minimal resource usage design

## Troubleshooting

### Common Issues and Solutions

**Issue 1: Application won't start**
```
Error: Could not find or load main class MainApp
```
**Solution**:
```bash
# Ensure you're in the correct directory
cd /path/to/application/files
# Recompile all files
javac *.java
# Try running again
java MainApp
```

**Issue 2: Locations not loading**
**Symptoms**: Dropdown shows only "Default Location"
**Solution**:
1. Check your internet connection (for production environments)
2. Click the "View Statistical Report" button anyway
3. The system will generate a report with simulated data
4. Contact system administrator if problem persists

**Issue 3: Report generation fails**
**Error Message**: "Error generating report: Connection to server interrupted"
**Solution**:
1. Check your network connection
2. Click "View Statistical Report" again
3. If problem continues, use the report with cached data
4. Reports contain timestamps so you know when data was last updated

**Issue 4: GUI appears too small or large**
**Solution**:
1. Close the application
2. Edit ReportDashboard.java, find setSize(600, 400)
3. Adjust numbers to your preference
4. Recompile and restart

### Error Messages Glossary

- **"Please select a location first"**: No location selected from dropdown
- **"Connection interrupted while fetching locations"**: Network issue during initial load
- **"No feedback data available for location"**: Selected location has no data
- **"Report generation failed"**: General error during report creation
- **"Connection to server interrupted"**: Network lost during data retrieval

## Best Pract

### For Optimal Performance
1. **Regular Updates**: Ensure Java is updated to latest stable version
2. **System Resources**: Close unnecessary applications before running
3. **Network Stability**: Use stable internet connection for best results
4. **Data Freshness**: Generate reports during off-peak hours for production systems

### Data Interpretation Tips
1. **Rating Context**: Consider 4.0+ as excellent, 3.0-4.0 as good, below 3.0 as needs improvement
2. **Visitor Patterns**: Look for trends in visitor counts (weekends vs weekdays)
3. **Comment Analysis**: Pay attention to recurring themes in user comments
4. **Date Range**: Consider seasonal factors when analyzing data

## System Architecture

### Key Components
1. **MainApp**: Application entry point and launcher
2. **ReportDashboard**: Main user interface and control center
3. **StatisticalReport**: Report display and navigation system
4. **ReportService**: Business logic and data processing
5. **SearchSite**: Data retrieval implementation
6. **MockDataService**: Demonstration data provider
7. **Location/SiteFeedback**: Data model classes
8. **ReportData**: Data transfer object

### Data Flow
```
User Interaction → Dashboard → ReportService → SearchSite → DataService → Processing → Report Display
```

## Customization

### Modifying Location Data
To add or modify locations, edit the `MockDataService.java` file:

1. **Add a new location**:
```java
locations.add(new Location("LOC008", "New Location Name", "Address", "Type", capacity));
```

2. **Modify visitor counts**:
```java
visitorMap.put("Location Name", newBaseCount);
```

3. **Add feedback templates**:
```java
String[] commentTemplates = {
    // Add your custom comments here
};
```

### Changing GUI Appearance
1. **Colors and Fonts**: Modify ReportDashboard.java and StatisticalReport.java
2. **Window Size**: Adjust setSize() parameters in constructor methods
3. **Layout Changes**: Modify GridBagConstraints and other layout managers

## Support and Maintenance

### Regular Maintenance Tasks
1. **Data Updates**: Refresh mock data periodically for accuracy
2. **Java Updates**: Keep Java runtime updated for security and performance
3. **Backup Reports**: Save important reports for historical analysis
4. **System Logs**: Monitor application logs for errors or performance issues

### Getting Help
1. **Internal Support**: Contact your system administrator
2. **Technical Issues**: Reference this manual's troubleshooting section
3. **Feature Requests**: Document desired features for future updates
4. **Bug Reports**: Note the exact error message and steps to reproduce

## FAQ

**Q: Can I export reports to other formats?**
A: Currently, reports are viewable only within the application. You can copy text from the Full Report tab.

**Q: How often is data updated?**
A: In the demo version, data is generated randomly. In production, update frequency depends on your data source.

**Q: Can I use this system offline?**
A: Yes, the application works offline using cached and mock data.

**Q: Is my data secure?**
A: The demo version uses mock data. For production, implement appropriate security measures based on your data source.

**Q: How do I add more statistics?**
A: Modify the calculateStatistics() method in ReportService.java to include additional calculations.

**Q: Can I customize the report layout?**
A: Yes, you can modify the StatisticalReport.java file to change how data is displayed.

## Release Notes

### Version 1.0.0
- Initial release
- Complete statistical report generation
- Multiple location support
- Robust error handling
- Tabbed report interface
- Mock data system for demonstration

## License and Acknowledgments

This software is provided for demonstration and educational purposes. For production use, consider implementing proper database connections, security measures, and data validation.

---

*Note: This is a demonstration system. Actual implementation may vary based on specific requirements and data sources.*