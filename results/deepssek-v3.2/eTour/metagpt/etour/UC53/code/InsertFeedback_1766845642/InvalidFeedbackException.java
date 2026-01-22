/**
 * InvalidFeedbackException -       
 *                  
 *       Errored  
 */
public class InvalidFeedbackException extends Exception {
    
    /**
     *     ，      
     * @param message     
     */
    public InvalidFeedbackException(String message) {
        super(message);
    }
    
    /**
     *     ，         
     * @param message     
     * @param cause     
     */
    public InvalidFeedbackException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     *            ，             
     * @param rating    
     * @param comment     
     * @return          InvalidFeedbackException  
     */
    public static InvalidFeedbackException create(int rating, String comment) {
        StringBuilder errorMsg = new StringBuilder("          : ");
        
        //         
        if (rating < 1 || rating > 5) {
            errorMsg.append(String.format("   '%d'         (1-5)。", rating));
        }
        
        //         
        if (comment == null || comment.trim().isEmpty()) {
            if (errorMsg.length() > 0) errorMsg.append(" ");
            errorMsg.append("        。");
        }
        
        //              ，     
        if (rating >= 1 && rating <= 5 && comment != null && !comment.trim().isEmpty() 
            && comment.trim().length() < 5) {
            errorMsg.append("        5   。");
        }
        
        return new InvalidFeedbackException(errorMsg.toString());
    }
    
    /**
     *            
     * @param reason       
     * @return          InvalidFeedbackException  
     */
    public static InvalidFeedbackException create(String reason) {
        return new InvalidFeedbackException("      : " + reason);
    }
}