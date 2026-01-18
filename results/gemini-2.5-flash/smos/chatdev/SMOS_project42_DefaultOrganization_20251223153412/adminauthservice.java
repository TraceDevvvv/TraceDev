/*
This service class simulates the authentication of an administrator.
It maintains a simple boolean flag to indicate if an administrator is currently logged in.
*/
package com.chatdev.eliminatejustification.serv;
public class AdminAuthService {
    private boolean loggedIn;
    /**
     * Constructs a new AdminAuthService.
     * Initially, no administrator is logged in.
     */
    public AdminAuthService() {
        this.loggedIn = false;
    }
    /**
     * Simulates an administrator logging into the system.
     */
    public void login() {
        this.loggedIn = true;
        System.out.println("DEBUG: Administrator logged in.");
    }
    /**
     * Simulates an administrator logging out of the system.
     */
    public void logout() {
        this.loggedIn = false;
        System.out.println("DEBUG: Administrator logged out.");
    }
    /**
     * Checks if an administrator is currently logged in.
     * @return true if an administrator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}