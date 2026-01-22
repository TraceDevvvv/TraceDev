import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JustificationRepository -      
 *        ，         、  、       。
 *                ，                  。
 */
public class JustificationRepository {
    
    //   ConcurrentHashMap       ，      
    private final Map<String, Justification> justificationStore;
    
    //   AtomicInteger    ID
    private final AtomicInteger idCounter;
    
    //          
    private boolean isConnected;
    
    /**
     *       
     *         ID   
     */
    public JustificationRepository() {
        this.justificationStore = new ConcurrentHashMap<>();
        this.idCounter = new AtomicInteger(1);
        this.isConnected = true; //       
        
        //              
        initializeSampleData();
    }
    
    /**
     *        
     *       ，             
     */
    private void initializeSampleData() {
        try {
            //           
            LocalDate today = LocalDate.now();
            
            Justification sample1 = new Justification(
                "ABS_001",
                today.minusDays(3),
                "    ，      ",
                "admin001"
            );
            sample1.setJustificationId("JUST_001");
            justificationStore.put(sample1.getJustificationId(), sample1);
            
            Justification sample2 = new Justification(
                "ABS_002",
                today.minusDays(1),
                "      ，      ",
                "admin002"
            );
            sample2.setJustificationId("JUST_002");
            justificationStore.put(sample2.getJustificationId(), sample2);
            
            System.out.println("         ，    " + justificationStore.size() + "      。");
            
        } catch (Exception e) {
            System.out.println("            : " + e.getMessage());
        }
    }
    
    /**
     *       
     *                
     * @param justification           
     * @return true      ，false      
     * @throws RepositoryException            
     */
    public boolean save(Justification justification) throws RepositoryException {
        //          
        checkConnection();
        
        if (justification == null) {
            throw new RepositoryException("          ");
        }
        
        //             
        if (!justification.isValid()) {
            throw new RepositoryException("        ，      ");
        }
        
        try {
            //   ID  ，    ID
            if (justification.getJustificationId() == null || 
                justification.getJustificationId().trim().isEmpty()) {
                String newId = "JUST_" + idCounter.getAndIncrement();
                justification.setJustificationId(newId);
            }
            
            //       
            justification.setUpdatedAt(LocalDate.now());
            
            //        
            justificationStore.put(justification.getJustificationId(), justification);
            
            System.out.println("       : ID=" + justification.getJustificationId());
            
            //          
            simulateDatabaseDelay();
            
            return true;
            
        } catch (Exception e) {
            throw new RepositoryException("        : " + e.getMessage(), e);
        }
    }
    
    /**
     *   ID      
     * @param justificationId     ID
     * @return          ，        null
     * @throws RepositoryException            
     */
    public Justification findById(String justificationId) throws RepositoryException {
        //          
        checkConnection();
        
        if (justificationId == null || justificationId.trim().isEmpty()) {
            throw new RepositoryException("    ID    ");
        }
        
        try {
            //         
            Justification justification = justificationStore.get(justificationId);
            
            //          
            simulateDatabaseDelay();
            
            return justification;
            
        } catch (Exception e) {
            throw new RepositoryException("        : " + e.getMessage(), e);
        }
    }
    
    /**
     *       ID      
     * @param absenceRecordId     ID
     * @return       ，            
     * @throws RepositoryException            
     */
    public List<Justification> findByAbsenceRecordId(String absenceRecordId) throws RepositoryException {
        //          
        checkConnection();
        
        if (absenceRecordId == null || absenceRecordId.trim().isEmpty()) {
            throw new RepositoryException("    ID    ");
        }
        
        try {
            List<Justification> results = new ArrayList<>();
            
            //         ，         ID
            for (Justification justification : justificationStore.values()) {
                if (absenceRecordId.equals(justification.getAbsenceRecordId())) {
                    results.add(justification);
                }
            }
            
            //          
            simulateDatabaseDelay();
            
            return results;
            
        } catch (Exception e) {
            throw new RepositoryException("      ID        : " + e.getMessage(), e);
        }
    }
    
