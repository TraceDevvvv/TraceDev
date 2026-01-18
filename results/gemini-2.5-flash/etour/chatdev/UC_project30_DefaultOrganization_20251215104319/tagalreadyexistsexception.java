/**
 * Custom exception thrown when an attempt is made to add a tag
 * that already exists in the system.
 * Corresponds to the 'ErroreTagEsistente' use case.
 */
public class TagAlreadyExistsException extends Exception {
    /**
     * Constructs a new TagAlreadyExistsException with the specified detail message.
     * @param message The detail message.
     */
    public TagAlreadyExistsException(String message) {
        super(message);
    }
}