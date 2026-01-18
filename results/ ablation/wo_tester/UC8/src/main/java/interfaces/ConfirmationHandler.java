package interfaces;

/**
 * Interface for confirmation handling (Flow step 9).
 */
public interface ConfirmationHandler {
    boolean requestConfirmation(String message);
}