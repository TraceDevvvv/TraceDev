package com.example.application.command;

/**
 * Command for assigning a role.
 */
public class AssignRoleCommand extends Command {
    public AssignRoleCommand(String userId, String roleId) {
        super(userId, roleId);
    }
}