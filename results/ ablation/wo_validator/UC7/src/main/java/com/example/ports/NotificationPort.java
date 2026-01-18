package com.example.ports;

import com.example.domain.Convention;

/**
 * Port interface for sending notifications.
 */
public interface NotificationPort {
    void notifyActivation(Convention convention);
}