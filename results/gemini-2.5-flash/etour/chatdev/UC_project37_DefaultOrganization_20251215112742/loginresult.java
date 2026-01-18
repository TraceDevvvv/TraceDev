/*
Represents the possible outcomes of a login attempt.
*/
package com.chatdev.session;
/**
 * Represents the possible outcomes of a login attempt.
 * This enum provides more specific feedback than a simple boolean.
 */
public enum LoginResult {
    SUCCESS,
    INVALID_CREDENTIALS,
    ALREADY_LOGGED_IN
}