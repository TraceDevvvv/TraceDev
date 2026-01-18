```markdown
# Tourist Localization System - User Manual

## Introduction

The **Tourist Localization System** is a Java application designed to simulate GPS-based position tracking for tourists. It implements the "Localization" use case as specified in the requirements, providing a complete, runnable program that handles GPS position calculation with timeout constraints and error handling.

## Main Features

### ✅ Core Functionality
- **GPS Position Calculation**: Simulates GPS hardware to calculate tourist's geographical coordinates
- **Timeout Management**: Enforces 5-second maximum processing time as per quality requirements
- **Error Handling**: Properly handles GPS signal loss and calculation failures
- **Retry Mechanism**: Supports multiple attempts for position acquisition in case of failures

### 🎯 Use Case Implementation
1. **Entry Condition**: System initialized when tourist begins a search or advanced search
2. **Flow of Events**:
   - System requests position data
   - GPS calculates tourist position
   - System waits for position data
3. **Exit Conditions**:
   - Success: System receives valid tourist position
   - Failure: Position not detectable by GPS

## Installation and Setup

### Prerequisites

Before running the Tourist Localization System, ensure you have the following installed:

#### 1. Java Development Kit (JDK)
- **Minimum Version**: JDK 8 or higher
- **Recommended**: JDK 11 or higher
- **How to Check**: Open terminal/command prompt and run:
  ```bash
  java -version
  ```

#### 2. Installation Methods

**Option A: Install JDK (if not already installed)**
- **Windows**: Download from [Oracle JDK](https://www.oracle.com/java/technologies/javase-downloads.html) or use [OpenJDK](https://adoptium.net/)
- **macOS**: 
  ```bash
  brew install openjdk@11
  ```
- **Linux (Ubuntu/Debian)**:
  ```bash
  sudo apt update
  sudo apt install openjdk-11-jdk
  ```

#### 3. Environment Variables (Windows Only)
After installing JDK, set JAVA_HOME environment variable:
1. Search for "Environment Variables" in Start Menu
2. Click "Edit the system environment variables"
3. Click "Environment Variables..."
4. Under System Variables, click "New"
5. Set:
   - **Variable Name**: `JAVA_HOME`
   - **Variable Value**: `C:\Program Files\Java\jdk-11.0.x` (adjust based on your installation path)

## How to Run

### Step 1: Save the Code
Create a new directory for the project and save the code as `Main.java`:

```bash
mkdir TouristLocalization
cd TouristLocalization
# Copy the Main.java code here using your favorite text editor
```

### Step 2: Compile the Program
Open terminal/command prompt in the project directory and run:

```bash
javac Main.java GPSPosition.java GPSException.java GPSProvider.java LocalizationSystem.java
```

**Note**: All classes are in the same file, so compiling `Main.java` will automatically compile all dependent classes.

### Step 3: Run the Program
Execute the compiled program:

```bash
java Main
```

## Usage Examples

### Basic Usage
The main program demonstrates the complete localization flow:

```bash
=== Tourist Localization System ===
Use Case: Localization
Entry Condition: Tourist began a search

System: Requesting tourist position data...
GPS: Calculating position...
System: Position received successfully

=== Result ===
Tourist Position: Latitude: 48.850123, Longitude: 2.347891, Accuracy: 12.34m
Exit Condition: System received position

=== Advanced Search (with retry) ===
Attempt 1 of 3:
System: Requesting tourist position data...
GPS: Calculating position...
System Error: GPS signal not available

Attempt 2 of 3:
System: Requesting tourist position data...
GPS: Calculating position...
System: Position received successfully
Advanced Search Successful: Latitude: 48.859456, Longitude: 2.358912, Accuracy: 8.76m
```

### Testing GPS Provider
You can test the GPS provider independently by running the test driver:

```bash
javac GPSTestDriver.java
java GPSTestDriver
```

**Expected Output**:
```
Requesting GPS position...
Position acquired: Latitude=48.851234, Longitude=2.349876, Accuracy=15.67m
```

## Program Structure

### 📁 Main Components

1. **Main.java** - Entry point of the application
2. **GPSPosition.java** - Data class representing geographical coordinates
   - Stores latitude, longitude, and accuracy
   - Provides formatted string representation
3. **GPSException.java** - Custom exception for GPS-related errors
4. **GPSProvider.java** - Core GPS simulation with timeout handling
5. **LocalizationSystem.java** - Manages the complete use case workflow
6. **GPSTestDriver.java** - Optional standalone test utility

### 🔧 Key Classes and Methods

#### LocalizationSystem Class
- **`getTouristPosition()`** - Primary method following use case flow
  - Requests position data
  - Waits for GPS calculation
  - Returns position or null if undetectable
- **`getTouristPositionWithRetry(maxRetries)`** - Advanced search with retry logic

#### GPSProvider Class
- **`calculatePosition()`** - Simulates GPS hardware with:
  - Random delay (0-4 seconds)
  - 80% success rate simulation
  - 5-second timeout enforcement
  - Error throwing for signal loss

## Configuration Options

### Customizing GPS Behavior
You can modify the GPS simulation parameters in `GPSProvider.java`:

```java
// Adjust these values in calculatePosition() method:

