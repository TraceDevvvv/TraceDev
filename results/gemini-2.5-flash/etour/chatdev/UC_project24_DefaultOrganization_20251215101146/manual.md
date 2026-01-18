```markdown
# Agency Operator Feedback Viewer Manual

## Introduction

The **Agency Operator Feedback Viewer** is a desktop application designed for agency operators to easily view feedback submitted for various sites. This tool streamlines the process of accessing specific site feedback, allowing operators to quickly understand public or internal sentiment regarding different locations or projects.

**Key Features:**
*   **Site Listing**: Displays a comprehensive list of available sites.
*   **Site Selection**: Allows operators to select a specific site from the list.
*   **Feedback Display**: Presents all associated feedback for the chosen site in a clear, readable format.
*   **Error Handling**: Provides informative messages for scenarios like network connection interruptions or when no feedback is available for a selected site.

## System Requirements

To run this application, you will need:
*   **Java Development Kit (JDK)**: Version 11 or higher. This is necessary for compiling and running Java applications. You can download it from the official Oracle website or adopt an OpenJDK distribution like Adoptium (Eclipse Temurin).

## Installation

Assuming you have access to the source code files (`site.java`, `feedback.java`, `siteservice.java`, `feedbackservice.java`, `agencyoperatorgui.java`, `main.java`, `connectionexception.java`), follow these steps:

1.  **Organize Files**: Create a directory structure that matches the package declarations in the Java files. For example, if your main project directory is `feedback-app`, you would create:
    ```
    feedback-app/
    └── src/
        └── com/
            └── chatdev/
                ├── app/
                │   └── Main.java
                ├── exception/
                │   └── ConnectionException.java
                ├── gui/
                │   └── AgencyOperatorGUI.java
                ├── model/
                │   ├── Feedback.java
                │   └── Site.java
                └── service/
                    ├── FeedbackService.java
                    └── SiteService.java
    ```
    Place each `.java` file into its corresponding directory.

2.  **Compile**: Open a terminal or command prompt, navigate to the `src` directory within your `feedback-app` project (e.g., `cd feedback-app/src`), and compile the Java source files using the Java compiler:
    ```bash
    javac com/chatdev/app/Main.java com/chatdev/exception/ConnectionException.java com/chatdev/gui/AgencyOperatorGUI.java com/chatdev/model/Feedback.java com/chatdev/model/Site.java com/chatdev/service/FeedbackService.java com/chatdev/service/SiteService.java
    ```
    Alternatively, to compile all `.java` files recursively from the `src` directory:
    ```bash
    javac com/chatdev/**/*.java
    ```
    This will create `.class` files in the same directory structure.

## How to Run

After successful compilation, you can run the application from the `src` directory:

```bash
java com.chatdev.app.Main
```

A graphical user interface (GUI) window titled "Agency Operator - View Site Feedback" should appear.

## How to Use the Application

Once the application is running, follow these steps to view site feedback:

1.  **Launch**: The application window will display two main sections: "Select a Site" on the left and "Site Feedback" on the right.
2.  **View Site List**: The "Select a Site" panel will automatically populate with a list of available sites (e.g., "ChatDev Office HQ", "Remote Work Hub A"). This list is analogous to the result of the `RicercaSito` use case.
3.  **Select a Site**: Click on any site name in the list to select it. For example, click on "ChatDev Office HQ".
4.  **View Feedback**: Click the "View Feedback" button located below the site list.
5.  **Observe Feedback**: The "Site Feedback" area on the right will then display all feedback associated with the selected site. Each feedback entry will show its date, author, rating, and comment.

### Handling Edge Cases and Messages:

*   **No Site Selected**: If you click "View Feedback" without selecting a site, a message "Please select a site from the list." will appear in the status bar at the bottom.
*   **No Feedback for Site**: If a site has no feedback (e.g., "Development Lab Z" in the sample data), the feedback area will display "No feedback available for [Site Name]."
*   **Connection Interruption**: The application simulates potential connection issues to the server. If this occurs while loading sites or feedback, an "Error" message box will pop up, and the status bar/feedback area will display a connection error message (e.g., "Failed to load sites: Simulated connection interruption..."). In such cases, you can try again by selecting a site and clicking "View Feedback," or if it's during initial load, you may need to restart the application.

This concludes the user manual for the Agency Operator Feedback Viewer. Enjoy using the application to manage and review site feedback efficiently!
```