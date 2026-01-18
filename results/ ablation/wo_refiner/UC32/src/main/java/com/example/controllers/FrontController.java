package com.example.controllers;

/**
 * Abstract base class for front controllers.
 */
public abstract class FrontController {
    
    /**
     * Handles incoming requests.
     * @param request the request string
     */
    public abstract void handleRequest(String request);
}