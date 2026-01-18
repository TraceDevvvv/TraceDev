package enterprisebusinessrules;

/**
 * Represents a result of an operation.
 */
public class Result {
    private Boolean success;
    private String message;

    public Result(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}