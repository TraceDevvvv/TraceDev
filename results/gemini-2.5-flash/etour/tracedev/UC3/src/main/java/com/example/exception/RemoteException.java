package com.example.exception;

/**
 * Custom RemoteException class.
 * This is used as a placeholder for the generic RemoteException mentioned in the diagram,
 * to distinguish from java.rmi.RemoteException if needed, or can be replaced by it.
 * For this context, it will wrap RMI related errors in the Application Service layer.
 */
public class RemoteException extends Exception {
    private static final long serialVersionUID = 1L;

    public RemoteException(String message) {
        super(message);
    }

    public RemoteException(String message, Throwable cause) {
        super(message, cause);
    }
}