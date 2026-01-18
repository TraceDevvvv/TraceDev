'''
Custom exception to simulate a failure in connecting to a backend system.
This is used to model the "Interruption of the connection to the server" exit condition.
'''
package com.chatdev.system;
public class ConnectionFailedException extends RuntimeException {
    public ConnectionFailedException(String message) {
        super(message);
    }
    public ConnectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}