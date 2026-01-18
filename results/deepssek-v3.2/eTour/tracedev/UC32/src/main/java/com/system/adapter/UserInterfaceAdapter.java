package com.system.adapter;

import com.system.application.NotificationContext;
import com.system.ports.NotificationOutputPort;

/**
 * Adapter implementing notification output port for UI display.
 */
public class UserInterfaceAdapter implements NotificationOutputPort {
    
    @Override
    public void notify(NotificationContext context) {
        // In a real system, this would interface with UI framework
        System.out.println("[UI Notification] Type: " + context.getType() + 
                         ", Message: " + context.getMessage());
    }
}