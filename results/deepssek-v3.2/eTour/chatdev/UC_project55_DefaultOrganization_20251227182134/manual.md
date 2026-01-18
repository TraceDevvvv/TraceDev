# FeedbackAlreadyReleased User Manual  

## **Overview**  
The **FeedbackAlreadyReleased** application is a Java-based software implementation of a use case where users attempt to submit feedback for a website, only to be notified that they have already submitted feedback for that site. The application demonstrates:  
- **Validation** of entry conditions (existing feedback).  
- **User notification** and operation cancellation.  
- **State recovery** to the previous application state.  
- **Seamless return** to user interaction after handling the scenario.  

The software is built using Java Swing for the graphical user interface (GUI) and follows a **multi-panel design** with clear separation between data management, business logic, and user interface components.  

---

## **Main Functions**  

### 1. **Simulated Feedback System**  
- Tracks feedback submissions per user and per site.  
- Stores feedback details: `site name`, `user ID`, `feedback text`, `rating`, and `submission date`.  

### 2. **Entry Condition Validation**  
- Automatically checks if a user has already submitted feedback for a given site.  
- Prevents duplicate submissions by canceling the operation.  

### 3. **User Notification Interface**  
- Displays a clear, formatted notification when duplicate feedback is detected.  
- Shows details of the existing feedback (rating, submission time, comments).  

### 4. **State Recovery**  
- Saves the application state before showing notifications.  
- Restores the previous state (screen, user, site, form data) after the user acknowledges the notification.  

### 5. **Interactive GUI**  
- Provides buttons to:  
  - Submit feedback (triggers validation and notification).  
  - Close the notification and return to the main screen.  
  - View all feedback entries in the system.  

### 6. **Test Scenarios**  
- **Test Mode:** Lets users choose between two scenarios at startup:  
  - **User HAS existing feedback** (triggers the notification).  
  - **User does NOT have existing feedback** (proceeds with submission).  

---

## **Installation & Environment Setup**  

### **Required Dependencies**  

#### 1. **Java Development Kit (JDK)**  
- **Version:** JDK 8 or higher.  
- **Download:** [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html) or [OpenJDK](https://openjdk.org/).  
- **Verification:**  
  ```bash
  java -version
  javac -version
  ```
  Ensure both commands return a version ≥ 1.8.  

#### 2. **Java Runtime Environment (JRE)**  
- Included with JDK installation.  

#### 3. **Operating System**  
- **Windows 7/10/11**, **macOS 10.10+**, or **Linux** with GUI support.  
- Display resolution: **800x600** or higher.  

#### 4. **System Resources**  
- **RAM:** Minimum 512MB.  
- **Disk space:** ~50MB for the application and compiled classes.  

#### 5. **Optional Build Tools**  
- **Maven** (≥3.6.0) or **Gradle** (≥6.0) – for advanced builds.  
- **IDEs (optional):**  
  - IntelliJ IDEA (≥2020.1)  
  - Eclipse (≥2020-06)  
  - VS Code with Java Extension Pack  
  - NetBeans (≥12.0)  

---

## **How to Use the Software**  

### **Step 1: Download and Extract**  
1. Download the source code package containing all `.java` files.  
2. Extract to a folder, e.g., `FeedbackAlreadyReleased/`.  

### **Step 2: Compile the Application**  
Open a terminal/command prompt in the project folder and run:  
```bash
javac -d . feedbacksystem/*.java
```
This compiles all Java files into the `feedbacksystem` package directory.  

### **Step 3: Run the Application**  
Execute the main class:  
```bash
java feedbacksystem.Main
```
The application window will launch.  

---

## **User Interface Walkthrough**  

### **1. Launch Screen – Test Scenario Selection**  
On startup, a dialog appears:  
```
Select test scenario:
☑ Test: User HAS existing feedback  
☐ Test: User does NOT have existing feedback  
```
- Choose **first option** to see the duplicate feedback notification.  
- Choose **second option** to proceed without notification.  

### **2. Main Application Window**  
The window has three sections:  

#### **Header**  
- Displays: `"Feedback Management System - Use Case: FeedbackAlreadyReleased"`.  

#### **Center Panel (Dynamic)**  
- Initially shows a simulated feedback form with:  
  - Site name and user ID (pre-filled).  
  - Rating and comments fields (editable).  
  - **Submit Feedback** button.  

#### **Footer**  
- Shows the **use case flow status** (all steps marked ✅ when completed).  

---

### **3. Using the Application**  

#### **Scenario A: User HAS Existing Feedback**  
1. Click **Submit Feedback** (or the **Submit Feedback** button in the form).  
2. The system detects duplicate feedback and shows a **notification panel**:  
   - **Red warning message:** "⚠ FEEDBACK ALREADY SUBMITTED ⚠".  
   - **Details panel:** Shows the existing feedback (rating, date, text).  
3. **Buttons:**  
   - **Close Notification & Return to Main Screen:**  
     - Restores the previous state.  
     - Shows a confirmation dialog.  
     - Returns to the main screen.  
   - **View All Site Feedback:**  
     - Displays summary of all feedback entries in the system.  

#### **Scenario B: User Does NOT Have Existing Feedback**  
1. Click **Submit Feedback**.  
2. System shows an **information dialog**:  
   - `"No existing feedback found. Proceeding with submission..."`.  
3. The application would then proceed to submit feedback (simulated).  

---

### **4. State Recovery Demo**  
When you close the notification:  
- The system **restores the previous application state** (screen, site, user).  
- A confirmation dialog appears with details of the restored state.  
- The **footer updates** to show all use case steps as completed (✅).  

---

## **Key Features Demonstrated**  

### **✅ Entry Conditions Validation**  
- Validates if `(user, site)` already has feedback.  
- Uses `FeedbackManager.validateEntryCondition()`.  

### **✅ Notification & Operation Cancellation**  
- Clear visual notification with formatted HTML.  
- Operation to insert new feedback is **cancelled automatically**.  

### **✅ Confirmation of Notification Reading**  
- Simulated via `acknowledgeNotification()` when the notification panel is displayed.  

### **✅ State Recovery**  
- `ApplicationState` objects save/restore screen, site, user, and form data.  
- Implemented in `FeedbackSubmissionHandler.restorePreviousState()`.  

### **✅ Return to User Interaction**  
- After closing the notification, control returns to the main interactive screen.  

---

## **Troubleshooting**  

| Issue | Solution |
|-------|----------|
| **"Error: could not find or load main class"** | Ensure you compiled with `-d .` and are running `java feedbacksystem.Main`. |
| **GUI doesn't appear** | Check that your OS has a GUI environment (Windows/macOS/Linux with desktop). |
| **Buttons don't respond** | Ensure Java Swing libraries are available (included in JDK 8+). |
| **Compilation errors** | Verify all `.java` files are in the `feedbacksystem/` folder and JDK ≥ 8. |

---

## **Uninstall**  
Simply delete the project folder. No system files or registry entries are modified.  

---

## **Final Notes**  
- This is a **demonstration application** for the **FeedbackAlreadyReleased use case**.  
- No internet connection, database, or external APIs are required.  
- All data is stored in memory and reset on each run.  
- The code is fully documented—browse `.java` files for detailed comments.  

**Enjoy exploring the FeedbackAlreadyReleased application!** 🚀