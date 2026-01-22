import java.util.List;
import java.util.Scanner;

/**
 * UserInterface          ，               。
 *            ，      、    、          。
 *       、              。
 */
public class UserInterface {
    private Scanner scanner;
    private AgencyOperator currentOperator;
    private CommentModificationService commentService;
    private List<Site> availableSites;
    
    /**
     *     ，       。
     * @param operator      ，     
     * @param sites        
     */
    public UserInterface(AgencyOperator operator, List<Site> sites) {
        this.scanner = new Scanner(System.in);
        this.currentOperator = operator;
        this.availableSites = sites;
        
        //        
        if (operator == null || !operator.isLoggedIn()) {
            throw new IllegalStateException("                  ");
        }
        
        //          
        this.commentService = new CommentModificationService(operator);
    }
    
    /**
     *      ，        。
     *              。
     */
    public void startModificationProcess() {
        System.out.println("======        ======");
        System.out.println("  ，" + currentOperator.getOperatorName());
        System.out.println("     ID: " + currentOperator.getOperatorId());
        System.out.println();
        
        //        
        if (!commentService.checkServerConnection()) {
            System.err.println("  ：        ，          ");
            return;
        }
        
        try {
            //         
            boolean continueProcess = true;
            
            //   1：           
            if (continueProcess) {
                continueProcess = step1_ViewAndSelectSite();
            }
            
            //   2 3：         
            if (continueProcess) {
                continueProcess = step2and3_DisplayAndSelectFeedback();
            }
            
            //   4 5：    
            if (continueProcess) {
                continueProcess = step4and5_EditComment();
            }
            
            //   6：         
            if (continueProcess) {
                continueProcess = step6_ValidateAndRequestConfirmation();
            }
            
            //   7：    
            if (continueProcess) {
                step7_ConfirmOperation();
            }
            
            //   8：    
            if (continueProcess) {
                step8_CompleteProcess();
            }
            
            //         
            if (commentService.isOperationCancelled()) {
                System.out.println("        ");
            }
            
        } catch (Exception e) {
            System.err.println("      : " + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("======          ======");
        }
    }
    
    /**
     *   1：           。
     *    SearchSite        。
     * @return            true，    false
     */
    private boolean step1_ViewAndSelectSite() {
        System.out.println("===   1：     ===");
        
        //         
        displaySiteList();
        
        //         ，    
        if (availableSites.isEmpty()) {
            System.err.println("  ：       ");
            return false;
        }
        
        //          ID
        Integer siteId = null;
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (siteId == null && attempts < maxAttempts) {
            try {
                System.out.print("         ID (  'cancel'    ): ");
                String input = scanner.nextLine().trim();
                
                //           
                if (input.equalsIgnoreCase("cancel")) {
                    commentService.cancelOperation();
                    return false;
                }
                
                //          
                siteId = Integer.parseInt(input);
                
                //       
                boolean success = commentService.selectSite(availableSites, siteId);
                if (!success) {
                    System.err.println("      ，   ");
                    siteId = null;
                    attempts++;
                }
            } catch (NumberFormatException e) {
                System.err.println("  ：        ID");
                attempts++;
            }
        }
        
        if (siteId == null) {
            System.err.println("  ：        ，    ");
            return false;
        }
        
        System.out.println("      ");
        return true;
    }
    
    /**
     *         。
     */
    private void displaySiteList() {
        System.out.println("      :");
        System.out.println("ID\t  \t\t   ");
        System.out.println("--------------------------------");
        
        for (Site site : availableSites) {
            System.out.printf("%-6d %-15s %-3d%n", 
                site.getSiteId(), 
                site.getSiteName(), 
                site.getFeedbacks().size());
        }
        System.out.println();
    }
    
    /**
     *   2 3：         。
     *           ，          。
     * @return            true，    false
     */
    private boolean step2and3_DisplayAndSelectFeedback() {
        System.out.println("===   2 3：     ===");
        
        Site selectedSite = commentService.getSelectedSite();
        if (selectedSite == null) {
            System.err.println("  ：     ");
            return false;
        }
        
        //          
        displayFeedbackList(selectedSite);
        
        List<Feedback> feedbacks = selectedSite.getFeedbacks();
        if (feedbacks.isEmpty()) {
            System.err.println("  ：          ");
            return false;
        }
        
        //          ID
        Integer feedbackId = null;
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (feedbackId == null && attempts < maxAttempts) {
            try {
                System.out.print("         ID (  'cancel'    ): ");
                String input = scanner.nextLine().trim();
                
                //           
                if (input.equalsIgnoreCase("cancel")) {
                    commentService.cancelOperation();
                    return false;
                }
                
                //          
                feedbackId = Integer.parseInt(input);
                
                //       
                boolean success = commentService.selectFeedback(feedbackId);
                if (!success) {
                    System.err.println("      ，   ");
                    feedbackId = null;
                    attempts++;
                }
            } catch (NumberFormatException e) {
                System.err.println("  ：        ID");
                attempts++;
            }
        }
        
        if (feedbackId == null) {
            System.err.println("  ：        ，    ");
            return false;
        }
        
        System.out.println("      ");
        return true;
    }
    
    /**
     *          。
     * @param site         
     */
    private void displayFeedbackList(Site site) {
        System.out.println("   \"" + site.getSiteName() + "\"      :");
        System.out.println("ID\t    \t\t\t    ");
        System.out.println("------------------------------------------------------------------------");
        
        List<Feedback> feedbacks = site.getFeedbacks();
        for (Feedback feedback : feedbacks) {
            String commentPreview = feedback.getComment();
            if (commentPreview.length() > 30) {
                commentPreview = commentPreview.substring(0, 27) + "...";
            }
            
            System.out.printf("%-6d %-20s %-30s%n",
                feedback.getFeedbackId(),
                feedback.getCreatedAt().toString().substring(0, 16),
                commentPreview);
        }
        System.out.println();
    }
    
    /**
     *   4 5：    。
     *              。
     * @return          true，    false
     */
    private boolean step4and5_EditComment() {
        System.out.println("===   4 5：     ===");
        
        Feedback selectedFeedback = commentService.getSelectedFeedback();
        if (selectedFeedback == null) {
            System.err.println("  ：     ");
            return false;
        }
        
        //       
        System.out.println("      :");
        System.out.println("----------------------------------------");
        System.out.println(selectedFeedback.getComment());
        System.out.println("----------------------------------------");
        System.out.println("      : " + selectedFeedback.getCreatedAt());
        System.out.println("      : " + selectedFeedback.getLastModifiedAt());
        System.out.println();
        
        //           
        String newComment = null;
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (newComment == null && attempts < maxAttempts) {
            System.out.println("          (  'cancel'    ):");
            System.out.println("  ：      ，      1000  ");
            System.out.print("> ");
            
            String input = scanner.nextLine().trim();
            
            //           
            if (input.equalsIgnoreCase("cancel")) {
                commentService.cancelOperation();
                return false;
            }
            
            //         
            if (input.isEmpty()) {
                System.err.println("  ：        ");
                attempts++;
                continue;
            }
            
            //       
            if (input.length() > 1000) {
                System.err.println("  ：      1000    ");
                attempts++;
                continue;
            }
            
            newComment = input;
        }
        
        if (newComment == null) {
            System.err.println("  ：        ，    ");
            return false;
        }
        
        //       
        boolean success = commentService.editComment(newComment);
        if (!success) {
            System.err.println("      ");
            return false;
        }
        
        System.out.println("      ，    ...");
        return true;
    }
    
    /**
     *   6：         。
     *                   。
     * @return                 true，    false
     */
    private boolean step6_ValidateAndRequestConfirmation() {
        System.out.println("===   6：        ===");
        
        //       editComment     
        //         
        
        Feedback selectedFeedback = commentService.getSelectedFeedback();
        if (selectedFeedback == null || !selectedFeedback.isModified()) {
            System.err.println("  ：          ");
            return false;
        }
        
        System.out.println("        ！");
        System.out.println("           :");
        System.out.println("  ID: " + selectedFeedback.getFeedbackId());
        System.out.println("    : " + selectedFeedback.getLastModifiedAt());
        System.out.println();
        
        //       
        String confirmation = null;
        int attempts = 0;
        final int maxAttempts = 3;
        
        while (confirmation == null && attempts < maxAttempts) {
            System.out.print("       ？(yes/no/cancel): ");
            String input = scanner.nextLine().trim().toLowerCase();
            
            if (input.equals("cancel")) {
                commentService.cancelOperation();
                return false;
            } else if (input.equals("no")) {
                System.out.println("         ");
                commentService.cancelOperation();
                return false;
            } else if (input.equals("yes")) {
                confirmation = input;
            } else {
                System.err.println("  ：    yes, no   cancel");
                attempts++;
            }
        }
        
        if (confirmation == null) {
            System.err.println("  ：        ，    ");
            return false;
        }
        
        System.out.println("    ，      ...");
        return true;
    }
    
    /**
     *   7：    。
     *            。
     */
    private void step7_ConfirmOperation() {
        System.out.println("===   7：     ===");
        
        boolean success = commentService.confirmOperation();
        if (!success) {
            System.err.println("      ");
        } else {
            System.out.println("      ");
        }
    }
    
    /**
     *   8：    。
     *              。
     */
    private void step8_CompleteProcess() {
        System.out.println("===   8：     ===");
        
        //          
        if (commentService.isOperationConfirmed()) {
            //     （        ）
            commentService.notifySystem();
            System.out.println("        ！");
        } else {
            System.out.println("           ");
        }
    }
    
    /**
     *       ，    。
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     *              。
     * @return         
     */
    public CommentModificationService getCommentService() {
        return commentService;
    }
}