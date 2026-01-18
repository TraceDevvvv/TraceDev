'''
Simulates sending notifications to parents.
In a real application, this would integrate with an email service, SMS gateway, or an in-app notification system.
'''
/**
 * Service responsible for simulating sending notifications.
 * For this example, it prints messages to the console.
 */
class NotificationService {
    /**
     * Simulates sending a notification to a specific recipient.
     *
     * @param recipientInfo The contact information of the recipient (e.g., email, phone number).
     * @param message       The content of the notification.
     * @return true if the notification was "sent" successfully, false otherwise.
     */
    public boolean sendNotification(String recipientInfo, String message) {
        System.out.println("--- Notification Service ---");
        System.out.println("Sending notification to: " + recipientInfo);
        System.out.println("Message: " + message);
        System.out.println("----------------------------");
        // Simulate potential failure
        if (recipientInfo == null || recipientInfo.isEmpty()) {
            System.err.println("Notification failed: Recipient info is empty.");
            return false;
        }
        return true; // Assume success for demonstration
    }
}