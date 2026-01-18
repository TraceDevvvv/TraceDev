package com.example.application;

import com.example.application.command.AssignRoleCommand;
import com.example.application.command.Command;
import com.example.domain.User;
import com.example.domain.Role;
import com.example.ports.UserRepository;
import com.example.ports.RoleRepository;

/**
 * Use case for assigning a role to a user.
 */
public class AssignRoleUseCase implements RoleManagementUseCase {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public AssignRoleUseCase(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void execute(Command command) {
        if (!(command instanceof AssignRoleCommand)) {
            throw new IllegalArgumentException("Command must be AssignRoleCommand");
        }
        AssignRoleCommand assignCommand = (AssignRoleCommand) command;
        String userId = assignCommand.getUserId();
        String roleId = assignCommand.getRoleId();

        User user = userRepository.findById(userId);
        if (user == null) {
            throw new RuntimeException("User not found: " + userId);
        }

        Role role = roleRepository.findById(roleId);
        if (role == null) {
            throw new RuntimeException("Role not found: " + roleId);
        }

        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public void rollback() {
        // In a real implementation, this would revert the last assignment.
        // For simplicity, we just log the rollback.
        System.out.println("Rolling back last role assignment.");
    }
}