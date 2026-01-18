/**
 * Provides utility methods for validating teaching-related data,
 * specifically the teaching name.
 */
public class TeachingValidator {

    /**
     * Validates a given teaching name.
     * A teaching name is considered valid if it is not null and not empty
     * after trimming whitespace.
     *
     * @param name The teaching name to validate.
     * @return true if the name is valid, false otherwise.
     */
    public static boolean isValidTeachingName(String name) {
        // A teaching name is valid if it's not null and not empty after trimming.
        // This aligns with the validation performed in the Teaching class constructor.
        return name != null && !name.trim().isEmpty();
    }

    /**
     * Checks if a teaching name is valid and throws an IllegalArgumentException if not.
     * This method can be used when an invalid name should immediately halt execution
     * or indicate a critical input error.
     *
     * @param name The teaching name to check.
     * @throws IllegalArgumentException if the teaching name is null or empty.
     */
    public static void validateTeachingNameOrThrow(String name) throws IllegalArgumentException {
        if (!isValidTeachingName(name)) {
            throw new IllegalArgumentException("Teaching name cannot be null or empty.");
        }
    }
}