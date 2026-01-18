package com.system.ports;

import com.system.application.NotificationContext;

/**
 * Output port for notifications.
 * Tagged with clear_actionable_notification to ensure quality.
 */
public interface NotificationOutputPort {
    void notify(NotificationContext context);
}