import java.util.Scanner;

/**
 * SearchForm       ，           。
 *          、       。
 */
public class SearchForm {
    private String searchKeyword;        //      
    private String siteCategory;         //     （  ）
    private int timeoutSeconds = 10;     //     （ ），  10 
    private boolean requireExactMatch;   //         
    
    /**
     *       
     */
    public SearchForm() {
    }
    
    /**
     *         
     * @param searchKeyword      
     * @param siteCategory     
     * @param timeoutSeconds     （ ）
     * @param requireExactMatch         
     */
    public SearchForm(String searchKeyword, String siteCategory, 
                     int timeoutSeconds, boolean requireExactMatch) {
        this.searchKeyword = searchKeyword;
        this.siteCategory = siteCategory;
        this.timeoutSeconds = timeoutSeconds;
        this.requireExactMatch = requireExactMatch;
    }
    
    // Getter Setter  
    
    public String getSearchKeyword() {
        return searchKeyword;
    }
    
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
    
    public String getSiteCategory() {
        return siteCategory;
    }
    
    public void setSiteCategory(String siteCategory) {
        this.siteCategory = siteCategory;
    }
    
    public int getTimeoutSeconds() {
        return timeoutSeconds;
    }
    
    public void setTimeoutSeconds(int timeoutSeconds) {
        this.timeoutSeconds = timeoutSeconds;
    }
    
    public boolean isRequireExactMatch() {
        return requireExactMatch;
    }
    
    public void setRequireExactMatch(boolean requireExactMatch) {
        this.requireExactMatch = requireExactMatch;
    }
    
    /**
     *              
     * @param scanner          Scanner  
     * @return           true，    false
     */
    public boolean displayAndFill(Scanner scanner) {
        System.out.println("===        ===");
        System.out.println("         ：");
        
        //        
        System.out.print("1.       (  ): ");
        String keyword = scanner.nextLine().trim();
        if (keyword.isEmpty()) {
            System.out.println("  ：         ！");
            return false;
        }
        this.searchKeyword = keyword;
        
        //       （  ）
        System.out.print("2.      (  ， Enter  ): ");
        String category = scanner.nextLine().trim();
        if (!category.isEmpty()) {
            this.siteCategory = category;
        } else {
            this.siteCategory = null;
        }
        
        //       
        System.out.print("3.     ( ) (  10 ， Enter     ): ");
        String timeoutInput = scanner.nextLine().trim();
        if (!timeoutInput.isEmpty()) {
            try {
                int timeout = Integer.parseInt(timeoutInput);
                if (timeout <= 0) {
                    System.out.println("  ：        0，      10 ");
                } else {
                    this.timeoutSeconds = timeout;
                }
            } catch (NumberFormatException e) {
                System.out.println("  ：         ，      10 ");
            }
        }
        
        //       
        System.out.print("4.         ? (y/n,   n): ");
        String exactMatchInput = scanner.nextLine().trim().toLowerCase();
        this.requireExactMatch = "y".equals(exactMatchInput) || "yes".equals(exactMatchInput);
        
        System.out.println("      ！");
        return validateForm();
    }
    
    /**
     *           
     * @return           true，    false
     */
    public boolean validateForm() {
        //        
        if (searchKeyword == null || searchKeyword.trim().isEmpty()) {
            System.out.println("    ：         ！");
            return false;
        }
        
        //       
        if (timeoutSeconds <= 0) {
            System.out.println("    ：        0！");
            return false;
        }
        
        //     （     ）
        if (siteCategory != null && siteCategory.length() > 100) {
            System.out.println("  ：      （  100  ），   ");
            siteCategory = siteCategory.substring(0, 100);
        }
        
        //          
        if (searchKeyword.length() > 200) {
            System.out.println("  ：       （  200  ），   ");
            searchKeyword = searchKeyword.substring(0, 200);
        }
        
        return true;
    }
    
    /**
     *     （      ，             ）
     *      ，             
     */
    public void submitForm() {
        System.out.println("\n===        ===");
        System.out.println("     : " + searchKeyword);
        System.out.println("    : " + (siteCategory != null ? siteCategory : "   "));
        System.out.println("    : " + timeoutSeconds + " ");
        System.out.println("    : " + (requireExactMatch ? " " : " "));
        System.out.println("    : " + (validateForm() ? "  " : "  "));
        
        if (validateForm()) {
            System.out.println("     ，    ...");
        } else {
            System.out.println("      ，          ！");
        }
    }
    
    /**
     *             ，         
     * @return           
     */
    @Override
    public String toString() {
        return String.format(
            "SearchForm{keyword='%s', category='%s', timeout=%ds, exactMatch=%b}",
            searchKeyword, siteCategory, timeoutSeconds, requireExactMatch
        );
    }
    
    /**
     *          
     * @return                 true
     */
    public boolean isFilled() {
        return searchKeyword != null && !searchKeyword.trim().isEmpty();
    }
    
    /**
     *          
     */
    public void reset() {
        this.searchKeyword = null;
        this.siteCategory = null;
        this.timeoutSeconds = 10;
        this.requireExactMatch = false;
        System.out.println("     ！");
    }
    
    /**
     *            
     * @return        
     */
    public String getSearchDescription() {
        StringBuilder description = new StringBuilder();
        description.append("     : \"").append(searchKeyword).append("\"");
        
        if (siteCategory != null && !siteCategory.isEmpty()) {
            description.append(",   : ").append(siteCategory);
        }
        
        if (requireExactMatch) {
            description.append(",       ");
        }
        
        description.append(",   : ").append(timeoutSeconds).append(" ");
        return description.toString();
    }
}