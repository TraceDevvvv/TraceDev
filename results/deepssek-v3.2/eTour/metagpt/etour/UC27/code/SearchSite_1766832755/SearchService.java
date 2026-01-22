import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * SearchService       ，  ：
 * 1.       
 * 2.     （       "             "）
 * 3.       （      "  ：    ETOUR     "）
 * 4.        
 */
public class SearchService {
    //        （      ，            API）
    private List<Site> siteDatabase;
    
    /**
     *      -        
     */
    public SearchService() {
        initializeMockData();
    }
    
    /**
     *          
     */
    private void initializeMockData() {
        siteDatabase = new ArrayList<>();
        
        //           
        siteDatabase.add(new Site(1, "Google", "https://www.google.com", 
                                 "         ", "    "));
        siteDatabase.add(new Site(2, "GitHub", "https://github.com", 
                                 "      ", "    "));
        siteDatabase.add(new Site(3, "StackOverflow", "https://stackoverflow.com", 
                                 "       ", "    "));
        siteDatabase.add(new Site(4, "Wikipedia", "https://www.wikipedia.org", 
                                 "       ", "    "));
        siteDatabase.add(new Site(5, "YouTube", "https://www.youtube.com", 
                                 "      ", "    "));
        siteDatabase.add(new Site(6, "Google Drive", "https://drive.google.com", 
                                 "     ", "   "));
        siteDatabase.add(new Site(7, "Google Maps", "https://maps.google.com", 
                                 "      ", "    "));
        siteDatabase.add(new Site(8, "ETOUR Server", "https://etour.example.com", 
                                 "ETOUR     ", "    "));
        siteDatabase.add(new Site(9, "ETOUR API", "https://api.etour.com", 
                                 "ETOUR API  ", "    "));
        siteDatabase.add(new Site(10, "Java Documentation", "https://docs.oracle.com/javase", 
                                 "Java    ", "    "));
    }
    
