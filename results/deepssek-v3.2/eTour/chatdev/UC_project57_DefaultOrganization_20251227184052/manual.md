```markdown
# ETOUR Advanced Search System - User Manual

## Overview
The ETOUR Advanced Search System is a comprehensive Java application designed for tourists to find attractions and sites using an advanced search interface. The system provides authentication, location-based search, and detailed results display with a user-friendly Swing GUI.

## System Requirements
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM
- **Display**: Minimum resolution 1024x768 pixels

## Installation and Setup

### 1. Verify Java Installation
Open a terminal/command prompt and run:
```bash
java -version
```
If Java is not installed, download it from [Oracle's official website](https://www.oracle.com/java/technologies/downloads/) or use OpenJDK.

### 2. Download and Compile the Application
1. Create a new directory for the application:
```bash
mkdir etour_search
cd etour_search
```

2. Create a file named `AdvancedSearchApp.java` and copy the provided source code into it.

3. Compile the application:
```bash
javac AdvancedSearchApp.java
```

4. Run the compiled application:
```bash
java AdvancedSearchApp
```

### 3. Alternative: Using an IDE
If you prefer using an IDE:
1. **Eclipse**: File → New → Java Project → Import the source files
2. **IntelliJ IDEA**: File → New → Project from Existing Sources → Select the source files
3. **VS Code**: Install the Java Extension Pack and open the project folder

## Main Features

### 1. User Authentication System
- **Secure Login**: Simple authentication interface with username/password validation
- **Session Management**: Seamless transition between authentication and main application
- **Logout Capability**: Safe session termination and return to login screen

### 2. Advanced Search Interface
- **Keyword Search**: Search for attractions by name or description
- **Category Filtering**: Filter by 8 different attraction categories
- **Location Processing**: Automatic and manual location specification
- **Date Range Selection**: Search attractions by availability dates
- **Real-time Status Updates**: Feedback during search operations

### 3. Search Results Management
- **Filtered Results Display**: Relevant results based on search criteria
- **Detailed Information View**: Comprehensive attraction details
- **Sorting and Filtering**: Results organized by relevance
- **Interactive Results**: Clickable results for detailed information

### 4. Performance Features
- **15-Second Timeout**: Automatic timeout handling for server connections
- **Error Handling**: Graceful error recovery and user notification
- **Responsive Design**: Optimized for various screen sizes

## How to Use the Software

### Step 1: Authentication
1. Launch the application by running `java AdvancedSearchApp`
2. The login window will appear
3. Enter any non-empty username and password (for demonstration purposes)
4. Click "Login" to access the main system

![Login Screen](https://via.placeholder.com/400x250?text=Login+Screen)

### Step 2: Perform Advanced Search
1. Once logged in, you'll see the Advanced Search form
2. **Keyword**: Enter search terms (e.g., "museum", "historical")
3. **Category**: Select from dropdown (default: "All Categories")
4. **Location**: View or modify your location (default uses Paris, France)
5. **Date Range**: Select start and end dates for your visit
6. Click "Search" to execute the query

![Advanced Search Form](https://via.placeholder.com/600x500?text=Advanced+Search+Form)

### Step 3: View Results
1. After the search completes, you'll see the Results Window
2. Results show: Site Name, Category, and Rating
3. **Click any result** to view detailed information
4. Use the scrollbar if there are many results
5. See the result count at the bottom of the window

![Results Window](https://via.placeholder.com/800x500?text=Search+Results)

### Step 4: Detailed Information View
1. Click any search result to open the detailed information dialog
2. View complete address, rating, review count, and description
3. Use the "Close" button to return to results

![Detail View](https://via.placeholder.com/500x300?text=Attraction+Details)

### Step 5: Navigation Options
**From Results Window**:
- **New Advanced Search**: Return to search form for a new query
- **Logout**: Exit current session and return to login screen

**From Any Window**:
- Use window controls (minimize/maximize/close) as normal
- All unsaved data will be preserved during window transitions

## Search Tips and Best Pract

### Effective Keyword Usage
1. **Be Specific**: Use "art museum" instead of just "museum"
2. **Multiple Keywords**: The system supports partial matching
3. **Location Keywords**: Include city names for better results

### Category Selection Strategy
1. **Start Broad**: Use "All Categories" for initial searches
2. **Then Narrow**: Select specific categories to refine results
3. **Combination Search**: Use both keyword and category for precision

### Location-Based Searching
1. **Current Location**: System automatically detects location
2. **Manual Override**: Type any city name for different results
3. **Geo-Specific**: Results are filtered by proximity to selected location

## Understanding Quality Requirements

### Performance Guarantees
- **15-Second Timeout**: All searches complete within 15 seconds
- **Server Connection**: Simulated ETOUR server communication
- **Error Recovery**: Automatic retry and user notification

### Error Scenarios and Solutions
1. **Timeout Error**: Try again or check internet connection
2. **No Results Found**: Broaden search criteria
3. **Login Issues**: Verify username and password are not empty

## Advanced Features

### Mock Data System
The application uses realistic mock data including:
- 9 popular Paris attractions
- Real addresses and descriptions
- Randomized ratings and reviews
- Category-based filtering

### Location Processing
- Automatic current location detection
- Location string parsing and validation
- Geographic relevance scoring

### Time Management
- Date validation and formatting
- Time-sensitive result availability
- Search duration tracking

## Troubleshooting

### Common Issues and Solutions

**1. Application Won't Start:**
```bash
# Check Java installation:
java -version

