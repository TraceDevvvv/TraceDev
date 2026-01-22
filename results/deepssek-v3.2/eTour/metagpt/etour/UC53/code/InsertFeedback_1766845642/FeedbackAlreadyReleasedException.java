/**
 * FeedbackAlreadyReleasedException -       
 *                   
 *       FeedbackAlreadyReleased  
 */
public class FeedbackAlreadyReleasedException extends Exception {
    
    /**
     *     ，      
     * @param message     
     */
    public FeedbackAlreadyReleasedException(String message) {
        super(message);
    }
    
    /**
     *     ，         
     * @param message     
     * @param cause     
     */
    public FeedbackAlreadyReleasedException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     *            ，    ID   ID  
     * @param touristId   ID
     * @param siteId   ID
     * @return          FeedbackAlreadyReleasedException  
     */
    public static FeedbackAlreadyReleasedException create(String touristId, String siteId) {
        String message = String.format("   '%s'       '%s'      。                 。", 
                touristId, siteId);
        return new FeedbackAlreadyReleasedException(message);
    }
}