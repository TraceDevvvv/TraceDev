```markdown
# Report Card Viewing System - User Manual

##  📋 Overview

The Report Card Viewing System is a Java-based desktop application designed for school administrators ("Direction") to access and view student report cards. The system follows a structured workflow that guides users through academic year selection, class selection, student selection, and finally displaying detailed report cards with comprehensive information.

##  ✨ Main Features

### 1. **Secure Authentication System**
- Role-based login for "Direction" users
- Pre-filled credentials for demo purposes (`direction` / `password123`)
- Logout functionality with clean session termination

### 2. **Academic Year Management**
- View all available academic years
- Identify current year with visual indicator
- Support for multiple past academic years

### 3. **Classroom Navigation**
- Browse classes organized by academic year
- View class details (name, grade level, room number)
- Access report cards through dedicated buttons

### 4. **Student Selection**
- View students organized by selected class
- Multi-dimensional selection (student + quadrimestre)
- Comprehensive student information display

### 5. **Report Card Display**
- Detailed subject-by-subject grade breakdown
- Average grade calculation with letter conversion
- Teacher and principal comments sections
- Professional formatting with color-coded elements

### 6. **User Experience**
- Clean, intuitive graphical interface
- Step-by-step workflow guidance
- Error handling and validation
- Navigation controls (back, logout, view another)

##   Installation & Setup

### Prerequisites

1. **Java Development Kit (JDK)**
   - Version 8 or higher required
   - Download from [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)

2. **System Requirements**
   - Minimum: 2GB RAM, 1GB free disk space
   - Recommended: 4GB RAM, 2GB free disk space
   - Any modern operating system (Windows, macOS, Linux)

### Installation Steps

#### Option 1: Using Compiled JAR File

1. **Download the Application**
   ```bash
   mkdir ReportCardSystem
   cd ReportCardSystem
   ```

2. **Copy these files to the directory:**
   ```
   Main.java
   LoginFrame.java
   Database.java
   AcademicYear.java
   Classroom.java
   Student.java
   ReportCard.java
   SubjectGrade.java
   ```

3. **Compile the Application**
   ```bash
   javac *.java
   ```

4. **Run the Application**
   ```bash
   java Main
   ```

#### Option 2: Using an IDE (Recommended)

1. **Download and install an IDE:**
   - [IntelliJ IDEA](https://www.jetbrains.com/idea/)
   - [Eclipse](https://www.eclipse.org/)
   - [VS Code](https://code.visualstudio.com/) with Java Extension Pack

2. **Create a new Java project**

3. **Copy all `.java` files into the project's source directory**

4. **Set up the project structure:**
   - All classes should be in the same package
   - No external libraries required - uses only standard Java libraries

5. **Build and run the project**

### Verifying Installation

1. Open a terminal/command prompt
2. Check Java version:
   ```bash
   java -version
   ```
   Should display: `java version "1.8.0_XXX"` or higher
3. Check Java compiler:
   ```bash
   javac -version
   ```
   Should display: `javac 1.8.0_XXX` or higher

##  📖 Using the Application

### Step 1: Login

1. **Launch the application** by running `Main.java`
2. **Login Screen appears** with pre-filled credentials:
   ```
   Username: direction
   Password: password123
   ```
3. **Click "Login"** to authenticate
4. **Cancel** button exits the application immediately

### Step 2: Main Menu

After successful login:
1. **Welcome screen** displays with greeting
2. **"Online Report Cards"** button - start report viewing process
3. **"Logout"** button - return to login screen

### Step 3: Academic Year Selection

1. **Dialog opens** showing available academic years
2. **Each year displays** in format: `YYYY-YYYY (Current)` for current year
3. **Features:**
   - Scrollable list for multiple years
   - Visual indication of current year
   - Cancel button to return to main menu
4. **Select a year** and click "Select"

### Step 4: Class Selection

1. **Classes list appears** for selected academic year
2. **Each class displays** format: `ClassName - RoomNumber (GradeLevel)`
3. **Additional information:**
   - Shows all classes in the selected year
   - "Report Cards" button next to selected class
   - "Back" button to return to year selection
4. **Select a class** and click "Report Cards"

### Step 5: Student & Quadrimestre Selection

1. **Student list appears** for selected class
2. **Each student shows**: `FullName (StudentID)`
3. **Quadrimestre selection** dropdown with options:
   - First Quadrimestre
   - Second Quadrimestre
   - Third Quadrimestre
4. **Navigation:**
   - "View Report Card" to proceed
   - "Back" to return to class selection
5. **Select a student** and **choose a quadrimestre**

### Step 6: Report Card Display

The report card is organized into several sections:

#### **Header Information**
- Student name
- Academic year
- Selected quadrimestre
- Class information

#### **Subject Grades Table**
Columns:
1. **Subject** - Course name
2. **Code** - Course code
3. **Grade** - Numerical grade (formatted to 2 decimals)
4. **Letter** - Letter grade (A-F scale)
5. **Teacher** - Instructor name
6. **Comments** - Subject-specific feedback

#### **Summary Section**
- **Overall Average** - Calculated average grade with letter equivalent
- **Teacher Comments** - General feedback from the teacher
- **Principal Comments** - Comments from school principal

#### **Action Buttons**
1. **Print Report** - Simulated print function (shows info message)
2. **Close** - Close report and optionally logout
3. **View Another** - Return to academic year selection for new report

## 🔒 Security Notes

### Demo Credentials
- Username: `direction`
- Password: `password123`
- **Important**: These are for demonstration only. In production, implement proper authentication.

### Session Management
- Clean logout process
- Connection termination simulation
- No persistent credential storage in demo version

##  💡 Tips & Best Pract

### For First-Time Users
1. Follow the step-by-step workflow
2. Use the provided demo credentials
3. All navigation buttons are labeled clearly
4. Use "Back" buttons to correct selections

### Data Management
- The application uses mock data stored in `Database.java`
- Real implementation would connect to SMOS server
- Data is reset on each application restart (demo only)

### Navigation Flow
```
Login → Main Menu → Year Selection → Class Selection → 
Student+Quadrimestre Selection → Report Card Display
```

You can:
- Navigate back at any step
- Logout from most screens
- View multiple reports per session

##  🛠️ Troubleshooting

### Common Issues

#### **1. Java Not Found**
```
Error: Could not find or load main class Main
```
**Solution:** Ensure Java is installed and PATH is set correctly.

#### **2. Compilation Errors**
```
LoginFrame.java: error: package javax.swing does not exist
```
**Solution:** Use Java 8 or higher which includes Swing libraries.

#### **3. Application Won't Start**
```
Exception in thread "main" java.lang.NoClassDefFoundError
```
**Solution:** Compile all `.java` files together in the same directory.

#### **4. GUI Not Displaying Correctly**
- **Symptoms:** Missing buttons, distorted layout
- **Solution:** Use `SwingUtilities.invokeLater()` (already implemented)

### Known Limitations

1. **Demo Data Only** - All data is pre-loaded mock data
2. **No Database Connection** - Standalone application only
3. **No Persistent Storage** - Changes are not saved between sessions
4. **Single User Role** - Only "Direction" role implemented
5. **Print Function Simulated** - Generates message instead of actual print

##  🔧 Customization

### Modifying Mock Data
Edit `Database.java` to:
- Add/remove academic years in `initializeMockData()`
- Create new students with `new Student(...)`
- Add classes with `new Classroom(...)`
- Create report cards with `new ReportCard(...)`

### UI Customization
Modify `LoginFrame.java` for:
- Colors in `new Color(R, G, B)` calls
- Font styles and sizes
- Window dimensions
- Button layouts

### Extending Functionality
Potential enhancements:
1. **Add database connectivity** - Replace mock data with real database
2. **Implement user management** - Multiple roles and credentials
3. **Add report generation** - PDF export functionality
4. **Include search features** - Find students by name or ID
5. **Implement editing capabilities** - Modify existing report cards

##  📞 Support

### Quick Reference Commands

```bash
# Compile all files
javac *.java

# Run application
java Main

# Check Java installation
java -version
```

### Demo Data Summary

**Academic Years Available:**
- 2021-2022
- 2022-2023
- 2023-2024 (Current)

**Sample Students:**
1. John Doe (S001) - Grade 5A
2. Jane Smith (S002) - Grade 5A
3. Mike Johnson (S003) - Grade 5B
4. Emily Williams (S004) - Grade 5B
5. David Brown (S005) - Grade 6A
6. Sarah Davis (S006) - Grade 6A

**Available Report Cards:**
- John Doe (First & Second Quadrimestre 2023-2024)
- Jane Smith (First Quadrimestre 2023-2024)
- Mike Johnson (First Quadrimestre 2023-2024)

##  🎯 Conclusion

This Report Card Viewing System provides a complete, user-friendly solution for school administrators to access student academic records. The step-by-step workflow ensures intuitive navigation, while the professional interface displays information clearly and attractively.

The application is production-ready in terms of user experience and can be extended with database connectivity and additional features for real-world deployment. The modular design allows for easy maintenance and enhancement to meet specific institutional requirements.
```