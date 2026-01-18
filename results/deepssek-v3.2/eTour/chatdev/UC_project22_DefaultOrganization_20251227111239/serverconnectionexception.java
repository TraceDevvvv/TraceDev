'''
Custom exception for server connection interruptions
Specifically handles ETOUR server connection issues
'''
package com.chatdev.newsapp;
public class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}