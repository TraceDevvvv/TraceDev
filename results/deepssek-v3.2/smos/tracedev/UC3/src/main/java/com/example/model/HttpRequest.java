package com.example.model;

import java.util.Map;

/**
 * Represents an HTTP request.
 */
public class HttpRequest {
    public String method;
    public String path;
    public Map<String, String> parameters;
    public Map<String, String> headers;

    /**
     * Gets a parameter value by name.
     * @param name the parameter name.
     * @return the parameter value, or null if not found.
     */
    public String getParameter(String name) {
        if (parameters == null) return null;
        return parameters.get(name);
    }
}