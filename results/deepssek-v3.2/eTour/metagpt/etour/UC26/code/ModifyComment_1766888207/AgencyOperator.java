/**
 * AgencyOperator        ，     ID、          。
 *          ，                     。
 */
public class AgencyOperator {
    private int operatorId;
    private String operatorName;
    private boolean isLoggedIn;
    
    /**
     *     ，      。
     * @param operatorId        
     * @param operatorName      
     */
    public AgencyOperator(int operatorId, String operatorName) {
        this.operatorId = operatorId;
        this.operatorName = operatorName;
        this.isLoggedIn = false; //      
    }
    
    /**
     *      ID。
     * @return    ID
     */
    public int getOperatorId() {
        return operatorId;
    }
    
    /**
     *      ID。
     * @param operatorId      ID
     */
    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }
    
    /**
     *        。
     * @return      
     */
    public String getOperatorName() {
        return operatorName;
    }
    
    /**
     *        。
     * @param operatorName        
     */
    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
    
    /**
     *           。
     * @return            true，    false
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     *          。
     * @param username    
     * @param password   
     * @return          true，    false
     */
    public boolean login(String username, String password) {
        //         
        //       ，               
        if (username != null && password != null && 
            !username.trim().isEmpty() && !password.trim().isEmpty()) {
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }
    
    /**
     *          。
     */
    public void logout() {
        this.isLoggedIn = false;
    }
    
    /**
     *                   。
     *       ，     "     "。
     * @return            true，    false
     */
    public boolean canModifyComment() {
        return isLoggedIn;
    }
    
    /**
     *          。
     *     "       "     。
     * @return             true，    false
     */
    public boolean checkServerConnection() {
        //          
        //       ，           
        return true; //       
    }
    
    /**
     *             。
     * @return         
     */
    @Override
    public String toString() {
        return "AgencyOperator [ID=" + operatorId + 
               ", Name=" + operatorName + 
               ", LoggedIn=" + isLoggedIn + "]";
    }
}