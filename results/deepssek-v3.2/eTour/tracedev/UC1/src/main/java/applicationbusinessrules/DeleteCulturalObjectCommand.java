package applicationbusinessrules;

/**
 * Command object for deleting a cultural object.
 */
public class DeleteCulturalObjectCommand {
    private String requestId;
    private String culturalObjectId;
    private String confirmedBy;

    public DeleteCulturalObjectCommand(String requestId, String culturalObjectId, String confirmedBy) {
        this.requestId = requestId;
        this.culturalObjectId = culturalObjectId;
        this.confirmedBy = confirmedBy;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getCulturalObjectId() {
        return culturalObjectId;
    }

    public String getConfirmedBy() {
        return confirmedBy;
    }
}