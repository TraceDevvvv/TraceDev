package com.system.observer;

import com.system.application.NotificationContext;

/**
 * Observer interface for notification updates.
 */
public interface Observer {
    void update(NotificationContext context);
}