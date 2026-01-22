import java.util.List;
import java.util.Scanner;

/**
 * SearchSite   -            
 *     ：SearchSite -            
 *   ：
 * 1.          
 * 2.       
 * 3.          
 * 4.     
 *     ：           
 *     ：    ETOUR     
 *     ：                
 */
public class SearchSite {
    
    /**
     *     -      
     *          ：
     * 1.       
     * 2.          
     * 3.          
     * 4.       
     * 
     * @param args      （   ）
     */
    public static void main(String[] args) {
        System.out.println("===        ===");
        System.out.println("     ...\n");
        
        // 1.       
        activateSearchFunction();
        
        // 2.              
        SearchForm searchForm = displayAndFillSearchForm();
        
        //          ，    
        if (searchForm == null || !searchForm.isFilled()) {
            System.out.println("      ，    。");
            return;
        }
        
        // 3.          
        List<Site> searchResults = processSearchRequest(searchForm);
        
        // 4.       
        displaySearchResults(searchResults, searchForm);
        
        System.out.println("\n===        ===");
    }
    
    /**
     *   1：         
     *        ，      
     */
    private static void activateSearchFunction() {
        System.out.println("  1:       ...");
        System.out.println("         ...");
        
        //       ，      ：
        // -        
        // -       
        // -       
        // -        
        
        System.out.println("       ，    ！\n");
    }
    
    /**
     *   2：         
     *         ，      ，      
     * 
     * @return      SearchForm  ，      null
     */
    private static SearchForm displayAndFillSearchForm() {
        System.out.println("  2:       ...");
        
        //   Scanner          
        Scanner scanner = new Scanner(System.in);
        
        //         
        SearchForm searchForm = new SearchForm();
        
        try {
            //            
            boolean formFilledSuccessfully = searchForm.displayAndFill(scanner);
            
            if (!formFilledSuccessfully) {
                System.out.println("      ，       。");
                return null;
            }
            
            //         
            searchForm.submitForm();
            System.out.println();
            
            return searchForm;
            
        } catch (Exception e) {
            System.err.println("           : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     *   3：      
     *   SearchService    ，          
     * 
     * @param searchForm          
     * @return       ，        null
     */
    private static List<Site> processSearchRequest(SearchForm searchForm) {
        System.out.println("  3:       ...");
        System.out.println("      ，   ...");
        
        //         
        SearchService searchService = new SearchService();
        
        try {
            //     （           ）
            List<Site> results = searchService.searchSites(searchForm);
            
            System.out.println("        ！");
            return results;
            
        } catch (SearchService.SearchException e) {
            //                 
            handleSearchException(e);
            return null;
        } catch (Exception e) {
            //           
            System.err.println("             : " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     *       ，           
     * 
     * @param e       
     */
    private static void handleSearchException(SearchService.SearchException e) {
        System.err.println("\n===      ===");
        System.err.println("    : " + e.getErrorType());
        System.err.println("    : " + e.getUserFriendlyMessage());
        
        //                
        switch (e.getErrorType()) {
            case TIMEOUT:
                System.err.println("  :        ，          ");
                break;
            case CONNECTION_ERROR:
                System.err.println("  :     ETOUR      ，    ");
                System.err.println("  :         '    ETOUR     '  ");
                break;
            case INTERRUPTED:
                System.err.println("  :        ，       ");
                break;
            case VALIDATION_ERROR:
                System.err.println("  :              ");
                break;
            default:
                System.err.println("  :      ，        ");
        }
        
        //        ，        
        if (e.getErrorType() == SearchService.SearchException.ErrorType.CONNECTION_ERROR) {
            System.err.println("\n        :");
            System.err.println("-     : ETOUR       ");
            System.err.println("-     :     、     、     ");
            System.err.println("-     :       ，       ，       ");
        }
    }
    
    /**
     *   4：      
     *                   
     * 
     * @param results       
     * @param searchForm        
     */
    private static void displaySearchResults(List<Site> results, SearchForm searchForm) {
        System.out.println("\n  4:       ...");
        System.out.println("========================================");
        System.out.println("    : " + searchForm.getSearchDescription());
        System.out.println("========================================\n");
        
        if (results == null) {
            System.out.println("    ，      。");
            return;
        }
        
        if (results.isEmpty()) {
            System.out.println("        。");
            System.out.println("  :                  。");
        } else {
            System.out.println("   " + results.size() + "       :");
            System.out.println();
            
            //            
            for (int i = 0; i < results.size(); i++) {
                Site site = results.get(i);
                System.out.println((i + 1) + ". " + site.getName());
                System.out.println("     : " + site.getUrl());
                System.out.println("     : " + site.getDescription());
                System.out.println("     : " + site.getCategory());
                
                //   URL     
                if (site.isValidUrl()) {
                    System.out.println("   URL  :     ");
                } else {
                    System.out.println("   URL  :     （  http:// https://）");
                }
                
                System.out.println();
            }
            
            //       
            System.out.println("========================================");
            System.out.println("      :");
            System.out.println("-     : " + results.size());
            System.out.println("-     : " + (searchForm.isRequireExactMatch() ? "    " : "    "));
            System.out.println("-     : " + searchForm.getTimeoutSeconds() + " ");
            
            //      ETOUR    
            int etourCount = 0;
            for (Site site : results) {
                if (site.getName().toLowerCase().contains("etour") || 
                    site.getUrl().toLowerCase().contains("etour")) {
                    etourCount++;
                }
            }
            
            if (etourCount > 0) {
                System.out.println("- ETOUR    : " + etourCount + " ");
                System.out.println("    : ETOUR          ，         ");
            }
        }
        
        System.out.println("========================================");
    }
    
    /**
     *     ：         
     *            
     */
    public static void demonstrateSearchProcess() {
        System.out.println("\n===        ===");
        
        //         
        SearchForm demoForm = new SearchForm("Google", "    ", 5, false);
        
        System.out.println("      :");
        System.out.println("-    : Google");
        System.out.println("-   :     ");
        System.out.println("-   : 5 ");
        System.out.println("-     :  ");
        
        //       
        SearchService service = new SearchService();
        
        try {
            System.out.println("\n      ...");
            List<Site> results = service.searchSites(demoForm);
            
            System.out.println("      :");
            if (results != null && !results.isEmpty()) {
                for (Site site : results) {
                    System.out.println("  - " + site.getName() + ": " + site.getUrl());
                }
            } else {
                System.out.println("       ");
            }
            
        } catch (SearchService.SearchException e) {
            System.err.println("      : " + e.getUserFriendlyMessage());
        }
        
        System.out.println("===      ===\n");
    }
    
    /**
     *         
     */
    public static void showUsage() {
        System.out.println("\n===            ===");
        System.out.println("  :            ");
        System.out.println("  :");
        System.out.println("  1.         ");
        System.out.println("  2.            ");
        System.out.println("  3.       （      ）");
        System.out.println("  4.         ");
        System.out.println("\n    :");
        System.out.println("  -                ");
        System.out.println("  -    ETOUR        ");
        System.out.println("  -               ");
        System.out.println("\n    :");
        System.out.println("  -          ");
        System.out.println("  -         0 ");
        System.out.println("  -   ETOUR             ");
        System.out.println("========================================\n");
    }
}