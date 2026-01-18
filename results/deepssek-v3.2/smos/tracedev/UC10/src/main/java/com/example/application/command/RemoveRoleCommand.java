package com.example.application.command;

/**
 * Command for removing a role.
 */
public class RemoveRoleCommand extends Command {
    public RemoveRoleCommand(String userId, String roleId) {
        super(userId, roleId);
    }
}