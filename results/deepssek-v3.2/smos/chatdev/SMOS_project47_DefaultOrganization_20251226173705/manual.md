```markdown
# Student Report Card Editor - User Manual

##  📋 Overview

The **Student Report Card Editor** is a Java-based desktop application designed for educational administrators to manage student report cards. This software allows administrators to view, edit, and update student academic records through an intuitive graphical user interface.

### Key Features

- **Student List View**: Display all students with their academic information
- **Edit Functionality**: Modify individual student report cards
- **Data Validation**: Ensures valid grade inputs and complete information
- **Navigation System**: Easy switching between list and edit views
- **Server Simulation**: Includes SMOS server connection interruption simulation

##  🛠 Installation Requirements

### System Requirements

- **Operating System**: Windows, macOS, or Linux
- **Java Runtime**: Java 8 or higher (JDK 8+ recommended)
- **Memory**: Minimum 512MB RAM
-python 
- **Storage**: 50MB free disk space

### Software Dependencies

The application requires the following Java libraries:

- **Java Swing** (included in standard Java SE)
- **Java AWT** (included in standard Java SE)

No additional external libraries are required as the application uses only standard Java libraries.

##  📥 Installation Steps

### Step 1: Install Java Development Kit (JDK)

1. Download JDK from [Oracle's website](https://www.oracle.com/java/technologies/javase-downloads.html) or use OpenJDK
2. Follow the installation instructions for your operating system
3. Verify installation by opening a terminal/command prompt and typing:
   ```bash
   java -version
   ```
   You should see Java version information.

### Step 2: Download Application Files

Download or create the following Java files in a single directory:

1. **Main.java** - Application entry point
2. **Student.java** - Student data model
3. **StudentDataManager.java** - Data management operations
4. **StudentListPanel.java** - Student list display panel
5. **EditFormPanel.java** - Report card edit panel

### Step 3: Compile the Application

1. Open a terminal/command prompt
2. Navigate to the directory containing the Java files
3. Compile all Java files:
   ```bash
   javac *.java
   ```
4. Verify that .class files have been created:
   ```bash
   dir *.class
   ```

### Step 4: Run the Application

1. From the same directory, run:
   ```bash
   java Main
   ```
2. The application window should appear on your screen.

##  🎮 How to Use the Application

### Starting the Application

1. **Launch**: Execute `java Main` from the command line
2. **Main Window**: The application opens with the Student List View
3. **Window Controls**: Standard minimize, maximize, and close buttons

### Main Interface - Student List View

Upon startup, you'll see:

- **Title**: "Student Report Cards" at the top
- **Data Table**: Shows all students with columns:
  - ID (Student identification number)
  - Name (Student's full name)
  - Grade Level (Academic year 1-12)
  - Subject Grades (Math, Science, English, History, Art)
  - Edit Button (Click to edit student record)
- **Control Buttons**:
  - Refresh List: Updates the display with current data
  - Exit (SMOS Disconnect): Simulates server disconnection

### Editing a Student Report Card

**Preconditions (as per use case):**
1. Administrator is logged into the system
2. Student list is displayed (DisplayedUnapagella use case)
3. Edit button is clicked

**Step-by-Step Process:**

1. **Navigate to Edit View**:
   - Double-click the "Edit" button in any student row **OR**
   - Click the "Edit" button in the table

2. **Edit Form Display**:
   - The system shows all editable fields pre-filled with current data
   - Student ID is fixed (cannot be modified)
   - Editable fields include:
     - Student Name
     - Grade Level (1-12)
     - Subject Grades (Math, Science, English, History, Art)

3. **Enter New Data**:
   - Modify any field as needed
   - Follow validation rules:
     - All fields are required
     - Grade Level must be 1-12 (numeric)
     - Subject grades accept:
       - Letter grades (A-F) with optional +/- (e.g., A, B+, C-)
       - Percentage grades (0-100) with optional % sign (e.g., 85%, 92)

4. **Save Changes**:
   - Click "Save Changes" button
   - System validates all inputs
   - On successful save:
     - Confirmation message appears
     - System returns to Student List View
     - Updated data is reflected in the table

5. **Cancel Edit**:
   - Click "Cancel" to discard changes
   - System asks for confirmation
   - Returns to Student List View without saving

### Post-Operation Actions

**After Successful Edit:**
- The student's report card is updated in the system
- The list view shows the modified data
- Confirmation message indicates success

**SMOS Server Disconnection:**
- Click "Exit (SMOS Disconnect)" button
- System asks for confirmation
- Application closes, simulating server interruption

##  ⚙️ Data Management

### Student Data Structure

Each student record contains:

```java
Student ID: Unique identifier (e.g., "S001")
Student Name: Full name (e.g., "Alice Johnson")
Grade Level: Academic year (1-12)
Subject Grades: Individual grades for five subjects
  - Math
  - Science
  - English
  - History
  - Art
