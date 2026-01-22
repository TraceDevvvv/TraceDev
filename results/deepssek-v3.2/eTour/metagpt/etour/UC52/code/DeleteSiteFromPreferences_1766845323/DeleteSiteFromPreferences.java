import java.util.List;
import java.util.Scanner;
import java.util.Random;

/**
 *     ，  DeleteSiteFromPreferences       
 *       、    、            
 *                   
 */
public class DeleteSiteFromPreferences {
    private BookmarkManager bookmarkManager;
    private Scanner scanner;
    
    /**
     *     ，              
     */
    public DeleteSiteFromPreferences() {
        this.bookmarkManager = new BookmarkManager();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     *        
     * @param args      （   ）
     */
    public static void main(String[] args) {
        DeleteSiteFromPreferences app = new DeleteSiteFromPreferences();
        app.run();
    }
    
    /**
     *         
     */
    public void run() {
        System.out.println("===            ===");
        System.out.println("  ：DeleteSiteFromPreferences");
        
        //            
        initializeSampleData();
        
        boolean continueRunning = true;
        
        while (continueRunning) {
            try {
                System.out.println("\n===     ===");
                System.out.println("1.       ");
                System.out.println("2.         ");
                System.out.println("3.          ");
                System.out.println("4.          ");
                System.out.println("5.     ");
                System.out.print("      (1-5): ");
                
                String choice = scanner.nextLine();
                
                switch (choice) {
                    case "1":
                        displayBookmarks();
                        break;
                    case "2":
                        deleteSiteFromBookmarks();
                        break;
                    case "3":
                        simulateConnectionInterruption();
                        break;
                    case "4":
                        simulateConnectionRecovery();
                        break;
                    case "5":
                        continueRunning = false;
                        System.out.println("              ，  ！");
                        break;
                    default:
                        System.out.println("     ，   1-5     ");
                }
            } catch (ETourConnectionException e) {
                System.out.println("  : " + e.getMessage());
                System.out.println("     ，           ");
            } catch (Exception e) {
                System.out.println("      : " + e.getMessage());
                System.out.println("   ");
            }
        }
        
        scanner.close();
    }
    
    /**
     *          
     */
    private void initializeSampleData() {
        try {
            //         
            TouristSite site1 = new TouristSite("S001", "     ", "        ", "    ");
            TouristSite site2 = new TouristSite("S002", "     ", "        ", "    ");
            TouristSite site3 = new TouristSite("S003", "     ", "     ", "    ");
            TouristSite site4 = new TouristSite("S004", "   ", "        ", "    ");
            TouristSite site5 = new TouristSite("S005", "     ", "        ", "      ");
            
            //        
            bookmarkManager.addSiteToBookmarks(site1);
            bookmarkManager.addSiteToBookmarks(site2);
            bookmarkManager.addSiteToBookmarks(site3);
            bookmarkManager.addSiteToBookmarks(site4);
            bookmarkManager.addSiteToBookmarks(site5);
            
            System.out.println("    5       ");
        } catch (ETourConnectionException e) {
            System.out.println("              : " + e.getMessage());
        }
    }
    
    /**
     *         
     */
    private void displayBookmarks() {
        try {
            bookmarkManager.displayAllBookmarks();
        } catch (ETourConnectionException e) {
            System.out.println("  : " + e.getMessage());
        }
    }
    
    /**
     *          ：        
     *          ：
     * 1.         
     * 2.     
     * 3.     
     * 4.              
     */
    private void deleteSiteFromBookmarks() {
        System.out.println("\n===          ===");
        
        try {
            // 1.              
            List<TouristSite> sites = bookmarkManager.getAllBookmarkedSites();
            
            if (sites.isEmpty()) {
                System.out.println("      ，        ");
                return;
            }
            
            System.out.println("      ：");
            for (int i = 0; i < sites.size(); i++) {
                TouristSite site = sites.get(i);
                System.out.println((i + 1) + ". ID: " + site.getSiteId() + 
                                 ",   : " + site.getName());
            }
            
            // 2.           （    1：                    ）
            System.out.print("\n            (1-" + sites.size() + ")，   '0'    : ");
            String input = scanner.nextLine();
            
            //           （      ：      ）
            if ("0".equals(input)) {
                System.out.println("     ");
                return;
            }
            
            int selectedIndex;
            try {
                selectedIndex = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("     ，     ");
                return;
            }
            
            if (selectedIndex < 1 || selectedIndex > sites.size()) {
                System.out.println("     ，   1-" + sites.size() + "     ");
                return;
            }
            
            TouristSite selectedSite = sites.get(selectedIndex - 1);
            
            // 3.     （    2：    ）
            System.out.println("\n       ：");
            System.out.println("ID: " + selectedSite.getSiteId());
            System.out.println("  : " + selectedSite.getName());
            System.out.println("  : " + selectedSite.getDescription());
            System.out.println("  : " + selectedSite.getLocation());
            
            // 4.     （    3：    ）
            System.out.print("\n             ？(  'y'    ，       ): ");
            String confirmation = scanner.nextLine();
            
            if (!"y".equalsIgnoreCase(confirmation)) {
                System.out.println("       ");
                return;
            }
            
            // 5.       （    4：             ）
            boolean isDeleted = bookmarkManager.deleteSiteFromBookmarks(selectedSite.getSiteId());
            
            if (isDeleted) {
                //         ：             
                System.out.println("\n===      ===");
                System.out.println("          : " + selectedSite.getName());
                System.out.println("    ：         ");
                
                //         
                int remainingCount = bookmarkManager.getBookmarkCount();
                System.out.println("        : " + remainingCount + "  ");
            } else {
                System.out.println("      ，   ");
            }
            
        } catch (ETourConnectionException e) {
            //         ：   ETOUR     
            System.out.println("  : " + e.getMessage());
            System.out.println("     ，         ");
        }
    }
    
    /**
     *          （        ）
     */
    private void simulateConnectionInterruption() {
        bookmarkManager.setConnectionStatus(false);
        System.out.println("            ");
        System.out.println("            ETourConnectionException");
    }
    
    /**
     *          
     */
    private void simulateConnectionRecovery() {
        bookmarkManager.setConnectionStatus(true);
        System.out.println("          ");
    }
    
    /**
     *        ：    ID      
     *              ，          
     * 
     * @param siteId       ID
     * @return         true，    false
     */
    public boolean deleteSiteById(String siteId) {
        try {
            return bookmarkManager.deleteSiteFromBookmarks(siteId);
        } catch (ETourConnectionException e) {
            System.out.println("    ，  : " + e.getMessage());
            return false;
        }
    }
    
    /**
     *          ，        
     * 
     * @param siteId       ID
     * @param confirm         
     * @return       
     */
    public String deleteSiteSafely(String siteId, boolean confirm) {
        try {
            TouristSite site = bookmarkManager.findSiteById(siteId);
            
            if (site == null) {
                return "   ID  '" + siteId + "'    ";
            }
            
            if (confirm) {
                System.out.println("      : " + site.getName());
                System.out.print("    ？(y/n): ");
                String response = scanner.nextLine();
                
                if (!"y".equalsIgnoreCase(response)) {
                    return "     ";
                }
            }
            
            boolean success = bookmarkManager.deleteSiteFromBookmarks(siteId);
            
            return success ? "      : " + site.getName() : "      ";
            
        } catch (ETourConnectionException e) {
            return "    ，       : " + e.getMessage();
        }
    }
}