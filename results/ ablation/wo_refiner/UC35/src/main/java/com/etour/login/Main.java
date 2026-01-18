package com.etour.login;

import com.etour.login.controller.LoginController;
import com.etour.login.dto.LoginRequestDTO;
import com.etour.login.repository.UserRepository;
import com.etour.login.repository.UserRepositoryImpl;
import com.etour.login.security.BCryptPasswordEncoder;
import com.etour.login.security.PasswordEncoder;
import com.etour.login.service.LoginService;
import com.etour.login.service.LoginServiceImpl;
import com.etour.login.ui.LoginForm;

/**
 * Main application class to demonstrate the login flow.
 * This simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== ETOUR Login System Demo ===\n");
        
        // 1. Setup dependencies (IoC container would do this in a real app)
        UserRepository userRepository = new UserRepositoryImpl();
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        LoginService loginService = new LoginServiceImpl(userRepository, passwordEncoder);
        LoginController loginController = new LoginController(loginService);
        LoginForm loginForm = new LoginForm(loginController);
        
        // 2. Simulate the sequence diagram flow
        
        // Actor: Registered User activates login feature
        System.out.println("1. User activates login feature");
        loginForm.activateLoginFeature();
        
        // User fills the form (simulated)
        System.out.println("\n2. User fills and submits form with username: john_doe, password: password123");
        loginForm.fillAndSubmitForm("john_doe", "password123"); // Password ends with "123" to match our dummy encoder
        
        // 3. Demonstrate error flow with invalid credentials
        System.out.println("\n\n=== Testing Error Flow ===");
        LoginForm loginForm2 = new LoginForm(loginController);
        loginForm2.displayLoginForm();
        loginForm2.setUsernameField("john_doe");
        loginForm2.setPasswordField("wrongpassword"); // Won't match
        System.out.println("\n3. User submits with wrong password");
        LoginRequestDTO request2 = loginForm2.createLoginRequest();
        loginForm2.submitCredentials(request2);
        
        // 4. Demonstrate user not found flow
        System.out.println("\n\n=== Testing User Not Found ===");
        LoginForm loginForm3 = new LoginForm(loginController);
        loginForm3.displayLoginForm();
        loginForm3.setUsernameField("nonexistent");
        loginForm3.setPasswordField("password123");
        System.out.println("\n4. User submits with non-existent username");
        LoginRequestDTO request3 = loginForm3.createLoginRequest();
        loginForm3.submitCredentials(request3);
        
        // 5. Demonstrate invalid data flow
        System.out.println("\n\n=== Testing Invalid Data ===");
        LoginForm loginForm4 = new LoginForm(loginController);
        loginForm4.displayLoginForm();
        loginForm4.setUsernameField("");
        loginForm4.setPasswordField("");
        System.out.println("\n5. User submits with empty credentials");
        LoginRequestDTO request4 = loginForm4.createLoginRequest();
        loginForm4.submitCredentials(request4);
        
        // 6. Demonstrate cancel flow
        System.out.println("\n\n=== Testing Cancel Flow ===");
        LoginForm loginForm5 = new LoginForm(loginController);
        loginForm5.displayLoginForm();
        System.out.println("\n6. User cancels the login form");
        loginForm5.cancel();
        
        System.out.println("\n=== Demo Complete ===");
    }
}