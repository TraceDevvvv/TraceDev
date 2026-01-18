package applicationbusinessrules;

/**
 * Result representing a successful operation.
 */
public class SuccessResult implements IResult {
    private boolean success = true;
    private String message;

    public SuccessResult(String message) {
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