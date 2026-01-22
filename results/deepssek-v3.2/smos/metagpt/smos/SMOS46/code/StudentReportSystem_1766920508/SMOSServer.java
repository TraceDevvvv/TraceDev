/**
 * SMOSServer.java
 * SMOS    ，   SMOS      
 *       、  、         
 *            
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Date;
import java.text.SimpleDateFormat;

public class SMOSServer {
    private String serverName;                    //      
    private String serverAddress;                 //      
    private int serverPort;                       //      
    private boolean isConnected;                  //     
    private boolean isAvailable;                  //        
    private int connectionTimeout;                //       （  ）
    private int maxRetryAttempts;                 //       
    private List<String> connectionLog;           //     
    private Map<Integer, ReportCard> archivedReports; //        
    private Random random;                        //         
    
    /**
     *       
     */
    public SMOSServer() {
        this.serverName = "SMOS    ";
        this.serverAddress = "smos.example.com";
        this.serverPort = 8080;
        this.isConnected = false;
        this.isAvailable = true;
        this.connectionTimeout = 5000; // 5   
        this.maxRetryAttempts = 3;
        this.connectionLog = new ArrayList<>();
        this.archivedReports = new HashMap<>();
        this.random = new Random();
        
        log("SMOS        ");
    }
    
    /**
     *         
     * @param serverName      
     * @param serverAddress      
     * @param serverPort      
     */
    public SMOSServer(String serverName, String serverAddress, int serverPort) {
        this.serverName = serverName;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.isConnected = false;
        this.isAvailable = true;
        this.connectionTimeout = 5000;
        this.maxRetryAttempts = 3;
        this.connectionLog = new ArrayList<>();
        this.archivedReports = new HashMap<>();
        this.random = new Random();
        
        log("SMOS        : " + serverName + " (" + serverAddress + ":" + serverPort + ")");
    }
    
    /**
     *    SMOS   
     * @return         true，    false
     */
    public boolean connect() {
        if (!isAvailable) {
            log("  ：        ");
            return false;
        }
        
        if (isConnected) {
            log("  ：        ");
            return true;
        }
        
        log("     SMOS   : " + serverAddress + ":" + serverPort);
        
        //       （ 10%       ）
        if (random.nextInt(100) < 10) {
            log("  ：    ，      ");
            isConnected = false;
            return false;
        }
        
        //       
        try {
            //       
            Thread.sleep(1000);
            
            isConnected = true;
            log("     SMOS   ");
            return true;
        } catch (InterruptedException e) {
            log("  ：      ");
            isConnected = false;
            return false;
        }
    }
    
    /**
     *    SMOS      
     * @return         true，    false
     */
    public boolean disconnect() {
        if (!isConnected) {
            log("  ：         ");
            return true;
        }
        
        log("     SMOS      ");
        
        try {
            //         
            Thread.sleep(500);
            
            isConnected = false;
            log("    SMOS      ");
            return true;
        } catch (InterruptedException e) {
            log("  ：        ");
            return false;
        }
    }
    
    /**
     *       
     * @return        true，    false
     */
    public boolean checkConnection() {
        if (!isAvailable) {
            log("      ");
            return false;
        }
        
        //       （ 5%          ）
        if (isConnected && random.nextInt(100) < 5) {
            log("  ：        ");
            isConnected = false;
        }
        
        return isConnected;
    }
    
    /**
     *       SMOS   
     * @param reportCard        
     * @return         true，    false
     */
    public boolean archiveReportCard(ReportCard reportCard) {
        if (reportCard == null) {
            log("  ：        ");
            return false;
        }
        
        if (!checkConnection()) {
            log("  ：    ，    SMOS   ");
            return false;
        }
        
        log("       : ID=" + reportCard.getId() + ",   =" + reportCard.getStudentName());
        
        //       （ 15%       ）
        if (random.nextInt(100) < 15) {
            log("  ：         ，       ");
            isConnected = false; //       
            return false;
        }
        
        try {
            //       
            Thread.sleep(800);
            
            //             
            archivedReports.put(reportCard.getId(), reportCard);
            reportCard.setArchived(true);
            
            log("       : ID=" + reportCard.getId());
            return true;
        } catch (InterruptedException e) {
            log("  ：      ");
            return false;
        }
    }
    
    /**
     *  SMOS            
     * @param reportCardId    ID
     * @return      ，        null
     */
    public ReportCard getArchivedReportCard(int reportCardId) {
        if (!checkConnection()) {
            log("  ：         ，    SMOS   ");
            return null;
        }
        
        log("   SMOS          : ID=" + reportCardId);
        
        //       （ 5%     ）
        if (random.nextInt(100) < 5) {
            log("  ：         ，       ");
            return null;
        }
        
        try {
            //       
            Thread.sleep(300);
            
            ReportCard reportCard = archivedReports.get(reportCardId);
            if (reportCard != null) {
                log("         : ID=" + reportCardId);
            } else {
                log("        : ID=" + reportCardId);
            }
            return reportCard;
        } catch (InterruptedException e) {
            log("  ：           ");
            return null;
        }
    }
    
    /**
     *      SMOS   
     * @param data       
     * @return         true，    false
     */
    public boolean syncData(Object data) {
        if (data == null) {
            log("  ：       ");
            return false;
        }
        
        if (!checkConnection()) {
            log("  ：      ，    SMOS   ");
            return false;
        }
        
        log("       SMOS   : " + data.getClass().getSimpleName());
        
        //       （ 20%       ）
        if (random.nextInt(100) < 20) {
            log("  ：      ，       ");
            isConnected = false; //       
            return false;
        }
        
        try {
            //       
            Thread.sleep(1200);
            
            log("      : " + data.getClass().getSimpleName());
            return true;
        } catch (InterruptedException e) {
            log("  ：        ");
            return false;
        }
    }
    
    /**
     *        
     */
    public void simulateServerFailure() {
        log("  ：       ");
        isAvailable = false;
        isConnected = false;
        
        //        （ 5-15  ）
        new Thread(() -> {
            try {
                int recoveryTime = 5000 + random.nextInt(10000); // 5-15 
                Thread.sleep(recoveryTime);
                
                isAvailable = true;
                log("      ，      ");
            } catch (InterruptedException e) {
                log("          ");
            }
        }).start();
    }
    
    /**
     *          
     * @return         
     */
    public String getServerStatus() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuilder status = new StringBuilder();
        
        status.append("=== SMOS      ===\n");
        status.append("     : ").append(serverName).append("\n");
        status.append("     : ").append(serverAddress).append(":").append(serverPort).append("\n");
        status.append("    : ").append(isConnected ? "   " : "   ").append("\n");
        status.append("     : ").append(isAvailable ? " " : " ").append("\n");
        status.append("    : ").append(connectionTimeout).append("ms\n");
        status.append("      : ").append(maxRetryAttempts).append("\n");
        status.append("        : ").append(archivedReports.size()).append("\n");
        status.append("      : ").append(connectionLog.isEmpty() ? " " : connectionLog.get(connectionLog.size() - 1)).append("\n");
        
        return status.toString();
    }
    
    /**
     *        
     */
    public void displayServerStatus() {
        System.out.println(getServerStatus());
    }
    
    /**
     *           
     * @return         
     */
    public int getArchivedReportCount() {
        return archivedReports.size();
    }
    
    /**
     *            ID  
     * @return    ID  
     */
    public List<Integer> getAllArchivedReportIds() {
        return new ArrayList<>(archivedReports.keySet());
    }
    
    /**
     *           （     ）
     */
    public void clearAllArchivedReports() {
        archivedReports.clear();
        log("          ");
    }
    
    /**
     *          
     * @param available     
     */
    public void setAvailable(boolean available) {
        this.isAvailable = available;
        if (!available) {
            this.isConnected = false;
            log("            ");
        } else {
            log("           ");
        }
    }
    
    /**
     *         
     * @param timeout     （  ）
     */
    public void setConnectionTimeout(int timeout) {
        if (timeout < 1000) {
            log("  ：        ，    1000ms");
        }
        this.connectionTimeout = timeout;
        log("          : " + timeout + "ms");
    }
    
    /**
     *         
     * @param maxRetryAttempts       
     */
    public void setMaxRetryAttempts(int maxRetryAttempts) {
        if (maxRetryAttempts < 1) {
            log("  ：           1");
            this.maxRetryAttempts = 1;
        } else {
            this.maxRetryAttempts = maxRetryAttempts;
        }
        log("          : " + this.maxRetryAttempts);
    }
    
    /**
     *      
     * @return         true，    false
     */
    public boolean reconnect() {
        log("         SMOS   ...");
        
        for (int attempt = 1; attempt <= maxRetryAttempts; attempt++) {
            log("     " + attempt + "/" + maxRetryAttempts);
            
            if (connect()) {
                log("    ");
                return true;
            }
            
            if (attempt < maxRetryAttempts) {
                try {
                    //          
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log("        ");
                    return false;
                }
            }
        }
        
        log("    ，         ");
        return false;
    }
    
    /**
     *     
     * @param message     
     */
    private void log(String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String timestamp = sdf.format(new Date());
        String logEntry = "[" + timestamp + "] " + message;
        
        connectionLog.add(logEntry);
        System.out.println(logEntry);
    }
    
    /**
     *       
     * @return       
     */
    public List<String> getConnectionLog() {
        return new ArrayList<>(connectionLog);
    }
    
    /**
     *          （  n ）
     * @param count         
     * @return        
     */
    public List<String> getRecentConnectionLog(int count) {
        if (count <= 0) {
            return new ArrayList<>();
        }
        
        int startIndex = Math.max(0, connectionLog.size() - count);
        return new ArrayList<>(connectionLog.subList(startIndex, connectionLog.size()));
    }
    
    /**
     *       
     */
    public void clearConnectionLog() {
        connectionLog.clear();
        log("       ");
    }
    
    /**
     *        
     * @return         true，    false
     */
    public boolean testConnection() {
        log("         ...");
        
        if (!isAvailable) {
            log("    ：      ");
            return false;
        }
        
        boolean originalConnectionState = isConnected;
        
        //     （     ）
        if (isConnected) {
            disconnect();
        }
        
        //     
        boolean connectionResult = connect();
        
        //         
        if (originalConnectionState && !connectionResult) {
            //                ，      
            reconnect();
        } else if (!originalConnectionState && connectionResult) {
            //               ，    
            disconnect();
        }
        
        log("         ，  : " + (connectionResult ? "  " : "  "));
        return connectionResult;
    }
    
    /**
     *       ：    
     */
    public void simulateConnectionInterruption() {
        log("      ： SMOS        ");
        isConnected = false;
        isAvailable = false;
        
        //          
        new Thread(() -> {
            try {
                Thread.sleep(8000); // 8    
                isAvailable = true;
                log("     ，       ");
            } catch (InterruptedException e) {
                log("         ");
            }
        }).start();
    }
    
    /**
     *          
     * @return         
     */
    @Override
    public String toString() {
        return "SMOSServer{" +
                "serverName='" + serverName + '\'' +
                ", serverAddress='" + serverAddress + '\'' +
                ", serverPort=" + serverPort +
                ", isConnected=" + isConnected +
                ", isAvailable=" + isAvailable +
                ", archivedReports=" + archivedReports.size() +
                '}';
    }
}