    /**
     *       （     ）
     * @param form          
     * @return         
     * @throws SearchException            （  、     ）
     */
    public List<Site> searchSites(SearchForm form) throws SearchException {
        if (form == null || !form.validateForm()) {
            throw new SearchException("       ");
        }
        
        System.out.println("    : " + form.getSearchDescription());
        
        //          
        Callable<List<Site>> searchTask = () -> {
            return performSearch(form);
        };
        
        //            ，      
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<List<Site>> future = executor.submit(searchTask);
        
        try {
            //                 
            return future.get(form.getTimeoutSeconds(), TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            //      -     
            future.cancel(true);
            throw new SearchException("    （" + form.getTimeoutSeconds() + "     ）", 
                                     SearchException.ErrorType.TIMEOUT);
        } catch (InterruptedException e) {
            //       
            Thread.currentThread().interrupt(); //       
            throw new SearchException("     ", SearchException.ErrorType.INTERRUPTED);
        } catch (ExecutionException e) {
            //         
            Throwable cause = e.getCause();
            if (cause instanceof ConnectionException) {
                throw new SearchException("     ETOUR     : " + cause.getMessage(), 
                                         SearchException.ErrorType.CONNECTION_ERROR);
            } else {
                throw new SearchException("         : " + cause.getMessage(), 
                                         SearchException.ErrorType.GENERAL_ERROR);
            }
        } finally {
            //      
            executor.shutdownNow();
        }
    }
    
    /**
     *          （              ）
     * @param form     
     * @return       
     * @throws ConnectionException       
     */
    private List<Site> performSearch(SearchForm form) throws ConnectionException {
        String keyword = form.getSearchKeyword().toLowerCase();
        String category = form.getSiteCategory();
        boolean exactMatch = form.isRequireExactMatch();
        
        List<Site> results = new ArrayList<>();
        
        //       （  0.5-2 ）
        simulateNetworkDelay();
        
        //           （ETOUR        ）
        if (shouldSimulateConnectionError(keyword)) {
            throw new ConnectionException(" ETOUR        ");
        }
        
        //            
        for (Site site : siteDatabase) {
            boolean matches = false;
            
            if (exactMatch) {
                //     ：             （     ）
                matches = (site.getName().equalsIgnoreCase(keyword) ||
                          site.getUrl().equalsIgnoreCase(keyword) ||
                          (site.getDescription() != null && site.getDescription().equalsIgnoreCase(keyword)) ||
                          (site.getCategory() != null && site.getCategory().equalsIgnoreCase(keyword)));
            } else {
                //     ：           
                matches = site.matchesKeyword(keyword);
            }
            
            //        ，         
            if (matches && category != null && !category.trim().isEmpty()) {
                String siteCategory = site.getCategory();
                if (siteCategory == null || !siteCategory.toLowerCase().contains(category.toLowerCase())) {
                    matches = false;
                }
            }
            
            if (matches) {
                results.add(site);
            }
        }
        
        return results;
    }
    
    /**
     *       （          ）
     */
    private void simulateNetworkDelay() {
        try {
            //     500-2000  
            long delay = 500 + (long)(Math.random() * 1500);
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("         ", e);
        }
    }
    
    /**
     *           
     * @param keyword      
     * @return     ETOUR    ， 20%        
     */
    private boolean shouldSimulateConnectionError(String keyword) {
        //          "etour"，         
        if (keyword.contains("etour")) {
            return Math.random() < 0.2; // 20%  
        }
        return false;
    }
    
    /**
     *       （       ）
     * @return          
     */
    public List<Site> getAllSites() {
        return new ArrayList<>(siteDatabase);
    }
    
    /**
     *          （    ）
     * @param site       
     */
    public void addSite(Site site) {
        if (site != null) {
            siteDatabase.add(site);
        }
    }
    
    /**
     *   ID    
     * @param id   ID
     * @return      ，     null
     */
    public Site findSiteById(int id) {
        for (Site site : siteDatabase) {
            if (site.getId() == id) {
                return site;
            }
        }
        return null;
    }
    
    /**
     *       
     * @return     
     */
    public int getSiteCount() {
        return siteDatabase.size();
    }
    
    /**
     *      ，            
     */
    public static class SearchException extends Exception {
        public enum ErrorType {
            TIMEOUT,            //     
            CONNECTION_ERROR,   //     （  ETOUR    ）
            INTERRUPTED,        //     
            VALIDATION_ERROR,   //     
            GENERAL_ERROR       //     
        }
        
        private final ErrorType errorType;
        
        public SearchException(String message) {
            super(message);
            this.errorType = ErrorType.GENERAL_ERROR;
        }
        
        public SearchException(String message, ErrorType errorType) {
            super(message);
            this.errorType = errorType;
        }
        
        public SearchException(String message, ErrorType errorType, Throwable cause) {
            super(message, cause);
            this.errorType = errorType;
        }
        
        public ErrorType getErrorType() {
            return errorType;
        }
        
        /**
         *                
         * @return             true
         */
        public boolean isCriticalError() {
            return errorType == ErrorType.CONNECTION_ERROR || 
                   errorType == ErrorType.TIMEOUT;
        }
        
        /**
         *            
         * @return        
         */
        public String getUserFriendlyMessage() {
            switch (errorType) {
                case TIMEOUT:
                    return "    ，                 ";
                case CONNECTION_ERROR:
                    return "          ，     ";
                case INTERRUPTED:
                    return "       ，     ";
                case VALIDATION_ERROR:
                    return "      ，      ";
                default:
                    return "         : " + getMessage();
            }
        }
    }
    
    /**
     *      ，   ETOUR        
     */
    public static class ConnectionException extends Exception {
        public ConnectionException(String message) {
            super(message);
        }
        
        public ConnectionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    /**
     *            
     * @param args      
     */
    public static void main(String[] args) {
        try {
            SearchService service = new SearchService();
            
            //           
            SearchForm form = new SearchForm("google", "    ", 5, false);
            
            //     
            System.out.println("      ...");
            List<Site> results = service.searchSites(form);
            
            //     
            System.out.println("   " + results.size() + "    :");
            for (Site site : results) {
                System.out.println("  - " + site.getName() + " (" + site.getUrl() + ")");
            }
            
        } catch (SearchException e) {
            System.err.println("    : " + e.getUserFriendlyMessage());
            e.printStackTrace();
        }
    }
}