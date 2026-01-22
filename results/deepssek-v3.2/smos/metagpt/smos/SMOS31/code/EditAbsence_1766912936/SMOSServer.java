import java.util.Random;

/**
 * SMOSServer  -    SMOS         
 *       、          
 */
public class SMOSServer {
    private String serverAddress;
    private int serverPort;
    private boolean isConnected;
    private Random random;
    
    /**
     *        -          
     */
    public SMOSServer() {
        this.serverAddress = "smos.education.gov";
        this.serverPort = 8080;
        this.isConnected = false;
        this.random = new Random();
    }
    
    /**
     *          
     * @param serverAddress      
     * @param serverPort      
     */
    public SMOSServer(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.isConnected = false;
        this.random = new Random();
    }
    
    /**
     *    SMOS   
     *       ，         
     * @return       
     */
    public boolean connect() {
        System.out.println("     SMOS    " + serverAddress + ":" + serverPort + "...");
        
        //       
        try {
            Thread.sleep(800);
        } catch (InterruptedException e) {
            System.out.println("       。");
            return false;
        }
        
        //   80%      
        if (random.nextDouble() < 0.8) {
            isConnected = true;
            System.out.println("     SMOS   。");
            return true;
        } else {
            System.out.println("  ：     SMOS   。       。");
            return false;
        }
    }
    
    /**
     *         
     * @param data       （JSON  ）
     * @return       
     */
    public boolean sendData(String data) {
        if (!isConnected) {
            System.out.println("  ：       ，      。");
            return false;
        }
        
        if (data == null || data.trim().isEmpty()) {
            System.out.println("  ：        。");
            return false;
        }
        
        System.out.println("   SMOS       ：" + data);
        
        //         
        try {
            Thread.sleep(500);
            
            //   30%      （      ）
            if (random.nextDouble() < 0.3) {
                //         
                simulateConnectionInterruption();
                return false;
            }
            
            //        
            System.out.println("     ：      ，       。");
            return true;
            
        } catch (InterruptedException e) {
            System.out.println("         。");
            return false;
        }
    }
    
    /**
     *         
     */
    private void simulateConnectionInterruption() {
        System.out.println("  ：        ...");
        
        //            
        int interruptionType = random.nextInt(3);
        switch (interruptionType) {
            case 0:
                System.out.println("  ： SMOS          。");
                System.out.println("    ：    ");
                break;
            case 1:
                System.out.println("  ：      。");
                System.out.println("    ：        ");
                break;
            case 2:
                System.out.println("  ：     。");
                System.out.println("    ：     ");
                break;
        }
        
        isConnected = false;
    }
    
    /**
     *         
     */
    public void disconnect() {
        if (isConnected) {
            System.out.println("   SMOS       ...");
            isConnected = false;
            System.out.println("       。");
        } else {
            System.out.println("       ，    。");
        }
    }
    
    /**
     *           （    ）
     * @return        
     */
    public boolean pingServer() {
        System.out.println(" SMOS         ...");
        
        //       
        try {
            Thread.sleep(200);
            
            //   90%    
            if (random.nextDouble() < 0.9) {
                System.out.println("       。");
                return true;
            } else {
                System.out.println("      。");
                return false;
            }
        } catch (InterruptedException e) {
            System.out.println("       。");
            return false;
        }
    }
    
    /**
     *          
     *       ，         
     */
    public void interruptByAdministrator() {
        System.out.println("           。");
        disconnect();
    }
    
    /**
     *     
     * @param maxRetries       
     * @return       
     */
    public boolean reconnect(int maxRetries) {
        System.out.println("       SMOS   ...");
        
        for (int i = 1; i <= maxRetries; i++) {
            System.out.println("   " + i + " ...");
            
            if (connect()) {
                System.out.println("    。");
                return true;
            }
            
            //      
            try {
                Thread.sleep(1000 * i); //       
            } catch (InterruptedException e) {
                System.out.println("       。");
                return false;
            }
        }
        
        System.out.println("         (" + maxRetries + ")，    。");
        return false;
    }
    
    /**
     *        
     * @return       
     */
    public boolean validateConfiguration() {
        if (serverAddress == null || serverAddress.trim().isEmpty()) {
            System.out.println("  ：         。");
            return false;
        }
        
        if (serverPort <= 0 || serverPort > 65535) {
            System.out.println("  ：        。");
            return false;
        }
        
        return true;
    }
    
    // Getter Setter  
    
    public String getServerAddress() {
        return serverAddress;
    }
    
    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }
    
    public int getServerPort() {
        return serverPort;
    }
    
    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }
    
    public boolean isConnected() {
        return isConnected;
    }
    
    /**
     *          
     * @return          
     */
    public String getStatus() {
        return String.format("SMOS   [  : %s:%d,     : %s]", 
                           serverAddress, serverPort, isConnected ? "   " : "   ");
    }
    
    @Override
    public String toString() {
        return String.format("SMOSServer{serverAddress='%s', serverPort=%d, isConnected=%s}",
                           serverAddress, serverPort, isConnected);
    }
}