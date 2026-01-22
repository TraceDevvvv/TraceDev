import java.util.UUID;

/**
 * FeedbackService  -      
 *   InsertFeedback       ，      、         
 *           ：
 * 1.       
 * 2.            
 * 3.       
 * 4.        
 * 5.     
 * 6.         
 */
public class FeedbackService {
    private DataStorage dataStorage;
    private FeedbackForm feedbackForm;
    
    /**
     *     
     * @param dataStorage       
     */
    public FeedbackService(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
    
    /**
     *   InsertFeedback       
     *     ：         
     * @param touristId   ID
     * @param siteId   ID
     * @return     ，    /       ID
     * @throws FeedbackAlreadyReleasedException               
     * @throws InvalidFeedbackException               
     * @throws IllegalStateException               
     */
    public InsertFeedbackResult executeInsertFeedback(String touristId, String siteId) 
            throws FeedbackAlreadyReleasedException, InvalidFeedbackException, IllegalStateException {
        
        //   1：       -     
        System.out.println("===        ===");
        System.out.println("  ID: " + touristId + ",   ID: " + siteId);
        
        //       ：           
        if (!dataStorage.touristExists(touristId)) {
            throw new IllegalStateException("  ID '" + touristId + "'    。");
        }
        
        if (!dataStorage.siteExists(siteId)) {
            throw new IllegalStateException("  ID '" + siteId + "'    。");
        }
        
        //   2：             
        if (dataStorage.hasTouristGivenFeedback(touristId, siteId)) {
            System.out.println("  ：             。");
            throw FeedbackAlreadyReleasedException.create(touristId, siteId);
        }
        
        //         
        System.out.println("    ：            。");
        
        //       ，    2       
        feedbackForm = new FeedbackForm(touristId, siteId);
        
        //         -            
        if (!feedbackForm.checkServerConnection()) {
            System.out.println("  ：   ETOUR        。     。");
            feedbackForm.close();
            throw new IllegalStateException("       。      。");
        }
        
        //   3：         ，        
        System.out.println("\n      ...");
        boolean formSubmitted = feedbackForm.showForm();
        
        //         （      ）
        if (!formSubmitted) {
            System.out.println("         。");
            feedbackForm.close();
            return new InsertFeedbackResult(false, null, "       。");
        }
        
        try {
            //   4：       
            int rating = feedbackForm.getRating();
            String comment = feedbackForm.getComment();
            
            //             
            Feedback tempFeedback = new Feedback("TEMP", touristId, siteId, rating, comment);
            
            if (!tempFeedback.isValid()) {
                System.out.println("  ：          。");
                //       Errored  
                throw InvalidFeedbackException.create(rating, comment);
            }
            
            //   5：      
            System.out.println("\n        ...");
            
            //       ，             
            //   ：              
            
            //   6：                   
            System.out.println("      ...");
            
            //        ID
            String feedbackId = generateFeedbackId();
            
            //          
            Feedback finalFeedback = feedbackForm.createFeedback(feedbackId);
            
            //        
            String savedFeedbackId = dataStorage.saveFeedback(finalFeedback);
            
            //       
            System.out.println("\n===        ===");
            System.out.println("  ID: " + savedFeedbackId);
            System.out.println("                。");
            System.out.println("               。");
            
            //       
            return new InsertFeedbackResult(true, savedFeedbackId, "         。");
            
        } finally {
            //          
            if (feedbackForm != null) {
                feedbackForm.close();
            }
        }
    }
    
    /**
     *   InsertFeedback       
     *        executeInsertFeedback  ，          
     * @param touristId   ID
     * @param siteId   ID
     * @return     ，           
     */
    public InsertFeedbackResult processFeedback(String touristId, String siteId) {
        try {
            return executeInsertFeedback(touristId, siteId);
        } catch (FeedbackAlreadyReleasedException e) {
            System.out.println("  : " + e.getMessage());
            return new InsertFeedbackResult(false, null, "  : " + e.getMessage());
        } catch (InvalidFeedbackException e) {
            System.out.println("      : " + e.getMessage());
            return new InsertFeedbackResult(false, null, "      : " + e.getMessage());
        } catch (IllegalStateException e) {
            System.out.println("    : " + e.getMessage());
            return new InsertFeedbackResult(false, null, "    : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("      : " + e.getMessage());
            return new InsertFeedbackResult(false, null, "      : " + e.getMessage());
        }
    }
    
    /**
     *                  
     *         ，            
     * @param touristId   ID
     * @param siteId   ID
     * @return             true，    false
     */
    public boolean canTouristProvideFeedback(String touristId, String siteId) {
        //            
        if (!dataStorage.touristExists(touristId) || !dataStorage.siteExists(siteId)) {
            return false;
        }
        
        //                  
        return !dataStorage.hasTouristGivenFeedback(touristId, siteId);
    }
    
    /**
     *         
     * @return          
     */
    public DataStorage getDataStorage() {
        return dataStorage;
    }
    
    /**
     *        ID
     * @return      ID   
     */
    private String generateFeedbackId() {
        //   UUID    ID，                  
        return "FB_" + UUID.randomUUID().toString().substring(0, 8);
    }
    
    /**
     * InsertFeedbackResult  -      
     *     InsertFeedback     
     */
    public static class InsertFeedbackResult {
        private boolean success;
        private String feedbackId;
        private String message;
        
        /**
         *     
         * @param success     
         * @param feedbackId   ID（   ）
         * @param message     
         */
        public InsertFeedbackResult(boolean success, String feedbackId, String message) {
            this.success = success;
            this.feedbackId = feedbackId;
            this.message = message;
        }
        
        // Getter  
        
        public boolean isSuccess() {
            return success;
        }
        
        public String getFeedbackId() {
            return feedbackId;
        }
        
        public String getMessage() {
            return message;
        }
        
        @Override
        public String toString() {
            return String.format("InsertFeedbackResult{success=%s, feedbackId='%s', message='%s'}", 
                               success, feedbackId, message);
        }
    }
    
    /**
     *       ：           
     *          
     */
    public void runTestScenarios() {
        System.out.println("\n===        ===");
        
        //   1：    
        System.out.println("\n  1：      ");
        InsertFeedbackResult result1 = processFeedback("T001", "S001");
        System.out.println("  : " + result1.getMessage());
        
        //   2：    （    FeedbackAlreadyReleasedException）
        System.out.println("\n  2：    （  T001   S001    ）");
        InsertFeedbackResult result2 = processFeedback("T001", "S001");
        System.out.println("  : " + result2.getMessage());
        
        //   3：       
        System.out.println("\n  3：       （  T002   S002  ）");
        InsertFeedbackResult result3 = processFeedback("T002", "S002");
        System.out.println("  : " + result3.getMessage());
        
        //   4：         
        System.out.println("\n  4：      （  T999   S001  ）");
        InsertFeedbackResult result4 = processFeedback("T999", "S001");
        System.out.println("  : " + result4.getMessage());
    }
}