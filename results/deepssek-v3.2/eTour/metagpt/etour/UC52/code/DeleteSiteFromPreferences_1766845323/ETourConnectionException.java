/**
 *       ，   ETOUR          
 *    Exception ，           
 */
public class ETourConnectionException extends Exception {
    
    /**
     *     ，           
     */
    public ETourConnectionException() {
        super();
    }
    
    /**
     *     ，           
     * @param message       
     */
    public ETourConnectionException(String message) {
        super(message);
    }
    
    /**
     *     ，              
     * @param message       
     * @param cause     
     */
    public ETourConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    /**
     *     ，         
     * @param cause     
     */
    public ETourConnectionException(Throwable cause) {
        super(cause);
    }
}