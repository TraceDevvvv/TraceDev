# Cultural Heritage Search System User Manual

## Overview

The Cultural Heritage Search System is a Java-based application that allows users to search for cultural heritage objects based on various criteria. The system includes a graphical user interface (GUI), a mock server for network communication, and efficient search algorithms to quickly find matching cultural objects.

## Main Features

### 1. Comprehensive Search Functionality
- **Name Search**: Partial text matching (case-insensitive)
- **Type Filter**: Options include Monument, Artifact, Building, Site, Tradition, or Any
- **Location Search**: Partial text matching (case-insensitive)
- **Year Filter**: Exact year matching
- **Combined Search**: All criteria can be combined for precise filtering

### 2. Response Time Monitoring
- Built-in performance tracking to ensure results within set time constraints
- Warning system for searches exceeding performance thresholds
- Real-time search duration display

### 3. Fault Tolerance
- Automatic fallback to local search if server connection fails
- Graceful error handling for invalid inputs
- Connection timeout management

### 4. User-Friendly Interface
- Clean, organized search form with clear labels
- Results displayed in a sortable table
- Search status feedback
- Clear search function to reset all fields

## System Requirements

### Software Requirements
- **Java Development Kit (JDK)**: Version 8 or higher
- **Java Runtime Environment (JRE)**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux

### Hardware Requirements
- **Processor**: 1 GHz or faster
- **RAM**: 2 GB minimum (4 GB recommended)
- **Disk Space**: 100 MB free space
- **Network**: Required for server-client communication (optional for local-only mode)

## Installation

### Step 1: Install Java
1. Download the latest Java SE Development Kit from Oracle's official website
2. Run the installer and follow the installation wizard
3. Set up JAVA_HOME environment variable:
   - Windows: `set JAVA_HOME=C:\Program Files\Java\jdk-version`
   - macOS/Linux: `export JAVA_HOME=/usr/lib/jvm/java-version`

### Step 2: Verify Java Installation
Open a command prompt or terminal and run:
```bash
java -version
javac -version
```

### Step 3: Prepare Application Files
Create a project directory and save all Java files:
```
CulturalHeritageSearch/
├── SearchService.java
├── CulturalObject.java
├── SearchCulturalHeritage.java
├── CulturalHeritageServer.java
└── CulturalHeritageGUI.java
```

### Step 4: Compile the Application
Navigate to your project directory and compile all Java files:
```bash
cd /path/to/CulturalHeritageSearch
javac *.java
```

## How to Use

### Starting the Application

#### Option 1: Launch Full System (Recommended)
Run the main application which starts both server and GUI:
```bash
java SearchCulturalHeritage
```

**Expected Output:**
```
Cultural Heritage Server started on port 8080
Application window appears with search interface
```

#### Option 2: Run Components Separately
1. Start the server in one terminal:
   ```bash
   java CulturalHeritageServer
   ```

2. Start the GUI in another terminal (modify GUI code to connect to localhost:8080):
   ```bash
   java CulturalHeritageGUI
   ```

### Using the Search Interface

#### Search Form Components
1. **Name Field**: 
   - Enter partial names of cultural objects
   - Example: "china" finds "Great Wall of China"
   - Case-insensitive matching

2. **Type Dropdown**:
   - Select from: Any, Monument, Artifact, Building, Site, Tradition
   - "Any" includes all types

3. **Location Field**:
   - Enter country or region names
   - Example: "Italy" finds Colosseum and Venice Carnival Masks
   - Case-insensitive matching

4. **Year Field**:
   - Enter exact year number
   - Leave blank to include all years
   - Example: "1653" finds Taj Mahal

#### Performing Searches

1. **Basic Search**:
   ```
   Name: Wall
   Type: Any
   Location: [blank]
   Year: [blank]
   ```
   *Finds: "Great Wall of China"*

2. **Filtered Search**:
   ```
   Name: [blank]
   Type: Monument
   Location: Italy
   Year: [blank]
   ```
   *Finds: "Colosseum"*