// Simulated success rate (currently 80%)
if (random.nextDouble() < 0.8) {
    // Success case
}

// Calculation delay range (0-4000ms = 0-4 seconds)
int delay = random.nextInt(4000);

// Default location (currently centered on Paris)
double latitude = 48.8566 + (random.nextDouble() - 0.5) * 0.01;
double longitude = 2.3522 + (random.nextDouble() - 0.5) * 0.01;
```

### Quality Requirement Settings
The 5-second timeout is implemented in the `Future.get()` call:

```java
GPSPosition position = future.get(5, TimeUnit.SECONDS);
```

## Error Handling

### Common Error Scenarios

1. **GPS Signal Not Available** (20% chance in simulation)
   ```
   System Error: GPS signal not available
   ```

2. **Timeout Exceeded** (if calculation takes >5 seconds)
   ```
   System Error: GPS calculation exceeded 5 second timeout
   ```

3. **GPS System Error**
   ```
   System Error: GPS system error: [specific error message]
   ```

### Recovery Options
- **Automatic Retry**: Use `getTouristPositionWithRetry()` for automatic retries
- **Manual Retry**: Call `getTouristPosition()` multiple times in your application logic
- **Graceful Degradation**: Application continues running even if position cannot be determined

## Integration Guide

### Embedding in Larger Systems
The LocalizationSystem can be easily integrated into larger tourist applications:

```java
// Example integration
public class TouristApp {
    private LocalizationSystem gpsSystem;
    
    public TouristApp() {
        gpsSystem = new LocalizationSystem();
    }
    
    public void performSearch() {
        System.out.println("Starting tourist search...");
        GPSPosition position = gpsSystem.getTouristPosition();
        
        if (position != null) {
            // Use position for mapping, recommendations, etc.
            displayOnMap(position);
            suggestNearbyAttractions(position);
        } else {
            // Fallback behavior
            useLastKnownPosition();
            notifyUser("GPS unavailable");
        }
    }
}
```

### Extending Functionality
Consider adding these enhancements for production use:

1. **Real GPS Integration**: Replace `GPSProvider` with actual GPS library (e.g., Android Location API)
2. **Persistence Layer**: Store position history in database
3. **Network Awareness**: Add fallback to network-based positioning
4. **Battery Optimization**: Implement location updates based on movement

## Troubleshooting

### Common Issues

#### 🔴 "javac: command not found"
**Solution**: JDK is not installed or not in PATH
- Verify installation: `java -version`
- Ensure `javac` is accessible from command line
- Reinstall JDK if necessary

#### 🔴 "Main class not found"
**Solution**: 
- Ensure you're in the correct directory
- Compile first: `javac Main.java`
- Run: `java Main` (not `java Main.java`)

#### 🔴 "Error: Could not find or load main class"
**Solution**:
- Check that all classes are in the same directory
- Ensure no package declarations at the top of files
- Delete and recompile: `del *.class` then `javac Main.java`

### Performance Issues

#### GPS Timeout Too Frequent
If you're getting frequent timeout messages:
- The simulation has a built-in 5-second limit
- In real implementation, adjust timeout based on:
  - Device capabilities
  - Environment (urban vs. rural)
  - Application requirements

#### High CPU Usage
The current implementation uses thread pools efficiently. For production:
- Consider using scheduled executors for periodic updates
- Implement location request batching
- Use appropriate thread pool sizes

## Best Pract

### 🚀 For Application Developers
1. **Always Check for null**: GPS position may be unavailable
2. **Use Retry Logic**: Implement retry with exponential backoff
3. **Monitor Battery Usage**: Frequent GPS use drains battery
4. **Respect Privacy**: Inform users about location tracking
5. **Handle Permissions**: Request location permissions appropriately

### 📊 For Testing
1. **Unit Tests**: Test each component independently
2. **Integration Tests**: Verify complete workflow
3. **Edge Cases**: Test timeout, signal loss, and recovery scenarios
4. **Performance Tests**: Ensure response time <5 seconds

## Support and Resources

### Getting Help
If you encounter issues:
1. Check the troubleshooting section above
2. Review the code comments for implementation details
3. Test with `GPSTestDriver` for isolated GPS testing

### Learning More
- **Java Concurrency**: Study `ExecutorService`, `Future`, and thread management
- **GPS Technology**: Understand real GPS implementation considerations
- **Location Serv**: Explore mobile platform location APIs

## License and Attribution

This Tourist Localization System is provided as a sample implementation for educational purposes. You are free to modify, extend, and integrate it into your projects.

### Key Design Decisions
1. **Timeout Handling**: Uses `Future.get()` with timeout parameter
2. **Simulation**: Random delays and success rates mimic real-world GPS
3. **Error Propagation**: Custom exceptions for clear error identification
4. **Thread Safety**: Proper executor service shutdown in all code paths

### Safety Considerations
⚠️ **Important**: This is a simulation. For production use:
- Replace simulation with actual GPS hardware integration
- Implement proper error recovery and user notifications
- Follow platform-specific best pract for location serv
- Comply with data protection regulations (GDPR, CCPA, etc.)

---

**Happy Coding!** 🗺️📍

*This manual covers the essential information to understand, install, and use the Tourist Localization System. For specific implementation questions or customization needs, refer to the inline code comments or consult the Java documentation.*
```