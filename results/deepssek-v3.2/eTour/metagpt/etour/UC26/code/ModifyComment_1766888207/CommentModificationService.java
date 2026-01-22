import java.util.List;

/**
 * CommentModificationService              。
 *       、    、         。
 *            ，      、       。
 */
public class CommentModificationService {
    private AgencyOperator currentOperator;
    private Site selectedSite;
    private Feedback selectedFeedback;
    private boolean isOperationConfirmed;
    private boolean isOperationCancelled;
    private boolean isServerConnected;
    
    /**
     *     ，     。
     * @param operator      ，     
     * @throws IllegalArgumentException            
     */
    public CommentModificationService(AgencyOperator operator) {
        if (operator == null) {
            throw new IllegalArgumentException("       ");
        }
        if (!operator.isLoggedIn()) {
            throw new IllegalArgumentException("                  ");
        }
        this.currentOperator = operator;
        this.selectedSite = null;
        this.selectedFeedback = null;
        this.isOperationConfirmed = false;
        this.isOperationCancelled = false;
        this.isServerConnected = operator.checkServerConnection();
    }
    
    /**
     *   1：           。
     *    SearchSite        ，         。
     * @param sites     
     * @param siteId       ID
     * @return          true，    false
     */
    public boolean selectSite(List<Site> sites, int siteId) {
        //        
        if (!isServerConnected) {
            System.err.println("  ：       ，      ");
            return false;
        }
        
        //        
        if (!currentOperator.canModifyComment()) {
            System.err.println("  ：      ，      ");
            return false;
        }
        
        //           ID   
        for (Site site : sites) {
            if (site.getSiteId() == siteId) {
                selectedSite = site;
                System.out.println("     : " + site.getSiteName());
                return true;
            }
        }
        
        System.err.println("  ：   ID  " + siteId + "    ");
        return false;
    }
    
    /**
     *   2 3：             。
     *           ，          。
     * @param feedbackId       ID
     * @return          true，    false
     */
    public boolean selectFeedback(int feedbackId) {
        //        
        if (!isServerConnected) {
            System.err.println("  ：       ，      ");
            return false;
        }
        
        //          
        if (selectedSite == null) {
            System.err.println("  ：      ");
            return false;
        }
        
        //          
        List<Feedback> feedbacks = selectedSite.getFeedbacks();
        if (feedbacks.isEmpty()) {
            System.err.println("  ：       ");
            return false;
        }
        
        //     ID   
        Feedback feedback = selectedSite.findFeedbackById(feedbackId);
        if (feedback == null) {
            System.err.println("  ：   ID  " + feedbackId + "    ");
            return false;
        }
        
        selectedFeedback = feedback;
        System.out.println("     : " + feedback);
        return true;
    }
    
    /**
     *   4 5：           。
     *              。
     * @param newComment       
     * @return          true，    false
     */
    public boolean editComment(String newComment) {
        //        
        if (!isServerConnected) {
            System.err.println("  ：       ，      ");
            return false;
        }
        
        //          
        if (selectedFeedback == null) {
            System.err.println("  ：      ");
            return false;
        }
        
        //   6：       
        if (!validateCommentData(newComment)) {
            //     ，  Errored  
            handleErroredCase("         ");
            return false;
        }
        
        //               
        String originalComment = selectedFeedback.getComment();
        
        //       
        boolean isChanged = selectedFeedback.setComment(newComment);
        if (!isChanged) {
            System.out.println("  ：       ");
            return false;
        }
        
        System.out.println("     ，    ...");
        System.out.println("    : " + originalComment);
        System.out.println("   : " + newComment);
        
        return true;
    }
    
    /**
     *   6：      。
     *            。
     * @param comment       
     * @return          true，    false
     */
    private boolean validateCommentData(String comment) {
        if (comment == null) {
            System.err.println("  ：      ");
            return false;
        }
        
        String trimmedComment = comment.trim();
        if (trimmedComment.isEmpty()) {
            System.err.println("  ：              ");
            return false;
        }
        
        //         
        if (trimmedComment.length() > 1000) {
            System.err.println("  ：        1000   ");
            return false;
        }
        
        //             （    ）
        if (trimmedComment.contains("<script>") || trimmedComment.contains("</script>")) {
            System.err.println("  ：             ");
            return false;
        }
        
        return true;
    }
    
    /**
     *   6：      ，  Errored  。
     *       ，         、               。
     * @param errorMessage     
     */
    private void handleErroredCase(String errorMessage) {
        System.err.println("  Errored  : " + errorMessage);
        //       ，     ：
        // 1.       
        // 2.       
        // 3.           
        // 4.       
        resetSelection();
    }
    
    /**
     *   7：    。
     *            。
     * @return          true，    false
     */
    public boolean confirmOperation() {
        //        
        if (!isServerConnected) {
            System.err.println("  ：       ，      ");
            return false;
        }
        
        //          
        if (selectedFeedback == null) {
            System.err.println("  ：          ");
            return false;
        }
        
        //           
        if (!selectedFeedback.isModified()) {
            System.out.println("  ：      ，    ");
            return false;
        }
        
        isOperationConfirmed = true;
        System.out.println("     ，        ...");
        
        //   8：        
        rememberModifiedComment();
        
        return true;
    }
    
    /**
     *   8：        。
     *       ，                    。
     */
    private void rememberModifiedComment() {
        if (selectedFeedback != null && selectedFeedback.isModified()) {
            //       ，    ：
            // 1.       
            // 2.     
            // 3.     
            
            System.out.println("          ");
            System.out.println("    : " + selectedFeedback);
            
            //       
            selectedFeedback.resetModifiedFlag();
        }
    }
    
    /**
     *     。
     *                 。
     * @return          true
     */
    public boolean cancelOperation() {
        isOperationCancelled = true;
        System.out.println("     ");
        
        //             ，      
        if (selectedFeedback != null && selectedFeedback.isModified()) {
            //       ，                
            System.out.println("       ");
            //   ：           ，          
            selectedFeedback.resetModifiedFlag();
        }
        
        resetSelection();
        return true;
    }
    
    /**
     *          。
     *               。
     * @return             true，    false
     */
    public boolean checkServerConnection() {
        isServerConnected = currentOperator.checkServerConnection();
        if (!isServerConnected) {
            System.err.println("  ：       ");
            resetSelection();
        }
        return isServerConnected;
    }
    
    /**
     *       。
     *      、        。
     */
    private void resetSelection() {
        selectedSite = null;
        selectedFeedback = null;
        isOperationConfirmed = false;
    }
    
    /**
     *          。
     * @return        ，        null
     */
    public Site getSelectedSite() {
        return selectedSite;
    }
    
    /**
     *          。
     * @return        ，        null
     */
    public Feedback getSelectedFeedback() {
        return selectedFeedback;
    }
    
    /**
     *          。
     * @return           true，    false
     */
    public boolean isOperationConfirmed() {
        return isOperationConfirmed;
    }
    
    /**
     *          。
     * @return           true，    false
     */
    public boolean isOperationCancelled() {
        return isOperationCancelled;
    }
    
    /**
     *          。
     * @return             true，    false
     */
    public boolean isServerConnected() {
        return isServerConnected;
    }
    
    /**
     *            。
     *         。
     */
    public void notifySystem() {
        if (isOperationConfirmed && selectedFeedback != null) {
            System.out.println("    ：   " + selectedFeedback.getFeedbackId() + "     ");
            System.out.println("      ：              ");
        }
    }
}