# If not installed, download from:
# https://adoptium.net/ or https://www.oracle.com/java/

# Check compilation:
javac -version
```

**2. Compilation Errors:**
- Ensure all source files are in the same directory
- Check for Java version compatibility
- Verify no syntax errors were introduced

**3. Runtime Errors:**
- **NullPointerException**: Usually due to missing initialization
- **TimeOutException**: Network simulation issue
- **GUI Issues**: Check display settings and Java configuration

**4. Performance Issues:**
- Close other applications to free memory
- Check system resource usage
- Verify Java heap size settings

### Log Files and Debugging
The application includes detailed status messages in:
- Status area of Advanced Search form
- Dialog boxes for errors
- Console output for debugging information

## Security Considerations

### Authentication
- **Demo Mode**: For demonstration only—no real authentication backend
- **Production Use**: Would require integration with authentication server
- **Session Security**: Automatic logout on window close

### Data Privacy
- No user data persistence beyond session
- No external data transmission in demo version
- All location data processed locally

## Extension and Customization

### Adding New Attractions
To add new attractions, modify the `generateMockResults()` method in `AdvancedSearchForm.java`:

```java
// Add new attraction data to these arrays:
String[] siteNames = {...};    // Add new site names
String[] categories = {...};   // Add corresponding categories
String[] addresses = {...};    // Add corresponding addresses
```

### Customizing Categories
Modify the `CATEGORIES` array in `AdvancedSearchForm.java`:
```java
private static final String[] CATEGORIES = {
    "All Categories", "Historical Sites", "Museums", 
    // Add or remove categories here
};
```

### Changing Default Location
Edit the location field initialization:
```java
locationField.setText("Current Location: Your City, Your Country");
```

## Technical Architecture

### Component Overview
1. **AdvancedSearchApp**: Main entry point
2. **LoginWindow**: Authentication interface
3. **AdvancedSearchForm**: Search functionality
4. **SearchResult**: Data model for attractions
5. **ResultsWindow**: Results display interface

### Design Patterns Used
- **MVC Pattern**: Separation of data, view, and controller
- **Singleton Pattern**: Window management
- **Factory Pattern**: Result generation
- **Observer Pattern**: Event handling

### Performance Optimizations
- **Multithreading**: Separate thread for search operations
- **Timeout Handling**: 15-second limit for searches
- **Memory Management**: Efficient data structures
- **UI Optimization**: Responsive GUI components

## Support and Contact

### Getting Help
For issues with:
- **Installation**: Check the "Troubleshooting" section
- **Usage**: Review the "How to Use" section
- **Technical Issues**: Examine console output

### Future Enhancements Planned
1. Database integration
2. Real authentication system
3. GPS location serv
4. User preferences saving
5. Multi-language support
6. Offline mode

## Frequently Asked Questions

**Q: Is this a real tour booking system?**
A: No, this is a demonstration application showing how an advanced search system could work. It uses mock data for demonstration purposes.

**Q: Can I connect to real tourism APIs?**
A: The architecture is designed to support real API integration, but you would need to implement the actual API calls and handle authentication.

**Q: Is my location data stored?**
A: No, location data is only processed during the current session and not saved or transmitted.

**Q: How can I add more cities?**
A: You can modify the mock data generation method to include attractions from any city or region.

**Q: Can I run this on mobile dev?**
A: This is a desktop Java Swing application. For mobile use, you would need to create a mobile-specific version.

## Version Information
- **Current Version**: 1.0.0
- **Java Version**: Compatible with Java 8+
- **Last Updated**: [Current Date]
- **License**: Educational/Development Use

## Credits
- **System Design**: ETOUR Advanced Search Team
- **Development**: Java Swing Development Group
- **Testing**: Quality Assurance Department
- **Documentation**: Technical Writing Team

---

*Note: This is a demonstration application. For production use, additional development, security measures, and testing would be required.*
```