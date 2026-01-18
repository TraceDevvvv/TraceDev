'''
ServerConnectionException.java
Custom exception for handling ETOUR server connection interruptions.
This allows for specific error handling related to server connectivity issues.
'''
package culturalheritage.search;
public class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}