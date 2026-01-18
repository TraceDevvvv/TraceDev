```
# Banner Management System

Manage and display promotional banners for your restaurant with ease.

## 🤔 What is this?

The Banner Management System (codenamed "INSERISCIBANNER") is a standalone Java application designed for `Point Of Restaurant Operators` to efficiently manage promotional banners associated with their establishment. This system streamlines the process of adding new banners, ensuring they meet specific requirements and managing the total number of active banners.

**Key Features:**

*   **Banner Insertion**: Allows operators to select and insert new image banners.
*   **Image Validation**: Automatically checks selected images for validity (file type, dimensions, file size, corruption) before insertion.
*   **Maximum Banner Limit**: Enforces a configurable maximum number of banners a restaurant can display, preventing overload.
*   **User Confirmation**: Prompts the operator for confirmation before finalizing a banner insertion.
*   **Real-time Updates**: Displays a list of currently active banners for the restaurant.
*   **Error Handling**: Provides clear notifications for various scenarios, including invalid images, reaching banner limits, user cancellations, and simulated connection issues.

This application simplifies the task of keeping restaurant promotions fresh and visually appealing by providing a structured and validated banner management workflow.

## 📖 How to Get Started

This section will guide you through setting up the environment and running the Banner Management System.

### Prerequisites

To run this Java application, you need to have the Java Development Kit (JDK) installed on your system.

*   **Java Development Kit (JDK)**: Version 11 or higher is recommended.

    You can download the JDK from Oracle's website or use OpenJDK distributions like AdoptOpenJDK/Eclipse Temurin.
    
    To check if Java is installed and configured correctly, open a terminal or command prompt and type:
    ```bash
    java -version
    javac -version
    ```
    If these commands return version information, you are all set.

### Installation

No complex installation steps are required beyond having the JDK ready. Simply download or clone the project source code.

1.  **Download the source code**: Ensure you have all the `.java` files: `MainApplication.java`, `Banner.java`, `Restaurant.java`, `BannerService.java`, `ImageSelectionDialog.java`, `RestaurantOperatorGUI.java`.

### Compilation

Navigate to the directory containing all the `.java` files in your terminal or command prompt and compile them using `javac`:

```bash
# Navigate to your project directory, e.g.,
# cd path/to/your/project
javac *.java
```

This command will compile all Java source files and create corresponding `.class` files in the same directory.

### Running the Application

After successful compilation, you can run the application by executing the `MainApplication` class:

```bash
java MainApplication
```

This will launch the `Restaurant Banner Management` graphical user interface.

## 🚀 How to Use the Application

Once launched, the application presents a GUI for the `Restaurant Operator`. Follow these steps to manage banners:

1.  **Welcome Screen**: Upon launching, you will see the main window titled "Restaurant Banner Management". It displays a welcome message indicating that you are an authenticated operator and lists any existing banners for "My Awesome Restaurant". Initially, there will be no banners.

2.  **Insert a New Banner**:
    *   Click the **"1. Insert New Banner"** button.
    *   A new dialog window titled "Select Banner Image" will appear. This is your image selection form.

3.  **Select an Image**:
    *   In the "Select Banner Image" dialog, navigate through your file system to find an image file you wish to use as a banner.
    *   The file chooser is configured to filter for common image formats (`jpg`, `jpeg`, `png`, `gif`).
    *   As you select files, a "Image Preview" panel on the right will attempt to display a small preview of the image.
    *   Click **"Open"** to select the image and proceed, or **"Cancel"** to abort the selection.

4.  **Confirm Insertion**:
    *   After selecting an image, the system will perform preliminary checks (simulated).
    *   A confirmation dialog will pop up asking: "Are you sure you want to insert this banner?\nImage: [Your Selected Image Path]".
    *   Click **"Yes"** to confirm the insertion or **"No"** to cancel.

5.  **Handling Outcomes**:
    *   **Success**: If the image is valid, the maximum banner limit is not reached, and you confirm the operation, a "Successfully inserted new banner!" message will appear. The banner list in the main window will update to show the newly added banner.
    *   **Cancellation**: If you cancel the image selection or the confirmation step, a "Banner insertion cancelled by user." message will be displayed, and no banner will be added.
    *   **Invalid Image**: If the selected image does not meet the specified characteristics (e.g., non-existent, invalid type, corrupted, improper dimensions, or too large), an "ERROR: Invalid image characteristics." message will pop up. This corresponds to the `Errored` use case.
    *   **Max Banners Exceeded**: If the restaurant has already reached its maximum allowed banners (default is 3), an "Error: Limit Exceeded" message will appear, and the banner will not be inserted.
    *   **Connection Error (Simulated)**: The system randomly simulates a `CONNECTION_ERROR` (ETOUR). If this occurs, an "ERROR: Connection interrupted (ETOUR)." message will be displayed, and you should try again.
    *   **Unknown Error**: For any other unexpected issues, an "An unexpected error occurred." message will appear.

6.  **View Current Banners**: The "Current Banners for My Awesome Restaurant" area in the main window will always display a list of all banners successfully associated with the restaurant, including their (shortened) ID and file path. It also indicates the current count versus the maximum allowed.

This system is designed to provide clear feedback and guide the operator through a regulated banner insertion process.

```