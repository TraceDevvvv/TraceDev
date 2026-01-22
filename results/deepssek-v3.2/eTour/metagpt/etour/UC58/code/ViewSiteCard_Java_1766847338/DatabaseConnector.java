// DatabaseConnector.java
//       ，          ，      。
//         ，          ，         。

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DatabaseConnector {
    //           ，    ID，  Site  
    private static Map<Integer, Site> siteDatabase = new HashMap<>();
    
    //       ，        
    private static Random random = new Random();
    
    //        
    private boolean isConnected;
    
    //        URL、      （           ）
    private String url;
    private String username;
    private String password;
    
    //       ，      
    static {
        //           
        siteDatabase.put(1, new Site(1, "     ", "            ， 324 。", "  ，  ", 4.8, 10000, true));
        siteDatabase.put(2, new Site(2, "  ", "           ，      。", "  ，  ", 4.9, 15000, true));
        siteDatabase.put(3, new Site(3, "     ", "             。", "  ，  ", 4.7, 8000, false));
        siteDatabase.put(4, new Site(4, "   ", "        ，        。", "  ，  ", 4.6, 6000, false));
        siteDatabase.put(5, new Site(5, "     ", "               。", "  ，    ", 4.5, 7000, true));
    }
    
    //     ，       
    public DatabaseConnector(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.isConnected = false;
    }
    
    //       ，        （     ）
    public DatabaseConnector() {
        this("jdbc:mysql://localhost:3306/etour_db", "tourist_user", "password123");
    }
    
    //       （  ）
    public void connect() throws DatabaseConnectionException {
        //       ： 20%       ，         
        if (random.nextDouble() < 0.2) {
            throw new DatabaseConnectionException("  ETOUR     ：       。          。");
        }
        
        //       
        System.out.println("        : " + url);
        //          JDBC    
        //   ：Connection conn = DriverManager.getConnection(url, username, password);
        
        //       
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        isConnected = true;
        System.out.println("       ！");
    }
    
    //        （  ）
    public void disconnect() {
        if (isConnected) {
            System.out.println("         ...");
            isConnected = false;
            System.out.println("        。");
        }
    }
    
    //       （  ）
    public Site getSiteById(int siteId) throws DatabaseConnectionException {
        //        
        if (!isConnected) {
            throw new DatabaseConnectionException("      。    connect()      。");
        }
        
        //       ： 10%       ，           
        if (random.nextDouble() < 0.1) {
            throw new DatabaseConnectionException("         。   。");
        }
        
        //       
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        //            
        Site site = siteDatabase.get(siteId);
        
        if (site == null) {
            System.out.println("   ID  " + siteId + "    。");
            return null;
        }
        
        System.out.println("          : " + site.getName());
        return site;
    }
    
    //       ID  （        ）
    public int[] getAllSiteIds() throws DatabaseConnectionException {
        if (!isConnected) {
            throw new DatabaseConnectionException("      。    connect()      。");
        }
        
        //       
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        //       ID
        int[] ids = new int[siteDatabase.size()];
        int i = 0;
        for (Integer id : siteDatabase.keySet()) {
            ids[i] = id;
            i++;
        }
        return ids;
    }
    
    //         
    public boolean isConnected() {
        return isConnected;
    }
}

//            
class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(String message) {
        super(message);
    }
}