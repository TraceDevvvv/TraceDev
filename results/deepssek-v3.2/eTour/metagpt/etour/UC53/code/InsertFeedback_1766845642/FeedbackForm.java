import java.util.Scanner;

/**
 * FeedbackForm  -     
 *             ，        
 *             
 */
public class FeedbackForm {
    private String touristId;
    private String siteId;
    private int rating;
    private String comment;
    private Scanner scanner;
    
    /**
     *     
     * @param touristId   ID
     * @param siteId   ID
     */
    public FeedbackForm(String touristId, String siteId) {
        this.touristId = touristId;
        this.siteId = siteId;
        this.scanner = new Scanner(System.in);
    }
    
    /**
     *            
     *         3：    ，           
     * @return              ，  true；        ，  false
     */
    public boolean showForm() {
        System.out.println("\n===      ===");
        System.out.println("  : " + touristId);
        System.out.println("  : " + siteId);
        System.out.println("          ：");
        
        //     
        boolean validRating = false;
        while (!validRating) {
            System.out.print("      (1-5，1     ，5     ): ");
            
            //           
            String input = scanner.nextLine();
            if ("cancel".equalsIgnoreCase(input)) {
                System.out.println("     。");
                return false;
            }
            
            try {
                rating = Integer.parseInt(input);
                if (rating >= 1 && rating <= 5) {
                    validRating = true;
                } else {
                    System.out.println("  ：     1 5  。        'cancel'  。");
                }
            } catch (NumberFormatException e) {
                System.out.println("  ：         (1-5)。        'cancel'  。");
            }
        }
        
        //     
        boolean validComment = false;
        while (!validComment) {
            System.out.print("      (  5   ): ");
            
            //           
            comment = scanner.nextLine();
            if ("cancel".equalsIgnoreCase(comment)) {
                System.out.println("     。");
                return false;
            }
            
            if (comment != null && comment.trim().length() >= 5) {
                validComment = true;
            } else {
                System.out.println("  ：      5   。        'cancel'  。");
            }
        }
        
        //     
        System.out.print("\n        (  'confirm'  ， 'cancel'  ): ");
        String confirmation = scanner.nextLine();
        
        if ("confirm".equalsIgnoreCase(confirmation)) {
            System.out.println("      ！");
            return true;
        } else {
            System.out.println("     。");
            return false;
        }
    }
    
    /**
     *          
     * @return     (1-5)
     */
    public int getRating() {
        return rating;
    }
    
    /**
     *          
     * @return     
     */
    public String getComment() {
        return comment;
    }
    
    /**
     *     ID
     * @return   ID
     */
    public String getTouristId() {
        return touristId;
    }
    
    /**
     *     ID
     * @return   ID
     */
    public String getSiteId() {
        return siteId;
    }
    
    /**
     *           
     *         4：       
     * @return       ，  true；    false
     */
    public boolean validateFormData() {
        //             
        if (rating < 1 || rating > 5) {
            return false;
        }
        
        //         
        if (comment == null || comment.trim().isEmpty()) {
            return false;
        }
        
        //           
        if (comment.trim().length() < 5) {
            return false;
        }
        
        return true;
    }
    
    /**
     *   Feedback  
     *   ：                  
     * @param feedbackId   ID
     * @return        Feedback  
     */
    public Feedback createFeedback(String feedbackId) {
        return new Feedback(feedbackId, touristId, siteId, rating, comment);
    }
    
    /**
     *       
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     *             
     *           "   ETOUR        "
     * @return       ，  true；      ，  false
     */
    public boolean checkServerConnection() {
        //       ，      ETOUR      
        //       90%        ，10%        
        double random = Math.random();
        return random < 0.9; // 90%       
    }
}