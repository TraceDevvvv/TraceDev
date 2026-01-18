package com.example.application;

import com.example.application.command.Command;
import com.example.application.command.RemoveRoleCommand;
import com.example.domain.User;
import com.example.domain.Role;
import com.example.ports.UserRepository;
import com.example.ports.RoleRepository;

/**
 * Use case for removing a role from a user.
 */
public class RemoveRoleUseCase implements RoleManagementUseCase {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public RemoveRoleUseCase(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void execute(Command command) {
        if (!(command instanceof RemoveRoleCommand)) {
            throw new IllegalArgumentException("Command must be RemoveRoleCommand");
        }
        RemoveRoleCommand removeCommand = (RemoveRoleCommand) command;
        String userId = removeCommand.getUserId();
        String roleId = removeCommand.getRoleId();

        User user = userRepository.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found: " + userId);
        }

        Role role = roleRepository.findById(roleId);
        if (role == null) {
            throw new RuntimeException("Role not found: " + roleId);
        }

        user.removeRole(role);
        userRepository.save(user);
    }

    @Override
    public void rollback() {
        // In a real implementation, this would revert the last removal.
        // For simplicity, we just log the rollback.
        System.out.println("Rolling back last role removal.");
    }
}