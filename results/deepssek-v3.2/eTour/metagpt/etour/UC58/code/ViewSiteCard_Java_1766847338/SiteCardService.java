// SiteCardService.java
//        ，      ：      、       、             。
//                     ，             。

public class SiteCardService {
    private DatabaseConnector dbConnector;
    private boolean isTouristAuthenticated;  //            
    private String currentArea;              //         （    、       、    ）

    //     
    public SiteCardService() {
        //          （        ）
        this.dbConnector = new DatabaseConnector();
        this.isTouristAuthenticated = false;  //      ，       
        this.currentArea = null;              //         
    }

    /**
     *         
     *     ，     "          "
     *             true
     */
    public void authenticateTourist() {
        this.isTouristAuthenticated = true;
        System.out.println("      ！");
    }

    /**
     *         
     *     ，               ：    、       、    
     * @param area     ：   "Research Results"、"Visited Sites" "Favorites List"
     */
    public void setCurrentArea(String area) {
        //         
        if (area.equals("Research Results") || area.equals("Visited Sites") || area.equals("Favorites List")) {
            this.currentArea = area;
            System.out.println("        : " + area);
        } else {
            System.out.println("  ：     。       ：Research Results, Visited Sites, Favorites List");
            this.currentArea = null;
        }
    }

    /**
     *           
     * @return           true，    false
     */
    private boolean validateEntryConditions() {
        if (!isTouristAuthenticated) {
            System.out.println("  ：     。      。");
            return false;
        }
        
        if (currentArea == null) {
            System.out.println("  ：        。           。");
            return false;
        }
        
        System.out.println("    ：     ，    " + currentArea + "   。");
        return true;
    }

    /**
     *              
     *          ：     ->          ->       
     * @param siteId       ID
     */
    public void viewSiteCard(int siteId) {
        System.out.println("\n===            ===");
        
        // 1.       
        if (!validateEntryConditions()) {
            System.out.println("        ：       。");
            return;
        }
        
        Site site = null;
        boolean success = false;
        
        try {
            // 2.       （  ）
            System.out.println("     ETOUR   ...");
            dbConnector.connect();
            
            // 3.           （      ：          ）
            System.out.println("            ...");
            site = dbConnector.getSiteById(siteId);
            
            if (site != null) {
                // 4.       ，      （      ：             ）
                System.out.println("\n===        ===");
                System.out.println(site.toString());
                System.out.println("=====================\n");
                success = true;
            } else {
                System.out.println("  ：   ID  " + siteId + "    。");
            }
            
        } catch (DatabaseConnectionException e) {
            //         （          ：   ETOUR     ）
            System.out.println("       : " + e.getMessage());
            System.out.println("  ：       ，    。");
            System.out.println("    : ETOUR_CONNECTION_FAILED");
            
        } finally {
            //          
            try {
                dbConnector.disconnect();
            } catch (Exception e) {
                System.out.println("         : " + e.getMessage());
            }
        }
        
        //       
        if (success) {
            System.out.println("        。");
        } else {
            System.out.println("        。");
        }
        System.out.println("===            ===\n");
    }

    /**
     *          ID  ，         
     * @return   ID  ，      null
     */
    public int[] getAvailableSiteIds() {
        try {
            if (!dbConnector.isConnected()) {
                dbConnector.connect();
            }
            return dbConnector.getAllSiteIds();
        } catch (DatabaseConnectionException e) {
            System.out.println("        : " + e.getMessage());
            return null;
        }
    }

    /**
     *     ID    
     * @param siteId       ID
     * @return       true，    false
     */
    public boolean validateSiteId(int siteId) {
        int[] availableIds = getAvailableSiteIds();
        if (availableIds == null) {
            return false;
        }
        
        for (int id : availableIds) {
            if (id == siteId) {
                return true;
            }
        }
        return false;
    }

    /**
     *               ，        
     *            
     */
    public void viewSiteCardWithValidation(int siteId) {
        if (!validateSiteId(siteId)) {
            System.out.println("  ：  ID " + siteId + "       。");
            System.out.println("    ID: ");
            int[] ids = getAvailableSiteIds();
            if (ids != null) {
                for (int id : ids) {
                    System.out.print(id + " ");
                }
                System.out.println();
            }
            return;
        }
        
        viewSiteCard(siteId);
    }

    //     ：        
    public String getCurrentStatus() {
        return "    : " + (isTouristAuthenticated ? "   " : "   ") + 
               ",     : " + (currentArea != null ? currentArea : "   ");
    }
}