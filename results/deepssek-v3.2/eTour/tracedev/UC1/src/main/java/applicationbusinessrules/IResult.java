package applicationbusinessrules;

/**
 * Generic result interface.
 */
public interface IResult {
    boolean isSuccess();
    String getMessage();
}