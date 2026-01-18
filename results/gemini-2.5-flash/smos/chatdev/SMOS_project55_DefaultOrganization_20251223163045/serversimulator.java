/*
Mocks a server interaction for sending delay data.
In a real application, this would handle network requests, database updates, etc.
For this simulation, it just prints the received data to the console.
*/
import java.util.List;
class ServerSimulator {
    /**
     * Simulates sending delay data for a list of students to a server.
     *
     * @param studentsWithDelays A list of Student objects that have recorded delays.
     *                           Only students where hasDelay() is true are typically sent.
     */
    public void sendDelayData(List<Student> studentsWithDelays) {
        System.out.println("--- Simulating sending data to server ---");
        if (studentsWithDelays.isEmpty()) {
            System.out.println("No delay data to send.");
        } else {
            System.out.println("Sending delay data for " + studentsWithDelays.size() + " student(s):");
            for (Student student : studentsWithDelays) {
                // In a real scenario, you might format this as JSON, XML, or database fields.
                System.out.println("  Student ID: " + student.getId() +
                                   ", Name: " + student.getName() +
                                   ", Delay: " + student.getDelayMinutes() + " minutes");
            }
            System.out.println("Data sent successfully.");
        }
        System.out.println("-----------------------------------------\n");
    }
    /**
     * Simulates an interruption of the server connection.
     * This method doesn't actualy disconnect anything but indicates the action to the console.
     */
    public void interruptConnection() {
        System.out.println("--- Connection to SMOS server interrupted. ---");
    }
}