package com.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.system.adapter.PasswordChangeAdapter;
import com.system.application.NotificationContext;
import com.system.dto.ChangeRequest;
import com.system.dto.ChangeResponse;
import com.system.observer.Observer;
import com.system.ports.PasswordChangeInputPort;

/**
 * Front controller handling requests and notifying observers.
 */
public class FrontController {
    private PasswordChangeInputPort inputPort;
    private List<Observer> observers;

    public FrontController(PasswordChangeInputPort inputPort) {
        this.inputPort = inputPort;
        this.observers = new ArrayList<>();
    }

    /**
     * Handles incoming requests based on action type.
     */
    public ChangeResponse handleRequest(String action, ChangeRequest request) {
        if ("PASSWORD_CONFIRM".equals(action)) {
            return handlePasswordChange(request);
        }
        return new ChangeResponse(null, "Unknown action");
    }

    public ChangeResponse handleRequest(String action, Map<String, String> data) {
        if ("PASSWORD_CONFIRM".equals(action)) {
            ChangeRequest request = mapToChangeRequest(data);
            return handlePasswordChange(request);
        }
        return new ChangeResponse(null, "Unknown action");
    }

    private ChangeResponse handlePasswordChange(ChangeRequest request) {
        // Delegate to adapter
        ChangeResponse response = inputPort.handlePasswordChange(request);
        
        // Notify observers with appropriate notification context
        NotificationContext context = new NotificationContext(
            response.message,
            response.result != null && response.result.name().equals("SUCCESS") 
                ? com.system.domain.NotificationType.SUCCESS 
                : com.system.domain.NotificationType.ERROR
        );
        notifyObservers(context);
        
        return response;
    }

    private ChangeRequest mapToChangeRequest(Map<String, String> data) {
        return new ChangeRequest(
            data.get("userId"),
            data.get("currentPassword"),
            data.get("newPassword"),
            data.get("confirmation")
        );
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers(NotificationContext context) {
        for (Observer observer : observers) {
            observer.update(context);
        }
    }
}