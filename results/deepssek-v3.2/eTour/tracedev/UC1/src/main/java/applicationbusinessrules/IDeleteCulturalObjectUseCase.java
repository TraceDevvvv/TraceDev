package applicationbusinessrules;

/**
 * Use case interface for deleting a cultural object.
 */
public interface IDeleteCulturalObjectUseCase {
    /**
     * Executes the delete cultural object command.
     * @param command the delete command
     * @return the result of the operation
     */
    applicationbusinessrules.Result execute(DeleteCulturalObjectCommand command);
}
