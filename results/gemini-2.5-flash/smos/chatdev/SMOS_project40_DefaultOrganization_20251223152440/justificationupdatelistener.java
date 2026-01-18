'''
An interface for callback mechanism when a justification is updated.
Used by EditJustificationFrame to notify JustificationListFrame.
'''
package gui;
/**
 * A functional interface for listening to justification updates.
 * Implementations will define what happens when a justification is successfully modified.
 */
@FunctionalInterface
public interface JustificationUpdateListener {
    /**
     * Called when a justification has been successfully updated.
     * This typically triggers a refresh or re-rendering of data in the listening component.
     */
    void onJustificationUpdated();
}