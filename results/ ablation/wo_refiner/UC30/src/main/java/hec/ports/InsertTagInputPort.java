package hec.ports;

import hec.usecase.InsertTagRequest;
import hec.usecase.InsertTagResponse;

/**
 * Input port (interface) for inserting tags.
 * Defines the contract for the controller.
 */
public interface InsertTagInputPort {
    /**
     * Inserts a tag based on the request.
     *
     * @param request the insert tag request
     * @return the insert tag response
     */
    InsertTagResponse insertTag(InsertTagRequest request);
}