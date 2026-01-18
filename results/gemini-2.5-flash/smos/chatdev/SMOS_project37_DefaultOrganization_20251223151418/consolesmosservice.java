'''
A concrete implementation of SmosService that simulates interrupting the connection
by printing a message to the console.
This is useful for development and testing without needing a real SMOS server.
'''
public class ConsoleSmosService implements SmosService {
    /**
     * Simulates interrupting the connection to the SMOS server.
     * In a real application, this would involve closing connections, logging out,
     * or releasing resources related to the SMOS server.
     */
    @Override
    public void interruptConnection() {
        System.out.println("--- SIMULATING SMOS SERVER CONNECTION INTERRUPTION ---");
        System.out.println("SMOS server connection has been gracefully interrupted.");
        System.out.println("----------------------------------------------------\n");
    }
}