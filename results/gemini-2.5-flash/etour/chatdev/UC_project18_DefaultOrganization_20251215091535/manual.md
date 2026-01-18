```markdown
# Banner Management System - Check Limit

This document serves as a user manual for the "Banner Management System - Check Limit" application. This Java-based application helps agencies verify if a refreshment point has reached its maximum allowed number of banners, preventing over-placement of advertisements.

## 🤔 What Does This Software Do?

The "Banner Management System - Check Limit" is a simple, graphical user interface (GUI) application designed to assist in managing banner placements. Its primary function is to:

*   **Load Refreshment Point Data**: Simulate loading information about a specific refreshment point, including its maximum allowed banners and the current number of banners already placed. Although it uses mock data for demonstration, in a real-world scenario, this data would come from a backend system.
*   **Check Banner Limit**: Determine if a new banner can be added to a refreshment point without exceeding its predefined limit.
*   **Provide Instant Notification**: If the limit is reached, the system displays a clear notification to the user, preventing further action that would violate the limit.
*   **User-Friendly Interface**: Offers an intuitive window where users can input or review refreshment point details and initiate the check with a single click.

This application directly addresses the `CONTROLLANUMEROBANNER` use case, ensuring that operational conditions regarding banner placement limits are met.

## ⚙️ System Requirements

To run this application, you will need:

*   **Java Development Kit (JDK)**: Version 8 or higher. The application is built using standard Java libraries and Swing for its GUI.
    *   You can download the latest JDK from the official Oracle website or adoptium.net (for OpenJDK).
*   **Basic understanding of command-line operations** (for compiling and running Java applications).

## 🚀 Installation & Setup

There is no complex installation process. You just need to compile and run the provided Java source files.

1.  **Save the Source Files**:
    Save the following three Java files into a directory named `com/chatdev/bannerapp` within your project root folder. Ensure the package declarations at the top of each file (`package com.chatdev.bannerapp;`) match this directory structure.

    *   `RefreshmentPointData.java`
    *   `BannerCheckService.java`
    *   `BannerManagementApp.java`

    Your directory structure should look like this:

    ```
    your_project_root/
    └── com/
        └── chatdev/
            └── bannerapp/
                ├── RefreshmentPointData.java
                ├── BannerCheckService.java
                └── BannerManagementApp.java
    ```

2.  **Open a Terminal or Command Prompt**:
    Navigate to your `your_project_root` directory using the `cd` command.

3.  **Compile the Java Files**:
    Use the Java compiler (`javac`) to compile all three `.java` files.

    ```bash
    javac com/chatdev/bannerapp/*.java
    ```

    If successful, this command will create corresponding `.class` files in the same directory.

## 🎮 How to Use

Follow these steps to run and interact with the "Banner Management System - Check Limit" application:

1.  **Run the Application**:
    From your `your_project_root` directory, execute the main application class using the Java Virtual Machine (`java`).

    ```bash
    java com.chatdev.bannerapp.BannerManagementApp
    ```

2.  **Application Window Appears**:
    A GUI window titled "Banner Management System - Check Limit" will appear.
    Upon startup, the application attempts to load data for a default refreshment point (e.g., "Convention Center Main Hall") from its internal mock data source and pre-populates the input fields.

3.  **Understand the Interface**:

    *   **Refreshment Point Name**: This field displays the name of the refreshment point. It's pre-filled with a default value.
    *   **Max Banners Allowed**: This field shows the maximum number of banners permitted at this point.
    *   **Current Banners**: This field shows how many banners are currently placed at this point.
    *   **Check if New Banner Can Be Added Button**: Click this button to perform the check.
    *   **Status Bar**: Located at the bottom, this area displays messages about the operation's result or any errors.

4.  **Perform a Check**:

    *   **Scenario 1: Adding a banner is possible**
        1.  Ensure the "Convention Center Main Hall" is loaded (Max Banners: 5, Current Banners: 3).
        2.  Click the "**Check if New Banner Can Be Added**" button.
        3.  The status bar will update to: `Result: A new banner CAN be added to 'Convention Center Main Hall'.` (in green text). No pop-up notification will appear.

    *   **Scenario 2: Maximum limit reached or exceeded**
        1.  You can manually edit the text fields. For example, change "Max Banners Allowed" to `2` and "Current Banners" to `2` (or "Max Banners Allowed" to `5` and "Current Banners" to `5`).
        2.  Click the "**Check if New Banner Can Be Added**" button.
        3.  A pop-up `JOptionPane` dialog titled "Banner Limit Exceeded" will appear with a message like: `Notification: The refreshment point 'Convention Center Main Hall' has reached its maximum allowed number of banners (2). Current banners: 2. No more banners can be added.`
        4.  Click "OK" on the pop-up to acknowledge the notification.
        5.  The status bar will update to: `Result: A new banner CANNOT be added to 'Convention Center Main Hall'. Limit reached!` (in red text).

    *   **Scenario 3: Invalid Input (Error Handling)**
        1.  Try entering non-numeric text (e.g., "abc") into "Max Banners Allowed" or "Current Banners".
        2.  Click the "**Check if New Banner Can Be Added**" button.
        3.  An "Input Error" pop-up will appear, guiding you to correct the input. The status bar will also show an error message.
        4.  Try entering "Current Banners" as `5` and "Max Banners Allowed" as `3`. This is an illogical state before attempting to add. The system will alert you with an "Input Error" pop-up.

5.  **Exit the Application**:
    Close the application window by clicking the "X" button in the top-right corner (or equivalent for your operating system).

## 📊 Understanding the Flow

When you click the "Check" button:

1.  The application reads the values from the "Refreshment Point Name", "Max Banners Allowed", and "Current Banners" fields.
2.  It performs basic validation on these inputs (e.g., ensuring numeric values, non-negative numbers, current banners not exceeding max banners initially).
3.  A `RefreshmentPointData` object is created internally using these values.
4.  The `BannerCheckService` is invoked to verify if `currentBanners < maxBanners`.
5.  **If `currentBanners < maxBanners`**: The check passes, and the status bar indicates that a new banner **can** be added.
6.  **If `currentBanners >= maxBanners`**: The check fails, indicating the limit is reached.
    *   A `JOptionPane` pop-up displays a notification message, fulfilling Use Case Step 1's requirement to end the operation input and display notification.
    *   Clicking "OK" on the pop-up confirms reading (Use Case Step 2).
    *   The status bar indicates that a new banner **cannot** be added.
7.  The system then "recovers the previous state" (Use Case Step 3) by simply returning control to the user interaction, keeping the GUI active and allowing further checks or modifications.
8.  The application remains responsive, allowing further user interaction, fulfilling the "system returns control to the user interaction" exit condition.

Happy banner managing!
```