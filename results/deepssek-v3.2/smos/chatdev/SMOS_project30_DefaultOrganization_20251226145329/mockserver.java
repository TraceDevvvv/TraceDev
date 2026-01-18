/**
 * MockServer.java
 *
 * This class simulates server communication for sending delay data and notifications.
 * It includes methods to send data to the server and send notifications to parents,
 * with simulation of possible server interruptions.
 */
public class MockServer {
    private boolean serverConnected = true;
    private int interruptionCounter = 0;
    // Simulates sending data to the server; returns true on success, false on failure
    public boolean sendData(StudentDelay record) {
        // Simulate occasional server interruption (e.g., every 3rd request fails)
        interruptionCounter++;
        if (interruptionCounter % 3 == 0) {
            serverConnected = false;
            System.err.println("Server connection interrupted (SMOS server) for record: " + record);
            return false;
        } else {
            serverConnected = true;
            // Simulate network delay
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("Data sent to server: " + record);
            return true;
        }
    }
    // Simulates sending notifications to parents
    public void sendNotificationToParents(StudentDelay record) {
        if (serverConnected) {
            // Simulate notification process
            System.out.println("Notification sent to parents of student: " + record.getStudentName());
        } else {
            System.err.println("Cannot send notification: server disconnected for " + record.getStudentName());
        }
    }
    // Method to check server connection status
    public boolean isServerConnected() {
        return serverConnected;
    }
}