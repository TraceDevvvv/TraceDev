package com.example.adapter;

/**
 * Port interface for ETOUR server communication.
 */
public interface ETOURServerAdapter {
    boolean sendActivationNotification(String conventionId);
    boolean isConnected();
}