3. **Precise Search**:
   ```
   Name: Taj
   Type: Monument
   Location: India
   Year: 1653
   ```
   *Finds: "Taj Mahal"*

4. **Empty Search**:
   - Leave all fields blank or select "Any" for type
   - Returns all 15 sample cultural objects

#### Search Buttons
- **Search Button**: Executes the search with current criteria
- **Clear Button**: Resets all search fields and results table

### Understanding Results

#### Results Table Columns
1. **ID**: Unique identifier (1-15 for sample data)
2. **Name**: Name of the cultural object
3. **Type**: Category (Monument, Artifact, etc.)
4. **Location**: Country or region
5. **Year**: When it was created/built
6. **Description**: Brief description of the object

#### Status Messages
- **Ready to search**: Initial state, ready for input
- **Searching...**: Search in progress
- **Search completed. Found X result(s)**: Search finished successfully
- **Error: [message]**: Something went wrong

## Troubleshooting

### Common Issues and Solutions

#### 1. "Cannot find symbol" Compilation Error
**Problem**: Missing Java files or compilation order
**Solution**: 
```bash
# Ensure all files are in the same directory
ls *.java
# Recompile all files
javac *.java
```

#### 2. "Port 8080 already in use" Error
**Problem**: Another application is using port 8080
**Solution**:
```bash
# On Linux/macOS
lsof -i :8080
kill -9 [PID]

# Change port in CulturalHeritageServer.java line 57
serverSocket = new ServerSocket(9090);  # Use different port
```

#### 3. Slow Search Performance
**Problem**: Searches taking too long
**Solution**:
- Check console for performance warnings
- Reduce search criteria specificity
- Ensure adequate system resources

#### 4. Server Connection Failed
**Problem**: GUI shows "Server connection failed, using local search"
**Solution**:
- Ensure CulturalHeritageServer is running
- Check firewall settings for port 8080
- Verify localhost connectivity

### Performance Optimization

#### For Large Datasets
1. **Modify SearchService.java**:
   ```java
   // Consider adding database support
   // Index frequently searched fields
   ```

2. **Adjust Timeout Settings**:
   ```java
   // In CulturalHeritageGUI.java, line ~260
   socket.setSoTimeout(10000);  // Increase from 5000ms
   ```

### Extending the System

#### Adding New Cultural Objects
1. Edit `SearchService.java`
2. Add to `initializeSampleData()` method:
   ```java
   culturalObjects.add(new CulturalObject(
       "16",  // Next ID
       "New Object Name",
       "Type",
       "Location",
       2024,   // Year
       "Description"
   ));
   ```

#### Customizing Search Logic
Modify the `search()` method in `SearchService.java`:
- Change matching logic (exact vs. partial)
- Add new search parameters
- Modify sorting behavior

## Sample Queries

Try these example searches to explore the system:

1. **All Italian Cultural Heritage**:
   ```
   Name: 
   Type: Any
   Location: Italy
   Year: 
   ```
   *Expected: Colosseum, Venice Carnival Masks*

2. **Ancient Monuments**:
   ```
   Name: 
   Type: Monument
   Location: 
   Year: 
   ```
   *Expected: Various monuments*

3. **Chinese Heritage**:
   ```
   Name: 
   Type: Any
   Location: China
   Year: 
   ```
   *Expected: Great Wall of China, Forbidden City*

## Best Pract

1. **Use Specific Searches**: Combine criteria for faster, more accurate results
2. **Monitor Performance**: Check console output for search duration
3. **Handle Connection Issues**: System automatically falls back to local search
4. **Regular Backups**: Backup custom cultural object data
5. **Update Sample Data**: Periodically refresh with new cultural objects

## Support

For additional assistance:
- Check console error messages for details
- Review Java documentation for GUI and networking
- Contact system administrator for custom deployments

---

*Note: This is a demonstration system with sample data. For production use, integrate with a proper database and implement user authentication.*