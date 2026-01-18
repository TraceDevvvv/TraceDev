package com.example.etour;

/**
 * Secure controller that coordinates the login workflow.
 * Implements the Use Case Controller pattern.
 */
public class LoginController {
    private UserService userService;
    private AuthService authService;
    private LoginErrorHandler errorHandler;

    public LoginController() {
        // Initialize dependencies; assumption: using concrete implementations.
        this.userService = new UserServiceImpl();
        this.authService = new JwtAuthService();
        this.errorHandler = new LoginErrorHandler();
    }

    /**
     * Handles a login request.
     * @param loginData the login data.
     * @return authentication result.
     */
    public AuthResultDTO handleLoginRequest(LoginDTO loginData) {
        // Check system status via ConnectionManager as per class diagram.
        ConnectionManager connMgr = new ConnectionManager();
        if (!connMgr.checkServerStatus()) {
            // Handle connection error.
            errorHandler.handleConnectionError(loginData);
            AuthResultDTO errorResult = new AuthResultDTO();
            errorResult.success = false;
            errorResult.message = "Connection error";
            return errorResult;
        }

        try {
            // Validate credentials.
            Boolean isValid = userService.validateCredentials(loginData.username, loginData.password);
            if (isValid) {
                // Authenticate and generate token.
                AuthResultDTO authResult = authService.authenticate(loginData);
                // Fetch user details.
                UserDTO userDTO = userService.findByUsername(loginData.username);
                authResult.user = userDTO;
                return createAuthResult(true, authResult.token, userDTO);
            } else {
                // Invalid credentials.
                errorHandler.handleAuthenticationFailure(loginData, "Invalid credentials");
                return createAuthResult(false, "Invalid credentials");
            }
        } catch (AuthenticationException e) {
            // Handle authentication exception.
            errorHandler.logError(e);
            AuthResultDTO errorResult = new AuthResultDTO();
            errorResult.success = false;
            errorResult.message = e.getMessage();
            return errorResult;
        } catch (Exception e) {
            // Handle other exceptions (e.g., connection).
            errorHandler.handleConnectionError(loginData);
            AuthResultDTO errorResult = new AuthResultDTO();
            errorResult.success = false;
            errorResult.message = "Connection error";
            return errorResult;
        }
    }

    /**
     * Creates an authentication result with success, token, and user DTO.
     * @param success whether authentication succeeded.
     * @param token the session token.
     * @param userDTO the user DTO.
     * @return the authentication result DTO.
     */
    public AuthResultDTO createAuthResult(Boolean success, String token, UserDTO userDTO) {
        AuthResultDTO result = new AuthResultDTO();
        result.success = success;
        result.token = token;
        result.user = userDTO;
        result.message = success ? "Authentication successful" : "Authentication failed";
        return result;
    }

    /**
     * Creates an authentication result with success and error message.
     * @param success whether authentication succeeded.
     * @param message the error message.
     * @return the authentication result DTO.
     */
    public AuthResultDTO createAuthResult(Boolean success, String message) {
        AuthResultDTO result = new AuthResultDTO();
        result.success = success;
        result.message = message;
        result.token = null;
        result.user = null;
        return result;
    }

    /**
     * Handles a logout request.
     * @param userId the user ID.
     */
    public void handleLogoutRequest(String userId) {
        authService.invalidateSession(userId);
    }
}