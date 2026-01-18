package com.example.session;

import com.example.actor.Administrator;
import java.time.LocalDateTime;

/**
 * Represents a session for an administrator.
 */
public class Session {
    private String id;
    private Administrator administrator;
    private LocalDateTime startTime;
    private String status;

    public Session() {}

    public Session(String id, Administrator administrator, LocalDateTime startTime, String status) {
        this.id = id;
        this.administrator = administrator;
        this.startTime = startTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Interrupts the session (e.g., connection to SMOS server).
     */
    public void interrupt() {
        this.status = "INTERRUPTED";
        System.out.println("Session interrupted.");
    }
}