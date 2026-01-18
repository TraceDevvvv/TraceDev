```
# Point Of Restaurant Statistics Viewer

## 💡 Introduction

This application, named "VISUALIZZASTATISTICHEPERSONALI" (View Personal Statistics), is designed for a Point Of Restaurant Operator to easily access and view their personal restaurant statistics. It provides a simple graphical user interface (GUI) to display key performance indicators (KPIs) and operational metrics, allowing for quick insights into the restaurant's performance.

The software is implemented in Java using the Swing framework, simulating common application behaviors such as data loading delays and potential connection interruptions.

## 🚀 Main Functions

The primary function of this application is to present a comprehensive overview of a restaurant's operational statistics. Key features include:

*   **Display of Personal Statistics**: Shows various metrics such as:
    *   Total Orders
    *   Total Revenue
    *   Average Order Value
    *   Most Popular Item
    *   Number of Customers
    *   Average Rating
*   **Simulated Data Loading**: The application simulates a realistic data loading process, including a short delay to imitate network Latency or complex data processing.
*   **Connection Interruption Handling**: It includes a simulated scenario for server connection interruption, demonstrating how the system would notify the user of such an event.
*   **User Feedback**: Provides clear status messages (loading, success, error) to keep the operator informed about the process.

## 🛠️ Environment Setup

To run this application, you will need a Java Development Kit (JDK) installed on your system.

### Prerequisites

*   **Java Development Kit (JDK)**: Version 8 or higher is recommended.
    *   You can download the latest JDK from Oracle's official website or use an open-source distribution like OpenJDK.

### Installation Steps

1.  **Verify Java Installation**:
    Open a terminal or command prompt and type:
    ```bash
    java -version
    javac -version
    ```
    If Java is correctly installed, you should see version information for both `java` (the runtime) and `javac` (the compiler). If not, please install the JDK first.

2.  **No External Dependencies**:
    This application uses standard Java libraries (Swing, AWT) which are included with the JDK. No additional third-party libraries or frameworks need to be installed.

## 🏃 How to Run the Application

Follow these steps to compile and run the Point Of Restaurant Statistics Viewer:

1.  **Save the Code**:
    Save the provided Java code snippets into three separate files in the same directory:
    *   `StatisticsData.java`
    *   `StatisticsPanel.java`
    *   `RestaurantStatisticsApp.java`

2.  **Compile the Code**:
    Navigate to the directory where you saved the `.java` files using your terminal or command prompt. Then, compile the code using the Java compiler:
    ```bash
    javac *.java
    ```
    This command compiles all Java source files in the current directory. If there are no errors, it will generate `.class` files for each Java source file.

3.  **Run the Application**:
    After successful compilation, you can run the main application:
    ```bash
    java RestaurantStatisticsApp
    ```

## 🎮 How to Use

Upon running `java RestaurantStatisticsApp`, a new window titled "Point Of Restaurant Statistics" will appear.

### Initial State

*   The application window will display a button labeled **"View Personal Statistics"** at the top.
*   Below the button, you will see an area reserved for statistics, initially showing "N/A" for all values and a status message: "Click 'View Personal Statistics' to load data."

### Viewing Statistics

1.  **Click the Button**: Click on the **"View Personal Statistics"** button.
2.  **Loading Process**:
    *   The button will become disabled during the loading process.
    *   The status message will change to "Loading statistics..." and its color will turn orange.
    *   A slight delay (simulating network or processing time) will occur.
3.  **Statistics Display**:
    *   If data loading is successful, the statistics area will populate with sample data for total orders, revenue, popular items, etc.
    *   The status message will change to "Statistics loaded successfully." and turn a darker green.
    *   The button will re-enable.

### Simulated Connection Interruption

The application has a built-in random chance (approximately 20%) to simulate a "server connection interruption."

*   If this occurs during data loading, the statistics will not be displayed.
*   An error message like "Failed to load statistics: Simulated server connection interruption." will appear in red in the status area.
*   The button will re-enable, allowing you to try loading the statistics again.

### Exit Conditions

*   **Normal Exit**: Close the application window by clicking the 'X' button in the title bar.
*   **Interruption of Connection to Server**: As described above, the system displays an error message on the screen and allows the user to retry.

This application provides a clear demonstration of how a restaurant operator can interact with a system to view essential business statistics, including feedback on data availability and potential issues.
```