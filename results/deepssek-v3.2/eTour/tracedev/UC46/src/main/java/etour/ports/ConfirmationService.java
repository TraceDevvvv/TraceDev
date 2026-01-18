package etour.ports;

/**
 * Port interface for requesting user confirmation.
 */
public interface ConfirmationService {
    boolean requestConfirmation(String message);
}