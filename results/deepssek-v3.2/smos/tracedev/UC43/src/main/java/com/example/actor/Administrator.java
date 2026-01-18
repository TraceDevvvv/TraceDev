package com.example.actor;

import com.example.application.DeleteNoteService;
import com.example.application.command.DeleteNoteCommand;

public class Administrator {
    private String adminId;
    private DeleteNoteService service;

    public Administrator(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setService(DeleteNoteService service) {
        this.service = service;
    }

    // Request deletion of a note via the DeleteNoteService
    public String requestNoteDeletion(DeleteNoteCommand command) {
        System.out.println("Administrator " + adminId + " requesting deletion of note " + command.getNoteId());
        if (service == null) {
            return "Service not set";
        }
        String result = service.executeDelete(command);
        System.out.println("Service returned: " + result);
        return result;
    }

    // Interrupt an ongoing operation
    public String interruptOperation() {
        System.out.println("Administrator " + adminId + " interrupting operation.");
        if (service == null) {
            return "Service not set";
        }
        String result = service.handleInterruption();
        System.out.println("Service returned: " + result);
        return result;
    }
}