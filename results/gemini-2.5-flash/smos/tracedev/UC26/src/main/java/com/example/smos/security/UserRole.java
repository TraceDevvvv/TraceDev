package com.example.smos.security;

/**
 * Enumeration representing different roles a user can have in the system.
 * Corresponds to the 'UserRole' enum in the UML Class Diagram.
 */
public enum UserRole {
    Administrator, // Role with elevated privileges, e.g., for eliminating teachings.
    Guest,         // Limited access role.
    Editor,        // Role with content creation/modification rights.
    Viewer         // Role with read-only access.
}