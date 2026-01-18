package applicationbusinessrules;

/**
 * Result representing a failed operation.
 */
public class ErrorResult implements IResult {
    private boolean success = false;
    private String message;

    public ErrorResult(String message) {
        this.message = message;
    }

    @Override
    public boolean isSuccess() {
        return success;
    }

    @Override
    public String getMessage() {
        return message;
    }
}