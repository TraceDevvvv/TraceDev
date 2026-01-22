import java.util.Scanner;

/**
 * InsertFeedbackApplication -       
 *   InsertFeedback       ，  main  、           
 *           ，                
 */
public class InsertFeedbackApplication {
    
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("      InsertFeedback              ");
        System.out.println("========================================");
        System.out.println("  ：          ");
        System.out.println("   ：  ");
        System.out.println("    ：         \n");
        
        try {
            // 1.           
            DataStorage dataStorage = new DataStorage();
            FeedbackService feedbackService = new FeedbackService(dataStorage);
            
            // 2.         
            displayInitialData(dataStorage);
            
            // 3.          
            Scanner scanner = new Scanner(System.in);
            
            // 4.      
            boolean exit = false;
            while (!exit) {
                displayMainMenu();
                
                String choice = scanner.nextLine().trim();
                
                switch (choice) {
                    case "1":
                        //          
                        executeFeedbackProcess(feedbackService, scanner);
                        break;
                        
                    case "2":
                        //       
                        feedbackService.runTestScenarios();
                        break;
                        
                    case "3":
                        //         
                        displayCurrentData(dataStorage);
                        break;
                        
                    case "4":
                        //             
                        checkFeedbackEligibility(feedbackService, scanner);
                        break;
                        
                    case "5":
                        //         
                        demonstrateExitConditions(feedbackService, scanner);
                        break;
                        
                    case "6":
                        //     
                        dataStorage.clearAllStorage();
                        System.out.println("          。");
                        displayCurrentData(dataStorage);
                        break;
                        
                    case "0":
                        exit = true;
                        System.out.println("    InsertFeedback    ！");
                        break;
                        
                    default:
                        System.out.println("     ，     。");
                }
            }
            
            scanner.close();
            
        } catch (Exception e) {
            System.err.println("        : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     *      
     */
    private static void displayMainMenu() {
        System.out.println("\n===     ===");
        System.out.println("1.       ");
        System.out.println("2.       ");
        System.out.println("3.         ");
        System.out.println("4.       ");
        System.out.println("5.       ");
        System.out.println("6.     ");
        System.out.println("0.     ");
        System.out.print("     : ");
    }
    
    /**
     *         
     * @param dataStorage       
     */
    private static void displayInitialData(DataStorage dataStorage) {
        System.out.println("===        ===");
        System.out.println(dataStorage.getStorageStats());
        
        System.out.println("     :");
        for (Tourist tourist : dataStorage.getAllTourists()) {
            System.out.println("  " + tourist.getTouristId() + ": " + tourist.getName());
        }
        
        System.out.println("\n     :");
        for (Site site : dataStorage.getAllSites()) {
            System.out.println("  " + site.getSiteId() + ": " + site.getName() + 
                             " - " + site.getDescription());
        }
        System.out.println();
    }
    
    /**
     *         
     * @param dataStorage       
     */
    private static void displayCurrentData(DataStorage dataStorage) {
        System.out.println("\n===        ===");
        System.out.println(dataStorage.getStorageStats());
        
        //       
        System.out.println("    :");
        for (Feedback feedback : dataStorage.getAllFeedback()) {
            System.out.println("  " + feedback.getFeedbackId() + 
                             ":    " + feedback.getTouristId() + 
                             "     " + feedback.getSiteId() + 
                             "    " + feedback.getRating() + 
                             "  ，  : " + feedback.getComment());
        }
    }
    
    /**
     *       （          ）
     * @param feedbackService       
     * @param scanner        
     */
    private static void executeFeedbackProcess(FeedbackService feedbackService, Scanner scanner) {
        System.out.println("\n===        ===");
        System.out.println("  ：        InsertFeedback  ");
        
        //          
        String touristId = getInput("     ID (  : T001, T002): ", scanner);
        String siteId = getInput("     ID (  : S001, S002): ", scanner);
        
        System.out.println("\n  InsertFeedback    ...");
        
        try {
            //          1：      
            System.out.println("  1:       ...");
            
            //       
            FeedbackService.InsertFeedbackResult result = 
                feedbackService.executeInsertFeedback(touristId, siteId);
            
            //     
            System.out.println("\n===        ===");
            System.out.println("  : " + result.isSuccess());
            System.out.println("  : " + result.getMessage());
            
            if (result.isSuccess()) {
                System.out.println("  ID: " + result.getFeedbackId());
                System.out.println("    :               ");
            }
            
        } catch (FeedbackAlreadyReleasedException e) {
            System.out.println("\n===        ===");
            System.out.println("    :   FeedbackAlreadyReleased  ");
            System.out.println("  : " + e.getMessage());
            
        } catch (InvalidFeedbackException e) {
            System.out.println("\n===        ===");
            System.out.println("    :   Errored  ");
            System.out.println("  : " + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("\n===        ===");
            System.out.println("    : " + e.getMessage());
        }
    }
    
    /**
     *             
     * @param feedbackService       
     * @param scanner        
     */
    private static void checkFeedbackEligibility(FeedbackService feedbackService, Scanner scanner) {
        System.out.println("\n===        ===");
        
        String touristId = getInput("     ID: ", scanner);
        String siteId = getInput("     ID: ", scanner);
        
        boolean canProvide = feedbackService.canTouristProvideFeedback(touristId, siteId);
        
        System.out.println("\n        :");
        System.out.println("  : " + touristId);
        System.out.println("  : " + siteId);
        System.out.println("      : " + (canProvide ? " " : " "));
        
        if (!canProvide) {
            System.out.println("     :");
            if (!feedbackService.getDataStorage().touristExists(touristId)) {
                System.out.println("  -      ");
            }
            if (!feedbackService.getDataStorage().siteExists(siteId)) {
                System.out.println("  -      ");
            }
            if (feedbackService.getDataStorage().hasTouristGivenFeedback(touristId, siteId)) {
                System.out.println("  -             ");
            }
        }
    }
    
    /**
     *         
     * @param feedbackService       
     * @param scanner        
     */
    private static void demonstrateExitConditions(FeedbackService feedbackService, Scanner scanner) {
        System.out.println("\n===        ===");
        System.out.println("1.      -       ");
        System.out.println("2.       ");
        System.out.println("3.         (FeedbackAlreadyReleased)");
        System.out.println("4.      (Errored)");
        System.out.println("5.     ");
        System.out.print("           : ");
        
        String choice = scanner.nextLine().trim();
        
        switch (choice) {
            case "1":
                System.out.println("\n  :      -       ");
                //                
                FeedbackService.InsertFeedbackResult result = 
                    feedbackService.processFeedback("T003", "S003");
                System.out.println("  : " + result.getMessage());
                System.out.println("    :              ");
                break;
                
            case "2":
                System.out.println("\n  :       ");
                System.out.println("  :         ，   'cancel'       ");
                System.out.println("    'cancel'        :");
                
                //                 
                System.out.println("      ...");
                FeedbackService.InsertFeedbackResult cancelResult = 
                    feedbackService.processFeedback("T003", "S001");
                System.out.println("  : " + cancelResult.getMessage());
                System.out.println("    :       ");
                break;
                
            case "3":
                System.out.println("\n  :        ");
                System.out.println("  :         ，               ");
                
                //        
                feedbackService.processFeedback("T001", "S001");
                
                //           ，   FeedbackAlreadyReleasedException
                System.out.println("\n             ...");
                FeedbackService.InsertFeedbackResult duplicateResult = 
                    feedbackService.processFeedback("T001", "S001");
                System.out.println("  : " + duplicateResult.getMessage());
                System.out.println("    :   FeedbackAlreadyReleased  ");
                break;
                
            case "4":
                System.out.println("\n  :     ");
                System.out.println("  :            ");
                
                //   ：        FeedbackForm       
                //       ，                
                System.out.println("        1，        （   6    ）    ");
                System.out.println("    :   Errored  ");
                break;
                
            case "5":
                System.out.println("\n  :     ");
                System.out.println("  :        ");
                
                DataStorage dataStorage = feedbackService.getDataStorage();
                FeedbackForm form = new FeedbackForm("T001", "S001");
                
                if (form.checkServerConnection()) {
                    System.out.println("       ");
                } else {
                    System.out.println("       ");
                    System.out.println("    :    ETOUR        ");
                }
                
                form.close();
                break;
                
            default:
                System.out.println("     ");
        }
    }
    
    /**
     *       
     * @param prompt     
     * @param scanner        
     * @return         
     */
    private static String getInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    /**
     *        ，             
     */
    private static void displayUseCaseFlow() {
        System.out.println("\n=== InsertFeedback       ===");
        System.out.println("    :          ");
        System.out.println("  1:       ");
        System.out.println("  2:           ");
        System.out.println("       ├─       →   FeedbackAlreadyReleased  ");
        System.out.println("       └─       →       ");
        System.out.println("  3:     （   +   ）");
        System.out.println("       ├─      →     ");
        System.out.println("       └─      →     4");
        System.out.println("  4:       ");
        System.out.println("       ├─      →   Errored  ");
        System.out.println("       └─      →     5");
        System.out.println("  5:       ");
        System.out.println("  6:               ");
        System.out.println("    :     /    /    ");
    }
}