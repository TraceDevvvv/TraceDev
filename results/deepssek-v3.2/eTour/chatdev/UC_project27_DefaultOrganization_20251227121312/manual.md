```markdown
# SearchSite User Manual

## Overview
SearchSite is a Java-based application that enables users to search for specific files and directories (referred to as "sites") on their computer. The application implements a comprehensive search functionality with timeout management and connection interruption handling, ensuring robust performance even under constrained network conditions.

## Main Features
- **Intelligent Search**: Perform case-insensitive partial matching searches across simulated site paths
- **Timeout Management**: Configure custom timeout limits to ensure responsive performance
- **Connection Resilience**: Handles server connection interruptions gracefully with proper error recovery
- **Interactive Interface**: User-friendly command-line interface with clear prompts and feedback
- **Performance Monitoring**: Displays search execution time for performance analysis

## System Requirements
- **Java Runtime Environment**: Version 8 or higher
- **Operating System**: Windows, macOS, or Linux
- **Memory**: Minimum 512MB RAM recommended
- **Storage**: Sufficient space for Java runtime and application files

## Installation

### Step 1: Install Java
If Java is not already installed on your system:

**Windows:**
1. Download Java from [Oracle's official website](https://www.oracle.com/java/technologies/downloads/)
2. Run the installer and follow the setup wizard
3. Verify installation by opening Command Prompt and typing:
   ```bash
   java -version
   ```

**macOS:**
```bash
brew install openjdk
```

**Linux (Ubuntu/Debian):**
```bash
sudo apt update
sudo apt install default-jre
```

### Step 2: Set Up the Application

#### Option A: Using Pre-compiled Files
1. Download the following files:
   - `ConnectionInterruptedException.java`
   - `SearchSite.java`

2. Create a dedicated directory for the application:
   ```bash
   mkdir SearchSiteApp
   cd SearchSiteApp
   ```

3. Place both Java files in this directory.

#### Option B: Compiling from Source
If you have the source code in a single file:

1. Save the complete code as `SearchSiteApplication.java`
2. Navigate to the directory containing the file
3. Compile the program:
   ```bash
   javac SearchSiteApplication.java
   ```

## Usage Guide

### Basic Operation

1. **Start the Application:**
   ```bash
   java SearchSite
   ```

2. **Application Flow:**
   - **Step 1**: Press Enter to activate search functionality
   - **Step 2**: Enter your search query when prompted
   - **Step 3**: Set a timeout value (or press Enter for default of 5000ms)
   - **Step 4**: View search results

### Search Queries

The application searches through a predefined set of simulated site paths. You can search for:
- **Full filenames**: `index.html`
- **Partial names**: `site`
- **Directories**: `WebSites`
- **Extensions**: `.php`

**Example Searches:**
- `index` - finds files containing "index"
- `site` - finds all site-related files
- `C:/WebSites` - finds files in specific directory
- `.html` - finds all HTML files

### Timeout Configuration

- **Default**: 5000 milliseconds (5 seconds)
- **Minimum**: 100 milliseconds
- **Maximum**: System-dependent (practically unlimited)

**Timeout Guidelines:**
- For local searches: 100-1000ms
- For network-based searches: 5000ms or more
- Adjust based on system performance and network conditions

### Understanding Output

**Successful Search:**
```
Sites found (3) in 234 ms:
  - C:/WebSites/index.html
  - C:/WebSites/contact.html
  - D:/Projects/Site1/home.html
```

**No Results:**
```
No sites found matching: [query]
```

**Error Conditions:**
- **Timeout**: "Search exceeded time limit of [timeout] ms"
- **Connection Interrupted**: "Connection to server ETOUR interrupted"

## Advanced Features

### Custom Site Database
To modify the searchable sites, edit the `SITES` array in the `SearchSite.java` file:

```java
private static final List<String> SITES = Arrays.asList(
    // Add your custom paths here
    "C:/MyWebsites/home.html",
    "D:/Projects/MyApp/index.php"
);
```

### Simulated Network Conditions
The application includes:
- 10% chance of simulated connection interruption (configurable in code)
- 200ms simulated processing delay (adjustable in code)

## Troubleshooting

### Common Issues

**1. "java: command not found"**
- Solution: Ensure Java is properly installed and added to PATH
- Verify: Run `java -version` in terminal

**2. "Error: Could not find or load main class"**
- Solution: Compile the Java files first:
  ```bash
  javac *.java
  java SearchSite
  ```

**3. Connection Interruptions**
- This is a simulated feature to test error handling
- Real interruptions would occur with network-based searches

**4. Timeout Errors**
- Increase the timeout value
- Check system performance and close unnecessary applications

### Performance Tips

1. **Optimize Timeout**: Start with 5000ms, adjust based on results
2. **Specific Queries**: Use more specific search terms for faster results
3. **Batch Searches**: For multiple searches, note that each runs independently

## Quality Requirements

The application meets the following quality standards:
- **Performance**: Returns results within user-defined time limits
- **Reliability**: Handles connection interruptions gracefully
- **Usability**: Clear interface with helpful prompts and feedback
- **Maintainability**: Well-commented code with proper error handling

## Simulated Data Structure

The application searches through these default paths:
- `C:/WebSites/index.html`
- `D:/Projects/Site1/home.html`
- `C:/Users/Admin/Documents/site2/login.php`
- `E:/Backup/site3/about.html`
- `C:/WebSites/contact.html`

## Limitations

1. **Simulated Search**: Currently searches a predefined list, not actual file system
2. **Single Thread**: Searches run sequentially (suitable for demonstration)
3. **Command-Line Only**: No graphical user interface

## Future Enhancements

Potential improvements for future versions:
- Real file system searching
- Graphical user interface
- Advanced search filters (date, size, type)
- Search history and saved queries
- Multi-threaded searching for better performance

## Support

For issues or questions:
1. Check the troubleshooting section above
2. Review the inline code comments
3. Ensure Java is properly installed and updated

## Version Information

- **Current Version**: 1.0
- **Last Updated**: [Current Date]
- **Java Version**: Compatible with Java 8+
- **License**: Demonstration/Educational Use

---

**Note**: This is a demonstration application for educational purposes. The search functionality uses simulated data and does not perform actual file system searches without modification.
```