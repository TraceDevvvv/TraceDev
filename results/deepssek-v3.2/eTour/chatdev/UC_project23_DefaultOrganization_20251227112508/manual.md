**Manual for ModifyNews Application**  

## 📋 Overview  

The ModifyNews application is a Java-based desktop software designed for Agency Operators to efficiently edit and update news items. It provides a complete workflow for selecting, modifying, validating, and confirming changes to news data, adhering to the specified use case while simulating real-world scenarios such as server interruptions.  

---

## 🚀 Key Features  

1. **User-Friendly Interface**  
   - Intuitive GUI built with Java Swing.  
   - Step-by-step navigation through the news modification process.  

2. **Complete News Management Workflow**  
   - **Step 1**: Activate editing functionality.  
   - **Step 2**: View all news items in a tabular list with previews.  
   - **Step 3**: Select a news item for editing.  
   - **Step 4**: Load and display existing news data in an editable form.  
   - **Step 5**: Modify news fields and submit changes.  
   - **Step 6**: Validate modified data; trigger error handling for invalid inputs.  
   - **Step 7**: Review changes in a confirmation screen and confirm/cancel operations.  
   - **Step 8**: Store updated news data (with simulated server interruptions).  

3. **Error Handling & Validation**  
   - Mandatory field validation.  
   - Date format validation (`YYYY-MM-DD`).  
   - Simulated server interruptions (ETOUR) during data storage.  

4. **Data Integrity**  
   - Immutable `NewsItem` objects ensure data consistency.  
   - Thread-safe operations using `SwingWorker` for UI responsiveness.  

5. **Mock Data Support**  
   - Pre-loaded sample news items for immediate testing.  

---

## 🛠️ Installation & Environment Setup  

### **Prerequisites**  

1. **Java Development Kit (JDK)**  
   - Version: **Java 11 or higher**  
   - Download: [Oracle JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)  
   - Verify installation:  
     ```bash  
     java -version  
     javac -version  
     ```  

2. **Integrated Development Environment (IDE)** *(Optional but Recommended)*  
   - Eclipse, IntelliJ IDEA, or NetBeans for easy code execution.  

### **Required Libraries**  

- **Java Standard Libraries**  
  - `javax.swing`  
  - `java.awt`  
  - `java.awt.event`  
  - `javax.swing.table`  
  - `java.util`  
  - `java.text`  

All required libraries are included in the Java Standard Edition (Java SE). No external dependencies are needed.  

---

## 📂 Project Structure  

```
ModifyNewsApp/
├── modifynewsapp.java        // Main application entry point
├── mainpanel.java            // Main control panel with CardLayout
├── newslistpanel.java        // Panel for news list selection
├── editnewspanel.java        // Panel for editing news data
├── confirmationpanel.java    // Panel for reviewing changes
├── agencynewsmodel.java      // Data model for news operations
├── newsitem.java             // News item value object
└── requirements.txt          // Environment dependencies (for reference)
```

---

## 🖥️ How to Use  

### **1. Compile and Run**  

#### **Via Command Line:**  
1. Compile all Java files:  
   ```bash  
   javac *.java  
   ```  
2. Run the application:  
   ```bash  
   java ModifyNewsApp  
   ```  

#### **Via IDE:**  
1. Import the project folder.  
2. Set `modifynewsapp.java` as the main class.  
3. Run the project.  

---

### **2. Step-by-Step Usage Guide**  

#### **Step 1: Launch the Application**  
- A window titled **"News Modification System - Agency Operator"** will open.  

#### **Step 2: View News List**  
- The initial screen displays a table of news items with columns:  
  - **ID**, **Title**, **Author**, **Date**, **Content Preview**  
- Select a news row by clicking it.  

#### **Step 3: Edit Selected News**  
- Click the **"Edit Selected News"** button.  
- The application navigates to the editing form.  

#### **Step 4: Modify News Data**  
- Edit the following fields (all required):  
  - **Title**: News headline.  
  - **Author**: Author name.  
  - **Date**: Format `YYYY-MM-DD` (e.g., `2023-10-25`).  
  - **Content**: Full news content.  
- Click **"Submit Changes"** to proceed.  

#### **Step 5: Validation & Error Handling**  
- If any field is empty or the date is invalid, an error dialog appears.  
- Correct the errors and resubmit.  

#### **Step 6: Confirm Changes**  
- A confirmation screen displays the updated news details for review.  
- Verify all changes carefully.  

#### **Step 7: Finalize or Cancel**  
- Click **"Confirm Changes"** to save modifications.  
- Or click **"Cancel"** to return to the news list without saving.  

#### **Step 8: Confirmation & Simulated Outcomes**  
- **Success**: A success message appears; news list updates automatically.  
- **Server Interruption (ETOUR)**: An error dialog notifies of connection failure; changes are not saved.  

---

## 🔧 Advanced Configuration  

### **Customizing Sample Data**  
To modify pre-loaded news items, edit the `loadSampleData()` method in `agencynewsmodel.java`.  

### **Adjusting Simulation Behaviors**  
- **Server Interruption Rate**: Adjust randomization in `updateNews()` (`random.nextDouble() < 0.1` → 10% chance).  
- **Simulated Delay**: Modify `Thread.sleep()` duration for persistence delays.  

---

## ⚠️ Limitations & Known Issues  

1. **Data Persistence**  
   - News data is stored in-memory; changes are lost after application closure.  

2. **Single-User Limitation**  
   - No concurrent user support; designed for single Agency Operator use.  

3. **Simulation Constraints**  
   - ETOUR interruptions are simulated; no actual network calls occur.  

4. **Thread Blocking**  
   - Persistence simulation uses `Thread.sleep()`; avoid calling on the Event Dispatch Thread (EDT).  

---

## 🧪 Testing  

### **Valid Test Cases:**  
1. Edit all fields with valid data → success.  
2. Cancel at confirmation → no changes saved.  

### **Error Test Cases:**  
1. Submit with empty fields → error dialog.  
2. Submit with invalid date format → error dialog.  

### **Edge Cases Simulated:**  
- Server interruption (ETOUR) on update → error message.  

---

## 📜 License & Support  

This software is provided as-is for demonstration purposes. No formal support is offered.  

---

## 📚 Additional Resources  

- [Java Swing Tutorial](https://docs.oracle.com/javase/tutorial/uiswing/)  
- [Java Best Pract](https://www.oracle.com/java/technologies/javase/se-code-conventions.html)  
- [Use Case Documentation Standards](https://www.uml-diagrams.org/use-case-diagrams.html)  

---

For any queries, refer to the inline code comments or contact your development team. Enjoy using the ModifyNews application! 🚀