```

### Initial Sample Data

The system is pre-loaded with three sample students:
1. Alice Johnson (ID: S001) - Grade 10
2. Bob Smith (ID: S002) - Grade 11
3. Carol Davis (ID: S003) - Grade 9

### Data Persistence

**Important Note**: This demonstration version uses in-memory data storage. All changes are lost when the application closes. For production use, database integration would be required.

## 🔧 Advanced Features

### Keyboard Navigation

- **Tab Key**: Navigate between form fields
- **Enter Key**: Activates focused button
- **Escape Key**: Cancels current operation
- **F5 Refresh**: Can be implemented for list refresh

### Data Validation Rules

The system enforces the following validation:

1. **Field Completeness**: All fields must contain data
2. **Grade Level Validation**: Must be numeric between 1 and 12
3. **Grade Format Validation**:
   - Letter grades: A, B, C, D, F with optional +/-
   - Percentage grades: 0-100 with optional % sign
   - Maximum length: 3 characters

4. **Duplicate Prevention**: Student IDs must be unique
5. **Data Integrity**: Update operations verify student exists before modifying

### Error Handling

The application provides user-friendly error messages for:

- **Validation Errors**: When input data doesn't meet requirements
- **Update Failures**: When student data cannot be saved
- **System Errors**: For unexpected application issues

##  📊 Use Case Alignment

### Preconditions Met
- ✓ User logged in as administrator
- ✓ Student list displayed (DisplayedUnapagella)
- ✓ Edit button available and functional

### Event Sequence Implementation
1. ✓ System displays edit form with current data
2. ✓ User enters new data and clicks confirmation
3. ✓ System shows confirmation and returns to list

### Postconditions Achieved
- ✓ Report card modified successfully
- ✓ User can interrupt SMOS connection (simulated)

##  🚨 Troubleshooting

### Common Issues

**Issue**: "Java not found" error
**Solution**: Ensure Java is installed and PATH environment variable is set

**Issue**: Application window doesn't appear
**Solution**: Check console for error messages and ensure all .java files are in same directory

**Issue**: "Could not find or load main class"
**Solution**: Run `javac *.java` to compile all files before running

**Issue**: Button clicks not working
**Solution**: Wait for full application load; check console for Java exceptions

**Issue**: Data not saving
**Solution**: Verify input meets validation requirements; check error messages

### Data Recovery

Since this version uses in-memory storage:
- All data is lost on application restart
- Regular saves during session are maintained until exit
- Consider implementing data export feature for critical data

##   Support

### Application Limitations

1. **No Database**: Uses in-memory storage only
2. **No Authentication**: Login simulation only
3. **No Network Features**: Server disconnection is simulated
4. **Single User**: Designed for single administrator use

### Extension Possibilities

Future versions could include:
- Database integration (MySQL, PostgreSQL)
- Multiple user authentication
- Network/server communication
- Report generation
- Data import/export features
- Backup and restore functionality

### Getting Help

For issues with the application:
1. Review this manual for relevant sections
2. Check console output for Java errors
3. Ensure all file versions are compatible
4. Verify Java installation is up to date

##  📄 License and Distribution

This application is provided as a demonstration of Java Swing programming concepts. It may be freely used, modified, and distributed for educational purposes.

**Disclaimer**: This software is for demonstration purposes. Always backup important data when using any data management software.

---

*Last Updated: November 2023*
*Version: 1.0*
*Author: ChatDev Software Solutions*
```