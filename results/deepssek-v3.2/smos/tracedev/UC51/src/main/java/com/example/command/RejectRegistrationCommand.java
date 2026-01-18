package com.example.command;

/**
 * Command class for rejecting a registration request.
 * Implements the Command Pattern for reliable, undoable execution.
 */
public class RejectRegistrationCommand {
    private String requestId;
    private String administratorId;
    private String rejectionReason;

    public RejectRegistrationCommand(String requestId, String administratorId) {
        this(requestId, administratorId, "No reason provided");
    }

    public RejectRegistrationCommand(String requestId, String administratorId, String rejectionReason) {
        this.requestId = requestId;
        this.administratorId = administratorId;
        this.rejectionReason = rejectionReason;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getAdministratorId() {
        return administratorId;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    /**
     * Executes the command.
     * In a full implementation, this might delegate to a handler.
     * Here it's left empty as the handler will perform the actual execution.
     */
    public void execute() {
        // Command execution is delegated to the handler.
    }
}