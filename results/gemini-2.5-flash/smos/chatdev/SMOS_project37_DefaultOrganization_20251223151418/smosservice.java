'''
Interface for interacting with the hypothetical SMOS server.
This defines the contract for interrupting the connection.
'''
public interface SmosService {
    /**
     * Simulates or performs the action of interrupting the connection to the SMOS server.
     */
    void interruptConnection();
}