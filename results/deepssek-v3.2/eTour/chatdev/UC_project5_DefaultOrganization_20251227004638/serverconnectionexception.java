"""
Custom exception for ETOUR server connection errors.
Handles interruption scenarios specified in requirements.
"""
/**
 * Custom exception for ETOUR server connection errors.
 */
class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}