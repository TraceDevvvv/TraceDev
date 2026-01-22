/**
 * Administrator  -        
 *              
 */
public class Administrator {
    private String id;
    private String name;
    private boolean isLoggedIn;
    
    /**
     *     
     * @param id    ID
     * @param name      
     */
    public Administrator(String id, String name) {
        this.id = id;
        this.name = name;
        this.isLoggedIn = false;
    }
    
    /**
     *     
     * @return       true，    false
     */
    public boolean login() {
        //       ，          
        //      ，        
        this.isLoggedIn = true;
        System.out.println("    " + name + " (ID: " + id + ")    。");
        return true;
    }
    
    /**
     *     
     */
    public void logout() {
        this.isLoggedIn = false;
        System.out.println("    " + name + "    。");
    }
    
    /**
     *           
     * @return     
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }
    
    /**
     *      ID
     * @return    ID
     */
    public String getId() {
        return id;
    }
    
    /**
     *        
     * @return      
     */
    public String getName() {
        return name;
    }
    
    /**
     *        
     * @param name    
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     *          
     * @param operation     
     * @return       
     */
    public boolean performOperation(String operation) {
        if (!isLoggedIn) {
            System.out.println("  ：      ，      。");
            return false;
        }
        
        System.out.println("    " + name + "       ：" + operation);
        return true;
    }
    
    /**
     *          
     * @param operation       
     */
    public void interruptOperation(String operation) {
        System.out.println("    " + name + "      ：" + operation);
    }
    
    @Override
    public String toString() {
        return "Administrator{id='" + id + "', name='" + name + "', isLoggedIn=" + isLoggedIn + "}";
    }
}