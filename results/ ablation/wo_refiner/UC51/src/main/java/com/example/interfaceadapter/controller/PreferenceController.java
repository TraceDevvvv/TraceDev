package com.example.interfaceadapter.controller;

import com.example.usecase.command.InsertSiteCommand;
import com.example.usecase.interfaces.IInsertSiteCommand;
import com.example.usecase.interfaces.INotificationService;
import com.example.usecase.interfaces.IServerConnectionService;

/**
 * Interface Adapter: Controller for handling preference operations
 */
public class PreferenceController {
    private IInsertSiteCommand insertHandler;
    private INotificationService notificationService;
    private IServerConnectionService serverConnectionService;

    public PreferenceController(IInsertSiteCommand handler, INotificationService notifier, IServerConnectionService connService) {
        this.insertHandler = handler;
        this.notificationService = notifier;
        this.serverConnectionService = connService;
    }

    /**
     * Activates site insertion feature (called from UI)
     * Implements sequence diagram flow
     */
    public String activateInsertion(String siteId, String touristId) {
        // Check connection first (sequence diagram step)
        boolean connectionOk = serverConnectionService.checkConnection();
        
        if (!connectionOk) {
            return "Error: Connection to ETOUR server interrupted";
        }
        
        try {
            // Create command and execute (requirement 3)
            InsertSiteCommand command = new InsertSiteCommand(touristId, siteId);
            insertHandler.execute(command);
            
            // Notify user (requirement 12 usability)
            notificationService.notifyInsertion("Site " + siteId);
            
            return "Success: Site added to bookmarks";
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}