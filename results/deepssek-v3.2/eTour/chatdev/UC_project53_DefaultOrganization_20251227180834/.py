/**
 * DatabaseManager extension to include helper methods.
 * This file adds the missing method to DatabaseManager.
 */
import java.util.List;
public class DatabaseManager {
    // ... Previous code (constructor, methods) ...
    /**
     * Get the total number of feedbacks (for ID generation).
     */
    public int getAllFeedbacksSize() {
        return feedbacks.size();
    }
    // Note: To avoid duplication, in a real project you'd edit the original file.
    // For this task, you can merge this method into the original DatabaseManager.
}