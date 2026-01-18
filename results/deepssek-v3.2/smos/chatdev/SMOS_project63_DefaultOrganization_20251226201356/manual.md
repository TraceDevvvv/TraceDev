```markdown
# Student Monitoring System - Direction

## Overview

The Student Monitoring System is a Java-based GUI application designed specifically for the "Direction" actor role within an educational institution. The system allows authorized personnel to monitor students based on predefined thresholds for both academic notes and attendance absences.

### Key Features

- **Threshold-based Filtering**: Search for students whose notes and absences exceed user-defined thresholds
- **Intuitive GUI Interface**: User-friendly Swing-based interface with clear input and display areas
- **Error Handling**: Robust handling of invalid inputs and simulated server connection failures
- **Fallback Data Display**: Graceful degradation when server connections are interrupted
- **Data Visualization**: Tabular display of student information with relevant metrics

## System Requirements

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Minimum 2GB RAM
- 200MB free disk space

### Software Dependencies
- Java Runtime Environment (JRE) 8+
- Standard Java Swing libraries (included with JDK/JRE)
- No external libraries required

## Installation

### Step 1: Verify Java Installation
Open a command prompt or terminal and type:
```bash
java -version
javac -version
```

If Java is not installed, download and install from [Oracle's website](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/).

### Step 2: Download the Application
1. Create a new directory for the application:
```bash
mkdir StudentMonitoringSystem
cd StudentMonitoringSystem
```

2. Copy all Java files to this directory:
   - `Main.java`
   - `Student.java`
   - `StudentDataService.java`

### Step 3: Compile the Application
In the directory containing all Java files, run:
```bash
javac *.java
```

This will generate `.class` files for all Java classes.

## How to Use

### Launching the Application
Run the following command:
```bash
java Main
```

### Interface Layout
The application window consists of four main sections:

1. **Threshold Input Panel** (Top)
   - Notes Threshold field: Enter minimum notes count
   - Absences Threshold field: Enter minimum absences count
   - Both fields accept non-negative integers only

2. **Search Button** (Center)
   - Click "Search Students" to execute the query

3. **Results Table** (Bottom)
   - Displays filtered student data with columns:
     * ID: Student identifier
     * Name: Student full name
     * Notes Count: Number of academic notes
     * Absences Count: Number of attendance absences

4. **Status Bar** (Bottom)
   - Shows operation status and result summary

### Step-by-Step Usage

#### Step 1: Set Thresholds
1. **Notes Threshold**: Enter the minimum number of academic notes. For example, enter "5" to find students with 5 or more notes.
2. **Absences Threshold**: Enter the minimum number of absences. For example, enter "3" to find students with 3 or more absences.

Default values are pre-filled for convenience.

#### Step 2: Execute Search
Click the **"Search Students"** button to:
- Validate input values
- Connect to simulated SMOS server
- Filter students based on thresholds
- Display results in the table

#### Step 3: Interpret Results
- If students are found, they appear in the results table
- The status bar shows the number of students found
- If no students meet the criteria, the status bar indicates "No students found"
- Double-click table headers to sort results

### Sample Scenarios

#### Scenario 1: Normal Operation
```
Input:
  Notes Threshold: 5
  Absences Threshold: 3
  
Output:
  Status: "Found 3 student(s) exceeding thresholds."
  Table shows 3 students with notes ≥5 and absences ≥3
```

#### Scenario 2: No Results
```
Input:
  Notes Threshold: 20
  Absences Threshold: 15
  
Output:
  Status: "No students found exceeding both thresholds."
  Table remains empty
```

#### Scenario 3: Server Connection Error
```
When the simulated server fails:
  Status: "Error: Failed to connect to SMOS server. Data may be incomplete."
  Warning dialog appears
  Table shows all available dummy data
  Search functionality continues with available data
```

#### Scenario 4: Invalid Input
```
Input:
  Notes Threshold: "abc"
  Absences Threshold: -1
  
Output:
  Error dialog with appropriate message
  No search executed
  Current table data preserved
```

## Data Model

### Student Entity
Each student record contains:
- **ID**: Unique numeric identifier (1-10 in demo)
- **Name**: Student's full name
- **Notes Count**: Number of academic observations (0-14 in demo)
- **Absences Count**: Number of attendance absences (0-9 in demo)

### Data Source
The application uses:
1. **Primary Source**: Simulated SMOS server connection (may fail for testing)
2. **Fallback Source**: Internal dummy data generator with 10 sample students

## Error Handling

### Input Validation Errors
- **Non-integer values**: Shows "Please enter valid integers for thresholds"
- **Negative values**: Shows "Thresholds cannot be negative"
- **Empty fields**: Treated as invalid input

### Server Connection Errors
When SMOS server simulation fails:
1. Warning dialog appears
2. Status bar updates with error message
3. Application continues with available data
4. User can retry the search

### Application Errors
- Unexpected errors are caught and displayed in user-friendly messages
- Application remains stable and responsive

## Customization

### Modifying Dummy Data
To change the sample student data, edit the `generateDummyData()` method in `StudentDataService.java`:

```java
// Add more names to this array
String[] names = {"Alice Johnson", "Bob Smith", /* Add more names */};

// Modify random ranges
int notes = random.nextInt(20); // Change 15 to desired maximum
int absences = random.nextInt(15); // Change 10 to desired maximum
```

### Simulating Server Failure
To test error handling, set `simulateServerFailure = true` in `StudentDataService.java`:

```java
private boolean simulateServerFailure = true; // Set to true to test server error
```

## Troubleshooting

### Common Issues

#### 1. "Could not find or load main class Main"
- **Solution**: Ensure you're in the correct directory and compiled files exist
- Run `javac *.java` then `java Main`

#### 2. Application does not start
- **Solution**: Verify Java installation with `java -version`
- Ensure you have Java 8 or higher

#### 3. GUI appears cut off or misaligned
- **Solution**: Resize the window or adjust your screen resolution
- Minimum recommended screen resolution: 1024x768

#### 4. No data appears in table
- **Solution**: Check threshold values are not too high
- Try lower thresholds like 0 for both fields

### Performance Tips
- For larger datasets, consider increasing JVM memory:
```bash
java -Xmx512m Main
```
- Close other applications for optimal performance
- Use reasonable threshold values to filter results effectively

## Security Considerations

### Access Control
- The application is designed for "Direction" role users only
- Actual authentication should be implemented in production

### Data Privacy
- Demo data uses fictional student names
- Real implementation must comply with data protection regulations
- Sensitive data should be encrypted in transit and at rest

## Support

### Contact Information
For technical support or feature requests, contact your system administrator.

### Documentation
Additional documentation can be found in:
- Code comments within Java files
- README.txt file included with distribution

### Updates
Check regularly for application updates that may include:
- Enhanced features
- Security patches
- Performance improvements

## License and Usage

This application is provided for educational and demonstration purposes. For production use, additional features should be implemented including:
- Real database integration
- User authentication and authorization
- Audit logging
- Data export capabilities
- Report generation

---

*Note: This is a demonstration system. In a production environment, proper database connectivity, security measures, and user management should be implemented according to institutional policies and relevant regulations.*
```