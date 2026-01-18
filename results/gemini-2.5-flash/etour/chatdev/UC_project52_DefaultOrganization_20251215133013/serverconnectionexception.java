'''
Custom exception to simulate an interruption in connection to the ETOUR server.
This helps handle a specific exit condition mentioned in the use case.
'''
package com.chatdev.tourism;
public class ServerConnectionException extends Exception {
    /**
     * Constructs a new ServerConnectionException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ServerConnectionException(String message) {
        super(message);
    }
}