    /**
     *         
     * @return          
     * @throws RepositoryException            
     */
    public List<Justification> findAll() throws RepositoryException {
        //          
        checkConnection();
        
        try {
            List<Justification> results = new ArrayList<>(justificationStore.values());
            
            //          
            simulateDatabaseDelay();
            
            return results;
            
        } catch (Exception e) {
            throw new RepositoryException("          : " + e.getMessage(), e);
        }
    }
    
    /**
     *       
     * @param justification           
     * @return true      ，false      
     * @throws RepositoryException            
     */
    public boolean update(Justification justification) throws RepositoryException {
        //          
        checkConnection();
        
        if (justification == null) {
            throw new RepositoryException("          ");
        }
        
        if (justification.getJustificationId() == null || 
            justification.getJustificationId().trim().isEmpty()) {
            throw new RepositoryException("    ID    ");
        }
        
        //             
        if (!justification.isValid()) {
            throw new RepositoryException("        ，      ");
        }
        
        try {
            //           
            if (!justificationStore.containsKey(justification.getJustificationId())) {
                throw new RepositoryException("       ，ID: " + justification.getJustificationId());
            }
            
            //       
            justification.setUpdatedAt(LocalDate.now());
            
            //       
            justificationStore.put(justification.getJustificationId(), justification);
            
            System.out.println("       : ID=" + justification.getJustificationId());
            
            //          
            simulateDatabaseDelay();
            
            return true;
            
        } catch (Exception e) {
            throw new RepositoryException("        : " + e.getMessage(), e);
        }
    }
    
    /**
     *       
     * @param justificationId     ID
     * @return true      ，false      
     * @throws RepositoryException            
     */
    public boolean delete(String justificationId) throws RepositoryException {
        //          
        checkConnection();
        
        if (justificationId == null || justificationId.trim().isEmpty()) {
            throw new RepositoryException("    ID    ");
        }
        
        try {
            //           
            if (!justificationStore.containsKey(justificationId)) {
                throw new RepositoryException("       ，ID: " + justificationId);
            }
            
            //         
            Justification removed = justificationStore.remove(justificationId);
            
            if (removed != null) {
                System.out.println("       : ID=" + justificationId);
                
                //          
                simulateDatabaseDelay();
                
                return true;
            } else {
                return false;
            }
            
        } catch (Exception e) {
            throw new RepositoryException("        : " + e.getMessage(), e);
        }
    }
    
    /**
     *         
     * @return       
     * @throws RepositoryException            
     */
    public int count() throws RepositoryException {
        //          
        checkConnection();
        
        try {
            return justificationStore.size();
        } catch (Exception e) {
            throw new RepositoryException("          : " + e.getMessage(), e);
        }
    }
    
    /**
     *          
     * @throws RepositoryException          
     */
    private void checkConnection() throws RepositoryException {
        if (!isConnected) {
            throw new RepositoryException("       ，      ");
        }
        
        //            
        if (Math.random() < 0.05) { // 5%         
            isConnected = false;
            throw new RepositoryException("SMOS         ");
        }
    }
    
    /**
     *          
     *       ，            
     */
    private void simulateDatabaseDelay() {
        try {
            //   50-200          
            Thread.sleep(50 + (long)(Math.random() * 150));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     *        
     * @return true      ，false      
     */
    public boolean testConnection() {
        try {
            //       
            Thread.sleep(100);
            
            //           
            if (Math.random() < 0.1) { // 10%         
                isConnected = false;
                return false;
            }
            
            isConnected = true;
            return true;
            
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            isConnected = false;
            return false;
        }
    }
    
    /**
     *          
     * @return true      ，false      
     */
    public boolean isConnected() {
        return isConnected;
    }
    
    /**
     *          
     * @param connected     
     */
    public void setConnected(boolean connected) {
        this.isConnected = connected;
    }
    
    /**
     *       （     ）
     *       ，               
     */
    public void clearAll() {
        justificationStore.clear();
        idCounter.set(1);
        System.out.println("       ");
    }
    
    /**
     *        
     *                 
     */
    public static class RepositoryException extends Exception {
        public RepositoryException(String message) {
            super(message);
        }
        
        public RepositoryException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}