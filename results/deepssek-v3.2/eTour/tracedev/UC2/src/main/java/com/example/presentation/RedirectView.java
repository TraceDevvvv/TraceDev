package com.example.presentation;

/**
 * Simple representation of a redirect instruction.
 */
public class RedirectView {
    private String url;
    
    public RedirectView(String url) {
        this.url = url;
    }
    
    public String getUrl() {
        return url;
    }
}