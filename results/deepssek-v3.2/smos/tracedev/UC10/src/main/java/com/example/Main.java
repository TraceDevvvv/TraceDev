
package com.example;

import com.example.domain.User;
import com.example.domain.Role;
import com.example.domain.Permission;
import com.example.adapters.controllers.RoleManagementController;
import com.example.adapters.requests.RoleUpdateRequest;
import com.example.adapters.responses.Confirmation;
import com.example.application.AssignRoleUseCase;
import com.example.application.RemoveRoleUseCase;
import com.example.infrastructure.persistence.JpaUserRepository;
import com.example.infrastructure.persistence.JpaRoleRepository;
import com.example.presentation.RoleManagementForm;
import com.example.presentation.handlers.ConnectionInterruptionHandler;
import com.example.ports.UserRepository;
import com.example.ports.RoleRepository;
import java.util.Arrays;

/**
 * Main class to simulate the role management scenario.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Role Management Simulation ===");

        // Setup repositories
        UserRepository userRepo = new JpaUserRepository();
        RoleRepository roleRepo = new JpaRoleRepository();

        // Create some roles
        Role adminRole = new Role("role1", "Admin");
        adminRole.addPermission(Permission.MANAGE_USERS);
        ((JpaRoleRepository) roleRepo).addRole(adminRole);

        Role editorRole = new Role("role2", "Editor");
        editorRole.addPermission(Permission.WRITE_DATA);
        ((JpaRoleRepository) roleRepo).addRole(editorRole);

        // Create a user
        User user = new User("user1", "John Doe");
        ((JpaUserRepository) userRepo).addUser(user);

        // Setup use cases
        AssignRoleUseCase assignUseCase = new AssignRoleUseCase(userRepo, roleRepo);
        RemoveRoleUseCase removeUseCase = new RemoveRoleUseCase(userRepo, roleRepo);

        // Setup controller
        RoleManagementController controller = new RoleManagementController(assignUseCase, removeUseCase);

        // Setup interruption handler
        ConnectionInterruptionHandler interruptionHandler = new ConnectionInterruptionHandler();

        // Setup form
        RoleManagementForm form = new RoleManagementForm(controller, interruptionHandler);
        form.createRoleManagementForm();
        form.display(user);

        // Simulate role selection
        System.out.println("\n--- Selecting roles ---");
        form.selectRole("role1", "assign");
        form.selectRole("role2", "assign");

        // Simulate sending request
        System.out.println("\n--- Sending request ---");
        form.clickSendButton();

        // Simulate interruption scenario
        System.out.println("\n--- Interruption scenario ---");
        form.cancelOperation();

        System.out.println("\n=== Simulation complete ===");
    }
}
